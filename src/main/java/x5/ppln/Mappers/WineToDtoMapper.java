package x5.ppln.Mappers;
import org.springframework.stereotype.Service;
import x5.ppln.DTO.get.WineDTO;
import x5.ppln.Entities.Wine;
import java.util.function.Function;

@Service
public class WineToDtoMapper implements Function<Wine, WineDTO> {
    @Override
    public WineDTO apply(Wine wine) {
        return new WineDTO(
                wine.getCode(),
                wine.getWineName(),
                wine.getWineColor(),
                wine.getWineCountry(),
                wine.getWineVintage(),
                wine.getWineGrapeVariety()
        );
    }
}
