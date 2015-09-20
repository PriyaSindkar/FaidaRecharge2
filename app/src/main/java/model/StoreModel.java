package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Priya on 9/19/2015.
 */
public class StoreModel {

    @SerializedName("store_name")
    public String storeName;

    @SerializedName("web_url")
    public String webURL;

    @SerializedName("img_url ")
    public String imageURL;
}
