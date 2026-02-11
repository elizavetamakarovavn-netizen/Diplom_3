package org.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.pageobject.ConstructorPage;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConstructorTabsClickTest {
    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    @DisplayName("Переход от 'Соусов' к 'Булкам'")
    @Description("Проверяет, что после выбора вкладки 'Соусы' пользователь может переключиться на вкладку 'Булки'," +
            " и она становится активной")
    public void testSaucesToBuns() {
        ConstructorPage constructor = factory.getConstructorPage();

        constructor.clickSaucesTab();
        constructor.waitForActiveTab("Соусы");

        constructor.clickBunsTab();
        constructor.waitForActiveTab("Булки");
        assertEquals("Булки", constructor.getActiveTabName());
    }

    @Test
    @DisplayName("Переход от 'Начинок' к 'Булкам'")
    @Description("Проверяет, что при выборе вкладки 'Начинки' пользователь может перейти на вкладку 'Булки'," +
            " и она корректно активируется.")
    public void testFillingsToBuns() {
        ConstructorPage constructor = factory.getConstructorPage();

        constructor.clickFillingsTab();
        constructor.waitForActiveTab("Начинки");

        constructor.clickBunsTab();
        constructor.waitForActiveTab("Булки");

        assertEquals("Булки", constructor.getActiveTabName());
    }

    @Test
    @DisplayName("Переход от 'Булок' к 'Соусам'")
    @Description("Проверяет корректность переключения вкладок: после выбора 'Булки' пользователь может перейти" +
            " на вкладку 'Соусы', и она становится активной.")
    public void testBunsToSauces() {

        ConstructorPage constructor = factory.getConstructorPage();

        constructor.clickBunsTab();
        constructor.waitForActiveTab("Булки");

        constructor.clickSaucesTab();
        constructor.waitForActiveTab("Соусы");

        assertEquals("Соусы", constructor.getActiveTabName());
    }
    @Test
    @DisplayName("Переход от 'Булок' к 'Начинкам'")
    @Description("Проверяет, что после выбора вкладки 'Булки' пользователь может переключиться на вкладку 'Начинки'," +
            " и активная вкладка обновляется корректно.")
    public void testBunsToFillings() {

        ConstructorPage constructor = factory.getConstructorPage();

        constructor.clickBunsTab();
        constructor.waitForActiveTab("Булки");

        constructor.clickFillingsTab();
        constructor.waitForActiveTab("Начинки");

        assertEquals("Начинки", constructor.getActiveTabName());
    }
}
