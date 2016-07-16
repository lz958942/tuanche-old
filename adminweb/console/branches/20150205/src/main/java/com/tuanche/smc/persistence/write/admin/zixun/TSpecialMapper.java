package com.tuanche.smc.persistence.write.admin.zixun;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.console.bean.TSpecial;

public interface TSpecialMapper {

	public void excleBatchImport(@Param("tSpecial")List<TSpecial> tSpecial);
}
