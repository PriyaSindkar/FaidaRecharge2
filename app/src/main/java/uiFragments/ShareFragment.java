package uiFragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

import faidarecharge.com.faidarecharge.R;
import uiActivities.MyDrawerActivity;
import uiCustomControls.AdvancedSpannableString;

/**
 * Created by Priya on 8/30/2015.
 */
public class ShareFragment extends Fragment{
    TextView txtSplashText1, txtSplashText2, txtSplashText3, txtSplashText4, txtSplashText5;
    View line1, line2, line3, line4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setHasOptionsMenu(true);
    }

    /*@Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_search).setVisible(false);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_share, container, false);
        setHasOptionsMenu(true);
        //((MyDrawerActivity) getActivity()).hideMenu();
        init(rootView);

        return rootView;
    }

    private void init(View rootView) {

        ((MyDrawerActivity) getActivity()).setTitle("Share The Love");

        txtSplashText1 = (TextView) rootView.findViewById(R.id.txtSplashText1);
        /*txtSplashText2 = (TextView) rootView.findViewById(R.id.txtSplashText2);
        txtSplashText3 = (TextView) rootView.findViewById(R.id.txtSplashText3);
        txtSplashText4 = (TextView) rootView.findViewById(R.id.txtSplashText4);
        txtSplashText5 = (TextView) rootView.findViewById(R.id.txtSplashText5);

        line1 = rootView.findViewById(R.id.line1);
        line2 = rootView.findViewById(R.id.line2);
        line3 = rootView.findViewById(R.id.line3);
        line4 = rootView.findViewById(R.id.line4);

        AdvancedSpannableString sp = new AdvancedSpannableString("If you love the way it works then share us.");
        sp.setColor(Color.parseColor("#19b1ec"), "share");
        sp.setUnderLine("share");


        txtSplashText1.setText(sp);
*/
       /* txtSplashText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Invitation to Faida Recharge");
                i.putExtra(android.content.Intent.EXTRA_TEXT, "Reach Faida Recharge @ faidarecharge.com");
                startActivity(Intent.createChooser(i, "Share via"));*/
        txtSplashText1.setText("If you love the way it works then share us.");

                Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                List activities = getActivity().getPackageManager().queryIntentActivities(sendIntent, 0);

                ListView lv=(ListView) rootView.findViewById(R.id.listView);
                final ShareAdapter adapter=new ShareAdapter(getActivity(),activities.toArray());
                lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                ResolveInfo info = (ResolveInfo) adapter.getItem(arg2);

                    Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Invitation to Faida Recharge");
                    intent.putExtra(Intent.EXTRA_TEXT, "Reach Faida Recharge @ faidarecharge.com");
                    getActivity().startActivity(intent);

            }
        });
           /* }
        });*/


        /*txtSplashText2.setVisibility(View.GONE);
        txtSplashText3.setVisibility(View.GONE);
        txtSplashText4.setVisibility(View.GONE);
        txtSplashText5.setVisibility(View.GONE);

        line1.setVisibility(View.GONE);
        line2.setVisibility(View.GONE);
        line3.setVisibility(View.GONE);
        line4.setVisibility(View.GONE);*/
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
    }

     class ShareAdapter extends BaseAdapter {
        Object[] items;
        private LayoutInflater mInflater;
        Context context;

        public ShareAdapter(Context context, Object[] items) {
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
                convertView = mInflater.inflate(R.layout.single_item, null);
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.text);
                holder.logo = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name
                    .setText(((ResolveInfo) items[position]).activityInfo.applicationInfo
                            .loadLabel(context.getPackageManager()).toString());

            Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Regular.ttf");
            holder.name.setTypeface(typeface);

            holder.logo
                    .setImageDrawable(((ResolveInfo) items[position]).activityInfo.applicationInfo
                            .loadIcon(context.getPackageManager()));

            return convertView;
        }
         class ViewHolder {

            TextView name;
            ImageView logo;
        }}

}




