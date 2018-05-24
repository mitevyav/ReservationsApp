package com.example.yavor.reservations;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.yavor.reservations.data.model.Reservation;
import com.example.yavor.reservations.preferences.PreferenceUtils;
import com.example.yavor.reservations.preferences.SettingsActivity;
import com.example.yavor.reservations.reservationinput.AddReservation;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = MainActivity.class.getSimpleName();

    private ReservationsAdapter adapter;

    private ReservationViewModel reservationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.reservations_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReservationsAdapter(this);
        recyclerView.setAdapter(adapter);

        reservationViewModel = ViewModelProviders.of(this).get(ReservationViewModel.class);
        reservationViewModel.getAllReservations().observe(this, new Observer<List<Reservation>>() {
            @Override
            public void onChanged(@Nullable List<Reservation> reservations) {
                adapter.setReservations(reservations);
            }
        });
        findViewById(R.id.fab).setOnClickListener(this);

        setUpDeleteOnSwipe(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        if (!hasAdminEmail()) {
            hasToEnterAdminEmailToast();
            return;
        }
        Intent intent = new Intent(this, AddReservation.class);
        startActivity(intent);
    }

    private void hasToEnterAdminEmailToast() {
        Toast.makeText(this, getString(R.string.no_admin_mail_toast), Toast.LENGTH_SHORT).show();
    }

    private boolean hasAdminEmail() {
        return !TextUtils.isEmpty(PreferenceUtils.getAdminEmail(this));
    }


    private void setUpDeleteOnSwipe(RecyclerView recyclerView) {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                int id = (int) viewHolder.itemView.getTag();

                // TODO delete on swipe

            }
        }).attachToRecyclerView(recyclerView);
    }
}
