package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Subscriptions;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.SubscriptionRepository;
//import com.zee.zee5app.repository.SubscriptionRepository2;
//import com.zee.zee5app.repository.impl.SubscriptionRepositoryImpl;
import com.zee.zee5app.service.SubscriptionService2;


@Service
public class SubscriptionServiceImpl implements SubscriptionService2 
{
	
	@Autowired
	private SubscriptionRepository subRepo;
	
	@Override
	public String addSub(Subscriptions sub) 
	{
		//return this.subRepo.addSub(sub);
		Subscriptions sub2 = subRepo.save(sub);
		if(sub2!=null)
		{
			return "success";
		}
		else
		{
			return "fail";
		}
	}

	@Override
	public String updateSub(Integer id, String packCountry, String status)
			throws IdNotFoundException, InvalidIdLengthException, InvalidAmountException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Subscriptions> getSubById(Integer id)
			throws IdNotFoundException, InvalidIdLengthException, InvalidAmountException 
	{
		// TODO Auto-generated method stub
		return subRepo.findById(id);
	}

	@Override
	public Optional<List<Subscriptions>> getSubs() throws InvalidIdLengthException, InvalidAmountException 
	{
		// TODO Auto-generated method stub
		return Optional.ofNullable(subRepo.findAll());
	}

	@Override
	public String deleteSubById(Integer id) throws IdNotFoundException 
	{
		// TODO Auto-generated method stub
		
		try {
			Optional<Subscriptions> optional = this.getSubById(id);
			if(optional.isEmpty())
			{
				throw new IdNotFoundException("record not found :(");
			}
			else
				subRepo.deleteById(id);
			return null;
		} catch (IdNotFoundException | InvalidIdLengthException | InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
		
	}

	

}
