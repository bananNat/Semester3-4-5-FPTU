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

/**
 *
 * @author triet
 */
public class DoctorList {
    private ArrayList<Doctor> list;
    private int size;
    String urlDoctor = "C:\\Users\\triet\\OneDrive\\Máy tính\\LAB\\Lab1\\doctor.txt";
    
    public DoctorList() {
        list = new ArrayList<>();
        size = 0;
        try
        {
            readDoctor(urlDoctor);
        }catch(Exception e)
        {
            System.out.println("Error");
        }
    }
    
    public ArrayList<Doctor> getList() {
        return list;
    }

    public void setList(ArrayList<Doctor> list) {
        this.list = list;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
    
    public Doctor searchDoctorByID( String id )
    {
        for (Doctor doctor : list) {
            if( doctor.getDoctorID().equals(id) )
            {
                return doctor;
            }
        }
        return null;
    }
    
    public boolean addDoctor( Doctor add )
    {
        if( searchDoctorByID(add.getDoctorID()) != null )
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
                writeDoctor(urlDoctor);
            }catch(Exception e)
            {
                System.out.println("Error");
            }
            return true;
        }
    }
    
    public boolean removeDoctor( String id )
    {
        if( searchDoctorByID(id) != null )
        {
            list.remove(searchDoctorByID(id));
            try
            {
                writeDoctor(urlDoctor);
            }catch(Exception e)
            {
                System.out.println("Error");
            }
            return true;
        }
        else
        {
            System.out.println("Cannot find valid doctor");
            return false;
        }
    }
    
    public boolean updateDoctor( String id )
    {
        if( searchDoctorByID(id) != null )
        {
            System.out.println("UPDATE!!!");
            String currentDate = searchDoctorByID(id).getCreateDate();
            searchDoctorByID(id).input();
            searchDoctorByID(id).setCreateDate(currentDate);
            try
            {
                writeDoctor(urlDoctor);
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
        for (Doctor doctor : list) {
            doctor.output();
        }
    }
    
    //ham nay de ghi danh sach dog
    public void writeDoctor( String filename) throws Exception
    {
        if( list != null && list.size() > 0 )
        {
            PrintWriter w = new PrintWriter(filename);
            for(Doctor doctor:list)
            {
                w.println(doctor.toString());
            }
            
            w.close();
        }
    }
    
    //ham nay de doc file phi nhan tra ve danh sach cac con Dog
    public void readDoctor(String filename) throws Exception
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
            if(arr.length == 7)
            {
                Doctor p = new Doctor(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
                this.addDoctor(p);
            }
        }
        f.close();
        bf.close();
    }
}
