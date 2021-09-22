CREATE USER 'adminUser'@'%' IDENTIFIED BY 'password';

GRANT ALL ON *.* TO 'adminUser'@'%';

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

CREATE TABLE IF NOT EXISTS `customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `firstname` VARCHAR(50) NOT NULL,
  `lastname` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
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
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`, `customer_id`),
  INDEX `fk_Delivery_details_Customers_idx` (`customer_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Delivery_details_Customers`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `shippings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(30) NOT NULL,
  `company_shipping` VARCHAR(60) NOT NULL,
  `tracking_number` VARCHAR(13) NOT NULL,
  `delivery_detail_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`, `delivery_detail_id`, `customer_id`),
  UNIQUE INDEX `tracking_number_UNIQUE` (`tracking_number` ASC) ,
  INDEX `fk_Shippings_Delivery_details1_idx` (`delivery_detail_id` ASC, `customer_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Shippings_Delivery_details1`
    FOREIGN KEY (`delivery_detail_id` , `customer_id`)
    REFERENCES `delivery_details` (`id` , `customer_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `payments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(40) NOT NULL,
  `status` VARCHAR(40) NOT NULL,
  `card_number` VARCHAR(16) NULL DEFAULT NULL,
  `security code` VARCHAR(3) NULL DEFAULT NULL,
  `expired_card` VARCHAR(5) NULL DEFAULT NULL,
  `provider` VARCHAR(40) NULL DEFAULT NULL,
  `transaction_date` DATETIME NULL DEFAULT NULL,
  `slip_image` VARCHAR(50) NULL DEFAULT NULL,
  `paid_date` DATETIME NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`, `customer_id`),
  UNIQUE INDEX `slip_image_UNIQUE` (`slip_image` ASC) ,
  INDEX `fk_Payments_Customers1_idx` (`customer_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Payments_Customers1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers` (`id`))
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
  `delivery_detail_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `payments_id` INT NOT NULL,
  `payments_customer_id` INT NOT NULL,
  PRIMARY KEY (`id`, `shipping_id`, `delivery_detail_id`, `customer_id`, `payments_id`, `payments_customer_id`),
  INDEX `fk_Order_Shippings1_idx` (`shipping_id` ASC, `delivery_detail_id` ASC, `customer_id` ASC) ,
  INDEX `fk_orders_payments1_idx` (`payments_id` ASC, `payments_customer_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Order_Shippings1`
    FOREIGN KEY (`shipping_id` , `delivery_detail_id` , `customer_id`)
    REFERENCES `shippings` (`id` , `delivery_detail_id` , `customer_id`),
  CONSTRAINT `fk_orders_payments1`
    FOREIGN KEY (`payments_id` , `payments_customer_id`)
    REFERENCES `payments` (`id` , `customer_id`)
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
  PRIMARY KEY (`id`, `product_id`),
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
  `discount_price` FLOAT NOT NULL,
  `order_id` INT NOT NULL,
  `shipping_id` INT NOT NULL,
  `delivery_detail_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`, `order_id`, `shipping_id`, `delivery_detail_id`, `customer_id`),
  INDEX `fk_Order_items_Orders1_idx` (`order_id` ASC, `shipping_id` ASC, `delivery_detail_id` ASC, `customer_id` ASC) ,
  INDEX `fk_Order_items_Products1_idx` (`product_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Order_items_Orders1`
    FOREIGN KEY (`order_id` , `shipping_id` , `delivery_detail_id` , `customer_id`)
    REFERENCES `orders` (`id` , `shipping_id` , `delivery_detail_id` , `customer_id`),
  CONSTRAINT `fk_Order_items_Products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

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

CREATE TABLE IF NOT EXISTS `specs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `spec` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `product_spec_values` (
  `product_id` INT NOT NULL,
  `spec_id` INT NOT NULL,
  `spec_value` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`product_id`, `spec_id`),
  INDEX `fk_Products_has_specs_specs1_idx` (`spec_id` ASC) ,
  INDEX `fk_Products_has_specs_Products1_idx` (`product_id` ASC) ,
  CONSTRAINT `fk_Products_has_specs_Products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`id`),
  CONSTRAINT `fk_Products_has_specs_specs1`
    FOREIGN KEY (`spec_id`)
    REFERENCES `specs` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `reviews` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `star` INT NULL DEFAULT NULL,
  `comment` VARCHAR(100) NULL DEFAULT NULL,
  `review_date` DATETIME NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Reviews_Customers1_idx` (`customer_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Reviews_Customers1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `ratings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`, `category_id`),
  INDEX `fk_Ratings_Categories1_idx` (`category_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Ratings_Categories1`
    FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `rating_of_product` (
  `Reviews_id` INT NOT NULL,
  `Ratings_id` INT NOT NULL,
  `Ratings_category_id` INT NOT NULL,
  `score` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`Reviews_id`, `Ratings_id`, `Ratings_category_id`),
  INDEX `fk_Reviews_has_Ratings_Ratings1_idx` (`Ratings_id` ASC, `Ratings_category_id` ASC) ,
  INDEX `fk_Reviews_has_Ratings_Reviews1_idx` (`Reviews_id` ASC) ,
  INDEX `fk_Rating_of_product_Products1_idx` (`product_id` ASC) ,
  CONSTRAINT `fk_Rating_of_product_Products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`id`),
  CONSTRAINT `fk_Reviews_has_Ratings_Ratings1`
    FOREIGN KEY (`Ratings_id` , `Ratings_category_id`)
    REFERENCES `ratings` (`id` , `category_id`),
  CONSTRAINT `fk_Reviews_has_Ratings_Reviews1`
    FOREIGN KEY (`Reviews_id`)
    REFERENCES `reviews` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `cart_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL DEFAULT 0,
  `customer_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_Cart_items_customers1_idx` (`customer_id` ASC) ,
  INDEX `fk_Cart_items_products1_idx` (`product_id` ASC) ,
  CONSTRAINT `fk_Cart_items_customers1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers` (`id`),
  CONSTRAINT `fk_Cart_items_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`id`))
ENGINE = InnoDB;
