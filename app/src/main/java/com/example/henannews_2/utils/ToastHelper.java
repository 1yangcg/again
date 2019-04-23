package com.example.henannews_2.utils;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/** Toast帮助类； */
public class ToastHelper
{
    /** Toast的位置； */
    private static int gravity, xOffset, yOffset;

    /** 设置Toast显示的位置； */
    public static void setGravity(int gravity, int xOffset, int yOffset)
    {
        ToastHelper.xOffset = xOffset;
        ToastHelper.yOffset = yOffset;
        ToastHelper.gravity = gravity;
    }

    /** 弹出一个系统默认Toast的快捷方法 */
    public static void showShortToast(String message)
    {
        Context context = ContextHelper.getContext();
        showShortToast(context, message);
    }


    public static void showShortToast(int resId)
    {
        Context context = ContextHelper.getContext();
        String message = context.getString(resId);
        showShortToast(context, message);
    }

    /** 弹出一个系统默认Toast的快捷方法 */
    private static void showShortToast(Context context, String message)
    {
        // 如果消息为空，直接返回；
        if (TextUtils.isEmpty(message))
        {
            return;
        }
        // 消息不为空，正常弹出提示；
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        // toast.setGravity(gravity, xOffset, yOffset);
        toast.show();
    }

    /** 弹出一个系统默认Toast的快捷方法 */
    public static void showLongToast(String message)
    {
        Context context = ContextHelper.getContext();
        if (TextUtils.isEmpty(message))
        {
            return;
        }
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(gravity, xOffset, yOffset);
        toast.show();
    }
}
