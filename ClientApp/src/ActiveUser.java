public class ActiveUser
{
    private User user;

    public ActiveUser()
    {

    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void  logout()
    {
        user.setLogged(false);
        setUser(null);
    }

    public User getUser()
    {
        return user;
    }
}
