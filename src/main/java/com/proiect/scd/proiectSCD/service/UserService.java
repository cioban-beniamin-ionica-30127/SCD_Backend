package com.proiect.scd.proiectSCD.service;

import com.proiect.scd.proiectSCD.dtos.UserDTO;
import com.proiect.scd.proiectSCD.entity.User;

public interface UserService {
    User findUserById(Long id);
    User findUserByUsername(String username);
    User saveUser(User user);
    boolean deleteUserById(Long id);

    User mapDTOtoUser(UserDTO userDTO);
}
