package quiz.game.model.entity;

import quiz.game.DbConsts;

import javax.persistence.*;

@Entity
@Table(name = DbConsts.Role.NAME)
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DbConsts.Role.Columns.ID)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = DbConsts.Role.Columns.NAME, length = 20)
	private ERole name;

	public Role() {
	}

	public Role(ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}
