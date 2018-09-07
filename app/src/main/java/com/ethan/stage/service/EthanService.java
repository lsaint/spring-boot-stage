package com.ethan.stage.service;

import com.ethan.stage.dal.Version;
import org.springframework.data.domain.Page;

public interface EthanService {

    // 分页查询示例
    public Page<Version> getPageQueryVersion();

    // rest get 调用示例
    public String getUrl();

    /*   rest post 调用示例
     *   curl -X POST https://jsonplaceholder.typicode.com/posts -d '{"title": "t", "body": "b", "userId": 1}' -H "Content-Type: application/json"
     *   {
     *     "title": "t",
     *     "body": "b",
     *     "userId": 1,
     *     "id": 101
     *   }
     */
    public String postUrl();
}
