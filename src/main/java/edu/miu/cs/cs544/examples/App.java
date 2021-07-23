package edu.miu.cs.cs544.examples;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class App {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Customer.class, Orderes.class, OrderLine.class,
				Product.class, CD.class, Book.class, DVD.class));
	}

	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		//////////////////////////////////
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Product product1 = new CD("Thriller", "Beat it", "Michael Jackson");
			Product product2 = new DVD("Avengers", "Infinity War", "Superheroes");
			Product product3 = new Book("Harry Potter", "Third part", "Prisioner of Azkaban");

			OrderLine orderLine1 = new OrderLine(product1, 6);
			OrderLine orderLine2 = new OrderLine(product2, 12);
			OrderLine orderLine3 = new OrderLine(product3, 18);

			Orderes order = new Orderes();
			order.setDate(LocalDate.now());
			order.addOrderLine(orderLine1);
			order.addOrderLine(orderLine2);
			order.addOrderLine(orderLine3);

			Customer customer1 = new Customer("Jorge", "Gil");
			customer1.addOrder(order);

			session.persist(customer1);

			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// retrieve all persons
			List<Customer> customerList = session.createQuery("from Customer", Customer.class).list();
			for (Customer e : customerList) {
				System.out.println(e);
			}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		// Close the SessionFactory (not mandatory)
		sessionFactory.close();
	}
}
