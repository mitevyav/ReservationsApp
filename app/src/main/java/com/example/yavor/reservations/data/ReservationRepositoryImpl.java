package com.example.yavor.reservations.data;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.yavor.reservations.data.model.Reservation;

import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {


    private ReservationDao reservationDao;

    private LiveData<List<Reservation>> reservationsList;


    public ReservationRepositoryImpl(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
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

    @Override
    public void delete(Reservation reservation) {
        new deleteAsyncTask(reservationDao).execute(reservation);
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


    private static class deleteAsyncTask extends AsyncTask<Reservation, Void, Void> {

        private ReservationDao mAsyncTaskDao;

        deleteAsyncTask(ReservationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Reservation... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
