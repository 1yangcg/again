package com.example.henannews_2.utils;


import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

public class TraverseUtils {

    public static void traverse(ViewGroup parent) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            String name = child.getClass().getSimpleName();
            MyLog.info(name, " in ", parent.getClass().getSimpleName());
            if (child instanceof ViewGroup) {
                traverse((ViewGroup) child);
            }
        }
    }

    // 遍历Cursor的内部结构
    public void traverse(Cursor cursor) {
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            String columnName = cursor.getColumnName(i);
            String value = cursor.getString(cursor.getColumnIndex(columnName));
            System.out.println(columnName + "=" + value);
        }
    }
}
