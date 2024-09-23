package x5.ppln.Test.servicies;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import x5.ppln.DTO.get.StocksDTO;
import x5.ppln.DTO.get.StocksWideDTO;
import x5.ppln.DTO.get.WineDTO;
import x5.ppln.Entities.Clients;
import x5.ppln.Entities.Shop;
import x5.ppln.Entities.Stocks;
import x5.ppln.Entities.Wine;
import x5.ppln.Mappers.StocksToDtoMapper;
import x5.ppln.Mappers.WineToDtoMapper;
import x5.ppln.Repositories.StocksRepository;
import x5.ppln.Repositories.WineRepository;
import x5.ppln.Services.StocksService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import x5.ppln.Services.WineService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StocksServiceTest {

    private double kremlinLatitude = 55.751999;
    private double kremlinLongitude =37.617734;

    private double shukinskayaLatitude =55.80796;
    private double shukinskayaLongitude = 37.46629;

    private double dinamoLatitude =55.78894939519145;
    private double dinamoLongitude = 37.55989896564562;

    private double ohotkaLatitude =55.755784;
    private double ohotkaLongitude = 37.616946;



    @Mock
    private StocksRepository stocksRepository;

//    @Mock
//    private Function<Wine, WineDTO> wineToDtoMapper;

    @InjectMocks
    private StocksService stocksService;


    @Test
    @DisplayName("testDistanceHaversine")
    public void testDistanceHaversine() {

        BigDecimal distanceFromDinamoToSchukinskaya = BigDecimal.valueOf(stocksService.distanceHaversine(dinamoLatitude, dinamoLongitude, shukinskayaLatitude, shukinskayaLongitude));
        BigDecimal distanceFromDinamoToKremlin = BigDecimal.valueOf(stocksService.distanceHaversine(kremlinLatitude, kremlinLongitude, dinamoLatitude, dinamoLongitude));

        assertEquals(BigDecimal.valueOf(6.22), distanceFromDinamoToSchukinskaya.setScale(2, BigDecimal.ROUND_HALF_UP));
        assertEquals(BigDecimal.valueOf(5.47), distanceFromDinamoToKremlin.setScale(2, BigDecimal.ROUND_HALF_UP));

    }


    @Test
    @DisplayName("shouldReturnAllSocolRecommndation")
    void shouldReturnAllSocolRecommndation() throws Exception {
        Wine wine = new Wine(1, "Nice Wine", "red", "Merlo", 1996, "Australia");
        Shop firstShop = new Shop(1, "Kremlin Shop", 55.751999, 37.617734);
        Shop secondShop = new Shop(2, "Socol Shop", 55.8053816621094, 37.5146721303463);
        StocksWideDTO stocksWideFirstShop = new StocksWideDTO(1, firstShop.getShopCode(), 88,  wine.getCode(), wine, firstShop);
        StocksWideDTO stocksWideSecondShop = new StocksWideDTO(2, firstShop.getShopCode(), 2,  wine.getCode(), wine, secondShop);

        Stocks stocksFirstShop = new Stocks(1, firstShop.getShopCode(), 88,  wine.getCode(), wine, firstShop);
        Stocks stocksSecondShop = new Stocks(2, firstShop.getShopCode(), 2,  wine.getCode(), wine, secondShop);

        List<Stocks> testStocksWideDTOList = Arrays.asList(stocksFirstShop, stocksSecondShop);


        Clients client = new Clients(22, "Ivan Ivanich na Dinamo", 55.78894939519145, 37.55989896564562, "red", "Australia", 2020);
        Mockito.when(stocksRepository.getStocksWideByWineCountry(client.getFavouriteCountry())).thenReturn(testStocksWideDTOList)   ;


        assertEquals(stocksWideFirstShop, stocksService.findRecommendationsByCountry(client));


        }





}
