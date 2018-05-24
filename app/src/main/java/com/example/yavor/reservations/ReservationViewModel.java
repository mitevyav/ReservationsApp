package com.example.yavor.reservations;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.yavor.reservations.data.ReservationRepository;
import com.example.yavor.reservations.data.model.Reservation;

import java.util.List;

public class ReservationViewModel extends AndroidViewModel {

    private LiveData<List<Reservation>> reservationsList;

    // TODO use DI
    private ReservationRepository repository;

    public ReservationViewModel(@NonNull Application application) {
        super(application);
        this.repository = ((ReservationApp) application).getApplicationComponent().getReservationRepository();
        reservationsList = repository.getAllReservations();
    }


    LiveData<List<Reservation>> getAllReservations() {
        return reservationsList;
    }

    public void insert(Reservation reservation) {
        repository.insert(reservation);
    }

    public void delete(Reservation reservation) {
        repository.delete(reservation);
    }
}
