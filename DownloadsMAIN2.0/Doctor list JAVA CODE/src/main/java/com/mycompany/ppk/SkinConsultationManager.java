/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ppk;

/**
 *
 * @author Mohammed Ali
 */
public interface SkinConsultationManager {
    public void addDoctor(Doctor doctor);
    public void deleteDoctor(String medicalLicenceNumber);
    public void printDoctors();
    public void saveDoctorsToFile();
    public void loadDoctorsFromFile();
}