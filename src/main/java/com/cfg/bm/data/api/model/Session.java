package com.cfg.bm.data.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.cfg.bm.data.api.model.elements.KeyWord;
import com.cfg.bm.data.api.model.security.User;
import com.cfg.bm.data.api.model.subhability.extrainfo.LevelComparation;
import com.sun.istack.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = true)
@Entity
public class Session implements Serializable {

	private static final long serialVersionUID = -7233996720427856911L;

	public static SequenceGenerator sequenceGeneratorToSessionId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGeneratorToSessionId")
	@EqualsAndHashCode.Include
	@ToString.Include
	private Long id;

	@Column
	@NotNull
	@ToString.Include
	private Form form;

	@Column
	@NotNull
	@ToString.Include
	private User benchmarker;

	@Column
	@ToString.Include
	private User metaCoach;

	@Column
	@ToString.Include
	private User coachee;

	@OneToMany
	@JoinTable(inverseJoinColumns = @JoinColumn(name = "user_id"))
	@ToString.Include
	private List<User> metaPerson;

	@Column
	@ToString.Include
	private Date startDate;

	@Column
	@ToString.Include
	private Date endtDate;

	@ElementCollection()
	@CollectionTable(name = "session_key_words", joinColumns = @JoinColumn(name = "session_id"), foreignKey = @ForeignKey(name = "session_key_word_fk"))
	@ToString.Include
	private List<KeyWord> keyWords;

//	@ElementCollection()
//	@CollectionTable(name = "session_coaching_points", joinColumns = @JoinColumn(name = "session_id"), foreignKey = @ForeignKey(name = "session_coaching_points_fk"))
//	private List<String> thingsNotNotHeard;

	@ManyToMany
	@JoinTable(inverseJoinColumns = @JoinColumn(name = "sub_hability_id"))
	@ToString.Include
	private List<LevelComparation> showedLevelComparationSubHabilities;

}
