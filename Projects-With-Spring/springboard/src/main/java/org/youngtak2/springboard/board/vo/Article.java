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
public class Article {
	private int articleNum;
	
	@NonNull
	private String articleTitle;
	
	@NonNull
	private String articleContent;
	
	@NonNull
	private String articleAuthor;
	
	private Date articleDate;
}
