package com.alebossi96.ramanshiftcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
public class MainActivity extends AppCompatActivity {
    double round(double value, int decimal){
        int rounding = (int) Math.pow(10, decimal);
        return (double)Math.round(value * rounding) / rounding;
    }
    int get_num_decimal(double val){

        String text = Double.toString(Math.abs(val));
        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces - 1;
        if(decimalPlaces == 0)
            return 1;
        return decimalPlaces;
    }
    void case0Calc(EditText pumpWl, EditText wvRaman, TextView  textRamanWl){
        String valuePump= pumpWl.getText().toString();
        String valueWn= wvRaman.getText().toString();
        double tyndall = 0;
        if (!valuePump.equals("") &&  !valueWn.equals("")) {

            tyndall = Double.parseDouble(valuePump);

            double wn = Double.parseDouble(valueWn);
            double ramanWl = (double) (1.0 / (1.0 / tyndall - wn / 1e7));
            int min_decimal = Math.min(get_num_decimal(wn),get_num_decimal(tyndall) );
            textRamanWl.setText(Double.toString(round(ramanWl, min_decimal)));

        }

    }
    void case1Calc(EditText pumpWl, EditText  textRamanWl, TextView wvRaman){
        String valuePump= pumpWl.getText().toString();
        String valueRamanWl= textRamanWl.getText().toString();
        int tyndall = 0;

        if (!valuePump.equals("") &&  !valueRamanWl.equals("")) {

            tyndall = Integer.parseInt(valuePump);

            int wl = Integer.parseInt(valueRamanWl);
            int wn = (int) ((1.0 / tyndall - 1.0/wl)*1e7);
            wvRaman.setText(Integer.toString(wn));

        }

    }
    void case2Calc(EditText  textRamanWl, EditText wnRaman,TextView pumpWl){
        String valueWn= wnRaman.getText().toString();
        String valueRamanWl= textRamanWl.getText().toString();
        int tyndall = 0;

        if (!valueWn.equals("") &&  !valueRamanWl.equals("")) {



            int wl = Integer.parseInt(valueRamanWl);
            int wn = Integer.parseInt(valueWn);

            tyndall = (int) (1.0 / (1.0 / wl + wn / 1e7));
            pumpWl.setText(Integer.toString(tyndall));

        }

    }
    void case3Calc(EditText  Editwl0, EditText Editwl1,TextView textEff){
        String stringWl0= Editwl0.getText().toString();
        String stringWl1= Editwl1.getText().toString();
        double efficiency = 0;

        if (!stringWl0.equals("") &&  !stringWl1.equals("")) {



            int wl1 = Integer.parseInt(stringWl1);
            int wl0 = Integer.parseInt(stringWl0);

            efficiency =(Math.pow(wl0*1.0/wl1,4)) ;
            textEff.setText(String.format("%.2f",efficiency));

        }

    }
    void case0name(TextView text1, TextView text2,TextView text3,
                   TextView textUm1, TextView textUm2, TextView textUm3 ){
                    text1.setText("Pump wavelength:");
                    text2.setText("wavenumber");
                    text3.setText("Raman wavelength:");
                    textUm1.setText("nm");
                    textUm2.setText("cm-1");
                    textUm3.setText("nm");
    }
    void case1name(TextView text1, TextView text2,TextView text3,
                   TextView textUm1, TextView textUm2, TextView textUm3 ){
        text1.setText("Pump wavelength:");
        text2.setText("Raman wavelength:");
        text3.setText("wavenumber ");
        textUm1.setText("nm");
        textUm2.setText("nm");
        textUm3.setText("cm-1");

    }
    void case2name(TextView text1, TextView text2,TextView text3,
                   TextView textUm1, TextView textUm2, TextView textUm3 ){


        text1.setText("Raman wavelength:");
        text2.setText("wavenumber");
        text3.setText("Pump wavelength:");
        textUm1.setText("nm");
        textUm2.setText("cm-1");
        textUm3.setText("nm");
    }
    void case3name(TextView text1, TextView text2,TextView text3,
                   TextView textUm1, TextView textUm2, TextView textUm3 ){


        text1.setText("initial wavelength:");
        text2.setText("final wavelength:");
        text3.setText("efficiency:");
        textUm1.setText("nm");
        textUm2.setText("nm");
        textUm3.setText("");
    }

    int state = 0;
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
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    if(state==0)
                        case0Calc(edit1,edit2,edit3);
                    else if(state==1)
                        case1Calc(edit1,edit2,edit3);
                    else if(state==2)
                        case2Calc(edit1,edit2,edit3);
                    else if(state==3)
                        case3Calc(edit1,edit2,edit3);
                }

        });
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state == 0){
                    state++;
                    case1name(text1,text2,text3,textUm1,textUm2,textUm3);
                }
                else if(state == 1){
                    state++;
                    case2name(text1,text2,text3,textUm1,textUm2,textUm3);
                }
                else if(state==2){
                    state ++;
                    case3name(text1,text2,text3,textUm1,textUm2,textUm3);
                }
                else if(state==3){
                    state =0;
                    case0name(text1,text2,text3,textUm1,textUm2,textUm3);
                }
            }
        });
    }

}