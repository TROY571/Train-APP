import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Timer;


public class TicketViewController
{
    private Region root;
    private ViewHandler  viewHandler;
    private TicketViewModel ticketViewModel;
    private TicketRouteList ticketRouteList;
    private Ticket selectedTicket;

    @FXML
    private TableView ticketTable;
    @FXML
    private Label userLbl;
    @FXML
    private Label seatLbl;
    @FXML
    private Label typeLbl;
    @FXML
    private Label dateLbl;
    @FXML
    private Label deleteLbl;

    public void init(ViewHandler viewHandler, TicketViewModel ticketViewModel, Region root)
    {
        this.viewHandler = viewHandler;
        this.root = root;
        this.ticketViewModel = ticketViewModel;
    }

    public TicketViewModel getTicketViewModel()
    {

        return ticketViewModel;
    }


    public Region getRoot()
    {
        return root;
    }

    public void reset()
    {

    }

    @FXML
    public void deleteTicket()
    {
        if(selectedTicket != null)
        {
            deleteLbl.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete ticket");
            alert.setHeaderText("Are you certain?");
            alert.setContentText("The following ticket will be deleted -> Id: " + selectedTicket.getId());

            Optional<ButtonType> alertResult = alert.showAndWait();

            if(!alertResult.isPresent() || alertResult.get() != ButtonType.OK)
            {

            }
            else
            {
                ticketViewModel.deleteTicket(Integer.parseInt(selectedTicket.getId()));

                List<RouteTable> tableList = new ArrayList<>();
                ticketViewModel.getTicketRouteList();
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
                ticketRouteList = ticketViewModel.getList();
                if(ticketRouteList == null)
                {
                    System.out.println("It is null u typ");
                }
                else {
                    for (Route temp : ticketRouteList.getRouteList()) {
                        RouteTable table = new RouteTable();
                        table.setFrom(temp.getFrom());
                        table.setTo(temp.getTo());
                        System.out.println(temp.getDepartureTime());
                        table.setExitOfStation(temp.getDepartureTime());
                        System.out.println(temp.getArrivalTime());
                        table.setArrivalTime(temp.getArrivalTime());
                        tableList.add(table);
                    }

                    Task<ObservableList<RouteTable>> task = new Task<ObservableList<RouteTable>>() {
                        @Override
                        protected ObservableList<RouteTable> call() throws Exception {
                            return FXCollections.observableArrayList(tableList);
                        }
                    };
                    ticketTable.itemsProperty().bind(task.valueProperty());
                    new Thread(task).start();
                    selectedTicket = null;
                    userLbl.setText("");
                    seatLbl.setText("");
                    typeLbl.setText("");
                    dateLbl.setText("");
                }

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Ticket deleted");
                alert2.setHeaderText("The ticket was deleted");
                alert2.show();
                }

        }
        else
        {
            System.out.println("Its null");
            deleteLbl.setText("You must select a ticket in order to remove it");
            deleteLbl.setTextFill(Paint.valueOf("#E53A15"));
        }

    }

    @FXML
    public void updateLabel()
    {
        ticketTable.setOnMouseClicked((MouseEvent e) ->
        {
            if(e.getEventType() == e.MOUSE_CLICKED)
            {
                int ind = ticketTable.getSelectionModel().getSelectedIndex();
                selectedTicket = ticketRouteList.getTicketByIndex(ind);
                userLbl.setText(selectedTicket.getUser());
                seatLbl.setText(selectedTicket.getSeat());
                typeLbl.setText(selectedTicket.getType());
                dateLbl.setText(ticketRouteList.getRouteByIndex(ind).getFormattedDate());
            }
        });
    }


    @FXML
    public void setTicketRouteList()
    {
        System.out.println("1");
        List<RouteTable> tableList = new ArrayList<>();
        ticketViewModel.getTicketRouteList();
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
        ticketRouteList = ticketViewModel.getList();
        if(ticketRouteList == null)
        {
            System.out.println("It is null u typ");
        }
        else
        {
            for(Route temp: ticketRouteList.getRouteList())
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
            ticketTable.itemsProperty().bind(task.valueProperty());
            new Thread(task).start();
        }
    }

    @FXML
    public void openMainView()
    {
        viewHandler.openView("main");
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
