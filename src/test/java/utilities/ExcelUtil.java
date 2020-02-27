package utilities;



import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	public ExcelUtil(String excelPth, String sheetName)  {
		try {
			workbook = new XSSFWorkbook(excelPth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sheet = workbook.getSheet(sheetName);
	}
	
	public String getData(int row , int column) {
		String data = sheet.getRow(row).getCell(column).toString();
		return data;
	}
	public  int getRowCount() {
		int rowcount = 0;
		try {

			rowcount = sheet.getPhysicalNumberOfRows();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return rowcount;
	}
	public  int getColCount() {
		int colcount = 0;
		try {
			colcount = sheet.getRow(0).getPhysicalNumberOfCells();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return colcount;
	}
}
