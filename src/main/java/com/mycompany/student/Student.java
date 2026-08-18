/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.student;

/**
 *
 * @author Administrator
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;
public class Student {
     static DefaultTableModel model=new DefaultTableModel(new String[]{"Name","Age","Course","GPA"
    },0); 
    static ArrayList<StudentManagement> students=new ArrayList<>();
    public static void main(String[] args) {
        JFrame frame=new JFrame("Student Grade Calculator");
       JTable table=new JTable(model);
       JScrollPane scrollpane=new JScrollPane(table);
       JPanel tablePanel=new JPanel();
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(4,2));
        JLabel nameLabel=new JLabel("Name: ");
        JTextField nameTxt=new JTextField(10);
        JLabel courseLabel=new JLabel("Course: ");
        JComboBox<String> courseBox=new JComboBox<>(new String[]{"Computer Science","IT","Computer Technology","Software Engineering"}); 
        JLabel gpaLabel=new JLabel("GPA: ");
        JTextField gpaTxt=new JTextField(10);
       JLabel ageLabel=new JLabel("Age: ");
       JTextField ageTxt=new JTextField(5);
 
        JPanel p2=new JPanel();
        JButton resultButton=new JButton("Result");
        resultButton.addActionListener(e->{
           try{
                String name=nameTxt.getText().trim().replaceAll("\\s+"," ");
                  if(name.isEmpty()){
               JOptionPane.showMessageDialog(frame,"Name cant be empty");
               return;}  
                  if(!name.matches("[a-zA-Z]+")){
                      JOptionPane.showMessageDialog(frame,"Name must contain letters only");
                      return;
                  }
           double gpa=Double.parseDouble(gpaTxt.getText().trim());
           if(gpa<0||gpa>4){
               JOptionPane.showMessageDialog(frame,"GPA enmtered should be btn 0 and 4");
               return;
           }
           String course=(String)courseBox.getSelectedItem();           
           int age=Integer.parseInt(ageTxt.getText().trim());
           if(age<18){
               JOptionPane.showMessageDialog(frame,"Invalid Age");
               return;
           }
            StudentManagement s=new StudentManagement(name,age,course,gpa);
            students.add(s);
            model.addRow(new Object[]{name,age,course,gpa});
           JOptionPane.showMessageDialog(frame, "Name :"+name+"\nCourse: "+course+"\nGPA: "+gpa);
           }
           
           catch(NumberFormatException m){
               JOptionPane.showMessageDialog(frame,"Invalid age or gpa entered");
            }
           
        });
        
        panel.add(nameLabel);
        panel.add(nameTxt);
        panel.add(courseLabel);
        panel.add(courseBox);
        panel.add(gpaLabel);
        panel.add(gpaTxt);
        panel.add(ageLabel);
        panel.add(ageTxt);
        tablePanel.add(scrollpane);
        p2.add(resultButton);
        frame.add(panel,BorderLayout.NORTH);
        frame.add(tablePanel,BorderLayout.CENTER);
        frame.add(p2,BorderLayout.SOUTH);
        panel.setBackground(Color.gray);
        p2.setBackground(Color.pink);
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }}
