#### 온라인 쇼핑몰 구축 (개인 프로젝트)

## 오라클 12C 사용자 생성
````sql
alter session set "_ORACLE_SCRIPT"=true;
CREATE USER shopper IDENTIFIED BY 12345;
alter session set "_ORACLE_SCRIPT"=false;

GRANT CONNECT,RESOURCE,UNLIMITED TABLESPACE, DBA TO shopper;
GRANT CREATE SESSION TO shopper;
ALTER USER shopper DEFAULT TABLESPACE USERS;
ALTER USER shopper TEMPORARY TABLESPACE TEMP;
````

## 테이블 생성
````sql
CREATE TABLE manager (
    id NUMBER PRIMARY KEY,
    username VARCHAR2(50) NOT NULL,
    password VARCHAR2(50) NOT NULL,
    email VARCHAR2(50) NOT NULL
);


CREATE TABLE category (
    id NUMBER PRIMARY KEY,
    root_category VARCHAR2(50) NOT NULL,
    sub_category VARCHAR2(50)
);

CREATE TABLE product (
    id NUMBER PRIMARY KEY,
    category_id NUMBER,
    name VARCHAR2(100) NOT NULL,
    description VARCHAR2(4000) NOT NULL,
    price NUMBER NOT NULL,
    CONSTRAINT FK_PRODUCT_CATEGORY
        FOREIGN KEY(category_id)
        REFERENCES category(id)
        ON DELETE SET NULL
);

CREATE TABLE productimage (
    id NUMBER PRIMARY KEY,
    product_id NUMBER NOT NULL,
    addr VARCHAR2(100) NOT NULL,
    CONSTRAINT FK_PRODUCTIMAGE_PRODUCT
        FOREIGN KEY(product_id)
        REFERENCES product(id)
        ON DELETE CASCADE
);

CREATE TABLE customer (
    id NUMBER PRIMARY KEY,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(30) NOT NULL,
    address VARCHAR(100) NOT NULL,
    registerDate TIMESTAMP NOT NULL
);

CREATE TABLE review (
    id NUMBER PRIMARY KEY,
    product_id NUMBER,
    customer_id NUMBER,
    rating NUMBER NOT NULL CHECK (rating <= 5),
    title VARCHAR2(150) NOT NULL,
    commentary VARCHAR2(1000) NOT NULL,		-- comment도 예약어여서 사용 불가
    write_time TIMESTAMP NOT NULL,
    CONSTRAINT FK_PRODUCT_REVIEW
        FOREIGN KEY(product_id)
        REFERENCES product(id)
        ON DELETE SET NULL,
    CONSTRAINT FK_CUSTOMER_REVIEW
        FOREIGN KEY(customer_id)
        REFERENCES customer(id)
        ON DELETE SET NULL
);

CREATE TABLE orders (
    id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    orders_date TIMESTAMP NOT NULL,
    shipping_address VARCHAR2(100) NOT NULL,
    recipient_name VARCHAR2(15) NOT NULL,
    recipient_phone VARCHAR(15) NOT NULL,
    payment VARCHAR2(30) NOT NULL,
    total NUMBER,
    status VARCHAR(30),
    CONSTRAINT FK_CUSTOMER_ORDERS
        FOREIGN KEY(customer_id)
        REFERENCES customer(id)
        ON DELETE CASCADE
);

CREATE TABLE item (
    id NUMBER PRIMARY KEY,
    orders_id NUMBER NOT NULL,
    product_id NUMBER NOT NULL,
    quantity NUMBER NOT NULL,
    unit_price NUMBER NOT NULL,
    total NUMBER,
    CONSTRAINT FK_ORDERS_ITEM
        FOREIGN KEY(orders_id)
        REFERENCES orders(id)
        ON DELETE CASCADE,
    CONSTRAINT FK_PRODUCT_ITEM
        FOREIGN KEY(product_id)
        REFERENCES product(id)
        ON DELETE SET NULL
);
````

## 샘플 데이터 입력
````sql
INSERT INTO CATEGORY VALUES(1,'의류/잡화','여성의류');
INSERT INTO CATEGORY VALUES(2,'의류/잡화','남성의류');
INSERT INTO CATEGORY VALUES(3,'의류/잡화','아동복');
INSERT INTO CATEGORY VALUES(4,'의류/잡화','신발');
INSERT INTO CATEGORY VALUES(5,'의류/잡화','가방');
INSERT INTO CATEGORY VALUES(6,'의류/잡화','아웃도어');

INSERT INTO CATEGORY VALUES(7,'식품/생필품','양곡류');
INSERT INTO CATEGORY VALUES(8,'식품/생필품','과일');
INSERT INTO CATEGORY VALUES(9,'식품/생필품','견과류');
INSERT INTO CATEGORY VALUES(10,'식품/생필품','육류');
INSERT INTO CATEGORY VALUES(11,'식품/생필품','생선');
INSERT INTO CATEGORY VALUES(12,'식품/생필품','반찬');

INSERT INTO CATEGORY VALUES(13,'생활용품','가구');
INSERT INTO CATEGORY VALUES(14,'생활용품','조명');
INSERT INTO CATEGORY VALUES(15,'생활용품','주방');
INSERT INTO CATEGORY VALUES(16,'생활용품','문구');
INSERT INTO CATEGORY VALUES(17,'생활용품','가구');

INSERT INTO CATEGORY VALUES(18,'컴퓨터/가전','노트북');
INSERT INTO CATEGORY VALUES(19,'컴퓨터/가전','데스크톱');
INSERT INTO CATEGORY VALUES(20,'컴퓨터/가전','모니터');
INSERT INTO CATEGORY VALUES(21,'컴퓨터/가전','PC주변기기');
INSERT INTO CATEGORY VALUES(22,'컴퓨터/가전','냉장고');
INSERT INTO CATEGORY VALUES(23,'컴퓨터/가전','TV');
INSERT INTO CATEGORY VALUES(24,'컴퓨터/가전','에어컨');

INSERT INTO CATEGORY VALUES(25,'스포츠용품','자전거');
INSERT INTO CATEGORY VALUES(26,'스포츠용품','골프');
INSERT INTO CATEGORY VALUES(27,'스포츠용품','등산');
INSERT INTO CATEGORY VALUES(28,'스포츠용품','헬스');
INSERT INTO CATEGORY VALUES(29,'스포츠용품','요가');

INSERT INTO CATEGORY VALUES(30,'여행/도서/티켓','여행/항공권');
INSERT INTO CATEGORY VALUES(31,'여행/도서/티켓','도서/음반');
INSERT INTO CATEGORY VALUES(32,'여행/도서/티켓','공연티켓');
````


## 결제모듈
[아임포트](https://www.iamport.kr/)