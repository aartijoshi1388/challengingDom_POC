package com.org.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.org.pages.ChallengingDom;
import com.org.utils.Index;

public class ChallengingDomTest extends BaseTest {
	SoftAssert softAssert;
	ChallengingDom challengingDom;
	private static final String TITLE = "The Internet";

	@Test
	public void verifyNavigationToChallengingDom() {
		softAssert = new SoftAssert();
		challengingDom = new ChallengingDom(driver);
		reportLog("Verifying for Page Title");
		softAssert.assertEquals(challengingDom.getPageTitle(), TITLE, "Page Title is not matching");
		reportLog("Verifying for Page Header element");
		softAssert.assertTrue(challengingDom.isHeaderVisible(), "Header is not visible");
		softAssert.assertAll();
	}

	@Test
	public void verifyTextBelowHeader() {
		challengingDom = new ChallengingDom(driver);
		reportLog("Verifying text below page header is visible");
		assertTrue(challengingDom.isTextBelowHeaderVisible(), "Text below header is not visible");
	}

	@Test
	public void verifyFirstButtonOperation() {
		challengingDom = new ChallengingDom(driver);
		String currentButtonName = challengingDom.getFirstButtonName();
		reportLog("Verifying First button is clickable");
		String newButtonName = challengingDom.clickOnFirstButton().getFirstButtonName();
		reportLog("verify Actual and expected button names");
		assertNotEquals(currentButtonName, newButtonName, "Actual and expected button names are not matching");
	}

	@Test
	public void verifySecondButtonOperation() {
		challengingDom = new ChallengingDom(driver);
		reportLog("Verifying Second button is clickable");
		String currentButtonName = challengingDom.getSecondButtonName();
		String newButtonName = challengingDom.clickOnSecondButton().getSecondButtonName();
		reportLog("verify Actual and expected button names");
		assertNotEquals(currentButtonName, newButtonName, "Actual and expected button names are not matching");
	}

	@Test
	public void verifyThirdButtonOperation() {
		challengingDom = new ChallengingDom(driver);
		reportLog("Verifying Third button is clickable");
		String currentButtonName = challengingDom.getThirdButtonName();
		String newButtonName = challengingDom.clickOnThirdButton().getThirdButtonName();
		reportLog("verify Actual and expected button names");
		assertNotEquals(currentButtonName, newButtonName, "Actual and expected button names are not matching");
	}

	@Test
	public void verifyTableHeaderText() {
		challengingDom = new ChallengingDom(driver);
		reportLog("Verifying Header Names in table");
		List<String> ActualHeadersName = challengingDom.getHeaderValues();
		assertEquals(ActualHeadersName, challengingDom.getExpectedHeaderValues());
	}
	
	@Test
	public void verifyClickOnNthEditLink() {
		challengingDom = new ChallengingDom(driver);
		reportLog("Verifying clicking on Nth Edit link in table");
		challengingDom.clickOnEditLink(Index.THIRD.ordinal());
		assertTrue(challengingDom.getPageCurrentUrl().contains("#edit"), "Not able to click on Edit link");
	}
	
	@Test
	public void verifyClickOnNthDeleteLink() {
		challengingDom = new ChallengingDom(driver);
		reportLog("Verifying clicking on Nth Delete link in table");
		challengingDom.clickOnDeleteLink(Index.FIFTH.ordinal());
		assertTrue(challengingDom.getPageCurrentUrl().contains("#delete"), "Not able click on Delete link");
	}
	
	@Test
	public void verifyAnswerValue() {
		challengingDom = new ChallengingDom(driver);
		reportLog("Verifying answer value should not be null");
		assertTrue(challengingDom.getAnswerValue().length() > 0, "Answer is invalid");
	}
	
	@Test
	public void verifyAnswerOnRefreshPage() {
		challengingDom = new ChallengingDom(driver);
		reportLog("Verifying answer value should not be null");
		String currentAnswer = challengingDom.getAnswerValue();
		String newAnswer = challengingDom.refreshPage().getAnswerValue();
		assertNotEquals(currentAnswer, newAnswer, "Answers are not matching");
	}

}