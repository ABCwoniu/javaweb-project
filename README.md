简介

这个项\目是一个简单的在线题库管理系统

使用技术
Web框架：AfWeb
ORM框架：AfSql
数据源：C3P0
日志：log4j
前端框架：jquery,afquery

快速上手
	1、运行环境和所需工具
	•	编译器：MyEclipse
	•	数据库：Mysql
	•	JDK版本：jdk1.8
	•	Tomcat版本：Tomcat v8.0
	2、初始化项目
	•	在你的Mysql中导入我提供的 .sql 文件
	•	使用 MyEclipse导入项目
	•	进入src/c3p0-config.xml修改数据库登录名和密码，改为你本地的
	•	修改tomcat的server.xml文件将映射路径映射到该项目的WebRoot目录
	•	运行 ：浏览器中输入127.0.0.1:8080/(项目的映射路径)
	•	登陆账户：admin,密码：159357

功能模块介绍

​	1、登录模块功能

​		使用AfSql框架实现了单一登陆的验证和登陆信息的存储

​	2、管理员模块功能

​		管理员可对 课程信息、章节信息、习题信息 进行 增删改查 操作，以及修改密码 ，退出等

​	3、用户模块功能

​		用户可根据课程，章节查询相关习题