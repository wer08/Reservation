import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightChoice
{
    public JPanel FlightChoice;
    private JButton acceptButton;
    private JComboBox comboBox1;
    private int day;
    private int month;
    private int year;
    private int idclients;
    private int count;
    private List<Integer> iddepartures;

    private int idAirport;

    public FlightChoice()
    {
        day = ReservationWindow.dayInt;
        month = ReservationWindow.monthInt;
        year = ReservationWindow.yearInt;
        iddepartures = new ArrayList<>();


        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplanes", "root", null);
            Statement statement = connection.createStatement();
            ResultSet resultSetID = statement.executeQuery("select * from departures,airplane,airports where airports.name ='"+ReservationWindow.airportNameDeparture+"' AND departures.day ='"+day+"' AND departures.month = '"+month+"' AND departures.year = '"+year+"' AND departures.idairplane = airplane.idairplane AND departures.idairport = airports.idairports ");
            while(resultSetID.next())
            {
                idAirport = resultSetID.getInt("departures.idairport");
                System.out.println("select * from departures,airplane,airports,arrivals where departures.idairports ='" + idAirport + "' AND departures.day ='" + day + "' AND departures.month = '" + month + "' AND departures.year = '" + year + "' AND departures.idairplane = airplane.idairplane AND departures.idairport = airports.idairports AND arrivals.idairplane = airplane.idairplane ");
            }
                ResultSet resultSet = statement.executeQuery("select * from departures,airplane,airports,arrivals where departures.idairports ='" + idAirport + "' AND departures.day ='" + day + "' AND departures.month = '" + month + "' AND departures.year = '" + year + "' AND departures.idairplane = airplane.idairplane AND departures.idairport = airports.idairports AND arrivals.idairplane = airplane.idairplane ");
                while (resultSet.next())
                {

                    comboBox1.addItem(resultSet.getString("departures.hour")+":"+resultSet.getString("departures.minute") + " " + resultSet.getString("airplane.name"));
                }
        }
        catch (SQLException e)
        {
            e.getSQLState();
        }

        comboBox1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String choice = String.valueOf(comboBox1.getSelectedItem());
            }
        });
        acceptButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFrame frame2 = new JFrame("Option Window");
                frame2.setContentPane(new OptionWindow().OptionWindow);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.pack();
                frame2.setVisible(true);
                JFrame f3 = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, FlightChoice);
                f3.dispose();
            }
        });
    }
}
