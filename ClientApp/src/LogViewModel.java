import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.awt.*;

public class LogViewModel implements LocalListener
{
    private StringProperty username;
    private StringProperty password;
    private StringProperty error;
    private LocalModel model;

    public LogViewModel(LocalModel model)
    {
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        error = new SimpleStringProperty();
        this.model = model;
        model.addListener(this);
    }

    public StringProperty getError()
    {
        return  error;
    }

    public void clear()
    {
        username.setValue("");
        password.setValue("");
        error.setValue("");
    }

    @Override
    public void propertyChange(ObserverEvent event)
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                if(event.getPropertyName().equals("registerData"))
                {

                }
                else if(event.getPropertyName().equals("logfail"))
                {
                    error.setValue(event.getValue2().toString());
                }
            }
        });
    }

    public void register(String username, String password, int age)
    {
        if(model.getActiveUser() == null)
        {
            error.setValue("");
            model.register(username, password, age);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User registered");
            alert.setHeaderText("The user " + username + " was registered");
            alert.show();
        }
        else
        {
            error.setValue("User is already logged");
        }


    }

    public void log(String username, String password)
    {
        if (model.getActiveUser() == null)
        {
            error.setValue("");
            model.login(username, password);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User logged");
            alert.setHeaderText("The user " + username + " was logged");
            alert.show();
        }
        else
        {
            model.login(username, password);
        }
    }

    public void logout()
    {
        error.setValue("");
        model.logout();
    }
}
