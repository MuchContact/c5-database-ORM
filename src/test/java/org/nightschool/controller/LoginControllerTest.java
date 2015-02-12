package org.nightschool.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.nightschool.mapper.LoginMapper;
import org.nightschool.model.User;

import javax.ws.rs.WebApplicationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.nightschool.wrapper.MybatisWrapper.getMapper;

/**
 * Created by Administrator on 15-2-5.
 */
public class LoginControllerTest {

    private final LoginController loginController = new LoginController();
    private final User user;

    public LoginControllerTest() {
        user = new User();
        user.setUsername("hello");
        user.setPassword("psdForHello");
    }

    @Test
    public void should_register_with_username_hello_password_psdForHello() {
        assertThat(loginController.register(user), is(1));
    }


    @Test
    public void should_give_tips_that_username_existed() {
        loginController.register(user);
        assertThat(loginController.checkUserNameExisted(user.getUsername()), is(true));
    }

    @Test(expected = WebApplicationException.class)
    public void should_tips_username_or_password_error() {
        loginController.register(user);
        User fakeUser = new User();
        fakeUser.setUsername(user.getUsername());
        fakeUser.setPassword("2dlik@oel");
        loginController.login(fakeUser);
    }

    @Test
    public void should_login_success() {
        loginController.register(user);
        User loginResponse = loginController.login(user);
        assertEquals(loginResponse.getUsername(), "hello");
        assertEquals(loginResponse.getType(), 0);
    }

    @After
    public void tearDown() throws Exception {
        LoginMapper mapper = getMapper(LoginMapper.class);
        mapper.deregister(user);
    }
}
