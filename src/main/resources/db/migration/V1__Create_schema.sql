
CREATE TABLE `category` (
  `id` char(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `book` (
  `id` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `isbn` varchar(21) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `category_id` char(36) NOT NULL,
  `quantity` int(11) DEFAULT '0',
  `price` double DEFAULT '0',
  `description` text,
  `publisher` varchar(255) DEFAULT NULL,
  `published_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn_UNIQUE` (`isbn`),
  KEY `BOOK_CATEGORY_ID_FK1_idx` (`category_id`),
  CONSTRAINT `CATEGORY_ID_FK1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `customer` (
  `id` char(36) NOT NULL,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `created_at` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `cart` (
  `id` char(36) NOT NULL,
  `customer_id` char(36) NOT NULL,
  `total_price` double DEFAULT '0',
  `status` varchar(255) DEFAULT NULL,
  `created_at` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `CUSTOMER_ID_FK1_idx` (`customer_id`),
  CONSTRAINT `CUSTOMER_ID_FK_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `cart_detail` (
  `id` char(36) NOT NULL,
  `cart_id` char(36) DEFAULT NULL,
  `book_id` char(36) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `BOOK_ID_FK_2_idx` (`book_id`),
  KEY `CARD_ID_FK_1_idx` (`cart_id`),
  CONSTRAINT `BOOK_ID_FK_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CART_ID_FK_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

