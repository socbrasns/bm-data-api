package com.cfg.bm.data.api.model.subhability.extrainfo;

import java.io.Serializable;

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
public class Markable extends SubHability implements Serializable {

	private static final long serialVersionUID = 9079328570466989272L;

	@Column
	@ToString.Include
	private int needs;

	@Column
	@ToString.Include
	private int max;

}
