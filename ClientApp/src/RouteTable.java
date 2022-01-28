import javafx.beans.property.SimpleStringProperty;

public class RouteTable
{
    private SimpleStringProperty from;
    private SimpleStringProperty to;
    private SimpleStringProperty exitOfStation;
    private SimpleStringProperty arrivalTime;

    public RouteTable()
    {
        from = new SimpleStringProperty();
        to = new SimpleStringProperty();
        exitOfStation = new SimpleStringProperty();
        arrivalTime = new SimpleStringProperty();
    }


    public void setFrom(String from)
    {
        this.from.set(from);
    }

    public void setTo(String to)
    {
        this.to.set(to);
    }

   public void setExitOfStation(String departureTime)
   {
       this.exitOfStation.set(departureTime);
   }

    public void setArrivalTime(String arrivalTime)
    {
        this.arrivalTime.set(arrivalTime);
    }

    public String getFrom()
    {
        return from.get();
    }

    public String getTo()
    {
        return to.get();
    }

    public String getExitOfStation()
    {
        return exitOfStation.get();
    }

    public String getArrivalTime()
    {
        return arrivalTime.get();
    }
}
