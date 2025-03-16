package ru.iteco.fmhandroid.ui.test;

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
import ru.iteco.fmhandroid.ui.pageObject.AboutPage;
import ru.iteco.fmhandroid.ui.pageObject.LoginPage;
import ru.iteco.fmhandroid.ui.pageObject.LoveIsAllPage;
import ru.iteco.fmhandroid.ui.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.NewsPage;

@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {
    LoginPage loginpage = new LoginPage();
    NewsPage newsPage = new NewsPage();
    AboutPage aboutPage = new AboutPage();
    MainPage mainPage = new MainPage();
    LoveIsAllPage loveIsAllPage = new LoveIsAllPage();
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void authorisationInApp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
        try {
            loginpage.appDownload();
        } catch (Exception e) {
            loginpage.exitFromApp();
        }
        loginpage.authorisationInApp();
        newsPage.goToNewsPageFromNavButton();
    }

    @Epic(value = "Тестирование страницы новостей")
    @Test
    @DisplayName("Проверка добавления новости")
    public void addNews(){
        newsPage.addNews();
        newsPage.checkEditNews();
        newsPage.filterNews();
        newsPage.checkAddedNewsInControlPanel();
    }

    @Epic(value = "Тестирование страницы новостей")
    @Test
    @DisplayName("Проверка добавления новости с пустыми полями")
    public void addNewsWithEmptyFields(){
        newsPage.addNewsWithEmptyFields();
        newsPage.checkEmptyFieldsMessage();
    }

    @Epic(value = "Тестирование страницы новостей")
    @Test
    @DisplayName("Проверка удаления новости")
    public void deleteNewsWithTodayPublicationDate(){
        newsPage.addNewsWithTodayPublicationDateAndSort();
        newsPage.filterTodayNews();
        newsPage.checkAddedNewsInControlPanel();
        newsPage.deleteNews();
        newsPage.checkAddedNewsIsDeleted();

    }

    @Epic(value = "Тестирование страницы новостей")
    @Test
    @DisplayName("Проверка редактирования новости")
    public void changeNews(){
        newsPage.addNewsWithTodayPublicationDateAndSort();
        newsPage.filterTodayNews();
        newsPage.checkAddedNewsInControlPanel();
        newsPage.checkEditNews();
        newsPage.editNews();
        newsPage.checkAddedNewsIsEditedInControlPanel();
    }
}