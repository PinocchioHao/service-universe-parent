
# 🚀 Service Universe 微服务系统

**Service Universe** 是一个基于 **Spring Boot + Spring Cloud + Docker Compose** 的完整微服务示例系统，展示了从服务拆分、注册发现、网关调用到流程日志收集与分析的完整实现流程。

---

## 🎯 项目目标

* 演示 **多服务独立部署与调用**
* 实现 **服务注册与发现**（Eureka）
* 提供多种业务接口（停车、能源上报、驾考预约）
* 自动记录业务流程日志，用于后续 **流程挖掘与分析**
* 通过 **Docker Compose 一键部署** 全套微服务

---

## 📂 项目结构

```
service-universe/
│── docker-compose.yml              # Docker Compose 部署配置
│── README.md
│
├── eureka/                         # 服务注册中心（Eureka Server）
├── gateway/                        # API 网关（Spring Cloud Gateway）
├── service-municipal-parking/      # 停车业务服务
├── service-energy-reporting/       # 能源上报服务
├── service-driver-booking/         # 驾考预约服务
│
├── logs/                           # 业务日志输出目录
└── scripts/                        # SQL 与 Python 工具脚本
```

---

## ⚙️ 技术栈

| 类别         | 技术                             |
| ---------- | ------------------------------ |
| **后端框架**   | Spring Boot                    |
| **微服务框架**  | Spring Cloud (Eureka, Gateway) |
| **数据库访问**  | MyBatis + MySQL                |
| **日志系统**   | Logback + 自定义 AOP 切面           |
| **部署与容器化** | Docker + Docker Compose        |
| **数据分析工具** | Python、流程挖掘工具（Disco, Celonis）  |

---

## 🧩 模块说明

| 模块                            | 说明                 |
| ----------------------------- | ------------------ |
| **Eureka**                    | 服务注册中心，所有服务启动后自动注册 |
| **Gateway**                   | API 网关，对外唯一访问入口    |
| **service-municipal-parking** | 停车业务服务             |
| **service-energy-reporting**  | 能源上报服务             |
| **service-driver-booking**    | 驾考预约服务             |


---

## 🏗 服务模块结构示例（service-municipal-parking）

```
SERVICE-MUNICIPAL-PARKING/
│
├─ pom.xml                     # Maven 构建文件
├─ mvnw / mvnw.cmd             # Maven Wrapper 启动脚本
│
├─ .mvn/wrapper/
│      └─ maven-wrapper.properties
│
├─ logs/
│      └─ parking-process.log  # 业务日志输出
│
└─ src/
    ├─ main/
    │  ├─ java/com/example/
    │  │  ├─ ServiceMunicipalParkingApplication.java   # 启动类
    │  │  ├─ aop/ProcessLogAspect.java                 # AOP 切面
    │  │  ├─ controller/ParkingController.java         # REST 控制器
    │  │  ├─ dao/ParkingRecordMapper.java              # 数据库访问层
    │  │  ├─ entity/{ApiResponse, ParkingRecord}.java  # 实体类
    │  │  ├─ service/ParkingService.java               # 业务逻辑实现
    │  │  └─ util/ProcessLogger.java                   # 日志记录工具
    │  │
    │  └─ resources/
    │      ├─ application.properties                   # Spring 配置
    │      ├─ logback-spring.xml                       # 日志配置
    │      └─ mapper/ParkingRecordMapper.xml            # MyBatis SQL 映射
    │
    └─ test/java/com/example/
           ServiceMunicipalParkingApplicationTests.java # 单元测试
```

---

## 📈 流程日志机制

每个业务服务在执行接口调用时，都会自动记录流程日志，用于 **流程分析与挖掘**。

### 日志格式

```json
{
  "activity": "<活动名称>",
  "resource": "<服务/模块>",
  "caseId": "<唯一业务ID>",
  "timestamp": "<时间戳>"
}
```

### 示例（停车服务）

```json
{"activity":"createParking","resource":"ParkingService","caseId":"ABC12333","timestamp":"2025-09-25T19:25:10.324931200"}
{"activity":"endParking","resource":"ParkingService","caseId":"ABC12333","timestamp":"2025-09-25T19:28:19.650373400"}
```

日志由 AOP 自动记录：

* `ProcessLogAspect.java` — 切面逻辑，拦截业务方法调用
* `ProcessLogger.java` — 日志输出工具，统一格式化为 JSON
* 所有日志自动输出到 `logs/` 目录中

---

## 🔧 工具脚本

| 类型            | 文件                       | 说明                        |
| ------------- | ------------------------ | ------------------------- |
| **SQL 脚本**    | `scripts/db-parking.sql` | 创建各服务数据库表结构               |
| **Python 脚本** | `scripts/trans_log.py`   | 将 JSON 日志转换为 CSV，用于流程挖掘分析 |


---

## 💡 核心设计要点

* **controller**：对外提供 REST 接口
* **service**：核心业务逻辑实现
* **dao / mapper**：数据库访问层
* **entity**：业务对象与数据模型
* **aop / ProcessLogAspect**：自动记录业务日志
* **util / ProcessLogger**：统一日志格式与输出

---

## 🌐 一键部署（Docker Compose）

### 构建所有服务

```bash
mvn clean package -DskipTests
```

### 启动所有容器

```bash
docker compose up -d
```

### 查看运行状态

```bash
docker ps
```

### 停止服务

```bash
docker compose down
```

---

## 🔹 项目特点总结

✅ **微服务架构** — 各模块独立部署
✅ **服务注册与发现** — Eureka 实现动态管理
✅ **API 网关统一访问** — Gateway 统一路由与鉴权
✅ **流程日志自动化** — AOP 统一拦截记录
✅ **日志格式标准化** — JSON 输出，便于分析
✅ **支持流程挖掘工具** — 可直接导入 Disco、Celonis

---

