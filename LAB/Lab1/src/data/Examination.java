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
public class Examination {
    private String examinationID;
    private String doctorID;
    private String patientID;
    private String result;
    private String date;

    public Examination() {
    }

    public Examination(String examinationID, String doctorID, String patientID, String result, String date) {
        this.examinationID = examinationID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.result = result;
        this.date = date;
    }

    public String getExaminationID() {
        return examinationID;
    }

    public void setExaminationID(String examinationID) {
        this.examinationID = examinationID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public void input()
    {
        boolean flag = false;
        do
        {
            try
            {
                examinationID = inputString("Input examination ID[Exxx where x is number 0-9]: ", "E[0-9]{3}");
                doctorID = inputString("Input doctor ID[Dxxx where x is number 0-9]: ", "D[0-9]{3}");
                patientID = inputString("Input patient ID[Pxxx where x is number 0-9]: ", "P[0-9]{3}");
                result = inputString("Input result: ", "^[a-zA-Z]+([a-zA-Z ]+)*$");
                date  = MyTool.dataToStr(new Date(), "dd/MM/yyyy");
                flag = true;
            }catch( Exception e )
            {
                System.out.println("Error");
            }
        }while(!flag);
    }
    
    public void output()
    {
        String str = String.format("%-30s%-30s%-30s%-30s%-30s", examinationID, doctorID, patientID, result, date);
        System.out.println(str);
    }

    @Override
    public String toString() {
        String str = String.format("%s, %s, %s, %s, %s", examinationID, doctorID, patientID, result, date);
        return str;
    }
}
