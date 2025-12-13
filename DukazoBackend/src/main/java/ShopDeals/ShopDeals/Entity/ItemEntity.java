package ShopDeals.ShopDeals.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;

public class ItemEntity {
    @Id
    private String id;
    private String itemName;
    private String itemCategory;
    private String itemBrand;
    private String itemDescription;

    private String itemMRP;
    private String itemDiscount;
    private String itemPrice;
    private String itemQuantity;

    private byte[] itemImage;
    private byte[] itemImage1;
    private byte[] itemImage2;
    private byte[] itemImage3;
    private byte[] itemImage4;
}
