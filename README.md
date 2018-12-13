# Javaweb期末大作业_CPS校园门户网站

## 上传到github时排除了配置文件 以及 mysql数据库文件

## 数据库建表sql语句

```sql
#数据库cps_database
CREATE DATABASE `cps_database` DEFAULT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci'

# 用户表users
CREATE TABLE users(
    school_id varchar(20) PRIMARY KEY NOT NULL,
    password varchar(50) NOT NULL,
    
    pic_url varchar(100) NULL,
    
    name varchar(50) NULL,
    sex varchar(50) NULL,
    age int(10) NULL,
    id_id varchar(50) NULL,
    grade varchar(50) NULL,
    classs varchar(50) NULL,
    politics_status varchar(50) NULL,
    
    phone_number varchar(100) NULL,
    qq_number varchar(100) NULL,
    email varchar(100) NULL,
    blog varchar(100) NULL

)ENGINE=InnoDB DEFAULT CHARSET=utf8

# 首页新闻表index_news
CREATE TABLE index_news(
	nid int(10) PRIMARY KEY NOT NULL,
    img_url varchar(500) NOT NULL,
    raw_title varchar(500) NOT NULL,
    date varchar(100) NOT NULL,
    source varchar(100) NOT NULL,
    author varchar(100) NOT NULL,
    content varchar(2000) NOT NULL,
    detail_content NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8

#IC卡信息表card_infos (navicat生成)
CREATE TABLE `card_infos` (
  `cardId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `studentId` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `money` float NOT NULL,
  PRIMARY KEY (`cardId`) USING BTREE,
  UNIQUE KEY `cardId` (`studentId`),
  KEY `cardId_2` (`studentId`),
  KEY `cardId_3` (`studentId`),
  KEY `cardId_4` (`studentId`),
  KEY `cardId_5` (`cardId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#IC卡充值/消费记录表card_infos (navicat生成)
CREATE TABLE `card_infos` (
  `cardId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `studentId` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `money` float NOT NULL,
  PRIMARY KEY (`cardId`) USING BTREE,
  UNIQUE KEY `cardId` (`studentId`),
  KEY `cardId_2` (`studentId`),
  KEY `cardId_3` (`studentId`),
  KEY `cardId_4` (`studentId`),
  KEY `cardId_5` (`cardId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
```


