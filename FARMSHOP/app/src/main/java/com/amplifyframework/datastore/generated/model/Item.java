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

/** This is an auto generated class representing the Item type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Items")
@Index(name = "byUser", fields = {"userID","name","price","quantity","image"})
public final class Item implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField USER_ID = field("userID");
  public static final QueryField NAME = field("name");
  public static final QueryField PRICE = field("price");
  public static final QueryField QUANTITY = field("quantity");
  public static final QueryField IMAGE = field("image");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String userID;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String", isRequired = true) String price;
  private final @ModelField(targetType="String", isRequired = true) String quantity;
  private final @ModelField(targetType="String", isRequired = true) String image;
  public String getId() {
      return id;
  }
  
  public String getUserId() {
      return userID;
  }
  
  public String getName() {
      return name;
  }
  
  public String getPrice() {
      return price;
  }
  
  public String getQuantity() {
      return quantity;
  }
  
  public String getImage() {
      return image;
  }
  
  private Item(String id, String userID, String name, String price, String quantity, String image) {
    this.id = id;
    this.userID = userID;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.image = image;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Item item = (Item) obj;
      return ObjectsCompat.equals(getId(), item.getId()) &&
              ObjectsCompat.equals(getUserId(), item.getUserId()) &&
              ObjectsCompat.equals(getName(), item.getName()) &&
              ObjectsCompat.equals(getPrice(), item.getPrice()) &&
              ObjectsCompat.equals(getQuantity(), item.getQuantity()) &&
              ObjectsCompat.equals(getImage(), item.getImage());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUserId())
      .append(getName())
      .append(getPrice())
      .append(getQuantity())
      .append(getImage())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Item {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("userID=" + String.valueOf(getUserId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("price=" + String.valueOf(getPrice()) + ", ")
      .append("quantity=" + String.valueOf(getQuantity()) + ", ")
      .append("image=" + String.valueOf(getImage()))
      .append("}")
      .toString();
  }
  
  public static UserIdStep builder() {
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
  public static Item justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Item(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      userID,
      name,
      price,
      quantity,
      image);
  }
  public interface UserIdStep {
    NameStep userId(String userId);
  }
  

  public interface NameStep {
    PriceStep name(String name);
  }
  

  public interface PriceStep {
    QuantityStep price(String price);
  }
  

  public interface QuantityStep {
    ImageStep quantity(String quantity);
  }
  

  public interface ImageStep {
    BuildStep image(String image);
  }
  

  public interface BuildStep {
    Item build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements UserIdStep, NameStep, PriceStep, QuantityStep, ImageStep, BuildStep {
    private String id;
    private String userID;
    private String name;
    private String price;
    private String quantity;
    private String image;
    @Override
     public Item build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Item(
          id,
          userID,
          name,
          price,
          quantity,
          image);
    }
    
    @Override
     public NameStep userId(String userId) {
        Objects.requireNonNull(userId);
        this.userID = userId;
        return this;
    }
    
    @Override
     public PriceStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public QuantityStep price(String price) {
        Objects.requireNonNull(price);
        this.price = price;
        return this;
    }
    
    @Override
     public ImageStep quantity(String quantity) {
        Objects.requireNonNull(quantity);
        this.quantity = quantity;
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
    private CopyOfBuilder(String id, String userId, String name, String price, String quantity, String image) {
      super.id(id);
      super.userId(userId)
        .name(name)
        .price(price)
        .quantity(quantity)
        .image(image);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
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
     public CopyOfBuilder quantity(String quantity) {
      return (CopyOfBuilder) super.quantity(quantity);
    }
    
    @Override
     public CopyOfBuilder image(String image) {
      return (CopyOfBuilder) super.image(image);
    }
  }
  
}
