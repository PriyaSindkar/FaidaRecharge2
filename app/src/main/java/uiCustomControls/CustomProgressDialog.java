package uiCustomControls;

import android.app.Dialog;
import android.content.Context;

import com.victor.loading.rotate.RotateLoading;

import faidarecharge.com.faidarecharge.R;


/**
 * Created by krishnakumar on 01-12-2015.
 */
public class CustomProgressDialog extends Dialog {
    RotateLoading rotateloading;
    public CustomProgressDialog(Context context) {
        super(context);

        init();
    }



    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    private void init(){
        setContentView(R.layout.custom_progress_dialog);
        this.rotateloading = (RotateLoading) findViewById(R.id.rotateloading);
        this.rotateloading.start();

    }
    protected CustomProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void start(){

    }


    public void stop(){
        this.rotateloading.stop();
    }
}
