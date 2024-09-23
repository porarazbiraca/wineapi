package x5.ppln.Mappers;

import org.springframework.stereotype.Service;
import x5.ppln.DTO.get.StocksWideDTO;
import x5.ppln.Entities.Stocks;

import java.util.function.Function;

@Service
public class StocksWideToDtoMapper implements Function<Stocks, StocksWideDTO> {

    @Override
    public StocksWideDTO apply(Stocks stocks) {
        return new StocksWideDTO(
                stocks.getId(),
                stocks.getShop().getShopCode(),
                stocks.getStockQuantity(),
                stocks.getWineCode(),
                stocks.getWine(),
                stocks.getShop()
        );
    }
}