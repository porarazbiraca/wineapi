package x5.ppln.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Objects;


@Entity
@Table(name = "stocks", schema = "test")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Stocks {


    @Id
    @SequenceGenerator(name = "stocks_code_seq",
            sequenceName = "stocks_sequence",
            initialValue = 1, allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stocks_code_seq")
    @Column(name = "id", nullable = false)
    @NotNull
    private Integer id;


    @Column(name = "shop_code")
    private Integer shopCode;

    @Column(name = "wine_code")
    private Integer wineCode;

    @Column(name = "quantity")
    @Min(value = 0, message = "The amount of stocks should be 0 or bigger")
    private Integer stockQuantity;

    @JsonIgnore
    @ManyToOne(targetEntity = Wine.class, fetch=FetchType.LAZY, optional=true, cascade=CascadeType.ALL)
    @JoinColumn(name = "wine_code", insertable=false, updatable=false)
    private Wine wine;

    @JsonIgnore
    @ManyToOne(targetEntity = Shop.class,  fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_code", referencedColumnName = "code", insertable=false, updatable=false)
    private Shop shop;



    public Stocks(Integer shopCode, Shop shop, Wine wine, Integer stockQuantity, Integer wineCode) {
        this.shopCode = shopCode;
        this.shop = shop;
        this.stockQuantity = stockQuantity;
        this.wine = wine;
        this.wineCode = wineCode;
    }

    @SequenceGenerator(name = "stocks_code_seq",
            sequenceName = "stocks_sequence",
            initialValue = 1, allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stocks_code_seq")
    public Integer getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stocks stocks = (Stocks) o;
        return Objects.equals(id, stocks.id) && Objects.equals(shopCode, stocks.shopCode) && Objects.equals(wineCode, stocks.wineCode) && Objects.equals(stockQuantity, stocks.stockQuantity) && Objects.equals(wine, stocks.wine) && Objects.equals(shop, stocks.shop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shopCode, wineCode, stockQuantity, wine, shop);
    }
}
