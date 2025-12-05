package org.tests;

import io.qameta.allure.junit4.DisplayName;
import org.example.pageobject.Constructor;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConstructorTabsClickTest {
    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    @DisplayName("Переход от 'Соусов' к 'Булкам'")
    public void testSaucesToBuns() {
        Constructor constructor = factory.getConstructorPage();

        constructor.clickSaucesTab();
        constructor.waitForActiveTab("Соусы");

        constructor.clickBunsTab();
        constructor.waitForActiveTab("Булки");
        assertEquals("Булки", constructor.getActiveTabName());
    }

    @Test
    @DisplayName("Переход от 'Начинок' к 'Булкам'")
    public void testFillingsToBuns() {
        Constructor constructor = factory.getConstructorPage();

        constructor.clickFillingsTab();
        constructor.waitForActiveTab("Начинки");

        constructor.clickBunsTab();
        constructor.waitForActiveTab("Булки");

        assertEquals("Булки", constructor.getActiveTabName());
    }

    @Test
    @DisplayName("Переход от 'Булок' к 'Соусам'")
    public void testBunsToSauces() {

        Constructor constructor = factory.getConstructorPage();

        constructor.clickBunsTab();
        constructor.waitForActiveTab("Булки");

        constructor.clickSaucesTab();
        constructor.waitForActiveTab("Соусы");

        assertEquals("Соусы", constructor.getActiveTabName());
    }
    @Test
    @DisplayName("Переход от 'Булок' к 'Начинкам'")
    public void testBunsToFillings() {

        Constructor constructor = factory.getConstructorPage();

        constructor.clickBunsTab();
        constructor.waitForActiveTab("Булки");

        constructor.clickFillingsTab();
        constructor.waitForActiveTab("Начинки");

        assertEquals("Начинки", constructor.getActiveTabName());
    }
}
