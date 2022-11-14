package com.cfg.bm.data.api.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cfg.bm.data.api.model.security.Auditory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = true)
@Entity
public class Event implements Serializable {

    private static final long serialVersionUID = -4653703250913981079L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGeneratorToEventId")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(nullable = false, unique = true)
    @ToString.Include
    private UUID uuid = UUID.randomUUID();

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    @ToString.Include
    private Calendar date;

    @Column(nullable = false)
    @ToString.Include
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column
    private List<Form> forms;

    @Column
    private boolean enabled = true;;

    @Embedded
    private Auditory auditory;
}