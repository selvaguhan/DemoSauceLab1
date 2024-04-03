package com.saucelab.qa.pages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.saucelab.qa.base.BasePage;
import com.saucelab.qa.util.TestUtil;

public class ProductHomePage extends BasePage {

	// Page Objects
	private By pageHeader = By.xpath(TestUtil.SECONDARY_PAGE_HEADER);

	private By productList = By.xpath("//div[@class='inventory_item']");
	private By productName = By.xpath(".//div[contains(@class,'inventory_item_name')]");
	private By productPrice = By.xpath(".//div[contains(@class,'inventory_item_price')]");

	private By selectSort = By.className("product_sort_container");

	private By navigateToShoppingCart = By.className("shopping_cart_link");

	private By homeMenuBtn = By.id("react-burger-menu-btn");
	private By logout = By.linkText("Logout");

	// Page Constructor
	public ProductHomePage(WebDriver driver) {
		super(driver);
	}

	// Page Actions
	public String getPageHeader() {
		return readText(pageHeader);
	}

	public int getProductListSize() {
		List<WebElement> itemList = driver.findElements(productList);
		return itemList.size();
	}

	public LinkedHashMap<String, BigDecimal> getProductList() {
		LinkedHashMap<String, BigDecimal> inventoryItemList = new LinkedHashMap<>();

		List<WebElement> itemList = driver.findElements(productList);

		for (WebElement element : itemList) {
			String name = element.findElement(productName).getText();
			String price = element.findElement(productPrice).getText();
			price = (price.replace("$", "")).replaceAll(" ", "");
//			System.out.println(name+" = "+ price);
			inventoryItemList.put(name, new BigDecimal(price));
		}

//		System.out.println(inventoryItemList);
		return inventoryItemList;
	}

	public void printProductList(LinkedHashMap<String, BigDecimal> itemList) {
		for (Map.Entry<String, BigDecimal> entry : itemList.entrySet()) {
			System.out.println("Product Name = " + entry.getKey() + "  & Product Price = $" + entry.getValue());
		}
	}

	public String verifySortOption() {
		Select sort = new Select(driver.findElement(selectSort));
		String option = sort.getFirstSelectedOption().getAttribute("value");
		return option;
	}

	public void selectSortOptionByValue(String options) {
		Select sort = new Select(driver.findElement(selectSort));
		sort.selectByValue(options);
	}

	public String checkSelectedSortOption() {
		String option = verifySortOption();
//		System.out.println("option - :"+option+";");
		switch (option) {
		case "az":
			System.out.println("Selected Sort Option is :: Name (A to Z)");
			break;
		case "za":
			System.out.println("Selected Sort Option is :: Name (Z to A)");
			break;
		case "lohi":
			System.out.println("Selected Sort Option is :: Price (low to high)");
			break;
		case "hilo":
			System.out.println("Selected Sort Option is :: Price (high to low)");
			break;

		default:
			System.out.println("NO Such selection option available..");
			break;
		}

		return option;
	}

	// Generating Runtime Xpath locator
	public By getAddToCartLocator(String productname) {
		By addTocart = By
				.xpath("//div[contains(.,'" + productname + "')]/ancestor::div[@class='inventory_item']//button");
		return addTocart;
	}

	public void selectAddToCart(String productName) {
		click(getAddToCartLocator(productName));
	}

	public ArrayList<String> retrieveProductNameList(LinkedHashMap<String, BigDecimal> itemList) {
		return new ArrayList<String>(itemList.keySet());
	}

	public ArrayList<BigDecimal> retrieveProductPriceList(LinkedHashMap<String, BigDecimal> itemList) {
		return new ArrayList<BigDecimal>(itemList.values());
	}

	public boolean verifyItemNameSortedAsc(LinkedHashMap<String, BigDecimal> itemList) {
		ArrayList<String> defaultList = retrieveProductNameList(itemList);
		System.out.println("Default Name Order:: " + defaultList);

		ArrayList<String> sortedAsc = new ArrayList<>();
		sortedAsc.addAll(defaultList);
		Collections.sort(sortedAsc);
		System.out.println("Ascending Sorted Name Order:: " + sortedAsc);

		if (defaultList.equals(sortedAsc)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyItemNameSortedDesc(LinkedHashMap<String, BigDecimal> itemList) {
		ArrayList<String> defaultList = retrieveProductNameList(itemList);
		System.out.println("Default Name Order:: " + defaultList);

		ArrayList<String> sortedDesc = new ArrayList<>();
		sortedDesc.addAll(defaultList);
		Collections.sort(sortedDesc, Collections.reverseOrder());
		System.out.println("Descending Sorted Name Order:: " + sortedDesc);

		if (defaultList.equals(sortedDesc)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyItemPriceSortedAsc(LinkedHashMap<String, BigDecimal> itemList) {
		ArrayList<BigDecimal> defaultList = retrieveProductPriceList(itemList);
		System.out.println("Default Price Order:: " + defaultList);

		ArrayList<BigDecimal> sortedAsc = new ArrayList<>();
		sortedAsc.addAll(defaultList);
		Collections.sort(sortedAsc);
		System.out.println("Ascending Sorted Price Order:: " + sortedAsc);

		if (defaultList.equals(sortedAsc)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyItemPriceSortedDesc(LinkedHashMap<String, BigDecimal> itemList) {
		ArrayList<BigDecimal> defaultList = retrieveProductPriceList(itemList);
		System.out.println("Default Price Order:: " + defaultList);

		ArrayList<BigDecimal> sortedDesc = new ArrayList<>();
		sortedDesc.addAll(defaultList);
		Collections.sort(sortedDesc, Collections.reverseOrder());
		System.out.println("Descending Sorted Price Order:: " + sortedDesc);

		if (defaultList.equals(sortedDesc)) {
			return true;
		} else {
			return false;
		}
	}

	public ShoppingCartPage navigateToCartPage() {
		click(navigateToShoppingCart);
		return new ShoppingCartPage(driver);
	}

	public void Logout() {
		click(homeMenuBtn);
		click(logout);
	}
}
