package x5.ppln.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import x5.ppln.DTO.get.StocksDTO;
import x5.ppln.DTO.get.StocksWideDTO;
import x5.ppln.DTO.post.CreateStocksDTO;
import x5.ppln.DTO.post.CreateStocksWideDTO;
import x5.ppln.DTO.put.PutStocksDTO;
import x5.ppln.DTO.put.UpdateStocksDTO;
import x5.ppln.Entities.Clients;
import x5.ppln.Entities.Shop;
import x5.ppln.Entities.Stocks;
import x5.ppln.Entities.Wine;
import x5.ppln.Mappers.*;
import x5.ppln.Repositories.ShopRepository;
import x5.ppln.Repositories.StocksRepository;
import x5.ppln.Repositories.WineRepository;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class StocksService {

        private final WineRepository wineRepository;
        private static StocksRepository stocksRepository;
        private final ShopRepository shopRepository;
        private final StocksToDtoMapper stocksToDtoMapper;
        private final StocksWideToDtoMapper stocksWideToDtoMapper;
        private static StocksDtoToUpdateStocksDto stocksDtoToUpdateStocksDto;

    @Autowired
        public StocksService(StocksRepository stocksRepository, StocksToDtoMapper stocksToDtoMapper, StocksWideToDtoMapper stocksWideToDtoMapper, WineRepository wineRepository, StocksDtoToUpdateStocksDto stocksDtoToUpdateStocksDto, ShopRepository shopRepository) {
            this.stocksRepository = stocksRepository;
            this.stocksToDtoMapper = stocksToDtoMapper;
            this.stocksWideToDtoMapper = stocksWideToDtoMapper;
            this.wineRepository = wineRepository;
            this.stocksDtoToUpdateStocksDto = stocksDtoToUpdateStocksDto;
            this.shopRepository = shopRepository;
        }


    @Transactional(readOnly = true)
    public StocksDTO findStocksById(Integer code) {
        return stocksRepository.findById(code)
                .map(stocksToDtoMapper)
                .get();
    }

    @Transactional
    public Stocks saveStocks(CreateStocksDTO createStocksDTO) {


        Wine wine = wineRepository.findWineByCode(createStocksDTO.getWineCode());
        Shop shop = shopRepository.findShopByShopCode(createStocksDTO.getShopCode());

        Stocks savedStocks = new Stocks(createStocksDTO.getShopCode(), shop, wine, createStocksDTO.getStockQuantity() , createStocksDTO.getWineCode());

        return stocksRepository.save(savedStocks);
    }

    @Transactional
    public Stocks saveStocksWide(CreateStocksWideDTO createStocksWideDTO)  {

        Wine wine = createStocksWideDTO.getWine();
        Shop shop = shopRepository.findShopByShopCode(createStocksWideDTO.getShopCode());
        Stocks savedStocks = new Stocks(createStocksWideDTO.getShopCode(), shop, wine, createStocksWideDTO.getStockQuantity() , createStocksWideDTO.getWine().getCode());

        return stocksRepository.save(savedStocks);
    }

    @Transactional
    public List<StocksDTO> findAllStocks() {
        return stocksRepository.findAll().stream().map(stocksToDtoMapper).collect(Collectors.toList());
    }

    @Transactional
    public List<StocksWideDTO> findAllStocksWide() {
        return stocksRepository.findAll().stream().map(stocksWideToDtoMapper).collect(Collectors.toList());
    }

    @Transactional
    public List<StocksWideDTO> findStocksWideByWineCountry(String wineCountry) throws Exception {
        return stocksRepository.getStocksByWineCountry(wineCountry).stream().map(stocksWideToDtoMapper).collect(Collectors.toList());
    }


    @Transactional
    public StocksWideDTO findAllStocksWideByCode(Integer code) {
        return stocksRepository.findById(code).map(stocksWideToDtoMapper).get();
    }

    @Transactional
    public List<StocksDTO> findStocksByWineCountry(String wineCountry) throws Exception {
        return stocksRepository.getStocksByWineCountry(wineCountry).stream().map(stocksToDtoMapper).collect(Collectors.toList());
    }

    @Transactional
    public boolean checkStocksExistsByCountry(String wineCountry) {
        return stocksRepository.existsByCountry(wineCountry);
    }

    @Transactional
    public void deleteStock(Integer code) {
        stocksRepository.deleteById(code);
    }


    @Transactional
    public static UpdateStocksDTO updateStocks(Integer id, StocksDTO stocksDTO)  {

        Stocks updatedStocks = stocksRepository.findById(id).get();

        updatedStocks.setStockQuantity(stocksDTO.getStockQuantity());

        stocksRepository.save(updatedStocks);
        return stocksDtoToUpdateStocksDto.apply(stocksDTO);
    }


    @Transactional
    public StocksWideDTO findRecommendationsByCountry(Clients client) throws Exception {
        List<StocksWideDTO> stocksWideDTOList = stocksRepository.getStocksWideByWineCountry(client.getFavouriteCountry()).stream().map(stocksWideToDtoMapper).collect(Collectors.toList());
        for (StocksWideDTO s : stocksWideDTOList){
            System.out.println("StocksWideDTO" + s);
        }

        TreeMap<Double, StocksWideDTO> shopsDestinations = new TreeMap<>();

        for (StocksWideDTO s : stocksWideDTOList) {
            shopsDestinations.put(distanceHaversine(client.getCurrentLatitude(), client.getCurrentLongitude(), s.getShop().getShopLatitude(), s.getShop().getShopLongitude()), s);
        }

        return shopsDestinations.firstEntry().getValue();
    }



    //Haversine Formula
    public static double distanceHaversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;  //Earth radius
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }


}

