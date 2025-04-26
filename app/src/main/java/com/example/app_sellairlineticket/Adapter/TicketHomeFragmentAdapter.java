package com.example.app_sellairlineticket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_sellairlineticket.Model.ItemTicket;
import com.example.app_sellairlineticket.R;
import com.example.app_sellairlineticket.TicketAirlineActivity;

import java.util.List;

public class TicketHomeFragmentAdapter extends RecyclerView.Adapter<TicketHomeFragmentAdapter.TicketViewHolder> {
    private List<ItemTicket> mList;
    private Context _context;

    public TicketHomeFragmentAdapter(List<ItemTicket> mList, Context _context) {
        this.mList = mList;
        this._context = _context;
    }

    public TicketHomeFragmentAdapter(List<ItemTicket> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_homefragment,parent,false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
           ItemTicket item = mList.get(position);
           if(item == null){
               return;
           }
           holder.avatar.setImageResource(item.getSourceId());
           holder.title.setText(item.getTitle());
           holder.logo.setImageResource(item.getLogocompany());
           holder.date.setText(item.getDate());
           holder.price.setText(item.getPrice());
           holder.linearLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(_context, TicketAirlineActivity.class);
                   intent.putExtra("from","HAN");
                   intent.putExtra("to" ,"SGN");
                   intent.putExtra("date",item.getDate());
                   _context.startActivity(intent);
               }
           });
    }

    @Override
    public int getItemCount() {
        if(mList != null){
            return mList.size();
        }
        return 0;
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar,logo;
        TextView title,date,price;
        LinearLayout linearLayout;
        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.TicketHomeFragment);
            avatar = itemView.findViewById(R.id.image_avatar);
            logo = itemView.findViewById(R.id.image_logo_company);
            title = itemView.findViewById(R.id.tvFlightFromTo);
            date =  itemView.findViewById(R.id.tvFlightDate);
            price = itemView.findViewById(R.id.tvFlightPrice);
        }
    }
}
