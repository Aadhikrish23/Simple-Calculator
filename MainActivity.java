package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result,above;
    MaterialButton buttonC,buttonAC,buttondot,button_open,button_close,button_divide,button_multiply,button_addition,button_sub,button_eql,button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
        above=findViewById(R.id.above);
        assingId(buttonC,R.id.button_C);
        assingId(buttonAC,R.id.button_AC);
        assingId(button_open,R.id.button_Open_bracket);
        assingId(button_close,R.id.button_close_bracket);
        assingId(buttondot,R.id.button_dot);
        assingId(button_divide,R.id.button_divide);
        assingId(button_multiply,R.id.button_multiply);
        assingId(button_sub,R.id.button_subraction);
        assingId(button_addition,R.id.button_Addition);
        assingId(button_eql,R.id.button_equal);
        assingId(button0,R.id.button_0);
        assingId(button1,R.id.button_1);
        assingId(button2,R.id.button_2);
        assingId(button3,R.id.button_3);
        assingId(button4,R.id.button_4);
        assingId(button5,R.id.button_5);
        assingId(button6,R.id.button_6);
        assingId(button7,R.id.button_7);
        assingId(button8,R.id.button_8);
        assingId(button9,R.id.button_9);

    }
    void assingId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton)  view;
        String buttonText = button.getText().toString();
        String dataToCalculate = above.getText().toString();
        if (buttonText.equals("AC")){
            above.setText("  ");
            result.setText(" ");
            return;
        }
        if (buttonText.equals("=")){
            above.setText(result.getText());
            return;
        }
        if ((buttonText.equals("C"))){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);

        }
        else {
            dataToCalculate=dataToCalculate+buttonText;

        }



        above.setText(dataToCalculate);
        String finalResult= getResult(dataToCalculate);
        if(!finalResult.equals("Err")){
            result.setText(finalResult);
        }

    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
           String finalResult= context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}