package com.fse.Authorization.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fse.Authorization.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
}
