package com.mastertheboss.gui;

import com.mastertheboss.application.QueryExecutor;
import com.mastertheboss.domain.Department;
import com.mastertheboss.domain.Employee;
import com.mastertheboss.framework.MyLogger;
import com.mastertheboss.util.ConvertFormats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class Employee_Form_V001 {
    private JTextField name;
    private JTextField familyName;
    private JTextField groupleader;
    private JTextField email;
    private JComboBox< Object > department;
    private JComboBox gender;
    private JTextField phoneNumber;
    private JTextField Birthday;
    private JPanel main;
    private JButton addUpdateEmployeeButton;
    private JTextField departmentName;
    private JButton addDepartmentButton;
    private JButton resetEmplyeeDatabaseButton;
    private JButton resetDepartmentDatabaseButton;
    private JButton deleteDepatmentButton;
    private JButton deleteEmployeeButton;
    private JLabel result;

    public Employee_Form_V001() {
        List departments =  new QueryExecutor().getDepartmentList();
        for (var department:departments) {
            this.department.addItem(department);
        }

        department.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyLogger.InfoLog(Employee_Form_V001.class, (String) department.getSelectedItem());
            }
        });
        addUpdateEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryExecutor().addnewEmployee((String) department.getSelectedItem(),
                        name.getText(),
                        com.mastertheboss.Constants.Gender.valueOf((String) gender.getSelectedItem()),
                        familyName.getText(),
                        groupleader.getText(),
                        email.getText(),
                        phoneNumber.getText(),
                        ConvertFormats.converStringToLocaldate(Birthday.getText()));

                MyLogger.InfoLog(Employee_Form_V001.class, "Employee '"+ familyName.getText()+"' is successfully added to database.");
                result.setText("Employee '"+ familyName.getText()+"' is successfully added to database.");
            }
        });
        addDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryExecutor().addNewDepartment(departmentName.getText());
                MyLogger.InfoLog(Employee_Form_V001.class, "Department '"+ departmentName.getText()+"' is successfully added to database.");
                result.setText("Department '"+ departmentName.getText()+"' is successfully added to database.");
            }
        });
        resetEmplyeeDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryExecutor().resetTable(Employee.class);
                MyLogger.InfoLog(Employee_Form_V001.class,"Employee table is successfully deleted");
                result.setText("Employee table is successfully deleted");

            }
        });
        resetDepartmentDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryExecutor().resetTable(Department.class);
                MyLogger.InfoLog(Employee_Form_V001.class,"Department table is successfully deleted");
                result.setText("Department table is successfully deleted");
            }
        });
        deleteDepatmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryExecutor().deleteFromTableDepartment(departmentName.getText());
                MyLogger.InfoLog(Employee_Form_V001.class, "Department '"+ departmentName.getText()+"' is successfully deleted from database.");
                result.setText("Department '"+ departmentName.getText()+"' is successfully deleted from database.");
            }
        });
        deleteEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryExecutor().deleteFromTableEmployee(name.getText(), familyName.getText());
                MyLogger.InfoLog(Employee_Form_V001.class, "Employee '"+ familyName.getText()+"' is successfully deleted from database.");
                result.setText("Employee '"+ familyName.getText()+"' is successfully deleted from database.");
            }
        });
    }


    public static void main(String[] args) throws IOException {



        JFrame frame = new JFrame("Employee Birthday");

        frame.setContentPane(new Employee_Form_V001().main);


//        frame.setPreferredSize( new Dimension( 200, 300 ) );
        frame.setLocation(400, 100);
        frame.setPreferredSize(new Dimension(500, 600));//400 width and 500 height
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);//making the frame visible

    }
}
