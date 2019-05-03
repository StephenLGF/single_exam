-- id 自增加
-- user_id 用户id
-- news_id 新闻id
-- created_time 收藏时间
-- deleted 0正常 1删除

DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id`           INT(11)    NOT NULL       AUTO_INCREMENT,
  `user_id`      INT(11)    NOT NULL,
  `news_id`      INT(11)    NOT NULL,
  `created_time` DATETIME   NOT NULL       DEFAULT CURRENT_TIMESTAMP,
  `deleted`      TINYINT(1) NOT NULL       DEFAULT 0,
    PRIMARY KEY (`id`),
  UNIQUE user (`user_id`, `news_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;