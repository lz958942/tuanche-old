package com.tuanche.mapper.che.read;

import java.util.HashMap;
import java.util.List;

import com.tuanche.bean.che.CarStyle;

public interface CarStylesReadMapper {

	List<CarStyle> getCarStyle();

	List<CarStyle> getCarStyleById(HashMap<String, Object> map);


}
