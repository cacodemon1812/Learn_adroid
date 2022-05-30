package jmaster.io.section1.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import jmaster.io.section1.R;
import jmaster.io.section1.model.Token;
import jmaster.io.section1.model.User;
import jmaster.io.section1.service.RetrofitUtils;
import retrofit2.Call;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle(R.string.register_member);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.super.onBackPressed();
            }
        });

        findViewById(R.id.textViewPolicy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://evn.com.vn"));
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = findViewById(R.id.editTextUsername).toString();
                String password = findViewById(R.id.editTextPassword).toString();
                String name = findViewById(R.id.editTextName).toString();

                User user = new User();
                user.setName(name);
                user.setPhone(phone);
                user.setPassword(password);

                Call<User> userCall = RetrofitUtils.userService().register(user);
                try {
                    User body =  userCall.execute().body();
                    Toast.makeText(getBaseContext(), "Register success " + body.getName(), Toast.LENGTH_SHORT);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        SharedPreferences sharedpreferences = getSharedPreferences("MySession", Context.MODE_PRIVATE);
        String token = sharedpreferences.getString("accessToken",null);
        Call<User> userCall = RetrofitUtils.userService().me(token);
        try {
            User currentUser = userCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}