package com.mastertheboss.application;

import com.mastertheboss.domain.Department;
import com.mastertheboss.domain.Employee;
import com.mastertheboss.Constants.Gender;
import com.mastertheboss.util.HibernateUtil;
import com.mastertheboss.util.Queries;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class QueryExecutor {
    private Session session =  HibernateUtil.getSessionFactory().openSession();

    //Tabel Employee
    public long rowCount(String name, String familyName){
        Query q =session.createQuery(Queries.GenericEmployeeQueries.COUNT_DATA_Employee.insetParameters(name, familyName));
        return  (long) q.getResultList().get(0);
    }

    public boolean personExist(String name, String familyName){
        return rowCount(name,familyName)!=0;
    }

    public void addNewDepartment(String departmentName){
        session.beginTransaction();
        Query q =session.createQuery(Queries.GenericEmployeeQueries.COUNT_DATA_Department.insetParameters(departmentName));
        long result= (long) q.getResultList().get(0);
        Department department = new Department(departmentName);
        if(result==0){
            session.save(department);
            session.getTransaction().commit();
            session.close();
        }// TODO: 19.05.2022 if it exist should get updated
    }

    public long getIdDepartment(String departmentName){
        if (!session.getTransaction().isActive()) session.beginTransaction();
        return (long) session.createQuery(Queries.GenericEmployeeQueries.GET_DEPARTMENT_ID_ONE_VARIABLE.insetParameters(departmentName)).getResultList().get(0);
    }

    public List getDepartmentList(){
        if (!session.getTransaction().isActive()) session.beginTransaction();
        return session.createQuery(Queries.GenericEmployeeQueries.GET_DEPARTMENTS_NAME.getQuery()).getResultList();
    }

    public void deleteFromTableDepartment(String departmentName){
        session.beginTransaction();
        Query q =session.createQuery(Queries.GenericEmployeeQueries.COUNT_DATA_Department.insetParameters(departmentName));
        long result= (long) q.getResultList().get(0);
        Department department = new Department(departmentName);
        if(result!=0){

            long id =  getIdDepartment(departmentName);
            Object persistentInstance = session.load(Department.class,id );
            if (persistentInstance != null) {
                session.delete(persistentInstance);

                session.getTransaction().commit();
            }else {
                System.out.println ("Did not find the  Department Object in persistance");
            }
            session.close();
        }
    }

    public int getIdEmployee(String familyName,String name){
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        return Integer.parseInt(session.createQuery(Queries.GenericEmployeeQueries.GET_Employee_ID_TWO_VARIABLE.insetParameters(familyName,name)).getResultList().get(0).toString());
    }

    public void deleteFromTableEmployee(String name, String familyName){
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        if (!session.isOpen()){ HibernateUtil.getSessionFactory().openSession();}
        Query q =session.createQuery(Queries.GenericEmployeeQueries.COUNT_DATA_Employee.insetParameters(name, familyName));
        long result= (long) q.getResultList().get(0);

        if(result!=0){

            int id =  getIdEmployee(familyName,name);
            Object persistentInstance = session.load(Employee.class,id );
            if (persistentInstance != null) {
                session.delete(persistentInstance);

                session.getTransaction().commit();
            }else {
                System.out.println ("Did not find the  Employee Object in persistance");
            }
            session.close();
        }
    }

    public void addnewEmployee(String departmentName,
                               String name,
                               Gender gender,
                               String familyName,
                               String groupLeader,
                               String email,
                               String phoneNumber,
                               LocalDate birthDate){
        session.beginTransaction();
        if (personExist(name, familyName)){
            deleteFromTableEmployee(name,familyName);
            session = HibernateUtil.getSessionFactory().openSession();
        }
        Department department = new Department(departmentName);
        session.load(department,getIdDepartment(departmentName));
        session.save(new Employee(
                name ,
                familyName,
                gender,
                groupLeader,
                email,
                phoneNumber,
                birthDate,
                department));


        session.getTransaction().commit();
        session.close();
    }

    public void resetTable(final Class<?> clazz){
        session.beginTransaction();
        final List instances = session.createCriteria(clazz).list();
        for (Object obj : instances) {
            session.delete(obj);
        }
        session.getTransaction().commit();
        session.close();
    }

    public List<Employee> employeesWithBirthday(){
        session.beginTransaction();


        Query q =session.createQuery(Queries.GenericEmployeeQueries.GET_Employees_WITH_BIRTHDAY.getQuery());


        List<Employee> employeeList = q.getResultList();

        return employeeList;
    }


    @Test
    public void test(){
//        System.out.println(personExist("Jakab","Gipsz"));
//        deleteFromTableDepartment("Vienna");
//        addNewDepartment("Graz");
//        System.out.println(getIdEmployee("Nemo","Captain"));
//        deleteFromTableEmployee("Captain","Nemo");
//        addnewEmployee("graz","mohsen",Gender.Mal e,"fakhari","Esther","m.fakhari@qcentris.com","06643710777",LocalDate.now().minusYears(2));
//            resetTable(Department.class);
//        session.beginTransaction();
//        System.out.println(session.createQuery("SELECT id FROM Department  WHERE department_name  = 'Graz'").getResultList());

//        employeesWithBirthday();

//        session.beginTransaction();
//int id = 1;
//        Object persistentInstance = session.load(Employee.class,id );
//        Hibernate.initialize(persistentInstance);
//
//        session.getTransaction().commit();
//        session.close();
//        System.out.println(persistentInstance);

//        getDepartmentList();


    }
}
