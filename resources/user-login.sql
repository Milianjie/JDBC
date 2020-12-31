drop table if exists t_user;

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   bigint auto_increment,
   loginName            varchar(255) binary,
   loginPwd             varchar(255),
   realName             varchar(255),
   primary key (id)
);
insert into t_user(loginName,loginPwd,realName) values ('xxpiaozhiyan','yanyan','朴智妍'); 
insert into t_user(loginName,loginPwd,realName) values ('admin','admin123','admin');
insert into t_user(loginName,loginPwd,realName) values ('16020520009','123456789','钟荣杰');
insert into t_user(loginName,loginPwd,realName) values ('lulujintaiyan','zxcvbnm','金泰妍');
