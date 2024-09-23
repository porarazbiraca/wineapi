package x5.ppln.DTO.post;

import x5.ppln.Entities.Wine;

public class CreateStocksWideDTO {
    private Integer id;
    private Integer shopCode;
    private Integer wineCode;
    private Integer stockQuantity;
    private Wine wine;

    public CreateStocksWideDTO(){}


    public CreateStocksWideDTO(Integer id, Integer shopCode, Integer stockQuantity, Integer wineCode, Wine wine ) {
        this.id = id;
        this.shopCode = shopCode;
        this.wineCode = wineCode;
        this.stockQuantity = stockQuantity;
        this.wine = wine;
    }

    public Wine getWine() {
        return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
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
