package BMS;

import javax.swing.*;
import java.awt.*;

public class BankATM extends JFrame {

    String pin;
    JButton btnDeposit, btnCash, btnFast, btnMini, btnPin, btnBalance, btnExit;
    BankATM(String pin){
        super("APPLICATION FORM");
        this.pin = pin;

        initBankATM();
        actionListener();

        setLayout(null);
        getContentPane().setBackground(new Color(129, 202, 247));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(170,30);
        setUndecorated(true);
        setSize(1550, 830);
    }

    public void initBankATM(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/Atm4.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel atmImage = new JLabel(i3);
        atmImage.setBounds(0,0,1550,830);
        add(atmImage);

        JLabel label = new JLabel("Please Select  Your Transaction");
        label.setFont(new Font("System", Font.BOLD, 30));
        label.setForeground(Color.WHITE);
        label.setBounds(385,180,700,35);
        atmImage.add(label);

        btnDeposit = new JButton("DEPOSIT");
        btnDeposit.setBackground(new Color(65,125,128));
        btnDeposit.setForeground(Color.WHITE);
        btnDeposit.setFont(new Font("Tahoma", Font.BOLD,16));
        btnDeposit.setBounds(340,272,200,35);
        atmImage.add(btnDeposit);

        btnCash = new JButton("CASH WITHDRAWL");
        btnCash.setBackground(new Color(65,125,128));
        btnCash.setForeground(Color.WHITE);
        btnCash.setFont(new Font("Tahoma", Font.BOLD,16));
        btnCash.setBounds(690,272,200,35);
        atmImage.add(btnCash);

        btnFast = new JButton("FAST CASH");
        btnFast.setBackground(new Color(65,125,128));
        btnFast.setForeground(Color.WHITE);
        btnFast.setFont(new Font("Tahoma", Font.BOLD,16));
        btnFast.setBounds(340,317,200,35);
        atmImage.add(btnFast);

        btnMini = new JButton("MINI STATEMENT");
        btnMini.setBackground(new Color(65,125,128));
        btnMini.setForeground(Color.WHITE);
        btnMini.setFont(new Font("Tahoma", Font.BOLD,16));
        btnMini.setBounds(690,317,200,35);
        atmImage.add(btnMini);

        btnPin = new JButton("PIN CHANGE");
        btnPin.setBackground(new Color(65,125,128));
        btnPin.setForeground(Color.WHITE);
        btnPin.setFont(new Font("Tahoma", Font.BOLD,16));
        btnPin.setBounds(340,362,200,35);
        atmImage.add(btnPin);

        btnBalance = new JButton("BALANCE ENQURIY");
        btnBalance.setBackground(new Color(65,125,128));
        btnBalance.setForeground(Color.WHITE);
        btnBalance.setFont(new Font("Tahoma", Font.BOLD,16));
        btnBalance.setBounds(690,362,200,35);
        atmImage.add(btnBalance);

        btnExit = new JButton("EXIT");
        btnExit.setBackground(new Color(65,125,128));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Tahoma", Font.BOLD,16));
        btnExit.setBounds(690,407,200,35);
        atmImage.add(btnExit);

    }

    public void actionListener(){
        btnDeposit.addActionListener(e -> {
            Deposit dpt = new Deposit(pin);
            dpt.setVisible(true);
            setVisible(false);
        });

        btnCash.addActionListener(e -> {
            Withdrawl wd = new Withdrawl(pin);
            wd.setVisible(true);
            setVisible(false);
        });

        btnFast.addActionListener(e -> {
            FastCash fc = new FastCash(pin);
            fc.setVisible(true);
            setVisible(false);
        });

        btnMini.addActionListener(e -> {
            Mini mn = new Mini(pin);
            mn.setVisible(true);
            setVisible(false);
        });

        btnPin.addActionListener(e -> {
            PinChange pc = new PinChange(pin);
            pc.setVisible(true);
            setVisible(false);
        });

        btnBalance.addActionListener(e -> {
            BalanceEnquriy be = new BalanceEnquriy(pin);
            be.setVisible(true);
            setVisible(false);
        });

        btnExit.addActionListener(e -> {
            System.exit(0);
        });
    }

}
