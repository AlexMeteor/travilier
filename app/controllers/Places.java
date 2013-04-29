package controllers;
import models.Place;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Places extends Controller {
	
	private static final Form<Place> productForm = Form.form(Place.class);
	
	public static Result list() {
		return ok(list.render(Place.findAll()));
	}
	
	public static Result newPlace() {
		return ok(details.render(productForm));
	}
	
	public static Result details(Long id) {
		 Place place = Place.find().byId(id);
		 if (place == null)
			 return notFound(String.format("Product %s does not exist.", id));
		
		 Form<Place> filledForm = productForm.fill(place);
			 return ok(details.render(filledForm));
		 
	}
	
	public static Result save() {
		Form<Place> boundForm = productForm.bindFromRequest();
		if(boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(details.render(boundForm));
		}
		Place product = boundForm.get();
		product.save();
		flash("success", String.format("Successfully added product %s", product));
		return redirect(routes.Places.list());
	}
	
	public static Result remove(Long id)
	{
		Place.remove(id);
		return redirect(routes.Places.list());
	}
}