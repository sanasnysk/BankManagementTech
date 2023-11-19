package BMS;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Login extends JFrame {

    JLabel welcome, cardNo, pin;
    JTextField txtCard;
    JPasswordField txtPass;
    JButton signIn, clear, signUp;
    public Login(){
        super("           Bank Management System");

        initLogin();
        actionListener();
        dateTimeFormatter();

        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950,490);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }

    public void initLogin(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/bank_login_6.png"));
        Image i2 = i1.getImage().getScaledInstance(128,128, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel bankImage = new JLabel(i3);
        bankImage.setBounds(400,10,128,128);
        add(bankImage);

        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("image/bank_login_2.png"));
        Image ic2 = ic1.getImage().getScaledInstance(128,128, Image.SCALE_DEFAULT);
        ImageIcon ic3 = new ImageIcon(ic2);

        JLabel cardImage = new JLabel(ic3);
        cardImage.setBounds(730,330,128,128);
        add(cardImage);

        welcome = new JLabel("Welcome To ATM");
        welcome.setBounds(280,140, 450, 40);
        welcome.setFont(new Font("Arial", Font.BOLD,38));
        welcome.setForeground(Color.WHITE);
        add(welcome);

        cardNo = new JLabel("Card No :");
        cardNo.setFont(new Font("Arial", Font.BOLD,28));
        cardNo.setForeground(Color.WHITE);
        cardNo.setBounds(200, 200, 375, 30);
        add(cardNo);

        txtCard = new JTextField(15);
        txtCard.setFont(new Font("Arial", Font.BOLD,24));
        txtCard.setForeground(Color.black);
        txtCard.setBounds(375, 200, 230, 30);
        txtCard.setText("1409963001291511");
        add(txtCard);

        pin = new JLabel("PinChange :");
        pin.setFont(new Font("Arial", Font.BOLD,28));
        pin.setForeground(Color.WHITE);
        pin.setBounds(200, 250, 375, 30);
        add(pin);

        txtPass = new JPasswordField(15);
        txtPass.setFont(new Font("Arial", Font.BOLD,24));
        txtPass.setForeground(Color.black);
        txtPass.setBounds(375, 250, 230, 30);
        txtPass.setText("6900");
        add(txtPass);

        signIn = new JButton("SIGN IN");
        signIn.setFont(new Font("Arial", Font.BOLD,18));
        signIn.setForeground(Color.WHITE);
        signIn.setBackground(Color.black);
        signIn.setBounds(250, 320, 150, 30);
        add(signIn);

        clear = new JButton("Clear");
        clear.setFont(new Font("Arial", Font.BOLD,18));
        clear.setForeground(Color.WHITE);
        clear.setBackground(Color.black);
        clear.setBounds(430, 320, 150, 30);
        add(clear);

        signUp = new JButton("SIGN UP");
        signUp.setFont(new Font("Arial", Font.BOLD,18));
        signUp.setForeground(Color.WHITE);
        signUp.setBackground(Color.black);
        signUp.setBounds(250, 370, 330, 30);
        add(signUp);

        ImageIcon iic1 = new ImageIcon(ClassLoader.getSystemResource("image/bank_login_1.png"));
        Image iic2 = iic1.getImage().getScaledInstance(950,490, Image.SCALE_DEFAULT);
        ImageIcon iic3 = new ImageIcon(iic2);

        JLabel backgroundImage = new JLabel(iic3);
        backgroundImage.setBounds(0,0,950,490);
        add(backgroundImage);
    }

    public void dateTimeFormatter(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        System.out.println(date);
    }

    public void actionListener(){
        signIn.addActionListener(e -> {
            try {
                String card = txtCard.getText();
                String pin = new String( txtPass.getPassword());
                Conn con = new Conn();
                String query = "SELECT * FROM login WHERE card_number = '" + card + "' AND pin = '" + pin + "'";

                ResultSet rs = con.stmt.executeQuery(query);
                if (rs.next()){
                    setVisible(false);
                    BankATM ba = new BankATM(pin);
                    ba.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PinChange");
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }

        });

        clear.addActionListener(e -> {
            txtCard.setText("");
            txtPass.setText("");
        });

        signUp.addActionListener(e -> {
            dispose();
            SignUp1 su = new SignUp1();
            su.setVisible(true);

        });
    }

}
