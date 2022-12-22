package workshop07;

public class Test05 {
	public static void main(String[] args) {
		String str = "   [Beyond Promise.]   ";
		System.out.println(str.replaceAll(" ", "_"));
		System.out.println(str.trim());
		System.out.println(str.trim().toUpperCase());
		System.out.println(str.trim().toLowerCase());
		System.out.println(str.trim().replaceAll("e", "a"));
		System.out.println(str.trim().substring(8, 15));
	}
}
