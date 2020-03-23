package service;

import com.google.gson.Gson;
import data.DataStore;
import model.ResponseMessage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() {
        log("started");
        DataStore dataStore = new DataStore();
        try {
            ServerSocket serverSocket = new ServerSocket(ConnectionSetting.PORT);
            while (true) {
                Socket connectionSocket = serverSocket.accept();
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                log("Request received");
                String studentId;
                studentId = inFromClient.readLine();
                log("Querying student " + studentId + "...");
                ResponseMessage responseMessage;
                String studentJson = dataStore.getStudentAsJsonString(studentId);
                if (studentJson != null) {
                    responseMessage = new ResponseMessage(200, studentJson);
                } else {
                    responseMessage = new ResponseMessage(500, "Student #" + studentId + " not found");
                }
                Gson gson = new Gson();
                log("Sending response");
                outToClient.writeBytes(gson.toJson(responseMessage) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(String message) {
        System.out.println("Server: " + message);
    }
}
