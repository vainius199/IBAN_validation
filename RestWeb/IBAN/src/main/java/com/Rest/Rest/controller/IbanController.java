package com.Rest.Rest.controller;

import com.Rest.Rest.List.Iban;
import com.Rest.Rest.Validate.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IbanController {

    @Autowired
    private Validation validation;

    @RequestMapping(value = "/rest", method = RequestMethod.POST)
    public List<Iban> validateIban(@RequestBody List<Iban> ibans){
        return validation.IbanList(ibans);
    }
}
