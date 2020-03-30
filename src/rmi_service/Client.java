package rmi_service;

import com.google.gson.Gson;
import model.ResponseMessage;
import model.Student;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        StudentInterface stub = (StudentInterface) registry.lookup("student");
        log("started");
        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();
        while(true) {
            System.out.println("Enter student ID to search...");
            String studentId = scanner.nextLine();
            if(studentId.equals("terminate")) {
                log("terminated");
                return;
            }
            String response = stub.getStudent(studentId);
            log("searching for student #" + studentId);
            ResponseMessage responseMessage = gson.fromJson(response, ResponseMessage.class);
            if(responseMessage.getStatus() == 200) {
                log("student found");
                Student student = gson.fromJson(responseMessage.getMessage(), Student.class);
                student.printInfo();
                log("end of result\n");
            } else {
                log("error: " + responseMessage.getMessage());
            }
        }
    }

    private static void log(String message) {
        System.out.println("RMI Client: " + message);
    }
}
