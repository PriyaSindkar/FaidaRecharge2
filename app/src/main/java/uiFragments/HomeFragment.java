package uiFragments;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import faidarecharge.com.faidarecharge.R;
import uiCustomControls.AdvancedSpannableString;

/**
 * Created by Priya on 8/30/2015.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ImageView imgPaytm, imgMobikwik, imgFreecharge;
    private Button btnMobileRecharge, btnDTHRecharge, btnBillPayment;
    private LinearLayout linearBottom;
    private TextView txtSwipeHelp, txtTodaysOffers;

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

        imgPaytm = (ImageView) rootView.findViewById(R.id.imgPaytm);
        imgMobikwik = (ImageView) rootView.findViewById(R.id.imgMobikwik);
        imgFreecharge = (ImageView) rootView.findViewById(R.id.imgFreecharge);

        btnMobileRecharge = (Button) rootView.findViewById(R.id.btnMobileRecharge);
        btnDTHRecharge = (Button) rootView.findViewById(R.id.btnDTHRecharge);
        btnBillPayment = (Button) rootView.findViewById(R.id.btnBillPayment);

        linearBottom = (LinearLayout) rootView.findViewById(R.id.linearBottom);
        txtSwipeHelp = (TextView) rootView.findViewById(R.id.txtSwipeHelp);
        txtTodaysOffers = (TextView) rootView.findViewById(R.id.txtTodaysOffers);

        AdvancedSpannableString sp = new AdvancedSpannableString("Today's Offer: For Mobile, DTH Recharge and Bill Payments");
        sp.setColor(Color.parseColor("#ff0000"), "Today's Offer:");
        txtTodaysOffers.setText(sp);

        imgPaytm.setOnClickListener(this);
        imgMobikwik.setOnClickListener(this);
        imgFreecharge.setOnClickListener(this);

        btnMobileRecharge.setOnClickListener(this);
        btnDTHRecharge.setOnClickListener(this);
        btnBillPayment.setOnClickListener(this);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if(position == 0) {
                    linearBottom.setVisibility(View.VISIBLE);
                    txtSwipeHelp.setVisibility(View.VISIBLE);
                    txtTodaysOffers.setVisibility(View.VISIBLE);
                } else {
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

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (view.getId()) {
            case R.id.imgPaytm:
                String url = "http://www.paytm.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case R.id.imgFreecharge:
                url = "http://www.freecharge.in";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case R.id.imgMobikwik:
                url = "http://www.mobikwik.com";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case R.id.btnMobileRecharge:
                fragmentTransaction.replace(R.id.frame, new CategoryFragment(), "CATEGORY");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case R.id.btnDTHRecharge:
                fragmentTransaction.replace(R.id.frame, new CategoryFragment(), "CATEGORY");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case R.id.btnBillPayment:
                fragmentTransaction.replace(R.id.frame, new CategoryFragment(), "CATEGORY");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

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




