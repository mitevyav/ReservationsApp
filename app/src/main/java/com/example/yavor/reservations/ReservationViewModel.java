package com.example.yavor.reservations;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.yavor.reservations.data.ReservationRepository;
import com.example.yavor.reservations.data.ReservationRepositoryImpl;
import com.example.yavor.reservations.data.model.Reservation;

import java.util.List;

public class ReservationViewModel extends AndroidViewModel {

    private LiveData<List<Reservation>> reservationsList;

    private ReservationRepository repository;

    public ReservationViewModel(Application application) {
        super(application);
        repository = new ReservationRepositoryImpl(application);
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
