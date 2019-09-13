DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` bigint(32) NOT NULL AUTO_INCREMENT,
    `name` varchar(20) DEFAULT NULL,
    `account` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_name_index` (`name`) USING BTREE ,
    KEY `user_account_index` (`account`) USing BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES ('1','阿易','ay');
INSERT INTO `user` VALUES ('2','阿兰','al');

DROP TABLE IF EXISTS `mood`;
CREATE TABLE `mood` (
    `id` bigint(32) NOT NULL AUTO_INCREMENT,
    `content` varchar(256) DEFAULT NULL,
    `user_id` varchar(32) DEFAULT NULL,
    `publish_time` datetime DEFAULT NULL,
    `praise_num` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `mood_user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `mood` VALUES ('1','今天天气真好','1','2018-06-30 22:09:06', '100');
INSERT INTO `mood` VALUES ('2','厦门真美，么么哒！','2','2018-07-29 17:13:04', '99');

DROP TABLE IF EXISTS `user_mood_praise_rel`;
CREATE TABLE `user_mood_praise_rel`(
    `id` bigint(32) NOT NULL AUTO_INCREMENT,
    `user_id` varchar(32) DEFAULT NULL,
    `mood_id` varchar(32) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_mood_praise_rel_user_id_index` (`user_id`) USING BTREE,
    KEY `user_mood_praise_rel_mood_id_index` (`mood_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
