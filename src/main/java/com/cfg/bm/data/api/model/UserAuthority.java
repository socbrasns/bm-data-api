package com.cfg.bm.data.api.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = true)
@Entity
public class UserAuthority implements GrantedAuthority {

    private static final long serialVersionUID = -6826442541543933719L;

    @Value("${jwt.claims-prefix}")
    private static String jwtClaimPrefix;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String authority;

    public Claims toClaims() {
	return new DefaultClaims(Map.of(jwtClaimPrefix.concat(String.valueOf(this.getId())), this.getAuthority()));
    }
}