/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.39 : Database - wn_example
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wn_example` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `wn_example`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(40) NOT NULL COMMENT '用户名',
  `password` varchar(40) NOT NULL COMMENT '密码',
  `cellphone` varchar(20) DEFAULT NULL COMMENT '电话',
  `timeCreated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`,`cellphone`,`timeCreated`) values (1,'admin','159357',NULL,'2019-05-02 13:11:00'),(3,'superuser','159357',NULL,'2019-05-04 22:57:35');

/*Table structure for table `chapter` */

DROP TABLE IF EXISTS `chapter`;

CREATE TABLE `chapter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(40) DEFAULT NULL COMMENT '章节名称',
  `course` int(11) DEFAULT NULL COMMENT '课程id',
  `number` int(11) DEFAULT NULL COMMENT '章节序号',
  PRIMARY KEY (`id`),
  KEY `course` (`course`),
  CONSTRAINT `chapter_ibfk_1` FOREIGN KEY (`course`) REFERENCES `course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `chapter` */

insert  into `chapter`(`id`,`title`,`course`,`number`) values (11,'抽象类',67,1),(12,'接口',67,2),(13,'内部类',67,3),(14,'静态对象',67,4),(15,'异常',67,5),(26,'Hello World',66,1),(27,'变量',66,2),(28,'表达式',66,3),(29,'语句',66,4),(30,'数组',66,5);

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(40) DEFAULT NULL COMMENT '课程名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`title`) values (66,'java快速入门篇'),(67,'Java高级语法篇');

/*Table structure for table `example` */

DROP TABLE IF EXISTS `example`;

CREATE TABLE `example` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(100) DEFAULT NULL COMMENT '习题标题[reserved]',
  `creator` int(11) DEFAULT NULL COMMENT '创建者[reserved]',
  `course` int(11) DEFAULT NULL COMMENT '课程id',
  `chapter` int(11) DEFAULT NULL COMMENT '章id',
  `section` int(11) DEFAULT NULL COMMENT '节id',
  `rank` tinyint(4) DEFAULT NULL COMMENT '难度等级1-5',
  `content` varchar(8000) DEFAULT NULL COMMENT '内容',
  `hint` varchar(400) DEFAULT NULL COMMENT '提示',
  `answer` varchar(8000) DEFAULT NULL COMMENT '答案',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `priority` tinyint(4) DEFAULT NULL COMMENT '显示优先等级1-9',
  `timeCreated` datetime DEFAULT NULL COMMENT '创建时间',
  `timeModified` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `chapter` (`chapter`),
  CONSTRAINT `example_ibfk_1` FOREIGN KEY (`chapter`) REFERENCES `chapter` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `example` */

insert  into `example`(`id`,`title`,`creator`,`course`,`chapter`,`section`,`rank`,`content`,`hint`,`answer`,`status`,`priority`,`timeCreated`,`timeModified`) values (6,NULL,NULL,66,26,NULL,1,'写一个程序，输出以下内容:\n\n************************\n*** Java学习指南    **\n************************',NULL,'package my;\n\npublic class HelloWorld\n{\n	public static void main(String[] args)\n	{\n		System.out.println(\"************************\");\n		System.out.println(\"*** Java学习指南    **\");\n		System.out.println(\"************************\");\n	}\n}',0,NULL,'2019-05-05 00:15:00','2019-05-05 00:32:45'),(10,NULL,NULL,66,27,NULL,1,'有一本书，参数如下：\n\n书名：C++学习指南（语法篇）\n出版社： 清华大学出版社\n作者：邵发\n定价：49元\nISBN：9787302419891\n\n请定义合适的变量表示这几个参数',NULL,'//		书名：C++学习指南（语法篇）\n//		出版社： 清华大学出版社\n//		作者：邵发\n//		定价：49元\n//		ISBN：9787302419891\n		\n		String title = \"C++学习指南（语法篇）\";\n		String press = \"清华大学出版社\";\n		String writer = \"邵发\";\n		double price = 49.0;\n		String ISBN = \"9787302419891\"; // 不能用int表示，因为值太大了。(int的值只能表达20亿左右）',0,NULL,'2019-05-05 00:21:48','2019-05-05 00:21:48'),(11,NULL,NULL,66,28,NULL,1,'给定3个整数，求它们的乘积。\n		int a = 123;\n		int b = 23;\n		int c = 9;\n求a,b,c的乘积。并打印输出。',NULL,'int a = 123;\n		int b = 23;\n		int c = 9;\n		\n		int result = a * b * c;\n		System.out.println(\"结果为\" + result);',0,NULL,'2019-05-05 00:22:30','2019-05-05 00:22:30'),(12,NULL,NULL,66,28,NULL,1,'给定3个数a,b,c ，求它们的平方之和。\n\nint a = 123;\nint b = 23;\nint c = 9;\n求a,b,c的平方之和。并打印输出。',NULL,'int a = 123;\n		int b = 23;\n		int c = 9;\n		\n		int result = a * a + b * b + c * c;\n		System.out.println(\"结果为\" + result);',0,NULL,'2019-05-05 00:22:56','2019-05-05 00:22:56'),(13,NULL,NULL,66,28,NULL,1,'在以下表达式中，result的值是多少？\n(1) \nint  a = 10;\nint  b = 20;\nint  result = a * b;\n\n(2) \nint result = 17 % 4 ;\n\n(3) \nint result = 4 % 17;\n\n(4) \nint result = 17 % 17;\n\n(5)\nint result = 13 / 5;\n\n(6)\ndouble result = 13.0 / 5.0;',NULL,'(1) \nint  a = 10;\nint  b = 20;\nint  result = a * b;  // 200\n\n(2) \nint result = 17 % 4 ; // 1\n\n(3) \nint result = 4 % 17;  // 4\n\n(4) \nint result = 17 % 17;  // 0\n\n(5)\nint result = 13 / 5;  // 2\n\n(6)\ndouble result = 13.0 / 5.0;   // 2.6',0,NULL,'2019-05-05 00:23:39','2019-05-05 00:23:39'),(14,NULL,NULL,66,29,NULL,1,'给定一个数a, 如果为奇数则输出“为奇数\"  。如果是偶数则输出“为偶数”\n\nint  a = 123;',NULL,'int a = 123; // 给定一个值\n		if(a % 2 == 0)\n		{\n			System.out.println( a + \"为偶数\");\n		}\n		else\n		{\n			System.out.println( a + \"为奇数\");\n		}',0,NULL,'2019-05-05 00:24:20','2019-05-05 00:24:20'),(15,NULL,NULL,66,29,NULL,1,'给定一个数，判断它是否既能被3整除，又能被7整除。\n\nint  a = 123;',NULL,'int a = 123; // 给定一个值\n		if(a % 3== 0 &&  a%7==0)\n		{\n			System.out.println( \"OK\");\n		}\n		else\n		{\n			System.out.println( \"不行\");\n		}',0,NULL,'2019-05-05 00:24:44','2019-05-05 00:24:44'),(16,NULL,NULL,66,29,NULL,1,'给定一个数，判断它是否在70和80之间。\n\nint a = 123;',NULL,'int a = 123; // 给定一个值\n		if(a >= 70 &&  a<=80)\n		{\n			System.out.println( \"OK\");\n		}\n		else\n		{\n			System.out.println( \"不行\");\n		}',0,NULL,'2019-05-05 00:25:06','2019-05-05 00:25:06'),(17,NULL,NULL,66,29,NULL,1,'给定一个数，如果它在 [ 10, 20] 之间 ，或者在 [50-60] 之间，则显示 OK。 否则显示错误。',NULL,'int a = 123; // 给定一个值\n		if(a >= 10 &&  a<=20)\n		{\n			System.out.println( \"OK\");\n		}\n		else if( a >= 50 && a <= 60)\n		{\n			System.out.println( \"OK\");\n		}\n		else\n		{\n			System.out.println( \"错误\");\n		}',0,NULL,'2019-05-05 00:25:50','2019-05-05 00:25:50'),(18,NULL,NULL,66,30,NULL,1,'给定一个数组，\nint[] abc = { 29, 90, 48, 92};\n\n请遍历输出每一个数 (从头到尾顺序)',NULL,'int[] abc = { 29, 90, 48, 92};\n		\n		for ( int i=0; i<abc.length; i++)\n		{\n			int n = abc[i];\n			System.out.print( n + \" \"); // 空格分开\n		}',0,NULL,'2019-05-05 00:26:42','2019-05-05 00:26:42'),(19,NULL,NULL,66,30,NULL,1,'给定一个数组，\nint[] abc = { 20, 90, 48, 92};\n\n请倒序输出每一个数。 即，输出 92 48 90 20',NULL,'int[] abc = { 20, 90, 48, 92};\n		\n		for ( int i=abc.length - 1; i>=0; i--)  // 最后一个数的下标为 abc.length - 1\n		{\n			int n = abc[i];\n			System.out.print( n + \" \"); // 空格分开\n		}',0,NULL,'2019-05-05 00:27:08','2019-05-05 00:27:08'),(20,NULL,NULL,66,30,NULL,1,'给定一个数组，\nint[] abc = { 20, 90, 48, 92};\n\n输出其中6的倍数',NULL,'int[] abc = { 20, 90, 48, 92};\n		\n		for ( int i= 0; i <abc.length; i++)  // 最后一个数的下标为 abc.length - 1\n		{\n			int n = abc[i];\n			if ( n % 6 == 0)\n			{\n				System.out.println(\"第\" + (i+1) + \"个数: \" + n);\n			}\n		}',0,NULL,'2019-05-05 00:27:38','2019-05-05 00:27:38');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
