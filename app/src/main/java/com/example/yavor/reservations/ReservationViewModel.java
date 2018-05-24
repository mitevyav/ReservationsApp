package com.example.yavor.reservations;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.yavor.reservations.data.model.Reservation;

import java.util.List;

public class ReservationViewModel extends ViewModel {

    private LiveData<List<Reservation>> reservationsList;
}
