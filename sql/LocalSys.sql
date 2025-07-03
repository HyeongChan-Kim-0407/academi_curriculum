-- 주석
/*
범위
주석
*/

-- 명령어 SQL 작성 >> F9키로 전송 오른쪽 위의 탭에서 선택한 데이터 베이스로 전송
-- F5 키 : 전체 명령어 전송 << 사용 x

-- [LOCALHOST(내 자리) SYS 계정]
-- 계정 생성

--CREATE USER 권한아이디 IDENTIFIED BY 비밀번호;
CREATE USER C##KHC IDENTIFIED BY "1234";

-- 권한 부여
--GRANT 부여할 권한 TO 아이디;
GRANT CREATE SESSION TO C##KHC;

-- 권한 회수
--REVOKE 회수할 권한 FROM 아이디;
REVOKE CREATE SESSION FROM C##KHC;

-- 계정 삭제
--DROP USER 삭제할 아이디
DROP USER C##KHC;

-- 공통사용자 규칙 해제
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
--LOCAL DB에 이니셜로 계정 생성
CREATE USER KHC IDENTIFIED BY "1234";
-- 계정에 DBA (DataBase Administrator) 권한 부여
GRANT DBA TO KHC;

/*
CREATE  데이터 입력 : INSERT
READ    데이터 조회 : SELECT
UPDATE  데이터 수정 : UPDATE
DELETE  데이터 삭제 : DELETE
*/  

-- 테이블 생성
--CREATE TABLE 테이블이름 (컬럼명1 데이터타입, 컬럼명2 데이터타입);
CREATE TABLE TBL01( -- TBL01 = 이름
    NAME NVARCHAR2 (20), -- NAME : 컬럼 명칭, NVARCHAR2() : 데이터 타입(문자 갯수는 ()까지)
    PHONE NVARCHAR2 (13) -- PHONE : 컬럼 명칭, NVARCHAR2() : 데이터 타입(문자 갯수는 ()까지)
);

-- 데이터 입력
--INSERT INTO 데이터를 입력할 테이블이름( 컬럼명1, 컬럼명2 ) VALUJES(컬럼명1에 넣을 값, 컬럼명2에 넣을 값);
INSERT INTO tbl01 (NAME, PHONE) VALUES('김형찬', '010-3851-7909');
INSERT INTO tbl01 (NAME, PHONE) VALUES('SQL', '010-1111-1111');
INSERT INTO tbl01 (NAME, PHONE) VALUES('ORACLE', '010-2222-2222');

-- 데이터 조회
--SELECT 컬럼명(, 컬럼명2) FROM 테이블명; * : 모든 컬럼
SELECT * FROM TBL01;

-- 데이터 수정
--UPDATE 테이블명 SET 컬럼명 = 수정할값 WHERE 조건;
UPDATE tbl01
SET PHONE = '010-1111-1111'
WHERE NAME = 'SQL';

-- 데이터 삭제
--DELETE FROM 테이블명 << 테이블 내 모든 데이터 삭제
--WHERE 삭제할 조건

DELETE FROM TBL01
WHERE NAME = 'ORACLE';

-- 테이블
CREATE TABLE STUDENT(
    STUID NUMBER,           --학생ID
    STUNAME NVARCHAR2(5),   --학생이름
    STUBIRTH DATE           --생년월일
);

/* NUMBER : 숫자형 데이터 타입
NUMBER(3)     : 최대 3자리 숫자
NUMBER(3,2)   : 최대 3자리 숫자 중 소수점 2자리 123.45 < X ex) 1.23
*/

CREATE TABLE NUMBER_TBL(
    NUM1 NUMBER,        --제한 없음
    NUM2 NUMBER(3),     --정수 3자리
    NUM3 NUMBER(3,2)    --3자리 숫자 중 소수점 2자리
); 

-- 데이터 입력
--INSERT INTO 테이블 이름 (컬럼명) VALUES (값);
INSERT INTO number_tbl(NUM1, NUM2, NUM3)
VALUES(12345, 123, 1.23);

SELECT * FROM number_tbl;

INSERT INTO number_tbl(NUM1, NUM2, NUM3)
VALUES(12345, 1234, 1.23);
-- 오류발생 NUM2 지정된 크기 초과
INSERT INTO number_tbl(NUM1, NUM2, NUM3)
VALUES(12345, 123, 1.234);
-- 소수점의 경우 지정된 크기 초과시 초과된 소수점 버리고 삽입
SELECT * FROM number_tbl;

/*
-- 문자형 (고정형, 가변형) - 고정형 : 자릿수 고정 / 조회속도에 이점, 가변형 : 자릿수 가변 / 저장용량에 이점
CHAR(숫자) : 고정형 (저장되는 용량(바이트))
VARCHAR2(숫자) : 가변형 (최대 저장되는 용량(바이트))

NCHAR(숫자) : 고정형 (저장되는 글자수)
NVARCHAR2(숫자) : 가변형 (최대 글자수)
*/

CREATE TABLE CHAR_TBL(
    COL1 NVARCHAR2 (5),
    COL2 VARCHAR2 (5)
);

INSERT INTO char_tbl(COL1, COL2)
VALUES('ABCDE', 'ABCDE');

INSERT INTO char_tbl(COL1, COL2)
VALUES('가나다라마', 'ABCDE');

INSERT INTO char_tbl(COL1, COL2)
VALUES('ABCDE', '가나다라마');
-- SQL 오류: ORA-12899: "KHC"."CHAR_TBL"."COL2" 열에 대한 값이 너무 큼(실제: 15, 최대값: 5)

-- DATE : 날짜형 데이터 타입 (연/월/일, 시:분:초, 설정에 따라 ms가능)
CREATE TABLE DATE_TBL(
    DATECOL DATE
);

-- 날짜형 데이터 삽입
INSERT INTO DATE_TBL(DATECOL)
VALUES( '2025-05-14' );
SELECT * FROM DATE_TBL;
INSERT INTO DATE_TBL(DATECOL)
VALUES( '2025-05-14 18:38:30' );
SELECT * FROM DATE_TBL;



/*
TO_DATE() : 문자를 날짜로 변환 , TO_CHAR() : 날짜를 문자로 변환
TO_DATE('문자', '날짜형식')

TO_CHAR( 날짜데이터, '변환할형식')
*/

INSERT INTO DATE_TBL(DATECOL)
VALUES( TO_DATE('2025-05-15 15:15:15', 'YYYY-MM-DD HH24:MI:SS') );
SELECT * FROM DATE_TBL;

SELECT *
FROM DATE_TBL
WHERE TO_CHAR ( DATECOL, 'DD') = 14;

CREATE TABLE STUDENT(
    STUID NUMBER,           --학생ID
    STUNAME NVARCHAR2(5),   --학생이름
    STUBIRTH DATE           --생년월일
);

DESC STUDENT; -- 테이블 구성요소 확인

SELECT *
FROM STUDENT;

INSERT INTO STUDENT(STUID, STUNAME, STUBIRTH)
VALUES(1, '김형찬', '2000-04-07');
SELECT *
FROM STUDENT; 
INSERT INTO STUDENT(STUNAME, STUID, STUBIRTH)
VALUES('김형찬',1 ,'2000-04-07');

INSERT INTO STUDENT(STUID)
VALUES(2);

INSERT INTO STUDENT(STUNAME)
VALUES('오라클');

DROP TABLE STUDENT;

CREATE TABLE MEMBER(
    MEMBERID NVARCHAR2(10),    --회원 아이디
    MEMBERPW NVARCHAR2(10),    --회원 비밀번호
    REGDATE DATE,              --가입일
    MEMBERPHONE NVARCHAR2(13)  --전화번호
);

-- 회원 정보 입력
INSERT INTO MEMBER(MEMBERID, MEMBERPW, REGDATE, MEMBERPHONE)
VALUES('ID01', '1234', SYSDATE, '010-1111-1111');
-- 모든 회원 정보 조회
SELECT * FROM MEMBER;
DELETE FROM MEMBER;

/*
제약조건 ( CONSTRAINT )
NOT NULL : NULL값 입력 방지
UNIQUE : 중복값 입력 방지
CHECK : 입력값 조건
DEFAULT : 기본값 설정

** 중요 제약조건 **
PRIMARY KEY : NOT NULL + UNIQUE
FOREIGN KEY : 외래키 > 테이블간 관계 설정
*/

-- 중복값 입력 방지 ( UNIQUE )
-- MEMBER 테이블의 MEMBERID(회원아이디) 컬럼에 UNIQUE 제약조건 추가
--ALTER TABLE 테이블명 ADD CONSTRAINTS 제약조건식별자 제약유형(컬럼명);
ALTER TABLE MEMBER 
ADD CONSTRAINTS UK_MEMBER_ID UNIQUE(MEMBERID);
--ORA-02299: 제약 (KHC.UK_MEMBER_ID)을 사용 가능하게 할 수 없음 - 중복 키가 있습니다
INSERT INTO MEMBER(MEMBERID, MEMBERPW, REGDATE, MEMBERPHONE)
VALUES('ID01', '1234', SYSDATE, '010-1111-1111');
--ORA-00001: 무결성 제약 조건(KHC.UK_MEMBER_ID)에 위배됩니다 
INSERT INTO MEMBER(MEMBERPW, REGDATE, MEMBERPHONE)
VALUES('1234', SYSDATE, '010-2222-2222');
INSERT INTO MEMBER(MEMBERPW, REGDATE, MEMBERPHONE)
VALUES('1234', SYSDATE, '010-3333-3333');

SELECT * FROM MEMBER;

DESC MEMBER;
-- NULL값 입력 방지 (NOT NULL)
-- MEMBER 테이블의 MEMBERID(회원아이디) 컬럼에 NOT NULL 지정
--ALTER TABLE 테이블명 MODIFY 컬럼명 NOT NULL;
ALTER TABLE MEMBER MODIFY MEMBERID NOT NULL;
--ORA-02296: (KHC.) 사용으로 설정 불가 - 널 값이 발견되었습니다.
INSERT INTO MEMBER(MEMBERPW, REGDATE, MEMBERPHONE)
VALUES('1234', SYSDATE, '010-3333-3333');
--ORA-01400: NULL을 ("KHC"."MEMBER"."MEMBERID") 안에 삽입할 수 없습니다

DROP TABLE MEMBER;

-- 테이블 생성시 제약조건 추가
CREATE TABLE MEMBER(
    MEMBERID NVARCHAR2(10) NOT NULL UNIQUE,    --회원 아이디
    MEMBERPW NVARCHAR2(10),    --회원 비밀번호
    REGDATE DATE,              --가입일
    MEMBERPHONE NVARCHAR2(13) UNIQUE  --전화번호
);
INSERT INTO MEMBER(MEMBERPW, REGDATE, MEMBERPHONE)
VALUES('1234', SYSDATE, '010-3333-3333');
--ORA-01400: NULL을 ("KHC"."MEMBER"."MEMBERID") 안에 삽입할 수 없습니다

-- MEMBERID가 같은 데이터 입력 시도
INSERT INTO MEMBER(MEMBERID, MEMBERPW, REGDATE, MEMBERPHONE)
VALUES('ID01', '1234', SYSDATE, '010-1111-1111');
--ORA-00001: 무결성 제약 조건(KHC.SYS_C008318)에 위배됩니다

DROP TABLE MEMBER;

-- 컬럼 명시와 동시에 제약조건, 제약조건식별자 추가
CREATE TABLE MEMBER(
    MEMBERID NVARCHAR2(10) CONSTRAINT UK_MEMBER_ID UNIQUE NOT NULL,
    MEMBERPW NVARCHAR2(10),
    REGDATE DATE,
    MEMBERPHONE NVARCHAR2(13) UNIQUE
);

-- 컬럼 명시 후 제약조건 명시
CREATE TABLE MEMBER( -- NOT NULL은 컬럼 옆에서만 명시 가능
    MEMBERID NVARCHAR2(10) NOT NULL,
    MEMBERPW NVARCHAR2(10),
    REGDATE DATE,
    MEMBERPHONE NVARCHAR2(13),
    CONSTRAINT UK_MEMBER_ID UNIQUE
    CONSTRAINT UK_MEMBER_PHONE UNIQUE(MEMBERPHONE)
);
-- MEMBERID 컬럼에 NOT NULL 지정
-- MEMBERID 컬럼에 UK_MEMBER_ID 이름의 UNIQUE 제약 부여
-- MEMBERPHONE 컬럼에 UK_MEMBER_PHONE 이름의 UNIQUE 제약 부여

-- (MEMBERID),제약조건 해제
-- MEMBERID 컬럼에 NULL 허용
--ALTER TABLE MEMBER MODIFY 테이블명 NULL;
ALTER TABLE MEMBER
MODIFY MEMBERID NULL;
-- MEMBERID 컬럼에 UNIQUE 제약 해제
--ALTER TABLE 테이블명 DROP CONSTRAINT 제약조건식별자;
ALTER TABLE MEMBER
DROP CONSTRAINT UK_MEMBER_ID;
-- MEMBERPHONE 컬럼에 UNIQUE 제약 해제
ALTER TABLE MEMBER
DROP CONSTRAINT UK_MEMBER_PHONE;

/* PRIMARY KEY : 기본 키 ( UNIQUE + NOT NULL ) */ -- 테이블당 딱 한 번만 사용 가능 (여러 컬럼을 묶어서 부여 가능)
-- PRIMARY KEY를 기반으로 테이블 간의 관계도를 구성

DROP TABLE MEMBER;

CREATE TABLE MEMBER(
    MEMBERID NVARCHAR2(10),    
    MEMBERPW NVARCHAR2(10),    
    REGDATE DATE,              
    MEMBERPHONE NVARCHAR2(13)  
);
-- MEMBERID 컬럼에 PRIMARY KEY 부여
--ALTER TABLE 테이블명 ADD CONSTRAINTS 식별자 제약조건(컬럼명);
ALTER TABLE MEMBER ADD CONSTRAINTS PK_MEMBER_ID PRIMARY KEY(MEMBERID);
-- MEMBERID PRIMARY KEY 부여
DESC MEMBER;
-- MEMBERID NOT NULL 부여확인
INSERT INTO MEMBER(MEMBERID, MEMBERPW, REGDATE, MEMBERPHONE)
VALUES('TEST1', '1111', SYSDATE, '010-1111-1111');
-- ID가 같은 데이터 입력 시도
INSERT INTO MEMBER(MEMBERID, MEMBERPW, REGDATE, MEMBERPHONE)
VALUES('TEST1', '2222', SYSDATE, '010-2222-2222');
-- ORA-00001: 무결성 제약 조건(KHC.PK_MEMBER_ID)에 위배됩니다

-- MEMBERPW 컬럼에 PRIMARY KEY 부여 시도
ALTER TABLE MEMBER ADD CONSTRAINTS PK_MEMBER_PW PRIMARY KEY(MEMBERPW);
--ORA-02260: 테이블에는 하나의 기본 키만 가질 수 있습니다.

-- 복합 PRIMARY KEY
CREATE TABLE PKTBL(
    COL1 NUMBER,
    COL2 NUMBER,
    COL3 NVARCHAR2(5)
);

-- (COL1, COL2) 컬럼에 PRIMARY KEY 부여
ALTER TABLE PKTBL ADD CONSTRAINTS PK_PKTBL PRIMARY KEY(COL1,COL2);
DESC PKTBL;

INSERT INTO PKTBL -- 컬럼 명시 X = 모든 컬럼 명시
VALUES(1, 1, '복합');

-- COL1, COL2 동시 중복 입력 시도
INSERT INTO PKTBL
VALUES(1, 1, '복합');
--ORA-00001: 무결성 제약 조건(KHC.PK_PKTBL)에 위배됩니다

INSERT INTO PKTBL
VALUES(1, 2, '복합');

INSERT INTO PKTBL
VALUES(2, 1, '복합');

SELECT * FROM PKTBL;

/* DEFAULT : 기본값 지정 */ -- 컬럼 속성 부여
DROP TABLE MEMBER;
CREATE TABLE MEMBER(
    MEMBERID NVARCHAR2(10),    
    MEMBERPW NVARCHAR2(10),    
    REGDATE DATE,              
    MEMBERPHONE NVARCHAR2(13)  
);

INSERT INTO MEMBER (MEMBERID, MEMBERPW)
VALUES( 'TEST1' );
--SQL 오류: ORA-00947: 값의 수가 충분하지 않습니다
INSERT INTO MEMBER (MEMBERID, MEMBERPW)
VALUES( 'TEST1' , '1111', SYSDATE );
--SQL 오류: ORA-00913: 값의 수가 너무 많습니다

-- DEFAULT 설정 전
-- REGDATE, MEMBERPHONE 데이터 없이 입력
INSERT INTO MEMBER (MEMBERID, MEMBERPW)
VALUES( 'TEST1', '1111' );
SELECT * FROM MEMBER;
-- MEMBER 테이블의 MEMBERPHONE 컬럼에 DEFAULT '번호없음' 설정
--ALTER TABLE 테이블명 MODIFY 컬럼명 DEFAULT '설정값';
ALTER TABLE MEMBER MODIFY MEMBERPHONE DEFAULT '번호없음';
-- MEMBERPHONE 값 없이 데이터 입력
INSERT INTO MEMBER (MEMBERID, MEMBERPW)
VALUES( 'TEST2', '1111' );
SELECT * FROM MEMBER;

-- MEMBER 테이블의 REGDATE 컬럼에 DEFAULT 'SYSDATE' 설정
ALTER TABLE MEMBER MODIFY REGDATE DEFAULT SYSDATE;
-- MEMBERPHONE, REGDATE 값 없이 데이터 입력
INSERT INTO MEMBER (MEMBERID, MEMBERPW)
VALUES( 'TEST3', '1111' );
SELECT * FROM MEMBER;

/* CHECK : 지정된 조건을 만족하는 값만 입력 허용 */
CREATE TABLE PRODUCT(
    NAME NVARCHAR2(10),
    PRICE NUMBER,
    TYPE NVARCHAR2(2)
);
-- 상품의 가격은 1000원 이상부터 입력 가능
-- PRICE >= 1000
-- PRODUCT 테이블의 PRICE 컬럼에 1000 이상 CHECK 제한 설정
--ALTER TABLE 테이블명 ADD CONSTRAINTS 식별자 CHECK(조건);
ALTER TABLE PRODUCT ADD CONSTRAINTS CK_PRICE CHECK(PRICE >= 1000);

-- PRICE 컬럼에 CK_PRICE 제약조건 설정 후
INSERT INTO PRODUCT
VALUES( '사이다', -1000, '음료' );
--ORA-02290: 체크 제약조건(KHC.CK_PRICE)이 위배되었습니다

-- PRODUCT 테이블의 PRICE 컬럼에 100000 이하 CHECK 제한 설정
ALTER TABLE PRODUCT ADD CONSTRAINTS CK_PRICE_MAX CHECK(PRICE <= 100000);
-- PRICE 컬럼에 CK_PRICE_MAX 제약조건 설정 후
INSERT INTO PRODUCT
VALUES( '사이다', 100000, '음료' );
-- PRICE에 부여된 CHECK 제약 제거
ALTER TABLE PRODUCT DROP CONSTRAINT CK_PRICE;
ALTER TABLE PRODUCT DROP CONSTRAINT CK_PRICE_MAX;
-- PRICE에 부여된 CHECK 제약 제거 후
INSERT INTO PRODUCT
VALUES( '사이다', -1111, '음료' );
SELECT * FROM PRODUCT;
-- PRICE에 1000원 이상 100000원 이하 CHECK 제약 부여
-- PRICE BETWEEN 1000 AND 100000
ALTER TABLE PRODUCT ADD CONSTRAINTS CK_PRICE CHECK(PRICE BETWEEN 1000 AND 100000);
-- PRICE에 CHECK 제약 부여 후
INSERT INTO PRODUCT
VALUES ('사이다', 10000, '음료');
--ORA-02290: 체크 제약조건(KHC.CK_PRICE)이 위배되었습니다
INSERT INTO PRODUCT
VALUES ('사이다', -100001, '음료');
--ORA-02290: 체크 제약조건(KHC.CK_PRICE)이 위배되었습니다

-- TYPE 컬럼은 '식품', '가전'만 입력 가능
ALTER TABLE PRODUCT ADD CONSTRAINTS CK_TYPE CHECK(TYPE IN ( '식품', '가전' ));
-- TYPE 컬럼에 CHECK 제약 부여 후
INSERT INTO PRODUCT
VALUES ('사이다', 10000, '음료');
--ORA-02290: 체크 제약조건(KHC.CK_TYPE)이 위배되었습니다
INSERT INTO PRODUCT
VALUES ('사이다', 10000, '식품');
--1 행 이(가) 삽입되었습니다.

/*
    NOT NULL,
    UNIQUE,
    PRIMARY KEY,
    DEFAULT,
    CHECK
*/

/* ALTER TABLE */
CREATE TABLE ALTERTBL(
    COL1 NUMBER
);

DESC ALTERTBL; -- COL1 NUMBER
-- 테이블에 컬럼 타입 변경
--ALTER TABLE 테이블명 MODIFY 컬럼명 데이터타입;
-- COL1 NUMBER >> COL1 NVARCHAR2(5)
ALTER TABLE ALTERTBL MODIFY COL1 NVARCHAR2(5);
DESC ALTERTBL; -- COL1 NVARCHAR2(5)

-- 테이블에 컬럼 추가
--ALTER TABLE 테이블명 ADD 컬럼명 데이터타입;
-- COL2 DATE 추가
ALTER TABLE ALTERTBL ADD COL2 DATE;
DESC ALTERTBL; -- COL1 NVARCHAR2(5), COL2 DATE

-- 테이블에 컬럼 삭제
--ALTER TABLE 테이블명 DROP COLUMN 컬럼명;
-- COL1 컬럼 삭제
ALTER TABLE ALTERTBL DROP COLUMN COL1;
DESC ALTERTBL; -- COL2 DATE

-- 테이블에 컬럼 이름 변경
--ALTER TABLE 테이블명 RENAME COLUMN 컬럼명 TO 새 컬럼명;
ALTER TABLE ALTERTBL RENAME COLUMN COL2 TO COLDATE;
DESC ALTERTBL; -- COLDATE DATE

-- FOREIGN KEY

-- 상품과 주문
DROP TABLE PRODUCT;
CREATE TABLE PRODUCT(
    PRID NUMBER,            --상품 번호
    PRNAME NVARCHAR2(20),   --상품 이름
    PRICE NUMBER,           --상품 가격
    PRTYPE NCHAR(2)         --상품 종류
);
-- PRODUCT테이블의 PRID 컬럼에 PRIMARY KEY 지정
ALTER TABLE PRODUCT
ADD CONSTRAINT PK_PRODUCT PRIMARY KEY(PRID);
INSERT INTO PRODUCT
VALUES(1, '키보드', 20000, 'PC');
INSERT INTO PRODUCT
VALUES(2, '제로콜라', 2000, '음료');
INSERT INTO PRODUCT
VALUES(3, 'OLED TV', 500000, '가전');
SELECT * FROM PRODUCT;

CREATE TABLE ORDERS(
    ODID NUMBER,            --주문번호
    PRID NUMBER,            --상품번호
    ODCOUNT NUMBER,         --주문수
    ODDATE DATE             --주문일
);
-- 키보드를 1개 2025-05-14 일에 구매
INSERT INTO ORDERS(ODID, PRID, ODCOUNT, ODDATE)
VALUES( 1, 1, 1, '2025-05-14' );
-- OLED TV 2개 시스템시간 
INSERT INTO ORDERS(ODID, PRID, ODCOUNT, ODDATE)
VALUES( 2, 3, 2, SYSDATE );
-- 상품번호 5번을 3개 시스템시간에 주문
INSERT INTO ORDERS(ODID, PRID, ODCOUNT, ODDATE)
VALUES( 3, 5, 3, SYSDATE );
SELECT * FROM ORDERS;
-- PRODUCT테이블과 ORDERS 테이블 의 FOREIGN KEY(외래키) 설정
ALTER TABLE ORDERS
ADD CONSTRAINTS FK_ORDERS_PR FOREIGN KEY(PRID)
REFERENCES PRODUCT(PRID);
-- ORA-02298: 제약 (YKD.FK_ORDERS_PR)을 사용 가능하게 할 수 없음 - 부모 키가 없습니다

-- 주문번호 3번인 레코드 삭제
DELETE FROM ORDERS
WHERE ODID = 3;

-- 상품번호 5번을 3개 시스템시간에 주문
INSERT INTO ORDERS(ODID, PRID, ODCOUNT, ODDATE)
VALUES( 3, 5, 3, SYSDATE );
SELECT * FROM ORDERS;
-- ORA-02291: 무결성 제약조건(YKD.FK_ORDERS_PR)이 위배되었습니다- 부모 키가 없습니다

-- 상품번호 2번을 3개 시스템시간에 주문
INSERT INTO ORDERS(ODID, PRID, ODCOUNT, ODDATE)
VALUES( 3, 2, 3, SYSDATE );

/* FOREIGN KEY(외래키) : 테이블 간의 관계 설정 */
-- 부모테이블의 PRIMARY KEY 를 자식 테이블에서 참조
-- 상품테이블의 PRID 를 ORDERS 테이블에서 참조
-- PRODUCT 테이블의 PRID 컬럼은 NUMBER 타입
-- ORDERS 테이블의 PRID 컬럼은 
-- PRODUCT 테이블의 PRID과 타입이 같아야 한다.
DROP TABLE PRODUCT;
-- ORA-02449: 외래 키에 의해 참조되는 고유/기본 키가 테이블에 있습니다
DROP TABLE ORDERS;
DROP TABLE PRODUCT;

CREATE TABLE PRODUCT(
    PRCODE NCHAR(4),        --상품 코드(PK)
    PRNAME NVARCHAR2(20),   --상품 이름
    PRICE NUMBER,           --상품 가격
    PRTYPE NCHAR(2),        --상품 종류
    CONSTRAINT PK_PRODUCT PRIMARY KEY(PRCODE)
);
DROP TABLE ORDERS;
CREATE TABLE ORDERS(
    ODID NUMBER,            --주문 번호
    PRCODE NCHAR(4),        --상품 코드(FK)
    ODCOUNT NUMBER,         --주문수
    ODDATE DATE,            --주문일
    CONSTRAINT FK_ORDER_PR FOREIGN KEY(PRCODE)
        REFERENCES PRODUCT(PRCODE)
);
-- ORA-02267: 열의 데이터 유형이 참조 열의 데이터 유형과 일치하지 않습니다
INSERT INTO PRODUCT
VALUES('0001', '키보드', 20000, 'PC');
INSERT INTO PRODUCT
VALUES('0002', '제로콜라', 2000, '음료');
INSERT INTO PRODUCT
VALUES('0003', 'OLED TV', 500000, '가전');

-- 키보드를 1개 2025-05-14 일에 구매
INSERT INTO ORDERS(ODID, PRCODE, ODCOUNT, ODDATE)
VALUES( 1, '0001', 1, '2025-05-14' );
-- OLED TV 2개 시스템시간 
INSERT INTO ORDERS(ODID, PRCODE, ODCOUNT, ODDATE)
VALUES( 2, '0003', 2, SYSDATE );
-- 상품번호 2번을 3개 시스템시간에 주문
INSERT INTO ORDERS(ODID, PRCODE, ODCOUNT, ODDATE)
VALUES( 3, '0002', 3, SYSDATE );

SELECT * FROM ORDERS;
CREATE TABLE PARENTTBL(
    PACOL1 NUMBER,
    PACOL2 NUMBER,
    PACOL3 NUMBER,
    PADATE DATE,
    CONSTRAINT PK_PARENT PRIMARY KEY(PACOL1,PACOL2,PACOL3 )
); -- PRIMARY KEY(PACOL1, PACOL2, PACOL3)

CREATE TABLE CHILDTBL(
    CHPACOL1 NUMBER,
    CHPACOL2 NUMBER,
    CHPACOL3 NUMBER,  
    CHDATE DATE,
    CHSTR NVARCHAR2(5),
    CONSTRAINT FK_CHILD_PA FOREIGN KEY(CHPACOL1,CHPACOL2,CHPACOL3)
          REFERENCES PARENTTBL(PACOL1,PACOL2,PACOL3)
);

-- SCOTT계정 - DEPT, EMP
DROP TABLE DEPT;
CREATE TABLE DEPT
       (DEPTNO NUMBER(2) CONSTRAINT PK_DEPT PRIMARY KEY,
	DNAME VARCHAR2(14) ,
	LOC VARCHAR2(13) ) ;
DROP TABLE EMP;
CREATE TABLE EMP
       (EMPNO NUMBER(4) CONSTRAINT PK_EMP PRIMARY KEY,
	ENAME VARCHAR2(10),
	JOB VARCHAR2(9),
	MGR NUMBER(4),
	HIREDATE DATE,
	SAL NUMBER(7,2),
	COMM NUMBER(7,2),
	DEPTNO NUMBER(2) CONSTRAINT FK_DEPTNO REFERENCES DEPT);
INSERT INTO DEPT VALUES
	(10,'ACCOUNTING','NEW YORK');
INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS');
INSERT INTO DEPT VALUES
	(30,'SALES','CHICAGO');
INSERT INTO DEPT VALUES
	(40,'OPERATIONS','BOSTON');
INSERT INTO EMP VALUES
(7369,'SMITH','CLERK',7902,to_date('17-12-1980','dd-mm-yyyy'),800,NULL,20);
INSERT INTO EMP VALUES
(7499,'ALLEN','SALESMAN',7698,to_date('20-2-1981','dd-mm-yyyy'),1600,300,30);
INSERT INTO EMP VALUES
(7521,'WARD','SALESMAN',7698,to_date('22-2-1981','dd-mm-yyyy'),1250,500,30);
INSERT INTO EMP VALUES
(7566,'JONES','MANAGER',7839,to_date('2-4-1981','dd-mm-yyyy'),2975,NULL,20);
INSERT INTO EMP VALUES
(7654,'MARTIN','SALESMAN',7698,to_date('28-9-1981','dd-mm-yyyy'),1250,1400,30);
INSERT INTO EMP VALUES
(7698,'BLAKE','MANAGER',7839,to_date('1-5-1981','dd-mm-yyyy'),2850,NULL,30);
INSERT INTO EMP VALUES
(7782,'CLARK','MANAGER',7839,to_date('9-6-1981','dd-mm-yyyy'),2450,NULL,10);
INSERT INTO EMP VALUES
(7788,'SCOTT','ANALYST',7566,to_date('13-JUL-87')-85,3000,NULL,20);
INSERT INTO EMP VALUES
(7839,'KING','PRESIDENT',NULL,to_date('17-11-1981','dd-mm-yyyy'),5000,NULL,10);
INSERT INTO EMP VALUES
(7844,'TURNER','SALESMAN',7698,to_date('8-9-1981','dd-mm-yyyy'),1500,0,30);
INSERT INTO EMP VALUES
(7876,'ADAMS','CLERK',7788,to_date('13-JUL-87')-51,1100,NULL,20);
INSERT INTO EMP VALUES
(7900,'JAMES','CLERK',7698,to_date('3-12-1981','dd-mm-yyyy'),950,NULL,30);
INSERT INTO EMP VALUES
(7902,'FORD','ANALYST',7566,to_date('3-12-1981','dd-mm-yyyy'),3000,NULL,20);
INSERT INTO EMP VALUES
(7934,'MILLER','CLERK',7782,to_date('23-1-1982','dd-mm-yyyy'),1300,NULL,10);

SELECT * FROM DEPT;
SELECT * FROM EMP;

COMMIT;
CREATE TABLE TEST_TBL(
    COL1 NUMBER
);
INSERT INTO TEST_TBL
VALUES(1);
SELECT * FROM TEST_TBL;
ROLLBACK;

/* DDL - 데이터 정의어 */ -- 실행 즉시 COMMIT
-- CREATE : 객체 생성
-- DROP   : 객체 제거
-- ALTER  : 객체 변경

/* DML - 데이터 조작어 */
-- INSERT : 데이터 입력
-- SELECT : 데이터 조회
-- UPDATE : 데이터 수정
-- DELETE : 데이터 삭제

/* DCL - 데이터 제어어 */
-- GRANT  : 권한 부여
-- REVOKE : 권한 회수

/* TCCL - 트랜잭션 */
-- COMMIT   : 변경사항 저장
-- ROLLBACK : 변경사항 취소

/* SELECT문 */
/* 
-- 필수
[5] SELECT -- 조회할 컬럼1, 조회할 컬럼2, .... [ * : 모든 컬럼 ]
[1] FROM -- 조회할 테이블
-- 선택
[2] WHERE -- 레코드 선별 조건
[3] GROUP BY -- 그룹으로 만들 컬럼
[4] HAVING -- 그룹 선별 조건
[6] ORDER BY -- 컬럼 정렬 기준
*/

-- 부서(DEPT)테이블의 모든 컬럼 조회
SELECT * FROM DEPT;
-- 직원(EMP)테이블의 모든 컬럼 조회
SELECT * FROM EMP;

/* 
EMP 테이블 
EMPNO : 직원번호, ENAME : 직원이름, JOB : 직무, MGR : 상사 직원번호
HIREDATE : 입사일, SAL : 급여, COMM : 커미션(수당), DEPTNO : 부서번호
*/
/*
DEPT 테이블
DEPTNO : 부서번호, DNAME : 부서이름, LOC : 지역
*/

-- EMP 테이블에서 직원이름(ENAME), 급여(SAL)를 조회
SELECT ENAME, SAL
FROM EMP;
-- 부서 테이블에서 부서번호, 부서이름을 조회
SELECT DEPTNO, DNAME
FROM DEPT;
-- 직원테이블에서 커미션을 조회
SELECT COMM
FROM EMP;
-- 직원테이블에서 직원이름, 입사일 (입사한 MONTH)
SELECT ENAME, TO_CHAR(HIREDATE, 'MM') AS MONTH
FROM EMP;

-- SELECT 작성한 컬럼의 별칭
/*
컬럼명 AS 별칭 << 권장) 자바와 연동시 가장 간편함
컬럼명 AS "별칭"

컬럼명 별칭
컬럼명 "별칭"
*/
-- EMPNO : 번호, ENAME : 이름, SAL : 급여
SELECT EMPNO AS 번호, ENAME AS 이름, SAL AS 급여
FROM EMP;

/* WHERE 절 */
-- 직원 테이블에서 [급여[SAL]가 3000 이상인] 직원을 조회
SELECT *
FROM EMP
WHERE SAL >= 3000;

-- 직무가 MANAGER인 직원 정보 조회
SELECT *
FROM EMP
WHERE JOB = 'MANAGER';

-- COMM이 0인 직원 조회
SELECT *
FROM EMP
WHERE COMM = 0;

-- NULL 값 조회 = IS NULL
-- COMM이 NULL인 직원 조회
SELECT *
FROM EMP
WHERE COMM IS NULL;
-- COMM이 있는 (NULL이 아닌) 직원 조회
SELECT *
FROM EMP
WHERE COMM IS NOT NULL;

-- 02월에 입사한 직원 정보를 조회
SELECT *
FROM EMP
WHERE TO_CHAR(HIREDATE, 'MM') = '02';

/*
와일드카드 : LIKE 연산자와 함께 사용
_ : 한 글자
% : 0개 이상 모든 문자
*/
-- [직원 이름이 M으로 시작하는] 직원 정보 조회
SELECT *
FROM EMP
WHERE ENAME LIKE 'M%';
-- [직원 이름이 5글자 이면서 3번째 글자가 'M'인] 직원 정보 조회
SELECT *
FROM EMP
WHERE ENAME LIKE '__M__';
-- [직원 이름에 'L'이 포함되는] 직원 정보 조회
SELECT *
FROM EMP
WHERE ENAME LIKE '%L%';
-- [직원 이름의 두 번째 문자가 'L'인] 직원 정보 조회
SELECT *
FROM EMP
WHERE ENAME LIKE '_L%'; -- 질의(QUERY)문법은 대소문자 구분 X 데이터는 구분 O

/* WHERE 절 (하나 이상의 조건) */
-- [직무가 'SALESMAN'] 이면서(AND) [COMM을 1000이상 받는] 직원 정보
SELECT *
FROM EMP
WHERE (JOB = 'SALESMAN') AND (COMM >= 1000);
-- [커미션을 받]거나(OR) [직무가 'MANAGER'인] 직원 정보 조회
SELECT *
FROM EMP
WHERE (JOB = 'MANAGER') OR (COMM IS NOT NULL);
-- [부서 번호가 10번] 이고(AND) [직무가 'CLERK'] 이거나(OR) ['MANAGER'인] 직원 조회
SELECT *
FROM EMP
WHERE (DEPTNO = 10) AND ( (JOB = 'CLERK') OR (JOB = 'MANAGER') );

/* GROUP BY : 특정 컬럼 기준으로 그룹 생성 */
-- 그룹(집계)함수
-- SUM()    : 총합
-- COUNT()  : 조회되는 레코드의 갯수
-- MAX()    : 최대값
-- MIN()    : 최소값
-- AVG()    : 평균값
--SELECT 그룹기준컬럼, 그룹(집계)함수
--FROM 테이블명
--GROUP BY 그룹기준컬럼;

-- 부서번호를 기준으로 그룹 생성 후 그룹별 직원 수를 조회
SELECT DEPTNO AS 부서번호, COUNT(*) AS 직원 --COUNT(컬럼명 OR *)
FROM EMP
GROUP BY DEPTNO;

-- 부서별 최고 급여 조회
SELECT DEPTNO, MAX(SAL)
FROM EMP
GROUP BY DEPTNO;

-- 직무별 최소 급여 조회
SELECT JOB, MIN(SAL)
FROM EMP
GROUP BY JOB;

-- 직무별 평균 조회
SELECT JOB, AVG(SAL)
FROM EMP
GROUP BY JOB;

-- NVL( 컬럼 , NULL인 경우 대체값 )
SELECT JOB, SAL, COMM, SAL + NVL(COMM, 0)
FROM EMP;

-- 부서별 (급여 + 커미션)의 총합
SELECT DEPTNO AS 부서번호, SUM(SAL + NVL(COMM, 0)) AS 총급여
FROM EMP
GROUP BY DEPTNO;

-- HAVING : 그룹선별 조건
-- 직무별 급여 총합이 5000 이상인 직무만 조회
SELECT JOB, SUM(SAL)
FROM EMP
GROUP BY JOB
HAVING SUM(SAL) >= 5000;
-- 부서별 평균 급여가 2000 이상인 부서만 조회
SELECT DEPTNO AS 부서번호, AVG(SAL) AS 평균급여
FROM EMP
GROUP BY DEPTNO
HAVING AVG(SAL) >= 2000;

-- 2월 또는 12월에 입사한 직원 수 조회
-- 2월 2명, 12월 2명 결과 도출
SELECT TO_CHAR(HIREDATE, 'MM') AS 입사월, COUNT(*) AS 직원수 -- || : 결합 연산자
FROM EMP
WHERE TO_CHAR(HIREDATE, 'MM') = '02' OR TO_CHAR(HIREDATE, 'MM') = '12' -- (TO_CHAR(HIREDATE, 'MM') IN ('02', '12'))
GROUP BY TO_CHAR(HIREDATE, 'MM');

SELECT * FROM EMP;
SELECT * FROM DEPT;

-- 직원테이블(EMP)에서 직원 이름, 직무, 급여를 조회
SELECT ENAME, JOB, SAL
FROM EMP;
-- 직무(JOB)가 'MANAGER'이거나 'SALESMAN'인 직원 조회
SELECT *
FROM EMP
WHERE (JOB = 'MANAGER') OR (JOB = 'SALESMAN'); -- JOB IN ('MANAGER', 'SALESMAN')
-- 직무(JOB)가 'MANAGER'와 'SALESMAN'이 아닌 직원 조회
SELECT *
FROM EMP
WHERE NOT (JOB = 'MANAGER') AND NOT (JOB = 'SALESMAN'); -- NOT JOB IN ('MANAGER', 'SALESMAN')
-- 부서별 급여의 평균
SELECT DEPTNO, AVG(SAL)
FROM EMP
GROUP BY DEPTNO;
-- 평균 급여가 2000 이상인 부서와 평균급여 조회
SELECT DEPTNO, AVG(SAL)
FROM EMP
GROUP BY DEPTNO
HAVING AVG(SAL) >= 2000;
-- 급여가 1000 이상인 직원들을 대상으로 직무별 평균 급여 조회
SELECT JOB, AVG(SAL)
FROM EMP
WHERE SAL >= 1000
GROUP BY JOB;

-- ORDER BY : 정렬 (ASC/DESC)
-- 직원 정보 조회
-- 급여를 기준으로 오름차순 정렬 (ASC는 생략가능)
SELECT *
FROM EMP
ORDER BY SAL ASC;
-- 직원 정보 조회
-- 급여를 기준으로 내림차순 정렬
SELECT *
FROM EMP
ORDER BY SAL DESC;

-- 부서번호를 기준으로 정렬하고, 급여를 기준으로 내림차순 정렬
SELECT *
FROM EMP
ORDER BY DEPTNO, SAL DESC;

/* 서브쿼리 */ -- 메인이 되는 SQL문 안에 보조로 SELECT문이 들어가는 것

-- MGR 컬럼 : 상급자 번호
-- 'BLAKE'가 관리하는 부하직원들의 목록 조회
-- 이름이 'BLAKE'인 직원의 번호 조회
SELECT EMPNO
FROM EMP
WHERE ENAME = 'BLAKE'; -- 7698
-- 상급자 번호가 7698번인 직원 목록 조회
SELECT * -- 메인쿼리
FROM EMP
WHERE MGR = (SELECT EMPNO -- 서브쿼리
             FROM EMP
             WHERE ENAME = 'BLAKE');
-- 'RESEARCH' 부서에서 근무하는 직원 목록 조회
SELECT *
FROM EMP
WHERE DEPTNO = (SELECT DEPTNO
                FROM DEPT
                WHERE DNAME = 'RESEARCH');
-- 'RESEARCH' 부서에서 근무하지 않는 직원 목록 조회
SELECT *
FROM EMP
WHERE DEPTNO != (SELECT DEPTNO
                 FROM DEPT
                 WHERE DNAME = 'RESEARCH');
SELECT *
FROM EMP
WHERE DEPTNO IN (SELECT DEPTNO
                 FROM DEPT
                 WHERE DNAME != 'RESEARCH');
-- 'JONES'와 같은 부서에서 근무하는 직원 목록 조회
SELECT *
FROM EMP
WHERE DEPTNO = (SELECT DEPTNO
                FROM EMP
                WHERE ENAME = 'JONES');
-- 'JONES'보다 많은 급여를 받는 직원 목록 조회
SELECT *
FROM EMP
WHERE SAL > (SELECT SAL
              FROM EMP
              WHERE ENAME = 'JONES');
-- 직원 전체 평균 급여 보다 많은 급여를 받는 직원 정보 조회
SELECT *
FROM EMP
WHERE SAL > (SELECT AVG(SAL)
              FROM EMP);
-- 직원 수가 3명 이하인 부서의 부서 정보 조회
SELECT *
FROM DEPT
WHERE DEPTNO IN (SELECT DEPTNO
                 FROM EMP
                 GROUP BY DEPTNO
                 HAVING COUNT(*) <= 3);

/* JOIN : 두 개 이상의 테이블을 사용하여 데이터를 조회 
사용되는 테이블의 관계성에 대한 조건 필요
FOREIGN KEY 참조관계로 연결된 컬럼
*/
-- EMP(직원 이름(ENAME), 급여(SAL)), DEPT(부서명(DNAME), 부서위치(LOC))
--'JONES', 2975, 'RESEARCH', 'DALLAS'
SELECT ENAME, SAL, DNAME, LOC
FROM EMP, DEPT; -- 48개 조회
-- 테이블의 조인 조건이 생략되면 모든 경우의 수가 조회

-- (EMP테이블 - DEPTNO 컬럼) = (DEPT테이블 - DEPTNO 컬럼)
SELECT ENAME, SAL, DNAME, LOC
FROM EMP, DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO; -- 조인조건 최소 (테이블갯수 - 1)개

/* JOIN ( INNER JOIN 내부조인, OUTER JOIN 외부조인, ... ) */
-- INNER JOIN (교집합) : 사용되는 테이블에 모두 존재하는 데이터만 조회
/*
SELECT 테이블명.조회할컬럼, 테이블명.조회할컬럼
FROM 테이블명1
    INNER JOIN 테이블명2
    ON 조인조건 AND 조인조건2
    INNER JOIN 테이블명3
    ON 조인조건
WHERE 결합이 된 테이블에서의 레코드 선별조건;
*/
SELECT EMP.ENAME, EMP.SAL, DEPT.DNAME, DEPT.LOC
FROM EMP
    INNER JOIN DEPT
    ON EMP.DEPTNO = DEPT.DEPTNO
WHERE EMP.SAL >= 2000;
-- EMP, DEPT 테이블 INNER JOIN
SELECT E.*, D.DNAME, D.LOC
FROM EMP E
    INNER JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO;

-- OUTER JOIN(합집합) : 다른 테이블에 일치하는 정보가 없어도 포함
/*
SELECT 테이블명.조회할컬럼, 테이블명.조회할컬럼
FROM 테이블명1
    [LEFT/RIGHT] OUTER JOIN 테이블명2
    ON 조인조건 AND 조인조건2
    [LEFT/RIGHT] OUTER JOIN 테이블명3
    ON 조인조건
WHERE 결합이 된 테이블에서의 레코드 선별조건;
*/
SELECT *
FROM EMP E
    LEFT OUTER JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO;

SELECT *
FROM EMP E
    RIGHT OUTER JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO;

SELECT *
FROM EMP E
    FULL OUTER JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO;

--부서별 직원수
SELECT D.DEPTNO, D.DNAME, NVL(E.ECOUNT,0) AS ECOUNT
FROM DEPT D
    LEFT OUTER JOIN (SELECT DEPTNO, COUNT(DEPTNO) AS ECOUNT
                     FROM EMP
                     GROUP BY DEPTNO) E
    ON D.DEPTNO = E.DEPTNO
ORDER BY D.DEPTNO;

SELECT * FROM EMP;
SELECT * FROM DEPT;
/* 'MARTIN'과 같은 부서에서 근무하는 직원의 EMP(이름, 직무), DEPT(부서명, 지역) 조회 */
SELECT EMP.ENAME, EMP.JOB, DEPT.DNAME, DEPT.LOC
FROM EMP
    INNER JOIN DEPT
    ON EMP.DEPTNO = DEPT.DEPTNO
WHERE EMP.DEPTNO IN (SELECT DEPTNO
                FROM EMP
                WHERE ENAME = 'MARTIN');
/* 'NEW YORK'에 있는 부서에 속한 직원의 이름, 부서명 */
SELECT E.ENAME, D.DNAME
FROM EMP E
    INNER JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO
WHERE E.DEPTNO IN (SELECT DEPTNO
                  FROM DEPT
                  WHERE LOC = 'NEW YORK');
/* 'SMITH'가 근무하는 부서의 평균 급여보다 많은 급여를 받는 직원의 이름, 급여, 부서명 조회 */
SELECT E.ENAME, E.SAL, D.DNAME
FROM EMP E
    INNER JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO
WHERE E.SAL > (SELECT AVG(SAL)
               FROM EMP
               WHERE DEPTNO = (SELECT DEPTNO
                               FROM EMP
                               WHERE ENAME = 'SMITH'));  
/* 부서별 평균급여가 가장 많은부서에서 근무하는 직원의 이름, 부서명, 급여 조회 */
SELECT E.ENAME, D.DNAME, E.SAL
FROM EMP E
    INNER JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO
WHERE E.DEPTNO IN (SELECT DEPTNO
                  FROM EMP
                  GROUP BY DEPTNO
                  HAVING AVG(SAL) = (SELECT MAX(평균급여)
                                     FROM (SELECT DEPTNO, AVG(SAL) AS 평균급여
                                           FROM EMP
                                           GROUP BY DEPTNO)));
/* 'SALES' 부서보다 평균 급여가 높은 부서명, 평균급여 조회 */
SELECT D.DNAME, AVG(SAL)
FROM EMP E
    INNER JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO
GROUP BY D.DNAME
HAVING AVG(SAL) > (SELECT AVG(SAL)
                   FROM EMP
                   WHERE DEPTNO = (SELECT DEPTNO
                                   FROM DEPT
                                   WHERE DNAME = 'SALES'));
/* 부하직원이 가장 많은 직원 보다 많은 급여를 받는 직원의 이름, 부서명, 직무, 급여 조회 */
-- ROWNUM n < 행 번호가 n번인 레코드
SELECT E.ENAME, D.DNAME, E.SAL
FROM EMP E
    INNER JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO
WHERE E.SAL > (SELECT SAL
               FROM EMP
               WHERE EMPNO = (SELECT MGR
                               FROM EMP
                               GROUP BY MGR
                               HAVING COUNT(MGR) = (SELECT MAX(COUNT_MGR)
                                                    FROM (SELECT MGR, COUNT(MGR) AS COUNT_MGR
                                                          FROM EMP
                                                          GROUP BY MGR))));
/* 부서별 최대급여, 최소급여를 받는 직원 이름, 직무, 부서명, 급여 조회
    부서번호기준 오름차순, 급여기준 내림차순 정렬*/
SELECT E.ENAME, E.JOB, D.DNAME, E.SAL
FROM EMP E
    INNER JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO
WHERE EMPNO IN (SELECT EMPNO
                FROM EMP
                WHERE SAL IN (SELECT MIN(SAL)
                              FROM EMP
                              GROUP BY DEPTNO) OR SAL IN (SELECT MAX(SAL)
                              FROM EMP
                              GROUP BY DEPTNO))
                              ORDER BY E.DEPTNO ASC, E.SAL DESC;

/*
INSERT문 
INSERT INTO 테이블명(컬럼1, 컬럼2, ..., 컬럼n) -- 컬럼명 생략시 테이블 내 모든 컬럼 지정 및 모든 컬럼의 값 기입 필수
VALUES (값1, 값2, ..., 값n);                 
*/
SELECT * FROM EMP;
SELECT * FROM DEPT;
DESC EMP;
-- EMP 테이블에 새 직원 추가 (8000, 'JUNMIN', 'KING', 7839, SYSDATE, 4000, NULL, 40)
INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES (8000, 'JUNMIN', 'KING', 7839, SYSDATE, 4000, NULL, 40);
--1 행 이(가) 삽입되었습니다.
-- 새 직원 추가 ( 직원번호를 서브쿼리 )
INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES ((SELECT MAX(EMPNO) + 1 FROM EMP), 'TAYLOR', 'SALESMAN', 7698, SYSDATE, 2000, 1000, 30);

/* 
UPDATE문 : 데이터 수정
UPDATE 테이블명
SET 컬럼명 = 값
WHERE 값을 수정할 레코드를 선별할 조건
*/
UPDATE EMP
SET SAL = 1000;
--14개 행 이(가) 업데이트되었습니다.
ROLLBACK;
-- 커미션을 받지 않는 직원들의 급여를 500 인상
UPDATE EMP
SET SAL = SAL + 500
WHERE COMM IS NULL;
-- 40번 부서의 이름을 '인천'으로 변경
UPDATE DEPT
SET DNAME = '인천'
WHERE DEPTNO = 40;
/* UPDATE에서의 서브쿼리 */
-- 'SMITH'와 같은 부서에서 근무하는 직원들의 급여를 300 인상
UPDATE EMP
SET SAL = SAL + 300
WHERE DEPTNO IN (SELECT DEPTNO
                FROM EMP
                WHERE ENAME = 'SMITH');
-- 'MARTIN'의 급여를 'BLAKE'의 급여와 같도록 변경
UPDATE EMP
SET SAL = (SELECT SAL
           FROM EMP
           WHERE ENAME = 'BLAKE')
WHERE ENAME = 'MARTIN';
/*
DELETE문 : 데이터(레코드) 삭제
DELETE FROM 테이블명
WHERE 삭제할 레코드를 선별할 조건
*/
-- 부서번호가 40번인 직원들을 삭제
DELETE FROM EMP
WHERE DEPTNO = 40;
--2개 행 이(가) 삭제되었습니다.
-- 부서 위치가 'DALLAS'인 직원들을 삭제
DELETE FROM EMP
WHERE DEPTNO IN (SELECT DEPTNO
                 FROM DEPT
                 WHERE LOC = 'DALLAS');
--3개 행 이(가) 삭제되었습니다.
ROLLBACK;
/* 쇼핑몰 ( 회원별 상품 주문 ) */
-- 회원정보, 상품정보, 주문정보
/* 회원(MEMBER)
MID NVARCHAR2(20)         아이디(PRIMARY KEY), 
MPW NVARCHAR2(20)         비밀번호(NOT NULL),
MEMAIL NVARCHAR2(50)      이메일, 
MPHONE NVARCHAR2(13)      전화번호,
MADDR NVARCHAR2(200)      주소지, 
MJOINDATE DATE            가입일(DEFAULT : SYSDATE) */

CREATE TABLE MEMBER(
    MID NVARCHAR2(20),
    MPW NVARCHAR2(20),
    MEMAIL NVARCHAR2(50),
    MPHONE NVARCHAR2(13),
    MADDR NVARCHAR2(200),
    MJOINDATE DATE
);
--ALTER TABLE 테이블명 ADD CONSTRAINTS 제약조건식별자 제약유형(컬럼명)
ALTER TABLE MEMBER ADD CONSTRAINTS PK_MID PRIMARY KEY(MID); -- MID 컬럼 PRIMARY KEY 부여

ALTER TABLE MEMBER MODIFY MPW NOT NULL; -- MPW 컬럼 NOT NULL 부여

ALTER TABLE MEMBER MODIFY MJOINDATE DEFAULT SYSDATE; -- MJOINDATE 컬럼 DEFAULT값 SYSDATE 부여

/* 상품(PRODUCT)테이블 
PCODE NUMBER            상품번호 (PK)
PNAME NVARCHAR2(20)      상품이름 (NOT NULL)
PPRICE NUMBER           상품가격 (CHECK >= 10000)
PCOMPANY NVARCHAR2(20)   제조사
PTYPE NVARCHAR2(10)      상품종류 (DEFAULT : '기타')
PSTOCK NUMBER(2)        상품재고 (CHECK >= 0) */

CREATE TABLE PRODUCT (
    PCODE NUMBER,
    PNAME NVARCHAR2(20) NOT NULL,
    PPRICE NUMBER,
    PCOMPANY NVARCHAR2(20),
    PTYPE NVARCHAR2(10) DEFAULT '기타',
    PSTOCK NUMBER(2),
    CONSTRAINT PK_PCODE PRIMARY KEY(PCODE),
    CONSTRAINT CHECK_PPRICE CHECK (PPRICE >= 10000),
    CONSTRAINT CHECK_PSTOCK CHECK (PSTOCK >= 0)
);

/* 주문(ORDERS)테이블 
OCODE NUMBER        주문번호(PK),
OPCODE NUMBER       상품(FK),
OMID NVARCHAR2(20)  회원(FK),
OCOUNT NUMBER       주문수,
ODATE DATE          주문일(DEFAULT SYSDATE) */

CREATE TABLE ORDERS(
    OCODE NUMBER,
    OPCODE NUMBER,
    OMID NVARCHAR2(20),
    OCOUNT NUMBER,
    ODATE DATE DEFAULT SYSDATE, -- ODATE에 DEFAULT 값 SYSDATE 부여
    CONSTRAINT PK_OCODE PRIMARY KEY (OCODE), --OCODE에 PRIMARY KEY 부여
    CONSTRAINT FK_OPCODE FOREIGN KEY (OPCODE) REFERENCES PRODUCT(PCODE)
    -- PRODUCT의 PCODE를 참조해서 OPCODE에 FOREIGN KEY 부여
);
ALTER TABLE ORDERS ADD CONSTRAINT FK_OMID FOREIGN KEY (OMID)
REFERENCES MEMBER(MID); -- MEMBER의 MID를 참조해서 OMID에 FOREIGN KEY 부여

DESC MEMBER;
DESC PRODUCT;
DESC ORDERS;

/* 회원(MEMBER) */
/* 회원가입 기능 */
-- USER > 가입할 회원 정보 입력
-- 입력한 정보에 대한 중복확인 (SELECT)
SELECT *
FROM MEMBER
WHERE MID = '입력한ID'; -- 1행 조회(중복된 아이디), 0행 조회(사용가능한 아이디)
--회원가입 처리 INSERT
INSERT INTO MEMBER(MID, MPW, MEMAIL, MPHONE, MADDR)
VALUES('입력한ID', '입력한PW', '입력한EMAIL', '입력한PHONE', '입력한ADDR'); --1개 행이 삽입되었습니다.
/* 로그인 */
-- USER > 사용자가 로그인을 위한 아이디, 비밀번호를 입력
-- SELECT
SELECT *
FROM MEMBER
WHERE MID = 'KHC' AND MPW = '1234'; -- 1행 조회(로그인) 0행 조회(로그인 불가)

/* 상품 기능 */
/* 상품 목록 출력 (인기순, 최신순, 높은가격순, 낮은가격순) */
-- SELECT
SELECT *
FROM PRODUCT;
-- 상품코드순
SELECT *
FROM PRODUCT
ORDER BY PCODE;
-- 가전제품 최신순 출력
SELECT *
FROM PRODUCT
WHERE PTYPE IN '가전'
ORDER BY PCODE DESC;


/* 상품 주문 */
-- USER > 상품 선택, 수량 입력
-- 사용자가 입력한 수량
INSERT INTO ORDERS(OCODE, OPCODE, OMID, OCOUNT)
VALUES ( (SELECT NVL(MAX(OCODE),0) + 1 FROM ORDERS),1, 'KHC', 3); 
--1 행 이(가) 삽입되었습니다.
-- 주문된 상품(상품번호 1번)의 재고 수정(3개 감소).
UPDATE PRODUCT
SET PSTOCK = PSTOCK - 1
WHERE PCODE = 1;
COMMIT;
-- 주문 정보 입력 : 주문번호는 1부터 시작해서 순차적으로 1씩 증가
-- 새 주문번호 조회
SELECT NVL(MAX(OCODE),0) + 1 FROM ORDERS;

-- 주문처리 INSERT
INSERT INTO ORDERS(OCODE, OPCODE, OMID, OCOUNT)
VALUES (주문번호, 상품번호, 로그인된아이디, 주문수량);

-- 주문된 상품의 재고 감소
UPDATE PRODUCT
SET PSTOCK = PSTOCK - (SELECT OCOUNT
                       FROM ORDERS
                       WHERE OCODE = 주문번호)
WHERE PCODE IN (SELECT OPCODE
                FROM ORDERS
                WHERE OCODE = 주문번호);

-- 주문 내역 조회 - 로그인된 회원
-- 주문번호, 상품이름, 주문수량, 주문금액, 주문일 조회
-- 최근 주문한 내역순으로 조회
SELECT O.OCODE AS 주문번호, P.PNAME AS 상품이름, O.OCOUNT AS 주문수량, (O.OCOUNT * P.PPRICE) AS 주문금액, O.ODATE AS 주문일
FROM ORDERS O
    INNER JOIN PRODUCT P
    ON O.OPCODE = P.PCODE
WHERE O.OMID = 'KHC'
ORDER BY O.ODATE DESC;

-- 주문 취소 기능
-- OCODE가 1인 주문에 대한 취소 처리 과정

SELECT OPCODE FROM ORDERS WHERE OCODE = 1;
SELECT * FROM PRODUCT WHERE PCODE = 1;

-- OCODE가 1인 주문의 상품 코드에 해당하는 품목의 재고를 주문 수량만큼 증가
UPDATE PRODUCT
SET PSTOCK = PSTOCK + (SELECT OCOUNT
                       FROM ORDERS
                       WHERE OCODE = 1)
WHERE PCODE = (SELECT OPCODE
               FROM ORDERS
               WHERE OCODE = 1);
-- ORDERS 테이블에서 OCODE가 1번인 주문 삭제
DELETE FROM ORDERS WHERE OCODE = 1;



SELECT * FROM ORDERS;
SELECT * FROM PRODUCT;

CREATE TABLE JDBCTBL(
    COL1 NUMBER,
    COL2 NVARCHAR2(20),
    COL3 DATE
);

SELECT * FROM JDBCTBL;

SELECT * FROM PRODUCT;
SELECT * FROM MEMBER;
SELECT * FROM ORDERS;

DELETE FROM MEMBER WHERE MID = 'TEST1' OR MID = 'test1';

SELECT *, PCODE
FROM PRODUCT;

SELECT P.*
FROM PRODUCT P
    LEFT OUTER JOIN (SELECT OPCODE, COUNT(OPCODE) AS TOTALCOUNT
                     FROM ORDERS
                     GROUP BY OPCODE) O
    ON P.PCODE = O.OPCODE
ORDER BY NVL(O.TOTALCOUNT, 0) DESC;

DESC PRODUCT;

DESC ORDERS;


delete from orders;

UPDATE PRODUCT
SET PSTOCK = PSTOCK + 5
WHERE PCODE = 1;

SELECT *
FROM ORDERS
WHERE OMID = 'KHC' AND TO_CHAR(ODATE, 'MM') IN '12';



SELECT *
FROM ORDERS
ORDER BY OC ODE ;


UPDATE ORDERS
SET ODATE = TO_DATE('2025-02-27 20:41:30', 'YYYY-MM-DD HH24:MI:SS')
WHERE OCODE IN 4;


SELECT *
FROM PRODUCT
WHERE PCODE IN (SELECT OPCODE FROM ORDERS WHERE OCODE = 5);

SELECT *
FROM ORDERS O
    INNER JOIN PRODUCT P
    ON O.OPCODE = P.PCODE
    INNER JOIN MEMBER M
    ON O.OMID = M.MID
WHERE O.OMID = 'KHC'
ORDER BY O.ODATE DESC;

DELETE FROM ORDERS WHERE OCODE = 1;

/* 도서 관리 시스템 */
-- MEMBER, BOOK, RENTAL
/* 회원(MEMBER)테이블 */
-- 기존 MEMBER테이블 사용
/* 도서(BOOK)테이블 */
-- 도서 코드, 도서명, 저자, 도서 종류
CREATE TABLE BOOK(
    BID NUMBER,                     --책 코드(PK)
    BTITLE NVARCHAR2(50) NOT NULL,  --제목(NOT NULL)
    BAUTHOR NVARCHAR2(20),          --저자
    BTYPE NVARCHAR2(10),            --종류('소설', '잡지', '코믹')
    BSTATE NVARCHAR2(5)             --'대여중','대여가능'(NOT NULL)
);
ALTER TABLE BOOK
ADD CONSTRAINT PK_BID PRIMARY KEY(BID);
ALTER TABLE BOOK
ADD CONSTRAINT CK_BTYPE CHECK( BTYPE IN ('소설', '잡지', '코믹') );
ALTER TABLE BOOK
ADD CONSTRAINT CK_BSTATE CHECK( BSTATE IN ('대여중', '대여가능') );
ALTER TABLE BOOK
MODIFY BSTATE DEFAULT '대여가능';

/* 대여(RENTAL)테이블 */
-- 회원아이디, 도서 코드, 대여일, 반납일
CREATE TABLE RENTAL(
    RID NUMBER,             --대여번호(PK)
    RMID NVARCHAR2(20),      --대여한 회원ID(FK)
    RBID NUMBER,            --대여한 책ID(FK)
    RENTALDATE DATE,        --대여한 날짜
    RETURNDATE DATE         --반납한 날짜
);
ALTER TABLE RENTAL
ADD CONSTRAINT PK_RID PRIMARY KEY(RID);
ALTER TABLE RENTAL
ADD CONSTRAINT FK_RMID_MID FOREIGN KEY(RMID) REFERENCES MEMBER(MID);
ALTER TABLE RENTAL
ADD CONSTRAINT FK_RBID_BID FOREIGN KEY(RBID) REFERENCES BOOK(BID);
ALTER TABLE RENTAL
MODIFY RENTALDATE DEFAULT SYSDATE;

DESC RENTAL;

SELECT * FROM RENTAL;
SELECT * FROM BOOK;

UPDATE BOOK
SET BSTATE = '대여가능'
WHERE BID IN 1;

UPDATE BOOK
SET BSTATE = '대여중'
WHERE BID IN 1;


INSERT INTO RENTAL(RID, RMID, RBID, RENTALDATE, RETURNDATE)
VALUES( (SELECT NVL(MAX(RID), 0) + 1 FROM RENTAL), 'KHC', 1, DEFAULT, NULL);

SELECT COUNT(*) AS RENTALCOUNT FROM RENTAL WHERE RMID = 'KHC' AND RETURNDATE IS NULL;

DELETE FROM RENTAL;


SELECT COUNT(*) AS RENTALCOUNT FROM RENTAL WHERE RMID = 'KHC' AND RETURNDATE IS NULL;

INSERT INTO RENTAL(RID, RMID, RBID, RENTALDATE, RETURNDATE)
VALUES( (SELECT NVL(MAX(RID), 0) + 1 FROM RENTAL ), 'KHC', 1, DEFAULT, NULL);

DESC MEMBER;

SELECT NVL(TO_CHAR(RETURNDATE), ' ') FROM RENTAL R INNER JOIN BOOK B ON R.RBID = B.BID WHERE RMID = 'KHC';

SELECT NVL(TO_CHAR(*), ' ') FROM RENTAL;

SELECT M.*, 대여권수, 현재대여권수
FROM MEMBER M
    LEFT OUTER JOIN (SELECT M.MID, COUNT(RENTALDATE) AS 대여권수, (COUNT(RENTALDATE) - COUNT(RETURNDATE)) AS 현재대여권수
                     FROM MEMBER M
                        LEFT OUTER JOIN RENTAL R
                        ON M.MID = R.RMID
                        GROUP BY M.MID) R
    ON M.MID = R.MID;

SELECT M.MID, COUNT(RENTALDATE) AS 대여권수, (COUNT(RENTALDATE) - COUNT(RETURNDATE)) AS 현재대여권수
FROM MEMBER M
    LEFT OUTER JOIN RENTAL R
    ON M.MID = R.RMID
GROUP BY M.MID;

SELECT B.*, NVL(대여횟수,0) AS 대여횟수
FROM BOOK B
    LEFT OUTER JOIN (SELECT RBID, COUNT(RBID) AS 대여횟수
                FROM RENTAL
                GROUP BY RBID) R
    ON R.RBID = B.BID
ORDER BY 대여횟수 DESC;

SELECT * FROM RENTAL WHERE RMID = 'KHC';

DESC RENTAL;

DESC ORDERS;

SELECT * FROM ORDERS;

INSERT INTO ORDERS(OCODE, OPCODE, OMID, OCOUNT, ODATE)
VALUES(1, 1, 'KHC', 1, DEFAULT);

INSERT INTO ORDERS(OCODE, OPCODE, OMID, OCOUNT, ODATE)
VALUES(2, 1, 'KHC', 1, '2024-06-05');

UPDATE ORDERS
SET ODATE = '2024-06-04'
WHERE OCODE = 1;











































