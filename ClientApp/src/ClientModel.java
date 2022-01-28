import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;

public interface ClientModel extends LocalSubject
{
    void sendMessage(String message);
    void register(String username, String password, int age);
    void login(String username, String password);
    void logout(String username);
    void bookTicket(String user, String ticket);
    void getBookedTickets(String user);
    void getRoutes(String from, String to, String date);
    void getTicketRouteList(String user);
    void removeTicket(int id);
    void close();
}
