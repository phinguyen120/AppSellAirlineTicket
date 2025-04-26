package com.example.app_sellairlineticket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app_sellairlineticket.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText registrationInputName,registrationInputSurname,registrationInputEmail,registrationInputPassword,registrationInputRepassword;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private Button btnComfirmRegister, btnGoToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        registrationInputName = findViewById(R.id.registrationInputName);
        registrationInputEmail = findViewById(R.id.registrationInputEmail);
        registrationInputSurname = findViewById(R.id.registrationInputSurname);
        registrationInputRepassword = findViewById(R.id.registrationInputRepassword);
        registrationInputPassword = findViewById(R.id.registrationInputPassword);

        btnComfirmRegister = findViewById(R.id.registrationConfirmButton);
        btnGoToLogin = findViewById(R.id.registrationUserExistingButton);

        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnComfirmRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = registrationInputName.getText().toString();
                String surname = registrationInputSurname.getText().toString();
                String email = registrationInputEmail.getText().toString();
                String password = registrationInputPassword.getText().toString();
                String repassword = registrationInputRepassword.getText().toString();
                if(!password.equals(repassword)){
                    Toast.makeText(RegisterActivity.this,"Hai trường mật khẩu không trùng khớp!" ,Toast.LENGTH_SHORT).show();
                }else {
                    if(name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || repassword.isEmpty()){
                        Toast.makeText(RegisterActivity.this,"Các trường không được bỏ trống!" ,Toast.LENGTH_SHORT).show();
                    }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        Toast.makeText(RegisterActivity.this,"Email không hợp lệ!" ,Toast.LENGTH_SHORT).show();
                    }else {
                        Register(email,password,name,surname);
                    }
                }
            }
        });
    }
    private void Register(String email , String password , String name, String surname){
           mAuth = FirebaseAuth.getInstance();
           mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if(task.isSuccessful()){
                    String uid = mAuth.getCurrentUser().getUid();
                    db = FirebaseDatabase.getInstance();
                    DatabaseReference useRef = db.getReference("Users");
                    User user = new User(uid, name, surname, email, password);
                    useRef.child(uid).setValue(user).addOnCompleteListener(t->{
                       if(t.isSuccessful()){
                           Toast.makeText(RegisterActivity.this,"Đăng ký tài khoản thành công !" ,Toast.LENGTH_SHORT).show();
                           new Handler().postDelayed(() -> {
                               Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                               startActivity(intent);
                               finish();
                           }, 2000);
                       }else {
                           Toast.makeText(RegisterActivity.this,"Có lõi xảy ra !" ,Toast.LENGTH_SHORT).show();
                       }
                    });
                }else {
                    Toast.makeText(RegisterActivity.this,"Email đã được sử dụng, Vui lòng sử dụng email khác !" ,Toast.LENGTH_SHORT).show();
                }
           });
    }
}