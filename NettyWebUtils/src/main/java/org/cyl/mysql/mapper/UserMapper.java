package org.cyl.mysql.mapper;


import org.cyl.mysql.pojo.User;

import java.util.List;

public interface UserMapper {
    
    List<User> getUsers();

}