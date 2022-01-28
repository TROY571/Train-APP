import javafx.application.Application;
import javafx.stage.Stage;

public class MyApp extends Application
{
    private ModelManager modelManager;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        modelManager = new ModelManager();
        LogViewModel logViewModel = new LogViewModel(modelManager);
        PersonalInfoViewModel personalInfoViewModel = new PersonalInfoViewModel(modelManager);
        TicketViewModel ticketViewModel = new TicketViewModel(modelManager);
        MainViewModel mainViewModel = new MainViewModel(modelManager);
        ViewModelFactory viewModelFactory = new ViewModelFactory(logViewModel, personalInfoViewModel, ticketViewModel, mainViewModel);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);
    }

    @Override
    public void stop() throws Exception
    {
        if(modelManager.getActiveUser() != null)
        {
            modelManager.logout();
        }
        super.stop();
    }
}
