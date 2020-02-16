package com.Rest.Rest.Validate;

import com.Rest.Rest.List.Iban;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class Validation {

    private static final List<String> CountryCodes = Arrays.asList(Locale.getISOCountries());

    public List<Iban> IbanList (List<Iban> ibans) {
        List<Iban> validatedIBANS = new ArrayList<>();
        for (Iban iban : ibans) {
            if (ValidateIban(iban.getIBAN()))
            {
                iban.setIBANValid(true);
                validatedIBANS.add(iban);
            } else
                {
                iban.setIBANValid(false);
                validatedIBANS.add(iban);
            }
        }
        return validatedIBANS;
    }



    private boolean ValidateIban(String iban){

        String lineWithoutSpaces = iban.replaceAll("\\s+","");
        String IBAN = lineWithoutSpaces.toUpperCase();


        if(convertIban(IBAN) && FindCountry(IBAN)){
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
