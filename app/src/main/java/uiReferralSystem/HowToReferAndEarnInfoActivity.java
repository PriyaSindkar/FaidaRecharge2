package uiReferralSystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import faidarecharge.com.faidarecharge.R;
import uiCustomControls.CustomShareAppDialog;

public class HowToReferAndEarnInfoActivity extends AppCompatActivity {
    TextView imgBack;
    private LinearLayout linearShare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_earn_and_refer);

        imgBack = (TextView) findViewById(R.id.imgBack);
        imgBack.setText("How To Refer And Earn");

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        linearShare = (LinearLayout) findViewById(R.id.linearShare);

        linearShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomShareAppDialog dialog = new CustomShareAppDialog(HowToReferAndEarnInfoActivity.this, "KLSKRISHNA", "Krishna Patel");
                dialog.show();
            }
        });
    }
}
