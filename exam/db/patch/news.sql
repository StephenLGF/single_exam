-- id 自增加
-- provider_id 新闻发布者id
-- title 新闻的标题
-- content 新闻的内容
-- type 新闻的类型 0-文字，1-图片，2-视频
-- created_time 新闻创建时间

DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id`           INT(11)      NOT NULL AUTO_INCREMENT,
  `provider_id`  INT(11)      NOT NULL,
  `title`        VARCHAR(127) NOT NULL,
  `content`      TEXT                  DEFAULT NULL,
  `type`         TINYINT(1)   NOT NULL,
  `created_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted`         TINYINT(1)   NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;