# HD Assignment - Car Registration Test Automation

## Overview
This is a test automation framework for testing a Car Registration Form. The framework uses modern tools and follows best practices for web application testing.

## Tech Stack
- **Java 21** - Programming language
- **Playwright 1.55.0** - Browser automation
- **Cucumber 7.29.0** - BDD framework (Behavior-Driven Development)
- **TestNG 7.10.2** - Test execution framework
- **Maven** - Build and dependency management
- **AssertJ 3.26.0** - Fluent assertions library

## Project Structure
```
hd-assignment/
├── pom.xml                                    # Maven configuration
├── src/test/
│   ├── java/hd/assignment/
│   │   ├── pages/
│   │   │   ├── BasePage.java                 # Base page with common methods
│   │   │   └── CarRegistrationPage.java      # Car registration page object
│   │   ├── steps/
│   │   │   └── CarRegistrationStepDefs.java  # Cucumber step definitions
│   │   ├── runners/
│   │   │   ├── RegressionTestRunner.java     # Run all tests
│   │   │   ├── ValidTestRunner.java          # Run valid scenarios only
│   │   │   └── InvalidTestRunner.java        # Run invalid scenarios only
│   │   └── utils/
│   │       ├── TestContext.java              # Browser lifecycle management
│   │       └── TestHelpers.java              # Helper utilities
│   └── resources/
│       └── features/
│           ├── ValidCarRegistraton.feature           # Valid submission tests
│           ├── InvalidCarRegistrationNumber.feature  # Invalid format tests
│           ├── InvalidYearValidation.feature         # Year validation tests
│           └── SecurityScenarios.feature             # Security tests
└── target/
    ├── cucumber-reports/                     # Test reports
    └── screenshots/                          # Failure screenshots
```

## Prerequisites

Before running the tests, ensure you have:

1. **Java 21 or higher**
   ```
   java -version
   ```

2. **Maven 3.6+**
   ```
   mvn -version
   ```

## Installation

1. **Clone or download the project**
   ```
   cd hd-assignment
   ```

2. **Install dependencies**
   ```
   mvn clean install
   ```

3. **Install Playwright browsers** (first time only)
   ```
   mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
   ```

## Running Tests

### Run All Tests
```
mvn clean test
```

### Run Specific Test Suites

**Valid scenarios only:**
```
mvn clean test -Dtest=ValidTestRunner
```

**Invalid scenarios only:**
```
mvn clean test -Dtest=InvalidTestRunner
```

**Security tests only:**
```
mvn clean test -Dtest=SecurityTestRunner
```

**All regression tests:**
```
mvn clean test -Dtest=RegressionTestRunner
```


**Note**: Tags are configured in the `@CucumberOptions` annotation within each test runner class. To run tests with different tag combinations, create a new test runner class or modify existing ones.

### Run with Different Browsers

**Chrome/Chromium (default):**
```
mvn clean test -Dbrowser=chromium
```

**Firefox:**
```
mvn clean test -Dbrowser=firefox
```

**Safari/WebKit:**
```
mvn clean test -Dbrowser=webkit
```

### Run in Headed Mode (See Browser)
```
mvn clean test -Dheadless=false
```

### Combine Options
```
# Run valid tests in Firefox with visible browser
mvn clean test -Dtest=ValidTestRunner -Dbrowser=firefox -Dheadless=false
```

## Test Reports

After running tests, reports are generated in:

- **Cucumber HTML Report**: `target/cucumber-reports/cucumber.html`
- **Cucumber JSON Report**: `target/cucumber-reports/cucumber.json`
- **TestNG Reports**: `target/surefire-reports/`
- **Screenshots** (on failure): `target/screenshots/`

Open the Cucumber report in your browser:
```
open target/cucumber-reports/cucumber.html
```

## Test Coverage

The framework includes tests for:

### Valid Scenarios
- Submit valid car registration with years 2015, 2016, 2017
- Multiple consecutive valid submissions
- Submit invalid then valid data (state management)
- Submit form using Enter key

### Invalid Car Registration Numbers
- Too many letters (e.g., ABCD1234)
- Too few letters (e.g., AB1234)
- Too many digits (e.g., ABC12345)
- Too few digits (e.g., ABC123)
- Special characters (e.g., ABC@1234)
- Lowercase letters (e.g., abc1234)
- Mixed case letters (e.g., AbC1234)
- Digits before letters (e.g., 1234ABC)
- Space in registration (e.g., ABC 1234)
- Greek characters (e.g., ΓΔΕ4567)
- Accented characters (e.g., ÁBĆ1234)
- Leading spaces (e.g., "  ZKE456")
- Trailing spaces (e.g., "ZKE456   ")
- Empty registration number with each year (2015, 2016, 2017)
- Only letters (e.g., ABCDEFG)
- Only numbers (e.g., 1234567)
- Submit valid then invalid data (state management)

### Invalid Year Validation
- Submit without selecting year
- Submit with both fields empty

### Security Testing
- SQL injection attempt in registration field
- XSS (Cross-Site Scripting) attempt in registration field

## Page Object Model

The framework follows the **Page Object Model** design pattern:

### CarRegistrationPage.java
Contains methods for all user interactions:
```java
// Example usage
CarRegistrationPage page = new CarRegistrationPage(testContext.getPage());
page.fillCarRegistrationNumber("ABC1234");
page.selectSpecificYearFromDropdown("2017");
page.clickSubmitButton();
assertTrue(page.isSuccessMessageVisible());
```

**Key methods:**
- `navigateToCarRegistrationPage(url)`
- `fillCarRegistrationNumber(number)`
- `selectSpecificYearFromDropdown(year)`
- `clickSubmitButton()`
- `isSuccessMessageVisible()`
- `isErrorMessageVisible()`
- `getSuccessMessage()`
- `getErrorMessage()`
- `clearCarRegistrationNumberField()`
- `submitFormWithEnterKey()`

## Configuration

### Browser Settings

Edit `TestContext.java` to change default settings, or use system properties:

```java
// Default values (can be overridden via -D flags)
BROWSER_TYPE = "chromium"  // chromium, firefox, webkit
HEADLESS = true            // true for CI/CD, false to see browser
```

### Override via Command Line
```
mvn test -Dbrowser=firefox -Dheadless=false
```





