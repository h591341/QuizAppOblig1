# Obligatory Assignment 1 & 2 DAT153

## Task 2 (oblig 2)

**Test method testMainActivity():**

This method tests that clicking a button in the main-menu takes you to the right sub-activity, in this case the quiz acitivity. 

The test launches the main menu and checks if the "Start Quiz" button shows up. It will then press this button and enter the quiz activity. 
The test then checks if the first alternative is there and then clicks it. Finally, it closes the MainMenuActivity.

**Test method testQuizActivity():**

This test is testing the QuizActivity by checking that the alternatives for the quiz are displayed, 
clicking on the correct option, incrementing the score and attempt count, and then clicking on an incorrect 
option and incrementing the attempt count again. Finally, it closes the QuizActivity.

The correct alternative is retrieved by calling the static method getCorrectButtonId().

**Test method testDatabaseActivity():**

This test checks that the number of registered pictures is correct after adding an entry.

After launching the main activity the test is checking that the four pictures is displayed. It clicks the button for adding a new photo... 


## Task 3 (oblig 2)

After writing the following command in the Android Studios terminal...  

*" ./gradlew connectedAndroidTest --info"*

we get the following **output**:

```At testDataBaseActivity:

java.lang.NullPointerException: Attempt to invoke virtual method 'void androidx.lifecycle.LiveData.observe(androidx.lifecycle.LifecycleOwner, androidx.lifecycle.Observer)' on a null object reference
at com.example.quizappoblig1.QuizTest.lambda$testDatabaseActivity$2(QuizTest.java:181)
at com.example.quizappoblig1.QuizTest$$ExternalSyntheticLambda0.perform(Unknown Source:4)
at androidx.test.core.app.ActivityScenario.lambda$onActivity$2$androidx-test-core-app-ActivityScenario(ActivityScenario.java:789)
at androidx.test.core.app.ActivityScenario$$ExternalSyntheticLambda2.run(Unknown Source:4)
at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:463)
at java.util.concurrent.FutureTask.run(FutureTask.java:264)
at android.app.Instrumentation$SyncRunnable.run(Instrumentation.java:2379)
at android.os.Handler.handleCallback(Handler.java:942)
at android.os.Handler.dispatchMessage(Handler.java:99)
at android.os.Looper.loopOnce(Looper.java:201)
at android.os.Looper.loop(Looper.java:288)
at android.app.ActivityThread.main(ActivityThread.java:7872)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:548)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:936)

At testQuizActivity:

androidx.test.espresso.base.AssertionErrorHandler$AssertionFailedWithCauseError: 'an instance of android.widget.TextView and view.getText() with or without transformation to match: is "Score: 1/2"' doesn't match the selected view.
Expected: an instance of android.widget.TextView and view.getText() with or without transformation to match: is "Score: 1/2"
Got: view.getText() was "Score: 1/1" transformed text was "Score: 1/1"
View Details: MaterialTextView{id=2131231178, res-name=textScore, visibility=VISIBLE, width=525, height=193, has-focus=false, has-focusable=false, has-window-focus=true, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=androidx.constraintlayout.widget.ConstraintLayout$LayoutParams@YYYYYY, tag=null, root-is-layout-requested=false, has-input-connection=false, x=490.0, y=2563.0, text=Score: 1/1, input-type=0, ime-target=false, has-links=false}

at dalvik.system.VMStack.getThreadStackTrace(Native Method)
at java.lang.Thread.getStackTrace(Thread.java:1841)
at androidx.test.espresso.base.AssertionErrorHandler.handleSafely(AssertionErrorHandler.java:3)
at androidx.test.espresso.base.AssertionErrorHandler.handleSafely(AssertionErrorHandler.java:1)
at androidx.test.espresso.base.DefaultFailureHandler$TypedFailureHandler.handle(DefaultFailureHandler.java:4)
at androidx.test.espresso.base.DefaultFailureHandler.handle(DefaultFailureHandler.java:5)
at androidx.test.espresso.ViewInteraction.waitForAndHandleInteractionResults(ViewInteraction.java:5)
at androidx.test.espresso.ViewInteraction.check(ViewInteraction.java:12)
at com.example.quizappoblig1.QuizTest.testQuizActivity(QuizTest.java:139)
... 33 trimmed
Caused by: junit.framework.AssertionFailedError: 'an instance of android.widget.TextView and view.getText() with or without transformation to match: is "Score: 1/2"' doesn't match the selected view.
Expected: an instance of android.widget.TextView and view.getText() with or without transformation to match: is "Score: 1/2"
Got: view.getText() was "Score: 1/1" transformed text was "Score: 1/1"
View Details: MaterialTextView{id=2131231178, res-name=textScore, visibility=VISIBLE, width=525, height=193, has-focus=false, has-focusable=false, has-window-focus=true, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=androidx.constraintlayout.widget.ConstraintLayout$LayoutParams@YYYYYY, tag=null, root-is-layout-requested=false, has-input-connection=false, x=490.0, y=2563.0, text=Score: 1/1, input-type=0, ime-target=false, has-links=false}

at androidx.test.espresso.matcher.ViewMatchers.assertThat(ViewMatchers.java:16)
at androidx.test.espresso.assertion.ViewAssertions$MatchesViewAssertion.check(ViewAssertions.java:7)
at androidx.test.espresso.ViewInteraction$SingleExecutionViewAssertion.check(ViewInteraction.java:2)
at androidx.test.espresso.ViewInteraction$2.call(ViewInteraction.java:14)
at androidx.test.espresso.ViewInteraction$2.call(ViewInteraction.java:1)
at java.util.concurrent.FutureTask.run(FutureTask.java:264)
at android.os.Handler.handleCallback(Handler.java:942)
at android.os.Handler.dispatchMessage(Handler.java:99)
at android.os.Looper.loopOnce(Looper.java:201)
at android.os.Looper.loop(Looper.java:288)
at android.app.ActivityThread.main(ActivityThread.java:7872)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:548)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:936)```



