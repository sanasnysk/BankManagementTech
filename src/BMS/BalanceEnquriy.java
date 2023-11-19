package BMS;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class BalanceEnquriy extends JFrame{

    String pin;
    JTextField textField;
    JButton btnBack;
    JLabel lblBalance;
    BalanceEnquriy(String pin){
        super("APPLICATION FORM");
        this.pin = pin;

        initWithdrawl();
        actionListener();
        setBalance();

        setLayout(null);
        getContentPane().setBackground(new Color(129, 202, 247));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(170,30);
        setUndecorated(true);
        setSize(1550, 830);
    }

    public void initWithdrawl(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/Atm4.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,850, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel atmImage = new JLabel(i3);
        atmImage.setBounds(0,0,1550,850);
        add(atmImage);

        JLabel label1 = new JLabel("Your Current Balance is Rs "); // 귀하의 현재 잔액은 Rs입니다.
        label1.setFont(new Font("System", Font.BOLD, 24));
        label1.setForeground(Color.WHITE);
        label1.setBounds(400,180,700,35);
        atmImage.add(label1);

        lblBalance = new JLabel();
        lblBalance.setFont(new Font("System", Font.BOLD, 20));
        lblBalance.setForeground(Color.WHITE);
        lblBalance.setBounds(400,220,400,35);
        atmImage.add(lblBalance);

        btnBack = new JButton("BACK");
        btnBack.setBounds(700,420,150,35);
        btnBack.setBackground(new Color(65,125,128));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Tahoma", Font.BOLD,16));
        atmImage.add(btnBack);

    }

    public void setBalance(){
        int balance = 0;
        try {
            Conn con = new Conn();
            ResultSet rs = con.stmt.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            while (rs.next()){
                if (rs.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(rs.getString("amount"));
                }else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        lblBalance.setText("" + balance);
    }

    public void actionListener(){
        btnBack.addActionListener(e -> {
            BankATM batm = new BankATM(pin);
            batm.setVisible(true);
            setVisible(false);
        });
    }

}
