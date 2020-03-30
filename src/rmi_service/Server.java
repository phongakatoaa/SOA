package rmi_service;

import com.google.gson.Gson;
import data.DataStore;
import model.ResponseMessage;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements StudentInterface {
    private DataStore dataStore;
    private Gson gson;

    public Server() {
        dataStore = new DataStore();
        gson = new Gson();
    }

    @Override
    public String getStudent(String studentId) {
        String result = dataStore.getStudentAsJsonString(studentId);
        ResponseMessage responseMessage;
        if (result == null) {
            responseMessage = new ResponseMessage(500, "Student #" + studentId + " not found");
        } else {
            responseMessage = new ResponseMessage(200, result);
        }
        return gson.toJson(responseMessage);
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Server obj = new Server();
        StudentInterface stub = (StudentInterface) UnicastRemoteObject.exportObject(obj, 0);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("student", stub);
        log("started");
    }

    private static void log(String message) {
        System.out.println("RMI Server: " + message);
    }
}
