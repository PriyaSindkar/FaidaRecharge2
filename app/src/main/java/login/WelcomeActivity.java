package login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import faidarecharge.com.faidarecharge.R;
import uiActivities.MyDrawerActivity;
import uiReferralSystem.ReferralSystemDetailsActivity;

public class WelcomeActivity extends AppCompatActivity {
    TextView txtGetDiscounts, txtEarn, imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_new_user);

        txtGetDiscounts = (TextView) findViewById(R.id.txtGetDiscounts);
        txtEarn = (TextView) findViewById(R.id.txtEarn);
        imgBack = (TextView) findViewById(R.id.imgBack);

        imgBack.setText(getResources().getString(R.string.app_name));

        /*txtGetDiscounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MyDrawerActivity.class);
                startActivity(intent);

            }
        });*/

        txtEarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, ReferralSystemDetailsActivity.class);
                startActivity(intent);
            }
        });


    }

}
