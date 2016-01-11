package uiCustomControls;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import junit.framework.TestResult;

import faidarecharge.com.faidarecharge.R;

/**
 * Created by Priya on 9/1/2015.
 */
public class HowItWorksCalculatorDialog extends Dialog {
    ImageView cancel;
    TextView edDetails, txtHeading;
    LinearLayout linearVariables;

    Context context;

    public HowItWorksCalculatorDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.calculate_how_it_works_dialog);

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

/*        edDetails = (TextView) findViewById(R.id.edDetails);
        edDetails.setText(context.getString(R.string.referral_system_intro_calculation));*/

        linearVariables = (LinearLayout) findViewById(R.id.linearVariables);

        initVariables();


    }


    private void initVariables() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.variables_item, null);
        ImageView imgVariable = (ImageView) view.findViewById(R.id.imgVariable);
        TextView txtVariable = (TextView) view.findViewById(R.id.txtVariable);
        imgVariable.setImageResource(R.drawable.ic_friend);
        txtVariable.setText("No. Friends You Can Refer To");
        linearVariables.addView(view);

        view = inflater.inflate(R.layout.variables_item, null);
        imgVariable = (ImageView) view.findViewById(R.id.imgVariable);
        txtVariable = (TextView) view.findViewById(R.id.txtVariable);
        imgVariable.setImageResource(R.drawable.ic_crowd);
        txtVariable.setText("No. Friends Your Friends Can Refer To");
        linearVariables.addView(view);

        view = inflater.inflate(R.layout.variables_item, null);
        imgVariable = (ImageView) view.findViewById(R.id.imgVariable);
        txtVariable = (TextView) view.findViewById(R.id.txtVariable);
        imgVariable.setImageResource(R.drawable.ic_my_avg);
        txtVariable.setText("My Monthly Recharge Amount Avg.");
        linearVariables.addView(view);

        view = inflater.inflate(R.layout.variables_item, null);
        imgVariable = (ImageView) view.findViewById(R.id.imgVariable);
        txtVariable = (TextView) view.findViewById(R.id.txtVariable);
        imgVariable.setImageResource(R.drawable.ic_all_avg);
        txtVariable.setText("Avg. Monthly Recharge Amount of All");
        linearVariables.addView(view);

        view = inflater.inflate(R.layout.variables_item, null);
        imgVariable = (ImageView) view.findViewById(R.id.imgVariable);
        txtVariable = (TextView) view.findViewById(R.id.txtVariable);
        imgVariable.setImageResource(R.drawable.ic_rupee);
        txtVariable.setText("My Monthly Earnings");
        linearVariables.addView(view);

    }
}