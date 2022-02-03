package com.zee.zee5app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
@AllArgsConstructor
@Table(name = "subscriptions")
public class Subscriptions implements Comparable<Subscriptions>
{
	public  Subscriptions() 
	{
		
	}
	
//	public Subscriptions(Integer id, Integer amount, String type, String DateOfPurchase, String Status, String PackCountry, String PaymentMode, String AutoRenewal, String Expiry, String regId) throws InvalidIdLengthException, InvalidAmountException
//	{
//		super();
//		this.setId(id);
//		this.setAmount(amount);
//		this.setType(type);
//		this.setDateOfPurchase(DateOfPurchase);
//		this.setStatus(Status);
//		this.setPackCountry(PackCountry);
//		this.setPaymentMode(PaymentMode);
//		this.setAutoRenewal(AutoRenewal);
//		this.setExpiry(Expiry);
//		this.setRegId(regId);
//	}
	@Id
	private Integer id;	
	@NotNull
	private Integer amount;	
	@Size(max = 50)
	@NotBlank
	private String type;	
	@NotNull
	private String DateOfPurchase;	
	@NotBlank
	private String Status;
	@NotBlank
	private String PackCountry;
	@NotBlank
	private String PaymentMode;
	@NotBlank
	private String AutoRenewal;	
	@NotNull
	private String Expiry;
	@NotBlank
	private String regId;
	
	@Override
	public int compareTo(Subscriptions o) 
	{
		// TODO Auto-generated method stub
		return o.getId().compareTo(this.getId());
	}

	
	
}
