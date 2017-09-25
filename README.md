## todo-list-api

### todo-api 需求

实现 `todo-api.yml` 文件中所定义的 `api`。
文件符合 `swagger` 规范，以支持对于 `todo-item` 的增删改查工作。
可以将 `todo-api.yml` 文件在 [swagger](http://editor.swagger.io) 打开，查看具体信息。

### 使用技术

使用 `spring boot` 来搭建 `rest api`，数据库选用 `mysql`

### 其他需求

需要提供 `data migration` 脚本（数据库结构可以自行设计）。
可运行 `src/main/resources/TodoList_2017-01-23.sql` 来构建完成数据库迁移
单元测试需要达到 `100%` 的覆盖率，可以使用jacoco来生成单元测试覆盖率报告。
请使用 `git` 来做版本控制。

### 获取并执行
```bash
$ git clone https://github.com/Josaber/todo-list-api.git
$ cd todo-list-api
$ mvn spring-boot:run
```