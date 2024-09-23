package x5.ppln.DTO.put;

import x5.ppln.Entities.Shop;
import x5.ppln.Entities.Wine;

public class PutStocksDTO {
    private Integer id;
    private Integer shopCode;
    private Integer wineCode;
    private Integer stockQuantity;

    public PutStocksDTO(){}

    public PutStocksDTO(Integer id, Integer shopCode, Integer wineCode, Integer stockQuantity) {
        this.id = id;
        this.shopCode = shopCode;
        this.stockQuantity = stockQuantity;
        this.wineCode = wineCode;
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
