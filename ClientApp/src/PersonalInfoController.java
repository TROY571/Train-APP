import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class PersonalInfoController
{

    private Region root;
    private ViewHandler  viewHandler;
    private PersonalInfoViewModel personalInfoViewModel;

    @FXML
    private Label userLabel;

    @FXML
    private Label ageLabel;

    public void init(ViewHandler viewHandler, PersonalInfoViewModel personalInfoViewModel, Region root)
    {
        this.viewHandler = viewHandler;
        this.root = root;
        this.personalInfoViewModel = personalInfoViewModel;
        userLabel.textProperty().bind(personalInfoViewModel.getUsernameFieldProperty());
        ageLabel.textProperty().bind(personalInfoViewModel.getAgeField());
    }

    public Region getRoot()
    {
        return root;
    }

    public void reset()
    {

    }

    @FXML
    public void openMainView()
    {
        viewHandler.openView("main");
    }

    @FXML
    public void openTicketView()
    {
        viewHandler.openView("ticket");
    }

    @FXML
    public void openLogView()
    {
        viewHandler.openView("log");
    }
}

