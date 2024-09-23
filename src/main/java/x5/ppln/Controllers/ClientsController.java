package x5.ppln.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import x5.ppln.DTO.get.ClientsDTO;
import x5.ppln.DTO.put.PutClientsDTO;
import x5.ppln.DTO.put.UpdateClientsDTO;
import x5.ppln.Entities.Clients;
import x5.ppln.Services.ClientsService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name="Контроллер клиентов", description="Контроллер позволяет управлять клиентами")
public class ClientsController {

    private final ClientsService clientsService;

    @Autowired
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }


    @GetMapping("/clients")
    @Operation(
            summary = "Поиск всех клиентов",
            description = "Позволяет искать всех клиентов"
    )
    public ResponseEntity<List<ClientsDTO>> findAllClients() {
        List<ClientsDTO> clients = clientsService.findAllClients();
        return ResponseEntity.ok(clients);
    }


    @GetMapping("/clients/{code}")
    @Operation(
            summary = "Поиск клиента по коду",
            description = "Позволяет искать клиента по коду"
    )
    public ResponseEntity findClientByCode(@PathVariable Integer code) {
        if (!clientsService.checkClientExistsByCode(code)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client with code " + code + " not found");
        }

            ClientsDTO client = clientsService.findClientDTOByCode(code);
            return ResponseEntity.ok(client);
    }



    @PostMapping(path = "/clients", consumes = "application/json")
    @Operation(
            summary = "Добавление клиента",
            description = "Добавление нового клиента"
    )
    public ResponseEntity addClient(@RequestBody @Parameter(description = "Новый клиент") Clients newClient) throws Exception {
        if (clientsService.checkClientExistsByCode(newClient.getCode())){
            return new ResponseEntity(
                    "Client is already exists!!!",
                    HttpStatus.CONFLICT);
        }

        clientsService.saveClient(newClient);

        return new ResponseEntity(HttpStatus.OK);

    }


    @DeleteMapping("/clients/{code}")
    @Operation(
            summary = "Удаление клиента",
            description = "Удаление клиента по коду"
    )
    public ResponseEntity deleteClientByCode(@PathVariable Integer code) {
        if(!clientsService.checkClientExistsByCode(code)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client with code " + code + " not found");
        }

        clientsService.deleteClientByCode(code);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("clients/{code}")
    @Operation(
            summary = "Обновление клиента",
            description = "Обновление клиента по коду"
    )
    public ResponseEntity updateClient(@PathVariable Integer code, @RequestBody ClientsDTO clientsDTO) {
        if(!clientsService.checkClientExistsByCode(code)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client with code " + code + " not found");
        }

        UpdateClientsDTO updateClientsDTO = ClientsService.updateClient(code, clientsDTO);
        return new ResponseEntity( HttpStatus.OK);
        }



}
