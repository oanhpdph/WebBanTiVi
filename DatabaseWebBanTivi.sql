CREATE DATABASE webbantivi

go

USE webbantivi

go

CREATE TABLE coupon
  (
     id      INT IDENTITY(1, 1) PRIMARY KEY,
     code    VARCHAR(30),
     [value] VARCHAR(10),
	 image varchar(200),
     active  BIT
  )

-- Hãng
CREATE TABLE brand
  (
     id        INT IDENTITY(1, 1) PRIMARY KEY,
     code      VARCHAR(10),
     namebrand NVARCHAR(max)
  )


-- Xuất xứ
CREATE TABLE origin
  (
     id         INT IDENTITY(1, 1) PRIMARY KEY,
     code       VARCHAR(10),
     nameorigin NVARCHAR(max)
  )

-- nhà cung cấp
CREATE TABLE manufacture
  (
     id               INT IDENTITY(1, 1) PRIMARY KEY,
     code             VARCHAR(10),
     name_manufacture NVARCHAR(max)
  )

-- màu sắc
CREATE TABLE color
  (
     id        INT IDENTITY(1, 1) PRIMARY KEY,
     code      VARCHAR(10),
     namecolor NVARCHAR(max)
  )

-- Loại
CREATE TABLE [type]
  (
     id        INT IDENTITY(1, 1) PRIMARY KEY,
     code      VARCHAR(10),
     name_type NVARCHAR(max)
  )

CREATE TABLE feature
  (
     id           INT IDENTITY(1, 1) PRIMARY KEY,
     code         VARCHAR(10),
     name_feature NVARCHAR(max)
  )

CREATE TABLE supplier
  (
     id            INT IDENTITY(1, 1) PRIMARY KEY,
     code          VARCHAR(10),
     name_supplier NVARCHAR(max)
  )

-- độ phân giải
CREATE TABLE resolution
  (
     id             INT IDENTITY(1, 1) PRIMARY KEY,
     code           VARCHAR(10),
     nameresolution NVARCHAR(max),
     screen_length  FLOAT,-- chiều dài màn hình (pixel)
     screen_width   FLOAT,
  )

-- size
CREATE TABLE size
  (
     id           INT IDENTITY(1, 1) PRIMARY KEY,
     code         VARCHAR(10),
     namesizeinch NVARCHAR(max),
     tv_length    FLOAT,-- chiều dài TV tính cả viền (cm)
     tv_width     FLOAT,
     thickness    FLOAT, -- độ dày TV
  )

-- sản phẩm
CREATE TABLE product
  (
     id             INT IDENTITY(1, 1) PRIMARY KEY,
     code           VARCHAR(10),
     nametv         NVARCHAR(max),
     price_import   money,
     price_export   money,
     quantity       INT,
     guarantee      INT,
     id_brand       INT REFERENCES brand(id),
     id_origin      INT REFERENCES origin(id),
     id_manufacture INT REFERENCES manufacture(id),
     id_color       INT REFERENCES color(id),
     id_type        INT REFERENCES [type](id),
     id_size        INT REFERENCES size(id),
     id_resolution  INT REFERENCES resolution(id),
     active         BIT
  )

CREATE TABLE product_supplier
  (
     id_supplier INT REFERENCES supplier(id),
     id_product  INT REFERENCES product(id),
     PRIMARY KEY(id_supplier, id_product)
  )

CREATE TABLE product_feature
  (
     id_feature INT REFERENCES feature(id),
     id_product INT REFERENCES product(id),
     PRIMARY KEY(id_feature, id_product)
  )

CREATE TABLE image_product
  (
     id INT IDENTITY(1,1) primary key,
	 id_product INT REFERENCES product(id),
     name_image varchar(200),
  )
-- chi tiết coupon
CREATE TABLE coupon_product
  (
     id_coupon  INT REFERENCES coupon(id),
     id_product INT REFERENCES product(id),
     date_start DATE,
     date_end   DATE,
     PRIMARY KEY(id_coupon, id_product)
  )

-- khách hàng
CREATE TABLE customer
  (
     id                INT IDENTITY(1, 1) PRIMARY KEY,
     [name]            NVARCHAR(50),
     [date]            DATE,
     address            NVARCHAR(max),
     phone_number      VARCHAR(15),
     email             VARCHAR(50) UNIQUE,
     gender            BIT,
     id_card           VARCHAR(15) UNIQUE,
     id_number         VARCHAR(15) UNIQUE,
     avatar            VARCHAR(50),
     status            INT,
     accumulated_point INT DEFAULT 0
  )

CREATE TABLE voucher
  (
     id               INT IDENTITY(1, 1) PRIMARY KEY,
     code             VARCHAR(30),
     name_voucher     VARCHAR(100),
     [value]          INT,
     reduced_form     BIT,-- giảm theo % hoặc tiền trực tiếp
     minimum_value    MONEY,-- giá trị đơn hàng tối thiểu cần
     maximum_discount MONEY,--giá trị tối đa đơn hàng giảm
     quantity         INT, -- số lượng voucher
	 start_day		  DATE,-- thời gian bắt đầu có hiệu lực
     expiration_date  DATE,-- thời gian mã giảm giá hết hiệu lực
	 active			  BIT
  )

CREATE TABLE voucher_customer
  (
     id_customer INT REFERENCES customer(id),
     id_voucher  INT REFERENCES voucher(id),
     date_start  DATETIME,-- thời gian nhận
     date_end    DATETIME,-- thời gian hết hiệu lực
	 active      BIT
  )

-- đánh giá
CREATE TABLE evaluate
  (
	 id          INT IDENTITY(1, 1) PRIMARY KEY,
     id_product  INT REFERENCES product(id),
     id_customer INT REFERENCES customer(id),
     date_create DATETIME,
     point       INT,
     comment     NVARCHAR(max),
  )

CREATE TABLE image_evaluate
  (
     id          INT IDENTITY(1, 1) PRIMARY KEY,
     id_evaluate INT REFERENCES evaluate(id),
	 name_image varchar(200)
  )


-- nhân viên
CREATE TABLE staff
  (
     id          INT IDENTITY(1, 1) PRIMARY KEY,
     code        NVARCHAR(30),
     [name]      NVARCHAR(50),
     gender      BIT,
     birthday    DATE,
     address     NVARCHAR(max),
     email       VARCHAR(100),
     phone       NVARCHAR(10),
     password    VARCHAR(20),
     active      BIT,
     position	 BIT,
     avatar      VARCHAR(100)
  )

-- trạng thái hóa đơn
CREATE TABLE bill_status
  (
     id          INT IDENTITY(1, 1) PRIMARY KEY,
     status      NVARCHAR(100),
     description NVARCHAR(max)
  )

-- phương thức thanh toán
CREATE TABLE payment_method
  (
     id             INT IDENTITY(1, 1) PRIMARY KEY,
     payment_method NVARCHAR(255),
     active         BIT,
     description    NVARCHAR(max)
  )

--hóa đơn
CREATE TABLE bill
  (
     id               INT IDENTITY(1, 1) PRIMARY KEY,
     id_customer      INT REFERENCES customer(id),
     code             VARCHAR(10),
     create_date      DATE NOT NULL,
     payment_date     DATE,-- ngày thanh toán
     id_status        INT REFERENCES bill_status(id),
     id_paymentmethod INT REFERENCES payment_method,
     note             NVARCHAR(max)
  )

-- hoa don chi tiet
CREATE TABLE bill_product
  (
     id_bill    INT REFERENCES bill(id),
     id_product INT REFERENCES product(id),
     quantity   INT,
     price      money,
     PRIMARY KEY(id_bill, id_product)
  )

CREATE TABLE delivery_notes
  (
     id             INT IDENTITY(1,1) PRIMARY KEY,
     received       NVARCHAR(50),
     received_phone VARCHAR(20),
     deliver        NVARCHAR(50),
     delivery_phone VARCHAR(20),
     delivery_date  DATE,
     received_date  DATE,
     delivery_fee   MONEY,
     note           NVARCHAR(max),
     status         INT,
	 id_bill        INT REFERENCES bill(id),
  )

--giỏ hàng
CREATE TABLE cart
  (
     id          INT IDENTITY(1, 1) PRIMARY KEY,
     id_customer INT REFERENCES customer(id),
     code        NVARCHAR(30),
     date_update DATETIME
  )

-- giỏ hàng chi tiết
CREATE TABLE cart_product
  (
     cart_id     INT REFERENCES cart(id),
     product_id  INT REFERENCES product(id),
     quantity    INT,
     note        NVARCHAR(max),
     create_date DATETIME,
	 date_update DATETIME
     PRIMARY KEY(cart_id, product_id)
  )