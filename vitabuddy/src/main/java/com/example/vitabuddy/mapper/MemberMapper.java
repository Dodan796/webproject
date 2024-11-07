package com.example.vitabuddy.mapper;

import com.example.vitabuddy.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void insertMember(MemberDTO member);  // 어노테이션 없이 메서드만 선언
}
