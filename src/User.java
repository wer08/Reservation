import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User
{
    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
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

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
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
                    System.out.println("correct");
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
