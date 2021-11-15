package tr.edu.mu.ceng.mad.name_city_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class NormalGameActivity extends AppCompatActivity {
    private TextView txtCity, txtAnswer, txtTotalPoints;
    private EditText editTextGuess;

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
    private int rndCityNumber, rndLetterNumber, startingLetterNumber;
    private String currentCity, txtAnswerString, editTextUserGuess;
    private ArrayList<Character> currentCityChar;
    private float maxPoints = 100.0f, pointsToBreak, totalPoints = 0, levelTotalPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_game);

        txtCity = findViewById(R.id.txtCity_normal);
        txtAnswer = findViewById(R.id.txtAnswer_normal);
        editTextGuess = findViewById(R.id.editTextGuess_normal);
        txtTotalPoints = findViewById(R.id.txtTotalPoints_normal);

        rndLetter = new Random();
        createWord();
    }


    public void btnGuess(View v){
        editTextUserGuess = editTextGuess.getText().toString();

        if(!TextUtils.isEmpty(editTextUserGuess)){
            if(editTextUserGuess.equals(currentCity)){
                levelTotalPoints += totalPoints;
                Toast.makeText(NormalGameActivity.this, "Congratulations! Correct Answer", Toast.LENGTH_SHORT).show();
                txtTotalPoints.setText("Total Points : " + levelTotalPoints);
                editTextGuess.setText("");
                createWord();
            }else{

            }
        }
    }

    public void btnLetter(View v){
        if(currentCityChar.size() > 0){
            takeRandomLetter();
            totalPoints -= pointsToBreak;
            Toast.makeText(NormalGameActivity.this, "Remaining points = " + totalPoints, Toast.LENGTH_SHORT).show(); 
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