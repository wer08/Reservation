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

    private List<String> allAirports = new ArrayList<>();





    private JButton startReservationButton;
    private ButtonGroup jRadioButtonGroup = new ButtonGroup();
    public JPanel ReservationWindow;
    private JRadioButton businessClassRadioButton;
    private JRadioButton economyClassRadioButton;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    DefaultComboBoxModel<String> defaultComboBoxModelDomestic;
    DefaultComboBoxModel<String> defaultComboBoxModelInternational;
    DefaultComboBoxModel<String> defaultComboBoxModel;



    public ReservationWindow()
    {
        jRadioButtonGroup.add(businessClassRadioButton);
        jRadioButtonGroup.add(economyClassRadioButton);


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
                JFrame frame = new JFrame("OptionWindow");
                frame.setContentPane(new OptionWindow().OptionWindow);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        for (String string:domesticAirports)
        {
            comboBox2.addItem(string);
        }
        comboBox1.addItem("Show All");
       comboBox1.addItem("Domestic Flights");
        comboBox1.addItem("International Flights");
        for (String string : internationalAirports)
        {
            System.out.println(string);
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
        businessClassRadioButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pricePerTicket = pricePerTicket*2;
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("ReservationWindow");
        frame.setContentPane(new ReservationWindow().ReservationWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents()
    {
        // TODO: place custom component creation code here
    }
}
