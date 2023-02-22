package springioc.anno.sample1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component // 기본 biean id:MyFoodManager
@Component("myFood")
public class MyFoodManager {

	@Autowired
	@Qualifier(value="unfavoriteFood")
	private Food favoriteFood;
	
	@Autowired
	private Food unfavoriteFood;
	
	public MyFoodManager() {
		System.out.println("[MyFoodManager] 기본 생성자 호출됨");
	}
	
	@Override
	public String toString() {
		return "\n선호 음식\t" + favoriteFood +
				"\n비선호 음식\t" + unfavoriteFood;
	}
}
