## todo-list-api

### 需求

实现 `todo-api.yml` 文件中所定义的 `api`。
文件符合 `swagger` 规范，以支持对于 `todo-item` 的增删改查工作。
可以将 `todo-api.yml` 文件在 [swagger](http://editor.swagger.io) 打开，查看具体信息。

### 使用技术

使用 `spring boot` 来搭建 `rest api`，数据库选用 `mysql`

### 其他需求

* 需要提供 `data migration` 脚本（数据库结构可以自行设计）。
* 可运行 `src/main/resources/TodoList_2017-01-23.sql` 来构建完成数据库迁移。
* 可以使用jacoco来生成单元测试覆盖率报告。
* 请使用 `git` 来做版本控制。

### 获取并执行
```bash
$ git clone https://github.com/Josaber/todo-list-api.git
$ cd todo-list-api
$ mvn spring-boot:run
```

### 结果

GET

```
$ curl -X GET "http://localhost:8080/todoitems" -H "accept: application/json"
...
{"items":[{"id":1,"text":"finish todo list","done":false,"timestamp":"2011-01-21T11:33:21Z"},{"id":2,"text":"finish the homework","done":true,"timestamp":"2011-01-21T11:33:21Z"},{"id":3,"text":"This is for test create.","done":true,"timestamp":"2017-01-20T16:23:05Z"}]}%
```

POST

```
$ curl -X POST "http://localhost:8080/todoitems" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"text\": \"string\"}"
...
{"status":201,"message":"Create Todo Item Successfully!","todoItem":{"id":4,"text":"string","done":false,"timestamp":"2017-09-25T21:46:25Z"}}%
```

PUT

```
$ curl -X PUT "http://localhost:8080/todoitems/1" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"text\": \"string\", \"done\": true}"
...
{"status":200,"message":"Update Todo Item Successfully!","todoItem":{"id":1,"text":"string","done":true,"timestamp":"2017-09-25T21:52:34Z"}}%

$ curl -X PUT "http://localhost:8080/todoitems/5" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"text\": \"string\", \"done\": true}"
...
{"status":404,"message":"The Todo Item Is Not Found!","todoItem":null}%
```

DELETE

```
$ curl -X DELETE "http://localhost:8080/todoitems/1" -H "accept: application/json"

$ curl -X DELETE "http://localhost:8080/todoitems/100" -H "accept: application/json"
...
{"status":404,"message":"Fail to Delete Todo Item!","todoItem":null}%
```