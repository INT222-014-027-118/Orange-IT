CREATE USER 'adminUser'@'%' IDENTIFIED BY 'password';

GRANT ALL ON *.* TO 'adminUser'@'%';

SET FOREIGN_KEY_CHECKS=0;
SET GLOBAL FOREIGN_KEY_CHECKS=0;

CREATE TABLE IF NOT EXISTS `categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(40) NOT NULL,
  `parent_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `category_UNIQUE` (`category` ASC) ,
  INDEX `fk_Categories_Categories1_idx` (`parent_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Categories_Categories1`
    FOREIGN KEY (`parent_id`)
    REFERENCES `categories` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `colors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(30) NOT NULL,
  `hex_code` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `roles` (
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `firstname` VARCHAR(50) ,
  `lastname` VARCHAR(50) ,
  `email` VARCHAR(50) ,
  `points` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `delivery_details` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(50) NOT NULL,
  `lastname` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  `province` VARCHAR(50) NOT NULL,
  `district` VARCHAR(50) NOT NULL,
  `sub_district` VARCHAR(50) NOT NULL,
  `postal_code` VARCHAR(5) NOT NULL,
  `address` VARCHAR(150) NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_delivery_details_users1_idx` (`users_id` ASC) ,
  CONSTRAINT `fk_delivery_details_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `users` (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `shippings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(30) NOT NULL,
  `company_shipping` VARCHAR(60) NOT NULL,
  `tracking_number` VARCHAR(13) NULL,
  `delivery_detail_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Shippings_Delivery_details1_idx` (`delivery_detail_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Shippings_Delivery_details1`
    FOREIGN KEY (`delivery_detail_id`)
    REFERENCES `delivery_details` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `payments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(40) NOT NULL,
  `status` VARCHAR(40) NOT NULL,
  `card_number` VARCHAR(16) NULL DEFAULT NULL,
  `security_code` VARCHAR(3) NULL DEFAULT NULL,
  `expired_card` VARCHAR(5) NULL DEFAULT NULL,
  `provider` VARCHAR(40) NULL DEFAULT NULL,
  `transaction_date` DATETIME NULL DEFAULT NULL,
  `slip_image` VARCHAR(50) NULL DEFAULT NULL,
  `paid_date` DATETIME NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `slip_image_UNIQUE` (`slip_image` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_payments_users1_idx` (`users_id` ASC) ,
  CONSTRAINT `fk_payments_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `users` (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `discounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `discount_percent` FLOAT NOT NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `orders` (
  `id` INT NOT NULL,
  `status` VARCHAR(30) NOT NULL,
  `order_date` DATETIME NOT NULL,
  `shipping_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Order_Shippings1_idx` (`shipping_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_orders_users1_idx` (`users_id` ASC) ,
  CONSTRAINT `fk_Order_Shippings1`
    FOREIGN KEY (`shipping_id`)
    REFERENCES `shippings` (`id`),
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


CREATE TABLE IF NOT EXISTS `products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(1000) NOT NULL,
  `price` FLOAT NOT NULL,
  `brand_name` VARCHAR(60) NOT NULL,
  `quantity_stock` INT NOT NULL,
  `active` TINYINT NOT NULL,
  `discount_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Products_Discounts1_idx` (`discount_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Products_Discounts1`
    FOREIGN KEY (`discount_id`)
    REFERENCES `discounts` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `images` (
  `id` INT NOT NULL,
  `source` VARCHAR(50) NOT NULL,
  `label` VARCHAR(30) NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Images_Products1_idx` (`product_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Images_Products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `order_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL,
  `price` FLOAT NOT NULL,
  `color_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Order_items_Orders1_idx` (`order_id` ASC) ,
  INDEX `fk_Order_items_Products1_idx` (`product_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Order_items_Orders1`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`id`),
  CONSTRAINT `fk_Order_items_Products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `user_roles` (
  `role_name` VARCHAR(50) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`role_name`, `user_id`),
  INDEX `fk_roles_has_users_users1_idx` (`user_id` ASC) ,
  INDEX `fk_roles_has_users_roles1_idx` (`role_name` ASC) ,
  CONSTRAINT `fk_roles_has_users_roles1`
    FOREIGN KEY (`role_name`)
    REFERENCES `roles` (`name`),
  CONSTRAINT `fk_roles_has_users_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `products_has_categories` (
  `product_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `category_id`),
  INDEX `fk_Products_has_Categories_Categories1_idx` (`category_id` ASC) ,
  INDEX `fk_Products_has_Categories_Products1_idx` (`product_id` ASC) ,
  CONSTRAINT `fk_Products_has_Categories_Categories1`
    FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`),
  CONSTRAINT `fk_Products_has_Categories_Products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `products_has_colors` (
  `product_id` INT NOT NULL,
  `color_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `color_id`),
  INDEX `fk_Products_has_Colors_Colors1_idx` (`color_id` ASC) ,
  INDEX `fk_Products_has_Colors_Products1_idx` (`product_id` ASC) ,
  CONSTRAINT `fk_Products_has_Colors_Colors1`
    FOREIGN KEY (`color_id`)
    REFERENCES `colors` (`id`),
  CONSTRAINT `fk_Products_has_Colors_Products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `attributes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `attribute` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `products_has_attributes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `attribute_id` INT NOT NULL,
  `attribute_value` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_products_has_attributes_attributes1_idx` (`attribute_id` ASC) ,
  INDEX `fk_products_has_attributes_products1_idx` (`product_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_products_has_attributes_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`id`) ,
  CONSTRAINT `fk_products_has_attributes_attributes1`
    FOREIGN KEY (`attribute_id`)
    REFERENCES `attributes` (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `reviews` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `star` INT NULL DEFAULT NULL,
  `comment` VARCHAR(100) NULL DEFAULT NULL,
  `review_date` DATETIME NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_reviews_users1_idx` (`users_id` ASC) ,
  CONSTRAINT `fk_reviews_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `users` (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `ratings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `rating_of_product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Reviews_id` INT NOT NULL,
  `Ratings_id` INT NOT NULL,
  `score` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Reviews_has_Ratings_Ratings1_idx` (`Ratings_id` ASC) ,
  INDEX `fk_Reviews_has_Ratings_Reviews1_idx` (`Reviews_id` ASC) ,
  INDEX `fk_Rating_of_product_Products1_idx` (`product_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Rating_of_product_Products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`id`),
  CONSTRAINT `fk_Reviews_has_Ratings_Ratings1`
    FOREIGN KEY (`Ratings_id`)
    REFERENCES `ratings` (`id`),
  CONSTRAINT `fk_Reviews_has_Ratings_Reviews1`
    FOREIGN KEY (`Reviews_id`)
    REFERENCES `reviews` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `cart_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL DEFAULT 0,
  `color_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_Cart_items_products1_idx` (`product_id` ASC) ,
  INDEX `fk_cart_items_users1_idx` (`users_id` ASC) ,
  CONSTRAINT `fk_Cart_items_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`id`) ,
  CONSTRAINT `fk_cart_items_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `users` (`id`) )
ENGINE = InnoDB;

INSERT INTO `colors` (`id`, `label`, `hex_code`) VALUES 
  ('1', 'Red', 'ff0000'), ('2', 'Black', '000000'),
  ('3', 'White', 'FFFFFF'),('4', 'Lavender', 'E6E6FA'),
  ('5', 'Pink', 'E5A3CF'), ('6', 'Gray', '9AA0A8'),
  ('7', 'Orange', 'FF7400'), ('8', 'Yellow', 'FFC100');

INSERT INTO `categories` (`id`, `category`, `parent_id`) VALUES 
  ('1', 'Keyboard', NULL),('2', 'Mouse', NULL), ('3', 'Headset', NULL), 
  ('4', 'Wireless keyboard', '1'),('5', 'Wireless mouse', '2'),('6', 'Wireless headset', '3');

INSERT INTO `products` (`id`, `product_name`, `description`, `price`, `brand_name`, `quantity_stock`,`active`, `discount_id`) VALUES
  ('1', 'LOGITECH G G913 LIGHTSPEED WIRELESS RGB', '', '5990', 'Logitech', '55',1, NULL),
  ('2', 'RAZER HUNTSMAN ELITE', '- Razer™ Opto-Mechanical 
  Switch with 45 G actuation force\r\n- 100 million keystroke lifespan\r\n- Chroma backlighting
  with 16.8 million customizable color options', '5990', 'Razer', '40',1, NULL),
  ('3', 'RAZER DEATHADDER V2', '- True 20,000 DPI Focus+ optical sensor\r\n- Up to 650 inches per second
  (IPS) / 50 G acceleration / industry best 99.6% resolution accuracy', '1990', 'Razer', '20',1, NULL),
  ('4', 'RAZER BLACKSHARK V2 PRO', 'Frequency Response : 12 Hz – 28 kHz\r\nImpedance : 32 Ω (1 kHz)', '6490', 'Razer', '50',1, NULL);

INSERT INTO `products_has_colors` (`product_id`, `color_id`) VALUES ('1', '2'),('1', '3'), ('2', '2'),('2', '3'), ('3', '2'), ('4','2');

INSERT INTO `products_has_categories` (`product_id`, `category_id`) VALUES ('1', '1'),('1', '4'), ('2','1'),('3','2'),('4','3'),('4','6');

INSERT INTO `attributes` (`id`, `attribute`) VALUES ('1', 'Wired/Wireless'), ('2', 'Keyboard type'), ('3', 'Keyboard size'), ('4', 'Switch'), ('5', 'Sound');

INSERT INTO `products_has_attributes` (`product_id`, `attribute_id`, `attribute_value`) VALUES 
  ('1', '1', 'Wireless'), ('1', '2', 'Mechanical'), ('1', '3', 'Full-Size'), ('1', '4', 'Logitech GL'), ('1', '5', 'Clicky, Silent'),
  ('2', '1', 'Wired'), ('2', '2', 'Mecha-Optical'), ('2', '3', 'Full-Size'), ('2', '4', 'Razer'), ('2', '5', 'Clicky'),
  ('3', '1', 'Wired'), ('4', '1', 'Wireless');

INSERT INTO `images` (`id`, `source`, `label`, `product_id`) VALUES 
  ('1', 'G913-1.png', 'G913-1', '1'), 
  ('2', 'G913-2.png', 'G913-2', '1'), 
  ('3', 'G913-3.png', 'G913-3', '1'), 
  ('4', 'G913-4.png', 'G913-4', '1'), 
  ('5', 'hmel-1-1.png', 'hmel-1-1', '2'), 
  ('6', 'hmel-1-2.png', 'hmel-1-2', '2'), 
  ('7', 'hmel-1-3.png', 'hmel-1-3', '2'), 
  ('8', 'hmel-1-4.png', 'hmel-1-4', '2'), 
  ('9', 'DeathAdder-V2-1.png', 'DeathAdder-V2-1', '3'),  
  ('10', 'DeathAdder-V2-2.png', 'DeathAdder-V2-2', '3'),  
  ('11', 'DeathAdder-V2-3.png', 'DeathAdder-V2-3', '3'),  
  ('12', 'DeathAdder-V2-4.png', 'DeathAdder-V2-4', '3'),  
  ('13', 'DeathAdder-V2-5.png', 'DeathAdder-V2-5', '3'), 
  ('14', 'BlackSharkV2Pro1.png', 'BlackSharkV2Pro1', '4'), 
  ('15', 'BlackSharkV2Pro2.png', 'BlackSharkV2Pro2', '4'),
  ('16', 'BlackSharkV2Pro3.jpg', 'BlackSharkV2Pro2', '4'),
  ('17', 'BlackSharkV2Pro4.jpg', 'BlackSharkV2Pro2', '4'),
  ('18', 'BlackSharkV2Pro5.jpg', 'BlackSharkV2Pro2', '4');
  
INSERT INTO `roles` (`name`, `description`) VALUES ('Admin', '');
INSERT INTO `roles` (`name`, `description`) VALUES ('User', '');

INSERT INTO `ratings` (`id`,`name`) VALUES ('1', 'Design');
INSERT INTO `ratings` (`id`,`name`) VALUES ('2', 'Material');
INSERT INTO `ratings` (`id`,`name`) VALUES ('3', 'Good value');
INSERT INTO `ratings` (`id`,`name`) VALUES ('4', 'Ease to use');

