简介<br/>
这个项\目是一个简单的在线题库管理系统<br/>
使用技术<br/>
Web框架：AfWeb<br/>
ORM框架：AfSql<br/>
数据源：C3P0<br/>
日志：log4j<br/>
前端框架：jquery,afquery<br/>
快速上手<br/>
1、运行环境和所需工具<br/>
•	编译器：MyEclipse<br/>
•	数据库：Mysql<br/>
•	JDK版本：jdk1.8<br/>
•	Tomcat版本：Tomcat v8.0<br/>
2、初始化项目<br/>
•	在你的Mysql中导入我提供的 .sql 文件,<br/>
•	使用 MyEclipse导入项目<br/>
•	进入src/c3p0-config.xml修改数据库登录名和密码，改为你本地的<br/>
•	修改tomcat的server.xml文件将映射路径映射到该项目的WebRoot目录<br/>
•	运行 ：浏览器中输入127.0.0.1:8080/(项目的映射路径)<br/>
•	登陆账户：admin,密码：159357<br/>
功能模块介绍<br/>
1、登录模块功能<br/>
使用AfSql框架实现了单一登陆的验证和登陆信息的存储<br/>
2、管理员模块功能<br/>
管理员可对 课程信息、章节信息、习题信息 进行 增删改查 操作，以及修改密码 ，退出等<br/>
3、用户模块功能<br/>
用户可根据课程，章节查询相关习题<br/>
