import com.google.gson.Gson;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.util.ArrayList;
import java.util.List;

public class TicketViewModel implements LocalListener
{
    private LocalModel model;
    private boolean taskDone;
    private TicketRouteList ticketRouteList;
    Gson gson;

    public TicketViewModel(LocalModel model)
    {
        this.model = model;
        gson = new Gson();
        model.addListener(this);
    }

    public void deleteTicket(int id)
    {
        model.removeTicket(id);
    }

    public TicketRouteList getList()
    {
        return ticketRouteList;
    }

    public boolean isTaskDone()
    {
        return taskDone;
    }

    public void getTicketRouteList()
    {
        System.out.println("2");
        taskDone = false;
        model.getTicketRouteList();
    }


    public void callTicketList()
    {
        model.getBookedTickets();
    }

    @Override
    public void propertyChange(ObserverEvent event)
    {
        if(event.getPropertyName().equals("ticketRouteList"))
        {
            String parameter = String.valueOf(event.getValue2());
            TicketRouteList ticketRouteList = gson.fromJson(parameter, TicketRouteList.class);
            this.ticketRouteList = ticketRouteList;
            taskDone = true;

        }
    }
}
