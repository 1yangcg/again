package com.example.henannews_2.utils;

import android.content.Context;

/**
 * 获取上下文对象的帮助类；<br>
 * 引用者需要先通过ContextHelper.setContext(context)方法把上下文传入<br>
 * HelperLibrary中的帮助类再通过ContextHelper.getContext()获取上下文；<br>
 * 强烈建议在目标项目的Application.onCreate()方法中设置上下文；<br>
 */
public class ContextHelper
{
	private static Context context;

	/** 通过调用者传入上下文对象； */
	public static void setContext(Context context)
	{
		// 通过用户设置的context获取ApplicationContext，避免内在泄漏；
		ContextHelper.context = context.getApplicationContext();
	}

	/** 获取ApplicationContext对象； */
	public static Context getContext()
	{
		return context;
	}

}
