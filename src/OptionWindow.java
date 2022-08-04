import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class OptionWindow
{
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JTextPane textPane1;
    private JButton makeReservationButton;
    private JButton countThePriceButton;
    public JPanel OptionWindow;
    private JRadioButton economyClassRadioButton;
    private JRadioButton businessClassRadioButton;
    private ButtonGroup jRadioButtonGroup = new ButtonGroup();
    private double price;
    private boolean isBusiness;


    public static void main(String[] args)
    {
        JFrame frame = new JFrame("OptionWindow");
        frame.setContentPane(new OptionWindow().OptionWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public OptionWindow()
    {
        jRadioButtonGroup.add(businessClassRadioButton);
        jRadioButtonGroup.add(economyClassRadioButton);
        /*Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplanes", "root", null);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from clients,departures,arrivals where id");
*/
        countThePriceButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!isBusiness)
                {
                    price = (Integer)spinner1.getValue() *  ReservationWindow.pricePerTicket+ (Integer) spinner2.getValue() * (0.5*ReservationWindow.pricePerTicket) + (Integer)spinner3.getValue()*1.50;
                }
                else
                {
                    price = 2 * ((Integer)spinner1.getValue() *  ReservationWindow.pricePerTicket+ (Integer) spinner2.getValue() * (0.5*ReservationWindow.pricePerTicket) + (Integer)spinner3.getValue()*1.50);
                }
                System.out.println(price);
                        textPane1.setText(""+price);
            }
        });
        makeReservationButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int answer = JOptionPane.showConfirmDialog(null,"Are you sure ?", "Confirm reservation",JOptionPane.YES_NO_OPTION);
                if(answer == 0)
                {
                    JOptionPane.showMessageDialog(null,"SUCCESFUL RESERVATION");
                   /* try
                    {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplanes", "root", null);
                        PreparedStatement statement = connection.prepareStatement("Insert into flights (idflights,idclients,iddepartures,idarrivals,price)Values (?,?,?,?,?)");
                        statement.setString(1,null);
                        statement.setString(2,name);
                        statement.setString(3,surname);
                        statement.setString(4,username);
                        statement.setString(5,password);
                        statement.execute();

                    }
                    catch (SQLException exception)
                    {
                        exception.getSQLState();
                    }*/
                }
            }
        });
        businessClassRadioButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                isBusiness = true;
            }
        });
        economyClassRadioButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                isBusiness = false;
            }
        });
    }
}
