package com.example.quizappoblig1;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

public class QuizTest {


    @Rule
    public ActivityScenarioRule<QuizActivity> QuizActivityRule = new ActivityScenarioRule<>(QuizActivity.class);


    @Test
    public void testQuizActivity() {

                //et eller anna her for å ha knappen som er riktig.. hmm
                int button = activity.isCorrect();

                if (button == 0) {
                    onView(withId(R.id.alt0)).perform(click());
                } else if (button == 1) {
                    onView(withId(R.id.alt1)).perform(click());
                } else {
                    onView(withId(R.id.alt2)).perform(click());
                }

                onView(withId(R.id.textScore)).check(matches(withText( " right answers out of " + " questions")));

                //same her. må ha knappen som er riktig
                button = activity.isCorrect();

                if (button == 0) {
                    onView(withId(R.id.alt1)).perform(click());
                } else if (button == 1) {
                    onView(withId(R.id.alt2)).perform(click());
                } else {
                    onView(withId(R.id.alt0)).perform(click());
                }

                onView(withId(R.id.textScore)).check(matches(withText( " right answers out of " + " questions")));

            }

        };




