DROP DATABASE IF EXISTS `TodoList`;

USE TodoList;

CREATE TABLE `todo_item` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `text` varchar(140) DEFAULT NULL,
  `done` tinyint(1) DEFAULT NULL,
  `timestamp` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `todo_item` WRITE;
/*!40000 ALTER TABLE `todo_item` DISABLE KEYS */;

INSERT INTO `todo_item` (`id`, `text`, `done`, `timestamp`)
VALUES
	(1,'finish todo list',0,'2011-01-21T11:33:21Z'),
	(2,'finish the homework',1,'2011-01-21T11:33:21Z'),
	(3,'This is for test create.',1,'2017-01-20T16:23:05Z');

/*!40000 ALTER TABLE `todo_item` ENABLE KEYS */;
UNLOCK TABLES;