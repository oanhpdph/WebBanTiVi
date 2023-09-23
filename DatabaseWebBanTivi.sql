﻿CREATE DATABASE webbantivi

go

USE webbantivi

go

-- lý do trả hàng
CREATE TABLE reason_returns
  (
     id     INT IDENTITY PRIMARY KEY,
     reason NVARCHAR(max)-- lý do trả hàng là ?
  )
  -- Hãng
CREATE TABLE brand
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY,
   code varchar(10),
   namebrand nvarchar(max)
  )
  -- Bảo hành
CREATE TABLE guarantee
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY,
   code varchar(10),
   nameguarantee nvarchar(max),
   year_guarantee date
  )
  -- Xuất xứ
CREATE TABLE origin
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY,
   code varchar(10),
   nameorigin nvarchar(max)
  )
  -- nhà cung cấp
CREATE TABLE manufacture
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY,
   code varchar(10),
   namemanufacture nvarchar(max)
  )
  -- màu sắc
CREATE TABLE color
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY,
   code varchar(10),
   namecolor nvarchar(max)
  )
  -- Loại
CREATE TABLE category
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY,
   code varchar(10),
   namecategory nvarchar(max)
  )
  -- độ phân giải
CREATE TABLE resolution
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY,
   code varchar(10),
   nameresolution nvarchar(max),
   screen_length float,-- chiều dài màn hình (pixel)
   screen_width float,
  )
  -- size
CREATE TABLE size
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY,
   code varchar(10),
   namesizeinch nvarchar(max),
   TV_length float, -- chiều dài TV tính cả viền (cm)
   TV_width float,
   thickness float, -- độ dày TV
  )
  -- sản phẩm
CREATE TABLE products
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY,
   code varchar(10),
   nameTV nvarchar(max),
   price_import bigint,
   price_export bigint,
   quantity int,
   id_brand     BigInt REFERENCES brand(id),
   id_guarantee    BigInt REFERENCES guarantee(id),
   id_origin   BigInt REFERENCES origin(id),
   id_manufacture   BigInt REFERENCES manufacture(id),
   id_color    BigInt REFERENCES color(id),
   id_category   BigInt REFERENCES category(id),
   id_resolution   BigInt REFERENCES resolution(id),
   id_size   BigInt REFERENCES size(id),

  )

  -- hình ảnh
CREATE TABLE images
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY,
   id_product   BigInt REFERENCES products(id) ,-- id sản phẩm
     image_product VARCHAR(max)
  )

-- khách hàng
CREATE TABLE customer
  (
     id                BIGINT IDENTITY(1, 1) PRIMARY KEY,
     NAME              NVARCHAR(50),
     [date]            DATE,
     diachi            NVARCHAR(max),
     phone_number      VARCHAR(15) UNIQUE,
     email             VARCHAR(50) UNIQUE,
     gender            BIT,
     id_card           VARCHAR(15) UNIQUE,
     id_number         VARCHAR(15) UNIQUE,
     avatar            VARCHAR(50),
     status            INT,
     accumulated_point INT DEFAULT 0
  )

    -- chức vụ
  CREATE TABLE Position 
  (
  id BIGINT IDENTITY(1, 1) PRIMARY KEY,
  code nvarchar(30),
  name nvarchar(30)
  )
  -- nhân viên
  CREATE TABLE staff
  (
  id BIGINT IDENTITY(1, 1) PRIMARY KEY,
  code nvarchar(30),
  last_name nvarchar(30),
  middle_name nvarchar(30),
  first_name nvarchar(30),
  gender tinyint,
  date Date,
  address nvarchar(50),
  phone nvarchar(30),
  password nvarchar(20),
  status int,
  position_id BIGINT REFERENCES Position(id)
  )
--hóa đơn
CREATE TABLE bill
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY,
	 id_customer BIGINT REFERENCES customer(id),
	 code varchar(10) ,
	 create_date Date not null,
	 payment_date Date not null, -- ngày thanh toán
	 status int,
  )

  -- hoa don chitiet 
  CREATE TABLE bill_product
  (
  id_bill BIGINT references bill(id),
  id_product BIGINT references products(id),
  quantity int ,
  price nvarchar(30),
  primary key(id_bill,id_product)
  )
  --giỏ hàng
  CREATE TABLE Cart
  (
  id BIGINT IDENTITY(1, 1) PRIMARY KEY,
  id_customer BIGINT REFERENCES customer(id) ,
  code nvarchar(30),
  create_date Date,
  )
  -- giỏ hàng chi tiết
  CREATE TABLE cart_product
  (
  cart_id BIGINT references Cart(id),
  product_id BIGINT references products(id),
  quantity int,
  price nvarchar(30),
  create_date Datetime
  primary key(cart_id,product_id)
  )
-- phiếu trả hàng
CREATE TABLE returns_product
  (
     id          BIGINT IDENTITY(1, 1) PRIMARY KEY,
     id_customer BIGINT REFERENCES customer(id),
     id_bill     BIGINT REFERENCES bill(id),
     id_product  BIGINT REFERENCES products(id),
     create_date DATETIME,
     quantity    INT,
     status      INT -- trạng thái phiếu trả hàng
  )

-- ảnh hoặc video đính kèm trả hàng
CREATE TABLE image_or_video
  (
     id             UNIQUEIDENTIFIER DEFAULT Newid() PRIMARY KEY,
     id_returns     BIGINT REFERENCES returns_product(id),
     -- id phiếu trả hàng
     image_or_video VARCHAR(max)
  )
 
 --
CREATE TABLE promotion(
  id  BIGINT IDENTITY(1, 1) PRIMARY KEY,
  code varchar(50),
  Reducedvalue    MONEY,
  Maximumreductionvalue  MONEY,
)
GO
CREATE TABLE promotion_details(
  id_promotion  BIGINT references promotion(id),
  id_product     BigInt REFERENCES products(id),
  Startdate    DATE,
  Enddate    DATE,
  Reducedform  BIT,
  status    INT,
  primary key(id_promotion, id_product)
)
GO

