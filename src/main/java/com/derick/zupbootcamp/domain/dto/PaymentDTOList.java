package com.derick.zupbootcamp.domain.dto;

import javax.validation.Valid;
import java.util.List;

@Valid
public class PaymentDTOList {

    @Valid
    List<PaymentDTO> list;

    public PaymentDTOList() {
    }

    public PaymentDTOList(List<PaymentDTO> list) {
        this.list = list;
    }

    public List<PaymentDTO> getList() {
        return list;
    }

    public void setList(List<PaymentDTO> list) {
        this.list = list;
    }
}
