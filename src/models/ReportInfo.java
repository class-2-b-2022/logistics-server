package models;

import java.io.Serializable;

public class ReportInfo implements Serializable {
    private int productId;
    private int companyTIN;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCompanyTIN() {
        return companyTIN;
    }

    public void setCompanyTIN(int companyTIN) {
        this.companyTIN = companyTIN;
    }
}
