package uiActivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.util.ArrayList;
import java.util.List;

import apiHelpers.EnumType;
import apiHelpers.GetPostClass;
import faidarecharge.com.faidarecharge.R;
import login.BaseLoginFragment;
import login.LoginActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class SplashActivity extends ActionBarActivity {
    GoogleCloudMessaging gcm;
    String regid;
    String PROJECT_NUMBER = "78688519071";
    String driverIMEI_Number;
    String addUSER_URL = "http://www.faidarecharge.com/admin/adduser.php";
    TextView txtSplashText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);
        /*txtSplashText = (TextView) findViewById(R.id.txtSplashText);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        txtSplashText.setTypeface(typeface);*/


        if (checkInternet()) {

            boolean mboolean = false;
            SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
            mboolean = settings.getBoolean("FIRST_RUN", false);
            if (!mboolean) {

                // do the thing for the first time
                settings = getSharedPreferences("PREFS_NAME", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("FIRST_RUN", true);
                editor.commit();

                addUser();

            } else {

               new CountDownTimer(1500,1500){

                   @Override
                   public void onTick(long millisUntilFinished) {

                   }

                   @Override
                   public void onFinish() {
                       Intent intent = new Intent(getBaseContext(), MyDrawerActivity.class);
                       startActivity(intent);
                       finish();
                   }
               }.start();


            }
        }else{
            Toast.makeText(getApplicationContext(), "Please connect your Internet", Toast.LENGTH_LONG).show();
            finish();
        }


    }




    private void addUser() {
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        driverIMEI_Number= telephonyManager.getDeviceId();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                driverIMEI_Number= telephonyManager.getDeviceId();

                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(SplashActivity.this);
                    }
                    regid = gcm.register(PROJECT_NUMBER);
                   // regid ="dd";

                    Log.e("GCM ID :", regid);
                    if(regid==null || regid==""){
                        SharedPreferences preferences = getSharedPreferences("run_before",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("RanBefore", false);
                        editor.commit();
                        AlertDialog.Builder alert = new AlertDialog.Builder(SplashActivity.this);
                        alert.setTitle("Error");
                        alert.setMessage("Internal Server Error");
                        alert.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                addUser();
                                dialog.dismiss();
                            }
                        });
                        alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        });
                        alert.show();
                    } else {

                        //call webservice
                        String android_id = Settings.Secure.getString(getContentResolver(),
                                Settings.Secure.ANDROID_ID);
                        // progressDialog =new ProgressDialog(SplashActivity.this);
                        // progressDialog.setMessage("Loading");
                        //  progressDialog.show();
                        List<NameValuePair> pairs = new ArrayList<>();

                        pairs.add(new BasicNameValuePair("gcm_regid",regid));
                        pairs.add(new BasicNameValuePair("device_id",android_id));

                        new GetPostClass(addUSER_URL,pairs, EnumType.POST) {

                            @Override
                            public void response(String response) {
                                Log.e("### res",response);

                                Intent intent = new Intent(getBaseContext(), MyDrawerActivity.class);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void error(String error) {
                                Log.e("### res", error);
                                // progressDialog.dismiss();
                            }

                        }.call();



                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        }.execute();
    }





    private boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(SplashActivity.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
        {
            return true;
        } else {
            return false;
        }
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
