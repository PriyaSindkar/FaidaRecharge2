package uiFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import adapters.CategoryAdapter;
import faidarecharge.com.faidarecharge.R;
import model.CategoryItem;

/**
 * Created by Priya on 8/30/2015.
 */
public class CategoryFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private CategoryAdapter adapter;
    private List<CategoryItem> categoryList = new ArrayList<>();

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

        init(rootView);

        return rootView;
    }

    private void init(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(layoutManager);

        // Initialize Category list
        categoryList.add(new CategoryItem("Mobile Recharge Offers"));
        categoryList.add(new CategoryItem("DTH Recharge Offers"));
        categoryList.add(new CategoryItem("Bill Payment Offers"));

        adapter = new CategoryAdapter(getActivity(), categoryList);
        mRecyclerView.setAdapter(adapter);
    }

}




