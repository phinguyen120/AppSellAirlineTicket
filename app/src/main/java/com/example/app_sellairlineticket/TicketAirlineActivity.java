package com.example.app_sellairlineticket;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_sellairlineticket.Adapter.TicketAdapter;
import com.example.app_sellairlineticket.Fragments.HomeFragment;
import com.example.app_sellairlineticket.Model.Flight;
import com.example.app_sellairlineticket.Model.TicketAirline;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TicketAirlineActivity extends AppCompatActivity {
    private RecyclerView rcvTicket;
    private ImageView btnBack;
    private List<TicketAirline> mListTicket;
    private List<Flight>mListFlight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_airline);

        String from = getIntent().getStringExtra("from");
        String to = getIntent().getStringExtra("to");
        String date = getIntent().getStringExtra("date");
        String seatClass = getIntent().getStringExtra("seatClass");
        mListFlight = new ArrayList<>();
        rcvTicket = findViewById(R.id.rcvTicketAirline);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference("Tickets");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot flightSnapshot : snapshot.getChildren()) {
                    Flight flight = flightSnapshot.getValue(Flight.class);
                    if(date.equals("")){
                        if(flight.getDepartureAirport().equalsIgnoreCase(from) &&
                                flight.getArrivalAirport().equalsIgnoreCase(to)) {
                            mListFlight.add(flight);

                        }
                    }
                    else if(flight.getDepartureAirport().equalsIgnoreCase(from) &&
                            flight.getArrivalAirport().equalsIgnoreCase(to)&& flight.getDepartureDatetime().equals(date)) {
                        mListFlight.add(flight);

                    }
                }
                mListTicket = new ArrayList<>();
                mListTicket = getListTicket(mListFlight);
                TicketAdapter adapter = new TicketAdapter(mListTicket,TicketAirlineActivity.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TicketAirlineActivity.this);
                rcvTicket.setLayoutManager(linearLayoutManager);
                rcvTicket.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TicketAirlineActivity.this , "Lỗi khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private List<TicketAirline> getListTicket(List<Flight> lists){
        List<TicketAirline> listTicket = new ArrayList<>();
        for (Flight flight:lists) {
            listTicket.add(new TicketAirline(flight.getAirline(),flight.getFlightId(),flight.getDepartureTime(),flight.getComeTime(), flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getDepartureDatetime(),flight.getPrice(), flight.getTicketClass()));
        }
        return listTicket;
    }
}