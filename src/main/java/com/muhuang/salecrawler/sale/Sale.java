package com.muhuang.salecrawler.sale;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.muhuang.salecrawler.item.Item;
import com.muhuang.salecrawler.shared.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sale extends BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;

    private int incrementalSellCount;

    private int number;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "out_item_id")
    @JsonIgnoreProperties({"saleList"})
    private Item item;
}
