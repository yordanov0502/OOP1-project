package bg.tu_varna.sit;

public class RemovedProduct {

    private Product product;

    public RemovedProduct(){}

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public String removeInfo()
    {
        return  "\n\t\t\t  productName='" + product.getProductName() + '\'' +
                "\n\t\t\t  expiryDate=" + product.getExpiryDate() +
                "\n\t\t\t  entryDate=" + product.getEntryDate() +
                "\n\t\t\t  manufacturer='" + product.getManufacturer() + '\'' +
                "\n\t\t\t  unit='" + product.getUnit() + '\'' +
                "\n\t\t\t  quantity=" + product.getQuantity() +
                "\n\t\t\t  location" + product.getLocation() +
                "\n\t\t\t  comment='" + product.getComment() + '\'' +
                "\n";
    }

}
