package x5.ppln.Mappers;

import org.springframework.stereotype.Service;
import x5.ppln.DTO.get.ShopDTO;
import x5.ppln.DTO.put.PutShopDTO;
import x5.ppln.DTO.put.UpdateShopDTO;

import java.util.function.Function;

@Service
public class ShopDtoToUpdateShopDto implements Function<ShopDTO, UpdateShopDTO> {

    @Override
    public UpdateShopDTO apply(ShopDTO shopDTO) {
        return new UpdateShopDTO(
                shopDTO.getShopName(),
                shopDTO.getShopLatitude(),
                shopDTO.getShopLongitude()
        );
    }

}

