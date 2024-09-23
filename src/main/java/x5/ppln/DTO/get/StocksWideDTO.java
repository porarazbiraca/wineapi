package x5.ppln.DTO.get;

import x5.ppln.Entities.Shop;
import x5.ppln.Entities.Wine;

public class StocksWideDTO {
    private Integer id;
    private Integer shopCode;
    private Integer wineCode;
    private Integer stockQuantity;
    private Wine wine;
    private Shop shop;


    public StocksWideDTO(){}


    public StocksWideDTO(Integer id, Integer shopCode, Integer stockQuantity, Integer wineCode, Wine wine, Shop shop) {
        this.id = id;
        this.shopCode = shopCode;
        this.wineCode = wineCode;
        this.stockQuantity = stockQuantity;
        this.wine = wine;
        this.shop = shop;
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

    public Wine getWine() {
        return new Wine(
                wine.getCode(),
        wine.getWineName(),
        wine.getWineColor(),
                wine.getWineGrapeVariety(),
        wine.getWineVintage(),
        wine.getWineCountry());

        //return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    public Shop getShop() {
        return new Shop(
                shop.getShopCode(),
                shop.getShopName(),
                shop.getShopLatitude(),
                shop.getShopLongitude()
        );
        //return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
