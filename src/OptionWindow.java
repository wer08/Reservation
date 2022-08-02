import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionWindow
{
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JTextPane textPane1;
    private JButton makeReservationButton;
    private JButton countThePriceButton;
    public JPanel OptionWindow;


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
        countThePriceButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                double price = (Integer)spinner1.getValue() *  ReservationWindow.pricePerTicket+ (Integer) spinner2.getValue() * (0.5*ReservationWindow.pricePerTicket) + (Integer)spinner3.getValue()*1.50;
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
                }
            }
        });
    }
}
