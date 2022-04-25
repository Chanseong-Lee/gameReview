package com.game.review.member.validate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.apache.tika.Tika;

public class FileTypeByTika {
	
	private static final Tika tika = new Tika();
	
	public static boolean validImgFile(InputStream inputStream) {
		try {
			List<String> notValidTypeList = Arrays.asList(
					"image/jpeg", "image/pjpeg", "image/png",
					"image/gif", "image/bmp", "image/x-windows-bmp");
			String mimeType = tika.detect(inputStream);
			System.out.println("MimeType : " + mimeType);
			//boolean isValid = notValidTypeList.stream().anyMatch(notValidType -> notValidType.equalsIgnoreCase(mimeType));
			boolean isValid = notValidTypeList.stream().anyMatch(
					new Predicate<String>() {
						@Override
						public boolean test(String notValidType) {
							return notValidType.equalsIgnoreCase(mimeType);
						}
					});
			return isValid;
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean validItemImgFile(InputStream inputStream) {
		try {
			List<String> notValidTypeList = Arrays.asList(
					"image/jpeg", "image/pjpeg", "image/png"
					);
			String mimeType = tika.detect(inputStream);
			System.out.println("MimeType : " + mimeType);
			//boolean isValid = notValidTypeList.stream().anyMatch(notValidType -> notValidType.equalsIgnoreCase(mimeType));
			boolean isValid = notValidTypeList.stream().anyMatch(
					new Predicate<String>() {
						@Override
						public boolean test(String notValidType) {
							return notValidType.equalsIgnoreCase(mimeType);
						}
					});
			return isValid;
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
