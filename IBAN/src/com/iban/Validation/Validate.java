package com.iban.Validation;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Validate {

    private static final List<String> CountryCodes = Arrays.asList(Locale.getISOCountries());

    public boolean ValidateIban(String iban){

        boolean isIbanValid;

        BigInteger bigInteger = convertIban(iban);
        BigInteger mod97 = BigInteger.valueOf(97);
        BigInteger answer =  bigInteger.mod(mod97);
        int compare = answer.compareTo(new BigInteger("1"));


        if(compare == 0 && FindCountry(iban) == true){
             return isIbanValid = true;
        }
        else {
          return isIbanValid = false;
        }
    }


    public BigInteger convertIban(String iban) {

        String shortIban = iban.substring(4) + iban.substring(0, 4);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < shortIban.length(); i++) {

            stringBuilder.append(Character.getNumericValue(shortIban.charAt(i)));
        }
        return new BigInteger(stringBuilder.toString());
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
