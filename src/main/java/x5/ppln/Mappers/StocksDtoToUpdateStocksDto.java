package x5.ppln.Mappers;

import org.springframework.stereotype.Service;
import x5.ppln.DTO.get.StocksDTO;
import x5.ppln.DTO.put.PutStocksDTO;
import x5.ppln.DTO.put.UpdateStocksDTO;

import java.util.function.Function;

@Service
public class StocksDtoToUpdateStocksDto implements Function<StocksDTO, UpdateStocksDTO> {

    @Override
    public UpdateStocksDTO apply(StocksDTO stocksDTO) {
        return new UpdateStocksDTO(
                stocksDTO.getShopCode(),
                stocksDTO.getWineCode(),
                stocksDTO.getStockQuantity()
        );
    }

}

