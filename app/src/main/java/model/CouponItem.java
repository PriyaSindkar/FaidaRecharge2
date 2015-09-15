package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Priya on 9/15/2015.
 */

public class CouponItem {

    @SerializedName("title")
    public String couponTitle;

    @SerializedName("desc1")
    public String couponDescription;

    @SerializedName("code")
    public String couponCode;

    @SerializedName("link")
    public String websiteLink;

    @SerializedName("catg")
    public String couponCategory;

    @SerializedName("store")
    public String couponStore;

    @SerializedName("ishomepage ")
    public String ishomepage;


    @Override
    public String toString() {
        return "CouponItem{" +
                "couponTitle='" + couponTitle + '\'' +
                ", couponDescription='" + couponDescription + '\'' +
                ", couponCode='" + couponCode + '\'' +
                ", websiteLink='" + websiteLink + '\'' +
                ", couponCategory='" + couponCategory + '\'' +
                ", couponStore='" + couponStore + '\'' +
                ", ishomepage='" + ishomepage + '\'' +
                '}';
    }
}
