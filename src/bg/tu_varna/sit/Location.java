package bg.tu_varna.sit;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlRootElement(name = "Location")
@XmlType(propOrder = { "sector", "shelf","number"})

public class Location {

    private String sector;
    private int shelf;
    private int number;

    public Location(String sector, int shelf, int number) {
        this.sector = sector;
        this.shelf = shelf;
        this.number = number;
    }

    public Location(){}

    @XmlAttribute
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @XmlAttribute
    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    @XmlAttribute
    public int getNumber() {
        return number;
    }


    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return shelf == location.shelf && number == location.number && sector.equals(location.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sector, shelf, number);
    }

    @Override
    public String toString()
    {
        return "{" +
                "sector='" + sector + '\'' +
                ", shelf=" + shelf +
                ", number=" + number +
                "}";
    }

    public String fullInfo(Double quantity) {
        return "{" +
                "sector='" + sector + '\'' +
                ", shelf=" + shelf +
                ", number=" + number +
                ", capacity=" + quantity +
                "/1000}";
    }


}