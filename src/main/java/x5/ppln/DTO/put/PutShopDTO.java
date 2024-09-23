package x5.ppln.DTO.put;

public class PutShopDTO {
    private String shopName;
    private Double shopLatitude;
    private Double shopLongitude;

    public PutShopDTO(){}

    public PutShopDTO(String shopName, Double shopLatitude, Double shopLongitude) {
        this.shopName = shopName;
        this.shopLatitude = shopLatitude;
        this.shopLongitude = shopLongitude;
    }



    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Double getShopLatitude() {
        return shopLatitude;
    }

    public void setShopLatitude(Double shopLatitude) {
        this.shopLatitude = shopLatitude;
    }

    public Double getShopLongitude() {
        return shopLongitude;
    }

    public void setShopLongitude(Double shopLongitude) {
        this.shopLongitude = shopLongitude;
    }
}
