package com.iban.service;

import com.iban.iban.Validation;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.math.BigInteger;
import java.util.*;

@Service
public class IbanService {

    private static final Map<String, Validation> isValid = new HashMap<>();
    private static final List<String> CountryCodes = Arrays.asList(Locale.getISOCountries());


@PostConstruct
    public void initialize(){

    }

    public Validation getValidation(String name){
        return IbaNumber(name);
    }

    public Validation  IbaNumber(String number){

        String lineWithoutSpaces = number.replaceAll("\\s+","");
        String IBAN = lineWithoutSpaces.toUpperCase();
        boolean isIBanValid = ValidateIban(IBAN);


        if(isIBanValid){

            Validation temp = new Validation();
            temp.setIBANumber(number);
            temp.setIsValid(true);
            isValid.put("true", temp);
            return isValid.get("true");

        }else {
            Validation temp = new Validation();
            temp.setIBANumber(number);
            temp.setIsValid(false);
            isValid.put("false", temp);
            return isValid.get("false");

        }

    }

    private boolean ValidateIban(String iban){

        if(convertIban(iban) && FindCountry(iban)){
            return true;
        }else {
            return false;
        }
    }



    private boolean convertIban(String iban) {

        String shortIban = iban.substring(4) + iban.substring(0, 4);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < shortIban.length(); i++) {
            stringBuilder.append(Character.getNumericValue(shortIban.charAt(i)));
        }

        BigInteger bigIntegerIban = new BigInteger(stringBuilder.toString());

        BigInteger mod97 = BigInteger.valueOf(97);
        BigInteger answer =  bigIntegerIban.mod(mod97);
        int compare = answer.compareTo(new BigInteger("1"));


        if(compare == 0){
            return true;

        }
        else {
           return false;

        }
    }

    private boolean FindCountry(String iban) {
        String CountryCode = iban.substring(0, 2);

        for (String countryCode : CountryCodes)
        {
            if (countryCode.equals(CountryCode))
            {
                return true;
            }
        }
        return false;
    }


}


