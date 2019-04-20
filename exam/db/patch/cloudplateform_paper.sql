-- id 自增加
-- name 试卷的名称
-- quiz_ids 试卷包含的题目id列表
-- answers 试卷的正确答案列表
-- creator 试卷的创建者
-- created_time 试卷创建时间

DROP TABLE IF EXISTS `cloudplateform_paper`;
CREATE TABLE `cloudplateform_paper` (
  `id`           INT(11)  NOT NULL       AUTO_INCREMENT,
  `name`      VARCHAR (254)  NOT NULL,
  `quiz_ids`      VARCHAR (500)  NOT NULL,
  `answers`      VARCHAR (500)  NOT NULL,
  `creator`      INT(11)  NOT NULL,
  `created_time` DATETIME NOT NULL       DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;