package org.nightschool.mapper;

import org.apache.ibatis.annotations.*;
import org.nightschool.model.CartItem;
import org.nightschool.model.Disk;

import java.util.List;

/**
 * Created by Administrator on 15-2-2.
 */
public interface CartMapper {
    @Select("select id, 'diskId', quantity from cart where user=#{username}")
    @Results(value = @Result(property = "disk", column = "diskId", one = @One(select = "getDisk")))
    public List<CartItem> queryAll(String username);

    @Select("select * from disks where id=#{diskId}")
    Disk getDisk(int diskId);

    @Select("select * from cart where id=#{primaryIdentity}")
    public CartItem queryOne(int primaryIdentity);

    @Insert("insert into cart(\"diskId\", quantity, \"user\") values(#{diskIdentity}, #{quantity}, #{username})")
    public int add(@Param("diskIdentity") int diskIdentity, @Param("quantity") int quantity, @Param("username") String username);

    @Update("update cart set quantity=#{quantity} where id=#{primaryIdentity}")
    public int modify(@Param("primaryIdentity") int primaryIdentity, @Param("quantity") int quantity);

    @Delete("delete from cart where id=#{primaryIdentity}")
    public int delete(int primaryIdentity);
}
