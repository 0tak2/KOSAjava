USE sqldb;

CREATE TABLE stdtbl (
    stdName    VARCHAR(10) NOT NULL PRIMARY KEY,
    addr       CHAR(4) NOT NULL
);

CREATE TABLE clubtbl (
    clubName   VARCHAR(10) NOT NULL PRIMARY KEY,
    roomNo     CHAR(4) NOT NULL
);

CREATE TABLE stdclubtbl (
    num        INT AUTO_INCREMENT NOt NULL PRIMARY KEY,
    stdName    VARCHAR(10) NOT NULL,
    clubName   VARCHAR(10) NOT NULL,
    FOREIGN KEY(stdName) REFERENCES stdtbl(stdName),
    FOREIGN KEY(clubName) REFERENCES clubtbl(clubName)
);
    
INSERT INTO stdtbl VALUES ('김범수', '경남');    
INSERT INTO stdtbl VALUES ('성시경', '서울');    
INSERT INTO stdtbl VALUES ('조용필', '경기');    
INSERT INTO stdtbl VALUES ('은지원', '경북');    
INSERT INTO stdtbl VALUES ('바비킴', '서울');

INSERT INTO clubtbl VALUES ('수영', '101호');
INSERT INTO clubtbl VALUES ('바둑', '102호');
INSERT INTO clubtbl VALUES ('축구', '103호');
INSERT INTO clubtbl VALUES ('봉사', '104호');

INSERT INTO stdclubtbl VALUES (NULL, '김범수', '바둑');
INSERT INTO stdclubtbl VALUES (NULL, '김범수', '축구');
INSERT INTO stdclubtbl VALUES (NULL, '조용필', '축구');
INSERT INTO stdclubtbl VALUES (NULL, '은지원', '축구');
INSERT INTO stdclubtbl VALUES (NULL, '은지원', '봉사');
INSERT INTO stdclubtbl VALUES (NULL, '바비킴', '봉사');