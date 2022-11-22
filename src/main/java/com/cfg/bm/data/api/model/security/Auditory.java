package com.cfg.bm.data.api.model.security;

import java.io.Serializable;
import java.security.Principal;
import java.util.Calendar;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cfg.bm.data.api.model.User;
import com.cfg.bm.data.api.repository.UserRepository;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class Auditory implements Serializable {

    private static final long serialVersionUID = 5680375350945475960L;

    @Transient
    private UserRepository userRepository;

    @Autowired
    public Auditory(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Calendar lastAlterationDate;

    @Column
    private User lastAlterationUser;

    @PrePersist
    public void prePersist() {
	lastAlterationDate = Calendar.getInstance();

	Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
		.filter(auth -> auth.isAuthenticated()).map(auth -> auth.getPrincipal()).map(Principal.class::cast)
		.map(Principal::getName).map(userRepository::findByUsername).filter(Optional::isPresent)
		.map(Optional::get).ifPresentOrElse(u -> setLastAlterationUser(u), () -> setLastAlterationUser(null));

    }
}
