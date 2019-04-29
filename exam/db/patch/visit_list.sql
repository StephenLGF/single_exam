-- id 自增加
-- user_id 用户id
-- news_id 新闻id
-- created_time 新闻浏览时间

DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id`           INT(11)  NOT NULL       AUTO_INCREMENT,
  `user_id`      INT(11)  NOT NULL,
  `news_id`      INT(11)  NOT NULL,
  `visited_time` DATETIME NOT NULL       DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE user (`user_id`, `news_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;