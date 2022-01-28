import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Route
{
    private String from;
    private String to;
    private Date date;
    private String departureTime;
    private String arrivalTime;
    private String id;

    public Route()
    {

    }

    public void setDateFromString(String dateString) throws ParseException
    {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = format.parse(dateString);
        this.date = date1;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public void setArrivalTimeTime(String arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(String departureTime)
    {
        this.departureTime = departureTime;
    }

    public String getDepartureTime()
    {
        return departureTime;
    }

    public String getArrivalTime()
    {
        return arrivalTime;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public String getFrom()
    {
        return from;
    }

    public String getTo()
    {
        return to;
    }

    public String getFormattedDate()
    {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = format.format(date);
        return dateString;
    }

    public Date getDate()
    {
        return date;
    }

    public String toString()
    {
        return "From: " + from + " to: " + to + " date: " + date;
    }

}
