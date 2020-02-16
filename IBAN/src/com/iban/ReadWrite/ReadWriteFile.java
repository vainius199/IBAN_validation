package com.iban.ReadWrite;


import com.iban.Validation.Validate;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadWriteFile {

    public void ReadMyFile(String textField1, String textField3) throws IOException{

        String ibanList="";
        String filePath = textField1;
        String fileN = "\\" + textField3 + ".txt";
        String fileName = filePath  + fileN;
        if(textField1 != "" && textField3 != ""){


            try {
                File file = new File(fileName);
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

                Validate validate = new Validate();



                String line;
                while((line = br.readLine()) != null){

                    String ibanString = line;
                    String lineWithoutSpaces = ibanString.replaceAll("\\s+","");
                    String IBAN = lineWithoutSpaces.toUpperCase();


                    if(validate.ValidateIban(IBAN) == true){
                        ibanList = ibanList + line +";true \n";
                    }
                    else {
                        ibanList = ibanList + line +";false \n";
                    }
                }
                br.close();

                writeUsingFiles(ibanList, textField1,textField3);
                file.delete();

            }catch (IOException e){
                JOptionPane.showMessageDialog(null,"Path to file or file was not found");
            }


        }else {
            JOptionPane.showMessageDialog(null,"Please fill both fields");
        }
    }


    public void writeUsingFiles(String data,String path,String name) {
        try {
            Files.write(Paths.get(path+"\\"+name+".out.txt"), data.getBytes());
            JOptionPane.showMessageDialog(null,"Validation to file was successful");
        } catch (IOException e) {
            e.printStackTrace();
           JOptionPane.showMessageDialog(null,"Path to file or file was not found");
        }

    }






}
