package com.example.yavor.reservations.data;

import android.arch.lifecycle.LiveData;
import com.example.yavor.reservations.data.model.Reservation;

import java.util.List;
import java.util.concurrent.Executor;

public class ReservationRepositoryImpl implements ReservationRepository {


    private final Executor executor;

    private final ReservationDao reservationDao;

    private LiveData<List<Reservation>> reservationsList;


    public ReservationRepositoryImpl(ReservationDao reservationDao, Executor executor) {
        this.executor = executor;
        this.reservationDao = reservationDao;
        reservationsList = reservationDao.getAll();
    }

    @Override
    public LiveData<List<Reservation>> getAllReservations() {
        return reservationsList;
    }

    @Override
    public void insert(final Reservation reservation) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                reservationDao.insert(reservation);
            }
        });
    }

    @Override
    public void delete(final Reservation reservation) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                reservationDao.delete(reservation);
            }
        });
    }
}
