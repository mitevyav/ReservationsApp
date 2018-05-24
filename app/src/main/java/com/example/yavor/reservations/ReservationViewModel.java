package com.example.yavor.reservations;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.yavor.reservations.data.ReservationRepository;
import com.example.yavor.reservations.data.model.Reservation;

import java.util.List;

import javax.inject.Inject;

public class ReservationViewModel extends ViewModel {

    private LiveData<List<Reservation>> reservationsList;

    private ReservationRepository repository;

    @Inject
    public ReservationViewModel(ReservationRepository repository) {
        this.repository = repository;
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
