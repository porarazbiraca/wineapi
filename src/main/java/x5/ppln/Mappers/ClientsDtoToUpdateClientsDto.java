package x5.ppln.Mappers;

import org.springframework.stereotype.Service;
import x5.ppln.DTO.get.ClientsDTO;
import x5.ppln.DTO.put.PutClientsDTO;
import x5.ppln.DTO.put.UpdateClientsDTO;

import java.util.function.Function;

@Service
public class ClientsDtoToUpdateClientsDto implements Function<ClientsDTO, UpdateClientsDTO> {

    @Override
    public UpdateClientsDTO apply(ClientsDTO clientsDTO) {
        return new UpdateClientsDTO(
                clientsDTO.getCode(),
                clientsDTO.getClientName(),
                clientsDTO.getCurrentLatitude(),
                clientsDTO.getCurrentLongitude(),
                clientsDTO.getFavouriteColor(),
                clientsDTO.getFavouriteCountry(),
                clientsDTO.getFavouriteVintage()
        );
    }
}

