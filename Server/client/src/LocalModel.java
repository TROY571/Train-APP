import model.Ticket;
import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;

public interface LocalModel extends LocalSubject
{
    void sendMessage(String message) throws RemoteException;
    void register(String username, String password, int age);
    void login(String username, String password);
    void logout();
    void bookTicket(String username, Ticket ticket);
    void getBookedTickets(String user);
    void close();
}
