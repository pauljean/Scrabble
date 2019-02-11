package com.scrabble.service;

import org.springframework.stereotype.Service;

@Service
public class ScrabbleRuleB implements ScrabbleRuleImpl{
	
	String onePoint = "DJKQXZ";
	String twoPoint = "BCFHMPVWY";
	String threePoint = "G";
	String fourPoint = "LSU";
	String sixPoint = "NRT";
	String eightPoint = "O";
	String tenPoint = "E";

	@Override
	public int checkPoint(String value) {
		value = value.toUpperCase();

		if (onePoint.contains(value)) {
			return 1;
		} else if (twoPoint.contains(value)) {

			return 2;

		} else if (threePoint.contains(value)) {

			return 3;

		} else if (fourPoint.contains(value)) {

			return 4;

		}  else if (sixPoint.contains(value)) {

			return 6;

		} else if (eightPoint.contains(value)) {

			return 8;

		} else if(tenPoint.contains(value)){
			return 10;
		}else {
			
			return 0;
		}
	}

}
