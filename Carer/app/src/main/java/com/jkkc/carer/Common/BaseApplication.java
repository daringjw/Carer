package com.jkkc.carer.Common;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by Guan on 2018/5/24.
 */

public class BaseApplication extends Application{



    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);

    }


}
