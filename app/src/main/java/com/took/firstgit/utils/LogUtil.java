package com.took.firstgit.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 日志文件， 所有日志用LLog，用法跟Log一样
 */
public class LogUtil {


	public static  String TAG 		="OTCLog";
	private static LogUtil 		_gLog 		= null;
	private boolean 			_outputLog  = true;
	private FileWriter 			_logFileWrite= null;
	public static String 		_logFileName = "/data/local/tmp/otc.txt";

	//测试哦
	private  static final LogUtil getLog() {
		if (_gLog==null) {
			_gLog =new LogUtil();
			_gLog._outputLog  = new File(_logFileName).exists();
			_gLog._outputLog  = true;
			if (_gLog._outputLog==true) {
				try {
					File logFile = new File(_logFileName);
					if (logFile.exists()) {
						_gLog._logFileWrite = new FileWriter(logFile);
					}
				} catch (IOException e) {
					_gLog._logFileWrite=null;
				}
			}
		}
		return _gLog;
	}
	
	@SuppressLint("SimpleDateFormat")
	static private void internal_log(String msg, int level ) {
		if (LogUtil.getLog()._outputLog==false) return;
        if (msg==null) return;
 		String writeMSG = msg;
        StackTraceElement ste = new Throwable().getStackTrace()[2];
		String methodName     = new Exception().getStackTrace()[2].getMethodName();
		String fileName       = ste.getFileName();
        if (fileName!=null && fileName.indexOf(".")>0) {
            fileName          = fileName.substring(0,fileName.indexOf("."));
        }
        else {
            fileName          = "";
        }
		writeMSG = "["+fileName+":"+methodName+":"+ste.getLineNumber()+"] " +msg;
        switch(level){
            case Log.ERROR:
            default:
                Log.e(TAG, writeMSG);
                break;
        }
		if (_gLog._logFileWrite!=null) {
			Date currentDate 	  = new Date(System.currentTimeMillis());
			String currentTime    = new SimpleDateFormat("HH:mm:ss:sss").format(currentDate);
			writeMSG = "["+currentTime+"] "+writeMSG+"\n";
			try {
				_gLog._logFileWrite.write(writeMSG);
				_gLog._logFileWrite.flush();
			} catch (IOException e) {
				_gLog._logFileWrite=null;
			}
		}
    }

	public static boolean isOutputLog() {
		if(LogUtil.getLog() != null)
			return LogUtil.getLog()._outputLog;
		else
			return false;
	}


	//日志接口，使用方法跟android系统的Log对应
    static public void v(String msg) {
        internal_log(msg, Log.VERBOSE);
    }
    static public void w(String msg) {
        internal_log(msg, Log.WARN);
    }
    static public void i(String msg) {
        internal_log(msg, Log.INFO);
    }
    static public void d(String msg) {
        internal_log(msg, Log.DEBUG);
    }
	static public void e(String msg) {
		internal_log(msg, Log.ERROR);
	}
	static public void e(Exception e){
		try {
	        StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	        e.printStackTrace(pw);
		    internal_log(" ERROR:"+e.getMessage(), Log.ERROR);
		    internal_log("\r\n" + sw.toString() + "\r\n", Log.ERROR);
	    } catch (Exception e2) {
	    	internal_log(" bad getErrorInfoFromException", Log.ERROR);
	    }
	}
}
