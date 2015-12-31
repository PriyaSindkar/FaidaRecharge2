package uiReferralSystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import faidarecharge.com.faidarecharge.R;

public class ReferralSystemDetailsActivity extends AppCompatActivity {
    TextView imgBack;
    private LinearLayout linearCalculate, linearSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refferal_system_details);


        imgBack = (TextView) findViewById(R.id.imgBack);
        imgBack.setText(getResources().getString(R.string.app_name));

        linearCalculate = (LinearLayout) findViewById(R.id.linearCalculate);
        linearSignup = (LinearLayout) findViewById(R.id.linearSignup);

        linearCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReferralSystemDetailsActivity.this, CalculateActivity.class);
                startActivity(intent);
            }
        });

        linearSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReferralSystemDetailsActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }



}
