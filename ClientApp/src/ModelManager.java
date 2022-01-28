import com.google.gson.Gson;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;


public class ModelManager implements LocalModel, LocalListener
{
    private PropertyChangeHandler  property;
    private ClientModel serverModel;
    private ActiveUser activeUser;
    Gson gson;

    public ModelManager()
    {
        try
        {
            this.serverModel = new Client("localhost", this);
            activeUser = new ActiveUser();
            this.serverModel.addListener(this);
        }
        catch (RemoteException | NotBoundException e)
        {
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        property = new PropertyChangeHandler(this, true);
        gson = new Gson();
    }

    public String getCurrentUsername()
    {
        return activeUser.getUser().getUsername();
    }

    @Override
    public void getTicketRouteList()
    {
        serverModel.getTicketRouteList(getCurrentUsername());
    }

    @Override
    public void removeTicket(int id)
    {
        serverModel.removeTicket(id);
    }

    public User getActiveUser()
    {
        if(activeUser == null)
        {
            return null;
        }
        else
        {
            return activeUser.getUser();
        }
    }


    @Override
    public void propertyChange(ObserverEvent event)
    {
        if(event.getPropertyName().equals("register"))
        {
            System.out.println("here");
            String userTemp = String.valueOf(event.getValue2());
            User userFromJson = gson.fromJson(userTemp, User.class);
            activeUser.setUser(userFromJson);
            System.out.println("User registered "  + userFromJson.getUsername());
            property.firePropertyChange("registerData", userFromJson.getAge(), userFromJson.getUsername());
        }
        else if(event.getPropertyName().equals("log"))
        {
            System.out.println("log listener");
            String userTemp = String.valueOf(event.getValue2());
            User userFromJson = gson.fromJson(userTemp, User.class);
            activeUser.setUser(userFromJson);
            System.out.println("User logged: " + activeUser.getUser().getUsername());
            System.out.println("ageeeeee: " + userFromJson.getAge());
            property.firePropertyChange("registerData", userFromJson.getAge(), userFromJson.getUsername());
        }
        else if(event.getPropertyName().equals("logfail"))
        {
            property.firePropertyChange(event.getPropertyName(), null, event.getValue2());
        }
        else if(event.getPropertyName().equals("logout"))
        {
            activeUser.setUser(null);
            property.firePropertyChange("logout", null, "logout");
            System.out.println("User was logged out");
        }
        else if(event.getPropertyName().equals("book"))
        {
            System.out.println((String)event.getValue2());
        }
        else if(event.getPropertyName().equals("ticketUserList"))
        {
            String parameter = String.valueOf(event.getValue2());
            TicketList ticketFromJson = gson.fromJson(parameter, TicketList.class);
            System.out.println(ticketFromJson.getTicketList());
            property.firePropertyChange(event.getPropertyName(), null, event.getValue2());
        }
        else if(event.getPropertyName().equals("routeList"))
        {
            String eventSecondValue = String.valueOf(event.getValue2());
            RouteList routeList = gson.fromJson(eventSecondValue, RouteList.class);
            System.out.println("I am showing th list");
            if(routeList == null)
            {
                System.out.println("The list is null");
            }
            routeList.listRoutes();
            property.firePropertyChange("routeList", null, eventSecondValue);
        }
        else  if (event.getPropertyName().equals("ticketRouteList"))
        {
            String ticketRouteListJson = String.valueOf(event.getValue2());
            TicketRouteList ticketRouteList = gson.fromJson(ticketRouteListJson, TicketRouteList.class);
            System.out.println("received");
            System.out.println(ticketRouteList.getRouteList());
            System.out.println(ticketRouteList.getTicketList());
            property.firePropertyChange("ticketRouteList", null, ticketRouteListJson);
        }
        else
            {
            property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2());
            System.out.println("Client model manager listener");
            System.out.println("Message: " + event.getValue2());
            }
    }

    @Override
    public boolean addListener(GeneralListener listener, String... propertyNames)
    {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener listener, String... propertyNames)
    {
        return property.removeListener(listener, propertyNames);
    }

    @Override
    public void sendMessage(String message)
    {
        serverModel.sendMessage(message);
    }

    @Override
    public void register(String username, String password, int age)
    {
        serverModel.register(username, password, age);
    }

    @Override
    public void login(String username, String password)
    {
        serverModel.login(username, password);
    }

    @Override
    public void logout()
    {
        serverModel.logout(activeUser.getUser().getUsername());
    }

    @Override
    public void bookTicket(String username,Ticket ticket)
    {
        String ticketG = gson.toJson(ticket);
        serverModel.bookTicket(username, ticketG);
    }

    @Override
    public void getBookedTickets()
    {
        serverModel.getBookedTickets(getActiveUser().getUsername());
    }

    @Override
    public void getRoutes(String from, String to, String date)
    {
        System.out.println(date);
        serverModel.getRoutes(from, to, date);
    }



    @Override
    public void close()
    {

    }
}
