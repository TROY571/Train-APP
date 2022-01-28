import java.util.ArrayList;
import java.util.List;

public class TicketRouteList
{
    private List<Route> routeList;
    private List<Ticket> ticketList;

    public TicketRouteList()
    {
        routeList = new ArrayList<>();
        ticketList = new ArrayList<>();
    }

    public Route getRouteByIndex(int ind)
    {
        return routeList.get(ind);
    }

    public Ticket getTicketByIndex(int ind)
    {
        return ticketList.get(ind);
    }

    public void addRoute(Route route)
    {
        routeList.add(route);
    }

    public void addTicket(Ticket ticket)
    {
        ticketList.add(ticket);
    }

    public void setTicketList(List<Ticket> ticketList)
    {
        this.ticketList = ticketList;
    }

    public void setRouteList(List<Route> routeList)
    {
        this.routeList = routeList;
    }

    public List<Ticket> getTicketList()
    {
        return ticketList;
    }

    public List<Route> getRouteList()
    {
        return routeList;
    }
}
