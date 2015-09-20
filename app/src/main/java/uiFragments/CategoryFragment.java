package uiFragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

import adapters.CategoryAdapter;
import adapters.OffersAdapter;
import apiHelpers.CallWebService;
import faidarecharge.com.faidarecharge.R;
import model.CategoryItem;
import model.CouponItem;
import uiActivities.MyDrawerActivity;

/**
 * Created by Priya on 8/30/2015.
 */
public class CategoryFragment extends Fragment {
    private ListView listOffers;
    private OffersAdapter adapter;
    private List<CategoryItem> categoryList = new ArrayList<>();
    String categoryNameForFilter;
    private static String URL = "http://faidarecharge.com/webservice/getCoupons.php";
    private ArrayList<CouponItem> couponList;
    private ArrayList<CouponItem> filteredCouponList;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    }

    public static CategoryFragment newInstance() {
        CategoryFragment f = new CategoryFragment();
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        Bundle bundle = getArguments();
        categoryNameForFilter = (String) bundle.get("category_name");
        ((MyDrawerActivity) getActivity()).setTitle(categoryNameForFilter +" Offers");

        getCoupons();
        init(rootView);

        return rootView;
    }

    private void init(View rootView) {
        listOffers = (ListView) rootView.findViewById(R.id.listOffers);


        // Initialize Category list
        categoryList.add(new CategoryItem("Mobile Recharge Offers"));
        categoryList.add(new CategoryItem("DTH Recharge Offers"));
        categoryList.add(new CategoryItem("Bill Payment Offers"));

       /* adapter = new CategoryAdapter(getActivity(), categoryList);
        mRecyclerView.setAdapter(adapter);*/
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
            if(couponList.get(i).couponCategory.equals(categoryNameForFilter)) {
                filteredCouponList.add(couponList.get(i));
            }
        }
        adapter = new OffersAdapter(getActivity(), filteredCouponList);
        listOffers.setAdapter(adapter);
    }

}




