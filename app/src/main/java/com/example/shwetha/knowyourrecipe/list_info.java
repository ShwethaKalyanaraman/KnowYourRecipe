package com.example.shwetha.knowyourrecipe;

import android.graphics.Bitmap;

/**
 * Created by SHWETHA on 13-09-2016.
 */
public class list_info {

    private String ingredients;
    private String imgResID;

    list_info(String ingredients, String imgResID)
    {

        this.ingredients=ingredients;

        this.imgResID=imgResID;

    }


    public void setImgResID(String imgResID) {
        this.imgResID = imgResID;
    }

    public String getImgResID() {
        return imgResID;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
