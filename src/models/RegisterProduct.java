package models;

public class RegisterProduct {
	private String productName;
	private String productType;
	private Integer pricePBulk;
	public RegisterProduct(){}
	public RegisterProduct(String productName,String productType,Integer pricePBulk){
		this.productName=productName;
				this.productType=productType;
				this.pricePBulk=pricePBulk;
	}
		public Integer getPricePBulk() {
		return pricePBulk;
	}
	public void setPricePBulk(Integer pricePBulk) {
		this.pricePBulk = pricePBulk;
	}
		public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String toString() {
		return productName+""+productType;
	}
	

}
