package com.example.vitabuddy.service;

import com.example.vitabuddy.dao.IRefreshDAO;
import com.example.vitabuddy.model.RefreshVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshService {
    @Autowired
    private IRefreshDAO dao;

    public Boolean existsByRefresh(String refreshToken) {
        return dao.existsByRefresh(refreshToken);
    }

    public void deleteByRefresh(String refreshToken) {
        dao.deleteByRefresh(refreshToken);
    }

    // 여기에서 DAO를 통해 refreshToken을 저장하는 로직을 구현
    public void saveRefreshToken(RefreshVO refreshVO) {
        dao.saveRefreshToken(refreshVO);
    }

}
