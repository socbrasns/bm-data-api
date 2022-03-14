package com.cfg.bm.data.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

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
public class HabilityLevelMap implements Serializable {

	private static final long serialVersionUID = -8934017433277389379L;

	public static SequenceGenerator sequenceGeneratorToFormId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGeneratorToHabilityLevelMapId")
	@EqualsAndHashCode.Include
	@ToString.Include
	private Long id;

	@Column
	@ToString.Include
	@NotNull
	private BigDecimal value;
	
	@Column
	@ToString.Include
	private Boolean benchmarkLevel = Boolean.FALSE;

	@Column
	@ToString.Include
	private Boolean habilityStartLevel = Boolean.FALSE;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	@ToString.Include
	private Hability hability;
}
