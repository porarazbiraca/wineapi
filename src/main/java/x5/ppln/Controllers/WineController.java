package x5.ppln.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import x5.ppln.DTO.get.WineDTO;
import x5.ppln.DTO.put.PutWineDTO;
import x5.ppln.DTO.put.UpdateWineDTO;
import x5.ppln.Entities.Wine;
import x5.ppln.Services.WineService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name="Контроллер вин", description="Контроллер позволяет управлять винами")
public class WineController {

    private final WineService wineService;

    @Autowired
    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping("/wines/{code}")
    @Operation(
            summary = "Поиск вина по коду",
            description = "Позволяет искать вино по коду"
    )
    public ResponseEntity findByWineCode(@PathVariable Integer code) {
        if (!wineService.checkWineExists(code)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wine with code " + code + " not found");
        }

            WineDTO wine = wineService.findWineByCode(code);
            return ResponseEntity.ok(wine);
    }

    @GetMapping("/wines")
    @Operation(
            summary = "Поиск всех вин",
            description = "Позволяет получить все вина"
    )
    public ResponseEntity<List<WineDTO>> findAllWines() {
        List<WineDTO> wines = wineService.findAllWines();
        return ResponseEntity.ok(wines);
    }

    @PostMapping(path = "/wines", consumes = "application/json")
    @Operation(
            summary = "Добавление вина",
            description = "Позволяет добавить вино"
    )
    public ResponseEntity addWine(@RequestBody Wine newWine) throws Exception {
        if (wineService.checkWineExists(newWine.getCode())){
            return new ResponseEntity(
                    "Wine is already exists!!!",
                    HttpStatus.CONFLICT);
        }

        wineService.saveWine(newWine);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/wines/{code}")
    @Operation(
            summary = "Удаление вина",
            description = "Позволяет удалять вино по коду"
    )
    public ResponseEntity deleteWineByCode(@PathVariable Integer code) {
        if (!wineService.checkWineExists(code)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wine with code " + code + " not found");
        }

            wineService.deleteWine(code);
            return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/wines/{code}")
    @Operation(
            summary = "Обновление вина",
            description = "Позволяет обновлять вино по коду"
    )
    public ResponseEntity updateWine(@PathVariable Integer code, @RequestBody WineDTO wineDTO) {

        if (!wineService.checkWineExists(code)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wine with code " + code + " not found");
        }
            UpdateWineDTO updateWineDTO = WineService.updateWine(code, wineDTO);
            return ResponseEntity.ok(updateWineDTO);
    }

}
