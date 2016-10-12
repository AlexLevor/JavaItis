package ru.itis.dao;

import org.junit.Before;
import org.junit.Test;
import ru.itis.models.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by user on 07.10.2016.
 */
public class UsersDaoFileBasedImplTestGet {
    private UsersDaoFileBasedImpl usersDao;
    @Before
    public void setUp() throws Exception {
        usersDao = new UsersDaoFileBasedImpl();
    }

    @Test
    public void get() throws Exception {
        assertEquals(654851815, usersDao.get(654851815).getId());

    }

}