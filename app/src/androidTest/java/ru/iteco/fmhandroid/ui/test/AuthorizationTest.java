package ru.iteco.fmhandroid.ui.test;

import static ru.iteco.fmhandroid.ui.data.Data.emptyField;
import static ru.iteco.fmhandroid.ui.data.Data.invalidLogin;
import static ru.iteco.fmhandroid.ui.data.Data.invalidPassword;
import static ru.iteco.fmhandroid.ui.data.Data.regSQLInjection;
import static ru.iteco.fmhandroid.ui.data.Data.validLogin;
import static ru.iteco.fmhandroid.ui.data.Data.validPassword;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageObject.LoginPage;
import ru.iteco.fmhandroid.ui.pageObject.MainPage;

@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();

    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
        try {
            loginPage.appDownload();
        } catch (Exception e) {
            loginPage.exitFromApp();
        }
    }

    @Epic(value = "Тестирование страницы авторизации в приложении")
    @Test
    @DisplayName("Авторизация в приложении")
    public void authorizationRegisteredUserTest() {
        loginPage.authorisationInApp();
        mainPage.checkingElemInMainPage();
        loginPage.exitFromApp();
    }

    @Epic(value = "Тестирование страницы авторизации в приложении")
    @Test
    @DisplayName("Авторизация пользователя с неверными логином и паролем")
    public void authorizationUnregisteredUserTest() {
        loginPage.authorisationFailedInApp(invalidLogin, invalidPassword);
        loginPage.checkToastMessageUnregisteredUser();
    }

    @Epic(value = "Тестирование страницы авторизации в приложении")
    @Test
    @DisplayName("Авторизация пользователя с неверным логином и верным паролем")
    public void authorizationInvalidLoginValidPasswordTest() {
        loginPage.authorisationFailedInApp(invalidLogin, validPassword);
        loginPage.checkToastMessageUnregisteredUser();
    }

    @Epic(value = "Тестирование страницы авторизации в приложении")
    @Test
    @DisplayName("Авторизация пользователя с верным логином и неверным паролем")
    public void authorizationValidLoginInvalidPasswordTest() {
        loginPage.authorisationFailedInApp(validLogin, invalidPassword);
        loginPage.checkToastMessageUnregisteredUser();
    }

    @Epic(value = "Тестирование страницы авторизации в приложении")
    @Test
    @DisplayName("Авторизация пользователя с пустыми полями")
    public void authorizationEmptyFieldsTest() {
        loginPage.authorisationFailedInApp(emptyField, emptyField);
        loginPage.checkToastMessageEmptyFields();
    }

    @Epic(value = "Тестирование страницы авторизации в приложении")
    @Test
    @DisplayName("Авторизация пользователя с SQL инъекцией в логине и верным паролем")
    public void authorizationWithSQLInjectionInLoginTest() {
        loginPage.authorisationFailedInApp(regSQLInjection, validPassword);
        loginPage.checkToastMessageUnregisteredUser();
    }

    @Epic(value = "Тестирование страницы авторизации в приложении")
    @Test
    @DisplayName("Авторизация пользователя с верным паролем и SQL инъекцией в пароли")
    public void authorizationWithSQLInjectionInPasswordTest() {
        loginPage.authorisationFailedInApp(validLogin, regSQLInjection);
        loginPage.checkToastMessageUnregisteredUser();
    }
}