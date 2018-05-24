package com.example.yavor.reservations;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yavor.reservations.data.model.Reservation;
import com.example.yavor.reservations.utils.Utils;

import java.util.List;

/**
 * Created by mitevyav on 10.7.2017 г..
 */

public class ReservationsAdapter extends RecyclerView.Adapter<ReservationsAdapter.ReservationViewHolder> {


    private Context context;


    private List<Reservation> reservations;

    public ReservationsAdapter(Context context) {
        this.context = context;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
        notifyDataSetChanged();
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reservation_list_item, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {

        Reservation reservation = reservations.get(position);


        String name = reservation.getGuestName();
        int numberOfGuests = reservation.getGuestsCount();
        String timestamp = Utils.formatDateForPresentation(reservation.getDate());

        holder.name.setText(context.getString(R.string.guest_name_label, name));
        holder.guestNumber.setText(context.getString(R.string.number_of_guests_label, numberOfGuests));
        holder.timeStamp.setText(timestamp);

        holder.itemView.setTag(reservation);
    }

    @Override
    public int getItemCount() {
        if (reservations == null) {
            return 0;
        }
        return reservations.size();
    }

    class ReservationViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView guestNumber;
        TextView timeStamp;

        public ReservationViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            guestNumber = itemView.findViewById(R.id.number_of_guests);
            timeStamp = itemView.findViewById(R.id.timestamp);
        }
    }
}
