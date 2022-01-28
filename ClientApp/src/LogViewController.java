import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;


public class LogViewController
{
    private Region root;
    private ViewHandler  viewHandler;
    private LogViewModel logViewModel;

    @FXML
    TextField usernameThing;
    @FXML
    TextField passwordThing;
    @FXML
    TextField ageThing;
    @FXML
    Label errorLabel;

    public void init(ViewHandler viewHandler,LogViewModel logViewModel, Region root)
    {
        this.viewHandler = viewHandler;
        this.root = root;
        this.logViewModel = logViewModel;
        errorLabel.textProperty().bind(logViewModel.getError());
    }

    public Region getRoot()
    {
        return root;
    }

    public void reset()
    {

    }

    @FXML
    public void register()
    {
        String username = usernameThing.getText();
        String password = passwordThing.getText();
        int age = Integer.parseInt(ageThing.getText());

        System.out.println(username);

        if(username != null && password != null)
        {
            logViewModel.register(username, password, age);
        }
        else
        {
           errorLabel.setText("Username, password and age must be filled");
        }
    }

    @FXML
    public void log()
    {
        String username = usernameThing.getText();
        String password = passwordThing.getText();

        if(username != null && password != null)
        {
            logViewModel.log(username, password);
        }
        else
        {
            errorLabel.setText("Username and password must be filled");
        }
    }

    @FXML
    public void logout()
    {
        usernameThing.setText("");
        passwordThing.setText("");
        ageThing.setText("");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User logged out");
        alert.setHeaderText("The user  was logged out");
        alert.show();
        logViewModel.logout();
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
    public void openMainView()
    {
        viewHandler.openView("main");
    }
}
