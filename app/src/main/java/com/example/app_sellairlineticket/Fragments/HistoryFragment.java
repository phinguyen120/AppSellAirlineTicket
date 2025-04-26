package com.example.app_sellairlineticket.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_sellairlineticket.Adapter.HistoryOrderAdapter;
import com.example.app_sellairlineticket.Model.Order;
import com.example.app_sellairlineticket.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private List<Order> listOrder;
    private RecyclerView rcvOrder;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment_layout, container, false);

        sharedPreferences = requireActivity().getSharedPreferences("UserPrefs",MODE_PRIVATE);
        String userId = sharedPreferences.getString("userID","");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference("Orders");
        myref.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOrder = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Order order = dataSnapshot.getValue(Order.class);
                    listOrder.add(order);
                }
                rcvOrder = view.findViewById(R.id.rcvHistoryTicket);
                HistoryOrderAdapter adapter = new HistoryOrderAdapter(listOrder);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                rcvOrder.setAdapter(adapter);
                rcvOrder.setLayoutManager(linearLayoutManager);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}