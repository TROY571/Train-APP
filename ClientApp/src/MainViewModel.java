import com.google.gson.Gson;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.util.List;

public class MainViewModel implements LocalListener
{
    private LocalModel model;
    private RouteList routeList;
    Gson gson;

    public MainViewModel(LocalModel model)
    {
        this.model = model;
        gson = new Gson();
        model.addListener(this);
    }

    public void getRouteTable(String from, String to, String date)
    {
        model.getRoutes(from, to, date);

    }

    public RouteList getRouteList()
    {
        return routeList;
    }

    public void bookTicket(String username, Ticket ticket)
    {
        model.bookTicket(username, ticket);
    }

    public String getActiveUser()
    {
        return model.getCurrentUsername();
    }

    public User getCurrentUser()
    {
        return model.getActiveUser();
    }

    @Override
    public void propertyChange(ObserverEvent event)
    {
        if(event.getPropertyName().equals("routeList"))
        {
            String eventSecondValue = String.valueOf(event.getValue2());
            RouteList routeList = gson.fromJson(eventSecondValue, RouteList.class);
            if(routeList == null)
            {

            }
            routeList.listRoutes();
            this.routeList = routeList;
        }
    }
}
