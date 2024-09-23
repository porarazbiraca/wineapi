package x5.ppln.Mappers;

import org.springframework.stereotype.Service;
import x5.ppln.DTO.get.ShopDTO;
import x5.ppln.Entities.Shop;


import java.util.function.Function;

@Service
public class ShopToDtoMapper implements Function<Shop, ShopDTO> {
    @Override
    public ShopDTO apply(Shop shop) {
        return new ShopDTO(
                shop.getShopCode(),
                shop.getShopName(),
                shop.getShopLatitude(),
                shop.getShopLongitude()
        );
    }
}
