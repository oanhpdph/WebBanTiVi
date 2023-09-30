CREATE DATABASE webbantivi

go

USE webbantivi

go

CREATE TABLE coupon
  (
     id      BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code    VARCHAR(30),
     [value] VARCHAR(10),
     active  BIT
  )

-- Hãng
CREATE TABLE brand
  (
     id        BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code      VARCHAR(10),
     namebrand NVARCHAR(max)
  )

-- Bảo hành
CREATE TABLE guarantee
  (
     id             BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code           VARCHAR(10),
     nameguarantee  NVARCHAR(max),
     year_guarantee DATE
  )

-- Xuất xứ
CREATE TABLE origin
  (
     id         BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code       VARCHAR(10),
     nameorigin NVARCHAR(max)
  )

-- nhà cung cấp
CREATE TABLE manufacture
  (
     id               BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code             VARCHAR(10),
     name_manufacture NVARCHAR(max)
  )

-- màu sắc
CREATE TABLE color
  (
     id        BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code      VARCHAR(10),
     namecolor NVARCHAR(max)
  )

-- Loại
CREATE TABLE [type]
  (
     id        BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code      VARCHAR(10),
     name_type NVARCHAR(max)
  )

CREATE TABLE feature
  (
     id           BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code         VARCHAR(10),
     name_feature NVARCHAR(max)
  )

CREATE TABLE supplier
  (
     id            BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code          VARCHAR(10),
     name_supplier NVARCHAR(max)
  )

-- độ phân giải
CREATE TABLE resolution
  (
     id             BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code           VARCHAR(10),
     nameresolution NVARCHAR(max),
     screen_length  FLOAT,-- chiều dài màn hình (pixel)
     screen_width   FLOAT,
  )

-- size
CREATE TABLE size
  (
     id           BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code         VARCHAR(10),
     namesizeinch NVARCHAR(max),
     tv_length    FLOAT,-- chiều dài TV tính cả viền (cm)
     tv_width     FLOAT,
     thickness    FLOAT, -- độ dày TV
  )

-- sản phẩm
CREATE TABLE product
  (
     id             BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code           VARCHAR(10),
     nametv         NVARCHAR(max),
     price_import   BIGINT,
     price_export   BIGINT,
     quantity       INT,
     id_brand       BIGINT REFERENCES brand(id),
     id_guarantee   BIGINT REFERENCES guarantee(id),
     id_origin      BIGINT REFERENCES origin(id),
     id_manufacture BIGINT REFERENCES manufacture(id),
     id_color       BIGINT REFERENCES color(id),
     id_type        BIGINT REFERENCES [type](id),
     id_size        BIGINT REFERENCES size(id),
     id_resolution  BIGINT REFERENCES resolution(id),
     image          VARCHAR(max),
     active         BIT
  )

CREATE TABLE product_supplier
  (
     id_supplier BIGINT REFERENCES supplier(id),
     id_product  BIGINT REFERENCES product(id),
     quantity    INT,
     PRIMARY KEY(id_supplier, id_product)
  )

CREATE TABLE product_feature
  (
     id_feature BIGINT REFERENCES feature(id),
     id_product BIGINT REFERENCES product(id),
     PRIMARY KEY(id_feature, id_product)
  )

-- chi tiết coupon
CREATE TABLE coupon_product
  (
     id_coupon  BIGINT REFERENCES coupon(id),
     id_product BIGINT REFERENCES product(id),
     date_start DATE,
     date_end   DATE,
     PRIMARY KEY(id_coupon, id_product)
  )

-- khách hàng
CREATE TABLE customer
  (
     id                BIGINT IDENTITY(1, 1) PRIMARY KEY,
     [name]              NVARCHAR(50),
     [date]            DATE,
     diachi            NVARCHAR(max),
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
     id               BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code             VARCHAR(30),
     name_voucher     VARCHAR(100),
     [value]          INT,
     reduced_form     BIT,-- giảm theo % hoặc tiền trực tiếp
     minimum_value    MONEY,-- giá trị đơn hàng tối thiểu cần
     maximum_discount MONEY,--giá trị tối đa đơn hàng giảm
     quantity         INT, -- số lượng voucher
  )

CREATE TABLE voucher_customer
  (
     id_customer BIGINT REFERENCES customer(id),
     id_voucher  BIGINT REFERENCES voucher(id),
     date_start  DATETIME,
     date_end    DATETIME,
  )

-- đánh giá
CREATE TABLE evaluate
  (
     id          BIGINT IDENTITY(1, 1) PRIMARY KEY,
     id_product  BIGINT REFERENCES product(id),
     id_customer BIGINT REFERENCES customer(id),
     date_create DATETIME,
     point       INT,
     comment     NVARCHAR(max),
     image       VARCHAR(max)
  )


-- nhân viên
CREATE TABLE staff
  (
     id          BIGINT IDENTITY(1, 1) PRIMARY KEY,
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
     id               BIGINT IDENTITY(1, 1) PRIMARY KEY,
     id_customer      BIGINT REFERENCES customer(id),
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
     id_bill    BIGINT REFERENCES bill(id),
     id_product BIGINT REFERENCES product(id),
     quantity   INT,
     price      NVARCHAR(30),
     PRIMARY KEY(id_bill, id_product)
  )

CREATE TABLE delivery_notes
  (
     id             UNIQUEIDENTIFIER DEFAULT Newid() PRIMARY KEY,
     received       NVARCHAR(50),
     received_phone VARCHAR(20),
     deliver        NVARCHAR(50),
     delivery_phone VARCHAR(20),
     delivery_date  DATE,
     received_date  DATE,
     delivery_fee   MONEY,
     id_bill        BIGINT REFERENCES bill(id),
     note           NVARCHAR(max),
     status         INT,
  )

--giỏ hàng
CREATE TABLE cart
  (
     id          BIGINT IDENTITY(1, 1) PRIMARY KEY,
     id_customer BIGINT REFERENCES customer(id),
     code        NVARCHAR(30),
     date_update DATETIME
  )

-- giỏ hàng chi tiết
CREATE TABLE cart_product
  (
     cart_id     BIGINT REFERENCES cart(id),
     product_id  BIGINT REFERENCES product(id),
     quantity    INT,
     note        NVARCHAR(max),
     create_date DATETIME,
	 date_update DATETIME
     PRIMARY KEY(cart_id, product_id)
  )