package com.tuanche.smc.common;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class ChangePINYIN {
    
    
	public static String getPingYin(String src) {
		if(src==null||src.trim().length()==0){
			return "";
		}
		char[] t1 = null;
		src=src.toLowerCase();
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					if(t2!=null && t2.length >0){
						t4 += t2[0];
					}
						
				} else
					t4 += java.lang.Character.toString(t1[i]);
			}
			return t4;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return t4;
	}

	
	
	public static void main(String args[]){
	    System.out.println(getPingYin("杨宗山"));
	}
}