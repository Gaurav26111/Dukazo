package ShopDeals.ShopDeals.Controller;

import ShopDeals.ShopDeals.Entity.ShopEntity;
import ShopDeals.ShopDeals.Service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/shops")
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend to access APIs
public class ShopController {

    @Autowired
    private ShopService shopService;

    // ✅ Create new shop with file upload support
    @PostMapping(consumes = "multipart/form-data")
    public ShopEntity saveShop(
            @RequestParam("shopName") String shopName,
            @RequestParam("shopType") String shopType,
            @RequestParam("shopKeeperName") String shopKeeperName,
            @RequestParam("gst") String gst,
            @RequestParam("openingHour") String openingHour,
            @RequestParam("closingHour") String closingHour,
            @RequestParam("address") String address,
            @RequestParam("state") String state,
            @RequestParam("district") String district,
            @RequestParam("pinCode") String pinCode,
            @RequestParam("mobileNumber") String mobileNumber,
            @RequestParam("email") String email,
            @RequestParam("passsword") String password,
            @RequestParam("adharCardNumber") String adharCardNumber,
            @RequestParam("panCardNumber") String panCardNumber,
            @RequestParam(value = "panCard", required = false) MultipartFile panCard,
            @RequestParam(value = "adharCard", required = false) MultipartFile adharCard,
            @RequestParam(value = "shopImage", required = false) MultipartFile shopImage,
            @RequestParam(value = "shopKeeperImage", required = false) MultipartFile shopKeeperImage
    ) throws IOException {

        ShopEntity shop = new ShopEntity();
        shop.setShopName(shopName);
        shop.setShopType(shopType);
        shop.setShopKeeperName(shopKeeperName);
        shop.setGst(gst);
        shop.setOpeningHour(openingHour);
        shop.setClosingHour(closingHour);
        shop.setAddress(address);
        shop.setState(state);
        shop.setDistrict(district);
        shop.setPinCode(pinCode);
        shop.setMobileNumber(mobileNumber);
        shop.setEmail(email);
        shop.setPassword(password);
        shop.setAdharCardNumber(adharCardNumber);
        shop.setPanCardNumber(panCardNumber);

        if (panCard != null) {
            shop.setPanCardFile(panCard.getBytes());
        }

        if (adharCard != null) {
            shop.setAdharCardFile(adharCard.getBytes());
        }

        if (shopImage != null) {
            shop.setShopImage(shopImage.getBytes());
        }

        if (shopKeeperImage != null) {
            shop.setShopKeeperImage(shopKeeperImage.getBytes());
        }

        return shopService.saveShop(shop);
    }

    // ✅ Get all shops
    @GetMapping
    public List<ShopEntity> getAllShops() {
        return shopService.getAllShops();
    }

    // ✅ Get shop by ID
    @GetMapping("/{id}")
    public ShopEntity getShopById(@PathVariable String id) {
        return shopService.getShopById(id);
    }

    // ✅ Update shop (supports JSON only for now)
    @PutMapping("/{id}")
    public ShopEntity updateShop(@PathVariable String id, @RequestBody ShopEntity shopEntity) {
        shopEntity.setId(id); // ensure correct ID
        return shopService.saveShop(shopEntity);
    }

    // ✅ Delete shop
    @DeleteMapping("/{id}")
    public String deleteShop(@PathVariable String id) {
        shopService.deleteShop(id);
        return "Shop deleted successfully with id: " + id;
    }
}

