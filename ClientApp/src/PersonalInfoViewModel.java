import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

public class PersonalInfoViewModel implements LocalListener
{
    private StringProperty usernameField;
    private StringProperty ageField;
    private LocalModel model;

    public  PersonalInfoViewModel(LocalModel model)
    {
        this.model = model;
        usernameField = new SimpleStringProperty();
        ageField = new SimpleStringProperty();
        model.addListener(this);
    }

    public void clear()
    {
        usernameField.setValue("");
        ageField.setValue("");
    }

    public StringProperty getUsernameFieldProperty()
    {
        return usernameField;
    }

    public StringProperty getAgeField()
    {
        return ageField;
    }

    @Override
    public void propertyChange(ObserverEvent event)
    {
        if(event.getPropertyName().equals("registerData"))
        {
            String username = String.valueOf(event.getValue2());
            String age = String.valueOf(event.getValue1());
            System.out.println(":::: " + username);
            usernameField.setValue(username);
            ageField.setValue(age);
        }
        else if(event.getPropertyName().equals("logout"))
        {
            clear();
        }
    }
}
