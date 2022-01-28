import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Controller
{
    private Region root;
    private ViewHandler  viewHandler;
    private MainViewModel viewModel;

    @FXML
    private Button ticketBtn;
    @FXML
    private ChoiceBox fromBox;
    @FXML
    private ChoiceBox toBox;
    @FXML
    private DatePicker routeDate;
    @FXML
    private ChoiceBox seatBox;
    @FXML
    private ChoiceBox typeBox;
    @FXML
    private TableView routeTable;
    @FXML
    private Label fromLabel;
    @FXML
    private Label toLabel;
    @FXML
    private Label departureTimeLabel;
    @FXML
    private Label arrivalTimeLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label errorLbl;

    public void init(ViewHandler viewHandler, MainViewModel viewModel, Region root)
    {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
    }

    public Region getRoot()
    {
        return root;
    }

    public void reset()
    {

    }
    public void updateLabel()
    {
        routeTable.setOnMouseClicked((MouseEvent e) ->
        {
            if(e.getEventType() == e.MOUSE_CLICKED)
            {
                RouteTable routeTableTemp = (RouteTable) routeTable.getSelectionModel().getSelectedItem();
                departureTimeLabel.setText(routeTableTemp.getExitOfStation());
                arrivalTimeLabel.setText(routeTableTemp.getArrivalTime());
                fromLabel.setText(routeTableTemp.getFrom());
                toLabel.setText(routeTableTemp.getTo());
            }
        });
    }

    public void bookTicket()
    {
        if(viewModel.getCurrentUser() != null)
        {
            errorLbl.setText("");
            int ind = routeTable.getSelectionModel().getSelectedIndex();
            RouteList routeList = viewModel.getRouteList();
            Route route = routeList.getRouteByIndex(ind);
            String routeId = route.getId();
            String seat = String.valueOf(seatBox.getValue());
            System.out.println(seat);
            String type = String.valueOf(typeBox.getValue());
            Ticket ticket = new Ticket();
            ticket.setSeat(seat);
            ticket.setRoute(routeId);
            ticket.setUser(viewModel.getActiveUser());
            ticket.setType(type);

            if(routeId != null && !(seat.equals("null")) && !(type.equals("null")))
            {
                viewModel.bookTicket(viewModel.getActiveUser(), ticket);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Ticket was added");
                alert2.setHeaderText("The ticket was added");
                alert2.show();
            }
            else
            {
                errorLbl.setText("Every field must be filled");
            }
        }
        else
        {
            errorLbl.setText("You must be logged in order to book a ticket");
        }
    }

    @FXML
    public void getTicketList()
    {
        List<RouteTable> tableList = new ArrayList<>();
        String from = String.valueOf(fromBox.getValue());
        String to = String.valueOf(toBox.getValue());
        LocalDate date = routeDate.getValue();
        String dateString = date.format(DateTimeFormatter.ofPattern("dd/M/yyyy"));
        timeLabel.setText(dateString);

        viewModel.getRouteTable(from, to, dateString);
        System.out.println("First part");

        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }


        System.out.println("Second part");
        RouteList routeList = viewModel.getRouteList();
        if(routeList == null)
        {
            System.out.println("It is null u typ");
        }
        else
            {
                for(Route temp: routeList.getRoutes())
                {
                    RouteTable table = new RouteTable();
                    table.setFrom(temp.getFrom());
                    table.setTo(temp.getTo());
                    System.out.println(temp.getDepartureTime());
                    table.setExitOfStation(temp.getDepartureTime());
                    System.out.println(temp.getArrivalTime());
                    table.setArrivalTime(temp.getArrivalTime());
                    tableList.add(table);
                }

                Task<ObservableList<RouteTable>> task = new Task<ObservableList<RouteTable>>()
                {
                    @Override
                    protected ObservableList<RouteTable> call() throws Exception
                    {
                        return FXCollections.observableArrayList(tableList);
                    }
                };
                routeTable.itemsProperty().bind(task.valueProperty());
                new Thread(task).start();
            }
    }

    @FXML
    public void openTicketView()
    {
        viewHandler.openView("ticket");
    }

    @FXML
    public void openInfoView()
    {
        viewHandler.openView("info");
    }

    @FXML
    public void openLogView()
    {
        viewHandler.openView("log");
    }

}
