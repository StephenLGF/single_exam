-- id 自增加
-- user_id 用户id
-- quiz_id 题目id
-- created_time 题目创建时间

DROP TABLE IF EXISTS `cloudplateform_collection`;
CREATE TABLE `cloudplateform_collection` (
  `id`           INT(11)  NOT NULL       AUTO_INCREMENT,
  `user_id`      INT(11)  NOT NULL,
  `quiz_id`      INT(11)  NOT NULL,
  `created_time` DATETIME NOT NULL       DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE user (`user_id`, `quiz_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;