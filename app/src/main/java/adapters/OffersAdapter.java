package adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import faidarecharge.com.faidarecharge.R;
import model.ComplexPreferences;
import model.CouponItem;
import model.StoreModel;
import uiActivities.OfferDetailsActivity;

/**
 * Created by Priya on 8/30/2015.
 */
public class OffersAdapter extends BaseAdapter {
    private Activity mContext;
    private List<CouponItem> mList;
    private ArrayList<StoreModel> storeItems;
    private static String IMAGE_URL = "http://faidarecharge.com/admin/upload_images/";
    private LayoutInflater mLayoutInflater = null;

    public OffersAdapter(Activity context, List<CouponItem> list, ArrayList<StoreModel> _storeItems) {
        mContext = context;
        mList = list;
        this.storeItems = _storeItems;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int pos) {
        return mList.get(pos);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        CompleteListViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item_offer, null);
            viewHolder = new CompleteListViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (CompleteListViewHolder) v.getTag();
        }
        viewHolder.txtDetails.setText(mList.get(position).couponTitle);
        viewHolder.txtGetOfferDetails.setText("Get Offer Details");

        String url = "";

        for(int i=0; i<storeItems.size(); i++) {
            if(storeItems.get(i).storeName.equals(mList.get(position).couponStore)) {
                url = IMAGE_URL + storeItems.get(i).imageURL;
                break;
            }
        }

        Glide.with(mContext)
                .load(url).placeholder(R.drawable.faidarecharge).into(viewHolder.logo);

        final String finalUrl = url;
        viewHolder.txtGetOfferDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, OfferDetailsActivity.class);
                intent.putExtra("image_url", finalUrl);
                /*intent.putExtra("code", mList.get(position).couponCode);
                intent.putExtra("website", mList.get(position).websiteLink);*/

                ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(mContext, "coupon-details", 0);
                complexPreferences.putObject("coupon-details", mList.get(position));
                complexPreferences.commit();
                mContext.startActivity(intent);
            }
        });
        return v;

    }
}
    class CompleteListViewHolder {
        public TextView txtDetails, txtGetOfferDetails;
        public ImageView logo;
        public CompleteListViewHolder(View base) {
            txtDetails = (TextView) base.findViewById(R.id.txtDetails);
            txtGetOfferDetails = (TextView) base.findViewById(R.id.txtGetOfferDetails);
            logo = (ImageView) base.findViewById(R.id.logo);
        }
    }
