package PageClassPackage;
import org.openqa.selenium.By;



public class PageObjectModel {
	public static final By carloan=By.cssSelector("#car-loan > a:nth-child(1)");
	public static final By carloanamount=By.id("loanamount");
	public static final By interest=By.id("loaninterest");
	public static final By tenure=By.id("loanterm");
	public static final By year=By.cssSelector("label.active:nth-child(1)");
	public static final By principaltable=By.cssSelector("#monthyear2020 > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)");
	public static final By interesttable=By.cssSelector("#monthyear2020 > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(3)");
	public static final By expand=By.id("year2020");
	public static final By graph=By.cssSelector("#emibarchart");
}
