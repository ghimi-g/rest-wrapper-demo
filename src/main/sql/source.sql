-- 用户表
drop table if exists tb_user;
CREATE TABLE tb_user(
	id bigint(11) not null auto_increment,
	name varchar(255) COMMENT '用户名称',
	email varchar(64) COMMENT '用户邮箱',
	phone varchar(32) COMMENT '用户手机号',
	status varchar(32) COMMENT '用户状态',
	password varchar(256) COMMENT '用户密码',
	create_time datetime default current_timestamp,
	update_time datetime default current_timestamp on update current_timestamp,
	primary key(id)
)engine=InnoDB charset=utf8;
drop table if exists tb_role;
CREATE TABLE tb_role(
	id bigint(11) not null auto_increment,
	name varchar(255),
	status varchar(32),
	create_time datetime default current_timestamp,
	update_time datetime default current_timestamp on update current_timestamp,
	primary key(id)
);
drop table if exists tb_user_role;
CREATE TABLE tb_user_role(
	id bigint(11) not null auto_increment,
	user_id bigint(11),
	role_id bigint(11),
	create_time datetime default current_timestamp,
	primary key(id)
);
drop table if exists tb_access;
CREATE TABLE tb_access(
	id bigint(11) not null auto_increment,
	name varchar(255),
	authority varchar(1024),
	status varchar(32),
	create_time datetime default current_timestamp,
	update_time datetime default current_timestamp on update current_timestamp,
	primary key(id)
);

drop table if exists tb_role_access;
CREATE TABLE tb_role_access(
	id bigint(11) not null auto_increment,
	role_id bigint(11),
	access_id bigint(11),
	create_time datetime default current_timestamp,
	primary key(id)
);
insert into tb_user(name,email,phone,password) values('admin','admin@ghimi.top','12345678','{noop}123456');