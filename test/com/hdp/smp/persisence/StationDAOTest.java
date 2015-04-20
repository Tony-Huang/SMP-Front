package com.hdp.smp.persisence;

import org.hibernate.Session;
import org.junit.*;

import com.hdp.smp.model.Monitor;
import com.hdp.smp.model.Station;
import com.hdp.smp.persistence.DAO;
import com.hdp.smp.persistence.HibernateUtil;

public class StationDAOTest {

	private static Session session;
	private static DAO stationDAO;
	private static Station s1 = new Station();
	private static Station s2 = new Station();

	private static Monitor m1 = new Monitor();
	private static Monitor m2 = new Monitor();

	@BeforeClass
	public static void setup() {
		session = HibernateUtil.getSession();
		s1.setName("station1");
		s1.setStatus("ACTIVE");

		s2.setName("station2");
		s2.setStatus("INACTIVE");

		stationDAO = new DAO();
	}

	@Test
	public void testSaveOrUpdate() {

		stationDAO.saveOrUpdate(s1, session);
		stationDAO.saveOrUpdate(s2, session);

		long actualCount = stationDAO.getCount(session,com.hdp.smp.model.Station.class);
		Assert.assertTrue(actualCount == 2L);
	}

	@Test
	public void testQuery() {

		Integer id = (Integer) stationDAO.getMaxId(session,com.hdp.smp.model.Station.class);
		if (id == null)
			return;
		Station station = (Station) stationDAO.load(session,com.hdp.smp.model.Station.class, id);

		// previous store station has no associated monitors. see
		// testSaveOrUpdate()
		Monitor monitor = station.getActiveMonitor();

		Assert.assertNull(monitor);
	}

	@Test
	public void testSaveStationNmonitors() {
		stationDAO.deleteAll(session, com.hdp.smp.model.Station.class);
		
		// cascading of save not work...
		Station s3 = new Station();
		s3.setName("s10");
		s3.setStatus("ACTIVE");
		Monitor m1 = new Monitor();
		String mname = "s10-m1";
		m1.setName(mname);
		m1.setStatus("ACTIVE");
		m1.setStation(s3);
		m1.setModel("XXX-1");
		m1.setIp("192.168.126.122");
		m1.setPort(502);
		m1.setProtocol("Modbus-TCPIP");
		// Monitor m2= new Monitor();
		// m2.setName("s3-m2");
		// m2.setStatus("ACTIVE");
		// m2.setStation(s3);
		// m2.setModel("XXX-1");
		// m2.setIp("192.168.126.123");
		// m2.setPort(502);
		// m2.setProtocol("Modbus-TCPIP");
		s3.setActiveMonitor(m1);

		stationDAO.saveOrUpdate(s3, session);
		Integer id = (Integer) stationDAO.getMaxId(session,	com.hdp.smp.model.Station.class);
		if (id == null)
			return;

		session.evict(m1);
		session.evict(s3);

		// load station from session cache(memory) if not found, then load from DB
		Station station = (Station) stationDAO.load(session,com.hdp.smp.model.Station.class, id);
		Monitor monitor = station.getActiveMonitor();
		// cascading not implemented...
		Assert.assertNull(monitor);

	}

	@Test
	public void testSaveCascade2() {
		//cascade of delete..ok for annotated pojo
		stationDAO.deleteAll(session, com.hdp.smp.model.Station.class);
		// cascading of save... not work!
		Station s3 = new Station();
		s3.setName("s13");
		s3.setStatus("ACTIVE");
		
		stationDAO.saveOrUpdate(s3, session);
		
		// to save monitor
		Monitor m1 = new Monitor();
		String mname = "s13-m1";
		m1.setName(mname);
		m1.setStatus("ACTIVE");
		m1.setStation(s3);  //very important
		m1.setModel("XXX-1");
		m1.setIp("192.168.126.122");
		m1.setPort(502);
		m1.setProtocol("Modbus-TCPIP");
		DAO monitorDAO = new DAO();
		Integer mid = (Integer) monitorDAO.save(m1, session);
		session.evict(m1);
		Monitor storedMon = (Monitor) stationDAO.load(session,com.hdp.smp.model.Monitor.class, mid);
		Assert.assertTrue(storedMon.getName().equals(mname));
		Assert.assertNotNull(storedMon.getStation().getId());

	}

	@Test
	public void testUpdateStationNmonitors() {

	}

	@Test
	public void testDelete() {
		// delete a transient obj with id attached to it , will be ok?

	}

	@AfterClass
	public static void destroy() {
		session.close();
	}

}
