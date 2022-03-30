package com.game.review.member.email;

import java.util.Random;

//This class is what makes a random code for verifying a email address.
public class TempKey {
	private boolean checker; //lower case checker ->if it has upper cases, this checker will be true
	private int size;
	
	public String getKey(int size, boolean checker) {
		this.size = size;
		this.checker = checker;
		return init();
	}
	
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;
		do {
			num = ran.nextInt(75) + 48; //0 ~ z
			if((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				//48~57 =0~9 || 65~90 = A~Z || 97~122 = a~z
				sb.append((char) num);
			}else {
				continue;
			}
		}while(sb.length() < size);
		
		if(checker) {
			return sb.toString().toLowerCase();
		}
		
		return sb.toString();
	}
}
