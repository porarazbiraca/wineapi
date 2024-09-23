package x5.ppln.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import x5.ppln.DTO.get.ClientsDTO;
import x5.ppln.DTO.put.UpdateClientsDTO;
import x5.ppln.Entities.Clients;
import x5.ppln.Mappers.ClientsDtoToUpdateClientsDto;
import x5.ppln.Mappers.ClientsToDtoMapper;
import x5.ppln.Repositories.ClientsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientsService {

        private static ClientsRepository clientsRepository;
        private ClientsToDtoMapper clientsToDtoMapper;
        private static ClientsDtoToUpdateClientsDto clientsDtoToUpdateClientsDto;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository, ClientsToDtoMapper clientsToDtoMapper, ClientsDtoToUpdateClientsDto clientsDtoToUpdateClientsDto) {
        this.clientsRepository = clientsRepository;
        this.clientsToDtoMapper = clientsToDtoMapper;
        this.clientsDtoToUpdateClientsDto = clientsDtoToUpdateClientsDto;
    }

    @Transactional(readOnly = true)
    public ClientsDTO findClientDTOByCode(Integer code) {

        return clientsRepository.findById(code)
                .map(clientsToDtoMapper)
                .get();
    }

    @Transactional(readOnly = true)
     public Clients findClientByCode(Integer code){
        return clientsRepository.findClientsByCode(code);
    }

    @Transactional
    public ResponseEntity saveClient(Clients newClient) {

        if(clientsRepository.existsByCode(newClient.getCode())) {

            return new ResponseEntity(
                    "The client with code=" + clientsRepository.existsByCode(newClient.getCode()) + " already exists !!!",
                    HttpStatus.CONFLICT);

        }

        Clients savedClient = new Clients(newClient.getCode(), newClient.getClientName(),
                newClient.getCurrentLatitude(), newClient.getCurrentLongitude(),  newClient.getFavouriteColor(), newClient.getFavouriteCountry(), newClient.getFavouriteVintage()
        );
        clientsRepository.save(savedClient);

        return new ResponseEntity("Client added successfully", HttpStatus.OK);

    }

    @Transactional
    public boolean checkClientExistsByCode(Integer code) {
        return clientsRepository.existsByCode(code);
    }


    @Transactional
    public void deleteClientByCode(Integer code) {
        clientsRepository.deleteById(code);
    }

    public List<ClientsDTO> findAllClients() {
        return clientsRepository.findAll().stream().map(clientsToDtoMapper).collect(Collectors.toList());
    }


    @Transactional
    public static UpdateClientsDTO updateClient(Integer code, ClientsDTO clientsDTO)  {

        Clients updatedClients = clientsRepository.findById(code).get();

        updatedClients.setCode(clientsDTO.getCode());
        updatedClients.setClientName(clientsDTO.getClientName());
        updatedClients.setCurrentLatitude(clientsDTO.getCurrentLatitude());
        updatedClients.setCurrentLongitude(clientsDTO.getCurrentLongitude());
        updatedClients.setFavouriteColor(clientsDTO.getFavouriteColor());
        updatedClients.setFavouriteCountry(clientsDTO.getFavouriteCountry());
        updatedClients.setFavouriteVintage(clientsDTO.getFavouriteVintage());

        clientsRepository.save(updatedClients);
        return clientsDtoToUpdateClientsDto.apply(clientsDTO);
    }




    }

