package BMS;

import javax.swing.*;
import java.awt.*;

public class SignUp2 extends JFrame {

    public SignUp2(){
        super("APPLICATION FORM");

        lblPage2.setText("<html><body><center>Page2<br>Additional Details</center></body></html>");

        lblNo = lblFormNo;

        comboBoxItem();
        buttonGroup();
        actionListener();

        setContentPane(signup2);
        getContentPane().setBackground(new Color(222, 208, 109));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(350,40);
        setSize(850, 750);
    }

    public void comboBoxItem(){
        String[] religion = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        DefaultComboBoxModel model1 = new DefaultComboBoxModel<>(religion);
        coboReligion.setModel(model1);

        String[] category = {"General", "OBC", "SC", "ST", "Other"};
        DefaultComboBoxModel model2 = new DefaultComboBoxModel<>(category);
        coboCategory.setModel(model2);

        String[] income = {"Null", "<1,50,000", "<2,50,000", "5,00,000", "Uptp 10,00,000", "Above 10,00,000"};
        DefaultComboBoxModel model3 = new DefaultComboBoxModel<>(income);
        coboIncome.setModel(model3);

        String[] educational = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctrate", "Others"};
        DefaultComboBoxModel model4 = new DefaultComboBoxModel<>(educational);
        coboEducational.setModel(model4);

        String[] occupation = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"};
        DefaultComboBoxModel model5 = new DefaultComboBoxModel<>(occupation);
        coboOccupation.setModel(model5);

    }

    public void buttonGroup(){
        ButtonGroup g1 = new ButtonGroup();
        g1.add(yesSeniorCitizen);
        g1.add(noSeniorCitizen);
        yesSeniorCitizen.setSelected(true);

        ButtonGroup g2 = new ButtonGroup();
        g2.add(yesAccount);
        g2.add(noAccount);
        yesAccount.setSelected(true);
    }

    public void addSignUp2(){
        String formNo = lblFormNo.getText();
        String religion = (String) coboReligion.getSelectedItem();
        String category = (String) coboCategory.getSelectedItem();
        String income = (String) coboIncome.getSelectedItem();
        String educational = (String) coboEducational.getSelectedItem();
        String occupation = (String) coboOccupation.getSelectedItem();
        String pan = txtPanNumber.getText();
        String aadhar = txtAadharNumber.getText();

        String citizen = "";
        if (yesSeniorCitizen.isSelected()){
            citizen = "Yes";
        } else if (noSeniorCitizen.isSelected()) {
            citizen = "No";
        }

        String account = "";
        if (yesAccount.isSelected()){
            account = "Yes";
        } else if (noAccount.isSelected()) {
            account = "No";
        }

        try {
            if (txtPanNumber.getText().equals("") || txtAadharNumber.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Fill all the Fields");
            }else {
                Conn con = new Conn();
                String query = "INSERT INTO signuptwo VALUES ('" + formNo + "', '" + religion + "', " +
                        " '" + category + "', '" + income + "', '" + educational + "', '" + occupation + "'," +
                        " '" + pan + "', '" + aadhar + "', '" + citizen + "', '" + account + "')";

                con.stmt.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "SignUpTwo Add Successfully");

                setVisible(false);
                SignUp3 su3 = new SignUp3();
                su3.setVisible(true);
                SignUp3.lblNo.setText(formNo);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void actionListener(){
        nextButton.addActionListener(e -> {
            addSignUp2();
        });
    }


    private JPanel framePanel;
    private JPanel signup2;
    private JComboBox coboReligion;
    private JTextField txtPanNumber;
    private JLabel lblPage2;
    private JButton nextButton;
    private JLabel lblFormNo;
    private JComboBox coboCategory;
    private JComboBox coboIncome;
    private JComboBox coboEducational;
    private JComboBox coboOccupation;
    private JTextField txtAadharNumber;
    private JRadioButton yesSeniorCitizen;
    private JRadioButton noSeniorCitizen;
    private JRadioButton yesAccount;
    private JRadioButton noAccount;
    public static JLabel lblNo;

    /*
    * Religion (종교)
    * Category (범주)
    * Income (소득)
    * Educational (교육적인)
    * Occupation (직업)
    * Aadhar (아드하르) Number (수)
    * Senior (상위)
    * Citizen (시민)
    * Account (계정) */
}
