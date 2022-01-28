package model;

public class Ticket
{


    private String from;
    private String to;

    public Ticket(String from, String to)
    {
        this.from = from;
        this.to = to;
    }

    public void setFrom(String from)
    {
        this.from = from;
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

    public String toString()
    {
        return "From: " + from + " to: " + to;
    }

}
