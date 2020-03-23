package service;

import com.google.gson.Gson;
import model.ResponseMessage;
import model.Student;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public Client() {
        log("Started");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter student ID to search:...");
                String studentId = scanner.nextLine();
                if (studentId.equals("terminate")) {
                    scanner.close();
                    log("terminated");
                    return;
                }
                log("Sending request...");
                sendStudentRequest(studentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendStudentRequest(String studentId) throws IOException {
        Socket socket = new Socket(ConnectionSetting.HOST, ConnectionSetting.PORT);
        Gson gson = new Gson();
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outToServer.writeBytes(studentId + "\n");
        ResponseMessage responseMessage = gson.fromJson(inFromServer.readLine(), ResponseMessage.class);
        inFromServer.close();
        outToServer.close();
        socket.close();
        if (responseMessage.getStatus() == 500) {
            System.out.println(responseMessage.getMessage());
        } else {
            Student student = gson.fromJson(responseMessage.getMessage(), Student.class);
            student.printInfo();
        }
    }

    private void log(String message) {
        System.out.println("Client: " + message);
    }
}
