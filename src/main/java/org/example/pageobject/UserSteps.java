package org.example.pageobject;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.example.pageobject.model.User;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserSteps {
    public static final String USER = "/api/auth/register";
    public static final String DELETE = "/api/auth/user/";
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public UserSteps() {
        RestAssured.baseURI = "https://stellarburgers.education-services.ru";
    }

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        ValidatableResponse response = given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(USER)
                .then();

        String token = response.extract().path("accessToken");
        if (token != null) {
            this.accessToken = token.replace("Bearer ", "");
        }
        return response;
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .header("Authorization", "Bearer " + accessToken.replace("Bearer ", ""))
                .when()
                .delete(DELETE)
                .then();
    }
}
