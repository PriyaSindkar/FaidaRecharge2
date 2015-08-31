package model;

/**
 * Created by Priya on 9/1/2015.
 */
public class CategoryItem {
    public CategoryItem() {
    }

    public CategoryItem(String title) {
        this.title = title;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
