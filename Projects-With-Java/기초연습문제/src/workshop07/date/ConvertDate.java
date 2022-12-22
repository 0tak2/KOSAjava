package workshop07.date;

import java.util.Calendar;

public class ConvertDate {
	public String convert(Calendar date, int type) throws Exception {
		switch(type) {
		case 1:
			return String.format("%d-%d-%d",
					date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE));
		case 2:
			String dayOfWeek;
			switch(date.get(Calendar.DAY_OF_WEEK)) {
			case 1:
				dayOfWeek = "일";
				break;
			case 2:
				dayOfWeek = "월";
				break;
			case 3:
				dayOfWeek = "화";
				break;
			case 4:
				dayOfWeek = "수";
				break;
			case 5:
				dayOfWeek = "목";
				break;
			case 6:
				dayOfWeek = "금";
				break;
			case 7:
				dayOfWeek = "토";
				break;
			default:
				dayOfWeek = "";
				break;
			}
			
			return String.format("%s년 %d월 %d일 %s요일",
					Integer.toString(date.get(Calendar.YEAR)).substring(2),
					date.get(Calendar.MONTH),
					date.get(Calendar.DATE),
					dayOfWeek);
		case 3:
			String amOrPm;
			if (date.get(date.get(Calendar.AM_PM)) == 0) {
				amOrPm = "오전";
			} else {
				amOrPm = "오후";
			}
			
			return String.format("%d-%d-%d %d:%d:%d %s",
					date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE),
					date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), date.get(Calendar.SECOND),
					amOrPm);
		default:
			throw new Exception("잘못된 인자를 입력했습니다");
		}
	}
}
