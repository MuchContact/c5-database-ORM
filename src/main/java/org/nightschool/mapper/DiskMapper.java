package org.nightschool.mapper;

import org.apache.ibatis.annotations.Delete;
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

    @Insert("INSERT INTO disks VALUES(#{id}, #{name},#{imgUrl},#{description},#{primaryPrice},#{discountedPrice},#{stockSize},#{shopKeeper})")
    public int insert(Disk disk);

    public int reduceStockSize(int purchaseQuantity);

    public void searchByName(String name);

    @Delete("delete from disks where id=#{id}")
    public int delete(int id);

    public int modify(Disk disk);
}
