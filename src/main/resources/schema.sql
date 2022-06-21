DROP TABLE IF EXISTS MEMBERSHIP CASCADE;
DROP TABLE IF EXISTS SHOP CASCADE;
DROP TABLE IF EXISTS MEMBER_POINT CASCADE;
DROP TABLE IF EXISTS POINT CASCADE;
DROP TABLE IF EXISTS CODE_DATA CASCADE;

CREATE TABLE MEMBERSHIP (
  MEMBERSHIP_SEQ       bigint NOT NULL, -- PK
  SHOP_SEQ          bigint NOT NULL, -- 논리적 외래키
  TID       varchar(50) UNIQUE NOT NULL,  -- 트랜잭션ID
  USER_ID           long NOT NULL, -- 사용자ID
  BARCODE           varchar(10) NOT NULL, -- 바코드
  RGSTED_AT     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(), -- 가입일
  PRIMARY KEY (MEMBERSHIP_SEQ)
);

CREATE TABLE SHOP(
  SHOP_SEQ           bigint NOT NULL AUTO_INCREMENT, -- PK
  CATEGORY       varchar(1) NOT NULL, -- 업종
  PARTNER_ID  varchar(50) NOT NULL,  -- 상점ID
  PARTNER_NAME  varchar(50) NOT NULL, -- 상점이름
  PRIMARY KEY (SHOP_SEQ)
);

CREATE TABLE MEMBER_POINT(
  MEMBER_POINT_SEQ          bigint NOT NULL AUTO_INCREMENT, -- PK
  MEMBERSHIP_SEQ       bigint NOT NULL, -- 논리적 외래키
  CATEGORY       varchar(1) NOT NULL, -- 업종
  TOTAL_POINT  float NOT NULL,  -- 포인트총합
  VERSION  long NOT NULL, -- 버전
  PRIMARY KEY (MEMBER_POINT_SEQ)
);

CREATE TABLE POINT (
  POINT_SEQ           bigint NOT NULL AUTO_INCREMENT, -- PK
  MEMBERSHIP_SEQ  bigint NOT NULL,  -- 논리적 외래키
  TID       varchar(50) UNIQUE NOT NULL,  -- 트랜잭션ID
  PARTNER_ID  varchar(50) NOT NULL,  -- 상점ID
  USER_ID           long NOT NULL, -- 사용자ID
  BARCODE           varchar(10) NOT NULL, -- 바코드
  CATEGORY       varchar(1) NOT NULL, -- 업종
  TYPE       varchar(10) NOT NULL, -- 사용타입
  POINT_AMOUNT        float NOT NULL,   --입금액
  APPRVED_AT     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),   -- 사용일
  PRIMARY KEY (POINT_SEQ)
);

CREATE TABLE CODE_DATA(
  ERROR_CODE       varchar(20) NOT NULL,  -- PK
  ERROR_MSG       varchar(255) NOT NULL, -- 에러메시지
  REASON_KEY       varchar(20) NOT NULL, -- 결과 키
  INDATE     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),   -- 생성일
  PRIMARY KEY (ERROR_CODE)
);

