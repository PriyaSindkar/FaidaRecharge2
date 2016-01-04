package uiReferralSystem;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import faidarecharge.com.faidarecharge.R;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{
    TextView imgBack;
    FrameLayout frameOverlay;
    FloatingActionsMenu floatingActionsMenu;
    FloatingActionButton myProfileActionButton, myReferalStatusActionButton, myEarningActionButton, payoutActionButton, helpActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        imgBack = (TextView) findViewById(R.id.imgBack);
        imgBack.setText("Dashboard");

        frameOverlay = (FrameLayout) findViewById(R.id.frameOverlay);

        floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.floatingActionsMenu);
        myProfileActionButton = (FloatingActionButton) findViewById(R.id.myProfileActionButton);
        myReferalStatusActionButton = (FloatingActionButton) findViewById(R.id.myReferalStatusActionButton);
        myEarningActionButton = (FloatingActionButton) findViewById(R.id.myEarningActionButton);
        payoutActionButton = (FloatingActionButton) findViewById(R.id.payoutActionButton);
        helpActionButton = (FloatingActionButton) findViewById(R.id.helpActionButton);

        myProfileActionButton.setOnClickListener(this);
        myReferalStatusActionButton.setOnClickListener(this);
        myEarningActionButton.setOnClickListener(this);
        payoutActionButton.setOnClickListener(this);
        helpActionButton.setOnClickListener(this);

        floatingActionsMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                frameOverlay.setBackgroundColor(Color.parseColor("#ab000000"));
                frameOverlay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        floatingActionsMenu.collapse();
                        return true;
                    }
                });
            }

            @Override
            public void onMenuCollapsed() {
                frameOverlay.setBackgroundColor(Color.parseColor("#00000000"));
                frameOverlay.setOnTouchListener(null);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myProfileActionButton:
                break;
            case R.id.myReferalStatusActionButton:
                break;
            case R.id.myEarningActionButton:
                break;
            case R.id.payoutActionButton:
                break;
            case R.id.helpActionButton:
                break;
        }
    }
}
