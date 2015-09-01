package uiCustomControls;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import faidarecharge.com.faidarecharge.R;

/**
 * Created by Priya on 9/1/2015.
 */
public class RedirectToPortalDialog extends Dialog{
    ImageView cancel;

    Context context;
    TextView footerText, edDetails, edHelp;
    boolean isClicked = false;

    public RedirectToPortalDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.redirect_to_portal_dialog);

        cancel = (ImageView) findViewById(R.id.cancel);
        footerText = (TextView) findViewById(R.id.edFooter);
        edDetails = (TextView) findViewById(R.id.edDetails);
        edHelp = (TextView) findViewById(R.id.edHelp);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked = true;
                dismiss();
            }
        });

        AdvancedSpannableString sp = new AdvancedSpannableString("( If you are not redirected to recharge portal automatically then click here)");
        sp.setColor(Color.parseColor("#19b1ec"), "click");
        sp.setUnderLine("click");
        footerText.setText(sp);

        AdvancedSpannableString sp2 = new AdvancedSpannableString("What next?\n" +
                "1. You will be taken to recharge portal now. Wait...\n" +
                "2. Tap again to apply promocode during checkout.\n" +
                "3. Cashback will be credited by recharge portal\n" +
                "In your Wallet within few hours!");

        sp2.setBold("What next?");
        sp2.setBoldItalic("Tap again to apply promocode");
        edDetails.setText(sp2);

        AdvancedSpannableString sp3 = new AdvancedSpannableString("Still Need Help? Call 9439590547");

        sp3.setColor(Color.parseColor("#c00000"), "39");
        sp3.setColor(Color.parseColor("#c00000"), "0547");
        edHelp.setText(sp3);



        footerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isClicked = true;

                String url = "http://www.paytm.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });

        new CountDownTimer(3000,1000) {

            @Override
            public void onFinish() {
                if(!isClicked) {
                    String url = "http://www.paytm.com";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                }
            }
            @Override
            public void onTick(long millisUntilFinished) {
            }
        }.start();

    }
}