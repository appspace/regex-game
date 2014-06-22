package ca.appspace.regexgame.app;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class RegexpGameActivity extends Activity {

    private final static String[] PATTERNS = new String[]{
            "\\w.*",    //Matches any single word
            "true",     //Matches only "true"
            "[tT]rue",  //Matches true or True
            "[cC]at|[dD]og", //Matches Cat or cat or Dog or Dog
            "[^0-9]*[12]?[0-9]{1,2}[^0-9]*" //Matches string containing a number less then 300
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regexp_game);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.regexp_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private EditText _answerInput;
        private EditText _regexpField;
        private final Random random = new Random();

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_regexp_game, container, false);
            _answerInput = (EditText) rootView.findViewById(R.id.answerInput);
            _answerInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    checkIfInputValid();
                }
            });
            _regexpField = (EditText) rootView.findViewById(R.id.regexpField);
            _regexpField.setEnabled(false);
            final Button btn = (Button) rootView.findViewById(R.id.changeRegexpBtn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = random.nextInt(PATTERNS.length);
                    String regexp = PATTERNS[pos];
                    _regexpField.setText(regexp);
                    checkIfInputValid();
                }


            });
            return rootView;
        }

        private void checkIfInputValid() {
            String regexp = _regexpField.getText().toString();
            String inputText = _answerInput.getText().toString();
            if (inputText.matches(regexp)) {
                _answerInput.setBackgroundColor(Color.GREEN);
            } else {
                _answerInput.setBackgroundColor(Color.RED);
            }
        }
    }
}
