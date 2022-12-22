package workshop07.date;

import java.util.Calendar;

public class DateTest {
	public static void main(String[] args) {
		Calendar date = Calendar.getInstance();
		ConvertDate dateStrGenerator = new ConvertDate();
		try {
			System.out.println(dateStrGenerator.convert(date, 1));
			System.out.println(dateStrGenerator.convert(date, 2));
			System.out.println(dateStrGenerator.convert(date, 3));
		} catch (Exception e) {
			System.err.println("예외가 발생했습니다. " + e);
		}
	}
}
