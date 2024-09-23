package x5.ppln.DTO.get;

public class WineDTO {
    private Integer code;
    private String wineName;
    private String wineColor;
    private String wineGrapeVariety;
    private Integer wineVintage;
    private String wineCountry;

    public WineDTO(){}

    public WineDTO(Integer code, String wineName, String wineColor, String wineGrapeVariety, Integer wineVintage, String wineCountry) {
        this.code = code;
        this.wineName = wineName;
        this.wineColor = wineColor;
        this.wineGrapeVariety = wineGrapeVariety;
        this.wineVintage = wineVintage;
        this.wineCountry = wineCountry;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public String getWineColor() {
        return wineColor;
    }

    public void setWineColor(String wineColor) {
        this.wineColor = wineColor;
    }

    public String getWineGrapeVariety() {
        return wineGrapeVariety;
    }

    public void setWineGrapeVariety(String wineGrapeVariety) {
        this.wineGrapeVariety = wineGrapeVariety;
    }

    public Integer getWineVintage() {
        return wineVintage;
    }

    public void setWineVintage(Integer wineVintage) {
        this.wineVintage = wineVintage;
    }

    public String getWineCountry() {
        return wineCountry;
    }

    public void setWineCountry(String wineCountry) {
        this.wineCountry = wineCountry;
    }
}
