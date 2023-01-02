/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import tools.MyTool;
import static tools.MyTool.inputString;

/**
 *
 * @author triet
 */
public class DepartmentList {
    private ArrayList<Department> list;
    private int size;
    String urlDepartment = "C:\\Users\\triet\\OneDrive\\Máy tính\\LAB\\Lab1\\department.txt";

    public DepartmentList() {
        list = new ArrayList<>();
        size = 0;
        try
        {
            readDepartment(urlDepartment);
        }catch(Exception e)
        {
            System.out.println("Cannot load");
        }
    }

    public ArrayList<Department> getList() {
        return list;
    }

    public void setList(ArrayList<Department> list) {
        this.list = list;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
    
    public Department searchDepartmentByID( String id )
    {
        for (Department department : list) {
            if( department.getDepartmentID().equals(id) )
            {
                return department;
            }
        }
        return null;
    }
    
    public boolean addDepartment( Department add )
    {
        if( searchDepartmentByID(add.getDepartmentID()) != null )
        {
            System.out.println("Already existed ID");
            return false;
        }
        else
        {
            ++size;
            list.add(add);
            try
            {
                writeDepartment(urlDepartment);
            }catch(Exception e)
            {
                System.out.println("Error");
            }
            return true;
        }
    }
    
    public boolean removeDepartment( String id )
    {
        if( searchDepartmentByID(id) != null )
        {
            list.remove(searchDepartmentByID(id));
            try
            {
                writeDepartment(urlDepartment);
            }catch(Exception e)
            {
                System.out.println("Error");
            }
            return true;
        }
        else
        {
            System.out.println("Cannot find valid department");
            return false;
        }
    }
    
    public boolean updateDepartment( String id )
    {
        if( searchDepartmentByID(id) != null )
        {
            System.out.println("UPDATE!!!");
            String currentDate = searchDepartmentByID(id).getCreateDate();
            searchDepartmentByID(id).input();
            searchDepartmentByID(id).setCreateDate(currentDate);
            try
            {
                writeDepartment(urlDepartment);
            }catch(Exception e)
            {
                System.out.println("Error");
            }
            return true;
        }
        else
        {
            System.out.println("Canot find valid department");
            return false;
        }
    }
    
    public void display()
    {
        for (Department department : list) {
            department.output();
        }
    }
    
    //ham nay de ghi danh sach dog
    public void writeDepartment( String filename) throws Exception
    {
        if( list != null && list.size() > 0 )
        {
            PrintWriter w = new PrintWriter(filename);
            for(Department department:list)
            {
                w.println(department.toString());
            }
            
            w.close();
        }
    }
    
    //ham nay de doc file phi nhan tra ve danh sach cac con Dog
    public void readDepartment(String filename) throws Exception
    {
        //mo file
        //doc file
        //dong file
        FileReader f = new FileReader(filename);
        BufferedReader bf = new BufferedReader(f);
        while(bf.ready())
        {
            String s = bf.readLine();
            String [] arr=s.split(",");
            if(arr.length == 4)
            {
                Department p = new Department(arr[0], arr[1], arr[2], arr[3]);
                this.addDepartment(p);
            }
        }
        f.close();
        bf.close();
    }
}
