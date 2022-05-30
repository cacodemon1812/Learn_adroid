package jmaster.io.section1.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import jmaster.io.section1.R;
import jmaster.io.section1.model.Token;
import jmaster.io.section1.service.RetrofitUtils;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedpreferences = getSharedPreferences("MySession", Context.MODE_PRIVATE);
        String username  = sharedpreferences.getString("username",null);
        if (username != null) {
            navigateMainActivity();
            return;
        }

        getSupportActionBar().setTitle(R.string.login);

        findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = findViewById(R.id.editTextUsername).toString();
                String password = findViewById(R.id.editTextPassword).toString();

                Call<Token> tokenCall = RetrofitUtils.userService().login(username, password,null);

                try {
                    Token token =  tokenCall.execute().body();

                    SharedPreferences sharedpreferences = getSharedPreferences("MySession", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =  sharedpreferences.edit();
                    editor.putString("username", username);
                    editor.putString("accessToken", token.getAccessToken());
                    editor.putInt("expirationTime", token.getExpirationTime());
                    editor.commit();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        findViewById(R.id.textViewHotline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0986983766"));
                startActivity(intent);
            }
        });

        SQLiteDatabase mydb = openOrCreateDatabase("dbname",MODE_PRIVATE,null);

        mydb.execSQL("CREATE TABLE IF NOT EXISTS fav_post(id INT, title VARCHAR, description VARCHAR);");

        mydb.execSQL("INSERT INTO contact VALUES(1, 'Tiêu đề','nội dung bài viết')");

        Cursor rs = mydb.rawQuery("Select *  from contact ",null);
        rs.moveToFirst();
        int id = rs.getInt(0);
        String title = rs.getString(1);
        String description = rs.getString(2);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
    }


    String msg = "Android : ";

    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();

        Log.d(msg, "The onResume() event");
    }

    private void navigateMainActivity () {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(msg, "The onRestart() event");
    }
}