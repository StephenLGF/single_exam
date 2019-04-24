-- id 自增加
-- name 用户名
-- password 密码
-- type 用户类型 0学生 1老师
-- created_time 用户注册时间

DROP TABLE IF EXISTS `cloudplateform_user`;
CREATE TABLE `cloudplateform_user` (
  `id`            INT(11)  NOT NULL,
  `name`          CHAR (16)  NOT NULL,
  `password`      VARCHAR (254)  NOT NULL,
  `type`          INT(11)  NOT NULL DEFAULT 0,
  `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;