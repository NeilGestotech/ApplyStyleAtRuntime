package com.example.neil.myapplication;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    MyTextView loginTextView;
    MyEditText userIDEditText;
    MyEditText passwordEditText;
    MyButton loginButton;
    MyButton signUpButton;
    SharedPreferences prefs;
    JSONObject mainProperties;
    public static final String MAIN_PROPERTIES_PREFS = "mainPropertiesPrefs";
    public static final String LABEL_PREFS = "labelPrefs";
    public static final String BUTTON_PREFS = "buttonPrefs";
    public static final String SPLASH_BUTTON_PREFS = "splashButtonPrefs";
    public static final String EDITTEXT_PREFS = "editTextPrefs";
    public static final String BACKGROUND_COLOR = "backgroundColor";
    public static final String TEXT_COLOR = "textColor";
    public static final String BORDER_WIDTH = "borderWidth";
    public static final String BORDER_COLOR = "borderColor";
    public static final String JTAG_STYLESHEET = "StyleSheet";
    public static final String JTAG_MAIN_PROPERTIES = "MainProperties";
    public static final String JTAG_LABEL = "Label";
    public static final String JTAG_EDITTEXT = "EditText";
    public static final String JTAG_BUTTON = "Button";
    public static final String JTAG_SPLASH_BUTTON = "SplashButton";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get JSON and parse out style properties===================================================
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            obj = obj.getJSONObject(JTAG_STYLESHEET);

            SharedPreferences.Editor mainEditor = getSharedPreferences(MAIN_PROPERTIES_PREFS, MODE_PRIVATE).edit();
            mainProperties = obj.getJSONObject(JTAG_MAIN_PROPERTIES);
            Iterator iterator = mainProperties.keys();

            while(iterator.hasNext()){
                String key = iterator.next().toString();
                mainEditor.putString(key, mainProperties.getString(key));
            }
            mainEditor.commit();

            prefs = getSharedPreferences(MainActivity.MAIN_PROPERTIES_PREFS, MODE_PRIVATE);

            populateSharedPrefs(JTAG_LABEL, LABEL_PREFS);
            populateSharedPrefs(JTAG_EDITTEXT, EDITTEXT_PREFS);
            populateSharedPrefs(JTAG_BUTTON, BUTTON_PREFS);
            populateSharedPrefs(JTAG_SPLASH_BUTTON, SPLASH_BUTTON_PREFS);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        //Assign UI properties to the Login Title===================================================
        String label = getString(R.string.login_label);
        loginTextView = new MyTextView(this, getSharedPreferences(LABEL_PREFS, MODE_PRIVATE), label);
        loginTextView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        linearLayout.addView(loginTextView);

        //Assign UI properties to the UserID and Password field=====================================
        String email = getString(R.string.user_id);
        userIDEditText = new MyEditText(this, getSharedPreferences(EDITTEXT_PREFS, MODE_PRIVATE), email);
        MyEditTextDrawable drawable = new MyEditTextDrawable(getSharedPreferences(EDITTEXT_PREFS, MODE_PRIVATE));
        userIDEditText.setBackground(drawable);
        linearLayout.addView(userIDEditText);

        String password = getString(R.string.password);
        passwordEditText = new MyEditText(this, getSharedPreferences(EDITTEXT_PREFS, MODE_PRIVATE), password);
        passwordEditText.setBackground(drawable);
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        linearLayout.addView(passwordEditText);

        //Assign UI properties to buttons===========================================================
        String login = getString(R.string.login);
        loginButton = new MyButton(this, getSharedPreferences(BUTTON_PREFS, MODE_PRIVATE), login);
        MyButtonDrawable buttonDrawable = new MyButtonDrawable(getSharedPreferences(BUTTON_PREFS, MODE_PRIVATE));
        loginButton.setBackground(buttonDrawable);
        linearLayout.addView(loginButton);

        //Assign UI properties to splash buttons====================================================
        String signUp = getString(R.string.sign_up);
        signUpButton = new MyButton(this, getSharedPreferences(SPLASH_BUTTON_PREFS, MODE_PRIVATE), login);
        MyButtonDrawable splashButtonDrawable = new MyButtonDrawable(getSharedPreferences(SPLASH_BUTTON_PREFS, MODE_PRIVATE));
        signUpButton.setBackground(splashButtonDrawable);
        linearLayout.addView(signUpButton);

        //999999
        int buttonPressedResId = R.drawable.button_left_pressed;
        int buttonNormalResId = R.drawable.button_left_normal;

        Resources resources = getApplicationContext().getResources();

        StateListDrawable statesd = new StateListDrawable();
        statesd.addState(new int[] {android.R.attr.state_pressed}, resources.getDrawable(buttonPressedResId));
        statesd.addState(new int[] {android.R.attr.state_focused}, resources.getDrawable(buttonPressedResId));
        statesd.addState(new int[]{}, resources.getDrawable(buttonNormalResId));
        ((Button) findViewById(R.id.calc_addPerson_button)).setBackground(statesd);
        //999999
//Implement different states of drawables===========================================================
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.current_points_container);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(getResources().getColor(R.color.orange));
        gradientDrawable.setStroke(5, Color.WHITE);
        gradientDrawable.setShape(GradientDrawable.OVAL);

        GradientDrawable pressedGradientDrawable = new GradientDrawable();
        pressedGradientDrawable.setColor(getResources().getColor(R.color.cream));
        pressedGradientDrawable.setStroke(5, getResources().getColor(R.color.orange));
        pressedGradientDrawable.setShape(GradientDrawable.OVAL);

        StateListDrawable states = new StateListDrawable();



        states.addState(new int[]{android.R.attr.state_pressed}, pressedGradientDrawable);

        states.addState(new int[] {android.R.attr.state_focused},gradientDrawable);
        states.addState(new int[] {android.R.attr.state_enabled},gradientDrawable);
        relativeLayout.setBackground(states);

        LinearLayout pointsLinearLayout = new LinearLayout(this);
        pointsLinearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        pointsLinearLayout.setGravity(Gravity.CENTER);
        pointsLinearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView number = new TextView(this);
        number.setText(getString(R.string.zero));
        number.setTextColor(Color.WHITE);
        number.setTextSize(40f);
        number.setGravity(Gravity.CENTER);

        pointsLinearLayout.addView(number);

        TextView points = new TextView(this);
        points.setText(getString(R.string.points_caps));
        points.setTextColor(Color.WHITE);
        points.setTextSize(16f);
        points.setGravity(Gravity.CENTER);

        pointsLinearLayout.addView(points);

        relativeLayout.addView(pointsLinearLayout);
        //Send HTTP request
        //Get response
        //Parse out the properties and store them in SharedPreferences
    }

    private void populateSharedPrefs(String objectName, String prefsName) throws JSONException{

        SharedPreferences.Editor editor = getSharedPreferences(prefsName, MODE_PRIVATE).edit();
        JSONObject uiObject = mainProperties.getJSONObject(objectName);
        Iterator iterator = uiObject.keys();

        while(iterator.hasNext()){
            String key = iterator.next().toString();
            String value = uiObject.getString(key);
            if(value.substring(0, 1).equals("$")) value = prefs.getString(value, "The string is not available!");
            editor.putString(key, value);
        }
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("io_gesto_mma_android.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
