package models;

import java.io.Serializable;

public class InventoryModel implements Serializable {
    int quantity;
    String Status;
    int productId;
    int branchId;
    String status;

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {

        Status = status;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }


    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getBranchId() {
        return branchId;
    }

    public String getStatus() {

        return Status;


    }
}
