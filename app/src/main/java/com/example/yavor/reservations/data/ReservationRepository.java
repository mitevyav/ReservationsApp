package com.example.yavor.reservations.data;

import android.arch.lifecycle.LiveData;

import com.example.yavor.reservations.data.model.Reservation;

import java.util.List;

public interface ReservationRepository {


    LiveData<List<Reservation>> getAllReservations();

    void insert(Reservation reservation);
}
