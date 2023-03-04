package org.youngtak2.springboard.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicSelect {
	private int numOfRows;
	private int start;
	private int end;
}
