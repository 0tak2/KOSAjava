package ch9;

public class StringBufferEx1_1 {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("μλ");
		
		sb.append(" μΈκ³");
		
		System.out.println(sb);
	}
}
