package ru.iteco.fmhandroid.ui.pageObject;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.data.Data.addNewsEmptyFieldsMessage;
import static ru.iteco.fmhandroid.ui.pageObject.LoginPage.waitId;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewsPage {
    public static ViewInteraction navigationButton = onView(withId(R.id.main_menu_image_button));
    public static ViewInteraction newsButton = onView(withText("News"));
    public static ViewInteraction newsList = onView(withId(R.id.news_list_recycler_view));
    public static ViewInteraction newsEditButton = onView(withId(R.id.edit_news_material_button));
    public static ViewInteraction controlPanelAddNewsButton = onView(withId(R.id.add_news_image_view));
    public static ViewInteraction newsItemCategory = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction newsItemTitle = onView(withId(R.id.news_item_title_text_input_edit_text));
    public static ViewInteraction newsItemPublicationDate = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public static ViewInteraction newsItemPublicationTime = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public static ViewInteraction newsItemDescription = onView(withId(R.id.news_item_description_text_input_edit_text));
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public static ViewInteraction controlPanelFilterNewsButton = onView(withId(R.id.filter_news_material_button));
    public static ViewInteraction controlPanelSortNewsButton = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction controlPanelDeleteNewsButton = onView(withId(R.id.delete_news_item_image_view));
    public static ViewInteraction filterNewsDateStart = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    public static ViewInteraction filterNewsDateEnd = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));

    public static ViewInteraction filterButton = onView(withId(R.id.filter_button));
    public static int filter_Button = R.id.filter_button;
    public static ViewInteraction checkTitle = onView(allOf(withId(R.id.news_item_title_text_view), withText(Data.newsTitle), isDisplayed()));
    public static ViewInteraction checkEditTitle = onView(allOf(withId(R.id.news_item_title_text_view), withText(Data.newsChangeTitle), isDisplayed()));
    public static ViewInteraction okButtonToDeleteNews = onView(allOf(withId(android.R.id.button1), withText("OK")));
    public static ViewInteraction controlPanelEditNewsButton = onView(withId(R.id.edit_news_item_image_view));
    public static ViewInteraction viewNewsItem = onView(withId(R.id.view_news_item_image_view));
    public View decorView;
    public void goToNewsPageFromNavButton() {
        Allure.step("Переход на страницу новостей через кнопку навигации");
        navigationButton.perform(click());
        newsButton.perform(click());
        checkNewsElements();
    }

//    public void clickOnNews(int index) {
//        newsList.perform(actionOnItemAtPosition(index,click()));
//    }

    public void checkNewsElements(){
        Allure.step("Проверка наличия элементов страницы новостей");
        newsEditButton.check(matches(isDisplayed()));
    }

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return dateFormat.format(new Date());
    }
    public void addNewsWithTodayPublicationDateAndSort(){
        Allure.step("Проверка добавления новости с текущей датой публикации");
        newsEditButton.perform(click());
        controlPanelAddNewsButton.perform(click());
        newsItemCategory.perform(replaceText(Data.newsCategory));
        newsItemTitle.perform(replaceText(Data.newsTitle));
        newsItemPublicationDate.perform(replaceText(getCurrentDate()));
        newsItemPublicationTime.perform(replaceText(Data.newsPublicationTime));
        newsItemDescription.perform(replaceText(Data.newsDescription));
        saveButton.perform(click());
    }

    public void addNews(){
        Allure.step("Проверка добавления новости");
        newsEditButton.perform(click());
        controlPanelAddNewsButton.perform(click());
        newsItemCategory.perform(replaceText(Data.newsCategory));
        newsItemTitle.perform(replaceText(Data.newsTitle));
        newsItemPublicationDate.perform(replaceText(Data.newsPublicationDate));
        newsItemPublicationTime.perform(replaceText(Data.newsPublicationTime));
        newsItemDescription.perform(replaceText(Data.newsDescription));
        saveButton.perform(click());
    }

    public void addNewsWithEmptyFields(){
        Allure.step("Проверка добавления новости с пустыми полями");
        newsEditButton.perform(click());
        controlPanelAddNewsButton.perform(click());
        saveButton.perform(click());
    }

    public void checkEditNews(){
        Allure.step("Проверка возможности добавления новости");
        controlPanelAddNewsButton.check(matches(isDisplayed()));
    }

    public void checkAddedNewsInControlPanel(){
        Allure.step("Проверка наличия добавленной новости");
        checkTitle.check(matches(withText(Data.newsTitle)));
    }

    public void checkAddedNewsIsEditedInControlPanel(){
        Allure.step("Проверка результата редактирования новости");
        checkEditTitle.check(matches(withText(Data.newsChangeTitle)));
    }
    public void checkAddedNewsIsDeleted(){
        Allure.step("Проверка результата удаления новости");
        onView(allOf(withText(Data.newsTitle), isDisplayed())).check(doesNotExist());
    }

    public void filterNews(){
        Allure.step("Проверка работы фильтра новостей по диапазону дат");
        controlPanelFilterNewsButton.perform(click());
        filterNewsDateStart.perform(replaceText(Data.newsPublicationDate));
        filterNewsDateEnd.perform(replaceText(Data.newsPublicationDate));
        onView(isRoot()).perform(waitId((filter_Button), 5000));
        filterButton.perform(click());
    }

    public void filterTodayNews(){
        Allure.step("Проверка работы фильтра новостей по текущей дате");
        controlPanelFilterNewsButton.perform(click());
        filterNewsDateStart.perform(replaceText(getCurrentDate()));
        filterNewsDateEnd.perform(replaceText(getCurrentDate()));
        onView(isRoot()).perform(waitId((filter_Button), 5000));
        filterButton.perform(click());
    }

    public void deleteNews(){
        Allure.step("Удаление новости");
        controlPanelDeleteNewsButton.perform(click());
        okButtonToDeleteNews.perform(click());
    }

    public void editNews(){
        Allure.step("Редактирование новости");
        controlPanelEditNewsButton.perform(click());
        newsItemTitle.perform(click());
        newsItemTitle.perform(replaceText(Data.newsChangeTitle));
        saveButton.perform(click());
    }
    public void checkEmptyFieldsMessage(){
        Allure.step("Всплывающее сообщение о пустых полях в новости");
        onView(withText(addNewsEmptyFieldsMessage))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}