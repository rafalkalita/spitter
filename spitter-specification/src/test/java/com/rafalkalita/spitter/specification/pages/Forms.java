package com.rafalkalita.spitter.specification.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.id;

/**
 * User: rafalkalita
 * Date: 02/02/2014
 * Time: 20:01
 */
public class Forms extends FluentWebDriverPage {

    public Forms(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void fillTextField(String value, String fieldId) {
        input(id(fieldId)).sendKeys(value);
    }

    public void submitForm(String formId) {
        form(By.id(formId)).submit();
    }
}
