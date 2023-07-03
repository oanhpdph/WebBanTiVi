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

  -- sản phẩm
CREATE TABLE products
  (
     id BIGINT IDENTITY(1, 1) PRIMARY KEY
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

