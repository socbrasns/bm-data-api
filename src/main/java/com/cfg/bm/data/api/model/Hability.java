package com.cfg.bm.data.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

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
public class Hability implements Serializable {

	private static final long serialVersionUID = -1337010680790951007L;

	public static SequenceGenerator sequenceGeneratorToHabilityId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGeneratorToHabilityId")
	@EqualsAndHashCode.Include
	@ToString.Include
	private long id;

	@Column
	@ToString.Include
	private String name;

	@Column
	@ToString.Include
	private BigDecimal startScaleValue;

	@Column
	@ToString.Include
	private BigDecimal endScaleValue;

	@Column
	@ToString.Include
	private BigDecimal percentualStartLevel;

//	@ToString.Include
//	public BigDecimal getScaleStartValue() {
//		return endScaleValue.subtract(startScaleValue).multiply(percentualStartLevel).add(startScaleValue);
//	}

}
