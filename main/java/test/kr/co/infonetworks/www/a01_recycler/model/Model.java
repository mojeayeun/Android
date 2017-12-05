package test.kr.co.infonetworks.www.a01_recycler.model;

import android.graphics.drawable.Drawable;

/**
 * Created by andy on 2017. 12. 2..
 */

public class Model {
    Drawable image;
    String name;

    public Model(Drawable image, String name) {
        this.image = image;
        this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
