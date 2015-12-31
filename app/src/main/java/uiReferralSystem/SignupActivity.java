package uiReferralSystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import faidarecharge.com.faidarecharge.R;

public class SignupActivity extends AppCompatActivity {
    TextView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        imgBack = (TextView) findViewById(R.id.imgBack);
        imgBack.setText(getResources().getString(R.string.app_name));


    }
}
