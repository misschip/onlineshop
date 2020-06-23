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
    name VARCHAR2(50) NOT NULL
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

## 결제모듈
[아임포트](https://www.iamport.kr/)