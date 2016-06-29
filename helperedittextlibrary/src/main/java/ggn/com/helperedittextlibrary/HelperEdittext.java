package ggn.com.helperedittextlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

/**
 * Created by gagandeep on 29 Jun 2016.
 */
public class HelperEdittext extends LinearLayout
{

    public HelperEdittext(Context context)
    {
        super(context);

        init(context, null);
    }

    public HelperEdittext(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    public HelperEdittext(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public HelperEdittext(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private AppCompatEditText edText;
    private AppCompatTextView tvHelpertext;

    int LineColor, editTextColor, helperTextColor;

    public void init(Context context, AttributeSet attrs)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.helperlayout, this);


        edText = (AppCompatEditText) findViewById(R.id.edText);
        tvHelpertext = (AppCompatTextView) findViewById(R.id.tvHelpertext);

        if (attrs != null)
        {
            TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.HelperEditText);

            LineColor = values.getResourceId(R.styleable.HelperEditText_LineColor, android.R.color.black);
            helperTextColor = values.getResourceId(R.styleable.HelperEditText_helperTextColor, android.R.color.darker_gray);
            editTextColor = values.getResourceId(R.styleable.HelperEditText_HelpereditTextColor, android.R.color.darker_gray);

            int inputType = values.getInt(R.styleable.HelperEditText_android_inputType, EditorInfo.TYPE_NULL);


            float edittextSize   = values.getDimension(R.styleable.HelperEditText_edittextSize, 16);
            float helpertextSize = values.getDimension(R.styleable.HelperEditText_helperTextSize, 9);

            edText.setTextSize(edittextSize);
            tvHelpertext.setTextSize(helpertextSize);


            edText.setInputType(inputType);


            boolean showHelper = values.getBoolean(R.styleable.HelperEditText_showHelperText, true);

            tvHelpertext.setVisibility(showHelper ? View.VISIBLE : View.GONE);

            tvHelpertext.setTextColor(getResources().getColor(helperTextColor));
            edText.setTextColor(getResources().getColor(editTextColor));

            bottomLineColor(edText, LineColor);
        }

    }

    int errorColor = android.R.color.holo_red_dark;

    public void bottomLineColor(View view, int color)
    {
        Drawable wrappedDrawable = DrawableCompat.wrap(view.getBackground());
        DrawableCompat.setTint(wrappedDrawable.mutate(), getResources().getColor(color));
        view.setBackgroundDrawable(wrappedDrawable);
    }


    public void setErrorColor(int color)
    {
        errorColor = color;
    }


    public void setLineColor(int color)
    {
        bottomLineColor(edText, color);
    }

    public void setEditTextColor(int color)
    {
        edText.setTextColor(getResources().getColor(color));
    }

    public void setHelperColor(int color)
    {
        tvHelpertext.setTextColor(getResources().getColor(color));
    }


    public void setError(String ErrorText)
    {
        setLineColor(errorColor);
        setHelperColor(errorColor);


        if (ErrorText.equals(""))
        {
            tvHelpertext.setVisibility(GONE);
        }
        else
        {
            tvHelpertext.setVisibility(View.VISIBLE);
            setHelperText(ErrorText);
        }

        tvHelpertext.setText(ErrorText);
    }


    public void removeError(String helperTxt)
    {
        setLineColor(LineColor);
        setHelperColor(helperTextColor);

        if (helperTxt.equals(""))
        {
            tvHelpertext.setVisibility(GONE);
        }
        else
        {
            setHelperText(helperTxt);
        }
    }

    public void setHelperText(String helperText)
    {
        tvHelpertext.setText(helperText);
    }

    public void showHelperText(boolean showHelper)
    {
        tvHelpertext.setVisibility(showHelper ? View.VISIBLE : View.GONE);

    }

    private boolean isValidEmail(String email)
    {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void validateEmail()
    {
        edText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if (isValidEmail(charSequence.toString()))
                {
                    setLineColor(android.R.color.holo_green_dark);
                }
                else
                {
                    setLineColor(errorColor);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }


    public void validatePassword(final int minLength)
    {
        edText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if (minLength < charSequence.length())
                {
                    setLineColor(android.R.color.holo_green_dark);
                }
                else
                {
                    setLineColor(errorColor);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }


}
