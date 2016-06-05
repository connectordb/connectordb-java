package com.connectordb.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.connectordb.client.ConnectorDB;
import com.connectordb.client.User;
import com.connectordb.client.RequestFailedException;

public class TestConnection {

    ConnectorDB cdb= new ConnectorDB("test","test","http://localhost:8000");

    @Test
    public void TestPing() throws Exception {
        assertEquals(cdb.ping(),"test/user");
    }

    @Test
    public void TestUser() throws Exception {
        User usr = cdb.getUser("test");
        assertEquals(usr.name,"test");
    }

    @Test(expected=RequestFailedException.class)
    public void TestNonexistentUser() throws Exception {
        cdb.getUser("not_a_user");
    }
}
