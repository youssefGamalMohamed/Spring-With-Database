DROP TABLE IF EXISTS `categories`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `products_categories`;

CREATE TABLE `products`
(
    `product_id` int(11) NOT NULL AUTO_INCREMENT,
    `name`       varchar(100) DEFAULT NULL,
    `quantity`   int(11) DEFAULT NULL,
    `price`      double       DEFAULT NULL,
    PRIMARY KEY (`product_id`)
);




CREATE TABLE `categories`
(
    `category_id` int(11) NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) DEFAULT NULL,
    PRIMARY KEY (`category_id`)
);


CREATE TABLE `products_categories`
(
    `product_id`  int(11) DEFAULT NULL,
    `category_id` int(11) DEFAULT NULL,
    KEY           `product_id_idx` (`product_id`),
    KEY           `category_id_idx` (`category_id`),
    CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
    CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
);



