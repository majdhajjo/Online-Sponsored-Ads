package org.criteoexam.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "product",schema = "ads_schema")
public class Product implements Serializable {
    @Id
    @Column(name = "product_serial_number")
    private String productSerialNumber;
    private String title;
    private String category;
    private BigDecimal price;
}
