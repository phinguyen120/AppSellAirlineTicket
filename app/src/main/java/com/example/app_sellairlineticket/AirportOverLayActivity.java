package com.example.app_sellairlineticket;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_sellairlineticket.Adapter.AirportSearchAdapter;
import com.example.app_sellairlineticket.Model.AirportSearch;

import java.util.ArrayList;
import java.util.List;

public class AirportOverLayActivity extends AppCompatActivity {

    private ImageButton btnClose;
    private RecyclerView rcvAirportSearch;
    private List<AirportSearch> mFullList;
    private AirportSearchAdapter adapter;
    private EditText etSearch;
    private String edtSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_over_lay);

        rcvAirportSearch = findViewById(R.id.rcvAirportSearchFrom);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvAirportSearch.setLayoutManager(linearLayoutManager);
        mFullList = getListAirport();
        List<AirportSearch> mlist =mFullList.size() > 10 ? mFullList.subList(0,10) : mFullList;
        edtSource = getIntent().getStringExtra("source");
        adapter = new AirportSearchAdapter(mlist, this,edtSource);
        rcvAirportSearch.setAdapter(adapter);

        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> finish());

        etSearch = findViewById(R.id.edtSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    filterList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private List<AirportSearch> getListAirport() {
        List<AirportSearch> mList = new ArrayList<>();
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Nội Bài", "HAN", "Hà Nội, Việt Nam"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Tân Sơn Nhất", "SGN", "TP.HCM, Việt Nam"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Changi", "SIN", "Singapore"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Suvarnabhumi", "BKK", "Bangkok, Thái Lan"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Incheon", "ICN", "Seoul, Hàn Quốc"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Narita", "NRT", "Tokyo, Nhật Bản"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Los Angeles", "LAX", "Los Angeles, Hoa Kỳ"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Heathrow", "LHR", "London, Vương quốc Anh"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Charles de Gaulle", "CDG", "Paris, Pháp"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Dubai", "DXB", "Dubai, UAE"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Frankfurt", "FRA", "Frankfurt, Đức"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Hồng Kông", "HKG", "Hồng Kông"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Sydney", "SYD", "Sydney, Úc"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Kuala Lumpur", "KUL", "Kuala Lumpur, Malaysia"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Bạch Vân", "CAN", "Quảng Châu, Trung Quốc"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Schiphol", "AMS", "Amsterdam, Hà Lan"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Zurich", "ZRH", "Zurich, Thụy Sĩ"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Hamad", "DOH", "Doha, Qatar"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Toronto Pearson", "YYZ", "Toronto, Canada"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Sao Paulo-Guarulhos", "GRU", "Sao Paulo, Brazil"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Indira Gandhi", "DEL", "New Delhi, Ấn Độ"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế OR Tambo", "JNB", "Johannesburg, Nam Phi"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Istanbul", "IST", "Istanbul, Thổ Nhĩ Kỳ"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Vienna", "VIE", "Vienna, Áo"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Barajas", "MAD", "Madrid, Tây Ban Nha"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Leonardo da Vinci–Fiumicino", "FCO", "Rome, Ý"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Brussels", "BRU", "Brussels, Bỉ"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Helsinki-Vantaa", "HEL", "Helsinki, Phần Lan"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Auckland", "AKL", "Auckland, New Zealand"));
        mList.add(new AirportSearch(R.drawable.flight_icon, "Sân bay Quốc tế Seattle-Tacoma", "SEA", "Seattle, Hoa Kỳ"));

        return mList;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            finish();
            return true;
        }
        return super.onTouchEvent(event);
    }
    private void filterList(String keyword) {
        List<AirportSearch> filtered = new ArrayList<>();

        for (AirportSearch airport :mFullList) {
            if (airport.getName().toLowerCase().contains(keyword.toLowerCase())
                    || airport.getCode().toLowerCase().contains(keyword.toLowerCase())
                    || airport.getLocation().toLowerCase().contains(keyword.toLowerCase())) {
                filtered.add(airport);
            }
        }
        adapter.updateList(filtered);
    }
}