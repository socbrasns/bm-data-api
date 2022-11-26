package com.cfg.bm.data.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = true)
@Entity
public class Hability implements Serializable {

    private static final long serialVersionUID = -1337010680790951007L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column
    @ToString.Include
    private String name;

    @Column
    @ToString.Include
    private BigDecimal startScaleValue;

    @Column
    @ToString.Include
    private Long numberOfElementsInScale;

    @Column
    @ToString.Include
    private BigDecimal stepIncrementScaleValue;

    @Column
    @ToString.Include
    private BigDecimal percentualStartLevel;

    @Column
    @Builder.Default
    private boolean enabled = true;

}
