package com.cfg.bm.data.api.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.cfg.bm.data.api.model.elements.CoachingPoint;
import com.cfg.bm.data.api.model.elements.KeyWord;
import com.cfg.bm.data.api.model.subhability.extrainfo.LevelComparation;
import com.sun.istack.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = true)
@Entity
public class Session implements Serializable {

    private static final long serialVersionUID = -7233996720427856911L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column
    @NotNull
    @ToString.Include
    private Form form;

    @Column(nullable = false)
    @ToString.Include
    private User benchmarker;

    @Column
    @ToString.Include
    private User metaCoach;

    @Column
    @ToString.Include
    private User coachee;

    @OneToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ToString.Include
    private List<User> metaPerson;

    // campo destinado a restringir acesso das metaPersons participantes da sessão
    // ao conteúdo da mesma sessão
    @OneToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> restrictedAccessData;

    @Column
    @ToString.Include
    private Date startDate;

    @Column
    @ToString.Include
    private Date endtDate;

    @OneToMany
    @JoinColumn(name = "keyWord_id")
    @ToString.Include
    private Collection<KeyWord> keyWords;

    @OneToMany
    @JoinColumn(name = "coachingPoint_id")
    @ToString.Include
    private Collection<CoachingPoint> coachingPoints;

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "sub_hability_id"))
    @ToString.Include
    private List<LevelComparation> showedLevelComparationSubHabilities;

    @ManyToOne
    @JoinColumn(name = "event_id", insertable = false, updatable = false, nullable = false)
    private Event event;

    @Column
    @Builder.Default
    private boolean enabled = true;

}
