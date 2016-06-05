import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.connectordb.client.ConnectorDB;

public class TestConnection {
	ConnectorDB cdb= new ConnectorDB("test","test","http://localhost:8000");

	@Test
	public void TestPing() throws Exception {
		assertEquals(cdb.ping(),"test/user");
	}
}
