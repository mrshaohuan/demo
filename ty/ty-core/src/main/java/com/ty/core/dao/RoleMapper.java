package com.ty.core.dao;

import com.ty.core.vo.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component("roloMaper")
public interface RoleMapper {
    @Delete({
        "delete from role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into role (id, add_time, ",
        "delete_status, display, info, ",
        "role_code, role_name, ",
        "sequence, type)",
        "values (#{id,jdbcType=BIGINT}, #{addTime,jdbcType=TIMESTAMP}, ",
        "#{deleteStatus,jdbcType=BIT}, #{display,jdbcType=BIT}, #{info,jdbcType=VARCHAR}, ",
        "#{roleCode,jdbcType=VARCHAR}, #{roleName,jdbcType=VARCHAR}, ",
        "#{sequence,jdbcType=INTEGER}, #{type,jdbcType=VARCHAR})"
    })
    int insert(Role record);

    @InsertProvider(type=RoleSqlProvider.class, method="insertSelective")
    int insertSelective(Role record);

    @Select({
        "select",
        "id, add_time, delete_status, display, info, role_code, role_name, sequence, ",
        "type",
        "from role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="add_time", property="addTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.BIT),
        @Result(column="display", property="display", jdbcType=JdbcType.BIT),
        @Result(column="info", property="info", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_code", property="roleCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="sequence", property="sequence", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="res_id",property="getResSet",many=@Many(select="com.ty.core.dao.ResMapper.selectByPrimaryKey"))
    })
    Role selectByPrimaryKey(Long id);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    @Update({
        "update role",
        "set add_time = #{addTime,jdbcType=TIMESTAMP},",
          "delete_status = #{deleteStatus,jdbcType=BIT},",
          "display = #{display,jdbcType=BIT},",
          "info = #{info,jdbcType=VARCHAR},",
          "role_code = #{roleCode,jdbcType=VARCHAR},",
          "role_name = #{roleName,jdbcType=VARCHAR},",
          "sequence = #{sequence,jdbcType=INTEGER},",
          "type = #{type,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Role record);

    @Select({
            "select",
            "r.id, r.add_time, r.delete_status, r.display, r.role_code, r.role_name, r.sequence, ",
            "r.type",
            "from role r,user_role ur",
            "where r.id = ur.id and ur.uid= #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="add_time", property="addTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.BIT),
            @Result(column="display", property="display", jdbcType=JdbcType.BIT),
            @Result(column="role_code", property="roleCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="sequence", property="sequence", jdbcType=JdbcType.INTEGER),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    List<Role> selectByUserId(Long id);

    @Select({
            "select",
            "*",
            "from role",
            "limit #{length},#{currentPage}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="add_time", property="addTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.BIT),
            @Result(column="display", property="display", jdbcType=JdbcType.BIT),
            @Result(column="role_code", property="roleCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="sequence", property="sequence", jdbcType=JdbcType.INTEGER),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    List<Role> findRolePage(@Param("length") Integer length, @Param("currentPage") Integer currentPage);

    @Select({
            "select",
            "count(*)",
            "from role"
    })
    int findRoleCount();

    @Select({
            "select",
            "r.*",
            "from role r left join user_role ur on r.id=ur.role_id",
            "where ur.u_id=#{uid}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="add_time", property="addTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.BIT),
            @Result(column="display", property="display", jdbcType=JdbcType.BIT),
            @Result(column="role_code", property="roleCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="sequence", property="sequence", jdbcType=JdbcType.INTEGER),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="id",property="resSet",many=@Many(select="com.ty.core.dao.ResMapper.selectResByRoleId"))
    })
    Set<Role> selectRoleByUid(long uid);

}