import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.connectordb.client.Connection;

public class TestConnection {
	Connection conn = new Connection("https://connectordb.com");

	@Test
	public void TestHello() {
		assertEquals(conn.Hello(),"Hello World!");
	}
}
