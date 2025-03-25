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
import io.qameta.allure.kotlin.Allure;
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


        Allure.step("Авторизация с валидными данными");
        loginPage.authorisationInApp();
        Allure.step("Проверка главной страницы");
        mainPage.checkingElemInMainPage();
        Allure.step("Выход из приложения");
        loginPage.exitFromApp();
        Allure.step("Авторизация с валидными данными");
        loginPage.authorisationInApp();
        Allure.step("Проверка главной страницы");
        mainPage.checkingElemInMainPage();
        Allure.step("Выход из приложения");
        loginPage.exitFromApp();
        loginPage.authorisationInApp();
        mainPage.checkingElemInMainPage();
        loginPage.exitFromApp();
    }


    @Epic(value = "Тестирование страницы авторизации в приложении")
    @Test
    @DisplayName("Авторизация пользователя с неверными логином и паролем")
    public void authorizationUnregisteredUserTest() {


        Allure.step("Попытка входа с несуществующими логином и паролем");
        loginPage.authorisationFailedInApp(invalidLogin, invalidPassword);
        Allure.step("Проверка тоста об ошибке авторизации");
        loginPage.checkToastMessageUnregisteredUser();
        Allure.step("Попытка входа с несуществующими логином и паролем");
        loginPage.authorisationFailedInApp(invalidLogin, invalidPassword);
        Allure.step("Проверка тоста об ошибке авторизации");
        loginPage.checkToastMessageUnregisteredUser();
        loginPage.authorisationFailedInApp(invalidLogin, invalidPassword);
        loginPage.checkToastMessageUnregisteredUser();
    }


    @Epic(value = "Тестирование страницы авторизации в приложении")
    @Test
    @DisplayName("Авторизация пользователя с неверным логином и верным паролем")
    public void authorizationInvalidLoginValidPasswordTest() {


        Allure.step("Попытка входа с неверным логином и верным паролем");
        loginPage.authorisationFailedInApp(invalidLogin, validPassword);
        Allure.step("Проверка тоста об ошибке авторизации");
        loginPage.checkToastMessageUnregisteredUser();
        Allure.step("Попытка входа с неверным логином и верным паролем");
        loginPage.authorisationFailedInApp(invalidLogin, validPassword);
        Allure.step("Проверка тоста об ошибке авторизации");
        loginPage.checkToastMessageUnregisteredUser();
        loginPage.authorisationFailedInApp(invalidLogin, validPassword);
        loginPage.checkToastMessageUnregisteredUser();
    }


    @Epic(value = "Тестирование страницы авторизации в приложении")
    @Test
    @DisplayName("Авторизация пользователя с верным логином и неверным паролем")
    public void authorizationValidLoginInvalidPasswordTest() {


        Allure.step("Попытка входа с верным логином и неверным паролем");
        loginPage.authorisationFailedInApp(validLogin, invalidPassword);
        Allure.step("Проверка тоста об ошибке авторизации");
        loginPage.checkToastMessageUnregisteredUser();
        Allure.step("Попытка входа с верным логином и неверным паролем");
        loginPage.authorisationFailedInApp(validLogin, invalidPassword);
        Allure.step("Проверка тоста об ошибке авторизации");
        loginPage.checkToastMessageUnregisteredUser();
        loginPage.authorisationFailedInApp(validLogin, invalidPassword);
        loginPage.checkToastMessageUnregisteredUser();
    }


    @Epic(value = "Тестирование страницы авторизации в приложении")
    @Test
    @DisplayName("Авторизация пользователя с пустыми полями")
    public void authorizationEmptyFieldsTest() {


        Allure.step("Попытка входа с пустыми полями");
        loginPage.authorisationFailedInApp(emptyField, emptyField);
        Allure.step("Проверка тоста о пустых полях");
        loginPage.checkToastMessageEmptyFields();
        Allure.step("Попытка входа с пустыми полями");
        loginPage.authorisationFailedInApp(emptyField, emptyField);
        Allure.step("Проверка тоста о пустых полях");
        loginPage.checkToastMessageEmptyFields();
        loginPage.authorisationFailedInApp(emptyField, emptyField);
        loginPage.checkToastMessageEmptyFields();
    }


    @Epic(value = "Тестирование страницы авторизации в приложении")
    @Test
    @DisplayName("Авторизация пользователя с SQL инъекцией в логине и верным паролем")
    public void authorizationWithSQLInjectionInLoginTest() {


        Allure.step("Попытка входа с SQL-инъекцией в логине");
        loginPage.authorisationFailedInApp(regSQLInjection, validPassword);
        Allure.step("Проверка тоста об ошибке авторизации");
        loginPage.checkToastMessageUnregisteredUser();
        Allure.step("Попытка входа с SQL-инъекцией в логине");
        loginPage.authorisationFailedInApp(regSQLInjection, validPassword);
        Allure.step("Проверка тоста об ошибке авторизации");
        loginPage.checkToastMessageUnregisteredUser();
        loginPage.authorisationFailedInApp(regSQLInjection, validPassword);
        loginPage.checkToastMessageUnregisteredUser();
    }


    @Epic(value = "Тестирование страницы авторизации в приложении")
    @Test
    @DisplayName("Авторизация пользователя с верным паролем и SQL инъекцией в пароли")
    public void authorizationWithSQLInjectionInPasswordTest() {


        Allure.step("Попытка входа с SQL-инъекцией в пароле");
        loginPage.authorisationFailedInApp(validLogin, regSQLInjection);
        Allure.step("Проверка тоста об ошибке авторизации");
        loginPage.checkToastMessageUnregisteredUser();
        Allure.step("Попытка входа с SQL-инъекцией в пароле");
        loginPage.authorisationFailedInApp(validLogin, regSQLInjection);
        Allure.step("Проверка тоста об ошибке авторизации");
        loginPage.checkToastMessageUnregisteredUser();
        loginPage.authorisationFailedInApp(validLogin, regSQLInjection);
        loginPage.checkToastMessageUnregisteredUser();
    }
}