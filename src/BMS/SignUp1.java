package BMS;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Random;

public class SignUp1 extends JFrame{

    Calendar calendar = Calendar.getInstance();
    JDateChooser dateChooser;
    Random ran = new Random();
    long first4 = (ran.nextLong() % 9000L) + 1000L;
    String first = " " + Math.abs(first4);

    public SignUp1(){
        super("APPLICATION FORM");

        lblAfn.setText("APPLICATION FORM NO." + first);

        ButtonGroup groupGender = new ButtonGroup();
        groupGender.add(rbMale);
        groupGender.add(rbFemale);
        rbMale.setSelected(true);

        ButtonGroup groupMarital = new ButtonGroup();
        groupMarital.add(rbMarried);
        groupMarital.add(rbUnmarried);
        groupMarital.add(rbOther);
        rbMarried.setSelected(true);

        // calender
        dateChooser = new JDateChooser();
        dateChooser.setBounds(100,600,150,30);
        dateChooser.setFont(new Font("serif", Font.BOLD, 18));
        dateChooser.setDateFormatString("yyyy-MM-dd"); // yyyy/MM/dd, dd/MM/yyyyy,mm/dd/yyyy
        dateChooser.setCalendar(calendar);
        datePanel.add(dateChooser);

        actionListener();

        setContentPane(signup1);
        getContentPane().setBackground(new Color(222, 255, 228));
        //setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(350,40);
        setSize(850, 800);

    }

    public void actionListener(){
        nextButton.addActionListener(e -> addSignUp1());
    }

    public void addSignUp1(){
        String formNo = first;
        String name = txtName.getText();
        String fname = txtFname.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (rbMale.isSelected()){
            gender = "Male";
        } else if (rbFemale.isSelected()) {
            gender = "Female";
        }
        String email = txtEmail.getText();
        String marital = null;
        if (rbMarried.isSelected()){
            marital = "Married";
        } else if (rbUnmarried.isSelected()) {
            marital = "Unmarried";
        } else if (rbOther.isSelected()) {
            marital = "Other";
        }
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String pin = txtPin.getText();
        String state = txtState.getText();

        try {
            if (txtName.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Fill all the Fields");
            }else {
                Conn con = new Conn();
                String query = "INSERT INTO signup VALUES ('" + formNo + "', '" + name + "', " +
                        " '" + fname + "', '" + dob + "', '" + gender + "', '" + email + "'," +
                        " '" + marital + "', '" + address + "', '" + city + "', '" + pin + "'," +
                        " '" + state + "')";

                con.stmt.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "SignUp Add Successfully");

                setVisible(false);
                SignUp2 su2 = new SignUp2();
                su2.setVisible(true);
                SignUp2.lblNo.setText(first);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private JPanel framePanel;
    private JPanel signup1;
    private JTextField txtName;
    private JButton nextButton;
    private JTextField txtFname;
    private JRadioButton rbMale;
    private JRadioButton rbFemale;
    private JTextField txtBirth;
    private JTextField txtEmail;
    private JTextField txtAddress;
    private JTextField txtCity;
    private JTextField txtPin;
    private JTextField txtState;
    private JRadioButton rbMarried;
    private JRadioButton rbUnmarried;
    private JRadioButton rbOther;
    private JLabel lblPage1;
    private JPanel datePanel;
    private JLabel lblAfn;

}
