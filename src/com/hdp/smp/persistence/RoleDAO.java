package com.hdp.smp.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hdp.smp.model.Role;

public class RoleDAO extends DAO{

//	public List<Role> getAllRoles(Session session) {
//		List<Role> roles =null;
//		try {
//			roles = session.createQuery("from com.hdp.smp.model.Role").list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return roles;
//	}
//	
//	public Role getRole(Session session,String name) {
//		Role role = null;
//		try {
//			Query query = session.createQuery("from com.hdp.smp.model.Role r where r.name=:name");
//			List<Role> roles = query.setParameter("name", name).list();
//			if(roles.size() > 0)
//				return roles.get(0);
//			else return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return role;
//	}
//	
	
	public Object getByNameCN(Session session, Class entity, String name_CN) {
			List list = session.createQuery("from "+entity.getName() +"  entity where entity.name_CN='" +name_CN+"'" ).list();
			return list.size()>0?list.get(0):null;
		}
	
	public Object getByNameEN(Session session, Class entity, String name_EN) {
		List list = session.createQuery("from "+entity.getName() +"  entity where entity.name_EN='" +name_EN+"'" ).list();
		return list.size()>0?list.get(0):null;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RoleDAO roleDao = new RoleDAO();
		Session session = HibernateUtil.getSession();
		List<Role> roles = roleDao.getAll(session,com.hdp.smp.model.Role.class);
		
		for( Role r:roles){
			System.out.println("roleid="+r.getId() +" cn_name="+r.getName_CN()+" en_name="+r.getName_EN());
			int usersize = r.getUsers().size();
			System.out.println("users="+usersize);
			System.out.println();
		}
		
		session.close();

	}
}
