DROP DATABASE IF EXISTS sqldb;   -- 만약 sqldb가 존재하면 삭제.

CREATE DATABASE sqldb;  -- 데이터베이스 생성

USE sqldb;  -- sqldb 사용

CREATE TABLE usertbl
( userID    CHAR(8) NOT NULL PRIMARY KEY,  -- 사용자 ID(PK)
  name      VARCHAR(10) NOT NULL, -- 이름
  birthYear INT NOT NULL, -- 출생연도
  addr      CHAR(2) NOT NULL, -- 지역(경기, 서울, 경남, etc)
  mobile1   CHAR(3), -- 휴대폰 국번(010)
  mobile2   CHAR(8), -- 휴대폰 나머지 전화번호(하이픈제외)
  height    SMALLINT, -- 키
  mDate     DATE -- 회원가입일
);

CREATE TABLE buytbl
( num       INT AUTO_INCREMENT NOT NULL PRIMARY KEY,  -- 순번(PK)
  userID    CHAR(8) NOT NULL, -- 아이디(FK)
  prodName  CHAR(6) NOT NULL, -- 물품명
  groupName CHAR(4), -- 분류
  price     INT NOT NULL, -- 단가
  amount    SMALLINT NOT NULL, -- 수량
  FOREIGN KEY (userID) REFERENCES usertbl(userID)
);  
