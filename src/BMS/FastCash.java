package BMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FastCash extends JFrame implements ActionListener{

    String pin;
    String date;
    JButton btn100, btn500, btn1000, btn2000, btn5000, btn10000, btnBack;
    FastCash(String pin){
        super("APPLICATION FORM");
        this.pin = pin;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
        System.out.println(date);

        initFastCash();

        setLayout(null);
        getContentPane().setBackground(new Color(129, 202, 247));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(170,30);
        setUndecorated(true);
        setSize(1550, 830);
    }

    public void initFastCash(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/Atm4.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel atmImage = new JLabel(i3);
        atmImage.setBounds(0,0,1550,830);
        add(atmImage);

        JLabel label = new JLabel("SELECT WITHDRAWL AMOUNT");
        label.setFont(new Font("System", Font.BOLD, 30));
        label.setForeground(Color.WHITE);
        label.setBounds(385,180,700,35);
        atmImage.add(label);

        btn100 = new JButton("Rs. 100");
        btn100.setBackground(new Color(65,125,128));
        btn100.setForeground(Color.WHITE);
        btn100.setFont(new Font("Tahoma", Font.BOLD,16));
        btn100.setBounds(340,272,200,35);
        btn100.addActionListener(this);
        atmImage.add(btn100);

        btn500 = new JButton("Rs. 500");
        btn500.setBackground(new Color(65,125,128));
        btn500.setForeground(Color.WHITE);
        btn500.setFont(new Font("Tahoma", Font.BOLD,16));
        btn500.setBounds(690,272,200,35);
        btn500.addActionListener(this);
        atmImage.add(btn500);

        btn1000 = new JButton("Rs. 1000");
        btn1000.setBackground(new Color(65,125,128));
        btn1000.setForeground(Color.WHITE);
        btn1000.setFont(new Font("Tahoma", Font.BOLD,16));
        btn1000.setBounds(340,317,200,35);
        btn1000.addActionListener(this);
        atmImage.add(btn1000);

        btn2000 = new JButton("Rs. 2000");
        btn2000.setBackground(new Color(65,125,128));
        btn2000.setForeground(Color.WHITE);
        btn2000.setFont(new Font("Tahoma", Font.BOLD,16));
        btn2000.setBounds(690,317,200,35);
        btn2000.addActionListener(this);
        atmImage.add(btn2000);

        btn5000 = new JButton("Rs. 5000");
        btn5000.setBackground(new Color(65,125,128));
        btn5000.setForeground(Color.WHITE);
        btn5000.setFont(new Font("Tahoma", Font.BOLD,16));
        btn5000.setBounds(340,362,200,35);
        btn5000.addActionListener(this);
        atmImage.add(btn5000);

        btn10000 = new JButton("Rs. 10000");
        btn10000.setBackground(new Color(65,125,128));
        btn10000.setForeground(Color.WHITE);
        btn10000.setFont(new Font("Tahoma", Font.BOLD,16));
        btn10000.setBounds(690,362,200,35);
        btn10000.addActionListener(this);
        atmImage.add(btn10000);

        btnBack = new JButton("BACK");
        btnBack.setBackground(new Color(65,125,128));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Tahoma", Font.BOLD,16));
        btnBack.setBounds(690,407,200,35);
        btnBack.addActionListener(this);
        atmImage.add(btnBack);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack){
            BankATM batm = new BankATM(pin);
            batm.setVisible(true);
            setVisible(false);
        }else {
            String amount = ((JButton) e.getSource()).getText().substring(4);
            String type = "Withdrawl";
            try {
                String query = "SELECT * FROM bank WHERE pin = '" + pin + "'";
                Conn con = new Conn();
                ResultSet rs = con.stmt.executeQuery(query);
                int balance = 0;
                while (rs.next()){
                    if (rs.getString("type").equals("Deposit")){
                        balance += Integer.parseInt(rs.getString("amount"));
                    }else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }String num = "17";

                if (e.getSource() == btnBack || balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null, "Insuffient Balance");
                    return;
                }else {
                    String query2 = "INSERT INTO bank VALUES ('" + pin + "', '" + date + "', '" + type + "', '" + amount + "')";
                    con.stmt.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null, "Rs." + amount + " Debited Successfully");
                }

                setVisible(false);
                BankATM batm = new BankATM(pin);
                batm.setVisible(true);

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }


    public void actionListener(){

        btn100.addActionListener(e -> {
            String amount = btn100.getText().substring(4);
            String type = "Withdrawl";
            try {
                String query = "SELECT * FROM bank WHERE pin = '" + pin + "'";
                Conn con = new Conn();
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

            }catch (Exception ex){
                ex.printStackTrace();
            }

        });

        btn500.addActionListener(e -> {
            String amount = ((JButton) e.getSource()).getText().substring(4);
            String type = "Withdrawl";

            try {
                String query = "SELECT * FROM bank WHERE pin = '" + pin + "'";
                Conn con = new Conn();
                ResultSet rs = con.stmt.executeQuery(query);
                int balance = 0;
                while (rs.next()){
                    if (rs.getString("type").equals("Deposit")){
                        balance += Integer.parseInt(rs.getString("amount"));
                    }else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }String num = "17";

                if (e.getSource() == btnBack || balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null, "Insuffient Balance");
                    return;
                }else {
                    String query2 = "INSERT INTO bank VALUES ('" + pin + "', '" + date + "', '" + type + "', '" + amount + "')";
                    con.stmt.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null, "Rs." + amount + " Debited Successfully");
                }

                setVisible(false);
                BankATM batm = new BankATM(pin);
                batm.setVisible(true);

            }catch (Exception ex){
                ex.printStackTrace();
            }

        });

        btn1000.addActionListener(e -> {

        });

        btn2000.addActionListener(e -> {

        });

        btn5000.addActionListener(e -> {

        });

        btn10000.addActionListener(e -> {

        });

        btnBack.addActionListener(e -> {
            BankATM batm = new BankATM(pin);
            batm.setVisible(true);
            setVisible(false);
        });
    }

}
