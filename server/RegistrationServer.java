package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import shared.RegistrationService;

public class RegistrationServer {
    public static void main(String[] args) {
        try {
            RegistrationServiceImpl serviceImpl = new RegistrationServiceImpl();
            RegistrationService stub = (RegistrationService) UnicastRemoteObject.exportObject(serviceImpl, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("RegistrationService", stub);

            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
