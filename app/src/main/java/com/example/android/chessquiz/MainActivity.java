package com.example.android.chessquiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Checks if the first answer is Queen, or queen. The return value will be added to the score
     * @param valueToCompare
     * @param editTextValue
     * @return 1 in case of good answer, otherwise 0
     */
    private int checkEditText(String valueToCompare, String editTextValue) {
         if (valueToCompare.equals(editTextValue.toLowerCase())) {
            return 1;
        }
        return 0;
    }

    /**
     * Checks if the 64 is checked. The return value will be added to the score
     * @return 1 in case of good answer, otherwise 0
     */
    private int checkSecondQuestion() {
        RadioButton radioButton= (RadioButton) (findViewById(R.id.radio_Button_2));
        if (radioButton.isChecked()) {
            return 1;
        }
        return 0;
    }

    /**
     * Checks if the first three checkbox is selected and the fourth one is not selected.
     * The return value will be added to the score.
     * @return 1 in case of good answer, otherwise 0
     */
    private int checkThirdQuestion() {

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox_1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox_2);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox_3);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox_4);

        if (checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && !checkBox4.isChecked()) {
            return 1;
        }
        return 0;
    }

    /**
     * Calculates the final score, and displays the grading using a toast.
     * The toast is displayed via custom_toast separate xml.
     * @param view
     */
    public void grading(View view) {

        int score = 0;
        int subScore = 0;
        String message="";
        EditText editText;
        String answer;

        //Check the result of the first question
        editText= (EditText) (findViewById(R.id.editText_Question_1));
        answer= editText.getText().toString();
        subScore=checkEditText(getString(R.string._queen),answer);
        if (subScore==1) {
            message+=getString(R.string._answer_1_is_good);
            score +=subScore;
        }else{
            message+=getString(R.string._answer_1_is_wrong);
        }

        //Check the result of the second question
        subScore=checkSecondQuestion();
        if (subScore==1) {
            message+="\n"+getString(R.string._answer_2_is_good);
            score +=subScore;
        }else{
            message+="\n"+getString(R.string._answer_2_is_wrong);
        }

        //Check the result of the third question
        subScore=checkThirdQuestion();
        if (subScore==1) {
            message+="\n"+getString(R.string._answer_3_is_good);
            score +=subScore;
        }else{
            message+="\n"+getString(R.string._answer_3_is_wrong);
        }

        //Check the result of the fourth question
        editText= (EditText) (findViewById(R.id.editText_Question_4));
        answer= editText.getText().toString();

        subScore=checkEditText(getString(R.string._h6),answer);
        if (subScore==1) {
            message+="\n"+getString(R.string._answer_4_is_good);
            score +=subScore;
        }else{
            message+="\n"+getString(R.string._answer_4_is_wrong);
        }

        // Add final grade to the message
        if (score==4){
            message+="\n\n"+getString(R.string._congratulations_perfect);
        }else{
            message+="\n\n"+getString(R.string._your_score_is)+score+getString(R.string._of_4)+"\n"+getString(R.string._try_it_again);
        }

        //Display toast
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(message);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
