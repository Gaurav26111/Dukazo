package ShopDeals.ShopDeals.Entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Document(collection = "shops")
public class ShopEntity {

    @Id
    private String id;

    private String shopName;
    private String shopType;
    private String shopKeeperName;
    private String gst;

    private String openingHour;
    private String closingHour;

    private String address;
    private String state;
    private String district;
    private String pinCode;

    private String mobileNumber;
    private String email;
    private String password;

    private String adharCardNumber;
    private String panCardNumber;

    private byte[] panCardFile;
    private byte[] adharCardFile;
    private byte[] shopImage;
    private byte[] shopKeeperImage;

    @DBRef
    private ArrayList<ItemEntity> item = new ArrayList<>();
}
