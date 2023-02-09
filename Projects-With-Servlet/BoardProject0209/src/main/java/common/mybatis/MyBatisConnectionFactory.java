package common.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConnectionFactory {
   private static SqlSessionFactory sqlSessionFactory;
   
   static {
      
      String resource = "./SqlMapConfig.xml"; 
      try {
         Reader reader = Resources.getResourceAsReader(resource);
         
         if( sqlSessionFactory == null ) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
         }
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public static SqlSessionFactory getSqlSessionFactory() {
      return sqlSessionFactory;
   }
}
