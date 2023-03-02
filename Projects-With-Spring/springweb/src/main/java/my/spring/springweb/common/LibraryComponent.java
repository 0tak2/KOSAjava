package my.spring.springweb.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

// ApplicationContext에 수동으로 빈 등록
@Configuration // 일단 이 클래스를 빈으로 등록
public class LibraryComponent {

	// 그럼 컨텍스트가 이 클래스를 스캔
	
	@Bean // 아래 메서드의 리턴 객체를 빈으로 등록
	public Gson getGson() { // 메서드 명이 빈 ID
		return new Gson();
	}
}

// 이러한 수동 빈 등록은 Gson이 아니더라도 여러 용도로 많이 사용됨
