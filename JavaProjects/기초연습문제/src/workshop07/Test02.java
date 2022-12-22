package workshop07;

public class Test02 {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("JAVAprogram");
		sb.append("mer");
		sb.setCharAt(4, 'P');
		String str = sb.toString();
		
		System.out.println(str);
	}
}
