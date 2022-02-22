package com.cfg.bm.data.api.model.elements;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class KeyWord implements Serializable {

	private static final long serialVersionUID = 2521714424547022441L;

	@Column
	private String value;

	@Column
	private boolean repeated;

	@Column
	private boolean clarified;

}
