package org.bigsnow.sandbox.smoketest.hbase;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author dwinters
 */
public class App2Test {

	// Values used by tests.
	private static final String tableName = "device_state";
	private static final byte[] columnFamilyBytes = Bytes.toBytes("cf1");

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HBaseAdmin admin = new HBaseAdmin(getConfiguration());
		try {
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
			HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
			HColumnDescriptor columnDescriptor = new HColumnDescriptor(columnFamilyBytes);
			tableDescriptor.addFamily(columnDescriptor);
			admin.createTable(tableDescriptor);
		} finally {
			admin.close();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPutAndScan() throws IOException {
		// Values used for test.
		final String rowKeyValue = "alpha";
		final byte[] columnQualifierBytes = Bytes.toBytes("resolution");
		final String resolutionValue = "1024x768";

		HTableInterface table = getTable(tableName);
		ResultScanner rs = null;
		try {
			Put p = new Put(Bytes.toBytes(rowKeyValue));
			p.add(columnFamilyBytes, columnQualifierBytes, Bytes.toBytes(resolutionValue));
			table.put(p);
			Scan s = new Scan();
			rs = table.getScanner(s);
			int rsCount = 0;
			for (Result r : rs) {
				rsCount++;
				String rowKey = Bytes.toString(r.getRow());
				assertEquals("row key does not match", rowKeyValue, rowKey);
				String resolution = Bytes.toString(r.getValue(columnFamilyBytes, columnQualifierBytes));
				assertEquals("resolution value does not match", resolutionValue, resolution);
			}
			assertEquals("Incorrect number of results returned", 1, rsCount);
		} finally {
			if (rs != null) { rs.close(); }
			table.close();
		}
		assertTrue(true);
	}

	/*
	 * ============================================================================================
	 * Helper Methods
	 * ============================================================================================
	 */

	/**
	 * @param tableName TODO
	 * @return
	 * @throws IOException
	 */
	private HTableInterface getTable(String tableName) throws IOException {
		Configuration conf = getConfiguration();
		HTableInterface table = new HTable(conf, tableName);
		return table;
	}

	/**
	 * TODO
	 * @return
	 */
	private static Configuration getConfiguration() {
		Configuration conf = new Configuration();
		return conf;
	}
}
