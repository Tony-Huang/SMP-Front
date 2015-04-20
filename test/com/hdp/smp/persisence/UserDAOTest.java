package com.hdp.smp.persisence;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hdp.smp.model.Role;
import com.hdp.smp.model.User;
import com.hdp.smp.persistence.HibernateUtil;
import com.hdp.smp.persistence.RoleDAO;
import com.hdp.smp.persistence.UserDAO;
//import org.junit.

public class UserDAOTest {
	
	private static UserDAO ud;
	private static User u1 ;
	private static User u2 ;
	private static Session session;

	@BeforeClass
	public static void setup() {
		//super.setup();
		session = HibernateUtil.getSession();
		u1 = new User();
		u1.setName("user1");
		u1.setPasswd("user1Passwd");
		u2 = new User();
		u2.setName("user2");
		u2.setPasswd("user2Passwd");
		ud = new UserDAO();
	}
	
	@Ignore
	@Test
	public void createUserNegative() {
		ud.saveOrUpdate(u1, session);
		
		//expect error here since the role is not set on the user, which is required.
		Integer id = (Integer)ud.save(u2, session);
		Integer maxId = (Integer)ud.getMaxId(session, com.hdp.smp.model.User.class);
		Assert.assertEquals(id, maxId);
		
	}
	
	@Test
	public void createUser() {
		RoleDAO rd = new RoleDAO();
		Role role_Admin_cn =(Role) rd.getByNameCN(session, com.hdp.smp.model.Role.class,"管理员");
		u1.setRole(role_Admin_cn);
		ud.saveOrUpdate(u1, session);
		
		Role role_Operator_en = (Role) rd.getByNameEN(session,  com.hdp.smp.model.Role.class, "Operators");
		u2.setRole(role_Operator_en);
		Integer id = (Integer)ud.save(u2, session);
		
		
		Integer maxId = (Integer)ud.getMaxId(session, com.hdp.smp.model.User.class);
		Assert.assertEquals(id, maxId);
		
	}
	
	@Test
	public void queryUser () {
		User user1 = (User)ud.getByName(session, com.hdp.smp.model.User.class, "user1");
		Assert.assertNotNull(user1.getId());
		
		//in same session , so association still ok.
		Assert.assertNotNull(user1.getRole().getName_EN());
	}
	
	@Test 
	public void updateUser (){
		User u1 = (User)ud.getByName(session, com.hdp.smp.model.User.class, "user1");
		String oldPasswd = u1.getPasswd();
		if(oldPasswd.length()>=60){
			oldPasswd=oldPasswd.substring(5);
		}
		u1.setPasswd(oldPasswd+"00XXX");
		ud.saveOrUpdate(u1, session);
		
		User u1new  = (User)ud.getByName(session, com.hdp.smp.model.User.class, "user1");
		Assert.assertFalse(oldPasswd.equals(u1new.getPasswd()));
	}
	
	@Test 
	public void deleteUser () {
		long userCount = ud.getCount(session, com.hdp.smp.model.User.class);
		RoleDAO rd = new RoleDAO();
		Role role_Admin_cn =(Role) rd.getByNameCN(session, com.hdp.smp.model.Role.class,"管理员");
		User tempUser = new User();
		tempUser.setName("tempName");
		tempUser.setPasswd("tempPasswd");
		tempUser.setRole(role_Admin_cn);
		ud.saveOrUpdate(tempUser, session);
		long afterInsertCount = ud.getCount(session, com.hdp.smp.model.User.class);
		Assert.assertTrue(afterInsertCount-userCount == 1);
		
		ud.delete(tempUser, session);
		long afterDeleteCount = ud.getCount(session, com.hdp.smp.model.User.class);
		Assert.assertTrue(afterDeleteCount-userCount == 0);
	}
	
	@Test 
	public void deleteUserByName () {
		
		ud.deleteByParam(session, com.hdp.smp.model.User.class, "name", "tempName");
		long count = ud.getCount(session, com.hdp.smp.model.User.class);
		System.out.println("count="+count);

		//Assert.assertTrue(11-count == 7);
	}
	
	@AfterClass
	public static void destroy() {
		session.close();
	}

}
