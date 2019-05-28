package com.ty.core.dao;

import com.ty.core.vo.Res;
import org.apache.ibatis.jdbc.SQL;

public class ResSqlProvider {

    public String insertSelective(Res record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("res");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getAddTime() != null) {
            sql.VALUES("add_time", "#{addTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDeleteStatus() != null) {
            sql.VALUES("delete_status", "#{deleteStatus,jdbcType=BIT}");
        }
        
        if (record.getInfo() != null) {
            sql.VALUES("info", "#{info,jdbcType=VARCHAR}");
        }
        
        if (record.getResName() != null) {
            sql.VALUES("res_name", "#{resName,jdbcType=VARCHAR}");
        }
        
        if (record.getSequence() != null) {
            sql.VALUES("sequence", "#{sequence,jdbcType=INTEGER}");
        }
        
        if (record.getSysType() != null) {
            sql.VALUES("sys_type", "#{sysType,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            sql.VALUES("value", "#{value,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Res record) {
        SQL sql = new SQL();
        sql.UPDATE("res");
        
        if (record.getAddTime() != null) {
            sql.SET("add_time = #{addTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDeleteStatus() != null) {
            sql.SET("delete_status = #{deleteStatus,jdbcType=BIT}");
        }
        
        if (record.getInfo() != null) {
            sql.SET("info = #{info,jdbcType=VARCHAR}");
        }
        
        if (record.getResName() != null) {
            sql.SET("res_name = #{resName,jdbcType=VARCHAR}");
        }
        
        if (record.getSequence() != null) {
            sql.SET("sequence = #{sequence,jdbcType=INTEGER}");
        }
        
        if (record.getSysType() != null) {
            sql.SET("sys_type = #{sysType,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            sql.SET("value = #{value,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=BIGINT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}