package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.pageObject.MainPage.loveIsAllButton;


import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class LoveIsAllPage {
    public static ViewInteraction loveIsAllImageButton = onView(withId(R.id.our_mission_image_button));
    public static ViewInteraction loveIsAllText = onView(withText("Love is all"));

    public void goToLoveIsAllPage(){
        Allure.step("Переход на страницу Главное - жить любя");
        loveIsAllButton.perform(click());
        loveIsAllText.check(matches(isDisplayed()));
    }

    public void checkingElemInLoveIsAllPage(){
        Allure.step("Проверка наличия элементов страницы Главное - жить любя");
        loveIsAllImageButton.check(matches(isDisplayed()));
        loveIsAllText.check(matches(isDisplayed()));
    }
}

