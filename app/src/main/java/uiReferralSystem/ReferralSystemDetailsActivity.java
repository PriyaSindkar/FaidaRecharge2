package uiReferralSystem;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import faidarecharge.com.faidarecharge.R;

public class ReferralSystemDetailsActivity extends AppCompatActivity {
    TextView imgBack;
    ViewPager viewPager;
    MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refferal_system_details);


        imgBack = (TextView) findViewById(R.id.imgBack);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        imgBack.setText(getResources().getString(R.string.app_name));

        myPagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(myPagerAdapter);


    }

    private class MyPagerAdapter extends PagerAdapter {

        int NumberOfPages = 4;

        String[] res = {
                getResources().getString(R.string.referral_system_intro_detail1),
                getResources().getString(R.string.referral_system_intro_detail2),
                getResources().getString(R.string.referral_system_intro_detail3),
                getResources().getString(R.string.referral_system_intro_detail4)};

        @Override
        public int getCount() {
            return NumberOfPages;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {


            TextView textView = new TextView(ReferralSystemDetailsActivity.this);
            textView.setTextColor(getResources().getColor(R.color.primaryColor));
            textView.setTextSize(getResources().getDimension(R.dimen.VL_TEXT_SIZE));
            textView.setGravity(Gravity.CENTER);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setText(res[position]);
            RelativeLayout layout = new RelativeLayout(ReferralSystemDetailsActivity.this);
           // layout.setOrientation(LinearLayout.VERTICAL);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            textView.setLayoutParams(layoutParams1);
            layout.addView(textView);
            container.addView(layout);

          /*  final int page = position;
            layout.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,
                            "Page " + page + " clicked",
                            Toast.LENGTH_LONG).show();
                }});

           */
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout)object);
        }

    }

}
