package com.example.vitabuddy.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.vitabuddy.dto.MemberDTO;

@Repository
public class MemberDAO {

    @Autowired
    private SqlSession sqlSession;

    public void insertMember(MemberDTO member) {
        sqlSession.insert("MemberMapper.insertMember", member);
    }

    public MemberDTO getUserById(String userId) {
        return sqlSession.selectOne("MemberMapper.selectUserById", userId);
    }
    
    public MemberDTO getUserByEmail(String userEmail) {
        return sqlSession.selectOne("MemberMapper.selectUserByEmail", userEmail);
    }
    
    public String getPasswordByUserId(String userId) {
        return sqlSession.selectOne("MemberMapper.selectPasswordByUserId", userId);
    }
}