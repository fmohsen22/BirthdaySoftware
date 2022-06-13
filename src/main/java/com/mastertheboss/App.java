package com.mastertheboss;

import java.time.LocalDate;
import java.util.List;


import com.mastertheboss.domain.Employee;
import com.mastertheboss.domain.Department;
import com.mastertheboss.Constants.Gender;
import com.mastertheboss.util.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();


        session.beginTransaction();
        Department department1 = new Department("Vienna");
        Department department2 = new Department("Graz");
        session.save(department1);
        session.save(department2);
        session.save(new Employee("Jakab" ,"Gipsz", Gender.Female,"Markus","xxx@email.com", "123456",LocalDate.of(2012,1,2),department1));
        session.save(new Employee("Captain" ,"Nemo",Gender.Male,"Markus","xxx@email.com", "4587957",LocalDate.of(2000,2,5),department2));

        session.getTransaction().commit();
        Query q = session.createQuery("From Employee ");

//        List<Employee> resultList = q.list();
//        System.out.println("num of employess:" + resultList.size());
//        for (Employee next : resultList) {
//            System.out.println("next employee: " + next);
//        }

        Employee employee =  new Employee("Jakab", "GipszTest",Gender.Male,"Esther","xxx@email.com", "123456",LocalDate.of(2012,1,2),department2);
        session.save(employee);

        session.close();

    }

    public static Employee getEmployee(int id){
        Transaction transaction = null;
        Employee employee = null;


        getCurrentSessionFromConfig().flush();
        transaction = getCurrentSessionFromConfig().beginTransaction();
        String hql = " FROM Employee e WHERE e.id  = :employeeId";
        Query query = getCurrentSessionFromConfig().createQuery(hql);

        query.setParameter("employeeId",id);
        List results = query.getResultList();

        if (results != null && !results.isEmpty()) {
            employee = (Employee) results.get(0);
        }

        transaction.commit();


        return employee;
    }


    public static Session getCurrentSessionFromConfig() {
        // SessionFactory in Hibernate 5 example
        Configuration config = new Configuration();
        config.configure();
        // local SessionFactory bean created
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
    @Test
    public void test(){

//        String hql = "UPDATE Employee  SET name  = 'Jakab_updated' WHERE id=1";
//        Query q =session.createQuery(hql);




    }




}
