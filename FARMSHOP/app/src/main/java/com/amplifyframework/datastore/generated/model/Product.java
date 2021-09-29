package com.amplifyframework.datastore.generated.model;


import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Product type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Products")
@Index(name = "byFarm", fields = {"farmID","name","price","image"})
public final class Product implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField FARM_ID = field("farmID");
  public static final QueryField NAME = field("name");
  public static final QueryField PRICE = field("price");
  public static final QueryField IMAGE = field("image");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String farmID;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String", isRequired = true) String price;
  private final @ModelField(targetType="String", isRequired = true) String image;
  public String getId() {
      return id;
  }
  
  public String getFarmId() {
      return farmID;
  }
  
  public String getName() {
      return name;
  }
  
  public String getPrice() {
      return price;
  }
  
  public String getImage() {
      return image;
  }
  
  private Product(String id, String farmID, String name, String price, String image) {
    this.id = id;
    this.farmID = farmID;
    this.name = name;
    this.price = price;
    this.image = image;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Product product = (Product) obj;
      return ObjectsCompat.equals(getId(), product.getId()) &&
              ObjectsCompat.equals(getFarmId(), product.getFarmId()) &&
              ObjectsCompat.equals(getName(), product.getName()) &&
              ObjectsCompat.equals(getPrice(), product.getPrice()) &&
              ObjectsCompat.equals(getImage(), product.getImage());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getFarmId())
      .append(getName())
      .append(getPrice())
      .append(getImage())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Product {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("farmID=" + String.valueOf(getFarmId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("price=" + String.valueOf(getPrice()) + ", ")
      .append("image=" + String.valueOf(getImage()))
      .append("}")
      .toString();
  }
  
  public static FarmIdStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Product justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Product(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      farmID,
      name,
      price,
      image);
  }
  public interface FarmIdStep {
    NameStep farmId(String farmId);
  }
  

  public interface NameStep {
    PriceStep name(String name);
  }
  

  public interface PriceStep {
    ImageStep price(String price);
  }
  

  public interface ImageStep {
    BuildStep image(String image);
  }
  

  public interface BuildStep {
    Product build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements FarmIdStep, NameStep, PriceStep, ImageStep, BuildStep {
    private String id;
    private String farmID;
    private String name;
    private String price;
    private String image;
    @Override
     public Product build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Product(
          id,
          farmID,
          name,
          price,
          image);
    }
    
    @Override
     public NameStep farmId(String farmId) {
        Objects.requireNonNull(farmId);
        this.farmID = farmId;
        return this;
    }
    
    @Override
     public PriceStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public ImageStep price(String price) {
        Objects.requireNonNull(price);
        this.price = price;
        return this;
    }
    
    @Override
     public BuildStep image(String image) {
        Objects.requireNonNull(image);
        this.image = image;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String farmId, String name, String price, String image) {
      super.id(id);
      super.farmId(farmId)
        .name(name)
        .price(price)
        .image(image);
    }
    
    @Override
     public CopyOfBuilder farmId(String farmId) {
      return (CopyOfBuilder) super.farmId(farmId);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder price(String price) {
      return (CopyOfBuilder) super.price(price);
    }
    
    @Override
     public CopyOfBuilder image(String image) {
      return (CopyOfBuilder) super.image(image);
    }
  }
  
}
