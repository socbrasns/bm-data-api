package com.cfg.bm.data.api.model.security;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = true)
@Entity
public class User implements UserDetails, Login {

	private static final long serialVersionUID = -9044817155487964977L;

	public enum Role {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGeneratorToUserId")
	@EqualsAndHashCode.Include
	@ToString.Include
	private long id;

	@Column
	@NotNull
	@ToString.Include
	@Getter(onMethod_ = { @Override })
	private String username;

	@Column
	@NotNull
	@Getter(onMethod_ = { @Override })
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@Nullable
	@Getter(onMethod_ = { @Override })
	private Collection<UserAuthority> authorities;

//	@ManyToMany(fetch = FetchType.EAGER)
//	private Set<Perfil> perfis;

	@Column
	@Getter(onMethod_ = { @Override })
	private boolean accountNonExpired;

	@Column
	@Getter(onMethod_ = { @Override })
	private boolean accountNonLocked;

	@Column
	@Getter(onMethod_ = { @Override })
	private boolean credentialsNonExpired;

	@Column
	@ToString.Include
	@Getter(onMethod_ = { @Override })
	private boolean enabled;

}
