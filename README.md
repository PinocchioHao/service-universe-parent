

# Service Universe 微服务作业项目

本项目是基于 **Spring Boot + Spring Cloud** 的微服务系统示例，包含注册中心、网关、业务服务、日志与脚本等模块。
主要目的是演示 **微服务开发** 与 **流程分析日志采集** 的结合。

---

## 📂 项目结构

```
service-universe-parent/        # 父模块（统一依赖 & 管理）
│── pom.xml                     # 父模块 POM
│
├── eureka/                     # 注册中心 (Eureka Server)
├── gateway/                    # 网关服务 (Spring Cloud Gateway)
├── service-municipal-parking/  # 停车服务示例 (可参考创建其他服务)
├── service-energy-reporting/   # 能源上报服务
│
├── logs/                       # 进程日志输出目录 (parking-process.log)
│
├── scripts/                    # 脚本集合
│   ├── db-parking.sql          # 示例 SQL 建表脚本
│   ├── trans_log.py            # 日志提取/转换 Python 脚本
│
└── README.md                   # 项目说明
```

---

## 🛠️ 模块说明

* **service-universe-parent**
  父模块，管理依赖和插件，各服务模块需继承此 POM。

* **eureka**
  注册中心，采用 Spring Cloud Netflix Eureka。

  > 默认配置，无需修改。

* **gateway**
  网关，采用 Spring Cloud Gateway。

  > 默认配置即可。运行时会根据 Eureka 自动路由到服务。

* **service-municipal-parking**
  示例业务服务：停车服务。

  > 其他微服务可参考该模块进行开发。

* **service-energy-reporting**
  业务服务：能源上报服务。



* **logs**
  存放 parking 服务产生的 **process analytics** 日志文件。
  日志格式示例（AOP 输出）：

  ```json
  {"activity":"endParking","resource":"ParkingService","caseId":"ABC12333","timestamp":"2025-09-25T19:28:19.650373400"}
  ```

* **scripts**
  存放 SQL 和 Python 脚本：

  * SQL：各服务的建表语句
  * Python：提取日志并转换为 **Disco** 所需 CSV 格式

---

## 📝 日志要求

所有服务在执行业务逻辑时，需要同步记录 **流程分析日志**。
日志格式统一为 JSON：

```json
{"activity":"<活动名称>","resource":"<服务/模块>","caseId":"<唯一业务ID>","timestamp":"<时间戳>"}
```

示例：

```json
{"activity":"createParking","resource":"ParkingService","caseId":"ABC12333","timestamp":"2025-09-25T19:25:10.324931200"}
{"activity":"endParking","resource":"ParkingService","caseId":"ABC12333","timestamp":"2025-09-25T19:28:19.650373400"}
```

---

## 📊 流程分析

* 日志文件收集在 `logs/parking-process.log`。
* 使用 `scripts/extract_parking_logs.py` 将日志转换为 CSV。
* 生成的 CSV 可导入 [Disco](https://fluxicon.com/disco/) 工具进行流程挖掘和分析。

最终在 Disco 中可以得到类似如下效果：

（此处可放上示例截图）

---

## ✅ 开发约定

1. 各服务模块在继承 parent POM的基础上可以自由扩展实现自己逻辑。
2. 实现业务逻辑时，**务必记录流程日志**。
3. 日志输出与 Python 脚本的格式必须保持一致，否则无法正常导入 Disco。
4. **DTO输出格式**参考停车服务`entity/ApiResponse.java`类进行封装传给前端
5. 脚本集中放在 `scripts/` 下，避免分散在各模块。


---

## 🤝 协作方式

* 开发时基于master拉取自己的dev分支，开发完后提pr给我合并到master
* 不明白的地方直接联系我

* 业务逻辑大家可自由发挥，但要确保能产生日志。

---

