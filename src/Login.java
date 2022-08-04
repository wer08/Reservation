import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Login
{
    private JTextField textField1;
    private JTextField textField2;
    private JButton logInButton;
    private JButton registerButton;
    private JPanel Login;
    private JTextField textField3;
    private JTextField textField4;
    private static JFrame frame;
    private final User user;
    private static boolean flag;


    public Login()
    {
        user = new User();

        List <String> users = user.getUsernames();
        System.out.println(users.size());
        registerButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(users.size());
                String username = textField1.getText();

                String password = textField2.getText();
                String name = textField3.getText();
                String surname = textField4.getText();
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
