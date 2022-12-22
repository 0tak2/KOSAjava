package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Exam02_ObjectStream {
	public static void main(String[] args) {
		// 1. Stream을 통해 내보낼 HashMap을 간단하게 만들어 본다
		HashMap<String, String> map =
				new HashMap<String, String>();
		
		map.put("1", "홍길동");
		map.put("2", "신사임당");
		map.put("3", "강감찬");
		
		// 2. 실제 파일을 생성하려면 File 객체가 먼저 있어야 한다
		File file = new File("readme.txt");
		
		try {
			FileOutputStream fis = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(map);
		} catch (FileNotFoundException e) { // 혹은 그냥 Exception 하나만 처리하기.
		} catch (IOException e) { // 최상위 Exception으로 모두 받을 수 있음.
		}
	}
}
