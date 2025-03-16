package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.ui.pageObject.MainPage.aboutButton;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.navigationButton;

import android.content.Intent;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Data;

public class AboutPage {
    public static ViewInteraction backFromAboutButton = onView(withId(R.id.about_back_image_button));
    public static ViewInteraction trademarkImage = onView(withId(R.id.trademark_image_view));
    public static ViewInteraction versionText = onView(withId(R.id.about_version_title_text_view));
    public static ViewInteraction versionValueText = onView(withId(R.id.about_version_value_text_view));
    public static ViewInteraction privacyPolicyText = onView(withId(R.id.about_privacy_policy_label_text_view));
    public static ViewInteraction privacyPolicyLink = onView(withId(R.id.about_privacy_policy_value_text_view));
    public static ViewInteraction termsOfUseText = onView(withId(R.id.about_terms_of_use_label_text_view));
    public static ViewInteraction termsOfUseLink = onView(withId(R.id.about_terms_of_use_value_text_view));
    public static ViewInteraction aboutCompanyText = onView(withId(R.id.about_company_info_label_text_view));

    public void checkingElemInAboutPage(){
        Allure.step("Проверка наличия элементов страницы О нас");
        trademarkImage.check(matches(isDisplayed()));
        versionText.check(matches(isDisplayed()));
        versionValueText.check(matches(isDisplayed()));
        privacyPolicyText.check(matches(isDisplayed()));
        privacyPolicyLink.check(matches(isDisplayed()));
        termsOfUseText.check(matches(isDisplayed()));
        termsOfUseLink.check(matches(isDisplayed()));
        aboutCompanyText.check(matches(isDisplayed()));
    }

    public void toAboutFromNavButton(){
        Allure.step("Переход на страницу О нас через кнопку навигации");
        navigationButton.perform(click());
        aboutButton.perform(click());
        checkingElemInAboutPage();
    }

    public void backButtonInAbout(){
        Allure.step("Нажатие кнопки Назад на странице О наса");
        backFromAboutButton.check(matches(isDisplayed()));
        backFromAboutButton.perform(click());
    }
    public void goToTheLinkPrivacyPolicy() {
        Allure.step("Переход по ссылке Политика конфиденциальности на странице О нас");
        privacyPolicyLink.perform(click());
        intended(hasAction(Intent.ACTION_VIEW));
        intended(hasData(Data.linkOfPrivacyPolicy));
    }

    public void goToLinkTermsOfUse(){
        Allure.step("Переход по ссылке Пользовательское соглашение на странице О нас");
        termsOfUseLink.perform(click());
        intended(hasAction(Intent.ACTION_VIEW));
        intended(hasData(Data.linkOfTermsOfUse));
    }
}