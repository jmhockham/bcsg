package bcsgtest.model;


import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Future;

public class Card {
	
	@NotNull
	@Size(max=50)
	private String bank;
	
	@NotNull
	@Size(min=19,max=19)
	private String code;
	
	@NotNull
	@Future
	private Date expiry;

	public Card(String bank, String code, Calendar expiry) {
		this.bank = bank;
		this.code = code;
		this.expiry = expiry.getTime();
	}

	public Card(String bank, String code, Date expiry) {
		this.bank = bank;
		this.code = code;
		this.expiry = expiry;
	}

	public Card() {
		// TODO Auto-generated constructor stub
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	@Override
	public String toString() {
		return "Card [bank=" + bank + ", code=" + code + ", expiry=" + expiry + "]";
	}
	
}
