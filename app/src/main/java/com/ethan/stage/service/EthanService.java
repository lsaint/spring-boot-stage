package com.ethan.stage.service;

import com.ethan.stage.dal.Version;
import org.springframework.data.domain.Page;

public interface EthanService {

    // 分页查询示例
    public Page<Version> getPageQueryVersion();
}
