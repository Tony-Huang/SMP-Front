package com.hdp.smp.persisence;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.junit.*;

import com.hdp.smp.model.Shift;
import com.hdp.smp.model.Station;
import com.hdp.smp.model.StationData;
import com.hdp.smp.persistence.DAO;
import com.hdp.smp.persistence.HibernateUtil;

public class StationDataTest {
	
	private static Session session;

	@BeforeClass
	public static void setup(){
		session = HibernateUtil.getSession();
	}
	
	@Test
	public void testSave() {
		DAO dao = new DAO();
		
		Serializable stnid = dao.getMaxId(session, com.hdp.smp.model.Station.class);
		Station stn = (Station)dao.load(session, com.hdp.smp.model.Station.class, stnid);
		
		Shift sh =null;
		long shiftCount = dao.getCount(session, com.hdp.smp.model.Shift.class);
		if(shiftCount < 1L){
		 sh = new Shift();
		 sh.setName("Daytime");
		 int shid = (Integer)dao.save(sh, session);
		}
		sh=(Shift)dao.load(session, com.hdp.smp.model.Shift.class, dao.getMaxId(session, com.hdp.smp.model.Shift.class));
		
		
		StationData stnData = new StationData();
		stnData.setStation(stn);
		stnData.setShift(sh);
		stnData.setAvgSpindleSpeed(12365.80f);
		stnData.setBrokenSpindles(2);
		stnData.setCreepSpindles(3);
		stnData.setEmptySpindles(0);
			
		Timestamp doff = new Timestamp(2015-1900, 5-1, 12, 12, 35, 36, 388);
		stnData.setDoffTime(doff);
		Timestamp now = new Timestamp( System.currentTimeMillis()  );
		stnData.setCreatedOn(now);
		
		dao.save(stnData, session);
		
		long count = dao.getCount(session, com.hdp.smp.model.StationData.class);
		Assert.assertTrue(count>0);
		
	}
	
	
	@AfterClass
	public static void destroy(){
		
	}
}
