package x5.ppln.DTO.get;

public class StocksDTO {
    private Integer id;
    private Integer shopCode;
    private Integer wineCode;
    private Integer stockQuantity;


    public StocksDTO(){}


    public StocksDTO(Integer id, Integer shopCode, Integer stockQuantity, Integer wineCode) {
        this.id = id;
        this.shopCode = shopCode;
        this.wineCode = wineCode;
        this.stockQuantity = stockQuantity;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopCode() {
        return shopCode;
    }

    public void setShopCode(Integer shopCode) {
        this.shopCode = shopCode;
    }

    public Integer getWineCode() {
        return wineCode;
    }

    public void setWineCode(Integer wineCode) {
        this.wineCode = wineCode;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
