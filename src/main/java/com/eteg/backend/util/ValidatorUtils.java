package com.eteg.backend.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Class responsible for validate CPF, name and date.
 *
 * @author Renan Mattos
 */
public class ValidatorUtils 
{
	
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
	
	/**
	 * This method handles the validation of cpf formatting.
	 * 
	 * @param cpf
	 * @return cpf CPF number
	 */
	public static boolean isValidCPF(String cpf)
	{
		if (cpf.isEmpty())
		{
			return false;
		}

		Pattern pattern = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2}");
		Matcher matcher = pattern.matcher(cpf);

		return matcher.matches();
	}
	
	/**
	 * This method handles the validation of name formatting.
	 * 
	 * @param name
	 * @return name Name of user
	 */
	public static boolean isValidName(String name)
	{
		if(name.isEmpty()) 
		{
			return false;
		}
		
		Pattern pattern = Pattern.compile(("[A-Z][a-z]{5,50}"));
		Matcher matcher = pattern.matcher(name);
		
		return matcher.matches();
	}
	
	/**
	 * This method handles the validation of date formatting.
	 * 
	 * @param date
	 * @return date Date of user
	 */
	public static boolean isValidDate(String date) 
	{         
		SimpleDateFormat dateFormat = new SimpleDateFormat();     
		dateFormat.setLenient(false);                                   
		try 
		{                                                           
			dateFormat.parse(date);                                     
			return true;        
			
		} catch (ParseException e) {                                    
			return false;                                               
		}                                                               
	}
	
	/**
	 * This method handles the validation if the user is over 18 years old.
	 * 
	 * @param date
	 * @return birthDate Date of birth
	 */
	public static boolean isOverAge (String bod)
	{
		LocalDate birthDate = LocalDate.parse(bod, dateFormatter);
		LocalDate today = LocalDate.now(ZoneId.of("Brazil"));
		long age = ChronoUnit.YEARS.between(birthDate, today);
		
		if(age < 18) 
		{
			return true;
		}
		return false;
	}
}
