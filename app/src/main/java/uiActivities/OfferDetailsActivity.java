package uiActivities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.text.ClipboardManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import faidarecharge.com.faidarecharge.R;
import model.ComplexPreferences;
import model.CouponItem;
import uiCustomControls.AdvancedSpannableString;
import uiCustomControls.RedirectToPortalDialog;


public class OfferDetailsActivity extends ActionBarActivity {

    private TextView txtOfferTitle, txtOfferDetails, imgBack, txtPromoCode;
    private String description, code;
    private ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);

        init();

    }

    private void init() {
        imgBack = (TextView) findViewById(R.id.imgBack);
        txtOfferTitle = (TextView) findViewById(R.id.txtOfferTitle);
        txtOfferDetails = (TextView) findViewById(R.id.txtOfferDetails);
        txtPromoCode = (TextView) findViewById(R.id.txtPromoCode);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);


        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(this, "coupon-details", 0);
        CouponItem couponItem = complexPreferences.getObject("coupon-details", CouponItem.class);

        if(couponItem != null) {
            txtOfferDetails.setText(couponItem.couponDescription);
            txtOfferTitle.setText(couponItem.couponTitle);
            txtPromoCode.setText(couponItem.couponCode);

            String website = couponItem.websiteLink;

            if(website.contains("freecharge")) {
                imgLogo.setImageResource(R.drawable.freecharge_logo);
            } else if (website.contains("mobikwik")) {
                imgLogo.setImageResource(R.drawable.mobikwik_logo);
            } else if (website.contains("flipkart")) {
                imgLogo.setImageResource(R.drawable.flipkart_logo);
            } else {
                imgLogo.setImageResource(R.drawable.paytm_logo);
            }
        }

        complexPreferences.clearObject();
        complexPreferences.commit();




        /*AdvancedSpannableString sp = new AdvancedSpannableString("Get Rs.100 Cashback for DTH Recharge of Rs.\n" +
                "500 or above.");
        sp.setColor(Color.parseColor("#19b050"), "Rs.100 Cashback");
        txtOfferTitle.setText(sp);

        AdvancedSpannableString sp1 = new AdvancedSpannableString("Offer Details:\n" +
                "1. Get Rs.100 cashback from Mobikwik for any DTH\n" +
                "Recharge of Rs.500 or above.\n" +
                "2. Tap to copy Promocode and apply in the checkout.\n" +
                "Cashback will be credited to your Mobikwik Wallet.\n" +
                "3. Only Valid for 3 times use by an user.");

        sp1.setBold("Offer Details:");
        txtOfferDetails.setText(sp1);*/

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtPromoCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(txtPromoCode.getText());
                Toast.makeText(OfferDetailsActivity.this, "Promo Code Copied", Toast.LENGTH_SHORT).show();

                RedirectToPortalDialog box = new RedirectToPortalDialog(OfferDetailsActivity.this);
                box.show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
