## 项目准则
* NO XML
* yml instead properties

## 分层规范

* controller 层: 主要是对访问控制进行转发，各类基本参数校验，或者不复用的业务简单处理等。
* service 层: 相对具体的业务逻辑服务层。
* manager 层: 通用业务处理层，它有如下特征:
    * 对第三方平台封装的层，预处理返回结果及转化异常信息;
    * 对Service层通用能力的下沉，如缓存方案、中间件通用处理; 
    * 与DAO层交互，对多个DAO的组合复用。
* dal 层: 数据访问层，与底层 MySQL、Oracle、Hbase 等进行数据交互。


## 领域对象规范

* DO(Data Object): 与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。
* DTO(Data Transfer Object): 数据传输对象，DO的一个子集，Service 或 Manager 向外传输的对象。
* BO(Business Object): 业务对象。由 Service 层输出的封装业务逻辑的对象。
* AO(ApplicationObject): 应用对象。在Web层与Service层之间抽象的复用对象模型， 极为贴近展示层，复用度不高。
* VO(View oBJect): 显示层对象，通常是 Web 向模板渲染引擎层传输的对象。
* Query: 数据查询对象，各层接收上层的查询请求。注意超过 2 个参数的查询封装，禁止使用 Map 类来传输。


## 异常处理规范

* dal层: 使用catch(Exception e)方式，并throw new DAOException(e)，不需要打印日志.
* service层: 必须记录出错日志到，尽可能带上参数信息保护案发现场.
* controller层: 不再抛异常，要将异常处理成错误码和错误信息方式返回.


## service 要不要使用定义接口？

* 先不用，等到某个时候某个功能需要的时候再重构。
