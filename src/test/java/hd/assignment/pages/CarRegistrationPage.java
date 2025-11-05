package hd.assignment.pages;

import com.microsoft.playwright.Page;
import hd.assignment.utils.TestHelpers;


public class CarRegistrationPage extends BasePage{

    //Element Locators
    private static final String REGISTRATION_NUMBER_INPUT="#input-number-plates";
    private static final String YEAR_DROPDOWN="#select-year";
    private static final String SUBMIT_FORM_BUTTON="#btn-submit";
    private static final String ERROR_MESSAGE=".alert-danger";
    private static final String SUCCESSFUL_SUBMISSION_MESSAGE=".alert-success";

    private static final String fileUrl= "src/webapp/QA Programming Exercise.html";

    public CarRegistrationPage(Page page) {
        super(page);
    }


    public void navigateToCarRegistrationPage() {
        String appUrl = TestHelpers.getLocalFileUrl(fileUrl);
        navigateTo(appUrl);
        waitForPageLoad();
    }

    public void fillCarRegistrationNumber(String carRegistrationNumber) {
        fill(REGISTRATION_NUMBER_INPUT,carRegistrationNumber);
    }

    public void selectSpecificYearFromDropdown(String yearToSelect) {
        click(YEAR_DROPDOWN);
        selectDropdown(YEAR_DROPDOWN,yearToSelect);
    }

    public void clickSubmitButton() {
        click(SUBMIT_FORM_BUTTON);
    }

    public boolean isSuccessMessageVisible(){
        return isVisible(SUCCESSFUL_SUBMISSION_MESSAGE);
    }

    public String getSuccessMessage(){
        return getText(SUCCESSFUL_SUBMISSION_MESSAGE);
    }

    public String getErrorMessage(){
        return getText(ERROR_MESSAGE).trim();
    }

    public boolean isErrorMessageVisible(){
        return isVisible(ERROR_MESSAGE);
    }

    public void clearCarRegistrationNumberField() {
        getLocator(REGISTRATION_NUMBER_INPUT).clear();
    }

    public void submitFormWithEnterKey() {
        getLocator(REGISTRATION_NUMBER_INPUT).press("Enter");
    }
}
