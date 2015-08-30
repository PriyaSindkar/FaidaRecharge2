package uiFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import faidarecharge.com.faidarecharge.R;

/**
 * Created by Priya on 8/30/2015.
 */
public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;

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
    }

    public class TabsPagerAdapter extends FragmentPagerAdapter {


        //private final String[] TITLES = { "","" };


        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


       /* @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }*/


        @Override
        public int getCount() {
            return 2;
        }


        @Override
        public Fragment getItem(int position) {

            if (position==0) {
                return OfferFragmentOne.newInstance();
            }else if(position==1) {
                return OfferFragmentOne.newInstance();
            }
            else{
                return OfferFragmentOne.newInstance();
            }
        }


    }

}




