import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject
{
    void sendMessage(String message) throws RemoteException;
    void register(String username, String password, int age) throws RemoteException;
    void login(String username, String password) throws RemoteException;
    void logout(String username) throws RemoteException;
    void bookTicket(String user, String ticket) throws RemoteException;
    void getBookedTickes(String user) throws RemoteException;
    void getRoutes(String from, String to, String date) throws RemoteException;
    void getTicketRouteList(String user) throws RemoteException;
    void removeTicket(int id) throws RemoteException;
}
