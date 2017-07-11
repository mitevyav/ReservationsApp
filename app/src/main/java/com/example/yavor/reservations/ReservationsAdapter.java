package com.example.yavor.reservations;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yavor.reservations.data.ReservationsContract.ReservationEntry;

/**
 * Created by mitevyav on 10.7.2017 г..
 */

public class ReservationsAdapter extends RecyclerView.Adapter<ReservationsAdapter.ReservationViewHolder> {


    private Context context;

    private Cursor cursor;

    public ReservationsAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }

        this.cursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reservation_list_item, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {

        if (!cursor.moveToPosition(position)) {
            return;
        }


        String name = cursor.getString(cursor.getColumnIndex(ReservationEntry.COLUMN_GUEST_NAME));
        int numberOfGuests = cursor.getInt(cursor.getColumnIndex(ReservationEntry.COLUMN_NUMBER_OF_GUESTS));
        String timestamp = cursor.getString(cursor.getColumnIndex(ReservationEntry.COLUMN_TIMESTAMP));
        long id = cursor.getLong(cursor.getColumnIndex(ReservationEntry._ID));

        holder.name.setText(context.getString(R.string.guest_name_label, name));
        holder.guestNumber.setText(context.getString(R.string.number_of_guests_label, numberOfGuests));
        holder.timeStamp.setText(timestamp);

        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class ReservationViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView guestNumber;
        TextView timeStamp;

        public ReservationViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            guestNumber = (TextView) itemView.findViewById(R.id.number_of_guests);
            timeStamp = (TextView) itemView.findViewById(R.id.timestamp);
        }
    }
}
