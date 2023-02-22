package springioc.sample6;

import java.io.File;
import java.io.FileWriter;

public class FileOutput implements Output {

	private String filePath;
	
	public FileOutput() {
		System.out.println("[FileOutput] 기본 생성자 호출됨");
	}
	
	public FileOutput(String filePath) {
		this.filePath = filePath;
		System.out.println("[FileOutput] 생성자 호출됨: " + filePath);
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
		System.out.println("[FileOutput] 세터 호출됨: " + filePath);
	}

	@Override
	public void print(String message) throws Exception {
		System.out.println("[FileOutput] print 메서드 호출됨: " + message);
		
		FileWriter out = new FileWriter(filePath); // PrintWriter와 비슷. 파일로 향하는 스트림
		out.write(message);
		out.close();
		
		System.out.println("[FileOutput] print 메서드 종료됨: " + message + "@" + filePath);
	}
}
