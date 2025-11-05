package hd.assignment.utils;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages Playwright browser lifecycle and provides shared context for tests
 * Thread-safe for parallel test execution
 */
public class TestContext {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;


    // Configuration
    private static final String BROWSER_TYPE = System.getProperty("browser", "chromium"); // chromium, firefox, webkit
    private static final boolean HEADLESS = Boolean.parseBoolean(System.getProperty("headless", "true"));

    @Before
    public void setUp(Scenario scenario) {
        // Initialize Playwright and Browser for each test
        playwright = Playwright.create();
        browser = createBrowser();

        // Create new context and page for each scenario
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setLocale("en-US"));

        page = context.newPage();

        System.out.println("Starting scenario: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        // Take screenshot on failure
        if (scenario.isFailed() && page != null) {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshots/" + scenario.getName() + ".png"))
                    .setFullPage(true));
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        // Close page and context
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }

        // Close browser and playwright
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }

        System.out.println("Finished scenario: " + scenario.getName() + " - Status: " + scenario.getStatus());
    }

    private Browser createBrowser() {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(HEADLESS)
                .setSlowMo(0);

        return switch (BROWSER_TYPE.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(options);
            case "webkit", "safari" -> playwright.webkit().launch(options);
            default -> playwright.chromium().launch(options);
        };
    }

    // Getters
    public Page getPage() {
        return page;
    }

    public BrowserContext getContext() {
        return context;
    }
}
