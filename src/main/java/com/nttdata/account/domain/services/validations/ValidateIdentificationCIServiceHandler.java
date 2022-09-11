package com.nttdata.account.domain.services.validations;


import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import io.jkratz.mediator.core.RequestHandler;

@Component
public class ValidateIdentificationCIServiceHandler implements RequestHandler<ValidateIdentificationCIService, Boolean>  {


    public ValidateIdentificationCIServiceHandler() {
        //Local validation
    }

    //10digit EC CI validation
    @Override
    public Boolean handle(@NotNull ValidateIdentificationCIService service) {

        Boolean isValid = Boolean.TRUE;
        String ciNumber = service.getCiNumber();
        List<Integer> ciArray =  this.createCiArray(ciNumber);

        //Regional Code
        int regionCode = Integer.parseInt(ciArray.get(0).toString()+ciArray.get(1).toString());

        if((regionCode<=24 && regionCode>=1)||(regionCode==30)){

            //Last digit
            int lastDigit = ciArray.get(ciArray.size()-1);

            //Even position numbers sum
            int evensSum = this.getEvenPositionNumbersSum(ciArray);

            //Odd position numbers by2 multiply and sum
            int oddsSum = 0;

            for(int j =0;j<ciArray.size()-1;j+=2){

                int oddVerficiation = ciArray.get(j)*2;

                if(oddVerficiation>9)
                {
                    oddVerficiation-=9;
                }

                oddsSum += oddVerficiation;
            }

            //digit sum and module 10
            int totalSum = oddsSum + evensSum;
            Integer module10 = Integer.parseInt(Integer.toString(totalSum).substring(Integer.toString(totalSum).length()-1,Integer.toString(totalSum).length()));

            //final verification
            int verificator = 0;

            if(module10 !=0)
            {
                verificator = 10 - module10;
            }

            if(verificator!=lastDigit)
            {
               isValid = Boolean.FALSE;
            }
        } else {

            isValid = Boolean.FALSE;
        }

        return isValid;
    }

    private List<Integer> createCiArray (String ciNumber){

        ArrayList<Integer> ciArray =  new ArrayList<>();

        for(int i=0;i<ciNumber.length();i++){

            Integer digit = Integer.parseInt(ciNumber.substring(i,i+1));
            ciArray.add(digit);
        }

        return ciArray;
    }

    private int getEvenPositionNumbersSum(List<Integer> ciArray){

        int evensSum = 0;

        for(int i = 1; i < ciArray.size()-1; i += 2){

            evensSum += ciArray.get(i);
        }

        return evensSum;
    }
}
