package uiFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapters.OffersAdapter;
import faidarecharge.com.faidarecharge.R;

/**
 * Created by Priya on 8/30/2015.
 */
public class OfferFragmentOne extends Fragment {
    OffersAdapter adapter;
    ListView listOffers;
    List<String> headersList = new ArrayList<>();
    List<String> detailsList = new ArrayList<>();

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
        initOfferDetails();
        adapter = new OffersAdapter(getActivity(), headersList,detailsList);
        listOffers.setAdapter(adapter);
    }

    private void initOfferDetails() {
        headersList.add("Get Rs.100 Cashback for DTH Recharge of Rs.500 or above.");
        headersList.add("Get Rs.100 Cashback for DTH Recharge of Rs.500 or above.");
        headersList.add("Get Rs.100 Cashback for DTH Recharge of Rs.500 or above.");
        headersList.add("Get Rs.100 Cashback for DTH Recharge of Rs.500 or above.");

        detailsList.add("Get Offer Details");
        detailsList.add("Get Offer Details");
        detailsList.add("Get Offer Details");
        detailsList.add("Get Offer Details");

    }


}




