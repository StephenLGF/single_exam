-- id 自增加
-- name 名字

DROP TABLE IF EXISTS `provider`;
CREATE TABLE `provider` (
  `id`   INT(11)      NOT NULL       AUTO_INCREMENT,
  `name` VARCHAR(254) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;