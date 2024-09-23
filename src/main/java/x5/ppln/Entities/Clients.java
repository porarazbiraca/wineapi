package x5.ppln.Entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@Table(name = "clients", schema = "test")
public class Clients {
    @Id
    @Column(name = "code", nullable = false)
    private Integer code;
    @Column(name = "name")
    private String clientName;
    @Column(name = "current_latitude")
    private Double currentLatitude;
    @Column(name = "current_longitude")
    private Double currentLongitude;
    @Column(name = "favourite_color")
    private String favouriteColor;
    @Column(name = "favourite_country")
    private String favouriteCountry;
    @Column(name = "favourite_vintage")
    private Integer favouriteVintage;

}
