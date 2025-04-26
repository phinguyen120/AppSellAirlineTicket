package com.example.app_sellairlineticket.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_sellairlineticket.Model.Order;
import com.example.app_sellairlineticket.R;

import java.util.List;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.OrderViewHolder>{
    private List<Order> lists;

    public HistoryOrderAdapter(List<Order> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_ticket,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
          Order order = lists.get(position);
          if(order == null){
              return;
          }
          holder.tvHistory_NameAirline.setText(order.getAirline());
          holder.tvHistory_Time.setText("Giờ khởi hành "+order.getDepartureTime() +" - "+ "Dự kiến "+order.getArrivalTime());
          holder.tvHistory_Total.setText(order.getTotal());
          holder.tvHistory_FromTo.setText(order.getFrom() + " -> " + order.getTo());
          holder.tvHistory_DateOrder.setText(order.getDateOrder());
          holder.tvHistory_Date.setText(order.getDepartureDate());
    }

    @Override
    public int getItemCount() {
        if(lists!=null){
            return lists.size();
        }
        return 0;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHistory_NameAirline,tvHistory_FromTo,tvHistory_Time,tvHistory_Total,tvHistory_DateOrder,tvHistory_Date;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHistory_DateOrder = itemView.findViewById(R.id.tvHistory_DateOrder);
            tvHistory_FromTo = itemView.findViewById(R.id.tvHistory_FromTo);
            tvHistory_Time = itemView.findViewById(R.id.tvHistory_Time);
            tvHistory_Total = itemView.findViewById(R.id.tvHistory_Total);
            tvHistory_NameAirline = itemView.findViewById(R.id.tvHistory_NameAirline);
            tvHistory_Date = itemView.findViewById(R.id.tvHistory_date);
        }
    }
}
