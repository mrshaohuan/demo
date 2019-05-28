package com.ty.core.dao;

import com.ty.core.vo.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getAddTime() != null) {
            sql.VALUES("add_time", "#{addTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDeleteStatus() != null) {
            sql.VALUES("delete_status", "#{deleteStatus,jdbcType=BIT}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getTrueName() != null) {
            sql.VALUES("true_name", "#{trueName,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            sql.VALUES("user_name", "#{userName,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("user");
        
        if (record.getAddTime() != null) {
            sql.SET("add_time = #{addTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDeleteStatus() != null) {
            sql.SET("delete_status = #{deleteStatus,jdbcType=BIT}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getTrueName() != null) {
            sql.SET("true_name = #{trueName,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            sql.SET("user_name = #{userName,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}