CREATE DATABASE webbantivi

go

USE webbantivi

go

-- lý do trả hàng
CREATE TABLE reason
  (
     id     INT IDENTITY PRIMARY KEY,
     reason NVARCHAR(max)-- lý do trả hàng là ?
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
     id              BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code            VARCHAR(10),
     namemanufacture NVARCHAR(max)
  )

-- màu sắc
CREATE TABLE color
  (
     id        BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code      VARCHAR(10),
     namecolor NVARCHAR(max)
  )

-- Loại
CREATE TABLE category
  (
     id           BIGINT IDENTITY(1, 1) PRIMARY KEY,
     code         VARCHAR(10),
     namecategory NVARCHAR(max)
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
CREATE TABLE products
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
     id_category    BIGINT REFERENCES category(id),
     id_resolution  BIGINT REFERENCES resolution(id),
     id_size        BIGINT REFERENCES size(id),
  )

-- hình ảnh
CREATE TABLE images
  (
     id            BIGINT IDENTITY(1, 1) PRIMARY KEY,
     id_product    BIGINT REFERENCES products(id),-- id sản phẩm
     image_product VARCHAR(max)
  )

CREATE TABLE adress
  (
     code         UNIQUEIDENTIFIER DEFAULT Newid() PRIMARY KEY,
     NAME         NVARCHAR(50),
     describe     NVARCHAR(max),
     city         NVARCHAR(50),
     district     NVARCHAR(50),
     ward         NVARCHAR(50),
     street       NVARCHAR(50),
     codecustomer BIGINT REFERENCES customer(id)
  )

go

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

go

--hóa đơn
CREATE TABLE bill
  (
     id                BIGINT IDENTITY(1, 1) PRIMARY KEY,
     date_founded      DATE,
     the_establishment NVARCHAR(50),
     note              NVARCHAR(max),
     date_payment      DATE,
     status            INT,
     buyer             BIGINT REFERENCES customer(id)
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

-- chi tiết lý do trả hàng
CREATE TABLE returns_reason
  (
     id_reason  INT REFERENCES reason(id),
     id_returns BIGINT REFERENCES returns_product(id),
     note       NVARCHAR(200),
     PRIMARY KEY (id_reason, id_returns)
  )

-- ảnh hoặc video đính kèm trả hàng
CREATE TABLE image_or_video
  (
     id             UNIQUEIDENTIFIER DEFAULT Newid() PRIMARY KEY,
     id_returns     BIGINT REFERENCES returns_product(id),
     -- id phiếu trả hàng
     image_or_video VARCHAR(max)
  )

CREATE TABLE promotiondetails
  (
     id          BIGINT IDENTITY(1, 1) PRIMARY KEY,
     id_product  BIGINT REFERENCES products(id),
     startdate   DATE,
     enddate     DATE,
     reducedform BIT,
     status      INT,
  )

go

CREATE TABLE promotion
  (
     id                    BIGINT IDENTITY(1, 1) PRIMARY KEY,
     id_promotiondetails   BIGINT REFERENCES promotiondetails(id),
     reducedvalue          MONEY,
     maximumreductionvalue MONEY,
  )

go

CREATE TABLE membershipcard
  (
     id                VARCHAR(50) PRIMARY KEY,
     color             NVARCHAR(10),
     card_type         NVARCHAR(10),
     release_date      DATE,
     cancellation_date DATE,
     status            INT,
     owner             BIGINT REFERENCES customer(id)
  )

go

CREATE TABLE exchange_card
  (
     id             BIGINT IDENTITY(1, 1) PRIMARY KEY,
     id_product_old BIGINT REFERENCES products(id),
     id_product_new BIGINT,
     id_customer    BIGINT REFERENCES customer (id),
     datestart      DATE,
     stattus        INT,
     describe       NVARCHAR(30),
     id_reason      BIGINT REFERENCES reason_exchange(id)
  )

CREATE TABLE image_or_video_exchange
  (
     id             UNIQUEIDENTIFIER DEFAULT Newid() PRIMARY KEY,
     id_exchange     BIGINT REFERENCES exchange_card(id),

     image_or_video VARCHAR(max)
  )

CREATE TABLE reason_exchange
  (
     id          BIGINT IDENTITY(1, 1) PRIMARY KEY,
     name_reason NVARCHAR(100)
  ) 