package jmaster.io.evnloyalty.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import jmaster.io.evnloyalty.R;
import jmaster.io.evnloyalty.model.AccessToken;
import jmaster.io.evnloyalty.model.User;
import jmaster.io.evnloyalty.service.LoginService;
import jmaster.io.evnloyalty.service.SharePrefService;
import jmaster.io.evnloyalty.service.UserService;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(jmaster.io.evnloyalty.R.layout.activity_login);

        findViewById(R.id.txtDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        findViewById(R.id.txtPolicy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0986983766"));
                startActivity(intent);
            }
        });
    }

    private void login() {
        String username = ((EditText) findViewById(R.id.edTextPhone)).getText().toString();
        String password = ((EditText) findViewById(R.id.edTextAddress)).getText().toString();

        new LoginService().login(username, password, null, new LoginService.OnDataChangeListener() {
            @Override
            public void setToken(AccessToken accessToken) {
                if (accessToken != null) {
                    new UserService().me("Bearer " + accessToken.getAccessToken(), new UserService.OnDataChangeListener() {
                        @Override
                        public void setUser(User user) {
                            SharePrefService sharePrefService = new SharePrefService(LoginActivity.this);
                            sharePrefService.putString(SharePrefService.KEY_AUTHEN, "true");
                            sharePrefService.putString(SharePrefService.KEY_USER, new Gson().toJson(user));
                            sharePrefService.putString(SharePrefService.KEY_TOKEN, accessToken.getAccessToken());
                            sharePrefService.putInt(SharePrefService.KEY_TOKEN_EXPIRE, accessToken.getExpirationTime());
                            //Chuyen trang main va xoa het lich su
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, R.string.login_fail, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}