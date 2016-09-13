package com.example.shwetha.knowyourrecipe;

/**
 * Created by SHWETHA on 13-09-2016.
 */
public class list_info {

    private String ingredients;
    private int imgResID;

    list_info(String ingredients,int imgResID)
    {

        this.ingredients=ingredients;

        this.imgResID=imgResID;

    }


    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }

    public int getImgResID() {
        return imgResID;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
