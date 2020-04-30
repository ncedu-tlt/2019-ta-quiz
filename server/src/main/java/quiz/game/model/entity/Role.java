package quiz.game.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quiz.game.DbConsts;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
