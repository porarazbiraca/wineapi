package x5.ppln.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@Table(name = "wines", schema = "test")
public class Wine {
    @Id
    @Column(name = "code", nullable = false)
    private Integer code;

    @Column(name = "name")
    private String wineName;
    @Column(name = "color")
    private String wineColor;
    @Column(name = "grape_variety")
    private String wineGrapeVariety;
    @Column(name = "vintage")
    private Integer wineVintage;
    @Column(name = "country")
    private String wineCountry;

    @JsonIgnore
    @OneToMany(mappedBy = "wine")
    private List<Stocks> stocks;


    public Wine(Integer code, String wineName, String wineColor, String wineGrapeVariety, Integer wineVintage, String wineCountry) {
    this.code = code;
    this.wineName = wineName;
    this.wineColor = wineColor;
    this.wineGrapeVariety = wineGrapeVariety;
    this.wineVintage = wineVintage;
    this.wineCountry = wineCountry;
    }
}
