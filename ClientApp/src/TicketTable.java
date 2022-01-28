import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TicketTable
{
    private SimpleStringProperty from;
    private SimpleStringProperty to;

    public TicketTable()
    {
        from = new SimpleStringProperty();
        to = new SimpleStringProperty();
    }

    public void setFrom(String from)
    {
        this.from.set(from);
    }

    public void setTo(String to)
    {
        this.to.set(to);
    }
}
