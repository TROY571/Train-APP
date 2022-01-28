import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
    private Scene currentScene;
    private Stage primaryStage;
    private Scene controllerScene;
    private TicketViewController ticketViewController;
    private PersonalInfoController personalInfoController;
    private LogViewController logViewController;
    private ViewModelFactory viewModelFactory;
    private  Controller controller;

    public ViewHandler(ViewModelFactory viewModelFactory)
    {
        this.currentScene = new Scene(new Region());
        this.controllerScene = new Scene(new Region());
        this.viewModelFactory = viewModelFactory;
    }

    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        String id = "main";
        openView(id);
    }

    public void openView(String id)
    {
        Region root = null;

        switch (id)
        {
            case "ticket":
                root = loadTicketView("/Ticket.fxml");
                break;
            case "main":
                root = loadMainView("/main.fxml");
                break;
            case "info":
                root = loadInfoView("/PersonalInfo.fxml");
                break;
            case "log":
                root = loadLogView("/logView.fxml");
                break;
        }
        currentScene.setRoot(root);
        String title = " ";

        if (root.getUserData() != null)
        {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.show();
    }

    private Region loadTicketView(String fxmlFile)
    {
        if(ticketViewController == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                ticketViewController = loader.getController();
                ticketViewController.init(this, viewModelFactory.getTicketViewModel(), root);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            ticketViewController.reset();
        }
        return ticketViewController.getRoot();
    }


    private Region loadMainView(String fxmlFile)
    {
        if(controller == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                controller = loader.getController();
                controller.init(this, viewModelFactory.getMainViewModel(), root);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            controller.reset();
        }
        return controller.getRoot();
    }

    private Region loadInfoView(String fxmlFile)
    {
        if(personalInfoController == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                personalInfoController = loader.getController();
                personalInfoController.init(this, viewModelFactory.getPersonalInfoViewModel(), root);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            personalInfoController.reset();
        }
        return personalInfoController.getRoot();
    }

    public Region loadLogView(String fxmlFile)
    {
        if(logViewController == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                logViewController = loader.getController();
                logViewController.init(this, viewModelFactory.getLogViewModel(), root);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
            {
                logViewController.reset();
            }

        return logViewController.getRoot();
    }




}
