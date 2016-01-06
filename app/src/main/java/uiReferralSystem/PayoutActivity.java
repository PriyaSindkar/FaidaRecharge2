package uiReferralSystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import faidarecharge.com.faidarecharge.R;

public class PayoutActivity extends AppCompatActivity {
    private TextView imgBack, txtTotalPaidAmount;
    private LinearLayout linearPaymentDetails;
    private boolean isPaymentDetailsShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payout);

        imgBack = (TextView) findViewById(R.id.imgBack);
        imgBack.setText("Payout");

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtTotalPaidAmount = (TextView) findViewById(R.id.txtTotalPaidAmount);
        linearPaymentDetails = (LinearLayout) findViewById(R.id.linearPaymentDetails);

        txtTotalPaidAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPaymentDetailsShown = !isPaymentDetailsShown;
                if( !isPaymentDetailsShown) {
                    linearPaymentDetails.setVisibility(View.GONE);
                } else {
                    linearPaymentDetails.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
