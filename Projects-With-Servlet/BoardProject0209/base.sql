-- 스키마 만들기
DROP DATABASE IF EXISTS simpleboard2;
CREATE DATABASE simpleboard2;

USE simpleboard2;

-- 멤버 테이블 만들기
CREATE TABLE members (
	memberId  VARCHAR(10)  NOT NULL PRIMARY KEY,
	memberName  VARCHAR(20)  NOT NULL,
	memberPw  VARCHAR(20) NOT NULL
);

-- 멤버 테이블 테스트 데이터
INSERT INTO members VALUES('hong', '홍길동', '1234');
INSERT INTO members VALUES('lim', '임영택', '1234');

-- 게시글 테이블 만들기
CREATE TABLE articles (
	articleNum  INTEGER  NOT NULL PRIMARY KEY auto_increment,
	articleTitle  VARCHAR(30)  NOT NULL,
	articleContent  VARCHAR(256)  NOT NULL,
	articleAuthor  VARCHAR(10),
	articleDate  DATETIME  NOT NULL DEFAULT NOW(),
	FOREIGN KEY (articleAuthor) REFERENCES members(memberId)
);

-- 게시글 테이블 테스트 데이터
INSERT INTO articles(articleTitle, articleAuthor, articleContent)
		VALUES('플스 팔아요', 'hong', '싸게 팝니다. 네고 사절');

INSERT INTO articles(articleTitle, articleAuthor, articleContent)
		VALUES('갤럭시 팔아요', 'hong', '5만원에 팝니다. 네고 사절');

-- 댓글 테이블 만들기
CREATE TABLE comments (
	commentNum  INTEGER  NOT NULL PRIMARY KEY auto_increment,
	commentContent  VARCHAR(256)  NOT NULL,
	commentAuthor  VARCHAR(10),
	commentDate  DATETIME  NOT NULL DEFAULT NOW(),
	commentArticle INTEGER,
	FOREIGN KEY (commentAuthor) REFERENCES members(memberId),
	FOREIGN KEY (commentArticle) REFERENCES articles(articleNum) ON DELETE CASCADE
);

-- 댓글 테이블 테스트 데이터
INSERT INTO comments(commentContent, commentAuthor, commentArticle)
		VALUES('판매 완료되었습니다.', 'hong', 1);

INSERT INTO comments(commentContent, commentAuthor, commentArticle)
		VALUES('가격 인하 - 3만원... 제발 사주세요', 'hong', 2);

-- 좋아요 테이블 만들기
CREATE TABLE likes (
	likeNum  INTEGER  NOT NULL PRIMARY KEY AUTO_INCREMENT,
	likeMemberId  VARCHAR(10),
	likeArticle  INTEGER,
	FOREIGN KEY (likeMemberId) REFERENCES members(memberId),
	FOREIGN KEY (likeArticle) REFERENCES articles(articleNum) ON DELETE CASCADE
);

-- 좋아요 테이블 테스트 데이터
INSERT INTO likes(likeMemberId, likeArticle) VALUES('hong', 1);