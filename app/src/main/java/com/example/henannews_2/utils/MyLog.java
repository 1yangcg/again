package com.example.henannews_2.utils;


import android.util.Log;

import java.util.Arrays;

/**
 * 一款小巧强大的日志输出工具；
 * @author Haoxueren
 */
public class MyLog {

    /** 默认打开日志； */
    public static boolean debug = true;
    // LOG标签；
    private static String tag = "Haoxueren";


    /**
     * 输出普通日志信息
     */
    public static void info(Object... message) {
        if (debug) {
            ThreadInfo thread = new ThreadInfo();
            String header = thread.getClassName() + "." + thread.getMethodName() + "()";
            Log.d(tag, header);
            Log.i(tag, "#" + thread.getLineNumber() + " --> " + Arrays.toString(message));
        }
    }

    /**
     * 输出捕获异常信息
     */
    public static void error(Object... message) {
        if (debug) {
            ThreadInfo thread = new ThreadInfo();
            String header = thread.getClassName() + "." + thread.getMethodName() + "()";
            Log.d(tag, header);
            Log.e(tag, "#" + thread.getLineNumber() + " --> " + Arrays.toString(message));
        }
    }

    /**
     * 输出捕获异常信息
     */
    public static void error(Throwable throwable) {
        if (debug) {
            StackTraceElement[] elements = throwable.getStackTrace();
            Log.e(tag, throwable.getClass().getSimpleName() + "：" + throwable.getMessage());
            for (StackTraceElement element : elements) {
                Log.w(tag, element.getClassName() + "." + element.getMethodName() + "(Line: " + element.getLineNumber
                        () + ")");
            }
        }
    }

    /**
     * System.out.println()
     */
    public static void println(Object... message) {
        if (debug) {
            System.out.println(Arrays.toString(message));
        }
    }

}
