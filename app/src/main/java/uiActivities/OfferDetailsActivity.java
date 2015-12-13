package uiActivities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.text.ClipboardManager;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import apiHelpers.CallWebService;
import faidarecharge.com.faidarecharge.R;
import model.ComplexPreferences;
import model.CouponItem;
import model.StoreModel;
import uiCustomControls.AdvancedSpannableString;
import uiCustomControls.RedirectToPortalDialog;


public class OfferDetailsActivity extends ActionBarActivity {

    private TextView txtOfferTitle, txtOfferDetails, imgBack, txtPromoCode;
    private String description, code;
    private ImageView imgLogo;
    private String STORES_URL = "http://faidarecharge.com/admin/getStore.php";
    private static String IMAGE_URL = "http://faidarecharge.com/admin/upload_images/";
    private ArrayList<StoreModel> storeItems;
    CouponItem couponItem;
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

        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(OfferDetailsActivity.this, "coupon-details", 0);
        couponItem = complexPreferences.getObject("coupon-details", CouponItem.class);

        if(couponItem != null) {
            txtOfferDetails.setText(Html.fromHtml(couponItem.couponDescription));
            Typeface typeface = Typeface.createFromAsset(getAssets(), "arialbd.ttf");
            txtOfferTitle.setTypeface(typeface);
            txtOfferTitle.setText(couponItem.couponTitle);
            txtPromoCode.setText(couponItem.couponCode);

            String url = getIntent().getStringExtra("image_url");
            if(url!=null) {
                Glide.with(this)
                        .load(url).placeholder(R.drawable.faidarecharge).into(imgLogo);
            }
        }

        complexPreferences.clearObject();
        complexPreferences.commit();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtPromoCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(txtPromoCode.getText());
                Toast.makeText(OfferDetailsActivity.this, "Promo Code Copied", Toast.LENGTH_SHORT).show();

                RedirectToPortalDialog box = new RedirectToPortalDialog(OfferDetailsActivity.this,couponItem.websiteLink,couponItem.couponStore);
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
