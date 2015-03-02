package org.nightschool.dao;

import org.apache.ibatis.session.SqlSession;
import org.nightschool.model.CartItem;
import org.nightschool.model.Disk;
import org.nightschool.mybatis.DBUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thoughtworks on 12/26/14.
 */
@Deprecated
public class CartDao {

//    public void addToCart(Disk disk) {
//        int i = 0;
//        for(i = 0; i < cart.size(); i++)
//        {
//            if(cart.get(i).getName().equals(disk.getName())) {
//                break;
//            }
//        }
//
//        if(i == cart.size()) {
//            cart.addToCart(disk);
//        }
//        else {
//            cart.get(i).setNumber(cart.get(i).getNumber() + disk.getNumber());
//        }
//    }

    public List<CartItem> cartList() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtil.getSqlSession();
            List<CartItem> cartItemList = sqlSession.selectList("Cart.queryAll");
            return cartItemList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            sqlSession.close();
        }
    }
}
