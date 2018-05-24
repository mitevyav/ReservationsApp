package com.example.yavor.reservations.reservationinput;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.example.yavor.reservations.ReservationViewModel;
import com.example.yavor.reservations.data.model.Reservation;
import com.example.yavor.reservations.preferences.PreferenceUtils;
import com.example.yavor.reservations.utils.Utils;

import java.util.Date;

public class AddReservation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private static final String DATE_PICKER_FRAGMENT_TAG = "timePicker";
    private static final String TIME_PICKER_FRAGMENT_TAG = "datePicker";

    private static final String MIME_TYPE = "message/rfc822";

    private EditText guestNameEditText;
    private EditText phoneNumberEditText;
    private EditText numberOfGuestsEditText;
    private EditText emailEditText;

    private String guestNameInput;
    private String phoneNumberInput;
    private String numberOfGuestsInput;
    private String emailInput;


    private ReservationViewModel viewModel;

    private boolean timeSet = false;
    private boolean dateSet = false;

    private Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reservation);

        guestNameEditText = findViewById(R.id.name_edit_text);
        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        numberOfGuestsEditText = findViewById(R.id.number_of_guests_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);

        viewModel = ViewModelProviders.of(this).get(ReservationViewModel.class);
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
        viewModel.insert(getReservation());
        sendEmail();
        finish();
    }

    private void notifyBlankFields() {
        Toast.makeText(this, getString(R.string.blank_fields), Toast.LENGTH_SHORT).show();
    }

    private Reservation getReservation() {
        Reservation reservation = new Reservation();
        reservation.setGuestName(guestNameInput);
        reservation.setGuestsCount(Integer.valueOf(numberOfGuestsInput));
        reservation.setPhoneNumber(phoneNumberInput);
        reservation.setEmail(emailInput);
        reservation.setDate(date);
        return reservation;
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

    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType(MIME_TYPE);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailInput, PreferenceUtils.getAdminEmail(this)});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body,
                guestNameInput,
                numberOfGuestsInput,
                phoneNumberInput,
                emailInput,
                Utils.formatDateForPresentation(date)));
        try {
            startActivity(Intent.createChooser(intent, getString(R.string.email_chooser_titles)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, getString(R.string.no_mails_toast), Toast.LENGTH_SHORT).show();
        }
    }
}
