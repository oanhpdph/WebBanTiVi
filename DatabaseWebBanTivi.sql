CREATE DATABASE webbantivi

go

USE webbantivi

go

CREATE TABLE coupon
  (
     id      INT IDENTITY(1, 1) PRIMARY KEY,
     code    VARCHAR(30) not null unique,
     [value] VARCHAR(10) not null,
	 image varchar(200),
     active  BIT
  )

-- Hãng
CREATE TABLE brand
  (
     id        INT IDENTITY(1, 1) PRIMARY KEY,
     code      VARCHAR(10) not null,
     namebrand NVARCHAR(max) not null
  )
  select * from brand 
  insert into brand values('b1','SamSung')
  insert into origin values('o1','VietNam')
  insert into manufacture values('m1','manu1')
  insert into color values('c1','Black')
  insert into [type] values('t1','oled')
  insert into feature values('f1','feature1')
  insert into supplier values('s1','sup1')

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
  insert into resolution values('b1','HD','720','1280')
insert into size values('b1','X','720','1280','4')
select * from size
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
  insert into image_product(id_product, name_image,location) values(4,'anh 1',1)
  select * from image_product
  drop table image_product

-- chi tiết coupon
CREATE TABLE coupon_product
  (
     id_coupon  INT REFERENCES coupon(id),
     id_product INT REFERENCES product(id),
     date_start DATE not null,
     date_end   DATE not null,
     PRIMARY KEY(id_coupon, id_product)
  )

-- khách hàng
CREATE TABLE customer
  (
     id                INT IDENTITY(1, 1) PRIMARY KEY,
     [name]            NVARCHAR(50),
     [date]            DATE,
     [address]         NVARCHAR(max),
     phone_number      VARCHAR(10) not null,
     email             VARCHAR(50) UNIQUE not null,
	 password          VARCHAR(50),
     gender            BIT,
     id_card           VARCHAR(15) UNIQUE,
     avatar            VARCHAR(50),
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
	 active			  BIT
  )

CREATE TABLE voucher_customer
  (
     id_customer INT REFERENCES customer(id),
     id_voucher  INT REFERENCES voucher(id),
     date_start  DATETIME not null,-- thời gian nhận
     date_end    DATETIME not null,-- thời gian hết hiệu lực
	 active      BIT not null
  )

-- đánh giá
CREATE TABLE evaluate
  (
	 id          INT IDENTITY(1, 1) PRIMARY KEY,
     id_product  INT REFERENCES product(id) not null,
     id_customer INT REFERENCES customer(id) not null,
     date_create DATETIME not null,
     point       INT not null,
     comment     NVARCHAR(max) ,
  )
  select * from evaluate 
  insert into evaluate values('7','1','10/19/2023',4,'ngon bo re')
   select * from cart
  insert into cart values('1','Cart1','10/19/2023')
   select * from cart_product
CREATE TABLE image_evaluate
  (
     id          INT IDENTITY(1, 1) PRIMARY KEY,
     id_evaluate INT REFERENCES evaluate(id) not null,
	 name_image varchar(200) not null
  )


-- nhân viên
CREATE TABLE staff
  (
     id          INT IDENTITY(1, 1) PRIMARY KEY,
     code        NVARCHAR(30) not null unique,
     [name]      NVARCHAR(50) not null,
     gender      BIT,
     birthday    DATE,
     address     NVARCHAR(max),
     email       VARCHAR(100) not null,
     phone       VARCHAR(10) not null,
     password    VARCHAR(20) not null,
     active      BIT,
     position	 BIT,
     avatar      VARCHAR(100)
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
     id_customer      INT REFERENCES customer(id) not null,
     code             VARCHAR(10) not null unique,
     create_date      DATE NOT NULL,
	 totalPrice		  Money,
     payment_date     DATE not null,-- ngày thanh toán
     id_status        INT REFERENCES bill_status(id) not null,
     id_paymentmethod INT REFERENCES payment_method not null,
	 payment_status	  INT default 1,
     note             NVARCHAR(max)
  )
  select * from bill b where b.create_date between '01/01/2023' and '01/02/2023'

-- hoa don chi tiet
CREATE TABLE bill_product
  (
     id_bill    INT REFERENCES bill(id),
     id_product INT REFERENCES product(id),
     quantity   INT not null,
     price      money not null,
	 status		bit
     PRIMARY KEY(id_bill, id_product)
  )

CREATE TABLE delivery_notes
  (
     id             INT IDENTITY(1,1) PRIMARY KEY,
     received       NVARCHAR(50) not null,
     received_phone VARCHAR(20) not null,
     deliver        NVARCHAR(50),
     delivery_phone VARCHAR(20) not null,
     delivery_date  DATE,
     received_date  DATE,
     delivery_fee   MONEY,
     note           NVARCHAR(max),
     status         INT,
	 id_bill        INT REFERENCES bill(id) not null,
  )

--giỏ hàng
CREATE TABLE cart
  (
     id          INT IDENTITY(1, 1) PRIMARY KEY,
     id_customer INT REFERENCES customer(id) not null,
     code        NVARCHAR(30) unique,
     date_update DATETIME,
  )
  select * from product
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
           ('OS'
           ,N'Hết hàng'
           ,N'Cửa hàng hết sản phẩm trong kho')
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
           ('DEE'
           ,N'Lỗi giao hàng'
           ,N'Xảy ra lỗi trong quá trình giao hàng')
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

INSERT INTO [dbo].[customer]
           ([name]
           ,[date]
           ,[address]
           ,[phone_number]
           ,[email]
           ,[password]
           ,[gender]
           ,[id_card]
           ,[avatar]
           ,[status])
     VALUES
           (N'Phạm đức oanh'
           ,'2003-12-13'
           ,N'Liên trì 1, yên hòa, yên mô, ninh bình'
           ,'0369921455'
           ,'oanhpdph25707@fpt.edu.vn'
           ,'123456'
           ,1
           ,'037203004908'
           ,''
           ,1)
GO

INSERT INTO [dbo].[bill]
           ([id_customer]
           ,[code]
           ,[create_date]
           ,[totalPrice]
           ,[payment_date]
           ,[id_status]
           ,[id_paymentmethod]
           ,[payment_status]
           ,[note])
     VALUES
           (1
           ,'HD1'
           ,'2023-10-15'
           ,10000000
           ,'2023-10-15'
           ,1
           ,2
           ,1
           ,N'')
GO


INSERT INTO [dbo].[product]
           ([code]
           ,[nametv]
           ,[price_import]
           ,[price_export]
           ,[quantity]
           ,[guarantee]
           ,[id_brand]
           ,[id_origin]
           ,[id_manufacture]
           ,[id_color]
           ,[id_type]
           ,[id_size]
           ,[id_resolution]
           ,[active])
     VALUES
           ('SP01'
           ,N'Sản phẩm 1'
           ,10000
           ,15000
           ,100
           ,3
           ,null
           ,null
           ,null
           ,null
           ,null
           ,null
           ,null
           ,1)
GO
INSERT INTO [dbo].[product]
           ([code]
           ,[nametv]
           ,[price_import]
           ,[price_export]
           ,[quantity]
           ,[guarantee]
           ,[id_brand]
           ,[id_origin]
           ,[id_manufacture]
           ,[id_color]
           ,[id_type]
           ,[id_size]
           ,[id_resolution]
           ,[active])
     VALUES
           ('SP02'
           ,N'Sản phẩm 2'
           ,10000
           ,15000
           ,100
           ,3
           ,null
           ,null
           ,null
           ,null
           ,null
           ,null
           ,null
           ,1)
GO

INSERT INTO [dbo].[product]
           ([code]
           ,[nametv]
           ,[price_import]
           ,[price_export]
           ,[quantity]
           ,[guarantee]
           ,[id_brand]
           ,[id_origin]
           ,[id_manufacture]
           ,[id_color]
           ,[id_type]
           ,[id_size]
           ,[id_resolution]
           ,[active])
     VALUES
           ('SP03'
           ,N'Sản phẩm 3'
           ,20000
           ,16000
           ,80
           ,2
           ,'1'
           ,'1'
           ,'1'
           ,'1'
           ,'1'
           ,'1'
           ,'1'
           ,1)
GO
select * from product

INSERT INTO [dbo].[bill_product]
           ([id_bill]
           ,[id_product]
           ,[quantity]
           ,[price]
           ,[status])
     VALUES
           (2
           ,1
           ,5
           ,500
           ,1)
GO

INSERT INTO [dbo].[bill_product]
           ([id_bill]
           ,[id_product]
           ,[quantity]
           ,[price]
           ,[status])
     VALUES
           (2
           ,2
           ,5
           ,500
           ,1)
GO