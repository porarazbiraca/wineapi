package x5.ppln.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import x5.ppln.Entities.Shop;
import x5.ppln.Entities.Stocks;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer>{
    @Transactional(readOnly = true)
    Shop findShopByShopCode(Integer shopCode);

    @Transactional(readOnly = true)
    boolean existsByShopCode(Integer shopCode);

//    @Transactional(readOnly = true)
//    @Query("select sh from Shops sh " +
//            "left join fetch Stocks st " +
//            "ON w.code = st.shopCode " +
//            "where w.wineCountry = ?1"
//    )
//    List<Stocks> getShopsByWineCountry(String wineCountry) throws Exception;
}
