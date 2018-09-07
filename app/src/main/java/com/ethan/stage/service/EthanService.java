package com.ethan.stage.service;

public interface EthanService {

    void invokeCommon();

    void readConfig();

    void queryMySql();

    // 分页查询示例
    void getPageQueryVersion();

    // rest get 调用示例
    String getUrl();

    /*   rest post 调用示例
     *   curl -X POST https://jsonplaceholder.typicode.com/posts -d '{"title": "t", "body": "b", "userId": 1}' -H "Content-Type: application/json"
     *   {
     *     "title": "t",
     *     "body": "b",
     *     "userId": 1,
     *     "id": 101
     *   }
     */
    String postUrl();
}
