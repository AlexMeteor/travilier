package controllers;
import models.Itinerary;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.itinerary_view.*;


public class Itineraries extends Controller {
	
	private static final Form<Itinerary> itineraryForm = Form.form(Itinerary.class);	

	public static Result list() {
		return ok( list.render(Itinerary.findAll()));
	}
	
	public static Result newItinerary() {
		return ok( detail.render(itineraryForm));
	}
	
	public static Result details(Long id) {
		Itinerary itinerary = Itinerary.find().byId(id);
		 if (itinerary == null)
			 return notFound(String.format("Product %s does not exist.", id));
		
		 Form<Itinerary> filledForm = itineraryForm.fill(itinerary);
			 return ok( detail.render(filledForm));
		 
	}
	
	public static Result save() {
		Form<Itinerary> boundForm = itineraryForm.bindFromRequest();
		if(boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(detail.render(boundForm));
		}
		Itinerary itinerary = boundForm.get();
		System.out.println( "Itinary id " + itinerary.id + "Itinerary name " + itinerary.name);
		itinerary.save();
		flash("success", String.format("Successfully added product %s", itinerary));
		return redirect(routes.Itineraries.list());
	}
	
	public static Result remove(Long id)
	{
		Itinerary.remove(id);
		return redirect(routes.Itineraries.list());
	}
}

