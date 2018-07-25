package com.took.firstgit;

import android.app.Application;
import android.widget.ImageView;

import com.lzy.imagepicker.loader.ImageLoader;
import com.nrs.utils.tools.CrashHandler;


/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/4/13
 * 描    述：我的Github地址  https://github.com/jeasonlzy0216
 * 修订历史：
 * ================================================
 */
public class GApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initCrashHandler();
    }

    public void initCrashHandler(){
        /**初始化错误日志工具**/
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }
}