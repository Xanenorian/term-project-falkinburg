/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import datamodel.EmployeeFalkinburg;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDBFalkinburg {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<EmployeeFalkinburg> listEmployees() {
      List<EmployeeFalkinburg> resultList = new ArrayList<EmployeeFalkinburg>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

      try {
         tx = session.beginTransaction();
         List<?> employees = session.createQuery("FROM EmployeeFalkinburg").list();
         for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
            EmployeeFalkinburg employee = (EmployeeFalkinburg) iterator.next();
            resultList.add(employee);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<EmployeeFalkinburg> listEmployees(String keyword) {
      List<EmployeeFalkinburg> resultList = new ArrayList<EmployeeFalkinburg>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         System.out.println((EmployeeFalkinburg)session.get(EmployeeFalkinburg.class, 1)); // use "get" to fetch data
        // Query q = session.createQuery("FROM Employee");
         List<?> employees = session.createQuery("FROM EmployeeFalkinburg").list();
         for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
            EmployeeFalkinburg employee = (EmployeeFalkinburg) iterator.next();
            if (employee.getName().startsWith(keyword)) {
               resultList.add(employee);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static void createEmployees(String userName, String age, String phone) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new EmployeeFalkinburg(userName, Integer.valueOf(age), phone));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
}
