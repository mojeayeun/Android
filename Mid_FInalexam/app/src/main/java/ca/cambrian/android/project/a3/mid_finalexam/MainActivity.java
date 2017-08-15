package ca.cambrian.android.project.a3.mid_finalexam;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String RADIO1_VALUE = "ca.cambrian.android.project.a3.mid_finalexam:id/radioButton1";
    public static final String RADIO2_VALUE = "ca.cambrian.android.project.a3.mid_finalexam:id/radioButton2";
    public static final String RADIO3_VALUE = "ca.cambrian.android.project.a3.mid_finalexam:id/radioButton3";

    TextView mTextView = null;
    SeekBar mSeekBar;
    Spinner mSpinner;
    CheckBox mCheckBox1,mCheckBox2,mCheckBox3;

    RadioGroup mRadioGroup;
    RadioButton mRadioButton1,mRadioButton2,mRadioButton3;

    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * 1st
         */
        mTextView = (TextView) findViewById(R.id.tvTextSize);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //mTextView.setText(String.valueOf(i));
                mTextView.setTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /**
         * 2nd
         */
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if (i == 0){
                    //Red
                   mTextView.setTextColor(Color.RED);
                }else if (i==1){
                    //Green
                   mTextView.setTextColor(Color.GREEN);
                }else if (i==2){
                    //Blue
                   mTextView.setTextColor(Color.BLUE);
                }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });


        /**
         * 3rd
         */
        mCheckBox1 = (CheckBox) findViewById(R.id.checkBox1);
        mCheckBox2 = (CheckBox) findViewById(R.id.checkBox2);
        mCheckBox3 = (CheckBox) findViewById(R.id.checkBox3);



        //Bold
        mCheckBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkTypeface();
            }
        });


        //Italic
        mCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkTypeface();
            }
        });

        //Underline
        mCheckBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkTypeface();
            }
        });



        mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                String msg =  radioGroup.getResources().getResourceName(i);

                if (msg.equals(RADIO1_VALUE)){
                    mTextView.setGravity(Gravity.LEFT);
                    //mTextView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                }else if(msg.equals(RADIO2_VALUE)){
                    mTextView.setGravity(Gravity.CENTER);

                    //mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                }else if(msg.equals(RADIO3_VALUE)){
                    mTextView.setGravity(Gravity.RIGHT);
                    //mTextView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);

                }else{
                    // do nothing
                }

            }
        });


    }


    public void checkTypeface(){

        int total = 0;
        //bold, italic, underline

        boolean b1 =  mCheckBox1.isChecked();
        boolean b2 =  mCheckBox1.isChecked();
        boolean b3 =  mCheckBox1.isChecked();

        if (mCheckBox1.isChecked()) total = total + 1;
        if (mCheckBox2.isChecked()) total = total + 2;
        if (mCheckBox3.isChecked()) total = total + 4;


        if (total==0) mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        if (total==1) mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        if (total==2) mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));

        SpannableString 을 사용하여 문자에 underline을 넣을수 있다


        //if (total==4) mTextView.setTypeface(TextAttribute.INPUT_METHOD_UNDERLINE);

//        UnderlineSpan us = new UnderlineSpan();
//        TextPaint tp = new TextPaint(1);
//        us.updateDrawState(tp.setUnderlineText();
//
//        SpannableStringBuilder.setSpan(mTextView,1,2,1);
//        SpannableStringBuilder.
//        Typeface.create()
//
//        mTextView.setTypeface()

        if (total==3) mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD | Typeface.ITALIC));
        if (total==5) mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD)); // bold + underline
        if (total==6) mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD)); // italic + underline


    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
    }
}

//
////Bold
//mCheckBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//@Override
//public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//        //checked
//        if (b){
//        mTextView.setTypeface(Typeface.DEFAULT_BOLD);
//        }else{
//        mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
//        }
//
//        }
//        });
//
//
//        //Italic
//        mCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//@Override
//public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//
//        //checked
//        if (b){
//        mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC | Typeface.BOLD));
//        }else{
//        mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
//        }
//
//
//        }
//        });
//
//        //Underline
//        mCheckBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//@Override
//public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//        //checked
//        if (b){
//        mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
//        }else{
//        mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
//        }
//
//
//        }
//        });