CREATE DATABASE webbantivi
go

USE webbantivi
go

-- lý do trả hàng
CREATE TABLE reason
  (
     id     INT IDENTITY PRIMARY KEY,
     reason NVARCHAR(max) -- lý do trả hàng là ?
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
     id BIGINT IDENTITY(1, 1) PRIMARY KEY
	 id_product     BigInt REFERENCES products(id) ,-- id sản phẩm
     image_product VARCHAR(max)
  )

-- khách hàng
CREATE TABLE customer
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY
  )

--hóa đơn
CREATE TABLE bill
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY
  )
  

-- phiếu trả hàng
CREATE TABLE returns_product
  (
     id          BIGINT IDENTITY(1, 1) PRIMARY KEY,
     id_customer BIGINT references customer(id),
     id_bill     BIGINT  references bill(id),
     id_product  BIGINT  references products(id),
     create_date DATETIME,
     quantity    INT,
     status      INT -- trạng thái phiếu trả hàng
  )

  -- chi tiết lý do trả hàng
  CREATE TABLE returns_reason
  (
     id_reason  int references reason(id),
     id_returns BIGINT  references returns_product(id),
     note       NVARCHAR(200) ,
	 primary key (id_reason, id_returns)
  ) 

-- ảnh hoặc video đính kèm trả hàng
CREATE TABLE image_or_video
  (
     id             UNIQUEIDENTIFIER DEFAULT Newid() PRIMARY KEY,
     id_returns     BigInt REFERENCES returns_product(id) ,-- id phiếu trả hàng
     image_or_video VARCHAR(max)
  )

