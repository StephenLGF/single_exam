-- id 自增加
-- question 题目
-- selection 选项
-- answer 答案
-- chapterId 章节*
-- level 难度系数*
-- share 共享程度 0 所有人可见，1 同校可见， 2 同班可见, 3 限制可见*
-- creator 题目创建者
-- created_time 题目创建时间
-- 星号代码字段当前未使用

DROP TABLE IF EXISTS `quiz`;
CREATE TABLE `quiz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(254) DEFAULT NULL,
  `selection` varchar(254) DEFAULT NULL,
  `answer` varchar(254) DEFAULT NULL,
  `chapterId` int(11) DEFAULT NULL,
  `level` tinyint(1) DEFAULT NULL,
  `share` tinyint(1) DEFAULT 0,
  `creator` int(11) NOT NULL,
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;