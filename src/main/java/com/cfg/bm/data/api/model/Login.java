package com.cfg.bm.data.api.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.cfg.bm.data.api.model.enums.LoginUserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Login implements Serializable {

    private static final long serialVersionUID = 7110298951389039898L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGeneratorToFormId")
    private Long id;

    @Transient
    private String username;

    @Transient
    private String password;

    @Transient
    private boolean rememberme;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private LoginUserRole role = LoginUserRole.GUEST;

    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    @Column
    private final Calendar lastLoginDate = Calendar.getInstance();

    @ManyToOne(optional = false)
    @JoinColumn(name = "User_UUID", referencedColumnName = "uuid")
    private User logedUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Calendar tokenIssuedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Calendar tokenExpirationDate;

}
