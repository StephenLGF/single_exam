-- id 自增加
-- title 信息的题目
-- content 信息的内容
-- type 信息的类型
-- creator 信息的创建者
-- created_time 信息创建时间

DROP TABLE IF EXISTS `cloudplateform_message`;
CREATE TABLE `cloudplateform_message` (
  `id`           INT(11)      NOT NULL       AUTO_INCREMENT,
  `title`        VARCHAR(254) NOT NULL,
  `content`      TEXT         NOT NULL,
  `type`         TINYINT(1)   NOT NULL       DEFAULT 0,
  `creator`      INT(11)      NOT NULL,
  `created_time` DATETIME     NOT NULL       DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;