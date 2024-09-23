package x5.ppln.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import x5.ppln.Entities.Shop;
import x5.ppln.Entities.Stocks;
import x5.ppln.Entities.Wine;

import java.util.List;
import java.util.Optional;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Integer>{
    @Transactional(readOnly = true)
    Wine findStocksByWineCode(Integer code);

    @Transactional(readOnly = true)
    List<Stocks> findStocksByShop(Shop shop);

//    @Transactional(readOnly = true)
//    boolean existsByWineCodeAndShop(Integer wineCode, Shop shop);
//
    @Transactional(readOnly = true)
    boolean existsByWineCodeAndShopCode(Integer wineCode, Integer shopCode);

    @Transactional(readOnly = true)
    @Query("select s from Stocks s " +
            " join FETCH Wine w " +
            "ON w.code = s.wineCode " +
            "where w.wineCountry = ?1"
            )
    List<Stocks> getStocksByWineCountry(String wineCountry) throws Exception;


    @Transactional(readOnly = true)
    @Query("select s from Stocks s " +
            "join FETCH s.shop sh " +
            "join FETCH s.wine w " +
            "where w.wineCountry = ?1"
    )
    List<Stocks> getStocksWideByWineCountry(String wineCountry) throws Exception;


    @Transactional(readOnly = true)
    @Query("select exists(select 1 from Stocks s " +
            "left join Wine w " +
            "ON w.code = s.wineCode " +
            "where w.wineCountry = ?1)"
    )
    boolean existsByCountry(String wineCountry);
}
