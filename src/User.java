import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User
{
    private String name;
    private String surname;
    List<String> usernames = new ArrayList<>();

    public List<String> getUsernames()
    {
        return usernames;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    private int adultTickets;
    private int infantTickets;
    private int bags;

    public int getAdultTickets()
    {
        return adultTickets;
    }

    public void setAdultTickets(int adultTickets)
    {
        this.adultTickets = adultTickets;
    }

    public int getInfantTickets()
    {
        return infantTickets;
    }

    public void setInfantTickets(int infantTickets)
    {
        this.infantTickets = infantTickets;
    }

    public int getBags()
    {
        return bags;
    }

    public void setBags(int bags)
    {
        this.bags = bags;
    }

    public User()
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplanes", "root", null);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from clients");
            while(resultSet.next())
            {
                usernames.add(resultSet.getString("username"));
            }

        }
        catch (SQLException e)
        {
            e.getSQLState();
        }
    }
    public void setData(String username)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplanes", "root", null);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from clients where username = '"+username+"'");
            System.out.println("select * from clients where username = '"+username+"'");
            while(resultSet.next())
            {
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
            }

        }
        catch (SQLException e)
        {
            e.getSQLState();
        }
    }
    public boolean checkPassword(String username, String password)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplanes", "root", null);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from clients where username ='"+username+"'");
            while(resultSet.next())
            {
                if(resultSet.getString("password").equals(password))
                {
                    return true;
                }
            }

        }
        catch (SQLException e)
        {
            e.getSQLState();
        }
        return false;
    }

    public void addUser(String name, String surname, String username, String password)
    {

        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplanes", "root", null);
            PreparedStatement statement = connection.prepareStatement("Insert into clients (idclients,name,surname,username,password)Values (?,?,?,?,?)");
            statement.setString(1,null);
            statement.setString(2,name);
            statement.setString(3,surname);
            statement.setString(4,username);
            statement.setString(5,password);
            statement.execute();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
