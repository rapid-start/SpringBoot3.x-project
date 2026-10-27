# Spring Boot 3.x 原型

基于 **IntelliJ IDEA + JDK 21 + Maven + Spring Boot 3.x + JUnit 5** 的最小可运行原型项目。

## 技术栈

| 组件 | 版本 |
|------|------|
| JDK | 21 (LTS) |
| Maven | 3.9.x（项目亦可用 Maven Wrapper） |
| Spring Boot | 3.5.0 |
| JUnit | 5（Jupiter） |

## 目录结构

```
examples
├── pom.xml
└── src
    ├── main
    │   ├── java/io/github/zxl20070701/examples
    │   │   ├── ExamplesApplication.java  # 启动类
    │   │   ├── controller/HelloController.java  # Web 层
    │   │   ├── service/GreetingService.java     # 业务层
    │   │   └── dto/GreetingResponse.java        # 响应 DTO
    │   └── resources/application.yml
    └── test/java/io/github/zxl20070701/examples
        ├── ExamplesApplicationTests.java # 集成测试（加载完整上下文）
        ├── controller/HelloControllerTest.java # 切片测试（MockMvc）
        └── service/GreetingServiceTest.java    # 纯单元测试
```

## 用 IntelliJ IDEA 打开

1. **File → Open**，选择 `examples` 目录（IDEA 会识别为 Maven 项目并自动导入依赖）。
2. **Project Structure (⌘;) → Project SDK** 选择 **JDK 21**（若列表没有，点击 *Add SDK → Download JDK* 或选择本机 JDK 21）。
3. 首次导入时等待 Maven 下载依赖（右下角进度条结束）。

## 运行

方式一（IDEA）：运行 `ExamplesApplication` 的 `main` 方法。

方式二（命令行）：
```bash
# 确认 JAVA_HOME 指向 JDK 21
export JAVA_HOME="$(/usr/libexec/java_home -v 21)"
mvn spring-boot:run
```

启动后访问：

```
GET http://localhost:8080/api/hello?name=zxl20070701
```

响应示例：
```json
{ "message": "Hello, zxl20070701!", "serverTime": "2026-10-26T10:30:00" }
```

## MySQL 操作示例

示例使用 `JdbcTemplate` 演示商品的增删改查。先创建数据库：

```sql
CREATE DATABASE examples CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

配置数据库连接（也可通过 `MYSQL_HOST`、`MYSQL_PORT`、`MYSQL_DATABASE`、`MYSQL_USERNAME` 和 `MYSQL_PASSWORD` 环境变量覆盖）：

```bash
export MYSQL_HOST=localhost
export MYSQL_PORT=3306
export MYSQL_DATABASE=examples
export MYSQL_USERNAME=root
export MYSQL_PASSWORD=your-password
mvn spring-boot:run
```

应用启动时会执行 `schema.sql` 创建 `product` 表。接口示例：

```bash
# 新增
curl -X POST http://localhost:8080/api/products \
  -H 'Content-Type: application/json' \
  -d '{"name":"键盘","price":299.00}'

# 查询列表、查询单条
curl http://localhost:8080/api/products
curl http://localhost:8080/api/products/1

# 修改、删除
curl -X PUT http://localhost:8080/api/products/1 \
  -H 'Content-Type: application/json' \
  -d '{"name":"机械键盘","price":399.00}'
curl -X DELETE http://localhost:8080/api/products/1
```

测试使用 H2 内存数据库，不需要启动 MySQL 服务。

## 运行测试

```bash
mvn test
```

包含以下测试，分别演示不同的测试写法：
- `GreetingServiceTest`：纯单元测试，不启动 Spring，最快。
- `HelloControllerTest`：`@WebMvcTest` 切片测试，用 MockMvc 测接口。
- `ExamplesApplicationTests`：`@SpringBootTest` 集成测试，加载完整上下文。
- `ProductServiceIntegrationTest`：使用 H2 内存数据库验证 MySQL CRUD 操作。

## 打包

```bash
mvn clean package
java -jar target/examples-0.0.1-SNAPSHOT.jar
```

## 版权

MIT License

Copyright (c) [zxl20070701](https://zxl20070701.github.io/notebook/home.html) 走一步，再走一步