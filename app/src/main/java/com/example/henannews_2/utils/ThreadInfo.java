package com.example.henannews_2.utils;

/** 获取当前的线程信息； */
public class ThreadInfo
{
	private StackTraceElement element;

	public ThreadInfo()
	{
		element = Thread.currentThread().getStackTrace()[4];
	}

	/** 获取线程的名称； */
	public static String getThreadName()
	{
		return Thread.currentThread().getName();
	}

	/** 获取当前线程所在类名； */
	public String getClassName()
	{
		return element.getClassName();
	}

	/** 获取当前线程所在类名； */
	public String getFileName()
	{
		return element.getFileName();
	}

	/** 获取当前线程所在的方法名； */
	public String getMethodName()
	{
		return element.getMethodName();
	}

	/** 获取当前线程所在的行号； */
	public int getLineNumber()
	{
		return element.getLineNumber();
	}

}
