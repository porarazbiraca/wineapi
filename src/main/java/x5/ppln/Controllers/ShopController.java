package x5.ppln.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import x5.ppln.DTO.get.ShopDTO;
import x5.ppln.DTO.put.PutShopDTO;
import x5.ppln.DTO.put.UpdateShopDTO;
import x5.ppln.Entities.Shop;
import x5.ppln.Services.ShopService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name="Контроллер магазинов", description="Контроллер позволяет управлять магазинами")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;

    }


    @GetMapping("/shop")
    @Operation(
            summary = "Поиск всех магазинов",
            description = "Позволяет искать все магазины"
    )
    public ResponseEntity<List<ShopDTO>> findAllShops() {
        List<ShopDTO> shops = shopService.findAllShops();
        return ResponseEntity.ok(shops);
    }


    @GetMapping("/shop/{code}")
    @Operation(
            summary = "Поиск магазина по коду",
            description = "Позволяет искать магазин по коду"
    )
    public ResponseEntity findShopByCode(@PathVariable Integer code) {
        if(!shopService.checkShopExists(code)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop with code " + code + " not found");
        }

        ShopDTO shop = shopService.findShopByCode(code);
        return ResponseEntity.ok(shop);
    }



    @PostMapping(path = "/shop", consumes = "application/json")
    @Operation(
            summary = "Добавление магазина",
            description = "Добавление нового магазина"
    )
    public ResponseEntity addShop(@RequestBody @Parameter(description = "Новый магазин") Shop newShop) throws Exception {
        if (shopService.checkShopExists(newShop.getShopCode())){
            return new ResponseEntity<>(
                    "Shop is already exists!!!",
                    HttpStatus.CONFLICT);
        }

        shopService.saveShop(newShop);
        return new ResponseEntity<String>(HttpStatus.OK);

    }


    @DeleteMapping("/shop/{code}")
    @Operation(
            summary = "Удаление магазина",
            description = "Удаление магазина по коду"
    )
    public ResponseEntity deleteWineByCode(@PathVariable Integer code) {
        if(!shopService.checkShopExists(code)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop with code " + code + " not found");
        }
            shopService.deleteShop(code);
            return new ResponseEntity(HttpStatus.OK);

    }

    @PutMapping("shop/{code}")
    @Operation(
            summary = "Обновление магазина",
            description = "Обновление магазина по коду"
    )
    public ResponseEntity updateShop(@PathVariable Integer code, @RequestBody ShopDTO shopDTO) {
if(!shopService.checkShopExists(code)) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop with code " + code + " not found");
    }

        UpdateShopDTO updateShopDTO = ShopService.updateShop(code, shopDTO);
        return new ResponseEntity(HttpStatus.OK);
    }



}
