package com.alebossi96.ramanshiftcalculator;
import android.widget.EditText;
import android.widget.TextView;


public class Field{
    int get_num_decimal(double val)
    {
        String text = Double.toString(Math.abs(val));
        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces - 1;
        return decimalPlaces;
    }
    int get_min_decimal(double val1, double val2)
    {
    return Math.min(get_num_decimal(val1),get_num_decimal(val2));
    }
    double round(double value, int decimal)
    {
        int rounding = (int) Math.pow(10, decimal);
        return (double)Math.round(value * rounding) / rounding;
    }
    double calc(double el1, double el2)
    {
        return 0;
    }
    double parseEdit(EditText edit)
    {
        String val_string = edit0.getText().toString();
        return Double.parseDouble(val_string);
    }
    void setResult(EditText edit0, EditText edit1, TextView  text0)
    {
        double el1_value= parseEdit(edit0);
        double el2_value= parseEdit(edit1);
        int min_decimal = get_min_decimal(el1_value, el2_value);
        double result = 0;
        if (!el1_value.equals("") &&  !el2_value.equals(""))
        {
            result = calc(el1, el2)
            result = round(result);
            if(min_decimal == 0)
                text.setText(Integer.toString(result));
            else
                text0.setText(Double.toString(result));
        }
    }
    void setName(TextView text1, TextView text2,TextView text3,
                   TextView textUm1, TextView textUm2, TextView textUm3)
    {
        return;
    }
                     
}

public class PumpWnShift extends Field
{
    @Override
    void setName(TextView text1, TextView text2,TextView text3,
                   TextView textUm1, TextView textUm2, TextView textUm3 )
    {
        text1.setText("Pump wavelength:");
        text2.setText("wavenumber");
        text3.setText("Raman wavelength:");
        textUm1.setText("nm");
        textUm2.setText("cm-1");
        textUm3.setText("nm");
    }
    @Override
    double calc(double tyndall, double wn)
    {
        return 1.0 / (1.0 / tyndall - wn / 1e7);
    }
}

public class PumpRamanWl extends Field
{
    @Override
    void setName(TextView text1, TextView text2,TextView text3,
                   TextView textUm1, TextView textUm2, TextView textUm3 )
    {
        text1.setText("Pump wavelength:");
        text2.setText("Raman wavelength:");
        text3.setText("wavenumber ");
        textUm1.setText("nm");
        textUm2.setText("nm");
        textUm3.setText("cm-1");
    }
    @Override
    double calc(double tyndall, double wn)
    {
        return (1.0 / tyndall - 1.0/wn)*1e7;
    }
}
public class RamanWlShift extends Field
{
    @Override
    void setName(TextView text1, TextView text2,TextView text3,
                   TextView textUm1, TextView textUm2, TextView textUm3 )
    {
        text1.setText("Raman wavelength:");
        text2.setText("wavenumber");
        text3.setText("Pump wavelength:");
        textUm1.setText("nm");
        textUm2.setText("cm-1");
        textUm3.setText("nm");
    }
    @Override
    double calc(double wl, double wn)
    {
        return 1.0 / (1.0 / wl + wn / 1e7);
    }
}
public class Efficiency extends Field
{
    @Override
    void setName(TextView text1, TextView text2,TextView text3,
                   TextView textUm1, TextView textUm2, TextView textUm3 )
    {
        text1.setText("initial wavelength:");
        text2.setText("final wavelength:");
        text3.setText("efficiency:");
        textUm1.setText("nm");
        textUm2.setText("nm");
        textUm3.setText("");
    }
    @Override
    double calc(double wl0, double wl1)
    {
        return Math.pow(wl0*1.0/wl1,4);
    }
}

