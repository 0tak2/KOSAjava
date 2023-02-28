package my.spring.springweb.shop.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class Basket {

	private int bananaCount;
	private int appleCount;
	private int halabongCount;
	
	public void setBananaCount(int bananaCount) {
		this.bananaCount++;
	}
	public void setAppleCount(int appleCount) {
		this.appleCount++;
	}
	public void setHalabongCount(int halabongCount) {
		this.halabongCount++;
	}
	
	
}
