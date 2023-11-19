package BMS;

import javax.swing.*;
import java.awt.*;

public class PinChange extends JFrame {

    String pin;
    JPasswordField txtPin, txtRe;
    JButton btnChange, btnBack;
    PinChange(String pin){
        super("APPLICATION FORM");
        this.pin = pin;

        initPin();
        actionListener();

        setLayout(null);
        getContentPane().setBackground(new Color(129, 202, 247));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(170,30);
        setUndecorated(true);
        setSize(1550, 830);

    }

    public void initPin(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/Atm4.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel atmImage = new JLabel(i3);
        atmImage.setBounds(0,0,1550,830);
        add(atmImage);

        JLabel label = new JLabel("CHANGE YOUR PIN");
        label.setFont(new Font("System", Font.BOLD, 30));
        label.setForeground(Color.WHITE);
        label.setBounds(385,160,700,35);
        atmImage.add(label);

        JLabel label2 = new JLabel("New PIN");
        label2.setFont(new Font("System", Font.BOLD, 18));
        label2.setForeground(Color.WHITE);
        label2.setBounds(400,210,300,30);
        atmImage.add(label2);

        txtPin = new JPasswordField();
        txtPin.setBackground(new Color(65,128,125));
        txtPin.setForeground(Color.WHITE);
        txtPin.setBounds(690,210,200,25);
        txtPin.setFont(new Font("Tahoma", Font.BOLD,18));
        atmImage.add(txtPin);

        JLabel label3 = new JLabel("Re_ Enter New PIN");
        label3.setFont(new Font("System", Font.BOLD, 18));
        label3.setForeground(Color.WHITE);
        label3.setBounds(400,240,300,30);
        atmImage.add(label3);

        txtRe = new JPasswordField();
        txtRe.setBackground(new Color(65,128,125));
        txtRe.setForeground(Color.WHITE);
        txtRe.setBounds(690,240,200,25);
        txtRe.setFont(new Font("Tahoma", Font.BOLD,18));
        atmImage.add(txtRe);

        btnChange = new JButton("CHANGE");
        btnChange.setBounds(690,375,200,35);
        btnChange.setBackground(new Color(65,125,128));
        btnChange.setForeground(Color.WHITE);
        btnChange.setFont(new Font("Tahoma", Font.BOLD,16));
        atmImage.add(btnChange);

        btnBack = new JButton("BACK");
        btnBack.setBounds(690,420,200,35);
        btnBack.setBackground(new Color(65,125,128));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Tahoma", Font.BOLD,16));
        atmImage.add(btnBack);
    }

    public void actionListener(){
        btnChange.addActionListener(e -> {
            String pin1 = new String(txtPin.getPassword());
            String pin2 = new String(txtRe.getPassword());

            if (!pin1.equals(pin2)){
                JOptionPane.showMessageDialog(null, "Entered PIN does not Match");
                return;
            }

            if (pin1.equals("")){
                JOptionPane.showMessageDialog(null, "Enter New PIN");
                return;
            }
            if (pin2.equals("")){
                JOptionPane.showMessageDialog(null, "Re_Enter New PIN");
                return;
            }

            try {
                Conn con = new Conn();
                String q1 = "UPDATE bank SET pin = '" +pin1+ "' WHERE pin = '" +pin+ "'";
                String q2 = "UPDATE login SET pin = '" +pin1+ "' WHERE pin = '" +pin+ "'";
                String q3 = "UPDATE signupthree SET pin = '" +pin1+ "' WHERE pin = '" +pin+ "'";

                con.stmt.executeUpdate(q1);
                con.stmt.executeUpdate(q2);
                con.stmt.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN Change Successfully");

                setVisible(false);
                BankATM batm = new BankATM(pin);
                batm.setVisible(true);

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
