package com.iban;

import com.iban.ReadWrite.ReadWriteFile;
import com.iban.Validation.Validate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.UIManager;

public class GUI {
    private JTextArea textArea1;
    private JButton button1;
    private JPanel main;
    private JTabbedPane tabbedPane1;
    private JTextField textField2;
    private JButton button2;
    private JTextField textField1;
    private JTextField textField3;

    public GUI() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Validate validate = new Validate();

                String ibanString = textField2.getText();
                String lineWithoutSpaces = ibanString.replaceAll("\\s+","");
                String IBAN = lineWithoutSpaces.toUpperCase();

               boolean isValid = validate.ValidateIban(IBAN);

            if(isValid == true){
                    textArea1.setText(ibanString + "; TRUE");
                }
               else {
                    textArea1.setText(ibanString + "; FALSE");

                }
        }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ReadWriteFile readWriteFile = new ReadWriteFile();

                  try {
                      readWriteFile.ReadMyFile(textField1.getText(), textField3.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }


    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }


        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }


}
