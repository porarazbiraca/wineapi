package x5.ppln.Mappers;

import org.springframework.stereotype.Service;
import x5.ppln.DTO.get.StocksDTO;
import x5.ppln.Entities.Stocks;

import java.util.function.Function;

@Service
public class StocksToDtoMapper implements Function<Stocks, StocksDTO> {

    @Override
    public StocksDTO apply(Stocks stocks) {
        return new StocksDTO(
                stocks.getId(),
                stocks.getShop().getShopCode(),
                stocks.getStockQuantity(),
                stocks.getWineCode()
        );
    }
}