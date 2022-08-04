import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationWindow
{
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    public static double pricePerTicket = 0;
    private List<String> domesticAirports = new ArrayList<>();
    private List<String> internationalAirports = new ArrayList<>();


    private JButton startReservationButton;
    public JPanel ReservationWindow;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    public static String airportNameDeparture;
    public static String airportNameArrival;
    public static int dayInt;
    public static int monthInt;
    public static int yearInt;


    private DefaultComboBoxModel<String> defaultComboBoxModelDomestic;
    private DefaultComboBoxModel<String> defaultComboBoxModelInternational;
    private DefaultComboBoxModel<String> defaultComboBoxModel;
    private SpinnerNumberModel day= new SpinnerNumberModel(1,1,31,1);
    private SpinnerNumberModel month= new SpinnerNumberModel(1,1,12,1);
    private SpinnerNumberModel year= new SpinnerNumberModel(2022,2022,2025,1);





    public ReservationWindow()
    {
        spinner1.setModel(day);
        spinner2.setModel(month);
        spinner3.setModel(year);


        List<String> allAirports = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplanes", "root", null);
            Statement statement = connection.createStatement();
            ResultSet resultSetDomestic = statement.executeQuery("select * from airports where country = 'Poland'");
            while (resultSetDomestic.next())
            {
                domesticAirports.add(resultSetDomestic.getString("name"));
            }
            ResultSet resultSetInternational = statement.executeQuery("select * from airports where country != 'Poland'");
            while (resultSetInternational.next())
            {
                internationalAirports.add(resultSetInternational.getString("name"));
            }
            ResultSet resultSet = statement.executeQuery("select * from airports ");
            while (resultSet.next())
            {
                allAirports.add(resultSet.getString("name"));
            }

        }
        catch (SQLException e)
        {
            e.getSQLState();
        }


        startReservationButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dayInt = (Integer)spinner1.getValue();
                monthInt = (Integer)spinner2.getValue();
                yearInt = (Integer)spinner3.getValue();
                if(comboBox3.getSelectedItem().equals("choose airport")||comboBox2.getSelectedItem().equals("choose airport"))
                {
                    JOptionPane.showMessageDialog(null,"You have to choose airports");
                }
                else
                {
                    JFrame frame2 = new JFrame("FlightChoice");
                    frame2.setContentPane(new FlightChoice().FlightChoice);
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame2.pack();
                    frame2.setVisible(true);
                    JFrame f3 = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, ReservationWindow);
                    f3.dispose();
                }
            }
        });
        comboBox2.addItem("choose airport");
        for (String string:domesticAirports)
        {
            comboBox2.addItem(string);
        }
        comboBox1.addItem("Show All");
       comboBox1.addItem("Domestic Flights");
        comboBox1.addItem("International Flights");
        comboBox3.addItem("choose airport");
        for (String string : internationalAirports)
        {
            comboBox3.addItem(string);
        }
        for (String string:domesticAirports)
        {
        comboBox3.addItem(string);
        }
                String[] domesticAirportsArray = domesticAirports.toArray(new String[0]);
                String[] internationalAirportsArray = internationalAirports.toArray(new String[0]);
                String[] allAirportsArray = allAirports.toArray(new String[0]);
                defaultComboBoxModelDomestic = new DefaultComboBoxModel<>(domesticAirportsArray);
                defaultComboBoxModelInternational = new DefaultComboBoxModel<>(internationalAirportsArray);
                defaultComboBoxModel = new DefaultComboBoxModel<>(allAirportsArray);
        comboBox1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(comboBox1.getSelectedItem().equals("Domestic Flights"))
                {

                    comboBox3.setModel(defaultComboBoxModelDomestic);
                }
                else if(comboBox1.getSelectedItem().equals("International Flights"))
                {

                    comboBox3.setModel(defaultComboBoxModelInternational);
                }
                else
                {
                    comboBox3.setModel(defaultComboBoxModel);
                }
            }
        });
        comboBox3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String tmp = String.valueOf(comboBox3.getSelectedItem());
                airportNameArrival = tmp;
                switch (tmp)
                {
                    case "Paris":
                    {
                        pricePerTicket = 100.0;
                        break;
                    }case "Bangok":
                    {
                        pricePerTicket = 900.0;
                        break;
                    }case "Madrid":
                    {
                        pricePerTicket = 60.0;
                        break;
                    }case "Berlin":
                    {
                        pricePerTicket = 25.0;
                        break;
                    }case "Monachium":
                    {
                        pricePerTicket = 30.0;
                        break;
                    }case "Lisbon":
                    {
                        pricePerTicket = 80.0;
                        break;
                    }case "Washington":
                    {
                        pricePerTicket = 600.0;
                        break;
                    }case "Moscow":
                    {
                        pricePerTicket = 120.0;
                        break;
                    }case "Beijing":
                    {
                        pricePerTicket = 1000.0;
                        break;
                    }case "London":
                    {
                        pricePerTicket = 50.0;
                        break;
                    }

                }
            }
        });
        comboBox2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                airportNameDeparture = String.valueOf(comboBox2.getSelectedItem());
            }
        });
    }

   /* public static void main(String[] args)
    {
        frame = new JFrame("ReservationWindow");
        frame.setContentPane(new ReservationWindow().ReservationWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }*/

    private void createUIComponents()
    {
        // TODO: place custom component creation code here
    }
}
