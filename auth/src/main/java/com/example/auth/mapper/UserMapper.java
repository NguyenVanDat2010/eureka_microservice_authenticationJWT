package com.example.auth.mapper;

import com.example.auth.entity.AppUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    AppUser findById(Long id);

    AppUser findByUsername(String username);

    List<AppUser> findAll();

    int insert(AppUser user);
}
