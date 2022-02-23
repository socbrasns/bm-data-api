package com.cfg.bm.data.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Form implements Serializable {

	private static final long serialVersionUID = -4653703250913981079L;

	public static SequenceGenerator sequenceGeneratorToFormId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGeneratorToFormId")
	@EqualsAndHashCode.Include
	@ToString.Include
	private long id;

	@Column
	@ToString.Include
	private String version;

	@Column
	@ToString.Include
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Hability> habilities;
}
