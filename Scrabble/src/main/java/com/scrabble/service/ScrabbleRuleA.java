package com.scrabble.service;

import org.springframework.stereotype.Service;

@Service
public class ScrabbleRuleA implements ScrabbleRuleImpl {

	String onePoint = "EAIONRTLSU";
	String twoPoint = "DG";
	String threePoint = "BCMP";
	String fourPoint = "FHVWY";
	String fivePoint = "K";
	String sixPoint = "JX";
	String sevenPoint = "QZ";

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

		} else if (fivePoint.contains(value)) {

			return 5;

		} else if (sixPoint.contains(value)) {

			return 8;

		} else if (sevenPoint.contains(value)) {

			return 10;

		} else {
			return 0;
		}
	}

}
