package hd.assignment.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    /**
     * Navigate to a URL
     */
    protected void navigateTo(String url) {
        page.navigate(url);
    }

    /**
     * Click on an element
     */
    protected void click(String selector) {
        page.locator(selector).click();
    }

    /**
     * Fill input field
     */
    protected void fill(String selector, String text) {
        page.locator(selector).fill(text);
    }

    /**
     * Get text from an element
     */
    protected String getText(String selector) {
        return page.locator(selector).textContent();
    }

    /**
     * Check if element is visible
     */
    protected boolean isVisible(String selector) {
        return page.locator(selector).isVisible();
    }


    /**
     * Get locator
     */
    protected Locator getLocator(String selector) {
        return page.locator(selector);
    }


    /**
     * Wait for page to load
     */
    protected void waitForPageLoad() {
        page.waitForLoadState();
    }

    /**
     * Select dropdown option by value
     */
    protected void selectDropdown(String selector, String value) {
        page.selectOption(selector, value);
    }

}
