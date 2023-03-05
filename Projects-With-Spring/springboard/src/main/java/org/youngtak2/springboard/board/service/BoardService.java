package org.youngtak2.springboard.board.service;

import java.util.List;

import org.youngtak2.springboard.board.vo.Article;
import org.youngtak2.springboard.board.vo.ArticleExtended;
import org.youngtak2.springboard.board.vo.Comment;
import org.youngtak2.springboard.board.vo.Like;
import org.youngtak2.springboard.common.service.ServiceResult;
import org.youngtak2.springboard.common.vo.BasicSelect;

public interface BoardService {
	public int getNumOfArticles();
	
	public List<ArticleExtended> getAllArticles();
	
	public List<ArticleExtended> getArticles(BasicSelect param);
	
	public ArticleExtended getArticle(Article articleParam);
	
	public boolean writeArticle(Article newArticle);
	
	public boolean editArticle(Article param);
	
	public boolean deleteArticle(Article param);
	
	public List<Comment> getAllComments(ArticleExtended param);
	
	public Comment getOneComment(Comment param);
	
	public boolean writeComment(Comment param);
	
	public boolean editComment(Comment param);
	
	public boolean deleteComment(Comment param);
	
	public Like getLike(Like param);
	
	public ServiceResult setLike(Like param);
	
	public boolean unSetLike(Like param);
	
	
}
