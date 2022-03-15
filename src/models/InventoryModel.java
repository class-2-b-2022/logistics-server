package models;

import java.io.Serializable;

public class InventoryModel implements Serializable {
    int quantity;
    String Status;
    int productId;
    int userId;

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUserId() {
        return userId;
    }

    public String getStatus() {
        return Status;
    }
}
