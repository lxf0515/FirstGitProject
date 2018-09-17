package com.took.firstgit.ui.notice;

import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

import com.took.firstgit.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NotificationUtils {

    private static final String CHECK_OP_NO_THROW    = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean isNotificationEnabled(Context context) {
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String packageName = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class<?> appOpsClass = null;
        try {

            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method method = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class);
            Field notificationFieldValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (int) notificationFieldValue.get(Integer.class);
            return ((int) method.invoke(appOps, value, uid, packageName) == AppOpsManager.MODE_ALLOWED);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }



    public static void toSetting(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));

        context.startActivity(localIntent);
    }


    public static void toSetting2(Context context) {
        NotificationManagerCompat.from(context).areNotificationsEnabled();
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(localIntent);
    }
}
