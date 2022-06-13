package com.mastertheboss.gui;

import com.mastertheboss.application.QueryExecutor;
import com.mastertheboss.domain.Department;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class Employee_Form_V001 {
    private JTextField Name;
    private JTextField FamilyName;
    private JTextField groupleader;
    private JTextField Email;
    private JComboBox Department;
    private JComboBox Gender;
    private JTextField PhoneNumber;
    private JTextField Birthday;
    private JPanel main;

    public Employee_Form_V001() {
        List departments =  new QueryExecutor().getDepartmentList();
        for (var department:departments) {
            Department.addItem(department);
        }

        Department.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(Department.getSelectedItem());

            }
        });
    }

    public static void main(String[] args) throws IOException {



        JFrame frame = new JFrame("Employee Birthday");

        frame.setContentPane(new Employee_Form_V001().main);


//        frame.setPreferredSize( new Dimension( 200, 300 ) );
        frame.setLocation(400, 100);
        frame.setPreferredSize(new Dimension(500, 300));//400 width and 500 height
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);//making the frame visible

    }
}
