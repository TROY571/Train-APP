package  model;

import java.util.ArrayList;
import java.util.List;

public class User
{
    private String username;
    private String password;
    private int  age;
    private boolean isLogged;
    private TicketList list;
    private List<Ticket> ticketList;

    public User(String username, String password, int age)
    {
        this.username = username;
        this.password = password;
        this.age = age;
        ticketList = new ArrayList<>();
        list = new TicketList();
    }

    public User()
    {

    }

    public int getAge()
    {
        return age;
    }

    public TicketList getTicketList()
    {
        return list;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setLogged(boolean logged)
    {
        this.isLogged = logged;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public boolean isLogged()
    {
        return isLogged;
    }

    public void bookTicket(Ticket ticket)
    {
        System.out.println("hh");
        if(ticket == null)
        {
            System.out.println("I am null");
        }
        else
        {
            if(ticketList != null)
            {
                list.reserve(ticket);
                System.out.println(ticketList);
            }
            else
            {
                System.out.println("Oh the list is null");
            }

        }
    }
}
