package id.jagokoding;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DemoMethodRemove {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();

	         Customer customer1=session.get(Customer.class, 3L);
	         if(customer1!=null){
	            session.remove(customer1);
	            System.out.println("Customer 3 removed");
	         }
	         
	         Customer customer2=new Customer();
	         customer2.setId(4L);
	         session.remove(customer2);
	         System.out.println("Customer 4 removed");

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
