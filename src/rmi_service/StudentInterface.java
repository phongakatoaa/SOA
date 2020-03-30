package rmi_service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentInterface extends Remote {

    String getStudent(String studentId) throws RemoteException;
}
