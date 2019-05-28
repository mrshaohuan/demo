package com.ty.core.dao;

import com.ty.core.vo.Res;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("resMapper")
public interface ResMapper {
    @Delete({
        "delete from res",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into res (id, add_time, ",
        "delete_status, info, ",
        "res_name, sequence, ",
        "sys_type, type, value, ",
        "parent_id)",
        "values (#{id,jdbcType=BIGINT}, #{addTime,jdbcType=TIMESTAMP}, ",
        "#{deleteStatus,jdbcType=BIT}, #{info,jdbcType=VARCHAR}, ",
        "#{resName,jdbcType=VARCHAR}, #{sequence,jdbcType=INTEGER}, ",
        "#{sysType,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, #{value,jdbcType=VARCHAR}, ",
        "#{parentId,jdbcType=BIGINT})"
    })
    int insert(Res record);

    @InsertProvider(type=ResSqlProvider.class, method="insertSelective")
    int insertSelective(Res record);

    @Select({
        "select",
        "id, add_time, delete_status, info, res_name, sequence, sys_type, type, value, ",
        "parent_id",
        "from res",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="add_time", property="addTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.BIT),
        @Result(column="info", property="info", jdbcType=JdbcType.VARCHAR),
        @Result(column="res_name", property="resName", jdbcType=JdbcType.VARCHAR),
        @Result(column="sequence", property="sequence", jdbcType=JdbcType.INTEGER),
        @Result(column="sys_type", property="sysType", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),

    })
    Res selectByPrimaryKey(Long id);

    @UpdateProvider(type=ResSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Res record);

    @Update({
        "update res",
        "set add_time = #{addTime,jdbcType=TIMESTAMP},",
          "delete_status = #{deleteStatus,jdbcType=BIT},",
          "info = #{info,jdbcType=VARCHAR},",
          "res_name = #{resName,jdbcType=VARCHAR},",
          "sequence = #{sequence,jdbcType=INTEGER},",
          "sys_type = #{sysType,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "value = #{value,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Res record);

    @Select({
            "select",
            "*",
            "from res",
            "limit #{currentPage},#{length}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="add_time", property="addTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.BIT),
            @Result(column="info", property="info", jdbcType=JdbcType.VARCHAR),
            @Result(column="res_name", property="resName", jdbcType=JdbcType.VARCHAR),
            @Result(column="sequence", property="sequence", jdbcType=JdbcType.INTEGER),
            @Result(column="sys_type", property="sysType", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
    })
    List<Res> findUserPage(@Param("length") Integer length, @Param("currentPage") Integer currentPage);

    @Select({
            "select",
            "count(*)",
            "from res"
    })
    int findUserCount();

    @Select({
            "select",
            "re.*",
            "from res re left join role_res rr on re.id = rr.res_id",
            "where rr.role_id=#{roleId}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="add_time", property="addTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.BIT),
            @Result(column="info", property="info", jdbcType=JdbcType.VARCHAR),
            @Result(column="res_name", property="resName", jdbcType=JdbcType.VARCHAR),
            @Result(column="sequence", property="sequence", jdbcType=JdbcType.INTEGER),
            @Result(column="sys_type", property="sysType", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
    })
    List<Res> selectResByRoleId(long roleId);
}