/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ppk;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Mohammed Ali
 */
//import java.io.*;
//import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    private List<Doctor> doctors;
    private final int MAX_DOCTORS = 10;
    private final String FILE_NAME = "doctors_list.txt";

    public WestminsterSkinConsultationManager() {
        doctors = new ArrayList<Doctor>();
    }
    
    
    public void showMenu() {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("1. Add a new doctor");
            System.out.println("2. Delete a doctor");
            System.out.println("3. Print the list of doctors");
            System.out.println("4. Save doctors to file");
            System.out.println("5. Load doctors from file");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // to consume the newline character after the integer input
            switch (choice) {
                case 1:
                    System.out.print("Enter doctor's name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter doctor's surename: ");
                    String surename = scanner.nextLine();
                    System.out.print("Enter doctor's dob: ");
                    String dateOfBirth = scanner.nextLine();
                    System.out.print("Enter doctor's mobileno: ");
                    String mobileNumber = scanner.nextLine();
                    
                    System.out.print("Enter doctor's medical licence number: ");
                    String medicalLicenceNumber = scanner.nextLine();
                    System.out.print("Enter doctor's specialization: ");
                    String specialisation = scanner.nextLine();
                    Doctor doctor = new Doctor(name,surename,dateOfBirth,mobileNumber, medicalLicenceNumber, specialisation);
                    manager.addDoctor(doctor);
                    break;
                case 2:
                    System.out.print("Enter doctor's medical licence number: ");
                    medicalLicenceNumber = scanner.nextLine();
                    manager.deleteDoctor(medicalLicenceNumber);
                    break;
                case 3:
                    manager.printDoctors();
                    break;
                case 4:
                    manager.saveDoctorsToFile();
                    break;
                case 5:
                    manager.loadDoctorsFromFile();
                    break;
                case 6:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);
        scanner.close();
    }

    @Override
    public void addDoctor(Doctor doctor) {
        if (doctors.size() < MAX_DOCTORS) {
            doctors.add(doctor);
            System.out.println("Doctor added successfully!");
        } else {
            System.out.println("Error: Maximum number of doctors reached.");
        }
    }

    @Override
    public void deleteDoctor(String medicalLicenceNumber) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getMedicalLicenceNumber().equals(medicalLicenceNumber)) {
                System.out.println("Doctor " + doctors.get(i).getName() + " deleted successfully!");
                doctors.remove(i);
                break;
            }
        }
        System.out.println("Total number of doctors: " + doctors.size());
    }

    @Override
    public void printDoctors() {
        Collections.sort(doctors, new Comparator<Doctor>() {
            public int compare(Doctor d1, Doctor d2) {
                return d1.getName().compareTo(d2.getName());
            }
        });

        for (Doctor doctor : doctors) {
            System.out.println("NAME "+doctor.getName()+""+"DOB : " +doctor.getDateOfBirth() +""+"Mobile number:" + doctor.getMobileNumber() + " " +"MedicalNo: " +doctor.getMedicalLicenceNumber() + " " +"speaclity: "+ doctor.getSpecialisation());
        }
    }

    @Override
    public void saveDoctorsToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(doctors);
            out.close();
            fileOut.close();
            System.out.println("Doctors information saved in " + FILE_NAME);
        } catch (IOException e) {
        }
    }

    @Override
    public void loadDoctorsFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream(FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            doctors = (List<Doctor>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Doctors information loaded from " + FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
        }
    }
}