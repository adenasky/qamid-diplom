package ru.iteco.fmhandroid.ui.test;


import android.view.View;


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
        Allure.step("Открыть экран добавления новости");
        newsPage.addNews();


        Allure.step("Проверить возможность редактирования новости");
        newsPage.checkEditNews();


        Allure.step("Отфильтровать список новостей");
        newsPage.filterNews();


        Allure.step("Проверить, что новость отображается в панели управления");
        newsPage.checkAddedNewsInControlPanel();
    }


    @Epic(value = "Тестирование страницы новостей")
    @Test
    @DisplayName("Проверка добавления новости с пустыми полями")
    public void addNewsWithEmptyFields(){
        Allure.step("Открыть форму добавления новости с пустыми полями");
        newsPage.addNewsWithEmptyFields();


        Allure.step("Проверить сообщение о незаполненных полях");
        newsPage.checkEmptyFieldsMessage();
    }


    @Epic(value = "Тестирование страницы новостей")
    @Test
    @DisplayName("Проверка удаления новости")
    public void deleteNewsWithTodayPublicationDate(){
        Allure.step("Добавить новость с сегодняшней датой публикации");
        newsPage.addNewsWithTodayPublicationDateAndSort();


        Allure.step("Отфильтровать новости по сегодняшней дате");
        newsPage.filterTodayNews();


        Allure.step("Убедиться, что новость отображается");
        newsPage.checkAddedNewsInControlPanel();


        Allure.step("Удалить новость");
        newsPage.deleteNews();


        Allure.step("Проверить, что новость удалена");
        newsPage.checkAddedNewsIsDeleted();
    }


    @Epic(value = "Тестирование страницы новостей")
    @Test
    @DisplayName("Проверка редактирования новости")
    public void changeNews(){
        Allure.step("Добавить новость для редактирования");
        newsPage.addNewsWithTodayPublicationDateAndSort();


        Allure.step("Отфильтровать новости по сегодняшней дате");
        newsPage.filterTodayNews();


        Allure.step("Убедиться, что новость отображается");
        newsPage.checkAddedNewsInControlPanel();


        Allure.step("Открыть редактор новости");
        newsPage.checkEditNews();


        Allure.step("Изменить содержание новости");
        newsPage.editNews();


        Allure.step("Проверить, что изменения сохранены");
        newsPage.checkAddedNewsIsEditedInControlPanel();
    }
}