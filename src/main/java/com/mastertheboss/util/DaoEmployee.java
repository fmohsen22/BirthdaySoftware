package com.mastertheboss.util;

import com.mastertheboss.domain.Department;
import com.mastertheboss.domain.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DaoEmployee {

    public static void saveEmployee(Employee employee, Department department) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.save(department);

        session.save(employee);


        session.getTransaction().commit();
    }

    public List< Employee > getEmployee() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        }
    }
}
