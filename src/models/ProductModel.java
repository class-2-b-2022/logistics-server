package models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductModel implements Serializable {
    int productId;
    String productName;
    String productType;
    int userId;
    String pricePerBulk;

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPricePerBulk(String pricePerBulk) {
        this.pricePerBulk = pricePerBulk;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public String getPricePerBulk() {
        return pricePerBulk;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }
}
