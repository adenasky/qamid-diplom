package ru.iteco.fmhandroid.ui.test;


import static androidx.test.espresso.action.ViewActions.click;


import static ru.iteco.fmhandroid.ui.pageObject.LoginPage.allNews;


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
import ru.iteco.fmhandroid.ui.pageObject.AboutPage;
import ru.iteco.fmhandroid.ui.pageObject.LoginPage;
import ru.iteco.fmhandroid.ui.pageObject.LoveIsAllPage;
import ru.iteco.fmhandroid.ui.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.NewsPage;


@RunWith(AllureAndroidJUnit4.class)
public class NavigationTest {


    LoginPage loginpage = new LoginPage();
    AboutPage aboutPage = new AboutPage();
    MainPage mainPage = new MainPage();
    LoveIsAllPage loveIsAllPage = new LoveIsAllPage();
    NewsPage newsPage = new NewsPage();


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


    @Epic(value = "Тестирование навигации")
    @Test
    @DisplayName("Проверка перехода с главной страницы на страницу новостей")
    public void fromMainToNewsTest(){
        newsPage.goToNewsPageFromNavButton();
    }


    @Epic(value = "Тестирование навигации")
    @Test
    @DisplayName("Проверка перехода с главной страницы на страницу О нас")
    public void fromMainToAbout(){
        aboutPage.toAboutFromNavButton();
        aboutPage.backButtonInAbout();
        mainPage.checkingElemInMainPage();
    }


    @Epic(value = "Тестирование навигации")
    @Test
    @DisplayName("Проверка перехода с виджета новостей на главной странице на страницу новостей и обратно")
    public void fromAllNewsInMainToNewsAndToMainTest(){
        allNews.perform(click());
        mainPage.toMainFromNavButton();
    }


    @Epic(value = "Тестирование навигации")
    @Test
    @DisplayName("Проверка перехода с главной страницы на страницу новостей, страницу О нас и обратно")
    public void fromMainToNewsToAboutTestTest() {
        Allure.step("Переход с главной на новости, затем на О нас и обратно");
        newsPage.goToNewsPageFromNavButton();
        aboutPage.toAboutFromNavButton();
        aboutPage.backButtonInAbout();
        mainPage.checkingElemInMainPage();
    }


    @Epic(value = "Тестирование навигации")
    @Test
    @DisplayName("Проверка перехода с главной страницы на страницу Главное - жить любя")
    public void fromMainToLove(){
        loveIsAllPage.goToLoveIsAllPage();
    }


    @Epic(value = "Тестирование навигации")
    @Test
    @DisplayName("Проверка перехода со страницы Главное - жить любя на главную страницу")
    public void fromLoveToMain(){
        loveIsAllPage.goToLoveIsAllPage();
        mainPage.toMainFromNavButton();
    }


    @Epic(value = "Тестирование навигации")
    @Test
    @DisplayName("Проверка перехода со страницы Главное - жить любя на страницу новостей")
    public void fromLoveToNews(){
        loveIsAllPage.goToLoveIsAllPage();
        newsPage.goToNewsPageFromNavButton();
    }


    @Epic(value = "Тестирование навигации")
    @Test
    @DisplayName("Проверка перехода со страницы Главное - жить любя на страницу О нас")
    public void fromLoveToAbout(){
        loveIsAllPage.goToLoveIsAllPage();
        aboutPage.toAboutFromNavButton();
        aboutPage.backButtonInAbout();
        loveIsAllPage.checkingElemInLoveIsAllPage();
    }
}