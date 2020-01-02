package com.ibranco.mybatis.dao.impl;

import com.ibranco.mybatis.dao.UserDao;
import com.ibranco.mybatis.entity.QueryVo;
import com.ibranco.mybatis.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public List<User> findAll() {
        SqlSession sqlSession = factory.openSession();
        List<User> list = sqlSession.selectList("com.ibranco.mybatis.dao.UserDao.findAll");
        sqlSession.close();
        return list;
    }

    public void saveUser(User user) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.insert("com.ibranco.mybatis.dao.UserDao.saveUser",user);
        sqlSession.commit();
        sqlSession.close();

    }

    public Integer updateUser(User user) {
        SqlSession sqlSession = factory.openSession();
        int update = sqlSession.update("com.ibranco.mybatis.dao.UserDao.updateUser", user);
        sqlSession.commit();
        sqlSession.close();
        return update;
    }

    public User findByID(Integer id) {

        return null;
    }

    public Integer deleteById(Integer id) {
        SqlSession sqlSession = factory.openSession();
        int update = sqlSession.update("com.ibranco.mybatis.dao.UserDao.deleteById", id);
        sqlSession.commit();
        sqlSession.close();
        return update;
    }

    public List<User> findByUserName(User user) {
        return null;
    }

    public List<User> findByUserName2(String user) {
        return null;
    }

    public Integer getCount() {
        return null;
    }

    public List<User> findByVo(QueryVo queryVo) {
        return null;
    }
}
