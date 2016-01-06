package uiReferralSystem;

/**
 * Created by Priya on 1/7/2016.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import faidarecharge.com.faidarecharge.R;

/**
 * Created by Synotek on 01/09/2015.
 */
public class KnowledgeBaseAdapter extends BaseExpandableListAdapter {


    Activity cnt;
    private ArrayList<Object> childtems;

    private LayoutInflater inflater;

    private ArrayList<String> parentItems, child;

    public KnowledgeBaseAdapter(ArrayList<Object> childtems, ArrayList<String> parentItems) {
        this.parentItems = parentItems;
        this.childtems = childtems;
    }

    public void setInflater(LayoutInflater inflater, Activity cnt) {

        this.inflater = inflater;

        this.cnt = cnt;

    }

    @Override
    public int getGroupCount() {
        return parentItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) childtems.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i2) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i2) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        CheckedTextView checkedTextView;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.row, null);

        }

        checkedTextView = (CheckedTextView) convertView.findViewById(R.id.textView2);
        ((CheckedTextView) convertView).setText(parentItems.get(groupPosition));

        ((CheckedTextView) convertView).setChecked(isExpanded);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        child = (ArrayList<String>) childtems.get(groupPosition);

        TextView textView = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.knowledge_base_list_item, null);
        }

        textView = (TextView) convertView.findViewById(R.id.textView1);

        textView.setText(child.get(childPosition));

       /* convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cnt, child.get(childPosition),Toast.LENGTH_SHORT).show();
            }
        });*/

        return convertView;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

        super.onGroupCollapsed(groupPosition);

    }

    @Override
    public void onGroupExpanded(int groupPosition) {

        super.onGroupExpanded(groupPosition);

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
