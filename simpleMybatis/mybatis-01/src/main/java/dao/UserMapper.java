package dao;

import org.apache.ibatis.annotations.Param;
import pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //增删改查
    List<User> getUserList();
    User getUserbyId(@Param("id") int id);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
    //最常用的是Map形式的参数
    int updateByMap(Map map);

}
