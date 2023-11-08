package testScripts;

import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genriclibraries.BaseClass;

public class CreateCategoryTest extends BaseClass{
	@Test
	public void createCategoryTest() {
		SoftAssert soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCategoryLink();
		soft.assertTrue(category.getPageHeader().contains("Category"));
		
		category.clickNewButton();
		Thread.sleep(3000);
		soft.assertEquals(addCategory.getPageHeader(),"Add New Category");
		Map<String, String>map = excel.readFormExcel("Sheet1","Add Category");
		String categoryName = map.get("Name")+jutil.generateRandomNum(100);
		addCategory.setName(categoryName);
		addCategory.clickSave();
		
		soft.assertTrue(category.getSuccessMessage().contains("success"));
		boolean isPresent = false;
		List<Element> categoryList = category.getCategoryList();
	
	  for	(WebElement e: categoryList) {
		  if(e.getText().equals(categoryName)) {
			  isPresent = true;
			  break;
		  }
	  }
	  soft.assertTrue(isPresent);
	  
	  category.clickDeleButton(categoryName, driver);
	  category.clickDelete();
	  soft.assertTrue(category.getSuccessMessage().contains("Success"));
	  soft.assertAll();
	  
	
	}
	

}
