package com.cfg.bm.data.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.cfg.bm.data.api.model.enums.Country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Place implements Serializable {

    private static final long serialVersionUID = -9117393993964073208L;

    @Column(nullable = false)
    private Country country;

    @Column(nullable = false)
    private String location;
}
