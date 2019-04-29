-- id 自增加
-- user_id 用户id
-- news_id 新闻id
-- created_time 新闻浏览时间

DROP TABLE IF EXISTS `visit_list`;
CREATE TABLE `visit_list` (
  `id`           INT(11)  NOT NULL       AUTO_INCREMENT,
  `user_id`      INT(11)  NOT NULL,
  `news_id`      INT(11)  NOT NULL,
  `visited_time` DATETIME NOT NULL       DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;