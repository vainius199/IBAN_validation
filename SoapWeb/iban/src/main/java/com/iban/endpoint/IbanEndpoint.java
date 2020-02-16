package com.iban.endpoint;

import com.iban.iban.GetIbanRequest;
import com.iban.iban.GetIbanResponse;
import com.iban.service.IbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class IbanEndpoint {


    @Autowired
    private IbanService ibanService;

    @PayloadRoot(namespace = "http://iban.com/iban", localPart = "getIbanRequest")
    @ResponsePayload
    public GetIbanResponse getIbanRequest(@RequestPayload GetIbanRequest request){

        GetIbanResponse response = new GetIbanResponse();

        response.setIban(ibanService.getValidation(request.getIban()));

        return response;


    }


}
