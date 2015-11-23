package uiFragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import adapters.OffersAdapter;
import apiHelpers.CallWebService;
import faidarecharge.com.faidarecharge.R;
import model.CouponItem;
import model.StoreModel;
import uiActivities.MyDrawerActivity;

/**
 * Created by Priya on 8/30/2015.
 */
public class StoresFragment extends Fragment {
    private ListView listOffers;
    private OffersAdapter adapter;
    String storeForFilter;
    private static String URL = "http://faidarecharge.com/admin/getCoupons.php";
    private String STORES_URL = "http://faidarecharge.com/admin/getStore.php";
    private ArrayList<CouponItem> couponList;
    private ArrayList<CouponItem> filteredCouponList;
    private ArrayList<StoreModel> storeItems;
    private ArrayList<CouponItem> searchList = new ArrayList<>();
    private TextView txtEmptyView;

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    }

    public static StoresFragment newInstance() {
        StoresFragment f = new StoresFragment();
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        storeForFilter = (String) bundle.get("store_name");
        ((MyDrawerActivity) getActivity()).setTitle(storeForFilter +" Recharge Portal Offers");

        getStores();
        init(rootView);

        return rootView;
    }

    private void init(View rootView) {
        listOffers = (ListView) rootView.findViewById(R.id.listOffers);
        txtEmptyView = (TextView) rootView.findViewById(R.id.txtEmptyView);
        listOffers.setEmptyView(txtEmptyView);

    }

    private void getCoupons() {

        final ProgressDialog circleDialog = ProgressDialog.show(getActivity(), "Please wait", "Loading...", true);
        circleDialog.setCancelable(true);
        circleDialog.show();

        new CallWebService(URL, CallWebService.TYPE_JSONOBJECT) {

            @Override
            public void response(String response) {

                circleDialog.dismiss();

                Log.e("RESP Coupon_Details", response);

                try {
                    JSONObject msg = new JSONObject(response);
                    JSONArray data = msg.getJSONArray("data");

                    if (msg.getString("status").equals("1")) {

                        Type listType = new TypeToken<List<CouponItem>>() {
                        }.getType();

                        couponList = new GsonBuilder().create().fromJson(data.toString(), listType);
                        filterCouponList(couponList);

                    }
                }catch(JSONException jsonEx) {
                    Log.e("JSON EXCEPTION: ", jsonEx.toString());
                }
            }

            @Override
            public void error(VolleyError error) {
                Log.e("VOLLEY ERROR", error.toString());
                circleDialog.dismiss();
            }
        }.start();
    }

    public void filterCouponList(ArrayList<CouponItem> couponList) {
        filteredCouponList= new ArrayList<>();

        for(int i=0; i<couponList.size(); i++) {
            if(couponList.get(i).couponStore.equals(storeForFilter)) {
                filteredCouponList.add(couponList.get(i));
            }
        }
        adapter = new OffersAdapter(getActivity(), filteredCouponList, storeItems);
        listOffers.setAdapter(adapter);
    }

    // Filter Class
    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());
        searchList.clear();
        if (charText.length() == 0) {
            filterCouponList(filteredCouponList);
            adapter.notifyDataSetChanged();

        } else {
            for (CouponItem obj : filteredCouponList) {

                if (charText.length() != 0 && obj.couponTitle.toLowerCase(Locale.getDefault()).contains(charText)) {
                    searchList.add(obj);
                }
            }
            adapter = new OffersAdapter(getActivity(), searchList, storeItems);
            listOffers.setAdapter(adapter);
        }

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
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() == 0) {
                    filterCouponList(couponList);
                } else {
                    filter(newText);
                }
                return false;
            }
        });
    }

    private void getStores() {

        final ProgressDialog circleDialog = ProgressDialog.show(getActivity(), "Please wait", "Loading...", true);
        circleDialog.setCancelable(true);
        circleDialog.show();

        new CallWebService(STORES_URL, CallWebService.TYPE_JSONOBJECT) {

            @Override
            public void response(String response) {

                circleDialog.dismiss();

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
                    Log.e("JSON EXCEPTION: ", jsonEx.toString());
                }
            }

            @Override
            public void error(VolleyError error) {
                Log.e("VOLLEY ERROR", error.toString());
                circleDialog.dismiss();
            }
        }.start();
    }

}




