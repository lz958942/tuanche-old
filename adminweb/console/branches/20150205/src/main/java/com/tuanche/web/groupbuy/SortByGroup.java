package com.tuanche.web.groupbuy;

import java.util.Comparator;

import com.tuanche.bean.che.CarstyleGroupbuy;

public class SortByGroup implements Comparator
{

	@Override
	public int compare(Object o1, Object o2) 
	{
		CarstyleGroupbuy s1 = (CarstyleGroupbuy) o1;
		CarstyleGroupbuy s2 = (CarstyleGroupbuy) o2;

		if ((s1 != null) && (s2 != null) && (s1.getBaseSeq() > s2.getBaseSeq())) {
			return 1;
		} else {
			return -1;
		}
	}
}
