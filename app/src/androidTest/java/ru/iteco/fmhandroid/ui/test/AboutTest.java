package ru.iteco.fmhandroid.ui.test;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageObject.AboutPage;
import ru.iteco.fmhandroid.ui.pageObject.LoginPage;
import ru.iteco.fmhandroid.ui.pageObject.MainPage;

@RunWith(AllureAndroidJUnit4.class)
public class AboutTest {
    LoginPage loginpage = new LoginPage();
    AboutPage aboutPage = new AboutPage();
    MainPage mainPage = new MainPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void authorisationInApp() {
        try {
            loginpage.appDownload();
        } catch (Exception e) {
            loginpage.exitFromApp();
        }
        loginpage.authorisationInApp();
    }

    @Epic("Тестирование страницы О нас")
    @Test
    @DisplayName("Проверка наличия элементов страницы О нас")
    public void checkingAllElemInAbout(){
        Allure.step("Открываем экран О нас через меню навигации");
        aboutPage.toAboutFromNavButton();

        Allure.step("Нажимаем кнопку Назад");
        aboutPage.backButtonInAbout();

        Allure.step("Проверяем элементы на главной странице");
        mainPage.checkingElemInMainPage();
    }

    @Epic("Тестирование страницы О нас")
    @Test
    @DisplayName("Переход по ссылке Политика конфиденциальности на странице О нас")
    public void goToLinkPrivacyPolicy(){
        Intents.init();
        Allure.step("Открываем экран О нас через меню навигации");
        aboutPage.toAboutFromNavButton();

        Allure.step("Переходим по ссылке Политика конфиденциальности");
        aboutPage.goToTheLinkPrivacyPolicy();
        Intents.release();
    }

    @Epic("Тестирование страницы О нас")
    @Test
    @DisplayName("Переход по ссылке Пользовательское соглашение на странице О нас")
    public void goToLinkTermsOfUse(){
        Intents.init();
        Allure.step("Открываем экран О нас через меню навигации");
        aboutPage.toAboutFromNavButton();

        Allure.step("Переходим по ссылке Пользовательское соглашение");
        aboutPage.goToLinkTermsOfUse();
        Intents.release();
    }
}