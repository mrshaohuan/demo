package com.ty.core.dao;

import com.ty.core.vo.Role;
import org.apache.ibatis.jdbc.SQL;

public class RoleSqlProvider {

    public String insertSelective(Role record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("role");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getAddTime() != null) {
            sql.VALUES("add_time", "#{addTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDeleteStatus() != null) {
            sql.VALUES("delete_status", "#{deleteStatus,jdbcType=BIT}");
        }
        
        if (record.getDisplay() != null) {
            sql.VALUES("display", "#{display,jdbcType=BIT}");
        }
        
        if (record.getInfo() != null) {
            sql.VALUES("info", "#{info,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleCode() != null) {
            sql.VALUES("role_code", "#{roleCode,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleName() != null) {
            sql.VALUES("role_name", "#{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getSequence() != null) {
            sql.VALUES("sequence", "#{sequence,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Role record) {
        SQL sql = new SQL();
        sql.UPDATE("role");
        
        if (record.getAddTime() != null) {
            sql.SET("add_time = #{addTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDeleteStatus() != null) {
            sql.SET("delete_status = #{deleteStatus,jdbcType=BIT}");
        }
        
        if (record.getDisplay() != null) {
            sql.SET("display = #{display,jdbcType=BIT}");
        }
        
        if (record.getInfo() != null) {
            sql.SET("info = #{info,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleCode() != null) {
            sql.SET("role_code = #{roleCode,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleName() != null) {
            sql.SET("role_name = #{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getSequence() != null) {
            sql.SET("sequence = #{sequence,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}