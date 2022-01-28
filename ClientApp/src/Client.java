import com.google.gson.Gson;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client implements ClientModel, RemoteListener
{
    public static final String HOST = "localhost";
    private String host;
    private PropertyChangeHandler property;
    private Gson gson;
    private LocalModel model;
    private RemoteModel remoteModel;
    private ModelManager modelManager;

    public Client(String host, LocalModel model) throws MalformedURLException, RemoteException, NotBoundException
    {
        this.host = host;
        this.model = model;
        this.remoteModel = (RemoteModel) Naming.lookup("rmi://" + host + ":1099/serverStub");
        UnicastRemoteObject.exportObject(this, 0);
        this.property = new PropertyChangeHandler(this, true);
        this.remoteModel.addListener(this);
        gson = new Gson();
        System.out.println("Client started...");
    }



    @Override
    public void propertyChange(ObserverEvent event) throws RemoteException
    {
        System.out.println("Client listener");
        property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2());
    }

    @Override
    public boolean addListener(GeneralListener listener, String... propertyNames)
    {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener listener, String... propertyNames)
    {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public void sendMessage(String message)
    {
        try
        {
            remoteModel.sendMessage(message);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void register(String username, String password, int age)
    {
        try
        {
            remoteModel.register(username, password, age);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void login(String username, String password)
    {
        try
        {
            remoteModel.login(username, password);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void logout(String username)
    {
        try
        {
            remoteModel.logout(username);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void bookTicket(String user, String ticket)
    {
        try
        {
            remoteModel.bookTicket(user, ticket);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void getBookedTickets(String user)
    {
        try
        {
            remoteModel.getBookedTickes(user);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void getRoutes(String from, String to, String date)
    {
        try
        {
            remoteModel.getRoutes(from, to, date);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void getTicketRouteList(String user)
    {
        try
        {
            System.out.println("4");
            remoteModel.getTicketRouteList(user);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTicket(int id)
    {
        try
        {
            remoteModel.removeTicket(id);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void close()
    {
        try
        {
            UnicastRemoteObject.unexportObject(this, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
