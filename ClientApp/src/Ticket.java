public class Ticket
{
    private String id;
    private String user;
    private String route;
    private String type;
    private String seat;

    public Ticket(String id, String user, String route, String type, String seat)
    {
        this.id = id;
        this.user = user;
        this.type = type;
        this.route = route;
        this.seat = seat;
    }

    public Ticket()
    {

    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setSeat(String seat)
    {
        this.seat = seat;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public void setRoute(String route)
    {
        this.route = route;
    }

    public String getId()
    {
        return id;
    }

    public String getType()
    {
        return type;
    }

    public String getSeat()
    {
        return seat;
    }

    public String getUser()
    {
        return user;
    }

    public String getRoute()
    {
        return route;
    }

    public String toString()
    {
        return id + " " + seat + " " + route + " " + user + " " + type;
    }
}