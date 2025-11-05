# Test Plan - Car Registration Form

## Table of Contents

1. [Introduction](#1-introduction)
2. [Test Objectives](#2-test-objectives)
3. [Test Environment](#3-test-environment)
4. [Test Cases](#4-test-cases)

---

## 1. Introduction

This test plan describes the testing strategy and test cases for the Car Registration Form application. The application allows users to enter a car registration number, select a year, and submit the form for validation.

### Application Under Test

**Car Registration Form** - A web form that validates car registration numbers against specific format rules and accepts year selections from 2015-2017.

---

## 2. Test Objectives

The primary objectives of this test plan are:

- Verify that valid car registration numbers are accepted
- Ensure invalid car registration formats are rejected with appropriate error messages
- Validate year selection functionality (2015, 2016, 2017)
- Test form state management and transitions
- Ensure proper error handling and user feedback
- Validate input behavior and submission methods
- Ensure cross-browser compatibility

---

## 3. Test Environment

### Browser Support

- Chromium (Chrome/Edge)
- Firefox
- WebKit (Safari)

### Test Data

Valid car registration format: **3 uppercase latin letters + 4 digits** (e.g., ABC1234)

Valid years: **2015, 2016, 2017**

---

## 4. Test Cases

### 4.1 Valid Car Registration Scenarios

| Test Case Number | Test Case                                                | Type |
|------------------|----------------------------------------------------------|------|
| 4.1.1            | Submit valid car registration with year (2015,2016,2017) | Positive |
| 4.1.2            | Multiple consecutive valid submissions                   | State Management                                          |
| 4.1.3            | Submit invalid then valid data                           | State Management                                         |
| 4.1.4            | Submit form using Enter key                              | Input Behavior                                           |

#### 4.1.1 Valid Registration for Each Year

**Objective**: Verify that valid car registration numbers are accepted for all supported years

**Preconditions**:
- User navigates to the car registration form

**Test Steps**:
1. Enter valid car registration number (e.g., ABC1234, AAA0000, ZZZ9999)
2. Select year from dropdown (2015, 2016, or 2017)
3. Click Submit button

**Expected Result**:
- Success message displayed: "Success! Selected [REGISTRATION] with year [YEAR]"
- Error message not visible
- Form accepts submission

**Test Data**:
- ABC1234 / 2015
- AAA0000 / 2016
- ZZZ9999 / 2017

---

#### 4.1.2 Multiple Consecutive Valid Submissions

**Objective**: Verify form can handle multiple valid submissions in sequence

**Test Steps**:
1. Enter "ABC1234" and select year "2016"
2. Click Submit button
3. Verify success message
4. Change registration to "XYZ5678"
5. Change year to "2017"
6. Click Submit button again

**Expected Result**:
- First submission shows: "Success! Selected ABC1234 with year 2016"
- Second submission shows: "Success! Selected XYZ5678 with year 2017"
- Both submissions successful

---

#### 4.1.3 Submit Invalid Then Valid Data

**Objective**: Verify form correctly transitions from error to success state

**Test Steps**:
1. Enter "XXY12345" (invalid) and select year "2016"
2. Click Submit (should fail)
3. Change registration to "ABC1234" (valid)
4. Click Submit again

**Expected Result**:
- First submission shows error message
- Second submission shows success message
- Form correctly switches from error to success state

---

#### 4.1.4 Submit Form Using Enter Key

**Objective**: Verify form can be submitted using keyboard (Enter key)

**Test Steps**:
1. Enter "ABC1234" in registration field
2. Select year "2016"
3. Press Enter key (instead of clicking Submit button)

**Expected Result**:
- Form submits successfully
- Success message displayed
- Enter key works as alternative to button click

---

### 4.2 Invalid Car Registration Number Scenarios

| Test Case Number | Test Case                               | Type |
|------------------|-----------------------------------------|------|
| 4.2.1            | Lowercase letters (abc1234) | Negative |
| 4.2.2            | Too few letters (AB1234) | Negative |
| 4.2.3            | Too few digits (ABC123) | Negative |
| 4.2.4            | Too many digits (ABC12345) | Negative |
| 4.2.5            | Too many letters (ABCD2345) | Negative |
| 4.2.6            | Digits before letters (1234ABC) | Negative |
| 4.2.7            | Space in registration (ABC 1234) | Negative |
| 4.2.8            | Mixed case letters (AbC1234) | Negative |
| 4.2.9            | Special characters (Ab@1234) | Negative |
| 4.2.10           | Only letters (ABCDEFG) | Negative |
| 4.2.11           | Only numbers (2345678) | Negative |
| 4.2.12           | Greek characters (ΓΔΕ4567) | Negative |
| 4.2.13           | Accented characters (ÁBĆ1234) | Negative |
| 4.2.14           | Leading spaces (  ZKE456) | Negative |
| 4.2.15           | Trailing spaces (ZKE456   ) | Negative |
| 4.2.16           | Empty registration field with year 2015 | Negative |
| 4.2.17           | Empty registration field with year 2016 | Negative |
| 4.2.18           | Empty registration field with year 2017 | Negative |
| 4.2.19           | Submit valid then invalid data | State Management |

#### 4.2.1 to 4.2.18 Invalid Format Validation

**Objective**: Verify that all invalid car registration formats are rejected

**Preconditions**:
- User navigates to the car registration form

**Test Steps**:
1. Enter invalid car registration number (see test data)
2. Select year "2017" (or as specified)
3. Click Submit button

**Expected Result**:
- Error message displayed: "There was an error!"
- Success message not visible
- Form rejects submission

**Valid Format Rule**: Exactly 3 uppercase letters followed by exactly 4 digits

**Test Data Coverage**:

| Invalid Format | Example | Reason |
|----------------|---------|--------|
| Lowercase letters | abc1234 | Must be uppercase |
| Too few letters | AB1234 | Must be exactly 3 letters |
| Too few digits | ABC123 | Must be exactly 4 digits |
| Too many digits | ABC12345 | Must be exactly 4 digits |
| Too many letters | ABCD2345 | Must be exactly 3 letters |
| Wrong order | 1234ABC | Letters must come first |
| Contains space | ABC 1234 | No spaces allowed |
| Mixed case | AbC1234 | All letters must be uppercase |
| Special character | Ab@1234 | Only letters and digits allowed |
| Letters only | ABCDEFG | Must include 4 digits |
| Numbers only | 2345678 | Must include 3 letters |
| Greek characters | ΓΔΕ4567 | Only English letters allowed |
| Accented letters | ÁBĆ1234 | No accented characters |
| Leading spaces | "  ZKE456" | No leading spaces |
| Trailing spaces | "ZKE456   " | No trailing spaces |
| Empty field | "" | Field is required |

---

#### 4.2.19 Submit Valid Then Invalid Data

**Objective**: Verify form correctly transitions from success to error state

**Test Steps**:
1. Enter "ABC1234" and select year "2016"
2. Click Submit (should succeed)
3. Verify success message is displayed
4. Change registration to "XXY12345" (invalid - too many digits)
5. Click Submit again

**Expected Result**:
- First submission shows success message
- Second submission shows error message
- Form correctly switches from success to error state

---

### 4.3 Invalid Year Selection Scenarios

| Test Case | Type |
|-----------|------|
| Submit without selecting year | Negative |
| Submit with both fields empty | Negative |

####  Submit Without Selecting Year

**Objective**: Verify that year selection is mandatory

**Test Steps**:
1. Enter valid car registration "IHK876"
2. Do NOT select any year from dropdown
3. Click Submit button

**Expected Result**:
- Error message displayed: "There was an error!"
- Form rejects submission
- Year is a required field

---

####  Submit With Both Fields Empty

**Objective**: Verify validation when all fields are empty

**Test Steps**:
1. Leave car registration field empty
2. Do NOT select any year
3. Click Submit button

**Expected Result**:
- Error message displayed: "There was an error!"
- Success message not visible
- Form requires both fields

---

### 4.4 Security Testing Scenarios

| Test Case | Type |
|-----------|------|
| SQL injection attempt in registration field | Security |
| XSS attempt in registration field | Security |

####  SQL Injection Attempt

**Objective**: Verify that SQL injection attempts are rejected and do not cause vulnerabilities

**Test Steps**:
1. Enter SQL injection payload: `ABC'; DROP TABLE--`
2. Select year "2017"
3. Click Submit button

**Expected Result**:
- Error message displayed: "There was an error!"
- Success message not visible
- Form rejects malicious input
- No database operations are executed

---

####  XSS (Cross-Site Scripting) Attempt

**Objective**: Verify that XSS attempts are rejected and do not execute scripts

**Test Steps**:
1. Enter XSS payload: `<script>alert('XSS')</script>`
2. Select year "2017"
3. Click Submit button

**Expected Result**:
- Error message displayed: "There was an error!"
- Success message not visible
- No script execution occurs
- Form rejects malicious input

---

### Test Reports

After each test execution:
- **HTML Report**: `target/cucumber-reports/cucumber-report.html`
- **JSON Report**: `target/cucumber-reports/cucumber.json`
- **TestNG Report**: `target/surefire-reports/`
- **Screenshots**: `target/screenshots/` (on failure)

---

## 13. Test Case Summary

### Test Distribution

| Category | Test Cases |
|----------|------------|
| Valid Scenarios | 6 |
| Invalid Registration Format | 19 |
| Invalid Year Selection | 2 |
| Security Testing | 2 |
| **Total** | **29** |


