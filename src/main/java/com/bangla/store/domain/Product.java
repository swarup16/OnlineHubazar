package com.bangla.store.domain;

import com.bangla.store.validator.ProductId;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.persistence.*;

@XmlRootElement
@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 3570930334140807514L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 4, max = 50, message = "{Size.Product.name.validation}")
    private String name;

    @Pattern(regexp = "P[0-9]+", message = "{Pattern.Product.productId.validation}")
    @ProductId
    private String productId;

    @Min(value = 0, message = "{Min.Product.unitPrice.validation}")
    @Digits(integer = 8, fraction = 2, message = "{Digits.Product.unitPrice.validation}")
    @NotNull(message = "{NotNull.Product.unitPrice.validation}")
    private BigDecimal unitPrice;
    private String description;
    private String manufacturer;
    //private String category;
    private long unitsInStock;
    private long unitsInOrder;
    private boolean discontinued;
    @Column(name = "condt")
    private String condt;

    public String getCondt() {
        return condt;
    }

    public void setCondt(String condt) {
        this.condt = condt;
    }

    @ManyToOne  @JsonIgnore
    @JoinColumn(name = "categoryId")
    private Category category;

    public int getId() {
        return id;
    }

    /*public Category getCategoryId() {
     return category;
     }*/
    public void setId(int id) {
        this.id = id;
    }

    /*public void setCategoryId(Category categoryId) {
     this.category = categoryId;
     }*/
    @XmlTransient  @JsonIgnore
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonIgnore
    @Transient
    private MultipartFile productImage;

    @XmlTransient
    public MultipartFile getProductImage() {
        return productImage;
    }

    public void setProductImage(MultipartFile productImage) {
        this.productImage = productImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
    public long getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(long unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public long getUnitsInOrder() {
        return unitsInOrder;
    }

    public void setUnitsInOrder(long unitsInOrder) {
        this.unitsInOrder = unitsInOrder;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public Product() {
        super();
    }

    public Product(String productId, String name, BigDecimal unitPrice) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    // add setters and getters for all the fields here
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        if (productId == null) {
            if (other.productId != null) {
                return false;
            }
        } else if (!productId.equals(other.productId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((productId == null) ? 0 : productId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + name + "]";
    }
}
