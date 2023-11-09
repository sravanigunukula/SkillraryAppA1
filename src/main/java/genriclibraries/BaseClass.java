 package genriclibraries;
 import org.openqa.selenium.WebDriver;
 import org.testng.Assert;
 import org.testng.annotations.AfterClass;
 import org.testng.annotations.AfterMethod;
 import org.testng.annotations.BeforeClass;
 import org.testng.annotations.BeforeMethod;

 import pomPackages.AddNewCategoryPage;
 import pomPackages.AddNewCoursePage;
 import pomPackages.AddNewUserPage;
 import pomPackages.AminHomePage;
 import pomPackages.CategoryPage;
 import pomPackages.CourseListPage;
 import pomPackages.LoginPage;
 import pomPackages.UserPage;
 import pomPackages.WelcomePage;
//this is a Baseclass
 public class BaseClass {

 	//@BeforeSuite
 	//@BeforeTest
 	
 	protected PropertiesUtility property;
 	protected ExcelUtility excel;
 	protected JavaUtility jutil;
 	protected WebDriverUtility webUtil;
 	protected WebDriver driver;
 	
 	public static WebDriver sdriver;
 	public static JavaUtility sjutil;
 	
 	protected WelcomePage welcome;
 	protected LoginPage login;
 	protected AminHomePage home;
 	protected UserPage users;
 	protected CourseListPage course;
 	protected CategoryPage category;
 	protected AddNewUserPage addUser;
 	protected AddNewCoursePage addCourse;
 	protected AddNewCategoryPage addCategory;
 	
 	@BeforeClass
 	public void classConfig() {
 		property = new PropertiesUtility();
 		excel = new ExcelUtility();
 		jutil = new JavaUtility();
 		webUtil = new WebDriverUtility();
 		
 		property.PropertiesInitialization(IConstantPath.PROPERTIES_PATH);
 		driver = webUtil.launchBrowser(property.readFromProperties("browser"));
 		
 		sdriver = driver;
 		sjutil = jutil;
 	}
 	
 	@BeforeMethod
 	public void methodConfig() throws InterruptedException {
 		excel.excelInitialization(IConstantPath.EXCEL_PATH);
 		
 		welcome = new WelcomePage(driver);
 		login = new LoginPage(driver);
 		home = new AminHomePage(driver);
 		users = new UserPage(driver);
 		course = new CourseListPage(driver);
 		category = new CategoryPage(driver);
 		addUser = new AddNewUserPage(driver);
 		addCourse = new AddNewCoursePage(driver);
 		addCategory = new AddNewCategoryPage(driver);
 		
 		webUtil.navigateToApp(property.readFromProperties("url"));
 		Assert.assertEquals(welcome.getLogo(), "SkillRary-ECommerce");
 		
 		long time = Long.parseLong(property.readFromProperties("timeouts"));
 		webUtil.waitTillElementFound(time);
 		
 		welcome.clickLogginButton();
 		Assert.assertEquals(login.getPageHeader(), "Login");
 		
 		login.setEmail(property.readFromProperties("username"));
 		login.setPassword(property.readFromProperties("password"));
 		login.clickLogin();
 		
 		Assert.assertEquals(home.getAdminIcon(), "SkillRary Admin");
 	}
 	
 	@AfterMethod
 	public void methodTeardown() {
 		excel.closeExcel();
 		home.signOutofApp();
 	}
 	
 	@AfterClass
 	public void classTeardown() {
 		webUtil.closeAllWindow();
 	}
 	
 	//@AfterTest
 	//@AfterSuite
 }
