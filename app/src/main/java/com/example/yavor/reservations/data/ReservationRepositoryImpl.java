package com.example.yavor.reservations.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.yavor.reservations.data.model.Reservation;

import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {


    private ReservationDao reservationDao;

    private LiveData<List<Reservation>> reservationsList;


    public ReservationRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        reservationDao = db.getReservationDao();
        reservationsList = reservationDao.getAll();
    }

    @Override
    public LiveData<List<Reservation>> getAllReservations() {
        return reservationsList;
    }

    @Override
    public void insert(Reservation reservation) {
        new insertAsyncTask(reservationDao).execute(reservation);
    }


    private static class insertAsyncTask extends AsyncTask<Reservation, Void, Void> {

        private ReservationDao mAsyncTaskDao;

        insertAsyncTask(ReservationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Reservation... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}