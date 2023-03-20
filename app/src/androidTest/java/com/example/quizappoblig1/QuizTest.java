package com.example.quizappoblig1;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import static java.util.Currency.getInstance;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.activity.result.ActivityResult;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.hamcrest.CustomMatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@RunWith(AndroidJUnit4.class)

public class QuizTest {

    private static int correctScoreId;
    private static int correctAttemptId;

    private ActivityScenario<QuizActivity> QuizActivityScenario;
    private ActivityScenario<MainMenuActivity> MainActivityScenario;
    private ActivityScenario<AddEntryActivity> DatabaseActivityScenario;


    @Rule
    public ActivityScenarioRule<QuizActivity> QuizActivityRule = new ActivityScenarioRule<>(QuizActivity.class);
    public ActivityScenarioRule<MainMenuActivity> MainMenuActivityRule = new ActivityScenarioRule<>(MainMenuActivity.class);
    public ActivityScenarioRule<AddEntryActivity> DatabaseActivityRule = new ActivityScenarioRule<>(AddEntryActivity.class);


    @Test
    public void testMainActivity() {

        //Fyre i gang hovud aktiviteten
        MainActivityScenario = ActivityScenario.launch(MainMenuActivity.class);

        //Sjekke at knappane kjem opp, i dette tilfellet "start quiz" knappen
        onView(withId(R.id.quiz)).check(matches(isDisplayed()));

        //Trykkje seg inn på quizzen
        onView(withId(R.id.quiz)).perform(click());

        //Sjekke at vi får opp alternativ, her då alternativ 1
        onView(withId(R.id.alt0)).check(matches(isDisplayed()));

        //Trykke på alternativ 1
        onView(withId(R.id.alt0)).perform(click());

        //Lat att mainactivity
        MainActivityScenario.close();
    }


    @Test
    public void testQuizActivity() throws InterruptedException {

        //Fyre i gang quizActivity
        QuizActivityScenario = ActivityScenario.launch(QuizActivity.class);

        //Berre sjekke at vi får opp alternativ
        onView(withId(R.id.alt0)).check(matches(isDisplayed()));
        onView(withId(R.id.alt1)).check(matches(isDisplayed()));
        onView(withId(R.id.alt2)).check(matches(isDisplayed()));

        final int correctButtonId;

        //et eller anna her for å ha knappen som er riktig.. hmm
        correctButtonId = QuizActivity.getCorrectButtonId();
        correctScoreId = QuizActivity.getCorrectScoreId();
        correctAttemptId = QuizActivity.getCorrectAttemptId();

        //Klikke korrekt alternativ og deretter sjekke score

        if (correctButtonId == 0) {
            onView(withId(R.id.alt0)).perform(click());
        } else if (correctButtonId == 1) {
            onView(withId(R.id.alt1)).perform(click());
        } else {
            onView(withId(R.id.alt2)).perform(click());
        }

        //Telle opp eitt antall på score og alternativ
        correctScoreId++;
        correctAttemptId++;

        onView(withId(R.id.textScore)).check(matches(withText((("Score: " + (correctScoreId) + "/" + (correctAttemptId))))));

        //Klikke feil alternativ og deretter sjekke score

        if (correctButtonId == 0) {
            onView(withId(R.id.alt2)).perform(click());

        } else if (correctButtonId == 1) {
            onView(withId(R.id.alt0)).perform(click());

        } else {
            onView(withId(R.id.alt1)).perform(click());

        }

        //Telle opp eit alternativ
        correctAttemptId++;
        onView(withId(R.id.textScore)).check(matches(withText("Score: " + (correctScoreId) + "/" + (correctAttemptId))));

        //Lat att quizzen
        QuizActivityScenario.close();

    }


    @Test
    public void testDatabaseActivity() {


        Intents.init();

        //Fyre opp database activity
        DatabaseActivityScenario = ActivityScenario.launch(AddEntryActivity.class);

        //Sjekke at dei fire bilda viser
        //onView(ViewMatchers.withId(R.id.animalImage)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(4)));
        onView(withId(R.id.animalImage)).check(matches(isDisplayed()));

        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Resources resources = context.getResources();

        int drawID = resources.getIdentifier("sample", "drawable", context.getPackageName());

        Uri imageURI = Uri.parse("C:\\Users\\Elias\\AndroidStudioProjects\\QuizAppOblig1\\app\\src\\main\\res\\drawable\\chrome.png");
        Intent resultData = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        System.out.println(imageURI);
        resultData.setData(imageURI);



        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData));



        //Get the number of entries before adding a new entry
        AtomicInteger initialCount = new AtomicInteger(-1);
        int number = 0;
        DatabaseActivityScenario.onActivity(activity -> {
            activity.getInstance().observe(() -> activity.getLifecycle(), animals -> initialCount.set(animals.size()));
        });

        //Testen
        onView(withId(R.id.submitEntry)).perform(click());

        number = initialCount.get();

        AtomicInteger newCount = new AtomicInteger(-1);
        DatabaseActivityScenario.onActivity(activity -> {
            activity.getInstance().observe(() -> activity.getLifecycle(), animals -> newCount.set(animals.size()));
        });

        //assertThat(newCount, is(equalTo(initialCount + 1)));

        Assert.assertEquals(number +1, newCount.get());
        Intents.release();
    }

}






