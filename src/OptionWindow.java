import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;

public class OptionWindow
{
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JButton makeReservationButton;
    private JButton countThePriceButton;
    public JPanel OptionWindow;
    private JRadioButton economyClassRadioButton;
    private JRadioButton businessClassRadioButton;
    private JLabel PRICE;
    private SpinnerNumberModel spinnerNumberModel1;
    private SpinnerNumberModel spinnerNumberModel2;
    private SpinnerNumberModel spinnerNumberModel3;
    private ButtonGroup jRadioButtonGroup = new ButtonGroup();
    private double price;
    private boolean isBusiness;

    public JButton getMakeReservationButton()
    {
        return makeReservationButton;
    }

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
        spinnerNumberModel1 = new SpinnerNumberModel(0,0,null,1);
        spinnerNumberModel2 = new SpinnerNumberModel(0,0,null,1);
        spinnerNumberModel3 = new SpinnerNumberModel(0,0,null,1);
        spinner1.setModel(spinnerNumberModel1);
        spinner2.setModel(spinnerNumberModel2);
        spinner3.setModel(spinnerNumberModel3);

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
                        PRICE.setText(String.valueOf(price));

            }
        });
        makeReservationButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!(spinner1.getValue().equals(0))||!(spinner2.getValue().equals(0)))
                {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Confirm reservation", JOptionPane.YES_NO_OPTION);
                    if (answer == 0)
                    {
                        Document doc = new Document();
                        try
                        {
//generate a PDF at the specified location
                            PdfWriter writer = PdfWriter.getInstance(doc,new FileOutputStream("C:\\Users\\wojci\\OneDrive\\Pulpit\\Nowy folder\\proba2.pdf"));
                            System.out.println("PDF created.");
//opens the PDF
                            doc.open();
//adds paragraph to the PDF file
                            doc.add(new Paragraph(FlightChoice.flight.getStartingAirport() + " " + FlightChoice.flight.getTimeOfDeparture() + "------>"+FlightChoice.flight.getLandingAirport()+" "+FlightChoice.flight.getTimeOfArrival()));
//close the PDF file
                            doc.close();
//closes the writer
                            writer.close();
                        }
                        catch (DocumentException exception)
                        {
                            exception.printStackTrace();
                        }
                        catch (FileNotFoundException exception)
                        {
                            exception.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "SUCCESFUL RESERVATION");
                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"You have to choose at leat one ticket");
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
