package com.rbinnovative.scrollingapp.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.rbinnovative.scrollingapp.R;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailedToolActivity extends AppCompatActivity {

    @BindView(R.id.date_picker)
    DatePicker datePicker;
    @BindView(R.id.date_selected_text_view)
    TextView dateValueTextView;
    @BindView(R.id.update_date_button)
    Button updateDateButton;

//    @Inject
//    ToolService toolService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        ((MainApplication) getApplicationContext()).appComponent.inject(this);
        setContentView(R.layout.activity_detailed_tool);
        ButterKnife.bind(this);
        prepareUi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepareUi() {

        // disable dates before today
        Calendar today = Calendar.getInstance();
        long now = today.getTimeInMillis();
        datePicker.setMinDate(now);
        updateDateButton.setOnClickListener(v -> onClickDate());
//        setSupportActionBar(toolbar);
    }

    private void onClickDate() {
        selectReservationDate();
        dateValueTextView.setText("Selected date: " + (datePicker.getMonth() + 1) + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear());
    }

    private void selectReservationDate() {

        CalendarConstraints.DateValidator validators = createValidators();
        CalendarConstraints constraints = new CalendarConstraints.Builder()
                .setValidator(validators)
                .setStart(System.currentTimeMillis())
                .setEnd(System.currentTimeMillis())
                .build();

        MaterialDatePicker<Pair<Long, Long>> pickerRange = MaterialDatePicker.Builder.dateRangePicker()
                .setCalendarConstraints(constraints)
                .setSelection(new Pair(System.currentTimeMillis(),System.currentTimeMillis()))
                .build();

        pickerRange.show(getSupportFragmentManager(), pickerRange.toString());
    }

    private CalendarConstraints.DateValidator createValidators() {
        LocalTime localHour = LocalTime.of(1, 1, 1);
        long minDate = LocalDateTime.of(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue(), 1), localHour).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long maxDate = LocalDateTime.of(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue(), 28), localHour).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        ArrayList<CalendarConstraints.DateValidator> listValidators =
                new ArrayList<>();

        listValidators.add(DateValidatorPointForward.from(minDate));
        listValidators.add(DateValidatorPointBackward.before(maxDate));
        listValidators.add(new WeekDayValidator());
        listValidators.add(new RestrictiveDateValidator());
        return CompositeDateValidator.allOf(listValidators);
    }
}
