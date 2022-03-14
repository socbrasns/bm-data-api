package com.cfg.bm.data.api.model.subhability;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import com.cfg.bm.data.api.model.Hability;
import com.cfg.bm.data.api.model.HabilityLevelMap;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = true)
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@MappedSuperclass
public abstract class SubHability implements Serializable {

	private static final long serialVersionUID = -1326108586524290337L;

	public static SequenceGenerator sequenceGeneratorToSubHabilityId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGeneratorToSubHabilityId")
	@EqualsAndHashCode.Include
	@ToString.Include
	private long id;

	@Column
	@ToString.Include
	private String name;

	@Column
	@ToString.Include
	private BigDecimal value;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	@ToString.Include
	private Hability hability;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	@ToString.Include
	private HabilityLevelMap habilityLevel;
}
