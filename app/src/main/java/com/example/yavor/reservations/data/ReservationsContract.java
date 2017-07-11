package com.example.yavor.reservations.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by mitevyav
 */

public class ReservationsContract {


    public static final String AUTHORITY = "com.example.yavor.reservations";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_RESERVATIONS = "reservations";

    public static final class ReservationEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESERVATIONS).build();

        public static final String TABLE_NAME = "reservations";
        public static final String COLUMN_GUEST_NAME = "guestName";
        public static final String COLUMN_NUMBER_OF_GUESTS = "numberOfGuests";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_PHONE_NUMBER = "phoneNumber";
        public static final String COLUMN_EMAIL = "email";
    }

}
