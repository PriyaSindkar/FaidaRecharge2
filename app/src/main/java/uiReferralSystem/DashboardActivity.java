package uiReferralSystem;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import org.w3c.dom.Text;

import faidarecharge.com.faidarecharge.R;
import uiCustomControls.TermsAndConditionsDialog;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{
    TextView imgBack, txtReadMore,txtEarnAndRefer, txtHowToEarnAndRefer, txtKnowledgeBase;
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

        txtReadMore = (TextView) findViewById(R.id.txtReadMore);
        txtReadMore.setOnClickListener(this);
        txtEarnAndRefer = (TextView) findViewById(R.id.txtEarnAndRefer);
        txtEarnAndRefer.setOnClickListener(this);
        txtHowToEarnAndRefer = (TextView) findViewById(R.id.txtHowToEarnAndRefer);
        txtHowToEarnAndRefer.setOnClickListener(this);
        txtKnowledgeBase = (TextView) findViewById(R.id.txtKnowledgeBase);
        txtKnowledgeBase.setOnClickListener(this);

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
                Intent intent = new Intent(DashboardActivity.this, MyProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.myReferalStatusActionButton:
                break;
            case R.id.myEarningActionButton:
                intent = new Intent(DashboardActivity.this, MyEarningActivity.class);
                startActivity(intent);
                break;
            case R.id.payoutActionButton:
                intent = new Intent(DashboardActivity.this, PayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.helpActionButton:
                intent = new Intent(DashboardActivity.this, HelpActivity.class);
                startActivity(intent);
                break;
            case R.id.txtReadMore:
                TermsAndConditionsDialog dialog = new TermsAndConditionsDialog(this);
                dialog.show();
                break;
            case R.id.txtEarnAndRefer:
                intent = new Intent(DashboardActivity.this, ReferAndEarnInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.txtHowToEarnAndRefer:
                intent = new Intent(DashboardActivity.this, HowToReferAndEarnInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.txtKnowledgeBase:
                intent = new Intent(DashboardActivity.this, KnowledgeBaseActivity.class);
                startActivity(intent);
                break;

        }
    }
}
