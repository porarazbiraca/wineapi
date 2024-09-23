package x5.ppln.DTO.put;

import x5.ppln.Entities.Shop;
import x5.ppln.Entities.Wine;

public class UpdateStocksDTO {
    private Integer shopCode;
    private Integer wineCode;
    private Integer stockQuantity;

    public UpdateStocksDTO(){}

    public UpdateStocksDTO(Integer shopCode, Integer wineCode, Integer stockQuantity) {
        this.shopCode = shopCode;
        this.wineCode = wineCode;
        this.stockQuantity = stockQuantity;

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

    public Integer getShopCode() {
        return shopCode;
    }

    public void setShopCode(Integer shopCode) {
        this.shopCode = shopCode;
    }
}
