package org.nightschool.mapper;

import org.apache.ibatis.annotations.*;
import org.nightschool.model.CartItem;
import org.nightschool.model.Disk;

import java.util.List;

/**
 * Created by Administrator on 15-2-2.
 */
public interface CartMapper {
    @Select("select id, \"diskId\", quantity from cart where \"user\"=#{username}")
    @Results(value = {@Result(property = "disk", column = "diskId", javaType = Disk.class, one = @One(select = "getDisk")),
                      @Result(property = "id", column = "id"),
                      @Result(property = "quantity", column = "quantity")
    })
    public List<CartItem> queryAll(String username);

    @Select("select * from disks where id=#{diskId}")
    Disk getDisk(int diskId);

    @Insert("insert into cart(\"diskId\", quantity, \"user\") values(#{diskIdentity}, #{quantity}, #{username})")
    public int add(@Param("diskIdentity") int diskIdentity, @Param("quantity") int quantity, @Param("username") String username);

    @Update("update cart set quantity=#{quantity} where id=#{primaryIdentity}")
    public int modify(@Param("primaryIdentity") int primaryIdentity, @Param("quantity") int quantity);

    @Delete("delete from cart where id=#{primaryIdentity}")
    public int delete(int primaryIdentity);

}
