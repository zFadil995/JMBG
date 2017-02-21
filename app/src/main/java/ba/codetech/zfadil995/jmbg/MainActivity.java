package ba.codetech.zfadil995.jmbg;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        Button generateJMBG = (Button) findViewById(R.id.generateJMBG);
        Button copyToClipboard = (Button) findViewById(R.id.copyToClipboard);
        final TextView jmbgText = (TextView) findViewById(R.id.jmbgTextView);

        generateJMBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jmbgText.setText(generateJMBG());
            }
        });

        copyToClipboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("JMBG", jmbgText.getText());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getApplicationContext(), "JMBG COPIED", Toast.LENGTH_SHORT).show();
            }
        });


        generateJMBG.performClick();
    }

    private String generateJMBG(){
        int[] jmbg = new int[13];
        int year = randBetween(1920, 1998);
        int month = randBetween(1, 12);
        int day = randBetween(1, 28);
        int region = randBetween(0, 99);
        int gender = randBetween(0, 999);
        int control;

        if(day < 10)
            jmbg[0] = 0;
        else
            jmbg[0] = day/10;
        jmbg[1] = day%10;

        if(month < 10)
            jmbg[2] = 0;
        else
            jmbg[2] = month/10;
        jmbg[3] = month%10;

        if(year%1000 < 100)
            jmbg[4] = 0;
        else
            jmbg[4] = (year%1000)/100;
        if(year%100 < 10)
            jmbg[5] = 0;
        else
            jmbg[5] = (year%100)/10;
        jmbg[6] = day%10;

        if(region < 10)
            jmbg[7] = 0;
        else
            jmbg[7] = region/10;
        jmbg[8] = region%10;

        if(gender%1000 < 100)
            jmbg[9] = 0;
        else
            jmbg[9] = (gender%1000)/100;
        if(gender%100 < 10)
            jmbg[10] = 0;
        else
            jmbg[10] = (gender%100)/10;
        jmbg[11] = gender%10;

        control = ((7*jmbg[0] +6*jmbg[1] +5*jmbg[2] +4*jmbg[3] +3*jmbg[4] +2*jmbg[5] +7*jmbg[6] +6*jmbg[7] +5*jmbg[8] +4*jmbg[9] +3*jmbg[10] +2*jmbg[11])%11);

        while(control == 1) {
            gender++;

            if(gender%1000 < 100)
                jmbg[9] = 0;
            else
                jmbg[9] = (gender%1000)/100;
            if(gender%100 < 10)
                jmbg[10] = 0;
            else
                jmbg[10] = (gender%100)/10;
            jmbg[11] = gender%10;

            control = ((7*jmbg[0] +6*jmbg[1] +5*jmbg[2] +4*jmbg[3] +3*jmbg[4] +2*jmbg[5] +7*jmbg[6] +6*jmbg[7] +5*jmbg[8] +4*jmbg[9] +3*jmbg[10] +2*jmbg[11])%11);
        }

        if(control > 1)
            control = 11 - control;

        jmbg[12] = control;

        String JMBG = "";

        for (int digit:jmbg) {
            JMBG += Integer.toString(digit);
        }

        return JMBG;
    }


    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
