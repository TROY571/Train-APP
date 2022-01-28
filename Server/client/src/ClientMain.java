import model.Ticket;
import model.User;

import java.util.Scanner;

public class ClientMain
{
    public static void main(String[] args)
    {
        ModelManager modelManager = new ModelManager();
        Scanner sc = new Scanner(System.in);

        while (true)
        {
            System.out.println("Enter a command: ");
            String command = sc.nextLine();

            if(command.equals("message"))
            {
                System.out.println("Enter a message");
                String message = sc.nextLine();
                modelManager.sendMessage(message);
            }
            else if(command.equals("register"))
            {
                System.out.println("Username: ");
                String username = sc.nextLine();
                System.out.println("Password: ");
                String password = sc.nextLine();
                System.out.println("Enter age: ");
                int age = sc.nextInt();
                modelManager.register(username, password, age);

            }
            else if (command.equals("log"))
            {
                System.out.println("Username: ");
                String username = sc.nextLine();
                System.out.println("Password: ");
                String password = sc.nextLine();
                modelManager.login(username, password);
            }
            else if(command.equals("logout"))
            {
                modelManager.logout();
            }
            else if(command.equals("book"))
            {

                User user = modelManager.getActiveUser();

                if(user != null)
                {
                    System.out.println("Enter starting point: ");
                    String from = sc.nextLine();
                    System.out.println("Enter destination: ");
                    String to = sc.nextLine();
                    Ticket ticket = new Ticket(from, to);
                    modelManager.bookTicket(modelManager.getActiveUser().getUsername() , ticket);
                }
                else
                {
                    System.out.println("User is null");
                }
            }
            else if(command.equals("get"))
            {
                System.out.println("Current user: " + modelManager.getCurrentUsername());
            }
            else if(command.equals("ticketList"))
            {
                modelManager.getBookedTickets(modelManager.getActiveUser().getUsername());
            }
            else
            {
                break;
            }
        }

    }
}
