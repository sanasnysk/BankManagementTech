package BMS;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Withdrawl extends JFrame {

    String pin;
    String date;
    JTextField textField;
    JButton btnWithdrawl, btnBack;
    JLabel lblBalance;
    Withdrawl(String pin){
        super("APPLICATION FORM");
        this.pin = pin;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
        System.out.println(date);

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

        JLabel label1 = new JLabel("MAXIMUM WITHDRAWL IS RS. 10,000"); // 최대 인출 금액은 RS입니다. 10,000
        label1.setFont(new Font("System", Font.BOLD, 24));
        label1.setForeground(Color.WHITE);
        label1.setBounds(400,160,700,35);
        atmImage.add(label1);

        JLabel label2 = new JLabel("PLEASE ENTER YOUR AMOUNT"); // 금액을 입력하세요.
        label2.setFont(new Font("System", Font.BOLD, 18));
        label2.setForeground(Color.WHITE);
        label2.setBounds(400,200,300,35);
        atmImage.add(label2);

        lblBalance = new JLabel();
        lblBalance.setFont(new Font("System", Font.BOLD, 16));
        lblBalance.setForeground(Color.WHITE);
        lblBalance.setBounds(750,200,200,35);
        atmImage.add(lblBalance);

        textField = new JTextField();
        textField.setBackground(new Color(65,128,125));
        textField.setForeground(Color.WHITE);
        textField.setBounds(400,240,320,25);
        textField.setFont(new Font("Tahoma", Font.BOLD,22));
        atmImage.add(textField);

        btnWithdrawl = new JButton("WITHDRAWL");
        btnWithdrawl.setBounds(700,375,150,35);
        btnWithdrawl.setBackground(new Color(65,125,128));
        btnWithdrawl.setForeground(Color.WHITE);
        btnWithdrawl.setFont(new Font("Tahoma", Font.BOLD,16));
        atmImage.add(btnWithdrawl);

        btnBack = new JButton("BACK");
        btnBack.setBounds(700,420,150,35);
        btnBack.setBackground(new Color(65,125,128));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Tahoma", Font.BOLD,16));
        atmImage.add(btnBack);

    }

    public void actionListener(){
        btnWithdrawl.addActionListener(e -> {
            try {
                String amount = textField.getText();
                String type = "Withdrawl";
                if (textField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Withdrawl");
                }else {
                    Conn con = new Conn();

                    String query = "SELECT * FROM bank WHERE pin = '" + pin + "'";

                    ResultSet rs = con.stmt.executeQuery(query);
                    int balance = 0;
                    while (rs.next()){
                        if (rs.getString("type").equals("Deposit")){
                            balance += Integer.parseInt(rs.getString("amount"));
                        }else {
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }

                    if (balance < Integer.parseInt(amount)){
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }else {
                        String query2 = "INSERT INTO bank VALUES ('" + pin + "', '" + date + "', '" + type + "', '" + amount + "')";
                        con.stmt.executeUpdate(query2);

                        JOptionPane.showMessageDialog(null, "Rs." + amount + " Withdrawl Successfully");
                    }

                    setVisible(false);
                    BankATM batm = new BankATM(pin);
                    batm.setVisible(true);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        btnBack.addActionListener(e -> {
            BankATM batm = new BankATM(pin);
            batm.setVisible(true);
            setVisible(false);
        });
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

        lblBalance.setText("BALANCE : " + balance);
    }

}
