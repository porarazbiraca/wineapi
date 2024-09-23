package x5.ppln.Mappers;

import org.springframework.stereotype.Service;
import x5.ppln.DTO.get.ClientsDTO;
import x5.ppln.Entities.Clients;

import java.util.function.Function;

@Service
public class ClientsToDtoMapper implements Function<Clients, ClientsDTO> {
    @Override
    public ClientsDTO apply(Clients clients) {
        return new ClientsDTO(
                clients.getCode(),
                clients.getClientName(),
                clients.getCurrentLatitude(),
                clients.getCurrentLongitude(),
                clients.getFavouriteColor(),
                clients.getFavouriteCountry(),
                clients.getFavouriteVintage()
        );
    }
}
