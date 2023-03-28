CREATE TABLE `bank_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `bank_user` (`email`, `pwd`, `role`)
 VALUES ('jak@mail.com', '54321', 'admin');