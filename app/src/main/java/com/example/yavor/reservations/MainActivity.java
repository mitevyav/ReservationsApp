package com.example.yavor.reservations;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.yavor.reservations.data.ReservationsContract.ReservationEntry;
import com.example.yavor.reservations.data.ReservationsDbHelper;
import com.example.yavor.reservations.preferences.PreferenceUtils;
import com.example.yavor.reservations.preferences.SettingsActivity;
import com.example.yavor.reservations.reservationinput.AddReservation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ReservationsAdapter adapter;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reservations_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ReservationsDbHelper dbHelper = new ReservationsDbHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor cursor = getAllReservations();

        adapter = new ReservationsAdapter(this, cursor);
        recyclerView.setAdapter(adapter);


        findViewById(R.id.fab).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                startAddReservationActivity();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preferences:
                launchPreferences();
                return false;
        }

        return (super.onOptionsItemSelected(item));
    }

    private void launchPreferences() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void startAddReservationActivity() {
        if(!hasAdminEmail()){
            hasToEnterAdminEmailToast();
            return;
        }
        Intent intent = new Intent(this, AddReservation.class);
        startActivity(intent);
    }

    private void hasToEnterAdminEmailToast() {
        Toast.makeText(this, getString(R.string.no_admin_mail_toast), Toast.LENGTH_SHORT).show();
    }


    private Cursor getAllReservations() {
        return db.query(
                ReservationEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    private boolean hasAdminEmail() {
        return !TextUtils.isEmpty(PreferenceUtils.getAdminEmail(this));
    }
}
