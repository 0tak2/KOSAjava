package springioc.sample4;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class AbstractDay {

	public abstract String dayInfo();
	
	public static AbstractDay getInstance() {
		// 오늘 날짜의 요일 반환
		GregorianCalendar cal = new GregorianCalendar();
		
		int day = cal.get(Calendar.DAY_OF_WEEK); // 금일 요일을 숫자로 반환. 1 -> 일, 2-> 월, ...
		
		AbstractDay my_day = null;
		
		switch(day) {
		case 1:
			my_day = new Sunday(); // Sunday는 AbstractDay를 상속
			break;
		case 2:
			my_day = new Monday(); // Monday는 AbstractDay를 상속
			break;
		case 3:
			my_day = new Tuesday(); // Tuesday는 AbstractDay를 상속
			break;
		}
		
		return my_day;
	}
}
