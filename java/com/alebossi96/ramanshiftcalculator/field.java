package com.alebossi96.ramanshiftcalculator;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Locale;
//TODO estendere a m input e n output
public abstract class field{
    TextView text1, text2, text3, textUm1, textUm2, textUm3;
    EditText edit0, edit1;
    TextView  text0;
    public field(TextView text1_, TextView text2_,TextView text3_,
                   TextView textUm1_, TextView textUm2_, TextView textUm3_,
                 EditText edit0_, EditText edit1_, TextView  text0_)
    {
        text1 = text1_;
        text2 = text2_;
        text3 = text3_;
        textUm1 = textUm1_;
        textUm2 = textUm2_;
        textUm3 = textUm3_;
        edit0 = edit0_;
        edit1 = edit1_;
        text0 = text0_;
        setName();
    }
    int get_num_decimal(EditText editText)
    {
        String text = editText.getText().toString();
        int integerPlaces = text.indexOf('.');
        if(integerPlaces == -1)
            return 0;
        return text.length() - integerPlaces - 1;
    }
    int get_min_decimal(EditText editText1, EditText editText2)
    {
        return Math.min(get_num_decimal(editText1),get_num_decimal(editText2));
    }
    int get_num_digit(EditText editText)
    {
        String text = editText.getText().toString();
        return text.length() - 1;
    }
    int get_min_digit(EditText editText1, EditText editText2)
    {
        return Math.min(get_num_digit(editText1),get_num_digit(editText2));
    }
    double round(double value, int decimal)
    {
        int rounding = (int) Math.pow(10, decimal);
        return (double)Math.round(value * rounding) / rounding;
    }
    double parseEdit(EditText edit)
    {
        String val_string = edit.getText().toString();
        return Double.parseDouble(val_string);
    }
    private boolean isFull(EditText etText) {
        return etText.getText().toString().trim().length() > 0;
    }

    void setResult()
    {

        if (isFull(edit0) &&  isFull(edit1))
        {
            double el1_value= parseEdit(edit0);
            double el2_value= parseEdit(edit1);
            double result;
            result = calc(el1_value, el2_value);
            if(scientificNotation())
            {
                int min_digit = get_min_digit(edit0,edit1);
                result = round(result, min_digit);
                text0.setText(String.format(Locale.getDefault(),"%."+min_digit+"e", result));
                return;
            }
            int min_decimal = get_min_decimal(edit0, edit1);
            result = round(result, min_decimal);
            if(min_decimal == 0)
                text0.setText(String.format(Locale.getDefault(),"%d",(int) result));
            else
                text0.setText(String.format(Locale.getDefault(),"%."+min_decimal+"f",result));
        }
    }
    void changedState(TextView edit)
    {
        edit.setText("");
    }
    abstract boolean scientificNotation();

    abstract double calc(double el1, double el2);
    abstract void setName();
}





class PumpWnShift extends field
{
    public PumpWnShift(TextView text1_, TextView text2_,TextView text3_,
                       TextView textUm1_, TextView textUm2_, TextView textUm3_,
                       EditText edit0_, EditText edit1_, TextView  text0_) {
        super(text1_, text2_, text3_, textUm1_, textUm2_, textUm3_, edit0_, edit1_, text0_);

    }
    boolean scientificNotation(){
        return false;
    }
    void setName()
    {
        text1.setText("Pump wavelength:");
        text2.setText("wavenumber");
        text3.setText("Raman wavelength:");
        textUm1.setText("nm");
        textUm2.setText("cm-1");
        textUm3.setText("nm");
    }
    double calc(double tyndall, double wn)
    {
        return 1.0 / (1.0 / tyndall - wn / 1e7);
    }
}
class PumpRamanWl extends field
{
    public PumpRamanWl(TextView text1_, TextView text2_,TextView text3_,
                       TextView textUm1_, TextView textUm2_, TextView textUm3_,
                       EditText edit0_, EditText edit1_, TextView  text0_) {
        super(text1_, text2_, text3_, textUm1_, textUm2_, textUm3_, edit0_, edit1_, text0_);
    }
    boolean scientificNotation(){
        return false;
    }
    void setName()
    {
        text1.setText("Pump wavelength:");
        text2.setText("Raman wavelength:");
        text3.setText("wavenumber ");
        textUm1.setText("nm");
        textUm2.setText("nm");
        textUm3.setText("cm-1");
    }
    double calc(double tyndall, double wn)
    {
        return (1.0 / tyndall - 1.0/wn)*1e7;
    }
}
class RamanWlShift extends field
{

    public RamanWlShift(TextView text1_, TextView text2_,TextView text3_,
                        TextView textUm1_, TextView textUm2_, TextView textUm3_,
                        EditText edit0_, EditText edit1_, TextView  text0_)
    {
        super(text1_, text2_, text3_, textUm1_, textUm2_, textUm3_, edit0_, edit1_, text0_);
    }
    boolean scientificNotation(){
        return false;
    }
    void setName()
    {
        text1.setText("Raman wavelength:");
        text2.setText("wavenumber");
        text3.setText("Pump wavelength:");
        textUm1.setText("nm");
        textUm2.setText("cm-1");
        textUm3.setText("nm");
    }
    double calc(double wl, double wn)
    {
        return 1.0 / (1.0 / wl + wn / 1e7);
    }
}
class Efficiency extends field
{
    public Efficiency(TextView text1_, TextView text2_,TextView text3_,
                      TextView textUm1_, TextView textUm2_, TextView textUm3_,
                      EditText edit0_, EditText edit1_, TextView  text0_)
    {
        super(text1_, text2_, text3_, textUm1_, textUm2_, textUm3_, edit0_, edit1_, text0_);
    }
    boolean scientificNotation(){
        return true;
    }
    void setName()
    {
        text1.setText("initial wavelength:");
        text2.setText("final wavelength:");
        text3.setText("relative Raman efficiency:");
        textUm1.setText("nm");
        textUm2.setText("nm");
        textUm3.setText("");
    }
    double calc(double wl0, double wl1)
    {
        return Math.pow(wl0/wl1,4);
    }
}