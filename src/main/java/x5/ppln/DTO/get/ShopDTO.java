package x5.ppln.DTO.get;

public class ShopDTO {
    private Integer shopCode;
    private String shopName;
    private Double shopLatitude;
    private Double shopLongitude;

    public ShopDTO(){}

    public ShopDTO(Integer shopCode, String shopName, Double shopLatitude, Double shopLongitude) {
        this.shopCode = shopCode;
        this.shopName = shopName;
        this.shopLatitude = shopLatitude;
        this.shopLongitude = shopLongitude;

    }

    public Integer getShopCode() {
        return shopCode;
    }

    public void setShopCode(Integer shopCode) {
        this.shopCode = shopCode;
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
