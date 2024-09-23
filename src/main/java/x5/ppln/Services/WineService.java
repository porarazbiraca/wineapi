package x5.ppln.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import x5.ppln.DTO.get.WineDTO;
import x5.ppln.DTO.put.UpdateWineDTO;
import x5.ppln.Entities.Wine;
import x5.ppln.Mappers.WineDtoToUpdateWineDto;
import x5.ppln.Mappers.WineToDtoMapper;
import x5.ppln.Repositories.WineRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WineService {

        private static WineRepository wineRepository;
        private WineToDtoMapper wineToDtoMapper;
        private static WineDtoToUpdateWineDto wineDtoToUpdateWineDto;

    @Autowired
        public WineService(WineRepository wineRepository, WineToDtoMapper wineToDtoMapper, WineDtoToUpdateWineDto wineDtoToUpdateWineDto) {
            this.wineRepository = wineRepository;
            this.wineToDtoMapper = wineToDtoMapper;
            this.wineDtoToUpdateWineDto = wineDtoToUpdateWineDto;
        }


    @Transactional(readOnly = true)
    public WineDTO findWineByCode(Integer code) {

        return wineRepository.findById(code)
                .map(wineToDtoMapper)
                .get();
    }

    @Transactional
    public Wine saveWine(Wine newWine) {

        Wine savedWine = new Wine(newWine.getCode(), newWine.getWineName(),
                newWine.getWineColor(), newWine.getWineGrapeVariety(),  newWine.getWineVintage(), newWine.getWineCountry()
                );
        return wineRepository.save(savedWine);
    }

    @Transactional
    public boolean checkWineExists(Integer code) {
        return wineRepository.existsByCode(code);
    }


    @Transactional
    public void deleteWine(Integer code) {
        wineRepository.deleteById(code);
    }

    @Transactional(readOnly = true)
    public List<WineDTO> findAllWines() {
        return wineRepository.findAll().stream().map(wineToDtoMapper).collect(Collectors.toList());
    }


    @Transactional
    public static UpdateWineDTO updateWine(Integer code, WineDTO wineDTO)  {

        Wine updatedWine = wineRepository.findById(code).get();


        updatedWine.setCode(code);
        updatedWine.setWineName(wineDTO.getWineName());
        updatedWine.setWineColor(wineDTO.getWineColor());
        updatedWine.setWineCountry(wineDTO.getWineCountry());
        updatedWine.setWineVintage(wineDTO.getWineVintage());
        updatedWine.setWineGrapeVariety(wineDTO.getWineGrapeVariety());

        wineRepository.save(updatedWine);
        return wineDtoToUpdateWineDto.apply(wineDTO);
    }




    }

