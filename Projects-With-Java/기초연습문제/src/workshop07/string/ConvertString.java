package workshop07.string;

public class ConvertString {
	public String convert(String str) {
		StringBuffer sb = new StringBuffer(str);
		int index = str.length() - 1;
		int count = 0;
		while(index >= 0) {
			if (count == 2) {
				sb.replace(index, index, ","
						+ sb.substring(index, index));
				count = 0;
				index--;
			}
			index--;
			count++;
		}
		return sb.toString();
	}
}
