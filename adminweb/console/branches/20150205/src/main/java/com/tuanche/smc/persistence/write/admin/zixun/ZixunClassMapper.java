package com.tuanche.smc.persistence.write.admin.zixun;

import com.tuanche.smc.domain.base.NewsClassify;

public interface ZixunClassMapper {
    void updateZixunClassStatus(NewsClassify newsClassify);
    void updateZixunClass(NewsClassify classify);
    void save(NewsClassify newsClassify);
}
