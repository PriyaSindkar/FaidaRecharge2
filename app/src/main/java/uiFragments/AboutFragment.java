package uiFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import apiHelpers.CallWebService;
import faidarecharge.com.faidarecharge.R;
import model.CategoryModel;
import model.StoreModel;
import uiActivities.MyDrawerActivity;
import uiCustomControls.AdvancedSpannableString;

/**
 * Created by Priya on 8/30/2015.
 */
public class AboutFragment extends Fragment{
    TextView txtSplashText1, txtSplashText2, txtSplashText3, txtSplashText4, txtSplashText5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        setHasOptionsMenu(true);
        //((MyDrawerActivity) getActivity()).hideMenu();
        init(rootView);

        return rootView;
    }

    private void init(View rootView) {
        ((MyDrawerActivity) getActivity()).setTitle("What is Faida Recharge?");

        txtSplashText1 = (TextView) rootView.findViewById(R.id.txtSplashText1);
        txtSplashText2 = (TextView) rootView.findViewById(R.id.txtSplashText2);
        txtSplashText3 = (TextView) rootView.findViewById(R.id.txtSplashText3);
        txtSplashText4 = (TextView) rootView.findViewById(R.id.txtSplashText4);
        txtSplashText5 = (TextView) rootView.findViewById(R.id.txtSplashText5);

        txtSplashText1.setText("Faida Recharge showcases promocode of various recharge portals. If you apply  these promocodes before payment, You would get cashback for each recharge, bill payment and DTH recharge. Cashback will be provided by respective recharge portals as per plan.");
        txtSplashText2.setText("Faida Recharge works to collect and display  those promocodes in a systematic format only.");
        txtSplashText3.setText("Top recharge portals are PAYTM, FREECHARGE AND MOBIKWIK. Else we display promocodes of other portals also.");
        txtSplashText4.setText("Cashback you receive from respective portals like PAYTM etc, are only to use for next recharge. You will get cashback in their wallet only.");
        txtSplashText5.setText("Faida recharge doesn’t store  any user information including mobile no, email or user id and password during the whole process.");
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
    }
}




