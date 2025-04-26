package com.example.app_sellairlineticket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText loginInputEmail,loginInputPassword;
    private Button btnLogin, btnDontHaveAccount;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginInputEmail = findViewById(R.id.loginInputEmail);
        loginInputPassword = findViewById(R.id.loginInputPassword);

        btnLogin = findViewById(R.id.loginConfirmButton);
        btnDontHaveAccount = findViewById(R.id.loginDontHaveAccButton);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  String email, password;
                  email = loginInputEmail.getText().toString();
                  password = loginInputPassword.getText().toString();

                  if(email.isEmpty() || password.isEmpty()){
                      Toast.makeText(LoginActivity.this , "Vui lòng điền đầy đủ thông tin !" , Toast.LENGTH_SHORT).show();;
                  }else {
                      LoginForEmailAndPassword(email,password);
                  }
            }
        });
        btnDontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void LoginForEmailAndPassword(String email, String password){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(LoginActivity.this,"Đăng nhập thành công !" ,Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(() -> {
                    SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userID", mAuth.getCurrentUser().getUid());
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }, 2000);
            }else {
                Toast.makeText(LoginActivity.this,"Tài khoản hoặc mật khẩu không đúng, vui lòng thử lại !" ,Toast.LENGTH_SHORT).show();
            }
        });
    }

}