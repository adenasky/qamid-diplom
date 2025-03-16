package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.pageObject.LoginPage.*;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainPage {
    public static ViewInteraction mainButton = onView(withText("Main"));
    public static ViewInteraction aboutButton = onView(withText("About"));
    public static ViewInteraction loveIsAllButton = onView(withId(R.id.our_mission_image_button));
    public static ViewInteraction navigationButton = onView(withId(R.id.main_menu_image_button));
    public static ViewInteraction newsDropdownButton = onView(withId(R.id.expand_material_button));

    public void checkingElemInMainPage(){
        Allure.step("Проверка наличия элементов главной страницы");
        allNews.check(matches(isDisplayed()));
    }

    public void newsDropdownButtonInMain(){
        Allure.step("Проверка работы выпадающего списка новостей на главной странице");
        newsDropdownButton.check(matches(isDisplayed()));
        newsDropdownButton.perform(click());
        allNews.check(matches(not(isDisplayed())));
        newsDropdownButton.perform(click());
        checkingElemInMainPage();
    }

    public void toMainFromNavButton(){
        Allure.step("Переход на главную страницу через кнопку навигации");
        navigationButton.perform(click());
        mainButton.perform(click());
        checkingElemInMainPage();
    }
}