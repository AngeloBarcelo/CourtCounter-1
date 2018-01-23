package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

/**
 *  These global values are no longer needed.
    int currentScoreHome = 0;
    int currentScoreVistor = 0;
**/

    /**************************************************************************
     * GLOBAL VARIABLES                                                       *
     * The following values determine the points for each button press        *
     **************************************************************************/
    int freeThrow = 1;
    int basket = 2;
    int threePointer = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**************************************************************************
     *  FUNCTION: updateDisplay                                               *
     * ************************************************************************
     *  Purpose: changes the displayed score by adding the points passed in   *
     *           with the score currently displayed                           *
     *                                                                        *
     *  Called By: setPoints()                                                *
     *                                                                        *
     *  @param points - the number of points to add to the current score      *
     *  @param scoreID - the view ID of the team to award the points          *
     *                                                                        *
     *  VARIABLES:                                                            *
     *           scoreText - the TextView that corresponds to the team        *
     *                 that was passed in through the scoreID param           *
     *           currentScore - the integer value represented by the text of  *
     *                 the scoreText TextView                                 *
     *                                                                        *
     *  How it works:                                                         *
     *                 First, set scoreText to the TextView of the team that  *
     *                 should be awarded the points. The ID of that view was  *
     *                 passed into the function in the scoreID param.         *
     *                 Once scoreText is set to a valid TextView, extract the *
     *                 text that it currently holds in its "android:text"     *
     *                 attribute, convert it to an integer (so we can add     *
     *                 to it), and store that in the currentScore variable.   *
     *                 Then, set the text of the scoreText TextView to the    *
     *                 sum of the points parameter and currentScore.          *
     *************************************************************************/

    //Displays the given score for the Home team.
    public void updateDisplay(int points, int scoreID) {
        // get the team we want to update
        TextView scoreText = findViewById(scoreID);
        // get whatever their current score is
        int currentScore = Integer.parseInt((String)scoreText.getText());
        scoreText.setText(String.valueOf(points + currentScore));
    }

    /**************************************************************************
     *  FUNCTION: setPoints                                                   *
     **************************************************************************
     *  Purpose: Determine which team has scored based on the button that was *
     *  pressed by the user, and award the corresponding points.              *
     *                                                                        *
     *  Called By: All scoring buttons                                        *
     *                                                                        *
     *  Calls: updateDisplay()                                                *
     *                                                                        *
     *  @param v : holds the ID of the button selected for the onClick method *
     *                                                                        *
     *  VARIABLES:                                                            *
     *          pointsToAdd - the value (from the globals) assigned to each   *
     *                        score button. Initialized to 0, for safety sake *
     *          team - the id of the TextView belonging to the team that has  *
     *                 just scored                                            *
     *          side - each button has a team, and a value. side has the id   *
     *                 of the button that was pressed, and from there, we can *
     *                 determine which team that belonged to, and how many    *
     *                 points are associated with that button.                *
     *                                                                        *
     *   How it works:                                                        *
     *           When any of the buttons are pressed, they automatically send *
     *           their id to the method in their onClick attribute. Since all *
     *           of the buttons do the same thing (update the score) the only *
     *           thing we need to determine is which team was pressed, and    *
     *           what "points" value was associated with it.                  *
     *           Initially, the pointsToAdd variable is set to 0. This is a   *
     *           safety precaution because an uninitialized variable can have *
     *           BAD behavior. Alternatively, it could have been assigned a   *
     *           valid points value that's relevant to the game.              *
     *           Initially, the team variable is set to the id of the TextView*
     *           that displays the score of the visitor team. Since the team  *
     *           can only be one or the other, we assign this by default, and *
     *           if we determine later that the home team actally score, then *
     *           we change the TextView to the id of the home team's score.   *
     *           Initially, side is assigned the integer value corresponding  *
     *           with the button that was pressed. Using that information, we *
     *           can decide how many points to award, and to which team.      *
     *                                                                        *
     *           Once all those initial values are set, the first step is to  *
     *           look at side, because from there, we can tell how many points*
     *           the button was worth. Since the point value is the same no   *
     *           matter which team scored it, we're only interested with the  *
     *           value of the button that was pressed.                        *
     *                                                                        *
     *           The SWITCH/CASE statement looks at side, and no matter which *
     *           team actually scored, assigns the appropriate points to the  *
     *           pointsToAdd variable. We don't care who yet, just how much!  *
     *           A SWITCH/CASE statement is a more efficient way to write a   *
     *           bunch of if/then/else statements, particularly when the      *
     *           outcomes are the same in some cases. So, it could also have  *
     *           been written:                                                *
     *           if (side == R.id.leftOnePoint || side == rightOnePoint) {    *
     *               pointsToAdd = freeThrow;                                 *
     *           }                                                            *
     *           else ...etc                                                  *
     *                                                                        *
     *           Now that pointsToAdd has a value (governed by the global     *
     *           variables from the beginning of the program), we just need   *
     *           to see if the button id stored in the side variable belogns  *
     *           to the home team, or the visiting team. Since we initially   *
     *           stored the visiting team's score TextView in the team        *
     *           variable, we only need to check if the button pressed belong *
     *           to the home team, and if so, change the TextView id store    *
     *           in the team variable to the TextView id of the home teams's  *
     *           current score.                                               *
     *                                                                        *
     *           So now, we know how many points (in pointsToAdd variable)    *
     *           and we know which team gets the points (in the team variable.*
     *                                                                        *
     *           Now, we just take the points and the team, send that info    *
     *           off to the updateDisplay function to actually change what    *
     *           appears on the screen, and we're done!                       *
     *************************************************************************/

    public void setPoints(View v) {
        int pointsToAdd = 0; // how many points to add to the display
        // set the team to B by default, and change it only if needed
        int team = R.id.team_b_score; // which team to add it to
        int side = v.getId(); // which button was pressed, home or visitor?

        // find out how many points to add by checking the button that was pressed
        switch (side) {
            case R.id.leftOnePoint:
            case R.id.rightOnePoint:
                pointsToAdd = freeThrow;
                break;
            case R.id.leftTwoPoints:
            case R.id.rightTwoPoints:
                pointsToAdd = basket;
                break;
            default: // if it isn't a free throw or a basket, it must be a three pointer button
                pointsToAdd = threePointer;
                break;
        }

        // check to see if the home team scored
        if (side == R.id.leftOnePoint || side == R.id.leftTwoPoints || side == R.id.leftThreePoints) {
            team = R.id.team_a_score; // change the team to home
        }

        // set the points and the team to the updater
        updateDisplay (pointsToAdd, team);
    }

    // reset the scoreboard
    public void resetScore(View v) {
        TextView homeTeam = (TextView) findViewById(R.id.team_a_score);
        TextView visitorTeam = (TextView) findViewById(R.id.team_b_score);
        homeTeam.setText("0");
        visitorTeam.setText("0");
    }

    /**
    //Displays the given score for the Home team.
    public void displayForTeamA(int scoreA) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(scoreA));
    }

    public void displayForTeamB(int scoreB) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(scoreB));
    }

    public void onePoint(View v) {
        if (v.getId() == R.id.leftOnePoint) {
            currentScoreHome += freeThrow;
            displayForTeamA(currentScoreHome);
        } else if (v.getId() == R.id.rightOnePoint) {
            currentScoreVistor += freeThrow;
            displayForTeamB(currentScoreVistor);
        }
    }

    public void twoPoints(View v) {
        if (v.getId() == R.id.leftTwoPoints) {
            currentScoreHome += basket;
            displayForTeamA(currentScoreHome);
        } else if ((v.getId() == R.id.rightTwoPoints)) {
            currentScoreVistor += basket;
            displayForTeamB(currentScoreVistor);
        }
    }

    public void threePoints(View v) {
        if (v.getId() == R.id.leftThreePoints) {
            currentScoreHome += threePointer;
            displayForTeamA(currentScoreHome);
        } else if (v.getId() == R.id.rightThreePoints) {
            currentScoreVistor += threePointer;
            displayForTeamB(currentScoreVistor);
        }
    }

    public void resetScore(View v) {
        currentScoreHome = 0;
        currentScoreVistor = 0;
        displayForTeamA(currentScoreHome);
        displayForTeamB(currentScoreVistor);
    } **/
}