import java.util.ArrayList;
import java.util.List;

public class TicketList
{
    private List<Ticket> ticketList;

    public TicketList()
    {
        ticketList = new ArrayList<>();
    }

    public List<Ticket> getTicketList()
    {
        return ticketList;
    }

    public void reserve(Ticket ticket)
    {
        ticketList.add(ticket);
    }

}
