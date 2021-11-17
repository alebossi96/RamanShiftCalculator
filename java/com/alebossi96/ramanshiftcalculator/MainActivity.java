package com.alebossi96.ramanshiftcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int state = 0;
    field newState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button clickButton = (Button) findViewById(R.id.Convert);
        Button toggleButton = (Button) findViewById(R.id.ToggleButton);

        //dati
        EditText edit1   = (EditText)findViewById(R.id.edit1);
        EditText edit2   = (EditText)findViewById(R.id.edit2);
        TextView edit3 = (TextView) findViewById(R.id.edit3);//nonè edit ma va bene lo stesso

        //nomi

        TextView text1 =(TextView)findViewById(R.id.text1);
        TextView text2 =(TextView)findViewById(R.id.text2);
        TextView text3 =(TextView)findViewById(R.id.text3);


        TextView textUm1 = (TextView)findViewById(R.id.textUm1);
        TextView textUm2 = (TextView)findViewById(R.id.textUm2);
        TextView textUm3 = (TextView)findViewById(R.id.textUm3);
        newState = new PumpWnShift(text1, text2, text3, textUm1, textUm2, textUm3, edit1, edit2, edit3);
        clickButton.setOnClickListener(v -> newState.setResult());
        toggleButton.setOnClickListener(view -> {
            newState.changedState(edit3);
            if(state == 0){
                state++;
                newState = new PumpRamanWl(text1, text2, text3, textUm1, textUm2, textUm3, edit1, edit2, edit3);
                //case1name(text1,text2,text3,textUm1,textUm2,textUm3);
            }
            else if(state == 1){
                state++;
                newState = new RamanWlShift(text1, text2, text3, textUm1, textUm2, textUm3, edit1, edit2, edit3);
                //case2name(text1,text2,text3,textUm1,textUm2,textUm3);
            }
            else if(state==2){
                state ++;
                newState = new Efficiency(text1, text2, text3, textUm1, textUm2, textUm3, edit1, edit2, edit3);
                //case3name(text1,text2,text3,textUm1,textUm2,textUm3);
            }
            else if(state==3){
                state =0;
                newState = new PumpWnShift(text1, text2, text3, textUm1, textUm2, textUm3, edit1, edit2, edit3);
                //case0name(text1,text2,text3,textUm1,textUm2,textUm3);
            }
        });
    }

}
