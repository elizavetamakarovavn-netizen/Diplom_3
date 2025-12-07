package org.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.pageobject.ConstructorPage;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConstructorScrollTests {
    @Rule
    public DriverFactory factory = new DriverFactory();


    @Test
    @DisplayName("Скролл вниз активирует вкладки Соусы и Начинки")
    @Description("Проверяет, что при прокрутке страницы вниз последовательно активируются вкладки 'Соусы' и 'Начинки'")
    public void testScrollDown() {

        ConstructorPage constructor = factory.getConstructorPage();

        constructor.scrollTo("Соусы");
        constructor.waitForActiveTab("Соусы");

        assertEquals("Соусы", constructor.getActiveTabName());

        constructor.scrollTo("Начинки");
        constructor.waitForActiveTab("Начинки");

        assertEquals("Начинки", constructor.getActiveTabName());
    }

    @Test
    @DisplayName("Скролл вверх активирует вкладки Начинки, Соусы и Булки")
    @Description("Проверяет, что при прокрутке страницы вверх элементы списка активируют вкладки 'Начинки', затем 'Соусы' и 'Булки'")
    public void testScrollUp() {

        ConstructorPage constructor = factory.getConstructorPage();

        constructor.scrollTo("Начинки");
        constructor.waitForActiveTab("Начинки");

        constructor.scrollTo("Соусы");
        constructor.waitForActiveTab("Соусы");

        constructor.scrollTo("Булки");
        constructor.waitForActiveTab("Булки");
    }

}
