package bcsgtest.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bcsgtest.model.Card;
import bcsgtest.persistence.CardDAO;

@Controller
public class ManualController {

    @RequestMapping(value="/createCard", method=RequestMethod.POST)
    public String postCreateCard(@Valid @ModelAttribute Card card, BindingResult bindingResult, ModelMap model) {
    	if (!bindingResult.hasErrors()) {
			CardDAO cardDao = CardDAO.getInstance();
			cardDao.createOrUpdate(card);
			model.addAttribute("message", "new card created");
			return "forward:/main";
		}
    	else{
    		return "manual";
    	}
	}

    @RequestMapping(value="/createCardsFromCsv", method=RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file){
    	//normally there would be more checks on the file contents; because this is 
    	//only a small exercise they've been omitted.
        if (!file.isEmpty()) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
                String line = "";
                CardDAO cardDao = CardDAO.getInstance();
                while((line=br.readLine())!=null){
                	String[] csvDataArr = line.split(",");
                	String bank = csvDataArr[0];
                	String code = csvDataArr[1];
                	String expiryStr = csvDataArr[2];
                	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
                	Calendar expiryDate = new GregorianCalendar();
                	expiryDate.setTime(dateFormat.parse(expiryStr));
                	Card c = new Card(bank,code,expiryDate);
                	cardDao.createOrUpdate(c);
                }
            } catch (Exception e) {
            }
        } else {
            return "manual";
        }
        return "forward:/main";
    }

}