package ShopDeals.ShopDeals.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.yaml.snakeyaml.events.Event;
@Getter
@Setter
@Document(collection = "shoes")
public class Shoes extends ItemEntity{
    @Id
    private String id;
    private String model;
    private String brand;
    private String category;
    private String type;

    private String size;
    private String material;
    private String soleMaterial;
    private String closureType;
    private String weight;
    private String heelHeight;
    private String shape;
    private double rating;
}
