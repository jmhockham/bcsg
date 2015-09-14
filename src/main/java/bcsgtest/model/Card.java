package bcsgtest.model;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Future;

public class Card {

	@NotNull
	@Size(max = 50)
	private String bank;

	@NotNull
	@Size(min = 17, max = 19)
	private String code;

	@NotNull
	@Future
	private Date expiry;

	private String codeObfuscated;

	public Card(String bank, String code, Calendar expiry) {
		this.bank = bank;
		this.code = code;
		this.expiry = expiry.getTime();
		setCodeObfuscated(code);
	}

	public Card(String bank, String code, Date expiry) {
		this.bank = bank;
		this.code = code;
		this.expiry = expiry;
		setCodeObfuscated(code);
	}

	public Card() {
		//blank constructor, necessary for spring
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
		setCodeObfuscated(code);
	}

	public String getCodeObfuscated() {
		return codeObfuscated;
	}

	//get an obfuscated version of the card long number
	private void setCodeObfuscated(String code) {
		String codeStr = code;
		String codeStart = codeStr.substring(0, codeStr.indexOf("-"));
		String codeRemainder = codeStr.substring(codeStr.indexOf("-"), codeStr.length());
		codeRemainder = codeRemainder.replaceAll("[0-9]", "*");
		codeStr = codeStart + codeRemainder;
		codeObfuscated = codeStr;
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
