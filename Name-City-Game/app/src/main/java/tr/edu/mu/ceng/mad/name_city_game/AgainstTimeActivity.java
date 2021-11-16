package tr.edu.mu.ceng.mad.name_city_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class AgainstTimeActivity extends AppCompatActivity {
    private TextView txtCity, txtAnswer, txtTotalPoints, txtRemainingTime;
    private EditText editTextGuess;
    private Button btnGuess, btnLetter, btnRestart;
    private String[] cities = {
            "adana", "adiyaman", "afyonkarahisar", "agri", "aksaray", "amasya", "ankara",
            "antalya", "ardahan", "artvin", "aydin", "balikesir", "bartin", "batman",
            "bayburt", "bilecik", "bingol", "bitlis", "bolu", "burdur", "bursa", "canakkale",
            "cankiri", "corum", "denizli", "diyarbakir", "duzce", "edirne", "elazig",
            "erzincan", "erzurum", "eskisehir", "gaziantep", "giresun", "gumushane",
            "hakkari", "hatay", "igdir", "isparta", "istanbul", "izmir", "kahramanmaras",
            "karabuk", "karaman", "kars", "kastamonu", "kayseri", "kilis", "kirikkale",
            "kirklareli", "kirsehir", "kocaeli", "konya", "kutahya", "malatya", "manisa",
            "mardin", "mersin", "mugla", "mus", "nevsehir", "nigde", "ordu", "osmaniye",
            "rize", "sakarya", "samsun", "sanliurfa", "siirt", "sinop", "sivas", "sirnak",
            "tekirdag", "tokat", "trabzon", "tunceli", "usak", "van", "yalova", "yozgat", "zonguldak"
    };
    private Random rndCity, rndLetter;
    private int rndCityNumber, rndLetterNumber, startingLetterNumber, totalTime = 60000;
    private String currentCity, txtAnswerString, editTextUserGuess;
    private ArrayList<Character> currentCityChar;
    private float maxPoints = 100.0f, pointsToBreak, totalPoints = 0, levelTotalPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_against_time);

        txtCity = findViewById(R.id.txtCity_time);
        txtAnswer = findViewById(R.id.txtAnswer_time);
        editTextGuess = findViewById(R.id.editTextGuess_time);
        txtTotalPoints = findViewById(R.id.txtTotalPoints_time);
        txtRemainingTime = findViewById(R.id.txtRemainingTime);
        btnGuess = findViewById(R.id.btnGuess_time);
        btnLetter = findViewById(R.id.btnLetter_time);
        btnRestart = findViewById(R.id.btnRestart_time);

        new CountDownTimer(totalTime, 1000) {
            @Override
            public void onTick(long l) {
                txtRemainingTime.setText((l / 60000) + ":" + ((l % 60000)/1000));
            }

            @Override
            public void onFinish() {
                txtRemainingTime.setText("0:00");
                btnGuess.setEnabled(false);
                btnLetter.setEnabled(false);
                editTextGuess.setEnabled(false);
                btnRestart.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Time is Over\nYou got " + levelTotalPoints + " points", Toast.LENGTH_SHORT).show();
            }
        }.start();
        rndLetter = new Random();
        createWord();
    }

    public void btnRestart(View v){
        Intent restart = new Intent(this, AgainstTimeActivity.class);
        finish();
        startActivity(restart);
    }

    public void btnLetter_time(View v){
        if(currentCityChar.size() > 0){
            takeRandomLetter();
            totalPoints -= pointsToBreak;
            Toast.makeText(AgainstTimeActivity.this, "Remaining points = " + totalPoints, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "No letters remaining", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnGuess_time(View v){
        editTextUserGuess = editTextGuess.getText().toString();
        if(!TextUtils.isEmpty(editTextUserGuess)){
            if(editTextUserGuess.equals(currentCity)){
                levelTotalPoints += totalPoints;
                Toast.makeText(AgainstTimeActivity.this, "Congratulations! Correct Answer", Toast.LENGTH_SHORT).show();
                txtTotalPoints.setText("Total Points : " + levelTotalPoints);
                editTextGuess.setText("");
                createWord();
            }else{
                Toast.makeText(getApplicationContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Answer is empty!", Toast.LENGTH_SHORT).show();
        }
    }

    private void createWord(){
        txtAnswerString = "";
        rndCity = new Random();
        rndCityNumber = rndCity.nextInt(cities.length);
        currentCity = cities[rndCityNumber];
        txtCity.setText("City composed of " +currentCity.length() + " words");

        if(currentCity.length() >= 5 && currentCity.length() <= 7){
            startingLetterNumber = 1;
        }else if(currentCity.length() >= 8 && currentCity.length() < 10){
            startingLetterNumber = 2;
        }else if(currentCity.length() >= 10){
            startingLetterNumber = 3;
        }else{
            startingLetterNumber = 0;
        }

        for(int i = 0; i < currentCity.length(); i++){
            if(i < currentCity.length() - 1){
                txtAnswerString += "_ ";
            }else{
                txtAnswerString += "_";
            }
        }
        txtAnswer.setText(txtAnswerString);
        currentCityChar = new ArrayList<>();

        for (char c: currentCity.toCharArray()) {
            currentCityChar.add(c);
        }

        for (int c = 0; c < startingLetterNumber; c++){
            takeRandomLetter();
        }

        pointsToBreak = maxPoints / currentCityChar.size();
        totalPoints = maxPoints;
    }

    private void takeRandomLetter(){
        rndLetterNumber = rndLetter.nextInt(currentCityChar.size());
        String[] txtLetters = txtAnswer.getText().toString().split(" ");
        char[] currentCityLetters = currentCity.toCharArray();

        for(int i = 0; i< currentCity.length(); i++){
            if(txtLetters[i].equals("_") && currentCityLetters[i] == currentCityChar.get(rndLetterNumber)){
                txtLetters[i] = String.valueOf(currentCityChar.get(rndLetterNumber));
                txtAnswerString = "";

                for(int j = 0; j < currentCity.length(); j++){
                    if(j == i || j < currentCity.length() - 1){
                        txtAnswerString += txtLetters[j] + " ";
                    }else{
                        txtAnswerString += txtLetters[j];
                    }
                }
                break;
            }
        }
        txtAnswer.setText(txtAnswerString);
        currentCityChar.remove(rndLetterNumber);
    }

}