package com.example.yavor.reservations.reservationinput;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.yavor.reservations.R;
import com.example.yavor.reservations.data.ReservationsContract.ReservationEntry;
import com.example.yavor.reservations.data.ReservationsDbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddReservation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private static final String DATE_PICKER_FRAGMENT_TAG = "timePicker";
    private static final String TIME_PICKER_FRAGMENT_TAG = "datePicker";

    private static final String DATE_FORMAT = "d MMM hh:mm";

    private EditText guestNameEditText;
    private EditText phoneNumberEditText;
    private EditText numberOfGuestsEditText;
    private EditText emailEditText;

    private String guestNameInput;
    private String phoneNumberInput;
    private String numberOfGuestsInput;
    private String emailInput;

    private boolean timeSet = false;
    private boolean dateSet = false;

    private Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reservation);

        guestNameEditText = (EditText) findViewById(R.id.name_edit_text);
        phoneNumberEditText = (EditText) findViewById(R.id.phone_number_edit_text);
        numberOfGuestsEditText = (EditText) findViewById(R.id.number_of_guests_edit_text);
        emailEditText = (EditText) findViewById(R.id.email_edit_text);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_reservation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_reservation:
                onAddReservation();
                return false;
        }

        return (super.onOptionsItemSelected(item));
    }

    private void onAddReservation() {
        getInput();
        if (isBlankFields()) {
            notifyBlankFields();
            return;
        }
        ContentValues contentValues = getContentValues();
        insertReservationValues(contentValues);
        finish();
    }

    private void notifyBlankFields() {
        Toast.makeText(this, getString(R.string.blank_fields), Toast.LENGTH_SHORT).show();
    }

    private void insertReservationValues(ContentValues contentValues) {
        ReservationsDbHelper dbHelper = new ReservationsDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(ReservationEntry.TABLE_NAME, null, contentValues);
    }

    private ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ReservationEntry.COLUMN_GUEST_NAME, guestNameInput);
        contentValues.put(ReservationEntry.COLUMN_NUMBER_OF_GUESTS, String.valueOf(numberOfGuestsInput));
        contentValues.put(ReservationEntry.COLUMN_PHONE_NUMBER, phoneNumberInput);
        contentValues.put(ReservationEntry.COLUMN_EMAIL, emailInput);
        contentValues.put(ReservationEntry.COLUMN_TIMESTAMP, formatDate());
        return contentValues;
    }

    private boolean isBlankFields() {
        if (TextUtils.isEmpty(guestNameInput) || TextUtils.isEmpty(phoneNumberInput) ||
                TextUtils.isEmpty(numberOfGuestsInput) || TextUtils.isEmpty(emailInput)) {
            return true;
        }
        if (!timeSet || !dateSet) {
            return true;
        }
        return false;
    }

    private void getInput() {
        guestNameInput = guestNameEditText.getEditableText().toString();
        phoneNumberInput = phoneNumberEditText.getEditableText().toString();
        numberOfGuestsInput = numberOfGuestsEditText.getEditableText().toString();
        emailInput = emailEditText.getEditableText().toString();
    }


    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), TIME_PICKER_FRAGMENT_TAG);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), DATE_PICKER_FRAGMENT_TAG);
    }


    private String formatDate() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        String strDate = format.format(date);
        return strDate;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateSet = true;
        date.setYear(year);
        date.setMonth(month);
        date.setDate(dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timeSet = true;
        date.setHours(hourOfDay);
        date.setMinutes(minute);
    }
}
