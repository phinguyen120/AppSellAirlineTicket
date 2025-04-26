package com.example.app_sellairlineticket;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app_sellairlineticket.Model.Customer;
import com.example.app_sellairlineticket.Model.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DetailTicketActivity extends AppCompatActivity {
    private TextView tvNameAirline,tvDeparturetime,tvArrivaltime,tvFrom,tvTo,tvSeatClass,tvPrice,tvDuration,tvToltal,tvDepartureDate;
    private ImageView btnBack;
    private Button btnOK, btnPay;
    private EditText count1,count2;
    private LinearLayout linearForAdult, linearForChilden;
    private List<Customer> listCustomers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ticket);

        String nameAirline = getIntent().getStringExtra("name");
        String departuretime = getIntent().getStringExtra("departuretime");
        String arrivaltime = getIntent().getStringExtra("arrivaltime");
        String from = getIntent().getStringExtra("from");
        String duration = getIntent().getStringExtra("duration");
        String to = getIntent().getStringExtra("to");
        String seatclass = getIntent().getStringExtra("seatclass");
        String price = getIntent().getStringExtra("price");
        String date = getIntent().getStringExtra("date");

        tvNameAirline = findViewById(R.id.tv_DetailTicket_AirlineName);
        tvDeparturetime = findViewById(R.id.tv_DetailTicket_DepartureTime);
        tvArrivaltime = findViewById(R.id.tv_DetailTicket_ArrivalTime);
        tvDuration = findViewById(R.id.tv_DetailTicket_FlightDuration);
        tvFrom = findViewById(R.id.tv_DetailTicket_FromAirport);
        tvTo = findViewById(R.id.tv_DetailTicket_ToAirport);
        tvSeatClass = findViewById(R.id.tv_DetailTicket_SeatClass);
        tvPrice = findViewById(R.id.tv_DetailTicket_Price);
        btnBack = findViewById(R.id.btn_DetailTicket_Back);
        tvToltal = findViewById(R.id.tv_DetailTicket_Total);
        tvDepartureDate = findViewById(R.id.tv_DetailTicket_DepartureDate);

        tvNameAirline.setText(nameAirline);
        tvDeparturetime.setText(departuretime);
        tvArrivaltime.setText(arrivaltime);
        tvDuration.setText(duration);
        tvFrom.setText(from);
        tvTo.setText(to);
        tvSeatClass.setText(seatclass);
        tvPrice.setText(price+"/khách");
        tvDepartureDate.setText(date);
        btnBack.setOnClickListener(v->finish());


        count1 = findViewById(R.id.etDetailTicketCount1);
        count2 = findViewById(R.id.etDetailTicketCount2);

        btnOK = findViewById(R.id.btn_DetailTicket_OK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int countAdult = Integer.parseInt(count1.getText().toString());
                int countChilden = Integer.parseInt(count2.getText().toString());
                addFormCustomer(countAdult,countChilden);

                tvToltal.setText(""+convertIntToCurrencyString((convertCurrencyStringToInt(price)*countAdult+convertCurrencyStringToInt(price)*countChilden*0.9)));
            }

        });
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs",MODE_PRIVATE);
        btnPay = findViewById(R.id.btnDetailTicketPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int countAdult = Integer.parseInt(count1.getText().toString());
                int countChilden = Integer.parseInt(count2.getText().toString());
                listCustomers = new ArrayList<>();
                listCustomers  = getAllCustomerData();
                if(listCustomers.size() == (countAdult+countChilden)){
                    Calendar calendar = Calendar.getInstance();
                    Date currentDate = calendar.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = sdf.format(currentDate);
                    Order order = new Order(sharedPreferences.getString("userID", null),listCustomers,nameAirline,departuretime,date,arrivaltime,from,to,convertIntToCurrencyString((convertCurrencyStringToInt(price)*countAdult+convertCurrencyStringToInt(price)*countChilden*0.9)),formattedDate);
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference myref = firebaseDatabase.getReference("Orders");
                    String orderId = myref.push().getKey();
                    myref.child(sharedPreferences.getString("userID", null)).child(orderId).setValue(order);
                    //Taaojo notification
                    LocalDate dateNotification = LocalDate.parse(date);
                    int day = dateNotification.getDayOfMonth();
                    int month = dateNotification.getMonthValue();
                    int year = dateNotification.getYear();
                    LocalTime timeNotification = LocalTime.parse(departuretime);
                    int hour = timeNotification.getHour();
                    int minute = timeNotification.getMinute();
                    checkPermissionAndSetReminder(day,month,year,hour,minute);
                    Toast.makeText(DetailTicketActivity.this,"Thêm thành công!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DetailTicketActivity.this, "Mời nhập đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void addFormCustomer(int countAdult, int countChilden) {
        linearForAdult = findViewById(R.id.DetailTicket_Container_Form_adult);
        linearForChilden = findViewById(R.id.DetailTicket_Container_Form_childen);
        linearForAdult.removeAllViews();
        linearForChilden.removeAllViews();
        if(countAdult > 0){
            for(int i = 0;i<countAdult;i++){
                LayoutInflater inflater = LayoutInflater.from(this);
                View customerForm = inflater.inflate(R.layout.form_customer, linearForAdult, false);
                linearForAdult.addView(customerForm);
            }
        }
        if(countAdult > 0){
            for(int i = 0;i<countChilden;i++){
                LayoutInflater inflater = LayoutInflater.from(this);
                View customerForm = inflater.inflate(R.layout.form_customer, linearForChilden, false);
                linearForChilden.addView(customerForm);
            }
        }
    }
    public static int convertCurrencyStringToInt(String currencyString) {
        String cleanedString = currencyString.replace(" VND", "").replace(".", "");
        return Integer.parseInt(cleanedString);
    }
    public static String convertIntToCurrencyString(double amount) {
        // Định dạng số với dấu chấm phân cách hàng nghìn
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedAmount = formatter.format(amount);

        // Thêm " VND" vào cuối chuỗi
        return formattedAmount + " VND";
    }
    private List<Customer> getAllCustomerData() {
        List<Customer> customerList = new ArrayList<>();
        linearForAdult = findViewById(R.id.DetailTicket_Container_Form_adult);
        linearForChilden = findViewById(R.id.DetailTicket_Container_Form_childen);
        int count = linearForAdult.getChildCount(); // Số form hiện tại
        for (int i = 0; i < count; i++) {
            View formView = linearForAdult.getChildAt(i);

            EditText edtLastName = formView.findViewById(R.id.editTextLastName);
            EditText edtPhone = formView.findViewById(R.id.editTextPhone);
            EditText edtEmail = formView.findViewById(R.id.editTextEmail);
            EditText edtFullName = formView.findViewById(R.id.editTextFullName);

            String name = edtLastName.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String fullname = edtFullName.getText().toString().trim();
            if(!name.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !fullname.isEmpty()){
                customerList.add(new Customer(name, phone, email));
            }

        }
        int count2 = linearForChilden.getChildCount(); // Số form hiện tại
        for (int i = 0; i < count2; i++) {
            View formView = linearForChilden.getChildAt(i);

            EditText edtName = formView.findViewById(R.id.editTextLastName);
            EditText edtPhone = formView.findViewById(R.id.editTextPhone);
            EditText edtEmail = formView.findViewById(R.id.editTextEmail);

            String name = edtName.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();

            customerList.add(new Customer(name, phone, email));
        }
        return customerList;
    }
    //notification
    private void checkPermissionAndSetReminder(int day,int month, int year,int hour, int minute) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager != null && !alarmManager.canScheduleExactAlarms()) {
                Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                startActivity(intent);
                Toast.makeText(this, "Cần cấp quyền đặt nhắc nhở chính xác!", Toast.LENGTH_LONG).show();
                return;
            }
        }

        setFlightReminder(day,month,year,hour,minute);
    }

    private void setFlightReminder(int day,int month, int year, int hour,int minute) {

        Calendar flightCalendar = Calendar.getInstance();
        flightCalendar.set(year, month, day, hour, minute, 0);
        flightCalendar.set(Calendar.MILLISECOND, 0);

        long flightTimeMillis = flightCalendar.getTimeInMillis();

        long reminder1DayMillis = flightTimeMillis - 1 * 60 * 1000;

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, FlightReminderReceiver.class);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(this, 5678, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, reminder1DayMillis, pendingIntent1);
            Toast.makeText(this, "Đã đặt nhắc nhở chuyến bay!", Toast.LENGTH_SHORT).show();
        }
    }
}