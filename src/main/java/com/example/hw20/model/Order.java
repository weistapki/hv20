package com.example.hw20.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("orders")
public class Order implements Serializable {
    @Id
    private long id;
    private LocalDate date;
    private int cost;
    //    private List<Product> products;
    @MappedCollection(idColumn = "id")
    private Product product;

}
