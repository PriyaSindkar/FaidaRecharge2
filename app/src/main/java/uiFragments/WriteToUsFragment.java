package uiFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import faidarecharge.com.faidarecharge.R;
import uiActivities.MyDrawerActivity;

/**
 * Created by Priya on 8/30/2015.
 */
public class WriteToUsFragment extends Fragment{
    TextView txtSplashText1, txtSplashText2, txtSplashText3, txtSplashText4, txtSplashText5;
    View line1, line2, line3, line4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        setHasOptionsMenu(true);
       // ((MyDrawerActivity) getActivity()).hideMenu();
        init(rootView);

        return rootView;
    }

    private void init(View rootView) {

        ((MyDrawerActivity) getActivity()).setTitle("Write To Us");

        txtSplashText1 = (TextView) rootView.findViewById(R.id.txtSplashText1);
        txtSplashText2 = (TextView) rootView.findViewById(R.id.txtSplashText2);
        txtSplashText3 = (TextView) rootView.findViewById(R.id.txtSplashText3);
        txtSplashText4 = (TextView) rootView.findViewById(R.id.txtSplashText4);
        txtSplashText5 = (TextView) rootView.findViewById(R.id.txtSplashText5);

        line1 = rootView.findViewById(R.id.line1);
        line2 = rootView.findViewById(R.id.line2);
        line3 = rootView.findViewById(R.id.line3);
        line4 = rootView.findViewById(R.id.line4);

        txtSplashText1.setText("Do you have any suggestion, ideas for  impovement?  Write to us. We would listen  you and even reward your for your brilliant suggestions. ");
        txtSplashText2.setText("Planning an advertisement? Do write to us also. ");
        txtSplashText3.setText("Any other queries? Must write to us.\nEmail: faidarecharge@gmail.com");
        txtSplashText4.setVisibility(View.GONE);
        txtSplashText5.setVisibility(View.GONE);

        line4.setVisibility(View.GONE);
        line3.setVisibility(View.GONE);
        line2.setVisibility(View.GONE);
        line1.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
    }
}




