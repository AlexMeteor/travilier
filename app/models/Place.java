package models;


import java.util.List;
import javax.persistence.*;

import play.db.ebean.Model;

@Entity
public class Place extends Model{
	
	@Id
	public Long id;
	public String name;
	public String description;
	public String type;
	public double Latitude;
	public double longtitude;
	
	@ManyToMany(mappedBy="places")
	public List<Itinary> itinarys;
	
	public Place()
	{
	}
	
	public Place(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public String toString() {
		return String.format("%s - %s", id, name);
	
	}
	
	public void copy(Place temp)
	{
		this.id = temp.id;
		this.name = temp.name;
		this.description = temp.description;
		this.type = temp.type;
		this.Latitude = temp.Latitude;
		this.longtitude = temp.longtitude;
	}
	
	public static List<Place> findAll() {
		return find().all();
	}
		
	public static Finder<Long, Place> find() {
		return new Finder<Long,Place>(Long.class, Place.class);
	}
	
	/**
	public static Set<Place> findByName(String term) {
		final Set<Place> results = new HashSet<Place>();
		for (Place candidate : products) {
			if(candidate.name.toLowerCase().contains(term.toLowerCase())){
				results.add(candidate);
			}
		}
		
		return results;
	}*/
		
	public static void remove(Long id) {
		 find().ref(id).delete();
	}
	
	public static void add(Place place)
	{
		place.save();
	}
	
	/**
	public void save() {
		products.remove(findByEan(this.ean));
		products.add(this);
	}
	**/
	
}
