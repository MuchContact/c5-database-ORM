package org.nightschool.controller;

import org.nightschool.mapper.LoginMapper;
import org.nightschool.model.User;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static org.nightschool.wrapper.MybatisWrapper.getMapper;

/**
 * Created by Administrator on 15-2-5.
 */
@Path("/user")
public class LoginController {

    private final LoginMapper mapper = getMapper(LoginMapper.class);

    @POST
    @Path("/register")
    public int register(User user) {
        return mapper.register(user);
    }

    @POST
    @Path("/login")
    public User login(User user) {
        if(mapper.login(user) == null )
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        return mapper.login(user);
    }

    @POST
    @Path("/checkUsername")
    public boolean checkUserNameExisted(String username) {
        return mapper.checkUserNameExisted(username) == null ? false : true;
    }
}
