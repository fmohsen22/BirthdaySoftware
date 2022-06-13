package com.mastertheboss.util;

import org.junit.Test;

public class Queries {

    public enum GenericEmployeeQueries {

        COUNT_DATA_Employee("SELECT COUNT(*) FROM Employee  WHERE name  = '%S' AND family_name  = '%S'"),
        COUNT_DATA_Department("SELECT COUNT(*) FROM Department  WHERE department_name  = '%S'"),
        GET_DEPARTMENT_ID_ONE_VARIABLE("SELECT id FROM Department  WHERE department_name  = '%S'"),
        GET_DEPARTMENT_ID_TWO_VARIABLES("SELECT id FROM Department  WHERE %s  = '%s' AND %s  = '%s'"),
        GET_DEPARTMENTS_NAME("SELECT department_name FROM Department"),
        GET_Employee_ID_TWO_VARIABLE("SELECT id FROM Employee  WHERE family_name  = '%S' AND name  = '%S'"),
        GET_Employees_WITH_BIRTHDAY("FROM Employee  WHERE DAY(birthday) = DAY(CURDATE())\n" +
                "   and MONTH(birthday) = MONTH(CURDATE())")
        ;

        private String query;

        GenericEmployeeQueries(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }

        public String insetParameters(String ... parameters){
            return String.format(getQuery(),parameters);
        }
    }



    @Test
    public void test(){
        System.out.println(GenericEmployeeQueries.COUNT_DATA_Employee.insetParameters("Employee","test"));
    }


}
