package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Mybatis工具类
 */
public class MybatisUtils {
    /*
    创建SqlSessionFactroy工程实例,在ssm整合项目中可以直接在spring的配置文件中配置
     */
    private static SqlSessionFactory sqlSessionFactory;
    static{
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取sqlSession实例
     * @return sqlSession实例
     */
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
