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

After writhing the following... 
" ./gradlew connectedAndroidTest --info", in Android Studios terminal, we get the following output:




