package com.cluster.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "idea")
public class idea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 200)
	@Column
	private String name;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column()
	private Date lastupdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column()
	private Date begindate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column()
	private Date expiredate;

	@Column()
	@NotNull
	private Boolean onsite;

	@Column()
	@NotNull
	private Long duration;

	public idea(Long id, @NotBlank @Size(max = 200) String name, @NotNull Date createdate, Date lastupdate,
			Date begindate, Date expiredate, @NotNull Long duration) {
		super();
		this.id = id;
		this.name = name;
		this.createdate = createdate;
		this.lastupdate = lastupdate;
		this.begindate = begindate;
		this.expiredate = expiredate;
		this.duration = duration;
	}

}
