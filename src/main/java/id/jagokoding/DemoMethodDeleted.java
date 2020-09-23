package id.jagokoding;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DemoMethodDeleted {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();

			Customer customer1 = session.get(Customer.class, 1L);
			if (customer1 != null) {
				session.delete(customer1);
				System.out.println("Customer 1 deleted");
			}

			Customer customer2 = new Customer();
			customer2.setId(2L);
			session.delete(customer2);
			System.out.println("Customer 2 deleted");

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		HibernateUtil.shutdown();
	}
}
