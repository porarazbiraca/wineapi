package x5.ppln.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "shops", schema = "test")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Shop {

    @Id
    @Column(name = "code", nullable = false)
    private Integer shopCode;
    @Column(name = "name")
    private String shopName;
    @Column(name = "shop_latitude")
    private Double shopLatitude;
    @Column(name = "shop_longitude")
    private Double shopLongitude;

}
