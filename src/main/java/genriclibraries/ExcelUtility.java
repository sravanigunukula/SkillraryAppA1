package genriclibraries;

	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.HashMap;
	import java.util.Map;


	import org.apache.poi.EncryptedDocumentException;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;

	/**
	 * THis class contains reusable methods to read data from excel
	 * @author ACER
	 *
	 */
	public class ExcelUtility {
		
		private Workbook wb;
		/**
		 * THis method is used to initialize excel
		 * @param excelPath
		 */
		
		
		public void excelInitialization(String excelPath) {
			FileInputStream fis = null;
			try {
			fis=new FileInputStream(excelPath);
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				wb= WorkbookFactory.create(fis);
				
			}catch(EncryptedDocumentException| IOException e) {
				e.printStackTrace();
			}
		}
		

}
