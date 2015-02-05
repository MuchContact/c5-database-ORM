package org.nightschool.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 15-2-2.
 */
public interface GlobalMapper {
    @Delete("delete from ${value}")
    public void clearTable(String table);

    @Select("select count(*) from ${value}")
    public int count(String table);
}
