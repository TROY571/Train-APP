import java.util.List;

public class RouteList
{
    private List<Route> routes;

    public RouteList(List<Route> routes)
    {
        this.routes = routes;
    }

    public RouteList()
    {

    }

    public Route getRouteByIndex(int ind)
    {
        return routes.get(ind);
    }

    public void setRoutes(List<Route> routes)
    {
        this.routes = routes;
    }

    public List<Route> getRoutes()
    {
        return routes;
    }

    public void listRoutes()
    {
        for(Route route : routes)
        {
            System.out.println(route.toString());
        }
    }
}
