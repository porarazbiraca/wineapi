package x5.ppln.DTO.put;

public class UpdateClientsDTO {

    private Integer code;
    private String clientName;
    private Double currentLatitude;
    private Double currentLongitude;
    private String favouriteColor;
    private String favouriteCountry;
    private Integer favouriteVintage;

    public UpdateClientsDTO() {
    }

    public UpdateClientsDTO(Integer code, String clientName, Double currentLatitude, Double currentLongitude, String favouriteColor, String favouriteCountry, Integer favouriteVintage) {
        this.code = code;
        this.clientName = clientName;
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.favouriteColor = favouriteColor;
        this.favouriteCountry = favouriteCountry;
        this.favouriteVintage = favouriteVintage;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(Double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public Double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(Double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public String getFavouriteColor() {
        return favouriteColor;
    }

    public void setFavouriteColor(String favouriteColor) {
        this.favouriteColor = favouriteColor;
    }

    public String getFavouriteCountry() {
        return favouriteCountry;
    }

    public void setFavouriteCountry(String favouriteCountry) {
        this.favouriteCountry = favouriteCountry;
    }

    public Integer getFavouriteVintage() {
        return favouriteVintage;
    }

    public void setFavouriteVintage(Integer favouriteVintage) {
        this.favouriteVintage = favouriteVintage;
    }
}
