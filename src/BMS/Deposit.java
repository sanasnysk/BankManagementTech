package BMS;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deposit extends JFrame {

    String pin;
    String date;
    JTextField textField;
    JButton btnDeposit, btnBack;
    Deposit(String pin){
        super("APPLICATION FORM");
        this.pin = pin;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
        System.out.println(date);

        initDeposit();
        actionListener();

        setLayout(null);
        getContentPane().setBackground(new Color(129, 202, 247));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(170,30);
        setUndecorated(true);
        setSize(1550, 830);
    }

    public void initDeposit(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/Atm4.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,850, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel atmImage = new JLabel(i3);
        atmImage.setBounds(0,0,1550,850);
        add(atmImage);

        JLabel label1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setForeground(Color.WHITE);
        label1.setBounds(460,180,400,35);
        atmImage.add(label1);

        textField = new JTextField();
        textField.setBackground(new Color(65,128,125));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,230,320,25);
        textField.setFont(new Font("Tahoma", Font.BOLD,22));
        atmImage.add(textField);

        btnDeposit = new JButton("DEPOSIT");
        btnDeposit.setBounds(700,375,150,35);
        btnDeposit.setBackground(new Color(65,125,128));
        btnDeposit.setForeground(Color.WHITE);
        btnDeposit.setFont(new Font("Tahoma", Font.BOLD,16));
        atmImage.add(btnDeposit);

        btnBack = new JButton("BACK");
        btnBack.setBounds(700,420,150,35);
        btnBack.setBackground(new Color(65,125,128));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Tahoma", Font.BOLD,16));
        atmImage.add(btnBack);

    }

    public void actionListener(){
        btnDeposit.addActionListener(e -> {
            try {
                String amount = textField.getText();
                String type = "Deposit";
                if (textField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
                }else {
                    Conn con = new Conn();

                    String query = "INSERT INTO bank VALUES ('" + pin + "', '" + date + "', '" + type + "', '" + amount + "')";

                    con.stmt.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "Rs." + amount + " Deposited Successfully");

                    setVisible(false);
                    BankATM batm = new BankATM(pin);
                    batm.setVisible(true);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        btnBack.addActionListener(e -> {
            setVisible(false);
            BankATM batm = new BankATM(pin);
            batm.setVisible(true);
        });
    }

}
