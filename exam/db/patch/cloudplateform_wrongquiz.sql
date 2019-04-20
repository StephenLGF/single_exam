-- id 自增加
-- user_id 用户id
-- quiz_id 题目id
-- wrong_answer 用户选的错误答案
-- answer 正确答案
-- created_time 题目创建时间

DROP TABLE IF EXISTS `wrongquiz`;
CREATE TABLE `wrongquiz` (
  `id`           INT(11)  NOT NULL AUTO_INCREMENT,
  `user_id`      INT(11)  NOT NULL,
  `quiz_id`      INT(11)  NOT NULL,
  `wrong_answer` VARCHAR(254)      DEFAULT NULL,
  `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;