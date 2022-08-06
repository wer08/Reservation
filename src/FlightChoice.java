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

    public JButton getAcceptButton()
    {
        return acceptButton;
    }

    private List<Integer> iddepartures;
    private List<Integer> idflights;
    private String sql;
    private String sql2;

    public FlightChoice()
    {
        day = ReservationWindow.dayInt;
        month = ReservationWindow.monthInt;
        year = ReservationWindow.yearInt;
        iddepartures = new ArrayList<>();
        idflights = new ArrayList<>();




        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplanes", "root", null);
            Statement statement = connection.createStatement();
            ResultSet resultSetIDdep = statement.executeQuery("select * from departures,airplane,airports where airports.name ='"+ReservationWindow.airportNameDeparture+"' AND departures.day ='"+day+"' AND departures.month = '"+month+"' AND departures.year = '"+year+"' AND departures.idairplane = airplane.idairplane AND departures.idairport = airports.idairports ");
            while(resultSetIDdep.next())
            {
                iddepartures.add(resultSetIDdep.getInt("departures.iddepartures"));
            }
            for (Integer integer:iddepartures)
            {
                sql ="select * from arrivals,flights,airports where airports.name ='"+ReservationWindow.airportNameArrival+"' AND flights.iddeparture = '"+integer+"' AND  arrivals.idairportarrival = airports.idairports AND arrivals.idarrivals = flights.idarrival";
                //System.out.println(sql);
                ResultSet resultSetIDarr = statement.executeQuery(sql);

                while (resultSetIDarr.next())
                {
                    idflights.add(resultSetIDarr.getInt("flights.idflights"));
                }
            }
                for (Integer integer1:idflights)
                {
                    sql2 ="select * from departures,flights,arrivals where flights.idflights = "+integer1+" AND flights.iddeparture = departures.iddepartures AND flights.idarrival = arrivals.idarrivals";
                    ResultSet resultSet = statement.executeQuery(sql2);
                    while (resultSet.next())
                    {
                        comboBox1.addItem("Departure at: "+resultSet.getString("departures.hour") + ":" + resultSet.getString("departures.minute") + " arrival at: " + resultSet.getString("arrivals.hour")+":"+resultSet.getString("arrivals.minute"));
                    }
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
                OptionWindow optionWindow = new OptionWindow();
                frame2.setContentPane(optionWindow.OptionWindow);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.pack();
                frame2.setVisible(true);
                frame2.getRootPane().setDefaultButton(optionWindow.getMakeReservationButton());
                JFrame f3 = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, FlightChoice);
                f3.dispose();
            }
        });
    }
}
