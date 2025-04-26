package com.example.app_sellairlineticket.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_sellairlineticket.Fragments.HomeFragment;
import com.example.app_sellairlineticket.MainActivity;
import com.example.app_sellairlineticket.Model.AirportSearch;
import com.example.app_sellairlineticket.R;

import java.util.List;

public class AirportSearchAdapter extends RecyclerView.Adapter<AirportSearchAdapter.AirportSearchViewHolder>{

    private List<AirportSearch> mListAirport;
    private Context mContext;
    private String edtSource;

    public AirportSearchAdapter(List<AirportSearch> mListAirport, Context context , String source) {
        this.edtSource = source;
        this.mContext = context;
        this.mListAirport = mListAirport;
    }
    public void updateList(List<AirportSearch> newList) {
        mListAirport = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AirportSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_airport_search ,parent , false);
        return new AirportSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AirportSearchViewHolder holder, int position) {
           AirportSearch airportSearch = mListAirport.get(position);

           if(airportSearch == null){
               return;
           }
           holder.imgIcon.setImageResource(airportSearch.getImgSource());
           holder.tvAirportName.setText(airportSearch.getName());
           holder.tvAirportCode.setText(airportSearch.getCode());
           holder.tvAirportLocation.setText(airportSearch.getLocation());

           holder.linearLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(mContext, MainActivity.class);
                   intent.putExtra("Name_Airport" , airportSearch.getCode());
                   intent.putExtra("source" , edtSource);
                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                   mContext.startActivity(intent);
               }
           });
    }

    @Override
    public int getItemCount() {
        if(mListAirport != null){
            return mListAirport.size();
        }
        return 0;
    }

    public class AirportSearchViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private final ImageView imgIcon;
        private final TextView tvAirportName;
        private final TextView tvAirportCode;
        private final TextView tvAirportLocation;
        public AirportSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.layout_itemAirportSearch);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            tvAirportName = itemView.findViewById(R.id.tvAirportName);
            tvAirportCode = itemView.findViewById(R.id.tvAirportCode);
            tvAirportLocation = itemView.findViewById(R.id.tvAirportLocation);
        }
    }

}
