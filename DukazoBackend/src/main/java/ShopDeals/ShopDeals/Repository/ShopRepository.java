package ShopDeals.ShopDeals.Repository;

import ShopDeals.ShopDeals.Entity.ShopEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopRepository extends MongoRepository<ShopEntity, String> {
}
