package login;

import android.content.Intent;
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

public class BaseLoginFragment extends Fragment {

    TextView txtNewuser, txtLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_login, container, false);
        setHasOptionsMenu(true);
        //((MyDrawerActivity) getActivity()).hideMenu();
        init(rootView);

        return rootView;
    }

    private void init(View rootView) {
        txtNewuser = (TextView) rootView.findViewById(R.id.txtNewuser);
        txtLogin = (TextView) rootView.findViewById(R.id.txtLogin);

        txtNewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
    }

}
