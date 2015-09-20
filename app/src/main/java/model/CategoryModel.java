package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Priya on 9/19/2015.
 */
public class CategoryModel {

    @SerializedName("catg_name")
    public String categoryTitle;

    @SerializedName("img_url ")
    public String imageURL;
}
