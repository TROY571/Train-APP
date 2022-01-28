public class ViewModelFactory
{
    private LogViewModel logViewModel;
    private PersonalInfoViewModel personalInfoViewModel;
    private TicketViewModel ticketViewModel;
    private MainViewModel mainViewModel;

    public ViewModelFactory(LogViewModel logViewModel, PersonalInfoViewModel personalInfoViewModel, TicketViewModel ticketViewModel, MainViewModel mainViewModel)
    {
        this.logViewModel = logViewModel;
        this.personalInfoViewModel = personalInfoViewModel;
        this.ticketViewModel = ticketViewModel;
        this.mainViewModel = mainViewModel;
    }

    public MainViewModel getMainViewModel()
    {
        return mainViewModel;
    }

    public TicketViewModel getTicketViewModel()
    {
        return ticketViewModel;
    }

    public LogViewModel getLogViewModel()
    {
        return logViewModel;
    }

    public PersonalInfoViewModel getPersonalInfoViewModel()
    {
        return personalInfoViewModel;
    }
}
