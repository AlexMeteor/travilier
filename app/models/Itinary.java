package models;
import java.util.List;
import javax.persistence.*;
import play.db.ebean.Model;

@Entity
public class Itinary {
	@Id
	public Long id;
	public String name;
	public String description;
	@ManyToOne
	public User users;
	@ManyToMany
	public List<Place> places;

}
