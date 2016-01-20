package uiReferralSystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import faidarecharge.com.faidarecharge.R;
import uiCustomControls.HowItWorksCalculatorDialog;

public class CalculateActivity extends AppCompatActivity {
    TextView imgBack, txtHowItWorks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        imgBack = (TextView) findViewById(R.id.imgBack);
        imgBack.setText(getResources().getString(R.string.app_name));

        txtHowItWorks = (TextView) findViewById(R.id.txtHowItWorks);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtHowItWorks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HowItWorksCalculatorDialog dialog = new HowItWorksCalculatorDialog(CalculateActivity.this);
                dialog.show();
            }
        });

    }
}
