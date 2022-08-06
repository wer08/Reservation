import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Login
{
    private JTextField textField1;
    private JTextField textField2;
    private JButton mainButton;
    private JButton registerButton;
    private JPanel Login;
    private JTextField textField3;
    private JTextField textField4;
    private static JFrame frame;
    private final User user;
    private boolean flag;
    private boolean flagUser;
    private boolean flagPassword;
    private String username;
    private String password;
    private String name;
    private String surname;
    private ActionListener actionListener;




    public Login()
    {
        user = new User();
        frame.getRootPane().setDefaultButton(mainButton);


        List <String> users = user.getUsernames();
        System.out.println(users.size());
        registerButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(users.size());
                username = textField1.getText();
                password = textField2.getText();
                name = textField3.getText();
                surname = textField4.getText();
                flag = true;
                for (String string: users)
                {
                    if(username.equals(string))
                    {
                        JOptionPane.showMessageDialog(null,"There is user with this username, pick another");
                        flag = false;
                    }
                }
                if (name.equals("")||surname.equals("")||password.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"All positions must be filled");
                    flag=false;
                }
                if(flag)
                {
                    user.addUser(name,surname,username,password );
                    JFrame frame2 = new JFrame("ReservationWindow");
                    frame2.setContentPane(new ReservationWindow().ReservationWindow);
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame2.pack();
                    frame2.setVisible(true);
                    frame.dispose();
                }
            }
        });

        mainButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                flagPassword = false;
                flagUser = false;
                username = textField1.getText();
                password = textField2.getText();
                name = textField3.getText();
                surname = textField4.getText();
                for (String string:users)
                {
                    if(username.equals(string))
                    {
                        flagUser =true;
                        System.out.println("User found");
                    }
                }

                if(user.checkPassword(username,password))
                {
                    flagPassword = true;
                    System.out.println("password correct");
                }
                if(!flagPassword)
                {
                    JOptionPane.showMessageDialog(null,"Wrong username or password");
                }
                else
                {
                    JFrame frame2 = new JFrame("ReservationWindow");
                    ReservationWindow reservationWindow = new ReservationWindow();
                    frame2.setContentPane(reservationWindow.ReservationWindow);
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame2.pack();
                    frame2.setVisible(true);
                    frame2.getRootPane().setDefaultButton(reservationWindow.getReservationButton());
                    frame.dispose();

                }



            }
        });
        mainButton.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.equals(KeyEvent.VK_ENTER))
                {

                }
            }
        });
    }

    public static void main(String[] args)
    {
        frame = new JFrame("Login");
        frame.setContentPane(new Login().Login);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

}
