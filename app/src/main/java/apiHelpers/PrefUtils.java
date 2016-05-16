package apiHelpers;

import android.content.Context;
import android.graphics.Typeface;

public class PrefUtils {

    //EN - English
    //AR - Arabic
    //NA - No language selected

    public static void setPushMessage(Context ctx,String val){
        Prefs.with(ctx).save("push_msg",val);
    }
    public static String getPushMessage(Context ctx){
        String val = Prefs.with(ctx).getString("push_msg", "NA");
        return val;
    }

}
