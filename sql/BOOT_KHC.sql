-- SYS 계정으로 접속
-- 공통사용자 규칙 해제
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
-- LOCAL DB에 계정 생성
CREATE USER BOOT_KHC IDENTIFIED BY "1234";
GRANT DBA TO BOOT_KHC; -- 관리자 권한 부여

-- BOOT_KHC 계정으로 접속

desc testboard;

select * from testboard;

select * from member;

select * from product;

select * from product_image;

delete from product_image;

delete from product;