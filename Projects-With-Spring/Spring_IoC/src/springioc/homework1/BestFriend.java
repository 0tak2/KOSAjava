package springioc.homework1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class BestFriend {
	private Friend friend;
	private String phone;
	
	@Autowired
	public BestFriend(Friend friend, String phone) {
		this.friend = friend;
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return friend.getName() + ", " + friend.getAge() + "세, " + phone;
	}
}
