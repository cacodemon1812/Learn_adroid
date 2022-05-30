package jmaster.io.evnloyalty.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import jmaster.io.evnloyalty.R;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(jmaster.io.evnloyalty.R.layout.activity_register);

        findViewById(R.id.txtChangePassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        findViewById(R.id.imageBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        findViewById(R.id.txtPolicy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.evn.com.vn/"));
                startActivity(intent);
            }
        });

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void register() {
        String username = ((EditText) findViewById(R.id.edTextPhone)).getText().toString();
        String password = ((EditText) findViewById(R.id.edTextAddress)).getText().toString();
        String name = ((EditText) findViewById(R.id.edTextName)).getText().toString();

        Toast.makeText(this, R.string.register_success, Toast.LENGTH_LONG).show();
    }
}