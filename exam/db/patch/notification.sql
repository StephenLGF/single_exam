-- id 自增加
-- user_id 用户id
-- news_id 新闻id
-- content 消息的内容
-- created_time 消息的时间

DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `id`           INT(11)      NOT NULL       AUTO_INCREMENT,
  `user_id`      INT(11)      NOT NULL,
  `news_id`      INT(11)      NOT NULL,
  `content`      VARCHAR(254) NOT NULL,
  `created_time` DATETIME     NOT NULL       DEFAULT CURRENT_TIMESTAMP,
  `deleted`      TINYINT(1)   NOT NULL       DEFAULT 0,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;