package ca.sheridancollege.nguye482.midterm.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.sheridancollege.nguye482.midterm.database.DatabaseAccess;
import ca.sheridancollege.nguye482.midterm.beans.Delivery;
import ca.sheridancollege.nguye482.midterm.beans.Purchase;
/**
 * Controller
 * 
 * @author Hoang Chuong Nguyen
 *
 */

@Controller
public class MainController {
	
	// access database
	@Autowired
	private DatabaseAccess da;
	
	@RequestMapping(value={"/","/index.htm","/index.html","/index"}, method = RequestMethod.GET)
    public String homePage(Model model)
    {	
		// create Object Purchase and assign it to purchase String
		model.addAttribute("purchase", new Purchase());
		// create a Delivery list to store the data from database
		List<Delivery> delivery = da.getDelivery();
		// assign value from database to String
		model.addAttribute("deliveries",delivery);
        return "index.html";
    }
	
	@RequestMapping(value={"/purchase","/purchase.html"},method = RequestMethod.POST)
	public String purchase(Model model,@ModelAttribute Purchase purchase) {
		// add data from input to database via Object variable
		model.addAttribute("addPurchase", da.addPurchase(purchase));
		// assign value from database to String
		model.addAttribute("purchases", da.getPurchase());
		List<Delivery> delivery = da.getDelivery();
		// assign value from database to String
		model.addAttribute("deliveries",delivery);
		
	    return "output.html";
	}
}
