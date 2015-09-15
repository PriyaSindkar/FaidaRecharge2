package uiFragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import adapters.OffersAdapter;
import apiHelpers.CallWebService;
import faidarecharge.com.faidarecharge.R;
import model.CouponItem;

/**
 * Created by Priya on 8/30/2015.
 */
public class OfferFragmentOne extends Fragment {
    OffersAdapter adapter;
    ListView listOffers;
    List<String> headersList;
    private static String URL = "http://faidarecharge.com/webservice/getCoupons.php";
    private ArrayList<CouponItem> couponList;
    private ArrayList<CouponItem> homePageCouponList;

    public static OfferFragmentOne newInstance() {
        OfferFragmentOne f = new OfferFragmentOne();
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offer_one, container, false);

        init(rootView);

        return rootView;
    }

    private void init(View rootView) {
        listOffers = (ListView) rootView.findViewById(R.id.listOffers);
        //initOfferDetails();
        getCoupons();



    }

    private void initOfferDetails() {
        headersList = new ArrayList<>();
        headersList.add("Get Rs.100 Cashback for DTH Recharge of Rs.500 or above.");
        headersList.add("Get Rs.100 Cashback for DTH Recharge of Rs.500 or above.");
        headersList.add("Get Rs.100 Cashback for DTH Recharge of Rs.500 or above.");
        headersList.add("Get Rs.100 Cashback for DTH Recharge of Rs.500 or above.");
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
                        filterHomePageCouponList(couponList);

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

    public void filterHomePageCouponList(ArrayList<CouponItem> couponList) {
        homePageCouponList = new ArrayList<>();

        for(int i=0; i<couponList.size(); i++) {
            if(couponList.get(i).ishomepage.equals("1")) {
                homePageCouponList.add(couponList.get(i));
            }
        }
        adapter = new OffersAdapter(getActivity(), homePageCouponList);
        listOffers.setAdapter(adapter);
    }


}




