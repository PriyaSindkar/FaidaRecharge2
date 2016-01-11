package uiFragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import adapters.OffersAdapter;
import apiHelpers.CallWebService;
import faidarecharge.com.faidarecharge.R;
import model.ComplexPreferences;
import model.CouponItem;
import model.StoreModel;
import uiActivities.MyDrawerActivity;

/**
 * Created by Priya on 8/30/2015.
 */
public class OfferFragmentTwo extends Fragment {
    OffersAdapter adapter;
    ListView listOffers;
    private static String URL = "http://faidarecharge.com/admin/getCoupons.php";
    private String STORES_URL = "http://faidarecharge.com/admin/getStore.php";
    private ArrayList<CouponItem> couponList;
    private ArrayList<CouponItem> pageTwoCouponList;
    private ArrayList<StoreModel> storeItems;
    private RotateLoading rotateloading;

    public static OfferFragmentTwo newInstance() {
        OfferFragmentTwo f = new OfferFragmentTwo();
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offer_one, container, false);
        setHasOptionsMenu(true);
       // ((MyDrawerActivity) getActivity()).setToolbarTitle("Other Offers");

        init(rootView);

        return rootView;
    }

    private void init(View rootView) {
        listOffers = (ListView) rootView.findViewById(R.id.listOffers);
        rotateloading = (RotateLoading) rootView.findViewById(R.id.rotateloading);
        getStores();
    }

    private void getCoupons() {

        /*final ProgressDialog circleDialog = ProgressDialog.show(getActivity(), "Please wait", "Loading...", true);
        circleDialog.setCancelable(true);
        circleDialog.show();*/
        //rotateloading.start();

        new CallWebService(URL, CallWebService.TYPE_JSONOBJECT) {

            @Override
            public void response(String response) {

               // circleDialog.dismiss();
                rotateloading.stop();

                Log.e("RESP Coupon_Details", response);

                try {
                    JSONObject msg = new JSONObject(response);
                    JSONArray data = msg.getJSONArray("data");

                    if (msg.getString("status").equals("1")) {

                        Type listType = new TypeToken<List<CouponItem>>() {
                        }.getType();

                        couponList = new GsonBuilder().create().fromJson(data.toString(), listType);
                        filterHomePageCouponList(couponList);

                    }
                }catch(JSONException jsonEx) {
                    rotateloading.stop();
                    Log.e("JSON EXCEPTION: ", jsonEx.toString());
                }
            }

            @Override
            public void error(VolleyError error) {
                Log.e("VOLLEY ERROR", error.toString());
                //circleDialog.dismiss();
                rotateloading.stop();
            }
        }.start();
    }

    public void filterHomePageCouponList(ArrayList<CouponItem> couponList) {
        int homePageCount = 0;
        pageTwoCouponList = new ArrayList<>();

        for(int i=0; i<couponList.size(); i++) {
            if(couponList.get(i).ishomepage.equals("0")) {
                pageTwoCouponList.add(couponList.get(i));
            } else {
                if(homePageCount >= 4) {
                    pageTwoCouponList.add(couponList.get(i));
                }
                homePageCount++;
            }
        }

        adapter = new OffersAdapter(getActivity(), pageTwoCouponList, storeItems);
        listOffers.setAdapter(adapter);
    }

    private void getStores() {

        /*final ProgressDialog circleDialog = ProgressDialog.show(getActivity(), "Please wait", "Loading...", true);
        circleDialog.setCancelable(true);
        circleDialog.show();*/
        //rotateloading.start();

        new CallWebService(STORES_URL, CallWebService.TYPE_JSONOBJECT) {

            @Override
            public void response(String response) {
              //  rotateloading.stop();
               // circleDialog.dismiss();

                Log.e("RESP stores_Details", response);

                try {
                    JSONObject msg = new JSONObject(response);
                    JSONArray data = msg.getJSONArray("data");

                    if (msg.getString("status").equals("1")) {

                        Type listType = new TypeToken<List<StoreModel>>() {
                        }.getType();

                        storeItems = new GsonBuilder().create().fromJson(data.toString(), listType);


                        getCoupons();

                        Log.e("storeItems", storeItems.toString());
                    }
                }catch(JSONException jsonEx) {
                    rotateloading.stop();
                    Log.e("JSON EXCEPTION: ", jsonEx.toString());
                }
            }

            @Override
            public void error(VolleyError error) {
                Log.e("VOLLEY ERROR", error.toString());
               // circleDialog.dismiss();
                rotateloading.stop();
            }
        }.start();
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv = new SearchView(((MyDrawerActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("TAG_SUBMIT", query + " submitted");
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("TAG_change", newText);
                if (newText.length() == 0) {
                    filterHomePageCouponList(couponList);
                } else {
                    filter(newText);
                }
                return false;
            }
        });
    }

    // Filter Class
    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());
        pageTwoCouponList.clear();
        if (charText.length() == 0) {
            filterHomePageCouponList(couponList);

        } else {
            for (CouponItem obj : couponList) {
                if (charText.length() != 0 && obj.couponTitle.toLowerCase(Locale.getDefault()).contains(charText)) {
                    pageTwoCouponList.add(obj);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

}




