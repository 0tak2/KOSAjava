package org.youngtak2.springboard.board.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Comment {
	
	private int commentNum;
	
	@NonNull
	private String commentContent;
	
	@NonNull
	private String commentAuthor;
	
	private String memberName;
	
	private Date commentDate;
	
	@NonNull
	private int commentArticle;
}
