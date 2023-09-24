package com.tushar.swiggy.convertingConfigtoJavaObject;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public class HostName {

    @NotBlank
    private String companyName;
    private List<String> companyType;

    public HostName(String companyName, List<String> companyType) {
        this.companyName = companyName;
        this.companyType = companyType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<String> getCompanyType() {
        return companyType;
    }

//    public void setCompanyName(String companyName) {
//        this.companyName = companyName;
//    }
//
//    public void setCompanyType(int companyType) {
//        this.companyType = companyType;
//    }
}
