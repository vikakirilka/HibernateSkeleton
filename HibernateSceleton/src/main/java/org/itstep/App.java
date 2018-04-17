package org.itstep;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.itstep.model.Account;
import org.itstep.model.Good;
import org.itstep.model.GoodAction;
import org.itstep.util.HiberPropertiesUtil;
import org.itstep.util.HibernateUtil;

/**
 * @author vikul
 *
 */
public class App {
	public static void main(String[] args) {
		Account account = new Account("vikuha778", "12345677", "Vikuha77", "Kiriluch77", new Date(99, 1, 26).getTime());

		Good good = new Good("BATSG6GSST1", "Name for test good", 77665);

		HiberPropertiesUtil hutil = new HiberPropertiesUtil();

		Session session = hutil.getSessionFactory().openSession();

		session.getTransaction().begin();

		session.save(good);

		session.save(account);

		session.getTransaction().commit();

		session.close();

		GoodAction goodAction = new GoodAction(System.currentTimeMillis(), "try to add to cart", Boolean.FALSE, account,
				good);

		Session session2 = hutil.getSessionFactory().openSession();

		session2.getTransaction().begin();

		session2.save(goodAction);

		session2.getTransaction().commit();

		session2.close();

		// String sql = "SELECT account_login FROM good_actions WHERE time_action>" +
		// Long.valueOf(System.currentTimeMillis());

		String sql = "SELECT account_login FROM good_actions WHERE time_action> :time";
		
		String sqlAll = "SELECT * FROM good_actions";

		Session session3 = hutil.getSessionFactory().openSession();

		session3.getTransaction().begin();

		Query query = session3.createNativeQuery(sql).setParameter("time",
				Long.valueOf(System.currentTimeMillis() - (10 * 60 * 1000)));

		List<String> result = query.getResultList();
		
		Query queryAll = session3.createNativeQuery(sqlAll);
		
		List<GoodAction> resultAll = queryAll.getResultList();

		session3.getTransaction().commit();

		session3.close();

		System.out.println(result.size());
		
		for (String string : result) {
			System.out.println(string);
			
		}
		
		for (GoodAction goodAct : resultAll) {
			System.out.println(goodAct.getGood().getName());
		}

		System.out.println("Everything is done!");

	}
}
