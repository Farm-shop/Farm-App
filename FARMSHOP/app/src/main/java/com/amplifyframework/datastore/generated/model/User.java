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

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users")
public final class User implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField USER_SIGN_ID = field("userSignId");
  public static final QueryField NAME = field("name");
  public static final QueryField PHONE = field("phone");
  public static final QueryField LABEL = field("label");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String userSignId;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String", isRequired = true) String phone;
  private final @ModelField(targetType="String", isRequired = true) String label;
  public String getId() {
      return id;
  }
  
  public String getUserSignId() {
      return userSignId;
  }
  
  public String getName() {
      return name;
  }
  
  public String getPhone() {
      return phone;
  }
  
  public String getLabel() {
      return label;
  }
  
  private User(String id, String userSignId, String name, String phone, String label) {
    this.id = id;
    this.userSignId = userSignId;
    this.name = name;
    this.phone = phone;
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
              ObjectsCompat.equals(getUserSignId(), user.getUserSignId()) &&
              ObjectsCompat.equals(getName(), user.getName()) &&
              ObjectsCompat.equals(getPhone(), user.getPhone()) &&
              ObjectsCompat.equals(getLabel(), user.getLabel());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUserSignId())
      .append(getName())
      .append(getPhone())
      .append(getLabel())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("userSignId=" + String.valueOf(getUserSignId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("phone=" + String.valueOf(getPhone()) + ", ")
      .append("label=" + String.valueOf(getLabel()))
      .append("}")
      .toString();
  }
  
  public static UserSignIdStep builder() {
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
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      userSignId,
      name,
      phone,
      label);
  }
  public interface UserSignIdStep {
    NameStep userSignId(String userSignId);
  }
  

  public interface NameStep {
    PhoneStep name(String name);
  }
  

  public interface PhoneStep {
    LabelStep phone(String phone);
  }
  

  public interface LabelStep {
    BuildStep label(String label);
  }
  

  public interface BuildStep {
    User build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements UserSignIdStep, NameStep, PhoneStep, LabelStep, BuildStep {
    private String id;
    private String userSignId;
    private String name;
    private String phone;
    private String label;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          userSignId,
          name,
          phone,
          label);
    }
    
    @Override
     public NameStep userSignId(String userSignId) {
        Objects.requireNonNull(userSignId);
        this.userSignId = userSignId;
        return this;
    }
    
    @Override
     public PhoneStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public LabelStep phone(String phone) {
        Objects.requireNonNull(phone);
        this.phone = phone;
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
    private CopyOfBuilder(String id, String userSignId, String name, String phone, String label) {
      super.id(id);
      super.userSignId(userSignId)
        .name(name)
        .phone(phone)
        .label(label);
    }
    
    @Override
     public CopyOfBuilder userSignId(String userSignId) {
      return (CopyOfBuilder) super.userSignId(userSignId);
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
     public CopyOfBuilder label(String label) {
      return (CopyOfBuilder) super.label(label);
    }
  }
  
}
