package org.youngtak2.springboard.board.dao;

import java.util.List;

import org.youngtak2.springboard.board.vo.Article;
import org.youngtak2.springboard.board.vo.ArticleExtended;
import org.youngtak2.springboard.board.vo.Comment;
import org.youngtak2.springboard.board.vo.Like;
import org.youngtak2.springboard.common.vo.BasicSelect;

public interface BoardDao {
	
	public BasicSelect selectArticleCounts();
	
	public List<ArticleExtended> selectAllArticles();
	
	public List<ArticleExtended> selectArticles(BasicSelect param);
	
	public ArticleExtended selectOneArticle(Article articleParam);
	
	public int insertArticle(Article newArticle);
	
	public int editArticle(Article param);
	
	public int deleteArticle(Article param);
	
	public List<Comment> selectAllComments(Article param);
	
	public Comment selectOneComment(Comment param);
	
	public int insertComment(Comment param);
	
	public int editComment(Comment param);
	
	public int deleteComment(Comment param);
	
	public Like selectLike(Like param);
	
	public int insertLike(Like param);
	
	public int deleteLike(Like param);
}
