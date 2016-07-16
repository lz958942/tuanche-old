package com.tuanche.console.adminwrite;

import org.apache.ibatis.annotations.Param;

import com.tuanche.console.bean.Dimension;

public interface DimensionWriteMapper {

	public void insertDimension(@Param(value="array") Object[] obj);

	public void update(Dimension dimension);

	public void deleteDie(Integer id);

	public void deleteAllDim(int[] id);
}
