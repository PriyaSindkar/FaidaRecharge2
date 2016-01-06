package uiCustomControls;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import faidarecharge.com.faidarecharge.R;

/**
 * Created by priyasindkar on 17-12-2015.
 */
public class CustomShareAppDialog extends Dialog  {


    Context context;
    ImageView cancel;
    ListView listView;
    private String subject, text;


    public CustomShareAppDialog(Context context, String promoCode, String userName) {
        super(context);
        this.context = context;
        this.subject = "Faida Recharge Offers!";

        StringBuilder message = new StringBuilder();
        message.append(context.getString(R.string.share_content1));
        message.append(context.getString(R.string.share_content2));
        message.append( promoCode);
        message.append(context.getString(R.string.share_content3));
        message.append(context.getString(R.string.share_content4));
        message.append(userName);

        this.text = message.toString();
    }


    public CustomShareAppDialog(Context context, int theme, String email, String phoneNo) {
        super(context, theme);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.custon_share_app_dialog);

        init();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.listView);

        Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        List activities = context.getPackageManager().queryIntentActivities(sendIntent, 0);

        final ShareAppAdapter adapter = new ShareAppAdapter(context, activities.toArray());
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ResolveInfo info = (ResolveInfo) adapter.getItem(arg2);

                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, text);
                context.startActivity(intent);
                dismiss();

            }
        });

        cancel = (ImageView) findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
