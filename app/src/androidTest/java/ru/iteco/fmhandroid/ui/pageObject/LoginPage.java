package ru.iteco.fmhandroid.ui.pageObject;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.data.Data.authorizationEmptyFieldsMessage;
import static ru.iteco.fmhandroid.ui.data.Data.toastMessageUnregisteredUser;
import static ru.iteco.fmhandroid.ui.data.Data.validLogin;
import static ru.iteco.fmhandroid.ui.data.Data.validPassword;

import android.view.View;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;

import java.util.concurrent.TimeoutException;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsInstanceOf;


public class LoginPage {
    public static ViewInteraction login_attr = onView((allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout))))));
    public static ViewInteraction password_attr = onView(allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout)))));
    public static ViewInteraction signInButton_attr = onView(allOf(withId(R.id.enter_button), withText("SIGN IN"), withContentDescription("Save"), withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))));
    public static ViewInteraction allNews = onView(withId(R.id.all_news_text_view));
    public static ViewInteraction logoutImage = onView(withId(R.id.authorization_image_button));
    public static ViewInteraction logoutText = onView(withText("Log out"));
    public static int enterButton = R.id.enter_button;
    private View decorView;
    public void appDownload(){
        Allure.step("Ожидание загрузки приложения");
        onView(isRoot()).perform(waitId((enterButton), 10000));
    }
    public void authorisationInApp(){
        Allure.step("Авторизация в приложении");
        login_attr.check(matches(isDisplayed()));
        login_attr.perform(replaceText(validLogin), closeSoftKeyboard());
        password_attr.perform(replaceText(validPassword), closeSoftKeyboard());
        signInButton_attr.check(matches(isDisplayed()));
        signInButton_attr.perform(click());
        onView(isRoot()).perform(waitMatcher(withText("All news"), 10000));
    }

    public void exitFromApp(){
        Allure.step("Выход из приложения");
        logoutImage.check(matches(isDisplayed()));
        logoutImage.perform(click());
        onView(isRoot()).perform(waitMatcher((withText("Log out")), 10000));
        logoutText.check(matches(isDisplayed()));
        logoutText.perform(click());
        login_attr.check(matches(isDisplayed()));
    }

    public void authorisationFailedInApp(String login, String password){
        Allure.step("Ошибка авторизации в приложении");
        onView(isRoot()).perform(waitMatcher((withId(R.id.enter_button)), 10000));
        login_attr.check(matches(isDisplayed()));
        login_attr.perform(replaceText(login), closeSoftKeyboard());
        password_attr.perform(replaceText(password), closeSoftKeyboard());
        signInButton_attr.check(matches(isDisplayed()));
        signInButton_attr.perform(click());
    }

    public void checkToastMessageUnregisteredUser(){
        Allure.step("Всплывающее сообщение об ошибке на экране");
        onView(withText(toastMessageUnregisteredUser))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    public void checkToastMessageEmptyFields(){
        Allure.step("Всплывающее сообщение о пустом поле на экране");
        onView(withText(authorizationEmptyFieldsMessage))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    public static ViewAction waitId(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };

    }
    public static ViewAction waitMatcher(final Matcher matcher, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + matcher + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = matcher;

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }
}