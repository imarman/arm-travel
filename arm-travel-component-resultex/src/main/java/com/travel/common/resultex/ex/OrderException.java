package com.travel.common.resultex.ex;

import com.travel.common.resultex.enums.IResult;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderException extends RuntimeException {

    private IResult iResult;

    public OrderException(IResult iResult) {
        super(iResult.getMessage());
        this.iResult = iResult;
    }

    public OrderException(IResult iResult, String message) {
        super(message);
        this.iResult = iResult;
    }

}
