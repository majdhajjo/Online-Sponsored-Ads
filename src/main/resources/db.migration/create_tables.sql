create table product (
    product_serial_number VARCHAR(50) PRIMARY KEY,
    category VARCHAR(50) NOT NULL,
    title VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    INDEX idx_category (category)
);

CREATE TABLE campaign (
    campaign_id INT NOT NULL NOT NULL AUTO_INCREMENT PRIMARY KEY,
    campaign_name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    bid DECIMAL(10, 2) NOT NULL
);

CREATE TABLE campaign_product (
    campaign_id INT,
    product_serial_number VARCHAR(50),
    PRIMARY KEY (campaign_id, product_serial_number),
    FOREIGN KEY (campaign_id) REFERENCES campaign(campaign_id),
    FOREIGN KEY (product_serial_number) REFERENCES product(product_serial_number)
);