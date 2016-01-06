package uiReferralSystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import faidarecharge.com.faidarecharge.R;

public class MyEarningActivity extends AppCompatActivity {
    TextView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_earning);

        imgBack = (TextView) findViewById(R.id.imgBack);
        imgBack.setText("My Earning");

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
