package com.example.yavor.reservations;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.yavor.reservations.data.ReservationsContract.ReservationEntry;
import com.example.yavor.reservations.preferences.PreferenceUtils;
import com.example.yavor.reservations.preferences.SettingsActivity;
import com.example.yavor.reservations.reservationinput.AddReservation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int TASK_LOADER_ID = 0;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ReservationsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reservations_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ReservationsAdapter(this);
        recyclerView.setAdapter(adapter);


        findViewById(R.id.fab).setOnClickListener(this);

        setUpDeleteOnSwipe(recyclerView);

        getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, this);
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


    @Override
    public Loader<Cursor> onCreateLoader(int id, final Bundle loaderArgs) {

        return new AsyncTaskLoader<Cursor>(this) {

            Cursor reservationData = null;

            @Override
            protected void onStartLoading() {
                if (reservationData != null) {
                    deliverResult(reservationData);
                } else {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {

                try {
                    return getContentResolver().query(ReservationEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            null);

                } catch (Exception e) {
                    Log.e(TAG, "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(Cursor data) {
                reservationData = data;
                super.deliverResult(data);
            }
        };

    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
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

                String stringId = Integer.toString(id);
                Uri uri = ReservationEntry.CONTENT_URI;
                uri = uri.buildUpon().appendPath(stringId).build();

                getContentResolver().delete(uri, null, null);

                getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, MainActivity.this);

            }
        }).attachToRecyclerView(recyclerView);
    }
}
