package com.jkkc.carer.Common;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.tencent.bugly.Bugly;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Guan on 2018/5/24.
 */

public class BaseApplication extends Application{



    @Override
    public void onCreate() {
        super.onCreate();

        Bugly.init(this, "42faa0ea22", false);
        Utils.init(this);
        ZXingLibrary.initDisplayOpinion(this);

    }




}
