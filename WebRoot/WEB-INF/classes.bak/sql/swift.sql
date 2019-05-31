/*==============================================================*/
/* 数据库脚本                        */
/*==============================================================*/
drop database if exists swift;
create database swift character set utf8;
use swift;
	
drop table if exists t_user;
create table t_user
(
  id          int not null AUTO_INCREMENT,
  username    varchar(255) not null,
  realname    varchar(255) not null,
  email       varchar(255) not null,
  password    varchar(255) not null,
  joindate    varchar(255) default null,
  lastdate    varchar(255) default null,
  capacity    int default 1024,
  primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into t_user VALUES ('1', 'demo', 'demo', 'demo@163.com', 'e10adc3949ba59abbe56e057f20f883e', '2014-12-30 11:26:23', '2014-12-30 11:26:23', '0');