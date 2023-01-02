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
public class ExaminationList {
    private ArrayList<Examination> list;
    private int size;
    String urlExamination = "C:\\Users\\triet\\OneDrive\\Máy tính\\LAB\\Lab1\\examination.txt";
    
    public ExaminationList() {
         list = new ArrayList<>();
         size = 0;
         try
         {
             readExamination(urlExamination);
         }catch(Exception e)
         {
             System.out.println("Error");
         }
    }

    public ArrayList<Examination> getList() {
        return list;
    }

    public void setList(ArrayList<Examination> list) {
        this.list = list;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    
    
    public Examination searchExaminationByID( String id )
    {
        for (Examination examination : list) {
            if( examination.getExaminationID().equals(id) )
            {
                return examination;
            }
        }
        return null;
    }
    
    public boolean addExamination( Examination add )
    {
        if( searchExaminationByID(add.getExaminationID()) != null )
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
                writeExamination(urlExamination);
            }catch( Exception e )
            {
                System.out.println("Error");
            }
            return true;
        }
    }
    
    public boolean removeExamination( String id )
    {
        if( searchExaminationByID(id) != null )
        {
            list.remove(searchExaminationByID(id));
            try
            {
                writeExamination(urlExamination);
            }catch(Exception e)
            {
                System.out.println("Error");
            }
            return true;
        }
        else
        {
            System.out.println("Cannot find valid examination");
            return false;
        }
    }
    
    public boolean updateExamination( String id )
    {
        if( searchExaminationByID(id) != null )
        {
            
            System.out.println("UPDATE!!!");
            searchExaminationByID(id).input();
            try
            {
                writeExamination(urlExamination);
            }catch(Exception e)
            {
                System.out.println("Error");
            }
            return true;
        }
        else
        {
            System.out.println("Canot find valid examination");
            return false;
        }
    }
    
    public void display()
    {
        for (Examination examination : list) {
            examination.output();
        }
    }
    
    //ham nay de ghi danh sach dog
    public void writeExamination( String filename) throws Exception
    {
        if( list != null && list.size() > 0 )
        {
            PrintWriter w = new PrintWriter(filename);
            for(Examination examination:list)
            {
                w.println(examination.toString());
            }
            
            w.close();
        }
    }
    
    //ham nay de doc file phi nhan tra ve danh sach cac con Dog
    public void readExamination(String filename) throws Exception
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
            if(arr.length == 5)
            {
                Examination p = new Examination(arr[0], arr[1], arr[2], arr[3], arr[4]);
                this.addExamination(p);
            }
        }
        f.close();
        bf.close();
    }
    
    
    
}
