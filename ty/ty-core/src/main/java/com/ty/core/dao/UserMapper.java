package com.ty.core.dao;

import com.ty.core.vo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("userMapper")
public interface UserMapper {
    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into user (id, add_time, ",
        "delete_status, password, ",
        "true_name, user_name)",
        "values (#{id,jdbcType=BIGINT}, #{addTime,jdbcType=TIMESTAMP}, ",
        "#{deleteStatus,jdbcType=BIT}, #{password,jdbcType=VARCHAR}, ",
        "#{trueName,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR})"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
        "select",
        "id, add_time, delete_status, password, true_name, user_name",
        "from user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="add_time", property="addTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.BIT),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="true_name", property="trueName", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="id",property="roleSet",many=@Many(select="com.ty.core.dao.RoleMapper.selectRoleByUid"))
    })
    User selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set add_time = #{addTime,jdbcType=TIMESTAMP},",
          "delete_status = #{deleteStatus,jdbcType=BIT},",
          "password = #{password,jdbcType=VARCHAR},",
          "true_name = #{trueName,jdbcType=VARCHAR},",
          "user_name = #{userName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);

    @Select({
            "select",
            "id, add_time, delete_status, password, true_name, user_name",
            "from user",
            "where user_name = #{userName,jdbcType=VARCHAR} and password = #{password,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="add_time", property="addTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.BIT),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="true_name", property="trueName", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR)
    })
    User login(@Param("userName") String userName,@Param("password")String password);

    @Select({
            "select",
            "id, add_time, delete_status, password, true_name, user_name",
            "from user",
            "where user_name = #{userName,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="add_time", property="addTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.BIT),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="true_name", property="trueName", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR)
    })
    User findUserByName(@Param("userName") String userName);

    @Select({
            "select",
            "*",
            "from user",
            "limit #{currentPage},#{length}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="add_time", property="addTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.BIT),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="true_name", property="trueName", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR)
    })
    List<User> findUserPage(@Param("length") Integer length, @Param("currentPage") Integer currentPage);

    @Select({
            "select",
            "count(*)",
            "from user"
    })
    int findUserCount();
}