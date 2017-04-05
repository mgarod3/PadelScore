package com.example.android.padelscore;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String SCORE_TEAM_A = "scoreTeamA";
    static final String SCORE_TEAM_B = "scoreTeamB";
    static final String GAMES_SCORE_TEAM_A = "gamesScoreTeamA";
    static final String GAMES_SCORE_TEAM_B = "gamesScoreTeamB";
    static final String SET_SCORE_TEAM_A = "setScoreTeamA";
    static final String SET_SCORE_TEAM_B = "setScoreTeamB";
    static final String VISIBILITY_A = "visibilityA";
    static final String VISIBILITY_B = "visibilityB";
    static final String ALERT_MESSAGE = "alertMessage";
    static final String GAME_BUTTON_ENABLE = "gameButtonEnable";
    static final String SET_BUTTON_ENABLE = "setButtonEnable";
    static final String POINT_BUTTON_ENABLE = "pointButtonEnable";
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int gamesScoreTeamA = 0;
    int gamesScoreTeamB = 0;
    int setScoreTeamA = 0;
    int setScoreTeamB = 0;
    int numTotalSets = 3;
    String alertMessage = "";
    Button buttonGame;
    Button buttonSet;
    Button buttonPointA;
    Button buttonPointB;
    TextView scoreViewA;
    TextView scoreViewB;
    TextView alertViewA;
    TextView alertViewB;
    TextView scoreGameA;
    TextView scoreGameB;
    TextView scoreSetA;
    TextView scoreSetB;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        boolean visibilityA;
        boolean visibilityB;
        boolean gameButtonEnable;
        boolean setButtonEnable;
        boolean pointButtonEnable;

        if (alertViewA.getVisibility() == View.VISIBLE) {
            visibilityA = true;
        } else {
            visibilityA = false;
        }
        if (alertViewB.getVisibility() == View.VISIBLE) {
            visibilityB = true;
        } else {
            visibilityB = false;
        }
        if (buttonGame.isEnabled()) {
            gameButtonEnable = true;
        } else {
            gameButtonEnable = false;
        }
        if (buttonSet.isEnabled()) {
            setButtonEnable = true;
        } else {
            setButtonEnable = false;
        }
        if (buttonPointA.isEnabled()) {
            pointButtonEnable = true;
        } else {
            pointButtonEnable = false;
        }
        savedInstanceState.putBoolean(GAME_BUTTON_ENABLE, gameButtonEnable);
        savedInstanceState.putBoolean(SET_BUTTON_ENABLE, setButtonEnable);
        savedInstanceState.putBoolean(POINT_BUTTON_ENABLE, pointButtonEnable);
        savedInstanceState.putBoolean(VISIBILITY_A, visibilityA);
        savedInstanceState.putBoolean(VISIBILITY_B, visibilityB);
        savedInstanceState.putInt(SCORE_TEAM_A, scoreTeamA);
        savedInstanceState.putInt(SCORE_TEAM_B, scoreTeamB);
        savedInstanceState.putInt(GAMES_SCORE_TEAM_A, gamesScoreTeamA);
        savedInstanceState.putInt(GAMES_SCORE_TEAM_B, gamesScoreTeamB);
        savedInstanceState.putInt(SET_SCORE_TEAM_A, setScoreTeamA);
        savedInstanceState.putInt(SET_SCORE_TEAM_B, setScoreTeamB);
        savedInstanceState.putString(ALERT_MESSAGE, alertMessage);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        scoreTeamA = savedInstanceState.getInt(SCORE_TEAM_A);
        scoreTeamB = savedInstanceState.getInt(SCORE_TEAM_B);
        gamesScoreTeamA = savedInstanceState.getInt(GAMES_SCORE_TEAM_A);
        gamesScoreTeamB = savedInstanceState.getInt(GAMES_SCORE_TEAM_B);
        setScoreTeamA = savedInstanceState.getInt(SET_SCORE_TEAM_A);
        setScoreTeamB = savedInstanceState.getInt(SET_SCORE_TEAM_B);
        alertMessage = savedInstanceState.getString(ALERT_MESSAGE);
        boolean visibilityA = savedInstanceState.getBoolean(VISIBILITY_A);
        boolean visibilityB = savedInstanceState.getBoolean(VISIBILITY_B);
        boolean gameButtonEnable = savedInstanceState.getBoolean(GAME_BUTTON_ENABLE);
        boolean setButtonEnable = savedInstanceState.getBoolean(SET_BUTTON_ENABLE);
        boolean pointButtonEnable = savedInstanceState.getBoolean(POINT_BUTTON_ENABLE);
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayGamesForTeamA(gamesScoreTeamA);
        displayGamesForTeamB(gamesScoreTeamB);
        displaySetsForTeamA(setScoreTeamA);
        displaySetsForTeamB(setScoreTeamB);
        if (alertMessage.equals(getString(R.string.deuce))){
            displayAlertForTeamA(alertMessage);
            displayAlertForTeamB(alertMessage);
        }else if (alertMessage.equals(getString(R.string.advantage_a))){
            displayAlertForTeamA(alertMessage);
        }else{
            displayAlertForTeamB(alertMessage);
        }
        if (visibilityA) {
            if (alertMessage.equals(getString(R.string.game_winner)) || alertMessage.equals(getString(R.string.set_winner)) || alertMessage.equals(getString(R.string.match_winner))) {
                displayAlertForTeamA(alertMessage);
            }
            alertVisible(getString(R.string.a));
        }
        if (visibilityB) {
            if (alertMessage.equals(getString(R.string.game_winner)) || alertMessage.equals(getString(R.string.set_winner)) || alertMessage.equals(getString(R.string.match_winner))) {
                displayAlertForTeamB(alertMessage);
            }
            alertVisible(getString(R.string.b));
        }

        //enable/disable buttons

        if (gameButtonEnable) {
            enableNewGameButton();
        } else {
            disableNewGameButton();
        }
        if (setButtonEnable) {
            enableNewSetButton();
        } else {
            disableNewSetButton();
        }
        if (pointButtonEnable) {
            enablePointButtons();
        } else {
            disablePointButtons();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonGame = (Button) findViewById(R.id.new_game_button);
        buttonSet = (Button) findViewById(R.id.new_set_button);
        buttonPointA = (Button) findViewById(R.id.point_button_a);
        buttonPointB = (Button) findViewById(R.id.point_button_b);
        scoreViewA = (TextView) findViewById(R.id.team_a_score);
        scoreViewB = (TextView) findViewById(R.id.team_b_score);
        alertViewA = (TextView) findViewById(R.id.team_a_alerts);
        alertViewB = (TextView) findViewById(R.id.team_b_alerts);
        scoreGameA = (TextView) findViewById(R.id.game_a_score);
        scoreGameB = (TextView) findViewById(R.id.game_b_score);
        scoreSetA = (TextView) findViewById(R.id.set_a_score);
        scoreSetB = (TextView) findViewById(R.id.set_b_score);
        if (savedInstanceState == null) {
            disableNewSetButton();
            disableNewGameButton();
        }
    }

    //Adds a point for Team A

    public void pointForTeamA(View view) {
        String team = getString(R.string.a);
        if (scoreTeamA < 40) {
            switch (scoreTeamA) {
                case 0:
                    scoreTeamA = 15;
                    break;
                case 15:
                    scoreTeamA = 30;
                    break;
                case 30:
                    scoreTeamA = 40;
                    break;
            }
            displayForTeamA(scoreTeamA);
            if (scoreTeamA == 40 && scoreTeamB == 40) {
                displayDeuce();
            }
        } else if (alertMessage.equals("")) {

            if (scoreTeamB < 40) {
                gameWinner(team);
            } else {
                displayDeuce();
            }
        } else if (alertMessage.equals(getString(R.string.deuce))) {
            alertMessage = getString(R.string.advantage_a);
            displayAlertForTeamA(alertMessage);
            alertInvisible(getString(R.string.b));
        } else if (alertMessage.equals(getString(R.string.advantage_b))) {
            displayDeuce();
        } else {
            gameWinner(team);
        }
    }

    //Adds a point for Team B

    public void pointForTeamB(View view) {
        String team = getString(R.string.b);
        if (scoreTeamB < 40) {
            switch (scoreTeamB) {
                case 0:
                    scoreTeamB = 15;
                    break;
                case 15:
                    scoreTeamB = 30;
                    break;
                case 30:
                    scoreTeamB = 40;
                    break;
            }
            displayForTeamB(scoreTeamB);
            if (scoreTeamA == 40 && scoreTeamB == 40) {
                displayDeuce();
            }
        } else if (alertMessage.equals("")) {
            alertVisible(team);
            if (scoreTeamA < 40) {
                gameWinner(team);
            } else {
                displayDeuce();
            }
        } else if (alertMessage.equals(getString(R.string.deuce))) {
            alertMessage = getString(R.string.advantage_b);
            displayAlertForTeamB(alertMessage);
            alertInvisible(getString(R.string.a));
        } else if (alertMessage.equals(getString(R.string.advantage_a))) {
            displayDeuce();
        } else {
            gameWinner(team);
        }
    }

    //Show messages on screen - Replaced by sending mail.

    public void showMessage(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        scoreViewA.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        scoreViewB.setText(String.valueOf(score));
    }

    // Displays alert message above Team A score

    public void displayAlertForTeamA(String AlertMessage) {
        alertViewA.setText(String.valueOf(AlertMessage));
    }

    // Displays alert message above Team B score

    public void displayAlertForTeamB(String AlertMessage) {
        alertViewB.setText(String.valueOf(AlertMessage));
    }

    // Display message "Game Winner" above the winner score and shows instructions on screen

    public void gameWinner(String gameWinner) {
        alertMessage = getString(R.string.game_winner);
        alertVisible(gameWinner);
        if (gameWinner.equals(getString(R.string.a))) {
            displayAlertForTeamA(alertMessage);
        } else {
            displayAlertForTeamB(alertMessage);
        }
        disablePointButtons();
        enableNewGameButton();
        String message = getString(R.string.winner_message, gameWinner, getString(R.string.game));
        showMessage(message);
        updateGamesScore(gameWinner);
        if (gamesScoreTeamA >= 6 || gamesScoreTeamB >= 6) {
            checkGamesScore();
        } else {
            showMessageGame();
        }
    }


    // Display "Deuce" above both scores

    public void displayDeuce() {
        alertVisible(getString(R.string.a));
        alertVisible(getString(R.string.b));
        alertMessage = getString(R.string.deuce);
        displayAlertForTeamA(alertMessage);
        displayAlertForTeamB(alertMessage);
    }


    //Updates the Games Score

    public void updateGamesScore(String team) {
        if (team.equals(getString(R.string.a))) {
            gamesScoreTeamA = gamesScoreTeamA + 1;
            displayGamesForTeamA(gamesScoreTeamA);
        } else {
            gamesScoreTeamB = gamesScoreTeamB + 1;
            displayGamesForTeamB(gamesScoreTeamB);
        }
    }

    //Checks if the team wins also a set

    public void checkGamesScore() {
        if (gamesScoreTeamA - gamesScoreTeamB >= 2) {
            setWinner(getString(R.string.a));
        } else if (gamesScoreTeamB - gamesScoreTeamA >= 2) {
            setWinner(getString(R.string.b));
        } else {
            showMessageGame();
        }
    }

    //Displays set winner

    public void setWinner(String setWinner) {
        alertMessage = getString(R.string.set_winner);
        alertVisible(setWinner);
        if (setWinner.equals(getString(R.string.a))) {
            displayAlertForTeamA(alertMessage);
        } else {
            displayAlertForTeamB(alertMessage);
        }
        disablePointButtons();
        disableNewGameButton();
        enableNewSetButton();
        String message = getString(R.string.winner_message, setWinner, getString(R.string.set));
        showMessage(message);
        updateSetScore(setWinner);
    }


    //Updates Set Score and check if the team wins also match

    public void updateSetScore(String team) {
        if (team.equals(getString(R.string.a))) {
            setScoreTeamA = setScoreTeamA + 1;
            displaySetsForTeamA(setScoreTeamA);
            if (numTotalSets == 3 && setScoreTeamA == 2) {
                matchWinner(team);
            } else if (numTotalSets == 5 && setScoreTeamA == 3) {
                matchWinner(team);
            } else {
                showMessageSet();
            }
        } else {
            setScoreTeamB = setScoreTeamB + 1;
            displaySetsForTeamB(setScoreTeamB);
            if (numTotalSets == 3 && setScoreTeamB == 2) {
                matchWinner(team);
            } else if (numTotalSets == 5 && setScoreTeamB == 3) {
                matchWinner(team);
            } else {
                showMessageSet();
            }
        }
    }

    //Displays match Winner

    public void matchWinner(String matchWinner) {
        alertMessage = getString(R.string.match_winner);
        alertVisible(matchWinner);
        if (matchWinner.equals(getString(R.string.a))) {
            displayAlertForTeamA(alertMessage);
        } else {
            displayAlertForTeamB(alertMessage);
        }
        disablePointButtons();
        disableNewGameButton();
        disableNewSetButton();
        String message = getString(R.string.winner_message, matchWinner, getString(R.string.match));
        message = message.toUpperCase();
        showMessage(message);
    }

    /**
     * Displays the games score.
     */

    // Displays the games score for Team A.
    public void displayGamesForTeamA(int score) {
        scoreGameA.setText(String.valueOf(score));
    }

    // Displays the games score for Team B.

    public void displayGamesForTeamB(int score) {
        scoreGameB.setText(String.valueOf(score));
    }

    /**
     * Displays the sets score.
     */

    //Displays the sets score for Team A.
    public void displaySetsForTeamA(int score) {
        scoreSetA.setText(String.valueOf(score));
    }


    //Displays the sets score for Team B.

    public void displaySetsForTeamB(int score) {
        scoreSetB.setText(String.valueOf(score));
    }

    //Forms message: Press new game button for... and sends to showMessage

    public void showMessageGame() {
        String str1 = getString(R.string.new_game_button);
        str1 = str1.toUpperCase();
        String str2 = getString(R.string.game);
        showMessage(getString(R.string.press_button_message, str1, str2));
    }

    //Forms message: Press new set button for... and sends to showMessage

    public void showMessageSet() {
        String str1 = getString(R.string.new_set_button);
        str1 = str1.toUpperCase();
        String str2 = getString(R.string.set);
        showMessage(getString(R.string.press_button_message, str1, str2));
    }

    /**
     * Code for New Buttons: New Game, New Set, New match
     **/

    // New Game: Resets both teams scores to start a new Game.
    public void newGame(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        alertMessage = "";
        alertInvisible(getString(R.string.a));
        alertInvisible(getString(R.string.b));
        enablePointButtons();
        disableNewGameButton();
    }

    //New set: Resets both teams scores and Games score to start a new set.

    public void newSet(View view) {
        newGame(view);
        gamesScoreTeamA = 0;
        gamesScoreTeamB = 0;
        displayGamesForTeamA(gamesScoreTeamA);
        displayGamesForTeamB(gamesScoreTeamB);
        disableNewSetButton();
    }

    //New Match: Resets both teams scores, Games score ans Sets score to start a new match.

    public void newMatch(View view) {
        newGame(view);
        newSet(view);
        setScoreTeamA = 0;
        setScoreTeamB = 0;
        displaySetsForTeamA(setScoreTeamA);
        displaySetsForTeamB(setScoreTeamB);
    }

    /**
     * Set Alert Message TextViews to Visible / invisible
     **/

    //set the alert messages textview to visible
    public void alertVisible(String team) {
        if (team.equals(getString(R.string.a))) {
            alertViewA.setVisibility(alertViewA.VISIBLE);
        } else {
            alertViewB.setVisibility(alertViewB.VISIBLE);
        }
    }

    //set the alert messages textview to invisible

    public void alertInvisible(String team) {
        if (team.equals(getString(R.string.a))) {
            alertViewA.setVisibility(alertViewA.INVISIBLE);
        } else {
            alertViewB.setVisibility(alertViewB.INVISIBLE);
        }
    }


    /**
     * Enable / Disable Buttons
     **/

    //Disable point buttons
    public void disablePointButtons() {
        buttonPointA.setEnabled(false);
        buttonPointB.setEnabled(false);
    }

    //Enable point buttons

    public void enablePointButtons() {
        buttonPointA.setEnabled(true);
        buttonPointB.setEnabled(true);
    }

    //Disable New Game button

    public void disableNewGameButton() {
        buttonGame.setEnabled(false);
    }

    //Enable New Game button

    public void enableNewGameButton() {
        buttonGame.setEnabled(true);
    }

    //Disable New Set button

    public void disableNewSetButton() {
        buttonSet.setEnabled(false);
    }

    //Enable New Set button

    public void enableNewSetButton() {
        buttonSet.setEnabled(true);
    }
}