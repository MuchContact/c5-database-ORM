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

    @POST
    @Path("addToCart")
    @Consumes(MediaType.APPLICATION_JSON)
    public int addToCart(int diskId, String username) {
        //TODO get user information, such as username
        CartMapper mapper = getMapper(CartMapper.class);
        int result = mapper.add(diskId, 1, username);
        if (result<1)
            throw new WebApplicationException(Response.Status.CONFLICT);
        return result;
    }

    @GET
    @Path("query")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    public List<CartItem> queryAll(String username, String token) {
        try {
            permissionValidate();
        } catch (IllegalAccessError illegalAccessError) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        CartMapper mapper = getMapper(CartMapper.class);
        List<CartItem> cartItemList = mapper.queryAll("twer");
        return cartItemList;
    }

    private void permissionValidate() throws IllegalAccessError {
//        throw new IllegalAccessError("权限不足，无法操作!");
    }

    @POST
    @Path("modifyQuantity")
    @Consumes(MediaType.APPLICATION_JSON)
    public int modifyQuantity(int primaryIdentity, int quantity, String username, String token) {
        CartMapper mapper = getMapper(CartMapper.class);
        return mapper.modify(primaryIdentity, quantity);
    }

    @DELETE
    @Path("delete")
    public int deleteFromCart(int diskId, String username, String token) {
        CartMapper mapper = getMapper(CartMapper.class);
        return mapper.delete(diskId);
    }
}
