package com.tuanche.smc.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ZiXunDateUtil {

	public  static  String getDateToString(){
		String returnDate="";
		Date date = new Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		returnDate=df.format(date);
		return returnDate;
	}
	public  static  String getDateToStringMM(){
		String returnDate="";
		Date date = new Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
		returnDate=df.format(date);
		return returnDate;
	}
	//后推一天
	public  static  String getEndDateToString(){
		String returnDate="";
		Date date = new Date(System.currentTimeMillis()+24*60*60*1000);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		returnDate=df.format(date);
		return returnDate;
	}
	public static void main(String[] args) {
		String returnDate="";
		Date date = new Date(System.currentTimeMillis()+24*60*60*1000);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		returnDate=df.format(date);
		System.out.println(returnDate);
	}
	public  static  String getEndDir(){
		String returnDate="";
		Date date = new Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		returnDate=df.format(date);
		return returnDate;
	}
	
	public static void copyfile(String path1, String path2) throws IOException
	// 使用FileInputStream和FileOutStream
	{
		FileInputStream fi = new FileInputStream(path1);
		FileOutputStream fo = new FileOutputStream(path2);
		byte data[] = new byte[fi.available()];
		
		fi.read(data);
		fo.write(data);
		fi.close();
		fo.close();
	}
}
