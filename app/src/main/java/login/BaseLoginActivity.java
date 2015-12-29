package login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import faidarecharge.com.faidarecharge.R;

public class BaseLoginActivity extends AppCompatActivity {

    TextView txtNewuser, txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_login);

        txtNewuser = (TextView) findViewById(R.id.txtNewuser);
        txtLogin = (TextView) findViewById(R.id.txtLogin);

        txtNewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseLoginActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseLoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

}
