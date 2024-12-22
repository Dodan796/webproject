package com.example.vitabuddy.dto;

import com.example.vitabuddy.model.MemberVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserInfo implements UserDetails {

    private final MemberDTO dto;

    public UserInfo(MemberDTO dto) {
        this.dto = dto;
    }


    //1. 회원 & 관리자 역할을 받는 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return dto.getUserRole();
            }
        });

        return collection;
    }

    //2. Pwd를 반환하는 메서드
    @Override
    public String getPassword() {
        return dto.getUserPwd();
    }

    //3. Username를 반환하는 메서드
    @Override
    public String getUsername() {
        return dto.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
