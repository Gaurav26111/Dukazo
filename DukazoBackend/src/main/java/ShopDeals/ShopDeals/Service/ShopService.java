package ShopDeals.ShopDeals.Service;

//package ShopDeals.ShopDeals.Service;

import ShopDeals.ShopDeals.Entity.ShopEntity;
import ShopDeals.ShopDeals.Repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    // ✅ Save a new shop or update existing
    public ShopEntity saveShop(ShopEntity shopEntity) {
        return shopRepository.save(shopEntity);
    }

    // ✅ Get all shops
    public List<ShopEntity> getAllShops() {
        return shopRepository.findAll();
    }

    // ✅ Get shop by ID
    public ShopEntity getShopById(String id) {
        Optional<ShopEntity> shop = shopRepository.findById(id);
        return shop.orElse(null); // return null if not found (can improve with exception)
    }

    // ✅ Delete shop by ID
    public void deleteShop(String id) {
        shopRepository.deleteById(id);
    }
}
