package com.cfg.bm.data.api.model;

import java.security.Principal;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.userdetails.UserDetails;

import com.cfg.bm.data.api.model.security.Auditory;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User implements UserDetails, Principal {

    private static final long serialVersionUID = -9044817155487964977L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGeneratorToUserId")
    private long id;

    @Column(nullable = false, unique = true)
    private UUID uuid = UUID.randomUUID();

    @Column(unique = true)
    @Getter(onMethod_ = { @Override })
    private String username;

    @Column
    @Getter(onMethod_ = { @Override })
    private String password;

    @Column
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authority", cascade = CascadeType.ALL)

    @Getter(onMethod_ = { @Override })
    private Collection<UserAuthority> authorities;

    @OneToMany(mappedBy = "uuid")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Event> events;

    @Column
    @Getter(onMethod_ = { @Override })
    private boolean accountNonExpired = true;

    @Column
    @Getter(onMethod_ = { @Override })
    private boolean accountNonLocked = true;;

    @Column
    @Getter(onMethod_ = { @Override })
    private boolean credentialsNonExpired = true;;

    @Column
    @Getter(onMethod_ = { @Override })
    private boolean enabled = true;;

    @Embedded
    private Auditory auditory;

    @Override
    public String getName() {
	return getUsername();
    }

}
