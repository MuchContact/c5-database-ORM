package org.nightschool.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.nightschool.model.Disk;

import java.util.List;

/**
 * Created by Administrator on 15-1-10.
 */
public interface DiskMapper {
    @Select("select * from disks")
    public List<Disk> getDisks();

    @Insert("INSERT INTO disks VALUES(#{id}, #{name},#{imgUrl},#{desc},#{primaryPrice},#{discountedPrice},#{stockSize},#{shopKeeper})")
    public boolean insert(Disk disk);
}
