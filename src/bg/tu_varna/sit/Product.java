package bg.tu_varna.sit;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlRootElement(name = "Product")
@XmlType(propOrder = { "productName", "expiryDate","entryDate","manufacturer","unit","quantity","location","comment"})

public class Product {

    private String productName;   //ime na produkta
    private org.joda.time.LocalDate expiryDate; //srok na godnost
    private org.joda.time.LocalDate entryDate;  //data na postupvane v sklada
    private String manufacturer;  //ime na proizvoditel
    private String unit;          //merna edinica
    private double quantity;      //kolichestvo na produkta
    private Location location;    //mestopolojenie na produkta
    private String comment;       //komentar

    public Product(String productName, org.joda.time.LocalDate expiryDate, org.joda.time.LocalDate entryDate, String manufacturer, String unit, double quantity, Location location, String comment) {
        this.productName = productName;
        this.expiryDate = expiryDate;
        this.entryDate = entryDate;
        this.manufacturer = manufacturer;
        this.unit = unit;
        this.quantity = quantity;
        this.location = location;
        this.comment = comment;
    }

    public Product(){}


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public org.joda.time.LocalDate getExpiryDate() {
        return expiryDate;
    }


    public void setExpiryDate(org.joda.time.LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }


    public org.joda.time.LocalDate getEntryDate() {
        return entryDate;
    }


    public void setEntryDate(org.joda.time.LocalDate entryDate) {
        this.entryDate = entryDate;
    }


    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productName.equals(product.productName) && expiryDate.equals(product.expiryDate) && manufacturer.equals(product.manufacturer) && unit.equals(product.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, expiryDate, manufacturer, unit);
    }

    @Override
    public String toString() {
        return  "\n\t\t\t  productName='" + productName + '\'' +
                "\n\t\t\t  expiryDate=" + expiryDate +
                "\n\t\t\t  entryDate=" + entryDate +
                "\n\t\t\t  manufacturer='" + manufacturer + '\'' +
                "\n\t\t\t  unit='" + unit + '\'' +
                "\n\t\t\t  quantity=" + quantity +
                "\n\t\t\t  location" + location.fullInfo(getQuantity()) +
                "\n\t\t\t  comment='" + comment + '\'' +
                "\n";
    }

    public String removeInfo()
    {
        return  "\n\t\t\t  productName='" + productName + '\'' +
                "\n\t\t\t  expiryDate=" + expiryDate +
                "\n\t\t\t  entryDate=" + entryDate +
                "\n\t\t\t  manufacturer='" + manufacturer + '\'' +
                "\n\t\t\t  unit='" + unit + '\'' +
                "\n\t\t\t  quantity=" + quantity +
                "\n\t\t\t  location" + location +
                "\n\t\t\t  comment='" + comment + '\'' +
                "\n";
    }
}