package com.bank;

import java.util.List;

public interface ClientAccountService {
	
	public String addAccount(PersonalDetails accounts);
	
	public String deleteAccount(int accountnum);
	
	public List<PersonalDetails> getAccount(int userid);
}
