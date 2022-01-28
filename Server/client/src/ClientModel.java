import utility.observer.subject.LocalSubject;

public interface ClientModel extends LocalSubject
{
    void sendMessage(String message);
    void register(String username, String password, int age);
    void login(String username, String password);
    void logout(String username);
    void bookTicket(String user, String ticket);
    void getBookedTickets(String user);
    void close();
}
