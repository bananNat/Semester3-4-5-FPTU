/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import tools.MyTool;
import static tools.MyTool.inputNumber;
import static tools.MyTool.inputString;

/**
 *
 * @author triet
 */
public class Patient {
    private String patientID;
    private String name;
    private int age;
    private String address;

    public Patient() {
    }

    public Patient(String patientID, String name, int age, String address) {
        this.patientID = patientID;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    
    
    public String getPatientID() {
        return patientID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
    public void input()
    {
        boolean flag = false;
        do
        {
            try
            {
                patientID = inputString("Input patient ID[Pxxx where x is number 0-9]:", "P[0-9]{3}");
                name = inputString("Input patient name: ", "^[a-zA-Z]+([a-zA-Z ]+)*$");
                age = (int)inputNumber("Input patient age[0-200]: ", 0, 200);
                address = inputString("Input patient address: ", "^[a-zA-Z]+([a-zA-Z ]+)*$");
                flag = true;
            }catch(Exception e)
            {
                System.out.println("Error");
            }
        }while(!flag);
    }
    
    public void output()
    {
        String str = String.format("%-30s%-30s%-30d%-30s", patientID, name, age, address);
        System.out.println(str);
    }

    @Override
    public String toString() {
        String str = String.format("%s, %s, %d, %s", patientID, name, age, address);
        return str;
    }
    
}
