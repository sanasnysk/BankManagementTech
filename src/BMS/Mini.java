package BMS;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Mini extends JFrame {

    String pin;
    JButton btnExit;
    Mini(String pin){
        super("APPLICATION FORM");
        this.pin = pin;

        initMini();
        actionListener();

        setLayout(null);
        getContentPane().setBackground(new Color(255, 204, 204));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 50);
        setUndecorated(true);
        setSize(500, 800);
    }

    public void initMini(){
        JLabel label2 = new JLabel("San Project coder");
        label2.setFont(new Font("Arial", Font.BOLD, 18));
        label2.setBounds(150, 20, 200, 20);
        add(label2);

        JLabel label3 = new JLabel();
        label3.setFont(new Font("Arial", Font.BOLD, 16));
        label3.setBounds(20, 80, 300, 20);
        add(label3);

        JLabel label1 = new JLabel();
        label1.setFont(new Font("Arial", Font.BOLD, 12));
        label1.setBounds(20, 100, 500, 600);
        add(label1);

        JLabel label4 = new JLabel();
        label4.setFont(new Font("Arial", Font.BOLD, 16));
        label4.setBounds(20, 700, 300, 20);
        add(label4);

        try {
            String query = "SELECT * FROM login WHERE pin = '" + pin + "'";
            Conn con = new Conn();
            ResultSet rs = con.stmt.executeQuery(query);
            while (rs.next()){
                String cardNo = rs.getString("card_number").substring(0,4) + "XXXXXXXX" + rs.getString("card_number").substring(12);
                label3.setText(cardNo);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            String query = "SELECT * FROM bank WHERE pin = '" + pin + "' ORDER BY date DESC LIMIT 15 ";
            Conn con = new Conn();
            ResultSet rs = con.stmt.executeQuery(query);

            int balance = 0;
            while (rs.next()){
                label1.setText(label1.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                        rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");

                if (rs.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(rs.getString("amount"));
                }else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            label4.setText("Your Total Balance is Rs " + balance);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        btnExit = new JButton("Exit");
        btnExit.setBounds(350, 750, 100, 25);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Tahoma", Font.BOLD,16));
        add(btnExit);
    }

    public void actionListener(){
        btnExit.addActionListener(e -> {
            BankATM batm = new BankATM(pin);
            batm.setVisible(true);
            setVisible(false);
        });
    }

}
