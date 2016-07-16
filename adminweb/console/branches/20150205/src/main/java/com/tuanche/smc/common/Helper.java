package com.tuanche.smc.common;

import com.tuanche.commons.util.Resources;

public class Helper {
	public static String getConfig(String key) {
		return Resources.getString(key);
	}

	public static int getConfigAsInt(String key) {
		return Integer.valueOf(getConfig(key));
	}

	public static String getTimestampTillNow(long dateTimeStamp) {
		if (dateTimeStamp > 0) {
			long t = (System.currentTimeMillis() - dateTimeStamp * 1000) / 1000; // 换算为秒
			// 小于五分钟
			if (t < 5 * 60) {
				return "刚刚";
			}
			// 五分钟至一小时
			else if (t < 3600) {
				return String.valueOf(t / 60) + "分钟前";
			}
			// 24小时
			else if (t < 86400) {
				return String.valueOf(t / 3600) + "小时前";
			}
			// 24-48小时
			else if (t < 172800) {
				return "昨天";
			}
			// 48小时以上
			else {
				int days = (int) t / 86400;
				if (days > 3) {
					days = 3;
				}
				return String.valueOf(days) + "天前";
			}
		}
		return "未知";
	}
}
