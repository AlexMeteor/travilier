package models;
import java.util.List;
import javax.persistence.*;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Itinerary extends Model{
	@Id
	public Long id;
	public String name;
	public String description;
	@ManyToOne
	public User users;
	@ManyToMany
	public List<Place> places;

	
	public Itinerary()
	{
	}
	
	public Itinerary(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public static List<Itinerary> findAll() {
		return find().all();
	}
		
	public static Finder<Long, Itinerary> find() {
		return new Finder<Long,Itinerary>(Long.class, Itinerary.class);
	}
	
		
	public static void remove(Long id) {
		 find().ref(id).delete();
	}
	
	public static void add(Itinerary itinerary)
	{
		itinerary.save();
	}
}
