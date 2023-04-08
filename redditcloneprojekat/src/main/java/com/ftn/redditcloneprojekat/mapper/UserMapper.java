package com.ftn.redditcloneprojekat.mapper;

import com.ftn.redditcloneprojekat.dto.UserDTO;
import com.ftn.redditcloneprojekat.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    UserDTO mapUserToDto(User user);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    User mapDtoToUser(UserDTO userDto);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findOneByUsername(String username);

    @Update("UPDATE users SET password = #{password} WHERE username = #{username}")
    void updatePassword(@Param("username") String username, @Param("password") String password);

}
