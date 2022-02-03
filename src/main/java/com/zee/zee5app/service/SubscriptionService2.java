package com.zee.zee5app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.dto.Subscriptions;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidIdLengthException;

public interface SubscriptionService2 
{
	public String addSub(Subscriptions sub);
	public String updateSub(Integer id, String packCountry, String status) throws IdNotFoundException, InvalidIdLengthException, InvalidAmountException;
	public Optional<Subscriptions> getSubById(Integer id) throws IdNotFoundException, InvalidIdLengthException, InvalidAmountException;
	public Optional<List<Subscriptions>> getSubs() throws InvalidIdLengthException, InvalidAmountException;
	public String deleteSubById(Integer id) throws IdNotFoundException;

}
