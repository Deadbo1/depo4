package application.models;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class ListPaidType {
    private List<PaidType> paidTypeList;


    public ListPaidType() {
    }
    @JsonCreator
    public ListPaidType(List<PaidType> paidTypesList) {
        this.paidTypeList = paidTypesList;
    }

    public List<PaidType> getPaidTypeList() {
        return paidTypeList;
    }

    public void setPaidTypeList(List<PaidType> paidTypeList) {
        this.paidTypeList = paidTypeList;
    }
}
