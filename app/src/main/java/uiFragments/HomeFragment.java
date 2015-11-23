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
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import apiHelpers.CallWebService;
import faidarecharge.com.faidarecharge.R;
import model.CategoryItem;
import model.CategoryModel;
import model.CouponItem;
import model.StoreModel;
import uiActivities.MyDrawerActivity;
import uiCustomControls.AdvancedSpannableString;

/**
 * Created by Priya on 8/30/2015.
 */
public class HomeFragment extends Fragment{
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private LinearLayout linearBottom, linearCategories, linearStores;
    private TextView txtSwipeHelp, txtTodaysOffers;
    private String CATEGORIES_URL = "http://faidarecharge.com/admin/getCategory.php";
    private String STORES_URL = "http://faidarecharge.com/admin/getStore.php";
    private static String IMAGE_URL = "http://faidarecharge.com/admin/upload_images/";
    private ArrayList<CategoryModel> categoryItems;
    private ArrayList<StoreModel> storeItems;
    private HorizontalScrollView hScrollView, hScrollView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        init(rootView);

        return rootView;
    }

    private void init(View rootView) {
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        mAdapter = new TabsPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mAdapter);

        linearBottom = (LinearLayout) rootView.findViewById(R.id.linearBottom);
        txtSwipeHelp = (TextView) rootView.findViewById(R.id.txtSwipeHelp);
        txtTodaysOffers = (TextView) rootView.findViewById(R.id.txtTodaysOffers);

        AdvancedSpannableString sp = new AdvancedSpannableString("Today's Offer: For Mobile, DTH Recharge and Bill Payments");
        sp.setColor(getResources().getColor(R.color.maroon), "Today's Offer:");
        txtTodaysOffers.setText(sp);

        linearCategories = (LinearLayout) rootView.findViewById(R.id.linearCategories);
        hScrollView = (HorizontalScrollView) rootView.findViewById(R.id.hScrollView);
        hScrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        linearStores = (LinearLayout) rootView.findViewById(R.id.linearStores);
        hScrollView2 = (HorizontalScrollView) rootView.findViewById(R.id.hScrollView2);
        hScrollView2.setOverScrollMode(View.OVER_SCROLL_NEVER);

        getCategories();
        getStores();

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if(position == 0) {
                    ((MyDrawerActivity) getActivity()).setToolbarTitle("Home");
                    linearBottom.setVisibility(View.VISIBLE);
                    txtSwipeHelp.setVisibility(View.VISIBLE);
                    txtTodaysOffers.setVisibility(View.VISIBLE);

                } else {
                    ((MyDrawerActivity) getActivity()).setToolbarTitle("Other Offers");
                    linearBottom.setVisibility(View.GONE);
                    txtSwipeHelp.setVisibility(View.GONE);
                    txtTodaysOffers.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
    }



    private void getCategories() {

        final ProgressDialog circleDialog = ProgressDialog.show(getActivity(), "Please wait", "Loading...", true);
        circleDialog.setCancelable(true);
        circleDialog.show();

        new CallWebService(CATEGORIES_URL, CallWebService.TYPE_JSONOBJECT) {

            @Override
            public void response(String response) {

                circleDialog.dismiss();

                Log.e("RESP categories_Details", response);

                try {
                    JSONObject msg = new JSONObject(response);
                    JSONArray data = msg.getJSONArray("data");

                    if (msg.getString("status").equals("1")) {

                        Type listType = new TypeToken<List<CategoryModel>>() {
                        }.getType();

                        categoryItems = new GsonBuilder().create().fromJson(data.toString(), listType);
                        //filterHomePageCouponList(couponList);

                        setCategories();

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
                        //filterHomePageCouponList(couponList);

                        Log.e("storeItems", storeItems.toString());

                        setStores();

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

    public void setCategories() {
        linearCategories.removeAllViews();
        linearCategories.setWeightSum(categoryItems.size());
        for(int i=0; i<categoryItems.size(); i++) {
            final int pos = i;
            LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.category_item,null);
            TextView txtCategoryTitle = (TextView) v.findViewById(R.id.txtCategoryTitle);
            txtCategoryTitle.setText(categoryItems.get(i).categoryTitle);

            linearCategories.addView(v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("category_name", categoryItems.get(pos).categoryTitle);

                    CategoryFragment categoryFragment = new CategoryFragment();
                    categoryFragment.setArguments(bundle);

                    fragmentTransaction.replace(R.id.frame, categoryFragment, "CATEGORY");
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
    }

    public void setStores() {
        linearStores.removeAllViews();
        linearStores.setWeightSum(storeItems.size());
        for(int i=0; i<storeItems.size(); i++) {
            final int pos = i;
            LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.store_item,null);
            ImageView imgLogo = (ImageView) v.findViewById(R.id.imgLogo);
            String url = IMAGE_URL+storeItems.get(i).imageURL;


            Glide.with(this)
                    .load(url).placeholder(R.drawable.faidarecharge).into(imgLogo);

            linearStores.addView(v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("store_name", storeItems.get(pos).storeName);

                    StoresFragment categoryFragment = new StoresFragment();
                    categoryFragment.setArguments(bundle);

                    fragmentTransaction.replace(R.id.frame, categoryFragment, "STORE");
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                   /* String url = storeItems.get(pos).webURL;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);*/
                }
            });
        }



    }

    public class TabsPagerAdapter extends FragmentPagerAdapter {

        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }


        @Override
        public Fragment getItem(int position) {
            if (position==0) {
                return OfferFragmentOne.newInstance();
            }else if(position==1) {
                return OfferFragmentTwo.newInstance();
            }
            else{
                return OfferFragmentOne.newInstance();
            }
        }
    }

}




