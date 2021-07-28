package homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	public static void main(String[] args) {
	/**
	 'd:/D_Other/'에 있는 'Tulips.jpg'파일을
	 '복사본_Tulips.jpg'로 복사하는 프로그램을 작성하시오.
	*/
		File ogFile = new File("d:/D_Other/Tulips.jpg");
		File copyFile = new File("d:/D_Other/복사본_Tulips.jpg");
		
		try {
			FileInputStream fis = new FileInputStream(ogFile);
			FileOutputStream fos = new FileOutputStream(copyFile);
			
			int c;
			while((c = fis.read()) != -1) {
				fos.write(c);
			}
			fos.close();
			fis.close();
			System.out.println("Copy 완료...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
