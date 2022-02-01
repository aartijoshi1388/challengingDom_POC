package com.org.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.org.base.BasePage;

public class ChallengingDom extends BasePage {

	private WebDriver driver;
	private static final int TIMEOUT = 5;

	@FindBy(css = ".example h3")
	private WebElement challengingDomHeader;

	@FindBy(css = ".example > p")
	private WebElement challengingDomText;

	@FindBy(className = "button")
	private WebElement firstButton;

	@FindBy(css = ".button.alert")
	private WebElement secondButton;

	@FindBy(css = ".button.success")
	private WebElement thirdButton;

	@FindBy(css = "div>table thead")
	private WebElement tableHeaderRow;

	@FindBy(css = "div>table thead th")
	private List<WebElement> tableHeaders;

	@FindBy(partialLinkText = "edit")
	private List<WebElement> editLink;

	@FindBy(partialLinkText = "delete")
	private List<WebElement> deleteLink;

	@FindBy(id = "canvas")
	private WebElement answerTextbox;

	@FindBy(css = "#content script")
	private WebElement contentScript;

	@FindBy(tagName = "script")
	private WebElement scriptTextBox;;

	public ChallengingDom(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
	}

	public boolean isHeaderVisible() {
		return isVisible(challengingDomHeader);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public ChallengingDom refreshPage() {
		driver.navigate().refresh();
		return this;
	}

	public String getPageCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public ChallengingDom clickOnFirstButton() {
		waitForElementToBeClickable(firstButton);
		firstButton.click();
		return this;
	}

	public ChallengingDom clickOnSecondButton() {
		waitForElementToBeClickable(secondButton);
		firstButton.click();
		return this;
	}

	public ChallengingDom clickOnThirdButton() {
		waitForElementToBeClickable(thirdButton);
		firstButton.click();
		return this;
	}

	public String getFirstButtonName() {
		waitForElementToBeClickable(firstButton);
		return firstButton.getText();
	}

	public String getSecondButtonName() {
		waitForElementToBeClickable(secondButton);
		return firstButton.getText();
	}

	public String getThirdButtonName() {
		waitForElementToBeClickable(thirdButton);
		return firstButton.getText();
	}

	public boolean isTextBelowHeaderVisible() {
		return isVisible(challengingDomText);
	}

	public List<String> getHeaderValues() {
		List<String> allHeaderNames = new ArrayList<>();
		tableHeaders.stream().forEach(headerName -> allHeaderNames.add(headerName.getText()));
		return allHeaderNames;
	}

	public List<String> getExpectedHeaderValues() {
		return Arrays.asList("Lorem", "Ipsum", "Dolor", "Sit", "Amet", "Diceret", "Action");
	}

	public ChallengingDom clickOnEditLink(int index) {
		waitForElementToBeClickable(editLink.get(index));
		editLink.get(index).click();
		return this;
	}

	public ChallengingDom clickOnDeleteLink(int index) {
		waitForElementToBeClickable(deleteLink.get(index));
		deleteLink.get(index).click();
		return this;
	}

	public String getAnswerValue() {
		String answer = new String();
		String focusText = contentScript.getAttribute("innerHTML");
		answer = focusText.substring(focusText.indexOf("Answer"), focusText.indexOf("',"));
		return answer;
	}
}