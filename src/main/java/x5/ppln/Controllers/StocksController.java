package x5.ppln.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import x5.ppln.DTO.get.StocksDTO;
import x5.ppln.DTO.get.StocksWideDTO;
import x5.ppln.DTO.post.CreateStocksDTO;
import x5.ppln.DTO.post.CreateStocksWideDTO;
import x5.ppln.DTO.put.PutStocksDTO;
import x5.ppln.DTO.put.UpdateStocksDTO;
import x5.ppln.Entities.Clients;
import x5.ppln.Repositories.StocksRepository;
import x5.ppln.Services.ClientsService;
import x5.ppln.Services.StocksService;
import x5.ppln.Services.WineService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name="Контроллер остатков", description="Контроллер позволяет управлять остатками вин в магазинах")
public class StocksController {

    private final StocksService stocksService;
    private final WineService wineService;
    private final StocksRepository stocksRepository;
    private final ClientsService clientsService;

    @Autowired
    public StocksController(StocksService stocksService, WineService wineService, StocksRepository stocksRepository, ClientsService clientsService) {
        this.stocksService = stocksService;
        this.wineService = wineService;
        this.stocksRepository = stocksRepository;
        this.clientsService = clientsService;
    }


    @GetMapping("/stocks")
    @Operation(
            summary = "Поиск всех остатков",
            description = "Позволяет искать остатки по всем магазинам"
    )
    public ResponseEntity<List<StocksDTO>> findAllStocks() {
        List<StocksDTO> stocks = stocksService.findAllStocks();
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/stocks/country/{wineCountry}")
    @Operation(
            summary = "Поиск остатков по стране",
            description = "Позволяет искать остатки по названию страны производства вина"
    )
    public ResponseEntity findStocksByWineCountry(@PathVariable @Parameter(description = "Название страны") String wineCountry) throws Exception {
        if(stocksService.checkStocksExistsByCountry(wineCountry)){
            List<StocksDTO> stocks = stocksService.findStocksByWineCountry(wineCountry);
            return ResponseEntity.ok(stocks);
        }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stocks with country " + wineCountry + " not found");
    }

    @GetMapping("/stocks/wide")
    @Operation(
            summary = "Расширенный поиск остатков",
            description = "Позволяет искать остатки с детализацированной информацией по вину"
    )
    public ResponseEntity<List<StocksWideDTO>> findAllStocksWide() {
        List<StocksWideDTO> stocks = stocksService.findAllStocksWide();
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/stocks/{code}")
    @Operation(
            summary = "Расширенный поиск остатков по коду",
            description = "Позволяет искать остатки по коду остатка"
    )
    public ResponseEntity<StocksDTO> findAllStocksByCode(@PathVariable @Parameter(description = "Идентификатор остатка") Integer code) {
        StocksDTO stock = stocksService.findStocksById(code);
        return ResponseEntity.ok(stock);
    }

    @GetMapping("/stocks/wide/{code}")
    @Operation(
            summary = "Расширенный поиск остатков по коду",
            description = "Позволяет искать остатки по коду остатка"
    )
    public ResponseEntity<StocksWideDTO> findAllStocksWideByCode(@PathVariable @Parameter(description = "Идентификатор остатка") Integer code) {
        StocksWideDTO stock = stocksService.findAllStocksWideByCode(code);
        return ResponseEntity.ok(stock);
    }

    @GetMapping("/stocks/wide/country/{wineCountry}")
    @Operation(
            summary = "Расширенный поиск остатков по стране",
            description = "Позволяет искать остатки по названию страны производства вина"
    )
    public ResponseEntity findStocksWideByWineCountry(@PathVariable @Parameter(description = "Название страны") String wineCountry) throws Exception {
        if(stocksService.checkStocksExistsByCountry(wineCountry)){
            List<StocksWideDTO> stocks = stocksService.findStocksWideByWineCountry(wineCountry);
            return ResponseEntity.ok(stocks);
        }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stocks wide with country " + wineCountry + " not found");

    }



    @PostMapping(path = "/stocks", consumes = "application/json")
    @Operation(
            summary = "Добавление остатка",
            description = "Добавление нового остатка вина в магазине"
    )
    public ResponseEntity addStocks(@RequestBody @Parameter(description = "Остаток вина в магазине") CreateStocksDTO createStocksDTO) throws Exception {

        if (!wineService.checkWineExists(createStocksDTO.getWineCode())){
            return new ResponseEntity("The wine with code=" + createStocksDTO.getWineCode() + " doesn't exists", HttpStatus.CONFLICT);
        }

        if(stocksRepository.existsByWineCodeAndShopCode(createStocksDTO.getWineCode(), createStocksDTO.getShopCode())) {
            return new ResponseEntity("The stock with shopCode=" + createStocksDTO.getWineCode() + " and wineCode=" + createStocksDTO.getShopCode() + " already exists !!!",HttpStatus.CONFLICT);
        }

        stocksService.saveStocks(createStocksDTO);
        return new ResponseEntity("Stocks added successfully", HttpStatus.OK);
    }




    @PostMapping(path = "/stocks/wide", consumes = "application/json")
    @Operation(
            summary = "Расширенное добавление остатка",
            description = "Добавление нового остатка вина в магазине с возможностью добавить новое вино"
    )
    public ResponseEntity addStocksWide(@RequestBody @Parameter(description = "Остаток вина в магазине с новым вином") CreateStocksWideDTO createStocksWideDTO) throws Exception {

        if(stocksRepository.existsByWineCodeAndShopCode(createStocksWideDTO.getWine().getCode(), createStocksWideDTO.getShopCode())) {
            return new ResponseEntity(
                    "The stock with shopCode=" + createStocksWideDTO.getWine().getCode() + " and wineCode=" + createStocksWideDTO.getShopCode() + " already exists !!!",
                    HttpStatus.CONFLICT);
        }

        stocksService.saveStocksWide(createStocksWideDTO);
        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("/stocks/{code}")
    @Operation(
            summary = "Удаление остатка",
            description = "Удаление остатков по коду остатка"
    )
    public ResponseEntity deleteStocksByCode(@PathVariable @Parameter(description = "Код остатка") Integer code) {

        if(!stocksRepository.existsById(code)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock with code " + code + " not found");
        }

            stocksService.deleteStock(code);
            return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/stocks/{code}")
    @Operation(
            summary = "Обновление остатка",
            description = "Обновление остатков по коду остатка"
    )
    public ResponseEntity updateStocks(@PathVariable @Parameter(description = "Код остатка") Integer code, @RequestBody StocksDTO stocksDTO) {
        if(!stocksRepository.existsById(code)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock with code " + code + " not found");
        }

            UpdateStocksDTO updateStocksDTO = StocksService.updateStocks(code, stocksDTO);
            return ResponseEntity.ok(updateStocksDTO);
    }



    @GetMapping("/stocks/recommendations/country/{clientCode}")
    @Operation(
            summary = "Рекоммендации по стране",
            description = "Позволяет формировать рекомендации для клиентов для поиска ближайших магазинов по предпочтениям клиента"
    )
    public ResponseEntity findRecommendationsByCountry(@PathVariable @Parameter(description = "Код клиента") Integer clientCode) throws Exception {
        if(!clientsService.checkClientExistsByCode(clientCode)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client with code " + clientCode + " not found");
        }

        Clients client = clientsService.findClientByCode(clientCode);
        StocksWideDTO stocksWideDTO = stocksService.findRecommendationsByCountry(client);
        return ResponseEntity.ok(stocksWideDTO);
    }

}
