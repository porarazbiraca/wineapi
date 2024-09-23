package x5.ppln.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import x5.ppln.DTO.get.ShopDTO;
import x5.ppln.DTO.put.UpdateShopDTO;
import x5.ppln.Entities.Shop;
import x5.ppln.Mappers.ShopDtoToUpdateShopDto;
import x5.ppln.Mappers.ShopToDtoMapper;
import x5.ppln.Repositories.ShopRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {

        private static ShopRepository shopRepository;
        private ShopToDtoMapper shopToDtoMapper;
        private static ShopDtoToUpdateShopDto shopDtoToUpdateShopDto;

    @Autowired
        public ShopService(ShopRepository shopRepository, ShopToDtoMapper shopToDtoMapper, ShopDtoToUpdateShopDto shopDtoToUpdateShopDto) {
            this.shopRepository = shopRepository;
            this.shopToDtoMapper = shopToDtoMapper;
            this.shopDtoToUpdateShopDto = shopDtoToUpdateShopDto;
        }

    @Transactional(readOnly = true)
    public ShopDTO findShopByCode(Integer shopCode) {

        return shopRepository.findById(shopCode)
                .map(shopToDtoMapper)
                .get();
    }

    @Transactional
    public List<ShopDTO> findAllShops() {
        return shopRepository.findAll().stream().map(shopToDtoMapper).collect(Collectors.toList());
    }

    @Transactional
    public Shop saveShop(Shop newShop) {

        Shop savedShop = new Shop(newShop.getShopCode(), newShop.getShopName(), newShop.getShopLatitude(), newShop.getShopLongitude());
        return shopRepository.save(savedShop);
    }

    @Transactional
    public boolean checkShopExists(Integer shopCode) {
        return shopRepository.existsByShopCode(shopCode);
    }


    @Transactional
    public void deleteShop(Integer shopCode) {
        shopRepository.deleteById(shopCode);
    }



    @Transactional
    public static UpdateShopDTO updateShop(Integer shopCode, ShopDTO shopDTO)  {

        Shop updatedShop = shopRepository.findById(shopCode).get();

        updatedShop.setShopName(shopDTO.getShopName());
        updatedShop.setShopLatitude(shopDTO.getShopLatitude());
        updatedShop.setShopLongitude(shopDTO.getShopLongitude());

        shopRepository.save(updatedShop);
        return shopDtoToUpdateShopDto.apply(shopDTO);
    }


    }

