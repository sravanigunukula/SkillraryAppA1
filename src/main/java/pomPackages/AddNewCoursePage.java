package pomPackages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genriclibraries.WebDriverUtility;

public class AddNewCoursePage {
//Declaration
	@FindBy(xpath="//b[text()='Add New Course']")
	private WebElement pageHeader;
	
	@FindBy(xpath="//input[@id='name']")
	private WebElement nameTF;
	
	@FindBy(id="category")
	private WebElement categoryDropDown;
	
	@FindBy(id="price")
	private WebElement priceTF;
	
	@FindBy(xpath="//input[@is='photo'])[2]")
	private WebElement photoButton;
	
	@FindBy(xpath="//button[@name='add']")
	private WebElement saveButton;
	
	@FindBy(xpath="//html/body/p")
	private WebElement descriptionTextArea;
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, editor1']")
	private WebElement descriptionFrame;
	
	//Initialization
	
	public AddNewCoursePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void setName(String courseName) {
		nameTF.sendKeys(courseName);
		
	}
	public void selectCategory(WebDriverUtility web, String category) {
		web.handledropDown(categoryDropDown, category);
		
	}

	 public void setPrice(String Price) {
		  priceTF.sendKeys(Price);
	 }
	 public void setDescription(WebDriverUtility web, String text) {
		 web.switchToFrame(descriptionFrame);
		 descriptionTextArea.sendKeys(text);
		 web.switchBackFromFrame();
	 }
	 public void clickSaveButton() {
		 saveButton.click();
	 }
	 
	 
	
	

}