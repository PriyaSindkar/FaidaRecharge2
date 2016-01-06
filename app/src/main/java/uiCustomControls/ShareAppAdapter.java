package uiCustomControls;

/**
 * Created by Priya on 1/7/2016.
 */
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import faidarecharge.com.faidarecharge.R;

/**
 * Created by priyasindkar on 17-12-2015.
 */
public class ShareAppAdapter extends BaseAdapter {
    Object[] items;
    private LayoutInflater mInflater;
    Context context;

    public ShareAppAdapter(Context context, Object[] items) {
        this.mInflater = LayoutInflater.from(context);
        this.items = items;
        this.context = context;
    }

    public int getCount() {
        return items.length;
    }

    public Object getItem(int position) {
        return items[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.share_app_single_item, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.text);
            holder.logo = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(((ResolveInfo) items[position]).activityInfo.applicationInfo
                .loadLabel(context.getPackageManager()).toString());

        holder.logo
                .setImageDrawable(((ResolveInfo) items[position]).activityInfo.applicationInfo
                        .loadIcon(context.getPackageManager()));

        return convertView;
    }
    class ViewHolder {

        TextView name;
        ImageView logo;
    }}
