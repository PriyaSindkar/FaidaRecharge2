package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import faidarecharge.com.faidarecharge.R;

/**
 * Created by Priya on 8/30/2015.
 */
public class OffersAdapter extends BaseAdapter {
    private Activity mContext;
    private List<String> mListDetails;
    private List<String> mList;
    private LayoutInflater mLayoutInflater = null;

    public OffersAdapter(Activity context, List<String> list, List<String> listDetails) {
        mContext = context;
        mList = list;
        mListDetails = listDetails;
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
    public View getView(int position, View convertView, ViewGroup parent) {
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
        viewHolder.txtDetails.setText(mList.get(position));
        viewHolder.txtOfferDetails.setText(mListDetails.get(position));
        return v;
    }
}
class CompleteListViewHolder {
    public TextView txtDetails, txtOfferDetails;
    public ImageView logo;
    public CompleteListViewHolder(View base) {
        txtDetails = (TextView) base.findViewById(R.id.txtDetails);
        txtOfferDetails = (TextView) base.findViewById(R.id.txtOfferDetails);
        logo = (ImageView) base.findViewById(R.id.logo);
    }
}
