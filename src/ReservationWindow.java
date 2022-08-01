import javax.swing.*;
import java.awt.event.*;

public class ReservationWindow
{
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;

    private JButton startReservationButton;
    private JPanel ReservationWindow;

    public ReservationWindow()
    {
        startReservationButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });
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
