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
public class CoachingPoint implements Serializable {

	
	private static final long serialVersionUID = -1834671132053348238L;

	@Column
	private String value;

}
