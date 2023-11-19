package BMS;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SignUp3 extends JFrame{

    public SignUp3(){
        super("APPLICATION FORM");
        lblNo = lblFormNo;

        buttonGroup();
        actionListener();

        setContentPane(signup3);
        getContentPane().setBackground(new Color(129, 202, 247));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(350,40);
        setSize(900, 750);
    }

    public void actionListener(){
        btnSubmit.addActionListener(e -> {
            addSignUp3();
        });

        btnCancel.addActionListener(e -> {
            setVisible(false);
            System.exit(0);
        });
    }

    public void buttonGroup(){
        ButtonGroup g1 = new ButtonGroup();
        g1.add(rbSaving);
        g1.add(rbFixed);
        g1.add(rbCurrent);
        g1.add(rbRecurring);
    }

    public void addSignUp3(){
        String formNo = lblFormNo.getText();
        String aType = null;
        if (rbSaving.isSelected()){
            aType = "Saving Account";
        } else if (rbFixed.isSelected()) {
            aType = "Fixed Deposit Account";
        } else if (rbCurrent.isSelected()) {
            aType = "Current Account";
        } else if (rbRecurring.isSelected()) {
            aType = "Recurring Deposit Account";
        }

        Random ran = new Random();
        long first7 = (ran.nextLong() % 90000000L) + 1409963000000000L;
        String cardNo = "" + Math.abs(first7);

        long first3 = (ran.nextLong() % 9000L) + 1000L;
        String pin = "" + Math.abs(first3);
        
        String fac = "";
        if (ckbAtm.isSelected()){
            fac = fac + "ATM CARD";
        } else if (ckbInternet.isSelected()) {
            fac = fac + "Internet Banking";
        } else if (ckbMobile.isSelected()) {
            fac = fac + "Mobile Banking";
        } else if (ckbEmail.isSelected()) {
            fac = fac + "EMAIL Alerts";
        } else if (ckbChequeBook.isSelected()) {
            fac = fac + "Cheque Book";
        } else if (ckbEStatement.isSelected()) {
            fac = fac + "E-Statement";
        }

        try {
            if (aType.equals("")){
                JOptionPane.showMessageDialog(null, "Fill all the Fields");
            }else {
                Conn con = new Conn();
                String query1 = "INSERT INTO signupthree VALUES ('" + formNo + "', '" + aType + "', " +
                        " '" + cardNo + "', '" + pin + "', '" + fac + "')";
                String query2 = "INSERT INTO login VALUES ('" + formNo + "', '" + cardNo + "', '" + pin + "')";

                con.stmt.executeUpdate(query1);
                con.stmt.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "Card Number : " + cardNo + "\n pin : " + pin +
                        "\n SignUp3 Add Successfully");

                setVisible(false);
                Deposit dpt = new Deposit(pin);
                dpt.setVisible(true);
                //SignUp3.lblNo.setText(formNo);
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private JPanel framePanel;
    private JPanel signup3;
    private JLabel lblPage2;
    private JLabel lblFormNo;
    private JRadioButton rbSaving; // 절약 저금
    private JRadioButton rbFixed; // 결정된
    private JRadioButton rbCurrent; // 현재의
    private JRadioButton rbRecurring; // 반복
    private JCheckBox ckbAtm; // ATM
    private JCheckBox ckbInternet; // 인터넷 뱅킹
    private JCheckBox ckbMobile; // 모바일 뱅킹
    private JCheckBox ckbEmail; // 이메일 알림
    private JCheckBox ckbChequeBook; // Chequebook 수표장
    private JCheckBox ckbEStatement; // E-Statement 전자명세서
    private JCheckBox ckbIhere; // 나는 위에 기재한 세부사항이 내가 아는 한 정확함을 선언합니다.
    private JButton btnSubmit;
    private JButton btnCancel;
    private JLabel lblCardNo; // (ATM 카드/수표 장부 및 명세서에 표시됩니다)
    private JLabel lblPinCode;
    public static JLabel lblNo;
}
