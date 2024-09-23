package x5.ppln.Mappers;

import org.springframework.stereotype.Service;
import x5.ppln.DTO.get.WineDTO;
import x5.ppln.DTO.put.PutWineDTO;
import x5.ppln.DTO.put.UpdateWineDTO;

import java.util.function.Function;

@Service
public class WineDtoToUpdateWineDto implements Function<WineDTO, UpdateWineDTO> {

    @Override
    public UpdateWineDTO apply(WineDTO wineDTO) {
        return new UpdateWineDTO(
                wineDTO.getWineName(),
                wineDTO.getWineColor(),
                wineDTO.getWineCountry(),
                wineDTO.getWineVintage(),
                wineDTO.getWineGrapeVariety()
        );
    }
}

