package com.codetest.fucheng.phonenumbers.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PhoneNumber
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-01-21T13:31:55.399+11:00")




public class PhoneNumber   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("phoneNumber")
  private String phoneNumber = null;

  @JsonProperty("customerId")
  private String customerId = null;

  @JsonProperty("description")
  private String description = null;

  /**
   * the type of phone number
   */
  public enum TypeEnum {
    MOBILE("mobile"),
    
    FIXEDLINE("fixedLine");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    ACTIVED("actived"),
    
    PROCESSING("processing");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("simSerial")
  private String simSerial = null;

  public PhoneNumber id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PhoneNumber phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * phone number
   * @return phoneNumber
  **/
  @ApiModelProperty(example = "61434583679", required = true, value = "phone number")
  @NotNull


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public PhoneNumber customerId(String customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * customer Id
   * @return customerId
  **/
  @ApiModelProperty(example = "customer123", required = true, value = "customer Id")
  @NotNull


  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public PhoneNumber description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "optional description.", value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PhoneNumber type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * the type of phone number
   * @return type
  **/
  @ApiModelProperty(required = true, value = "the type of phone number")
  @NotNull


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public PhoneNumber status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public PhoneNumber simSerial(String simSerial) {
    this.simSerial = simSerial;
    return this;
  }

  /**
   * SIM card number
   * @return simSerial
  **/
  @ApiModelProperty(example = "89-302-720-40000001234-9", value = "SIM card number")


  public String getSimSerial() {
    return simSerial;
  }

  public void setSimSerial(String simSerial) {
    this.simSerial = simSerial;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneNumber phoneNumber = (PhoneNumber) o;
    return Objects.equals(this.id, phoneNumber.id) &&
        Objects.equals(this.phoneNumber, phoneNumber.phoneNumber) &&
        Objects.equals(this.customerId, phoneNumber.customerId) &&
        Objects.equals(this.description, phoneNumber.description) &&
        Objects.equals(this.type, phoneNumber.type) &&
        Objects.equals(this.status, phoneNumber.status) &&
        Objects.equals(this.simSerial, phoneNumber.simSerial);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, phoneNumber, customerId, description, type, status, simSerial);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneNumber {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    simSerial: ").append(toIndentedString(simSerial)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

