package com.rbinnovative.rentalotool.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.util.Pair;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.rbinnovative.rentalotool.R;
import com.rbinnovative.rentalotool.model.Order;
import com.rbinnovative.rentalotool.model.RentalDatePick;
import com.rbinnovative.rentalotool.model.Tool;
import com.rbinnovative.rentalotool.service.web.RentaloToolClient;
import com.rbinnovative.rentalotool.ui.validator.RestrictiveDateValidator;
import com.rbinnovative.rentalotool.ui.validator.WeekDayValidator;
import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rbinnovative.rentalotool.utils.Constants.ACTIVITY_MAPPING_CURRENT_TOOL;
import static com.rbinnovative.rentalotool.utils.Constants.ACTIVITY_MAPPING_USER_ID;


public class DetailedToolActivity extends AppCompatActivity {

    @BindView(R.id.date_selected_text_view)
    TextView dateValueTextView;
    @BindView(R.id.dateMaxValueSelected)
    TextView dateValueMaxValueSelected;
    @BindView(R.id.update_date_button)
    Button updateDateButton;
    @BindView(R.id.reservationButton)
    AppCompatButton reservationButton;
    @BindView(R.id.details_tool_image)
    ImageView detailsToolImage;
    @BindView(R.id.details_tool_text)
    TextView toolName;
    @BindView(R.id.details_tools_description)
    TextView toolDescription;
    @BindView(R.id.imageView3)
    ImageView rentaloToolLogoImage;
    @Inject
    RentaloToolClient rentaloToolClient;
    private GoogleSignInClient googleSignInClient;
    private String[] toolAvailability;
    private Tool tool;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplicationContext()).appComponent.inject(this);
        setContentView(R.layout.tool_detail_reservation);
        ButterKnife.bind(this);
        prepareGoogleSignIn();
        if (getIntent().hasExtra(ACTIVITY_MAPPING_CURRENT_TOOL)) {
            this.tool = getIntent().getParcelableExtra(ACTIVITY_MAPPING_CURRENT_TOOL);
            Picasso.get()
                    .load(tool.getImageUrl())
                    .into(detailsToolImage);
            this.toolName.setText(tool.getName());
            this.toolDescription.setText(tool.getDescription());
        }
        if (getIntent().hasExtra(ACTIVITY_MAPPING_USER_ID)) {
            this.currentUserId = (String) getIntent().getExtras().get(ACTIVITY_MAPPING_USER_ID);
        }
        rentaloToolLogoImage.setOnClickListener((click) -> {
            Intent toolsLandingActivityIntent = new Intent(this.getApplicationContext(), LandingScrollingActivity.class);
            toolsLandingActivityIntent.putExtra(ACTIVITY_MAPPING_USER_ID, currentUserId);
            startActivity(toolsLandingActivityIntent);
        });
        prepareUi(tool.getId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.logout_menu_item) {
            googleSignInClient.signOut()
                    .addOnCompleteListener(this, task -> runOnUiThread(() -> {
                        if (task.isSuccessful()) {
                            Intent toolsLandingActivityIntent = new Intent(this.getApplicationContext(), LoginActivity.class);
                            toolsLandingActivityIntent.putExtra(ACTIVITY_MAPPING_USER_ID, currentUserId);
                            startActivity(toolsLandingActivityIntent);
                        }
                    }));
        } else if (id == R.id.orders_menu_item) {
            Intent orderActivityIntent = new Intent(this.getApplicationContext(), OrdersActivity.class);
            orderActivityIntent.putExtra(ACTIVITY_MAPPING_USER_ID, currentUserId);
            startActivity(orderActivityIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepareUi(Integer id) {
        toolAvailability = new String[0];
        rentaloToolClient.retrieveToolAvailability(id,
                ((successRetrievedTools) -> runOnUiThread(() -> toolAvailability = successRetrievedTools)),
                ((failureRetrieved) -> runOnUiThread(() -> toolAvailability = failureRetrieved)));
        updateDateButton.setOnClickListener(v -> selectReservationDate());
        reservationButton.setOnClickListener(v -> makeReservation());
    }

    private void prepareGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void makeReservation() {
        Order order = new Order("pending", currentUserId, tool.getId(),rentalDatePick.getStartDate(), rentalDatePick.getEndDate());
        rentaloToolClient.makeReservation(order,
                ((successRetrievedTools) -> runOnUiThread(() -> {
                    Intent toolsLandingActivityIntent = new Intent(this.getApplicationContext(), LandingScrollingActivity.class);
                    toolsLandingActivityIntent.putExtra(ACTIVITY_MAPPING_USER_ID, currentUserId);
                    startActivity(toolsLandingActivityIntent);
                })),
                ((failureRetrieved) -> runOnUiThread(() -> reservationButton.setEnabled(false))));
    }

    private void selectReservationDate() {

        CalendarConstraints.DateValidator validators = createValidators();
        CalendarConstraints constraints = new CalendarConstraints.Builder()
                .setValidator(validators)
                .setStart(System.currentTimeMillis())
                .setEnd(LocalDateTime.now().plusMonths(1)
                        .atZone(ZoneId.ofOffset("UTC", ZoneOffset.UTC)).toInstant().toEpochMilli())
                .build();

        MaterialDatePicker<Pair<Long, Long>> pickerRange = MaterialDatePicker.Builder.dateRangePicker()
                .setCalendarConstraints(constraints)
                .setSelection(new Pair(System.currentTimeMillis(), System.currentTimeMillis()))
                .build();
        pickerRange.show(getSupportFragmentManager(), pickerRange.toString());
        pickerRange.addOnPositiveButtonClickListener(selection -> {
            LocalDate startDate = Instant.ofEpochMilli(selection.first).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = Instant.ofEpochMilli(selection.second).atZone(ZoneId.systemDefault()).toLocalDate();
            dateValueTextView.setText("Rental starts: " + startDate.getDayOfMonth() + "/" + startDate.getMonth() + "/" + startDate.getYear());
            dateValueMaxValueSelected.setText("Rental ends: " + endDate.getDayOfMonth() + "/" + endDate.getMonth() + "/" + endDate.getYear());
            rentalDatePick = new RentalDatePick(startDate, endDate);
            reservationButton.setEnabled(true);
        });
    }

    private CalendarConstraints.DateValidator createValidators() {
        //TOOD: fix date bug for last day
        LocalTime localHour = LocalTime.of(1, 1, 1);
        long minDate = LocalDateTime.of(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue(), 1), localHour).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long maxDate = LocalDateTime.of(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue() +1, LocalDate.now().lengthOfMonth()), localHour).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        ArrayList<CalendarConstraints.DateValidator> listValidators =
                new ArrayList<>();

        listValidators.add(DateValidatorPointForward.from(minDate));
        listValidators.add(DateValidatorPointBackward.before(maxDate));
        listValidators.add(new WeekDayValidator());
        List<LocalDate> toolsDate = Stream.of(toolAvailability).map(LocalDate::parse).collect(Collectors.toList());
        listValidators.add(new RestrictiveDateValidator(toolsDate));
        return CompositeDateValidator.allOf(listValidators);
    }
}
