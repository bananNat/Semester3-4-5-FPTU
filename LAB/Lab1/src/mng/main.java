/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import data.Department;
import data.DepartmentList;
import data.Doctor;
import data.DoctorList;
import data.Examination;
import data.ExaminationList;
import data.Patient;
import data.PatientList;
import java.awt.BorderLayout;
import java.util.Scanner;

/**
 *
 * @author triet
 */
public class main {
    public static void main(String[] args) {
        
        DepartmentList bList = new DepartmentList();
        DoctorList dList = new DoctorList();
        ExaminationList eList = new ExaminationList();
        PatientList pList = new PatientList();
        
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        int choicesub = 0;
        do
        {
            System.out.println("1/ Add info");
            System.out.println("2/ Update info");
            System.out.println("3/ Remove info");
            System.out.println("4/ Display");
            System.out.println("5/ Quit");
            System.out.println("Input your choice: ");
            choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                {
                    do
                    {
                        System.out.println("1/ Department");
                        System.out.println("2/ Doctor");
                        System.out.println("3/ Patient");
                        System.out.println("4/ Examination");
                        System.out.println("Which one you want to add[1-4]: ");
                        sc = new Scanner(System.in);
                        choicesub = sc.nextInt();
                        if(choicesub < 1 || choicesub > 4)
                        {
                            System.out.println("Please input 1-4!");
                        }
                    }while(choicesub < 1 || choicesub > 4);
                    
                    switch(choicesub)
                    {
                        case 1:
                        {
                            Department newB = new Department();
                            newB.input();
                            bList.addDepartment(newB);
                            System.out.println("Done");
                            break;
                        }
                        case 2:
                        {
                            Doctor newD = new Doctor();
                            if( bList.getSize() == 0 )
                            {
                                System.out.println("Please input department first");
                                break;
                            }
                            bList.display();
                            System.out.println("Please choose existed department!!");
                            do
                            {
                                newD.input();
                                if( bList.searchDepartmentByID(newD.getDepartmentID()) == null )
                                {
                                    System.out.println("Cannot find valid department");
                                }
                            }while( bList.searchDepartmentByID(newD.getDepartmentID()) == null );
                            dList.addDoctor(newD);
                            System.out.println("Done");
                            break;
                        }
                        case 3:
                        {
                            Patient newP = new Patient();
                            newP.input();
                            pList.addPatient(newP);
                            System.out.println("Done");
                            break;
                        }
                        case 4:
                            Examination newE = new Examination();
                            if( dList.getSize() == 0 )
                            {
                                System.out.println("Please input some doctor first");
                                break;
                            }
                            if( pList.getSize() == 0 )
                            {
                                System.out.println("Please input some patient first");
                            }
                            dList.display();
                            pList.display();
                            
                            do
                            {
                                newE.input();
                                if( dList.searchDoctorByID(newE.getDoctorID()) == null )
                                {
                                    System.out.println("Cannot find valid doctor");
                                }
                                if( pList.searchPatientByID(newE.getPatientID()) == null )
                                {
                                    System.out.println("Cannot find valid patient");
                                }
                            }while( dList.searchDoctorByID(newE.getDoctorID()) == null || pList.searchPatientByID(newE.getPatientID()) == null );
                            eList.addExamination(newE);
                            System.out.println("Done");
                            break;
                    }
                    break;
                }
                
                case 2:
                {
                    do
                    {
                        
                        System.out.println("1/ Department");
                        System.out.println("2/ Doctor");
                        System.out.println("3/ Patient");
                        System.out.println("4/ Examination");
                        System.out.println("Which one you want to update[1-4]: ");
                        sc = new Scanner(System.in);
                        choicesub = sc.nextInt();
                        if(choicesub < 1 || choicesub > 4)
                        {
                            System.out.println("Please input 1-4!");
                        }
                    }while(choicesub < 1 || choicesub > 4);
                    
                    switch(choicesub)
                    {
                        case 1:
                        {
                            if( bList.getSize() == 0 )
                            {
                                System.out.println("Emptylist nothing to update");
                                break;
                            }
                            System.out.println("Department List!!!");
                            bList.display();
                            String updateID;
                            System.out.println("Input ID of department want to update: ");
                            sc = new Scanner(System.in);
                            updateID = sc.nextLine();
                            bList.updateDepartment(updateID);
                            System.out.println("Done");
                            break;
                        }
                        case 2:
                        {
                            if( dList.getSize() == 0 )
                            {
                                System.out.println("Emptylist nothing to update");
                                break;
                            }
                            System.out.println("Doctor List!!!");
                            dList.display();
                            String updateID;
                            System.out.println("Input ID of doctor want to update: ");
                            sc = new Scanner(System.in);
                            updateID = sc.nextLine();
                            bList.display();
                            dList.updateDoctor(updateID);
                            System.out.println("Done");
                            break;
                        }
                        case 3:
                        {
                            if( pList.getSize() == 0 )
                            {
                                System.out.println("Emptylist nothing to update");
                                break;
                            }
                            System.out.println("Patient List!!!");
                            pList.display();
                            String updateID;
                            System.out.println("Input ID of patient want to update: ");
                            sc = new Scanner(System.in);
                            updateID = sc.nextLine();
                            pList.updatePatient(updateID);
                            System.out.println("Done");
                            break;
                        }
                        case 4:
                        {
                            if( eList.getSize() == 0 )
                            {
                                System.out.println("Emptylist nothing to update");
                                break;
                            }
                            System.out.println("Examination List!!!");
                            eList.display();
                            String updateID;
                            System.out.println("Input ID of examination want to update: ");
                            sc = new Scanner(System.in);
                            updateID = sc.nextLine();
                            dList.display();
                            pList.display();
                            eList.updateExamination(updateID);
                            System.out.println("Done");
                            break;
                        }
                    }
                    break;
                }
                
                case 3:
                {
                    do
                    {
                        System.out.println("1/ Department");
                        System.out.println("2/ Doctor");
                        System.out.println("3/ Patient");
                        System.out.println("4/ Examination");
                        System.out.println("Which one you want to remove[1-4]: ");
                        sc = new Scanner(System.in);
                        choicesub = sc.nextInt();
                        if(choicesub < 1 || choicesub > 4)
                        {
                            System.out.println("Please input 1-4!");
                        }
                    }while(choicesub < 1 || choicesub > 4);
                    
                    switch(choicesub)
                    {
                        case 1:
                        {
                            if( bList.getSize() == 0 )
                            {
                                System.out.println("Emptylist nothing to remove");
                                break;
                            }
                            System.out.println("Department List!!!");
                            bList.display();
                            String removeID;
                            System.out.println("Input ID of department want to remove: ");
                            sc = new Scanner(System.in);
                            removeID = sc.nextLine();
                            bList.removeDepartment(removeID);
                            System.out.println("Done");
                            break;
                        }
                        
                        case 2:
                        {
                            if( dList.getSize() == 0 )
                            {
                                System.out.println("Emptylist nothing to remove");
                                break;
                            }
                            System.out.println("Doctor List!!!");
                            dList.display();
                            String removeID;
                            System.out.println("Input ID of doctor want to remove: ");
                            sc = new Scanner(System.in);
                            removeID = sc.nextLine();
                            dList.removeDoctor(removeID);
                            System.out.println("Done");
                            break;
                        }
                        
                        case 3:
                        {
                            if( pList.getSize() == 0 )
                            {
                                System.out.println("Emptylist nothing to remove");
                                break;
                            }
                            System.out.println("Patient List!!!");
                            pList.display();
                            String removeID;
                            System.out.println("Input ID of patient want to remove: ");
                            sc = new Scanner(System.in);
                            removeID = sc.nextLine();
                            pList.removePatient(removeID);
                            System.out.println("Done");
                            break;
                        }
                        
                        case 4:
                        {
                            if( eList.getSize() == 0 )
                            {
                                System.out.println("Emptylist nothing to remove");
                                break;
                            }
                            System.out.println("Examination List!!!");
                            eList.display();
                            String removeID;
                            System.out.println("Input ID of examination want to remove: ");
                            sc = new Scanner(System.in);
                            removeID = sc.nextLine();
                            eList.removeExamination(removeID);
                            System.out.println("Done");
                            break;
                        }
                    }
                    break;
                }
                
                case 4:
                {
                    do
                    {
                        System.out.println("1/ Department");
                        System.out.println("2/ Doctor");
                        System.out.println("3/ Patient");
                        System.out.println("4/ Examination");
                        System.out.println("Which one you want to display[1-4]: ");
                        sc = new Scanner(System.in);
                        choicesub = sc.nextInt();
                        if(choicesub < 1 || choicesub > 4)
                        {
                            System.out.println("Please input 1-4!");
                        }
                    }while(choicesub < 1 || choicesub > 4);
                    
                    switch(choicesub)
                    {
                        case 1:
                        {
                            if( bList.getSize() == 0 )
                            {
                                System.out.println("Emptylist nothing to display");
                                break;
                            }
                            bList.display();
                            break;
                        }
                        
                        case 2:
                        {
                            if( dList.getSize() == 0 )
                            {
                                System.out.println("Emptylist nothing to display");
                                break;
                            }
                            dList.display();
                            break;
                        }
                        
                        case 3:
                        {
                            if( pList.getSize() == 0 )
                            {
                                System.out.println("Emptylist nothing to display");
                                break;
                            }
                            pList.display();
                            break;
                        }
                        
                        case 4:
                        {
                            if( eList.getSize() == 0 )
                            {
                                System.out.println("Emptylist nothing to display");
                                break;
                            }
                            eList.display();
                            break;
                        }
                        
                    }
                    break;
                }
            }
                    
        }while(choice < 5 && choice > 0);
    }
}
