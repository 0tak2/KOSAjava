package org.youngtak2.springboard.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleExtended extends Article {
	private String memberName;
	private int articleLike;
	private int articleComments;
}
