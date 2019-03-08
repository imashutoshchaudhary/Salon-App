package com.example.sampat.brillsalon;

import android.graphics.Bitmap;
import android.view.View;

public class Screenshot {
    public static Bitmap stakescreenshot(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b=Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(true);
        return b;
    }
    public static Bitmap takescreenshotrootview(View v){
        return stakescreenshot(v.getRootView());
    }
}
