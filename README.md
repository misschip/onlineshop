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
    phone VARCHAR(15) NOT NULL,
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
    image1 VARCHAR2(260),			-- windows 10의 file path max limit = 260
    image2 VARCHAR2(260),			-- productimage 테이블 따로 두는 대신 product 테이블로 넣음
    image3 VARCHAR2(260),
    CONSTRAINT FK_PRODUCT_CATEGORY
        FOREIGN KEY(category_id)
        REFERENCES category(id)
        ON DELETE SET NULL
);


CREATE TABLE customer (
    id NUMBER PRIMARY KEY,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(30) NOT NULL,
    address VARCHAR(300) NOT NULL,
    registerDate TIMESTAMP NOT NULL
    zipNo NUMBER(5,0)
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
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(30) NOT NULL,
    address VARCHAR2(300) NOT NULL,
    zipNo NUMBER(5,0),
    recipient_name VARCHAR2(15) NOT NULL,
    payment VARCHAR2(30) NOT NULL,
    total NUMBER,
    status VARCHAR(30),
    CONSTRAINT FK_CUSTOMER_ORDERS
        FOREIGN KEY(customer_id)
        REFERENCES customer(id)
        ON DELETE CASCADE
);


CREATE TABLE payresult (
    id NUMBER PRIMARY KEY,
    ORDERS_ID NUMBER NOT NULL,
    IMP_UID VARCHAR(30),
    MERCHANT_UID VARCHAR(30),
    PAID_AMOUNT NUMBER,
    APPLY_NUM VARCHAR(30),
    CONSTRAINT FK_PAYRESULT_ORDERS
        FOREIGN KEY(ORDERS_ID)
        REFERENCES ORDERS(ID)
        ON DELETE CASCADE
);




CREATE TABLE item (
    id NUMBER PRIMARY KEY,
    orders_id NUMBER NOT NULL,
    product_id NUMBER NOT NULL,
    quantity NUMBER NOT NULL,
    unit_price NUMBER NOT NULL,
--    total NUMBER,				-- 이 필드 삭제
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

## 시퀀스 생성
````sql
CREATE SEQUENCE product_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE category_seq
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE customer_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE item_seq
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE manager_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE orders_seq
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE review_seq
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE payresult_seq
    START WITH 1
    INCREMENT BY 1;
    
````


## 샘플 데이터 입력

### Category 테이블

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
-- INSERT INTO CATEGORY VALUES(17,'생활용품','가구'); -- 13과 중복 삭제

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

### Customer 테이블

````sql
INSERT INTO customer values (1,'ssar','1234','010-1111-2222','ssar@nate.com','부산시 중구',sysdate);
INSERT INTO customer values (2,'ssarmango','1234','010-2222-2222','ssarmango@nate.com','부산시 남구',sysdate);
INSERT INTO customer values (3,'ssardo','1234','010-1111-3333','ssardo@nate.com','부산시 해운대구',sysdate);
INSERT INTO customer values (4,'ssari','1234','010-3333-2222','ssari@nate.com','부산시 사하구',sysdate);
INSERT INTO customer values (5,'hong','1234','010-3333-3333','hong@nate.com','부산시 북구',sysdate);
INSERT INTO customer values (6,'kim','1234','010-5555-1111','kim@nate.com','부산시 진구',sysdate);
INSERT INTO customer values (7,'lim','1234','010-6666-2222','lim@nate.com','김해시',sysdate);
INSERT INTO customer values (8,'choi','1234','010-7777-2222','choi@nate.com','양산시',sysdate);
INSERT INTO customer values (9,'park','1234','010-1111-8888','park@nate.com','경상남도',sysdate);
````

### Manager 테이블

````sql
INSERT INTO manager values (1,'admin2','1234','010-1111-2222','admin2@nate.com');
INSERT INTO manager values (2,'kimbujang','1234','010-2222-2222','kimbujang@nate.com');
INSERT INTO manager values (3,'chaderi','1234','010-1111-3333','chaderi@nate.com');
INSERT INTO manager values (4,'leegajang','1234','010-3333-2222','leegajang@nate.com');
INSERT INTO manager values (5,'parksajang','1234','010-3333-3333','parksajang@nate.com');
INSERT INTO manager values (6,'hanbujang','1234','010-5555-1111','hanbujang@nate.com');
INSERT INTO manager values (7,'kimjuim','1234','010-6666-2222','kimjuim@nate.com');
INSERT INTO manager values (8,'bekjuim','1234','010-7777-2222','bekjuim@nate.com');
INSERT INTO manager values (9,'parkjuim','1234','010-1111-8888','parkjuim@nate.com');
````

### Product 테이블

````sql
INSERT INTO product VALUES (1,2,'티셔츠','부드러우면서 베이직한 핏에 다양한 사이즈 선택이 가능',100);
INSERT INTO product VALUES (2,8,'성주 꿀참외','우등생 성주참외 산지직송 정품 로얄과 2.5kg (7~12개)',150);
INSERT INTO product VALUES (3,8,'소양강 찰토마토','소양강 찰토마토 10kg 쥬스용 못난이 강원도 산지직송',110);
INSERT INTO product VALUES (4,9,'볶음땅콩','볶음땅콩 3.75kg 알큰사이즈 햇 19년산',120);
INSERT INTO product VALUES (5,9,'호두','캘리포니아 햇 호두 (A급) 1kg /2019년산 무료배송',100);
INSERT INTO product VALUES (6,16,'샤프','자바 0.5mm 칼라 제도샤프',150);
INSERT INTO product VALUES (7,16,'연필깎이','[티티] 하이샤파 연필깎이 KI-200 기차모양 레트로감성',150);
INSERT INTO product VALUES (8,18,'LG 노트북','[LG전자] LG i5 I7 외 풀스펙 SSD빠른게이밍 사무노트북',200);
INSERT INTO product VALUES (9,18,'LG 그램 노트북','LG 그램17 17Z90N-VA76K 222만구매 인텔i7 고사양 노트북',170);
INSERT INTO product VALUES (10,22,'삼성전자 김치냉장고','삼성전자 김치플러스 상품명 뚜껑형김치냉장고 RP22N3111S9 221L 인증점 으뜸효율',150);
INSERT INTO product VALUES (11,22,'삼성 냉장고','[삼성전자] 삼성냉장고 160리터 소형/미니/원룸/사업자전용',120);
INSERT INTO product VALUES (12,25,'케이투 자전거','상품명2019 K2BIKE MTB자전거 KMT26GS 26인치 21단',140);
````

### Orders, Item 테이블

````sql
INSERT INTO orders VALUES (1,6,TIMESTAMP '2020-04-15 10:05:00','부산시 진구','kim','010-5555-1111','신용카드',200,'배송완료');
INSERT INTO item VALUES (1,1,8,1,200);
INSERT INTO orders VALUES (2,1,TIMESTAMP '2020-05-01 14:23:00','부산시 중구','ssar','010-1111-2222','신용카드',450,'배송완료');
INSERT INTO item VALUES (2,2,6,2,150);
INSERT INTO item VALUES (3,2,7,1,150);
INSERT INTO orders VALUES (3,5,TIMESTAMP '2020-05-06 19:45:00','경기도 양평','장보고','010-7777-7777','계좌이체',300,'배송완료');
INSERT INTO item VALUES (4,3,2,2,150);
INSERT INTO orders VALUES (4,9,TIMESTAMP '2020-05-21 16:31:00','경상남도','park','010-1111-8888','휴대폰결제',380,'배송완료');
INSERT INTO item VALUES (5,4,2,1,150);
INSERT INTO item VALUES (6,4,3,1,110);
INSERT INTO item VALUES (7,4,4,1,120);
````

## 결제모듈
[아임포트](https://www.iamport.kr/)