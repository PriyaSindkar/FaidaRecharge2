package uiCustomControls;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import faidarecharge.com.faidarecharge.R;

/**
 * Created by Priya on 9/1/2015.
 */
public class RedirectToPortalDialog extends Dialog {
    ImageView cancel;

    Context context;
    TextView footerText, edDetails, edHelp, txtGoToPortal;
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

        ViewGroup.LayoutParams params = getWindow().getAttributes();
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        cancel = (ImageView) findViewById(R.id.cancel);
        footerText = (TextView) findViewById(R.id.edFooter);
        edDetails = (TextView) findViewById(R.id.edDetails);
        edHelp = (TextView) findViewById(R.id.edHelp);
        txtGoToPortal = (TextView) findViewById(R.id.txtGoToPortal);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked = true;
                dismiss();
            }
        });

        setCancelable(false);

        /*AdvancedSpannableString sp = new AdvancedSpannableString("( If you are not redirected to recharge portal automatically then click here)");
        sp.setColor(Color.parseColor("#19b1ec"), "click");
        sp.setUnderLine("click");
        footerText.setText(sp);*/

        AdvancedSpannableString sp2 = new AdvancedSpannableString("Step 1: Go to recharge portal.\n" +
                "Step 2: Just tap again to paste in the  \"Apply promocode\" section.\n\n" +
                "Get Extra cashback  in your wallet  from respective  recharge portals!");

        sp2.setBold("Step 1:");
        sp2.setBold("Step 2:");
        sp2.setBackgroundColor(Color.YELLOW, "Apply promocode");
        edDetails.setText(sp2);


        Animation anim = new AlphaAnimation(0.5f, 1.0f);
        anim.setDuration(100); //You can manage the time of the blink with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        txtGoToPortal.startAnimation(anim);

        /*AdvancedSpannableString sp3 = new AdvancedSpannableString("Still Need Help? Call 9439590547");

        sp3.setColor(Color.parseColor("#c00000"), "39");
        sp3.setColor(Color.parseColor("#c00000"), "0547");
        edHelp.setText(sp3);*/



        txtGoToPortal.setOnClickListener(new View.OnClickListener() {
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