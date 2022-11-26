package com.cfg.bm.data.api.model;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cfg.bm.data.api.model.security.Auditory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails, Principal {

    private static final long serialVersionUID = -9044817155487964977L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    @Builder.Default
    private UUID uuid = UUID.randomUUID();

    @Column(unique = true)
    @Getter(onMethod_ = { @Override })
    private String username;

    @Column
    @Getter(onMethod_ = { @Override })
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

//    @Column
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "authority", cascade = CascadeType.ALL, targetEntity = SimpleGrantedAuthority.class)
//    @Getter(onMethod_ = { @Override })
//    private Collection<? extends GrantedAuthority> authorities;

    @OneToMany(mappedBy = "uuid")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Event> events;

    @Column
    @Getter(onMethod_ = { @Override })
    @Builder.Default
    private boolean accountNonExpired = true;

    @Column
    @Getter(onMethod_ = { @Override })
    @Builder.Default
    private boolean accountNonLocked = true;;

    @Column
    @Getter(onMethod_ = { @Override })
    @Builder.Default
    private boolean credentialsNonExpired = true;;

    @Column
    @Getter(onMethod_ = { @Override })
    @Builder.Default
    private boolean enabled = true;;

    @Embedded
    private Auditory auditory;

    @Override
    public String getName() {
	return getUsername();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Login login;

    public Collection<? extends GrantedAuthority> getAuthorities() {
	return List.of(new SimpleGrantedAuthority(getLogin().getLoginUserRole().getRole()));
    }

}
