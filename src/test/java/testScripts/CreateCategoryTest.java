package testScripts;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genriclibraries.IConstantPath;

//This test is success
public class CreateCategoryTest extends genriclibraries.BaseClass {


	@Test
	public void createCategoryTest() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCategoryLink();
		soft.assertTrue(category.getPageHeader().contains("Category"));
		
		category.clickNewButton();
		Thread.sleep(3000);
		soft.assertEquals(addCategory.getPageHeader(), "Add New Category");
		Map<String, String> map = excel.readFromExcel("Sheet1", "Add Category");
		String categoryName = map.get("Name")+jutil.generateRandomNum(100);
		addCategory.setName(categoryName);
		addCategory.clickSave();
		
		soft.assertTrue(category.getSuccessMessage().contains("Success"));
		boolean isPresent = false;
		List<WebDriver> categoryList = category.getCategoryList();
		for (WebDriver e : categoryList) {
			if(e.getTitle().equals(categoryName)) {
				isPresent = true;
				break;
			}
		}
		soft.assertTrue(isPresent);
		
		category.clickDeleButton(categoryName, driver);
		category.clickDelete();
		soft.assertTrue(category.getSuccessMessage().contains("Success"));
		if(category.getSuccessMessage().contains("Success")) 
			excel.writeToExcel("Sheet1", "Add Category", "Pass", IConstantPath.EXCEL_PATH);

		else
			excel.writeToExcel("Sheet1", "Add Category", "Fail", IConstantPath.EXCEL_PATH);

		soft.assertAll();
	}
}