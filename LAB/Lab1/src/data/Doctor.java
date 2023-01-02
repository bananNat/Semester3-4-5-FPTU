/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Date;
import tools.MyTool;
import static tools.MyTool.inputString;

/**
 *
 * @author triet
 */
public class Doctor {
    private String doctorID;
    private String name;
    private String sex;
    private String address;
    private String departmentID;
    private String createDate;
    private String lastUpdateDate;

    public Doctor() {
    }

    public Doctor(String doctorID, String name, String sex, String address, String departmentID, String createDate, String lastUpdateDate) {
        this.doctorID = doctorID;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.departmentID = departmentID;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    
    
    public void input()
    {
        boolean flag = false;
        do
        {
            try
            {
                doctorID = inputString("Input Doctor ID[Dxxx where x is number 0-9]: ", "D[0-9]{3}");
                name = inputString("Input Doctor name: ", "^[a-zA-Z]+([a-zA-Z ]+)*$");
                sex = inputString("Input Doctor sex[M/F]: ", "M|F");
                address = inputString("Input Doctor address: ", "^[a-zA-Z]+([a-zA-Z ]+)*$");
                departmentID = inputString("Input departmetn ID: ", "B[0-9]{3}");
                createDate = MyTool.dataToStr(new Date(), "dd/MM/yyyy");
                lastUpdateDate = MyTool.dataToStr(new Date(), "dd/MM/yyyy");
                flag = true;
            }catch(Exception e)
            {
                System.out.println("Error");
            }
        }while(!flag);
    }
    
    public void output()
    {
        String str = String.format("%-30s%-30s%-30s%-30s%-30s%-30s%-30s", doctorID, name, sex, address, departmentID, createDate, lastUpdateDate);
        System.out.println(str);
    }

    @Override
    public String toString() {
        String str = String.format("%s, %s, %s, %s, %s, %s, %s", doctorID, name, sex, address, departmentID, createDate, lastUpdateDate);
        return str;
    }
}
