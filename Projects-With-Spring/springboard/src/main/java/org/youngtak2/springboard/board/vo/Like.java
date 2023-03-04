package org.youngtak2.springboard.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Like {
	private String likeMemberId;
	private int likeArticle;
}
