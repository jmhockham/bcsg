package bcsgtest.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bcsgtest.model.Card;
import bcsgtest.persistence.CardDAO;

@Controller
public class MainController {

	@RequestMapping(value = {"/","/main"}, method = {RequestMethod.GET,RequestMethod.POST})
	public String getMain(ModelMap model) {

		CardDAO cardDAO = CardDAO.getInstance();

		ArrayList<Card> cardList = cardDAO.getCards();
		
		//obfuscate card long number
		for (Card card : cardList) {
			String code = card.getCode();
			String codeStart = code.substring(0,code.indexOf("-"));
			String codeRemainder = code.substring(code.indexOf("-"),code.length());
			codeRemainder=codeRemainder.replaceAll("[0-9]", "*");
			code = codeStart + codeRemainder;
			card.setCode(code);
		}
		
		//sort with by expiry date
		Collections.sort(cardList, new ExpiryDateComparator());
		
		model.addAttribute("cardList", cardList);
		return "main";

	}

	class ExpiryDateComparator implements Comparator<Card> {

		@Override
		public int compare(Card c1, Card c2) {
			long firstDate = c1.getExpiry().getTime();
			long secondDate = c2.getExpiry().getTime();
			return firstDate > secondDate ? 1 : firstDate < secondDate ? -1 : 0;
		}

	}

	@RequestMapping(value = "/manual", method = RequestMethod.GET)
	public String getManual(ModelMap model) {

		Card card = new Card();
		card.setBank("Test Bank");
		card.setCode("1234-5678-9012-345");
		Calendar expiry = new GregorianCalendar(2011, 2, 1);
		card.setExpiry(expiry.getTime());
		model.addAttribute("card", card);
		return "manual";

	}
	
}
