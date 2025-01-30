package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.RegisterDTO;
import com.dictionaryapp.model.dto.UserDTO;
import com.dictionaryapp.model.entity.User;

import java.util.Optional;

public interface UserService {

     UserDTO findUserByUsername(String username);

     UserDTO findUserByEmail(String email) ;

     boolean checkCredentials(String username, String password);

     void login(String username) ;

     void register(RegisterDTO registerDTO) ;

     void logout() ;

     void initAdmin();

     Optional<User> findUserById(Long id);

     void initTest();

}
