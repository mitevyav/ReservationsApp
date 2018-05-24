package com.example.yavor.reservations.data;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ReservationDaoTest {

    private ReservationDao reservationDao;
    private AppDatabase database;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        reservationDao = database.getReservationDao();
    }

    @Test
    public void getAll() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void delete() {
    }
}