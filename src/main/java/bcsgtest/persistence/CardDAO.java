package bcsgtest.persistence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import bcsgtest.model.*;

/*
 * This is just a stub, which returns some hardcoded values.
 * In reality the card crud operations would go here.
 */
public class CardDAO {

	private ArrayList<Card> cards = new ArrayList<Card>();

	private static CardDAO instance = null;

	private CardDAO(){
		Calendar date = new GregorianCalendar(2017, 10, 1);
		Card c1 = new Card("HSBC Canada","5601-2345-3446-5678",date);
		date = new GregorianCalendar(2017, 9, 1);
		Card c2 = new Card("Royal Bank of  Canada","4519-4532-4524-2456",date);
		date = new GregorianCalendar(2018, 11, 1);
		Card c3 = new Card("American Express","3786-7334-8965-345",date);
		
		cards.add(c1);
		cards.add(c2);
		cards.add(c3);
	}
	
	public static CardDAO getInstance() {
		if (instance == null) {
			instance = new CardDAO();
		}
		return instance;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
	
	/*
	 * In reality this method would return false if the db couldn't insert,
	 * for our purposes it always returns true.
	 */
	public boolean createOrUpdate(Card c){
		//check if the card already exists
		Card existingCard = null;
		for (Card card : cards) {
			if(card.getCode().equals(c.getCode())){
				existingCard = card;
				break;
			}
		}
		//insert if new
		if(existingCard==null){
			cards.add(c);
		}
		//update if existing
		else{
			existingCard.setBank(c.getBank());
			existingCard.setExpiry(c.getExpiry());
		}
		
		return true;
	}
}
