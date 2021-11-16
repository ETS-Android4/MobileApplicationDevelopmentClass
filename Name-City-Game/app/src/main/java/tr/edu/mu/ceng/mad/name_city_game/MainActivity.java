package tr.edu.mu.ceng.mad.name_city_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnMainPage(View v){
        switch (v.getId()){
            case R.id.btnNormal:
                activitySurf("NormalGame");
                break;

            case R.id.btnAgainstTime:
                activitySurf("AgainstTime");
                break;

            case R.id.btnExit:
                exit();
                break;
        }
    }

    private void activitySurf(String activityName){
        if(activityName.equals("NormalGame"))
            intent = new Intent(this,NormalGameActivity.class);
        else
            intent = new Intent(this,AgainstTimeActivity.class);

        startActivity(intent);
    }

    private void exit(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        exit();
    }
}