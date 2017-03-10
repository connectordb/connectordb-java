package com.connectordb.test;

import com.connectordb.client.ConnectorDB;
import com.connectordb.client.Device;
import com.connectordb.client.RequestFailedException;
import com.connectordb.client.Stream;
import com.connectordb.client.User;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestConnection {

    ConnectorDB cdb = new ConnectorDB("test", "test", "http://localhost:3124");

    @Test
    public void TestPing() throws Exception {
        assertEquals(cdb.ping(), "test/user");
    }

    @Test
    public void TestUser() throws Exception {
        User usr = cdb.getUser("test");
        assertEquals(usr.getName(), "test");
    }

    @Test(expected = RequestFailedException.class)
    public void TestGetNonexistentUser() throws Exception {
        cdb.getUser("not_a_user");
    }

    @Test(expected = RequestFailedException.class)
    public void TestGetNonexistentDevice() throws Exception {
        cdb.getDevice("test/not_a_device");
    }

    @Test(expected = RequestFailedException.class)
    public void TestGetNonexistentStream() throws Exception {
        cdb.getStream("test/user/not_a_stream");
    }

    @Test(expected = RequestFailedException.class)
    public void TestDeleteNonexistentUser() throws Exception {
        cdb.deleteUser("not_a_user");
    }

    @Test(expected = RequestFailedException.class)
    public void TestDeleteNonexistentDevice() throws Exception {
        cdb.deleteDevice("test/not_a_device");
    }

    @Test(expected = RequestFailedException.class)
    public void TestDeleteNonexistentStream() throws Exception {
        cdb.deleteStream("test/user/not_a_stream");
    }

    @Test(expected = RequestFailedException.class)
    public void TestCreateExistingUser() throws Exception {
        User u = new User();
        u.setEmail("tee@hee");
        cdb.createUser("test", u);
    }

    @Test(expected = RequestFailedException.class)
    public void TestCRUD() throws Exception {
        String uname = "javatest";
        String dname = "javatest/testdevice";
        String sname = "javatest/testdevice/teststream";
        User u = new User();
        u.setEmail("tee@hee");
        cdb.createUser(uname, u);

        u = cdb.getUser(uname);
        assertEquals(u.getEmail(), "tee@hee");
        u.setEmail("foo@bar.java");
        cdb.updateUser(uname, u);
        u = cdb.getUser(uname);
        assertEquals(u.getEmail(), "foo@bar.java");

        cdb.createDevice(dname, new Device());
        Device d = cdb.getDevice(dname);
        String apikey = d.getApikey();
        d.resetApikey();
        cdb.updateDevice(dname, d);
        d = cdb.getDevice(dname);
        assertEquals(d.getApikey().equals(apikey), false);

        Stream s = new Stream();
        s.setSchema("{\"type\":\"string\"}");
        cdb.createStream(sname, s);

        s = cdb.getStream(sname);
        s.setDatatype("lol");
        cdb.updateStream(sname, s);
        s = cdb.getStream(sname);
        assertEquals(s.getDatatype(), "lol");

        boolean exists = true;
        cdb.deleteStream(sname);
        try {
            cdb.getStream(sname);
        } catch (RequestFailedException ex) {
            exists = false;
        }
        assertEquals(exists, false);

        cdb.deleteDevice(dname);
        exists = true;
        try {
            cdb.getDevice(dname);
        } catch (RequestFailedException ex) {
            exists = false;
        }
        assertEquals(exists, false);

        cdb.deleteUser(uname);
        exists = true;
        try {
            cdb.getUser(uname);
        } catch (RequestFailedException ex) {
            exists = false;
        }
        assertEquals(exists, false);

    }

    @Test
    public void TestInsert() throws Exception {
        String sname = "test/user/javastream";
        Stream s = new Stream();
        s.setSchema("{\"type\":\"string\"}");
        cdb.createStream(sname, s);
        cdb.insertJson(sname, "[{\"t\":123456,\"d\":\"Hello World!\"}]");
        cdb.deleteStream(sname);
    }

    @Test
    public void TestGetMostRecent() throws Exception {
        String sname = "test/user/javastream";
        Stream s = new Stream();
        s.setSchema("{\"type\":\"string\"}");
        cdb.createStream(sname, s);
        double ts = cdb.getMostRecentTimestamp("test/user/javastream");
        cdb.insertJson(sname, "[{\"t\":123456,\"d\":\"Hello World!\"}]");
        double ts2 = cdb.getMostRecentTimestamp("test/user/javastream");

        cdb.deleteStream(sname);
        assertEquals(ts, 0, 0.0001);
        assertEquals(ts2, 123456.0, 0.0001);
    }
}
