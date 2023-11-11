﻿CREATE DATABASE webbantivi

go

USE webbantivi

go


--- BỎ
-- Hãng
CREATE TABLE brand
  (
     id        INT IDENTITY(1, 1) PRIMARY KEY,
     code      VARCHAR(10) not null,
     namebrand NVARCHAR(max) not null
  )
 
-- Xuất xứ
CREATE TABLE origin
  (
     id         INT IDENTITY(1, 1) PRIMARY KEY,
     code       VARCHAR(10) not null,
     nameorigin NVARCHAR(max) not null
  )

-- nhà cung cấp
CREATE TABLE manufacture
  (
     id               INT IDENTITY(1, 1) PRIMARY KEY,
     code             VARCHAR(10) not null,
     name_manufacture NVARCHAR(max) not null
  )

-- màu sắc
CREATE TABLE color
  (
     id        INT IDENTITY(1, 1) PRIMARY KEY,
     code      VARCHAR(10) not null,
     namecolor NVARCHAR(max) not null
  )

-- Loại
CREATE TABLE [type]
  (
     id        INT IDENTITY(1, 1) PRIMARY KEY,
     code      VARCHAR(10) not null,
     name_type NVARCHAR(max) not null
  )

CREATE TABLE feature
  (
     id           INT IDENTITY(1, 1) PRIMARY KEY,
     code         VARCHAR(10) not null,
     name_feature NVARCHAR(max) not null
  )

CREATE TABLE supplier
  (
     id            INT IDENTITY(1, 1) PRIMARY KEY,
     code          VARCHAR(10) not null,
     name_supplier NVARCHAR(max) not null
  )

-- độ phân giải
CREATE TABLE resolution
  (
     id             INT IDENTITY(1, 1) PRIMARY KEY,
     code           VARCHAR(10) not null,
     nameresolution NVARCHAR(max) not null, 
     screen_length  FLOAT not null,-- chiều dài màn hình (pixel)
     screen_width   FLOAT not null,
  )
 
-- size
CREATE TABLE size
  (
     id           INT IDENTITY(1, 1) PRIMARY KEY,
     code         VARCHAR(10) not null,
     namesizeinch NVARCHAR(max) not null,
     tv_length    FLOAT not null,-- chiều dài TV tính cả viền (cm)
     tv_width     FLOAT not null,
     thickness    FLOAT not null, -- độ dày TV
  )

-- sản phẩm
CREATE TABLE product
  (
     id             INT IDENTITY(1, 1) PRIMARY KEY,
     code           VARCHAR(10) not null unique,
     nametv         NVARCHAR(max) not null,
     price_import   money not null,
     price_export   money not null,
     quantity       INT not null,
     guarantee      INT not null,
     id_brand       INT REFERENCES brand(id),
     id_origin      INT REFERENCES origin(id),
     id_manufacture INT REFERENCES manufacture(id),
     id_color       INT REFERENCES color(id),
     id_type        INT REFERENCES [type](id),
     id_size        INT REFERENCES size(id),
     id_resolution  INT REFERENCES resolution(id),
	 id_supplier	INT REFERENCES supplier(id),
     active         BIT
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
	 id_product INT REFERENCES product(id) not null,
     name_image varchar(200) not null,
	 location bit
  )


-- chi tiết coupon
CREATE TABLE coupon_product
  (
     id_coupon  INT REFERENCES coupon(id),
     id_product INT REFERENCES product(id),
     date_start DATE not null,
     date_end   DATE not null,
	 status BIT not null,
     PRIMARY KEY(id_coupon, id_product)
  )


  -- nhân viên
CREATE TABLE staff
  (
     id          INT IDENTITY(1, 1) PRIMARY KEY,
     username    NVARCHAR(30) not null unique,
     [name]      NVARCHAR(50) not null,
     gender      BIT,
     birthday    DATE,
     address     NVARCHAR(max),
     email       VARCHAR(100) not null,
     phone       VARCHAR(10) not null,
     password    VARCHAR(max) not null,
     active      BIT,
     role varchar(30),
     avatar      VARCHAR(100)
  )



  -- đến đây







CREATE TABLE coupon
  (
     id      INT IDENTITY(1, 1) PRIMARY KEY,
     code    VARCHAR(30) not null unique,
     [value] VARCHAR(10) not null,
	 image varchar(200),
     active  BIT
  )

-- tài khoản
CREATE TABLE users
  (
     id                INT IDENTITY(1, 1) PRIMARY KEY,
	 username          NVARCHAR(50), 
     [name]            NVARCHAR(50),
     [date]            DATE,
     [address]         NVARCHAR(max),
     phone_number      VARCHAR(10) not null,
     email             VARCHAR(50) UNIQUE not null,
	 password          VARCHAR(max),
     gender            BIT,
     avatar            VARCHAR(50),
	 role              varchar(20),
     status            bit not null,
  )
  

CREATE TABLE voucher
  (
     id               INT IDENTITY(1, 1) PRIMARY KEY,
     code             VARCHAR(30) not null unique,
     name_voucher     VARCHAR(100) not null,
     [value]          INT not null,
     reduced_form     BIT not null,-- giảm theo % hoặc tiền trực tiếp
     minimum_value    MONEY not null,-- giá trị đơn hàng tối thiểu cần
     maximum_discount MONEY not null,--giá trị tối đa đơn hàng giảm
     quantity         INT not null, -- số lượng voucher
	 start_day		  DATE not null,-- thời gian bắt đầu có hiệu lực
     expiration_date  DATE not null,-- thời gian mã giảm giá hết hiệu lực
	 active			  BIT,
	 image			  varchar(100)
  )

CREATE TABLE voucher_user
  (	 id			 Int IDENTITY(1, 1) primary key,
     id_user  INT references users(id),
     id_voucher  INT references voucher(id),
     date_start  DATETIME not null,-- thời gian nhận
     date_end    DATETIME not null,-- thời gian hết hiệu lực
	 active      BIT not null
  )

-- đánh giá
CREATE TABLE evaluate
  (
	 id          INT IDENTITY(1, 1) PRIMARY KEY,
     id_product  INT REFERENCES product(id) not null,
     id_user	INT REFERENCES users(id) not null,
     date_create DATETIME not null,
     point       float not null,
     comment     NVARCHAR(max),
  )

CREATE TABLE image_evaluate
  (
     id          INT IDENTITY(1, 1) PRIMARY KEY,
     id_evaluate INT REFERENCES evaluate(id) not null,
	 name_image varchar(200) not null
  )

-- trạng thái hóa đơn
CREATE TABLE bill_status
  (
     id          INT IDENTITY(1, 1) PRIMARY KEY,
	 code		 varchar(10) not null unique,
     status      NVARCHAR(100) not null,
     description NVARCHAR(max) not null
  )

-- phương thức thanh toán
CREATE TABLE payment_method
  (
     id             INT IDENTITY(1, 1) PRIMARY KEY,
	 code			varchar(10) not null unique,
     payment_method NVARCHAR(255) not null,
     active         BIT not null,
     description    NVARCHAR(max)
  )

--hóa đơn
CREATE TABLE bill
  (
     id               INT IDENTITY(1, 1) PRIMARY KEY,
     id_user		  INT REFERENCES users(id) not null,
     code             VARCHAR(20) not null unique,
     create_date      DATE NOT NULL,
	 total_price		  Money,
     payment_date     DATE not null,-- ngày thanh toán
     id_status        INT REFERENCES bill_status(id) not null,
     id_paymentmethod INT REFERENCES payment_method(id) not null,
	 id_voucher		  int references voucher(id),
	 voucher_value	  money,
	 payment_status	  INT default 1,
     note             NVARCHAR(max)
  )

-- hoa don chi tiet
CREATE TABLE bill_product
  (
	 id				int identity(1,1) primary key,
     id_bill		INT REFERENCES bill(id),
     id_product		INT REFERENCES product(id),
     quantity		INT not null,
     price			money not null,
	 reduced_money	money,
	 status			bit,
	 note			NVARCHAR(200)
   --  PRIMARY KEY(id_bill, id_product)
  )

CREATE TABLE image_returned
  (
     id					INT IDENTITY(1, 1) PRIMARY KEY,
     id_bill_product	 INT REFERENCES bill_product(id) not null,
	 name_image			 varchar(200) not null
  )

CREATE TABLE delivery_notes
  (
     id					INT IDENTITY(1,1) PRIMARY KEY,
     received			NVARCHAR(50) not null,
     received_phone		VARCHAR(20) not null,
	 received_email		VARCHAR(100) not null,
	 receiving_address  NVARCHAR(200) NOT NULL,
     deliver			NVARCHAR(50),
     delivery_phone		VARCHAR(20) not null,
     delivery_date		DATE,
     received_date		DATE,
     delivery_fee		MONEY,
     note				NVARCHAR(max),
     status				INT,
	 id_bill			INT REFERENCES bill(id) not null,
  )

--giỏ hàng
CREATE TABLE cart
  (
     id          INT IDENTITY(1, 1) PRIMARY KEY,
     id_users INT REFERENCES users(id) not null,
     code        NVARCHAR(30) unique,
     date_update DATETIME,
  )

-- giỏ hàng chi tiết
CREATE TABLE cart_product
  (
     cart_id     INT REFERENCES cart(id),
     product_id  INT REFERENCES product(id),
     quantity    INT not null,
     note        NVARCHAR(max),
     create_date DATETIME,
	 date_update DATETIME
     PRIMARY KEY(cart_id, product_id)
  )
GO
 INSERT INTO [dbo].[bill_status]
           ([code]
           ,[status]
           ,[description])
     VALUES
           ('WP'
           ,N'Chờ xử lý'
           ,N'Chờ xác nhận mua hàng bên phía khách hàng')
GO
INSERT INTO [dbo].[bill_status]
           ([code]
           ,[status]
           ,[description])
     VALUES
           ('PG'
           ,N'Đang chuẩn bị hàng'
           ,N'Cửa hàng chuẩn bị hàng, đóng gói hàng cho khách')
GO
INSERT INTO [dbo].[bill_status]
           ([code]
           ,[status]
           ,[description])
     VALUES
           ('DE'
           ,N'Đang giao hàng'
           ,N'Sản phẩm đang được giao đến cho khách hàng')
GO

INSERT INTO [dbo].[bill_status]
           ([code]
           ,[status]
           ,[description])
     VALUES
           ('CO'
           ,N'Đã hoàn thành'
           ,N'Đơn hàng giao thành công đến cho khách hàng')
		   GO
INSERT INTO [dbo].[bill_status]
           ([code]
           ,[status]
           ,[description])
     VALUES
           ('SC'
           ,N'Shop hủy'
           ,N'Đơn hàng bị hủy từ phía cửa hàng')

GO
INSERT INTO [dbo].[bill_status]
           ([code]
           ,[status]
           ,[description])
     VALUES
           ('CC'
           ,N'Khách hủy'
           ,N'Khách hàng hủy mua sản phẩm')

GO
INSERT INTO [dbo].[bill_status]
           ([code]
           ,[status]
           ,[description])
     VALUES
           ('RR'
           ,N'Yêu cầu trả hàng'
           ,N'Khách hàng yêu cầu trả hàng')

GO
INSERT INTO [dbo].[bill_status]
           ([code]
           ,[status]
           ,[description])
     VALUES
           ('WR'
           ,N'Chờ trả hàng'
           ,N'Chờ sản phẩm gửi về khi yêu cầu trả hàng được xác nhận')

GO
INSERT INTO [dbo].[bill_status]
           ([code]
           ,[status]
           ,[description])
     VALUES
           ('RE'
           ,N'Đã trả hàng'
           ,N'Đã nhận lại được sản phẩm bị yêu cầu trả hàng')
GO

INSERT INTO [dbo].[payment_method]
           ([code]
           ,[payment_method]
           ,[active]
           ,[description])
     VALUES
           ('CA'
           ,N'Tiền mặt'
           ,1
           ,N'Thanh toán trực tiếp bằng tiền mặt khi nhận hàng')
GO

INSERT INTO [dbo].[payment_method]
           ([code]
           ,[payment_method]
           ,[active]
           ,[description])
     VALUES
           ('VNP'
           ,'Thanh toán điện tử Vn-pay'
           ,1
           ,N'Thanh toán trực tuyến qua Vn-pay')
GO



create table group_product(
  id int identity(1,1) primary key,
  name_group nvarchar(100)
)


CREATE TABLE [type]
  (
     id        INT IDENTITY(1, 1) PRIMARY KEY,
     code      VARCHAR(10) not null,
     name_type NVARCHAR(max) not null,
	 group_product_id int references group_product(id)
 )

Create table field(
	id		int identity(1,1) primary key,
	code	varchar(10),
	name	nvarchar(100) not null,
	variant bit
)

CREATE TABLE coupon_product
  (
     id_coupon  INT REFERENCES coupon(id),
     id_product INT REFERENCES product(id),
     date_start DATE not null,
     date_end   DATE not null,
     PRIMARY KEY(id_coupon, id_product)
  )
CREATE TABLE product
  (
     id             INT IDENTITY(1, 1) PRIMARY KEY,
     sku            VARCHAR(40),
	 name_product   nvarchar(200),
	 type			int references type(id),
     price_import   money,
     price_export   money,
     quantity       INT,
	 description	nvarchar(1000),
	 avg_point		float,
	 same_product	varchar(10),
     active         BIT
)

create table image (
	id					int identity(1,1) primary key,
	id_product			int references product(id),
	link				varchar(1000),
	location			int
)

create table product_field_value (
	id					int	identity(1,1) primary key,
	value				nvarchar(1000),
	field_id			int references field(id),
	product_id   int references product(id),
	priority			int
)

