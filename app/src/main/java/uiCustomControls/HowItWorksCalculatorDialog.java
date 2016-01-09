package uiCustomControls;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import junit.framework.TestResult;

import faidarecharge.com.faidarecharge.R;

/**
 * Created by Priya on 9/1/2015.
 */
public class HowItWorksCalculatorDialog extends Dialog {
    ImageView cancel;
    TextView edDetails, txtread;

    Context context;

    public HowItWorksCalculatorDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.terms_and_conditions_dialog);

        ViewGroup.LayoutParams params = getWindow().getAttributes();
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        cancel = (ImageView) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        edDetails = (TextView) findViewById(R.id.edDetails);
        edDetails.setText(context.getString(R.string.referral_system_intro_calculation));

        txtread = (TextView) findViewById(R.id.txtread);
        txtread.setVisibility(View.GONE);
    }
}