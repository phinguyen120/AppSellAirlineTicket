package com.example.app_sellairlineticket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_sellairlineticket.DetailTicketActivity;
import com.example.app_sellairlineticket.Model.TicketAirline;
import com.example.app_sellairlineticket.R;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {
    private List<TicketAirline> mLists;
    private Context _context;

    public TicketAdapter(List<TicketAirline> mLists, Context _context) {
        this.mLists = mLists;
        this._context = _context;
    }

    public TicketAdapter(List<TicketAirline> mLists) {
        this.mLists = mLists;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket,parent,false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
          TicketAirline item = mLists.get(position);
          if(item == null){
              return;
          }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        // Chuyển từ String sang LocalTime
        LocalTime departure = LocalTime.parse(item.getDepartureTime(), formatter);
        LocalTime arrival = LocalTime.parse(item.getArrivalTime(), formatter);

        // Nếu giờ đến trước giờ đi (qua ngày hôm sau)
        if (arrival.isBefore(departure)) {
            arrival = arrival.plusHours(24); // cộng thêm 24h
        }

        // Tính thời gian bay
        Duration duration = Duration.between(departure, arrival);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
          holder.tvNameAirline.setText(item.getAirline());
          holder.tvDepartureTime.setText(item.getDepartureTime());
          holder.tvFlightDuration.setText(hours+"h"+minutes+"m");
          holder.tvArrivalTime.setText(item.getArrivalTime());
          holder.tvFromAirport.setText(item.getFrom());
          holder.tvToAirport.setText(item.getTo());
          holder.tvDepartureDate.setText(item.getDepartureDate());
          holder.tvPrice.setText(item.getPrice()+"/ khách");
          holder.tvSeatClass.setText("Hạng : "+item.getSeatClass());
          holder.btnSelect.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(_context, DetailTicketActivity.class);
                  intent.putExtra("name",item.getAirline());
                  intent.putExtra("departuretime", item.getDepartureTime());
                  intent.putExtra("arrivaltime" , item.getArrivalTime());
                  intent.putExtra("from", item.getFrom());
                  intent.putExtra("date", item.getDepartureDate());
                  intent.putExtra("duration",hours+"h"+minutes+"m");
                  intent.putExtra("to", item.getTo());
                  intent.putExtra("price",item.getPrice());
                  intent.putExtra("seatclass", item.getSeatClass());
                  _context.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        if(mLists != null){
            return mLists.size();
        }
        return 0;
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameAirline,tvDepartureTime,tvArrivalTime,tvFromAirport,tvToAirport,tvPrice,tvFlightDuration,tvSeatClass,tvDepartureDate;
        Button btnSelect;
        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameAirline =itemView.findViewById(R.id.tvAirlineName);
            tvDepartureTime = itemView.findViewById(R.id.tvDepartureTime);
            tvArrivalTime = itemView.findViewById(R.id.tvArrivalTime);
            tvFromAirport = itemView.findViewById(R.id.tvFromAirport);
            tvToAirport = itemView.findViewById(R.id.tvToAirport);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDepartureDate = itemView.findViewById(R.id.tvDepartureDate);
            tvFlightDuration = itemView.findViewById(R.id.tvFlightDuration);
            tvSeatClass = itemView.findViewById(R.id.tvSeatClass);
            btnSelect = itemView.findViewById(R.id.btnSelect);
        }
    }
}
