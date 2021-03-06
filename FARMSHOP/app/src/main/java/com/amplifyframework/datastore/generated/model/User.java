package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;

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

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users")
@Index(name = "byProduct", fields = {"productID","name","phone","longitude","latitude","label"})
public final class User implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField PRODUCT_ID = field("productID");
  public static final QueryField NAME = field("name");
  public static final QueryField PHONE = field("phone");
  public static final QueryField LONGITUDE = field("longitude");
  public static final QueryField LATITUDE = field("latitude");
  public static final QueryField LABEL = field("label");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String productID;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String", isRequired = true) String phone;
  private final @ModelField(targetType="String", isRequired = true) String longitude;
  private final @ModelField(targetType="String", isRequired = true) String latitude;
  private final @ModelField(targetType="String", isRequired = true) String label;
  private final @ModelField(targetType="Farm") @HasMany(associatedWith = "userID", type = Farm.class) List<Farm> farms = null;
  public String getId() {
      return id;
  }
  
  public String getProductId() {
      return productID;
  }
  
  public String getName() {
      return name;
  }
  
  public String getPhone() {
      return phone;
  }
  
  public String getLongitude() {
      return longitude;
  }
  
  public String getLatitude() {
      return latitude;
  }
  
  public String getLabel() {
      return label;
  }
  
  public List<Farm> getFarms() {
      return farms;
  }
  
  private User(String id, String productID, String name, String phone, String longitude, String latitude, String label) {
    this.id = id;
    this.productID = productID;
    this.name = name;
    this.phone = phone;
    this.longitude = longitude;
    this.latitude = latitude;
    this.label = label;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getProductId(), user.getProductId()) &&
              ObjectsCompat.equals(getName(), user.getName()) &&
              ObjectsCompat.equals(getPhone(), user.getPhone()) &&
              ObjectsCompat.equals(getLongitude(), user.getLongitude()) &&
              ObjectsCompat.equals(getLatitude(), user.getLatitude()) &&
              ObjectsCompat.equals(getLabel(), user.getLabel());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getProductId())
      .append(getName())
      .append(getPhone())
      .append(getLongitude())
      .append(getLatitude())
      .append(getLabel())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("productID=" + String.valueOf(getProductId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("phone=" + String.valueOf(getPhone()) + ", ")
      .append("longitude=" + String.valueOf(getLongitude()) + ", ")
      .append("latitude=" + String.valueOf(getLatitude()) + ", ")
      .append("label=" + String.valueOf(getLabel()))
      .append("}")
      .toString();
  }
  
  public static ProductIdStep builder() {
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
  public static User justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new User(
      id,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      productID,
      name,
      phone,
      longitude,
      latitude,
      label);
  }
  public interface ProductIdStep {
    NameStep productId(String productId);
  }
  

  public interface NameStep {
    PhoneStep name(String name);
  }
  

  public interface PhoneStep {
    LongitudeStep phone(String phone);
  }
  

  public interface LongitudeStep {
    LatitudeStep longitude(String longitude);
  }
  

  public interface LatitudeStep {
    LabelStep latitude(String latitude);
  }
  

  public interface LabelStep {
    BuildStep label(String label);
  }
  

  public interface BuildStep {
    User build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements ProductIdStep, NameStep, PhoneStep, LongitudeStep, LatitudeStep, LabelStep, BuildStep {
    private String id;
    private String productID;
    private String name;
    private String phone;
    private String longitude;
    private String latitude;
    private String label;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          productID,
          name,
          phone,
          longitude,
          latitude,
          label);
    }
    
    @Override
     public NameStep productId(String productId) {
        Objects.requireNonNull(productId);
        this.productID = productId;
        return this;
    }
    
    @Override
     public PhoneStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public LongitudeStep phone(String phone) {
        Objects.requireNonNull(phone);
        this.phone = phone;
        return this;
    }
    
    @Override
     public LatitudeStep longitude(String longitude) {
        Objects.requireNonNull(longitude);
        this.longitude = longitude;
        return this;
    }
    
    @Override
     public LabelStep latitude(String latitude) {
        Objects.requireNonNull(latitude);
        this.latitude = latitude;
        return this;
    }
    
    @Override
     public BuildStep label(String label) {
        Objects.requireNonNull(label);
        this.label = label;
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
    private CopyOfBuilder(String id, String productId, String name, String phone, String longitude, String latitude, String label) {
      super.id(id);
      super.productId(productId)
        .name(name)
        .phone(phone)
        .longitude(longitude)
        .latitude(latitude)
        .label(label);
    }
    
    @Override
     public CopyOfBuilder productId(String productId) {
      return (CopyOfBuilder) super.productId(productId);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder phone(String phone) {
      return (CopyOfBuilder) super.phone(phone);
    }
    
    @Override
     public CopyOfBuilder longitude(String longitude) {
      return (CopyOfBuilder) super.longitude(longitude);
    }
    
    @Override
     public CopyOfBuilder latitude(String latitude) {
      return (CopyOfBuilder) super.latitude(latitude);
    }
    
    @Override
     public CopyOfBuilder label(String label) {
      return (CopyOfBuilder) super.label(label);
    }
  }
  
}
