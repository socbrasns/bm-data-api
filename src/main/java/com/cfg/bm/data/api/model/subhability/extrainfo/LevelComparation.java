package com.cfg.bm.data.api.model.subhability.extrainfo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.cfg.bm.data.api.model.subhability.SubHability;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true, includeFieldNames = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LevelComparation extends SubHability implements Serializable {

	private static final long serialVersionUID = -7678991004458488192L;

	@Column
	@ToString.Include
	private BigDecimal upToLevelDiference;


}
