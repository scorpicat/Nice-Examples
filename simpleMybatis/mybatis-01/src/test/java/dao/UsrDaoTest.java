package dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.User;
import utils.MybatisUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsrDaoTest {
    private SqlSession sqlSession = MybatisUtils.getSqlSession();

    private UserMapper getUserDao(){
        return sqlSession.getMapper(UserMapper.class);
    }
    @Test
    public void getUserbyId(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserbyId(2);
        System.out.println(user.getId()+" "+user.getUsername()+" "+user.getPwd());
    }


    @Test
    public void TestGetUserList(){
        try{
            //方式一
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUserList();

            //方式二
//            List<User> userList1 = sqlSession.selectList("dao.UserMapper.getUserList");
            for (User user : userList) {
                System.out.println(user.getId()+" "+user.getUsername()+" "+user.getPwd());
            }
//            for (User user : userList1) {
//                System.out.println(user.getId()+" "+user.getName()+" "+user.getPwd());
//            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void addUsertest(){
        int nums = getUserDao().addUser(new User("jack","jj"));
        if(nums>0){
            System.out.println("插入成功");
            sqlSession.commit();
        }else{
            System.out.println("插入失败");
            sqlSession.rollback();
        }
    }

    @Test
    public void updateUserTest(){
        int nums = getUserDao().updateUser(new User(1,"jess","shit"));
        if(nums>0){
            System.out.println("修改成功");
            sqlSession.commit();
        }else{
            System.out.println("修改失败");
            sqlSession.rollback();
        }
    }

    @Test
    public void deleteUserTest(){
        int nums = getUserDao().deleteUser(3);
        if(nums>0){
            System.out.println("删除成功");
            sqlSession.commit();
        }else{
            System.out.println("删除失败");
            sqlSession.rollback();
        }
    }

    @Test
    public void updateByMapTest(){
        Map map = new HashMap<String,String>();
        map.put("id","4");
        map.put("name","jessca");
        int nums = getUserDao().updateByMap(map);
        if(nums>0){
            System.out.println("更新成功");
            sqlSession.commit();
        }else{
            System.out.println("更新失败");
            sqlSession.rollback();
        }
    }

}
