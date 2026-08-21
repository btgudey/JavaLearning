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
import java.awt.event.*;
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
        table.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent l){
            int row=table.getSelectedRow();
            if(row!=-1){
                StudentManagement s=students.get(row);
                nameTxt.setText(s.name);
                ageTxt.setText(String.valueOf(s.age));
                courseBox.setSelectedItem(s.course);
                gpaTxt.setText(String.valueOf(s.gpa));
            }
        }
    });
        
       JButton deleteButton=new JButton("DELETE");
        JPanel p2=new JPanel();
        JButton updateButton=new JButton("UPDATE");
        JButton resultButton=new JButton("Result");
        resultButton.addActionListener(e->{
           try{
                String name=nameTxt.getText().trim().replaceAll("\\s+"," ");
                  if(name.isEmpty()){
               JOptionPane.showMessageDialog(frame,"Name cant be empty");
               return;}  
                  if(!name.matches("[a-zA-Z ]+")){
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
           
           nameTxt.setText("");
           ageTxt.setText("");
           courseBox.setSelectedIndex(0);
           gpaTxt.setText("");
                   }
           catch(NumberFormatException m){
               JOptionPane.showMessageDialog(frame,"Invalid age or gpa entered");
            }
           
        });
        deleteButton.addActionListener(el->{
           
               int row=table.getSelectedRow();
               if(row==-1){
                   JOptionPane.showMessageDialog(frame,"Select a student First");
                   return;
               }
               students.remove(row);
               model.removeRow(row);
             
        });
        updateButton.addActionListener(ej->{

            int row=table.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(frame,"Select a student first");
                return;
            }
            StudentManagement s=students.get(row);
            String newName=nameTxt.getText().trim().replaceAll("\\s+"," ");
            if(newName.isEmpty()||!newName.matches("[a-zA-Z ]+")){
                JOptionPane.showMessageDialog(frame,"Invalid NAME")
                        ;return;
        }
            
            int newAge=Integer.parseInt(ageTxt.getText().trim().replaceAll("\\s+",""));
            if(newAge<18){
                   JOptionPane.showMessageDialog(frame,"Invalid AGE");
                   return;
            }
            String newCourse=(String)courseBox.getSelectedItem();
            
            double newgpa=Double.parseDouble(gpaTxt.getText().trim().replaceAll("\\s+",""));
            if(newgpa<0||newgpa>4){
                JOptionPane.showMessageDialog(frame,"Invalid GPA");
                return;
            }
            s.name=newName;
            s.age=newAge;
            s.course=newCourse;
            s.gpa=newgpa;
            model.setValueAt(newName, row, 0);
            model.setValueAt(newAge, row, 1);
            model.setValueAt(newCourse, row, 2);
            model.setValueAt(newgpa, row, 3);
            nameTxt.setText("");
             ageTxt.setText("");
              courseBox.setSelectedIndex(0);
               gpaTxt.setText("");
            
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
        p2.add(deleteButton);
        p2.add(updateButton);
        frame.add(panel,BorderLayout.NORTH);
        frame.add(tablePanel,BorderLayout.CENTER);
        frame.add(p2,BorderLayout.SOUTH);
        panel.setBackground(Color.gray);
        p2.setBackground(Color.pink);
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }}
