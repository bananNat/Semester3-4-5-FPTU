/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.Date;
import tools.MyTool;
import static tools.MyTool.inputString;

/**
 *
 * @author triet
 */
public class Department implements Serializable {
    private String departmentID;
    private String name;
    private String createDate;
    private String lastUpdateDate;

    public Department() {
    }

    public Department(String departmentID, String name, String createDate, String lastUpdateDate) {
        this.departmentID = departmentID;
        this.name = name;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
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
                departmentID = inputString("Input departmentID[Bxxx where x is number 0-9]: ", "B[0-9]{3}");
                name = inputString("Input department name: ", "^[a-zA-Z]+([a-zA-Z ]+)*$");
                createDate = MyTool.dataToStr(new Date(), "dd/MM/yyyy");
                lastUpdateDate = MyTool.dataToStr(new Date(), "dd/MM/yyyy");
                flag = true;
            }
            catch(Exception e)
            {
                System.out.println("Error");
            }
        }while(!flag);
    }
    
    public void output()
    {
        String str = String.format("%-30s%-30s%-30s%-30s", departmentID, name, createDate, lastUpdateDate);
        System.out.println(str);
    }

    @Override
    public String toString() {
        String str = String.format("%s, %s, %s, %s", departmentID, name, createDate, lastUpdateDate);
        return str;
    }
    
    
}
