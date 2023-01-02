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
public class PatientList {
    private ArrayList<Patient> list;
    private int size;
    String urlPatient = "C:\\Users\\triet\\OneDrive\\Máy tính\\LAB\\Lab1\\patient.txt";

    public PatientList() {
        list = new ArrayList<>();
        size = 0;
        try
        {
            readPatient(urlPatient);
        }catch(Exception e)
        {
            System.out.println("Error");
        }
    }

    public ArrayList<Patient> getList() {
        return list;
    }

    public void setList(ArrayList<Patient> list) {
        this.list = list;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
    
    public Patient searchPatientByID( String id )
    {
        for (Patient patient : list) {
            if( patient.getPatientID().equals(id) )
            {
                return patient;
            }
        }
        return null;
    }
    
    public boolean addPatient( Patient add )
    {
        if( searchPatientByID(add.getPatientID()) != null )
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
                writePatient(urlPatient);
            }catch(Exception e)
            {
                System.out.println("Error");
            }
            return true;
        }
    }
    
    public boolean removePatient( String id )
    {
        if( searchPatientByID(id) != null )
        {
            list.remove(searchPatientByID(id));
            try
            {
                writePatient(urlPatient);
            }catch(Exception e)
            {
                System.out.println("Error");
            }
            return true;
        }
        else
        {
            System.out.println("Cannot find valid patient");
            return false;
        }
    }
    
    public boolean updatePatient( String id )
    {
        if( searchPatientByID(id) != null )
        {
            System.out.println("UPDATE!!!");
            searchPatientByID(id).input();
            try
            {
                writePatient(urlPatient);
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
        for (Patient patient : list) {
            patient.output();
        }
    }
    
    //ham nay de ghi danh sach dog
    public void writePatient( String filename) throws Exception
    {
        if( list != null && list.size() > 0 )
        {
            PrintWriter w = new PrintWriter(filename);
            for(Patient patient:list)
            {
                w.println(patient.toString());
            }
            
            w.close();
        }
    }
    
    //ham nay de doc file phi nhan tra ve danh sach cac con Dog
    public void readPatient(String filename) throws Exception
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
                Patient p = new Patient(arr[0], arr[1], Integer.parseInt(arr[2].trim()), arr[3]);
                this.addPatient(p);
            }
        }
        f.close();
        bf.close();
    }
}
