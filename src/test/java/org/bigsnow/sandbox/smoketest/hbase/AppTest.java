package org.bigsnow.sandbox.smoketest.hbase;

import java.io.IOException;

//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.client.HTable;
//import org.apache.hadoop.hbase.client.HTableInterface;
//import org.apache.hadoop.hbase.client.Put;
//import org.apache.hadoop.hbase.util.Bytes;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest( String testName ) {
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite( AppTest.class );
	}

	/**
	 * Rigourous Test :-)
	 *
	 * @throws IOException 
	 */
	public void testApp() throws IOException {
//		Configuration conf = new Configuration();
//		HTableInterface table = new HTable(conf, "device_state");
//		Put p = new Put(Bytes.toBytes("rowKey"));
//		p.add(Bytes.toBytes("cf1"), Bytes.toBytes("resolution"), Bytes.toBytes("1024x768"));
//		table.put(p);
//		table.close();
		assertTrue( true );
	}
}
