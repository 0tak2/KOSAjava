package lecture.jdbc.di.step5;

import java.sql.Connection;

public interface ConnectionMaker {

	/* public abstract */ Connection getConnection(); // 자동으로 컴파일러가 붙여줌
}
