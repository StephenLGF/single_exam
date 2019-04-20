
-- user_id 考生的id
-- paper_id 试卷的id
-- state 考试的状态 0：未开始， 1：进行中， 2：考试完成
-- re_answers 考生在考试中的作答情况
-- result 成绩
-- start_time 考试开始时间
-- end_time 考试结束时间

DROP TABLE IF EXISTS `cloudplateform_exam`;
CREATE TABLE `cloudplateform_exam` (
  `id`         INT(11)    NOT NULL       AUTO_INCREMENT,
  `user_id`    INT(11)    NOT NULL,
  `paper_id`   INT(11)    NOT NULL,
  `state`      TINYINT(1) NOT NULL       DEFAULT 0,
  `re_answers` VARCHAR(500)              DEFAULT NULL,
  `result`     DECIMAL(5, 2)             DEFAULT NULL,
  `start_time` DATETIME                  DEFAULT NULL,
  `end_time`   DATETIME                  DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE user (`user_id`, `paper_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;