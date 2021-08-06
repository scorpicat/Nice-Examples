package pojo;

import org.apache.ibatis.type.Alias;

/**
 * User对象实体类
 */
//可以使用@Alias("<别名>")来给pojo起别名，或者在配置文件中配置
public class User {
    /**
     * 属性名最好与数据库表中的字段名保持一致，如果不一致，需要在mapper.xml中对返回的结果使用<resultMap>进行映射
     */
    private int id;
    private String username;//这里与数据库表不一致，用于演示resultMap标签的映射
    private String pwd;

    public User(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public User(int id, String username, String pwd) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
    }

    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
