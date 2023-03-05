package org.youngtak2.springboard.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youngtak2.springboard.board.dao.BoardDao;
import org.youngtak2.springboard.board.vo.Article;
import org.youngtak2.springboard.board.vo.ArticleExtended;
import org.youngtak2.springboard.board.vo.Comment;
import org.youngtak2.springboard.board.vo.Like;
import org.youngtak2.springboard.common.service.ServiceResult;
import org.youngtak2.springboard.common.vo.BasicSelect;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao dao;
	
	@Autowired
	SqlSession session;
	
	@Override
	public int getNumOfArticles() {
		BasicSelect result = null;
		try {
			result = dao.selectArticleCounts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.getNumOfRows();
	}

	@Override
	public List<ArticleExtended> getAllArticles() {
		List<ArticleExtended> list = null;
		try {
			list = dao.selectAllArticles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ArticleExtended> getArticles(BasicSelect param) {
		List<ArticleExtended> list = null;
		try {
			list = dao.selectArticles(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArticleExtended getArticle(Article articleParam) {
		ArticleExtended result = null;
		try {
			result = dao.selectOneArticle(articleParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean writeArticle(Article newArticle) {
		boolean result = false;
		try {
			int affectedRows = dao.insertArticle(newArticle);
			
			if (affectedRows  == 1) {
//				sqlSession.commit();
				result = true;
			} else {
//				sqlSession.rollback();
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean editArticle(Article param) {
		boolean result = false;
		try {
			int affectedRows = dao.editArticle(param);
			
			if (affectedRows  == 1) {
				System.out.println(param.getArticleContent());
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteArticle(Article param) {
		boolean result = false;
		try {
			
			int affectedRows = dao.deleteArticle(param);
			
			if (affectedRows  == 1) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Comment> getAllComments(ArticleExtended param) {
		List<Comment> list = null;
		try {
			list = dao.selectAllComments(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Comment getOneComment(Comment param) {
		Comment result = null;
		try {
			result = dao.selectOneComment(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean writeComment(Comment param) {
		boolean result = false;
		try {
			int affectedRows = dao.insertComment(param);
			
			if (affectedRows  == 1) {
//				sqlSession.commit();
				result = true;
			} else {
//				sqlSession.rollback();
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean editComment(Comment param) {
		boolean result = false;
		try {
			int affectedRows = dao.editComment(param);
			
			if (affectedRows  == 1) {
//				sqlSession.commit();
				result = true;
			} else {
//				sqlSession.rollback();
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteComment(Comment param) {
		boolean result = false;
		try {
			int affectedRows = dao.deleteComment(param);
			
			if (affectedRows  == 1) {
//				sqlSession.commit();
				result = true;
			} else {
//				sqlSession.rollback();
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Like getLike(Like param) {
		Like result = null;
		try {
			result = dao.selectLike(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ServiceResult setLike(Like param) {
		ServiceResult result = ServiceResult.FAILED;
		try {
			// 중복 좋아요 방지
			Like likeExpectedNull = dao.selectLike(param);
			if (likeExpectedNull != null) {
				// 이미 좋아요 기록이 있으면 처리 거부
				return ServiceResult.REJECTED;
			}
			
			// 좋아요 처리
			int affectedRows = dao.insertLike(param);
			
			if (affectedRows  == 1) {
//				sqlSession.commit();
				result = ServiceResult.SUCCESS;
			} else {
//				sqlSession.rollback();
				result = ServiceResult.FAILED;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean unSetLike(Like param) {
		boolean result = false;
		try {
			int affectedRows = dao.deleteLike(param);
			
			if (affectedRows  == 1) {
//				sqlSession.commit();
				result = true;
			} else {
//				sqlSession.rollback();
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
