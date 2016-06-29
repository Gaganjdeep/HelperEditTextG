package ggn.com.helperedittextg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ggn.com.helperedittextlibrary.HelperEdittext;

public class MainActivity extends AppCompatActivity
{

    private HelperEdittext tvHelpertext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvHelpertext = (HelperEdittext) findViewById(R.id.helpertext);


        tvHelpertext.validateEmail();


        ((HelperEdittext) findViewById(R.id.pswdcheck)).validatePassword(7);

    }

    public void yelloWedit(View view)
    {
        tvHelpertext.setEditTextColor(R.color.yellow);
        tvHelpertext.setHelperColor(R.color.green);

        tvHelpertext.setLineColor(R.color.red);

    }

    public void yelloWhelper(View view)
    {
        tvHelpertext.setEditTextColor(R.color.green);
        tvHelpertext.setHelperColor(R.color.purple);

        tvHelpertext.setLineColor(R.color.yellow);
    }
}
