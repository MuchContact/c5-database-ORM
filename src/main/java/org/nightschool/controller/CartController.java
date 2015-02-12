package org.nightschool.controller;

import org.nightschool.dao.CartDao;
import org.nightschool.mapper.CartMapper;
import org.nightschool.model.CartItem;
import org.nightschool.model.Disk;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.nightschool.wrapper.MybatisWrapper.getMapper;

/**
 * Created by Thoughtworks on 12/26/14.
 */
@Path("/cart")
public class CartController {
    CartDao cartDao = new CartDao();

    @GET
    public List<Disk> list() {
        return cartDao.cartList();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public int add(int diskId, String username) {
//        cartDao.add(disk);
        //TODO get user information, such as username
        CartMapper mapper = getMapper(CartMapper.class);
        return mapper.add(diskId, 1, username);
    }

    @GET
    @Path("query")
    public List<CartItem> listAll(String username, String token) {
        try {
            permissionValidate();
        } catch (IllegalAccessError illegalAccessError) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        CartMapper mapper = getMapper(CartMapper.class);
        return mapper.queryAll(username);
    }

    private void permissionValidate() throws IllegalAccessError {
//        throw new IllegalAccessError("权限不足，无法操作!");
    }

    @POST
    @Path("modify")
    @Consumes(MediaType.APPLICATION_JSON)
    public int modify(int primaryIdentity, int quantity, String username, String token) {
        CartMapper mapper = getMapper(CartMapper.class);
        return mapper.modify(primaryIdentity, quantity);
    }

    public CartItem listById(int primaryIdentity) {
        return null;
    }
}
