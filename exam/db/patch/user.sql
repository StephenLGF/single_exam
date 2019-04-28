-- id 自增加
-- id 手机号
-- password 密码
-- name 用户名
-- created_time 用户注册时间

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`           INT(11)      NOT NULL AUTO_INCREMENT,
  `tel`          BIGINT(11)   NOT NULL,
  `password`     VARCHAR(254) NOT NULL,
  `name`         CHAR(16)     NOT NULL,
  `created_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;