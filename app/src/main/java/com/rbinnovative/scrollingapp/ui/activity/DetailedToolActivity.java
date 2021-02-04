package com.rbinnovative.scrollingapp.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.rbinnovative.scrollingapp.R;
import com.rbinnovative.scrollingapp.service.RangeValidator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
        setupRangePickerDialog();
        dateValueTextView.setText("Selected date: " + (datePicker.getMonth() + 1) + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear());
    }

    private void setupRangePickerDialog() {
        asd();
//        MaterialDatePicker.Builder<Pair<Long, Long>> builderRange = MaterialDatePicker.Builder.dateRangePicker();
//        builderRange.setCalendarConstraints(limitRange().build());
//        MaterialDatePicker<Pair<Long, Long>> pickerRange = builderRange.build();
//        pickerRange.show(this.getSupportFragmentManager(), pickerRange.toString());
    }

    /*
   Limit selectable range to Oct 17 - Nov 20 2019
    */
    private CalendarConstraints.Builder limitRange()  {

        CalendarConstraints.Builder constraintsBuilderRange = new CalendarConstraints.Builder();
        Calendar calendarStart  = GregorianCalendar.getInstance();
        Calendar calendarEnd  = GregorianCalendar.getInstance();

        int year = 2019;

        calendarStart.set(year, 10, 17);
        calendarEnd.set(year, 11, 20);

        long minDate = calendarStart.getTimeInMillis();
        long maxDate = calendarEnd.getTimeInMillis();

        constraintsBuilderRange.setStart(minDate);
        constraintsBuilderRange.setEnd(maxDate);

//        constraintsBuilderRange.setValidator(new RangeValidator(minDate, maxDate));

        return constraintsBuilderRange;
    }

    private void asd(){
        MaterialDatePicker.Builder<Pair<Long, Long>> builderRange = MaterialDatePicker.Builder.dateRangePicker();
        CalendarConstraints.Builder constraintsBuilderRange = new CalendarConstraints.Builder();

//....define min and max for example with LocalDateTime and ZonedDateTime or Calendar
        Calendar calendarStart  = GregorianCalendar.getInstance();
        Calendar calendarEnd  = GregorianCalendar.getInstance();

        int year = 2019;

        calendarStart.set(year, 10, 17);
        calendarEnd.set(year, 11, 20);

        long minDate = calendarStart.getTimeInMillis();
        long maxDate = calendarEnd.getTimeInMillis();


        CalendarConstraints.DateValidator dateValidatorMin = DateValidatorPointForward.from(minDate);
        CalendarConstraints.DateValidator dateValidatorMax = DateValidatorPointBackward.before(maxDate);
        calendarStart.set(year, 10, 19);
        calendarEnd.set(year, 11, 21);
         minDate = calendarStart.getTimeInMillis();
         maxDate = calendarEnd.getTimeInMillis();
        RangeValidator rangeValidator = new RangeValidator(List.of(minDate,maxDate));
        ArrayList<CalendarConstraints.DateValidator> listValidators =
                new ArrayList<>();

        listValidators.add(dateValidatorMin);
        listValidators.add(dateValidatorMax);
        listValidators.add(rangeValidator);
        CalendarConstraints.DateValidator validators = CompositeDateValidator.allOf(listValidators);


        constraintsBuilderRange.setValidator(validators);

        builderRange.setCalendarConstraints(constraintsBuilderRange.build());
        MaterialDatePicker<Pair<Long, Long>> pickerRange = builderRange.build();
        pickerRange.show(getSupportFragmentManager(), pickerRange.toString());
    }
}
