DROP TABLE IF EXISTS `ay_user`;
CREATE TABLE `ay_user` (
    `id` bigint(32) NOT NULL AUTO_INCREMENT,
    `name` varchar(10) DEFAULT NULL,
    `password` varchar(64) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=2;

INSERT INTO ay_user(`name`, `password`) VALUES
('diaom1','123456'),('diaomao2','454332423');
