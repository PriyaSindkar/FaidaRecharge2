package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import faidarecharge.com.faidarecharge.R;
import model.CategoryItem;

/**
 * Created by Priya on 9/1/2015.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CustomViewHolder> {
    private List<CategoryItem> feedItemList;
    private Context mContext;

    public CategoryAdapter(Context context, List<CategoryItem> _feedItemList) {
        this.feedItemList = _feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_row, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        CategoryItem feedItem = feedItemList.get(i);
        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));
        customViewHolder.textView.setTag(customViewHolder);
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomViewHolder holder = (CustomViewHolder) view.getTag();
            int position = holder.getPosition();

            String feedItem = feedItemList.get(position).getTitle();
            Toast.makeText(mContext,feedItem, Toast.LENGTH_SHORT).show();
        }
    };

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.category_name);
        }
    }
}