package com.Rest.Rest.List;

public class Iban {

    private String IBAN;

    private boolean  isValid;


    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String Iban) {
        this.IBAN = Iban;
    }

    public boolean getIBANValid() { return isValid;}

    public void setIBANValid(boolean Valid) {
        isValid = Valid;
    }

}
