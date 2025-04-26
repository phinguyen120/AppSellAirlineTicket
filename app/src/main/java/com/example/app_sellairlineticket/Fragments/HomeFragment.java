package com.example.app_sellairlineticket.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.app_sellairlineticket.Adapter.PhotoViewpageAdapter;
import com.example.app_sellairlineticket.Adapter.TicketHomeFragmentAdapter;
import com.example.app_sellairlineticket.AirportOverLayActivity;
import com.example.app_sellairlineticket.BottomSheetForHomeFragment;
import com.example.app_sellairlineticket.Model.DepartureAirport;
import com.example.app_sellairlineticket.Model.Flight;
import com.example.app_sellairlineticket.Model.ItemTicket;
import com.example.app_sellairlineticket.Model.Photo;
import com.example.app_sellairlineticket.Model.TicketAirline;
import com.example.app_sellairlineticket.R;
import com.example.app_sellairlineticket.TicketAirlineActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import me.relex.circleindicator.CircleIndicator3;

public class HomeFragment extends Fragment {
        private ViewPager2 viewPager2;
        private CircleIndicator3 circleIndicator3;
        private List<Photo> mListPhoto;
        private Handler mHandler = new Handler();
        private EditText etDateTimePicker,etHangGhe;
        Calendar calendar = Calendar.getInstance();
        private EditText etFlightFrom, etFlighFor;
        private RecyclerView rcvTicket;
        private TicketHomeFragmentAdapter ticketadapter;
        private Button btnSearchTicket;
        private Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                if(viewPager2.getCurrentItem() == mListPhoto.size()-1){
                    viewPager2.setCurrentItem(0);
                }else {
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
                }
            }
        };
        private List<Flight> mListFlights;
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
            viewPager2 = view.findViewById(R.id.viewpage2);
            circleIndicator3 = view.findViewById(R.id.circle_Indicator);
            mListPhoto = getListPhoto();
            PhotoViewpageAdapter adapter = new PhotoViewpageAdapter(mListPhoto);
            viewPager2.setAdapter(adapter);
            circleIndicator3.setViewPager(viewPager2);

            mHandler.postDelayed(mRunnable, 3000);
            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }

                @Override
                public void onPageSelected(int position) {
                    mHandler.removeCallbacks(mRunnable);
                    mHandler.postDelayed(mRunnable,3000);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                }
            });

            etFlightFrom = view.findViewById(R.id.etHomeFragmentFlightFrom);
            etFlightFrom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireContext() , AirportOverLayActivity.class);
                    intent.putExtra("source", "from");
                    startActivity(intent);
                }
            });
            etFlighFor = view.findViewById(R.id.etHomeFragmentFlightFor);
            etFlighFor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireContext() , AirportOverLayActivity.class);
                    intent.putExtra("source", "for");
                    startActivity(intent);
                }
            });
            etDateTimePicker = view.findViewById(R.id.etHomeFragmentDateTimePicker);
            etDateTimePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePicker();
                }
            });
            etHangGhe = view.findViewById(R.id.etHomeFragmentHangGhe);
            etHangGhe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BottomSheetForHomeFragment bottomSheet = new BottomSheetForHomeFragment();
                    bottomSheet.show(getParentFragmentManager(), bottomSheet.getTag());
                    bottomSheet.setOnOptionSelectedListener(op->{
                        etHangGhe.setText(op);
                    });
                }
            });
            btnSearchTicket = view.findViewById(R.id.searchButton);
            btnSearchTicket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   String  from = etFlightFrom.getText().toString();
                   String to = etFlighFor.getText().toString();
                   String date = etDateTimePicker.getText().toString();
                   String seatClass = etHangGhe.getText().toString();

                   Intent intent = new Intent(getContext(), TicketAirlineActivity.class);
                   intent.putExtra("from", from);
                   intent.putExtra("to", to);
                   intent.putExtra("date",date);
                   intent.putExtra("seatClass", seatClass);

                   startActivity(intent);
                }
            });
            getTicketAirport(view);
            return view;
        }
        /*private List<DepartureAirport> getDepartureAirport(){
            List<DepartureAirport> departureAirports = new ArrayList<>();
            departureAirports.add(new DepartureAirport("HAN", "Hà Nội"));
            departureAirports.add(new DepartureAirport("SGN", "TP. Hồ Chí Minh"));
            departureAirports.add(new DepartureAirport("DAD", "Đà Nẵng"));
            departureAirports.add(new DepartureAirport("HUI", "Huế"));
            departureAirports.add(new DepartureAirport("DOH", "Doha"));
            departureAirports.add(new DepartureAirport("DXB", "Dubai"));
            departureAirports.add(new DepartureAirport("BKK", "Bangkok"));
            departureAirports.add(new DepartureAirport("KUL", "Kuala Lumpur"));
            departureAirports.add(new DepartureAirport("NRT", "Tokyo Narita"));
            departureAirports.add(new DepartureAirport("LHR", "London Heathrow"));
            departureAirports.add(new DepartureAirport("JFK", "New York"));
            departureAirports.add(new DepartureAirport("SYD", "Sydney"));
            departureAirports.add(new DepartureAirport("FRA", "Frankfurt"));
            departureAirports.add(new DepartureAirport("HKG", "Hong Kong"));
            departureAirports.add(new DepartureAirport("PEK", "Bắc Kinh"));
            departureAirports.add(new DepartureAirport("CGK", "Jakarta"));
            departureAirports.add(new DepartureAirport("MNL", "Manila"));
            departureAirports.add(new DepartureAirport("SFO", "San Francisco"));
            return departureAirports;
        }
        private  List<Flight> getFlights(List<Flight> flights){
            flights = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            try {
                flights.add(new Flight("VN123", "HAN", "SGN", "07:30", "09:45", "2025-05-01", "Vietnam Airlines", "Phổ thông", "1.500.000 VND", true, "vietnam_airlines"));
                flights.add(new Flight("VJ456", "SGN", "DAD", "10:00", "11:30", "2025-05-02", "Vietjet Air", "Thương gia", "2.300.000 VND", false, "vietjet"));
                flights.add(new Flight("BL789", "DAD", "HAN", "13:00", "14:20", "2025-05-03", "Bamboo Airways", "Phổ thông", "1.800.000 VND", true, "bamboo"));
                flights.add(new Flight("QH101", "HAN", "PQC", "06:00", "08:10", "2025-05-04", "Bamboo Airways", "Phổ thông", "1.600.000 VND", true, "bamboo"));
                flights.add(new Flight("VN201", "SGN", "CXR", "09:00", "10:30", "2025-05-04", "Vietnam Airlines", "Thương gia", "2.400.000 VND", true, "vietnam_airlines"));
                flights.add(new Flight("VJ301", "CXR", "DAD", "14:00", "15:30", "2025-05-05", "Vietjet Air", "Phổ thông", "1.450.000 VND", false, "vietjet"));
                flights.add(new Flight("BL401", "DAD", "HAN", "17:30", "19:00", "2025-05-05", "Jetstar", "Thương gia", "2.700.000 VND", true, "jetstar"));
                flights.add(new Flight("VN501", "SGN", "HAN", "07:45", "09:55", "2025-05-06", "Vietnam Airlines", "Phổ thông", "1.500.000 VND", true, "vietnam_airlines"));
                flights.add(new Flight("VJ601", "PQC", "SGN", "11:00", "12:15", "2025-05-06", "Vietjet Air", "Thương gia", "2.200.000 VND", false, "vietjet"));
                flights.add(new Flight("BL701", "CXR", "PQC", "15:00", "17:00", "2025-05-07", "Jetstar", "Phổ thông", "1.400.000 VND", true, "jetstar"));
                flights.add(new Flight("QH801", "HAN", "DAD", "08:00", "09:20", "2025-05-08", "Bamboo Airways", "Thương gia", "2.600.000 VND", true, "bamboo"));
                flights.add(new Flight("VN901", "DAD", "SGN", "13:00", "14:45", "2025-05-09", "Vietnam Airlines", "Phổ thông", "1.700.000 VND", true, "vietnam_airlines"));
                flights.add(new Flight("VJ100", "CXR", "HAN", "16:20", "18:30", "2025-05-09", "Vietjet Air", "Thương gia", "2.350.000 VND", false, "vietjet"));
                flights.add(new Flight("BL111", "HAN", "SGN", "06:15", "08:35", "2025-05-10", "Jetstar", "Phổ thông", "1.550.000 VND", true, "jetstar"));
                flights.add(new Flight("QH121", "PQC", "CXR", "10:10", "11:40", "2025-05-10", "Bamboo Airways", "Thương gia", "2.500.000 VND", true, "bamboo"));
                flights.add(new Flight("VN131", "SGN", "DAD", "12:00", "13:30", "2025-05-11", "Vietnam Airlines", "Phổ thông", "1.600.000 VND", true, "vietnam_airlines"));
                flights.add(new Flight("VJ141", "HAN", "CXR", "18:00", "20:10", "2025-05-11", "Vietjet Air", "Thương gia", "2.800.000 VND", false, "vietjet"));
                flights.add(new Flight("BL151", "DAD", "PQC", "07:30", "09:20", "2025-05-12", "Jetstar", "Phổ thông", "1.350.000 VND", true, "jetstar"));
                flights.add(new Flight("QH161", "SGN", "CXR", "13:30", "14:50", "2025-05-12", "Bamboo Airways", "Thương gia", "2.900.000 VND", true, "bamboo"));
                flights.add(new Flight("VN171", "CXR", "SGN", "17:00", "18:20", "2025-05-13", "Vietnam Airlines", "Phổ thông", "1.600.000 VND", true, "vietnam_airlines"));
                flights.add(new Flight("VJ181", "SGN", "PQC", "08:00", "09:10", "2025-05-14", "Vietjet Air", "Thương gia", "2.000.000 VND", true, "vietjet"));
                flights.add(new Flight("BL191", "HAN", "DAD", "15:00", "16:30", "2025-05-14", "Jetstar", "Phổ thông", "1.500.000 VND", true, "jetstar"));
                flights.add(new Flight("QH202", "DAD", "CXR", "18:45", "20:00", "2025-05-15", "Bamboo Airways", "Thương gia", "2.750.000 VND", true, "bamboo"));
                flights.add(new Flight("VN212", "SGN", "HAN", "05:30", "07:45", "2025-05-16", "Vietnam Airlines", "Phổ thông", "1.550.000 VND", false, "vietnam_airlines"));
                flights.add(new Flight("VJ222", "CXR", "DAD", "11:00", "12:15", "2025-05-16", "Vietjet Air", "Thương gia", "2.450.000 VND", true, "vietjet"));
                flights.add(new Flight("BL232", "SGN", "DAD", "13:15", "14:45", "2025-05-17", "Jetstar", "Phổ thông", "1.400.000 VND", true, "jetstar"));
                flights.add(new Flight("QH242", "HAN", "CXR", "09:00", "11:00", "2025-05-17", "Bamboo Airways", "Thương gia", "2.950.000 VND", false, "bamboo"));
                flights.add(new Flight("VN252", "PQC", "HAN", "16:00", "18:15", "2025-05-18", "Vietnam Airlines", "Phổ thông", "1.650.000 VND", true, "vietnam_airlines"));
                flights.add(new Flight("VJ262", "DAD", "SGN", "20:30", "22:00", "2025-05-18", "Vietjet Air", "Thương gia", "2.200.000 VND", true, "vietjet"));
                flights.add(new Flight("BL272", "CXR", "PQC", "07:00", "08:20", "2025-05-19", "Jetstar", "Phổ thông", "1.300.000 VND", true, "jetstar"));
                flights.add(new Flight("QH282", "DAD", "HAN", "14:30", "16:00", "2025-05-19", "Bamboo Airways", "Thương gia", "2.880.000 VND", false, "bamboo"));

            } catch (Exception e) {
                e.printStackTrace();
            }

            return flights;
        }*/
        private void getTicketAirport( View view){
                List<ItemTicket> lists = new ArrayList<>();
                List<Flight> mlistsTickets = new ArrayList<>();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myref = firebaseDatabase.getReference("Tickets");
                myref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot flightSnapshot : snapshot.getChildren()) {
                            Flight flight = flightSnapshot.getValue(Flight.class);

                            if(flight.getDepartureAirport().equalsIgnoreCase("HAN") &&
                                    flight.getArrivalAirport().equalsIgnoreCase("SGN")) {
                                mlistsTickets.add(flight);
                            }
                        }
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate currentDate = LocalDate.now();
                        for(Flight flight : mlistsTickets){
                            LocalDate inputDate = LocalDate.parse(flight.getDepartureDatetime(), formatter);
                            if(inputDate.isAfter(currentDate)){
                                lists.add(new ItemTicket(R.drawable.plane,"Hà Nội - Sài Gòn",flight.getDepartureDatetime(),R.drawable.custom_default_icon3,flight.getPrice()));
                            }
                        }
                        rcvTicket = view.findViewById(R.id.rcvTicketHomeFragment);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                        rcvTicket.setLayoutManager(linearLayoutManager);
                        ticketadapter = new TicketHomeFragmentAdapter(lists,getContext());
                        rcvTicket.setAdapter(ticketadapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        private void showDatePicker() {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    requireContext(), // hoặc getContext()
                    (view, y, m, d) -> {
                        calendar.set(y, m, d);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        etDateTimePicker.setText(sdf.format(calendar.getTime()));
                    },
                    year, month, day
            );

            datePickerDialog.show();
        }
        private List<Photo> getListPhoto(){
            List<Photo> list = new ArrayList<>();
            list.add(new Photo(R.drawable.slide1));
            list.add(new Photo(R.drawable.silde2));
            list.add(new Photo(R.drawable.slide3));
            list.add(new Photo(R.drawable.slide4));
            list.add(new Photo(R.drawable.slide5));
            return list;
        }

        public void updateEditextSearchAirportFrom(String name , String edtSource){
           if(edtSource.equals("from")){
               EditText editText = getView().findViewById(R.id.etHomeFragmentFlightFrom);
               editText.setText(name);
           }else {
               EditText editText = getView().findViewById(R.id.etHomeFragmentFlightFor);
               editText.setText(name);
           }
        }
        @Override
        public void onPause() {
            super.onPause();
            mHandler.removeCallbacks(mRunnable);
        }

        @Override
        public void onResume() {
            super.onResume();
            mHandler.postDelayed(mRunnable,3000);
        }
}

