package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RegistrationService extends Remote {
    String registerUser(String username, String password, String email) throws RemoteException;
}
