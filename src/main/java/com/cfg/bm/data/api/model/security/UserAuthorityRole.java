package com.cfg.bm.data.api.model.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = true)
@Entity
public class UserAuthorityRole implements Serializable {

	private static final long serialVersionUID = -3737973350089904173L;

	public static SequenceGenerator sequenceGeneratorToUserAuthorityRoleId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGeneratorToUserAuthorityRoleId")
	@EqualsAndHashCode.Include
	@ToString.Include
	private long id;
	
	@Column
	@NotNull
	@ToString.Include
	private String authorityRole;
}
