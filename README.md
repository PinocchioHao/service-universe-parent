
# Service Universe 微服务项目

本项目是一个基于 **Spring Boot + Spring Cloud** 的微服务示例系统，展示了 **微服务设计、开发、日志收集及流程分析** 的完整实现。

项目主要目标：

* 演示多服务独立部署与调用
* 实现服务间注册与发现
* 提供业务服务接口（停车、能源上报、交通监控等）
* 自动记录流程分析日志，便于后续流程挖掘与分析

---

## 📂 项目结构

```
service-universe-parent/        # 父模块，统一依赖和管理
│── pom.xml
│
├── eureka/                     # 注册中心（Eureka Server）
├── gateway/                    # API 网关（Spring Cloud Gateway）
├── service-municipal-parking/  # 停车业务服务示例
├── service-energy-reporting/   # 能源上报服务
├── service-traffic-monitoring/ # 交通监控服务示例
│
├── logs/                       # 业务服务流程日志输出
├── scripts/                    # SQL & Python 脚本（建表 & 日志转换）
└── README.md
```

---

## 🛠 技术栈

* **Spring Boot**：微服务快速开发
* **Spring Cloud**：

  * **Eureka**：服务注册与发现
  * **Spring Cloud Gateway**：统一路由入口
* **MyBatis / MySQL**：数据持久化
* **AOP 切面**：业务流程日志自动记录
* **Python 脚本**：日志提取与格式转换
* **流程分析工具**（如 Disco）支持对生成的日志进行流程挖掘和可视化分析

---

## 🔹 服务模块说明

### 1. Eureka 注册中心

* 提供服务注册与发现功能
* 各业务服务启动时自动注册，网关通过 Eureka 路由请求到具体服务

### 2. Gateway 网关

* 接受外部请求并转发到后端微服务
* 自动从 Eureka 获取服务列表，无需硬编码路由
* 可统一处理跨域、限流、日志等功能

### 3. 业务服务示例

| 服务模块                       | 功能描述                   |
| -------------------------- | ---------------------- |
| service-municipal-parking  | 停车管理：开始停车、结束停车、支付、历史查询 |
| service-energy-reporting   | 能源上报及统计                |
| service-traffic-monitoring | 交通监控与数据收集              |

---

## 📈 流程日志

每个业务服务在执行接口调用时会自动记录流程日志，用于 **流程分析与挖掘**。

### 日志格式

```json
{"activity":"<活动名称>","resource":"<服务/模块>","caseId":"<唯一业务ID>","timestamp":"<时间戳>"}
```

### 停车服务示例

```json
{"activity":"createParking","resource":"ParkingService","caseId":"ABC12333","timestamp":"2025-09-25T19:25:10.324931200"}
{"activity":"endParking","resource":"ParkingService","caseId":"ABC12333","timestamp":"2025-09-25T19:28:19.650373400"}
```

* 日志自动通过 **AOP 切面**（ProcessLogAspect.java）记录
* ProcessLogger.java 提供统一接口，支持 JSON 输出
* 日志可集中存放在 `logs/` 目录

---

## 🔧 脚本工具

* **SQL 脚本**：创建各服务数据库表，例如 `db-parking.sql`
* **Python 脚本**：提取日志并转换为 CSV，方便导入流程分析工具

  * 示例：`scripts/trans_log.py` 将 JSON 日志转换为流程分析工具所需格式

---

## 🏗 服务模块目录示例（service-municipal-parking）

```
SERVICE-MUNICIPAL-PARKING/
│
├─pom.xml                     # Maven 构建文件，定义依赖与插件
├─mvnw                       # Maven Wrapper 启动脚本（Linux/Mac）
├─mvnw.cmd                   # Maven Wrapper 启动脚本（Windows）
│
├─.mvn/wrapper/
│      └─maven-wrapper.properties  # Maven Wrapper 配置
│
├─logs/
│      parking-process.log     # 停车服务生成的流程日志
│
└─src/
    ├─main/
    │  ├─java/com/example/
    │  │  ├─ServiceMunicipalParkingApplication.java
    │  │  │      # 应用入口类，Spring Boot 启动类
    │  │  ├─aop/ProcessLogAspect.java
    │  │  │      # AOP 切面，用于自动记录业务流程日志
    │  │  ├─controller/ParkingController.java
    │  │  │      # REST 控制器，提供停车业务接口（start、end、pay、history）
    │  │  ├─dao/ParkingRecordMapper.java
    │  │  │      # MyBatis Mapper，访问停车记录数据库表
    │  │  ├─entity/ApiResponse.java
    │  │  │      # API 响应封装类，统一返回结构
    │  │  ├─entity/ParkingRecord.java
    │  │  │      # 停车记录实体类，对应数据库表字段
    │  │  ├─service/ParkingService.java
    │  │  │      # 停车业务逻辑实现类
    │  │  └─util/ProcessLogger.java
    │  │         # 日志工具类，提供统一接口记录 JSON 流程日志
    │  │
    │  └─resources/
    │      ├─application.properties
    │      │      # Spring Boot 配置文件（数据库、端口、日志等）
    │      ├─logback-spring.xml
    │      │      # 日志框架配置文件（控制日志输出格式和路径）
    │      └─mapper/ParkingRecordMapper.xml
    │             # MyBatis 映射文件，定义 SQL 语句与 ParkingRecord 实体映射
    │
    └─test/java/com/example/main/
            ServiceMunicipalParkingApplicationTests.java
                    # 测试类，用于单元测试应用启动及业务功能

```

* **核心职责**

  * `controller`：REST 接口，支持业务操作
  * `service`：业务逻辑实现
  * `dao/mapper`：数据库访问层
  * `entity`：业务对象实体
  * `aop/ProcessLogAspect.java`：切面记录日志
  * `util/ProcessLogger.java`：日志输出工具

---

## 🔹 项目特点

1. **微服务架构**

  * 独立部署
  * 服务注册与发现
  * API 网关统一访问

2. **流程日志自动化**

  * 接口调用全程记录
  * JSON 格式统一
  * 支持日志收集和后续流程分析

3. **流程分析支持**

  * 日志可转换为 CSV 格式
  * 可导入流程挖掘工具进行可视化分析

---