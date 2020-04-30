package quiz.game.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quiz.game.DbConsts;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(	name = DbConsts.User.NAME,
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username")
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DbConsts.User.Columns.ID)
	private Long id;

	@NotBlank
	@Size(max = 20)
	@Column(name = DbConsts.User.Columns.USERNAME)
	private String username;

	@NotBlank
	@Size(max = 120)
	@Column(name = DbConsts.User.Columns.PASSWORD)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;

	}
}
