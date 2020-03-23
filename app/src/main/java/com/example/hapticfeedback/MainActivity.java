package com.example.hapticfeedback;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.hapticfeedback.ui.frame.FrameCreation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.Object;

import android.graphics.Color;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    //Frame creation stuff
    Button setCoils, setDelay, setOnTime, setFrameName, saveFrame;
    Spinner numOfCoils, amountOfDelay, amountOfOnTime, nameOfFrame;
    TextView frameCreationView, indexFinger, middleFinger, ringFinger, firstCoil, secondCoil,
            thirdCoil, fourthCoil, fifthCoil, sixthCoil, seventhCoil, eighthCoil, counter, coilsCounter,
            directoryLocation;

    //Saved frame stuff
    Button frameCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_frameCreation, R.id.nav_sequenceCreation, R.id.nav_savedFrames,
                R.id.nav_savedSequences, R.id.nav_exportFramesSequences)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * Array lists
     */

    //Overall pin selection data
    ArrayList<Object> pinSelection = new ArrayList<>();
    //Overall command data
    ArrayList<Object> commandInformation = new ArrayList<>();
    //Coils used data
    ArrayList<Object> coilsUsed = new ArrayList<>();
    //Total amount
    ArrayList<Integer> totals = new ArrayList<>();

    //Finished frames
    ArrayList<Object> frameFinished1 = new ArrayList<>();
    ArrayList<Object> frameFinished2 = new ArrayList<>();
    ArrayList<Object> frameFinished3 = new ArrayList<>();
    ArrayList<Object> frameFinished4 = new ArrayList<>();
    ArrayList<Object> frameFinished5 = new ArrayList<>();
    ArrayList<Object> frameFinished6 = new ArrayList<>();

    //Frame names
    ArrayList<Object> frameName1 = new ArrayList<>();
    ArrayList<Object> frameName2 = new ArrayList<>();
    ArrayList<Object> frameName3 = new ArrayList<>();
    ArrayList<Object> frameName4 = new ArrayList<>();
    ArrayList<Object> frameName5 = new ArrayList<>();
    ArrayList<Object> frameName6 = new ArrayList<>();

    //Sequence names
    ArrayList<Object> sequenceName1 = new ArrayList<>();
    ArrayList<Object> sequenceName2 = new ArrayList<>();
    ArrayList<Object> sequenceName3 = new ArrayList<>();
    ArrayList<Object> sequenceName4 = new ArrayList<>();
    ArrayList<Object> sequenceName5 = new ArrayList<>();
    ArrayList<Object> sequenceName6 = new ArrayList<>();

    /**
     * Method to register if a pin has been selected or not (frame)
     *
     * @param view
     */
    int coilCounter = 0;

    public void pin(View view) {
        String finial_commands = "";
        String finial_selection = "";
        String selection_order = "";
        boolean checked = ((CheckBox) view).isChecked();
        TextView t = (TextView) findViewById(R.id.frameCreationView);
        String coilsSelected = numOfCoils.getSelectedItem().toString();
        int coilLimit = Integer.parseInt(coilsSelected);
        int coilCount = 0;

        numOfCoils = findViewById(R.id.numberOfCoils);
        counter = findViewById(R.id.counter);

        //Moved when name has been made
        for (Object Commands : commandInformation) {
            finial_commands = finial_commands + Commands + "\n";
        }

        switch (view.getId()) {
            case R.id.pin0:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y000");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin0).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin0).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin0).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin0).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin0).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin0).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin0).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin0).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y000");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y000");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin0).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin1:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y001");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin1).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin1).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin1).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin1).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin1).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin1).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin1).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin1).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y001");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y001");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin1).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin2:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y002");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin2).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin2).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin2).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin2).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin2).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin2).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin2).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin2).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y002");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y002");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin2).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin3:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y003");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin3).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin3).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin3).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin3).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin3).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin3).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin3).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin3).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y003");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y003");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin3).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin4:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y004");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin4).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin4).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin4).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin4).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin4).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin4).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin4).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin4).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y004");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y004");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin4).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin5:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y005");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin5).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin5).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin5).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin5).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin5).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin5).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin5).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin5).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y005");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y005");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin5).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin6:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y006");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin6).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin6).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin6).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin6).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin6).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin6).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin6).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin6).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y006");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y006");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin6).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin7:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y007");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin7).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin7).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin7).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin7).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin7).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin7).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin7).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin7).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y007");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y007");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin7).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin8:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y008");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin8).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin8).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin8).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin8).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin8).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin8).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin8).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin8).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y008");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y008");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin8).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin9:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y009");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin9).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin9).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin9).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin9).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin9).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin9).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin9).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin9).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y009");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y009");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin9).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin10:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y010");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin10).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin10).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin10).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin10).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin10).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin10).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin10).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin10).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y010");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y010");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin10).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin11:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y011");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin11).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin11).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin11).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin11).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin11).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin11).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin11).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin11).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y011");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y011");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin11).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin12:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y012");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin12).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin12).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin12).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin12).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin12).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin12).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin12).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin12).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y012");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y012");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin12).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin13:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y013");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin13).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin13).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin13).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin13).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin13).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin13).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin13).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin13).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y013");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y013");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin13).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin14:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y014");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin14).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin14).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin14).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin14).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin14).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin14).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin14).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin14).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y014");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y014");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin14).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin15:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y015");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin15).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin15).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin15).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin15).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin15).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin15).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin15).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin15).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y015");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y015");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin15).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin16:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y016");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin16).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin16).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin16).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin16).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin16).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin16).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin16).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin16).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y016");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y016");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin16).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin17:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y017");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin17).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin17).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin17).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin17).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin17).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin17).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin17).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin17).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y017");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y017");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin17).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin18:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y018");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin18).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin18).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin18).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin18).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin18).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin18).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin18).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin18).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y018");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y018");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin18).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin19:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y019");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin19).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin19).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin19).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin19).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin19).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin19).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin19).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin19).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y019");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y019");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin19).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin20:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y020");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin20).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin20).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin20).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin20).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin20).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin20).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin20).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin20).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y020");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y020");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin20).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin21:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y021");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin21).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin21).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin21).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin21).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin21).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin21).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin21).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin21).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y021");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y021");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin21).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin22:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y022");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin22).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin22).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin22).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin22).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin22).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin22).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin22).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin22).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y022");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y022");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin22).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin23:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y023");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin23).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin23).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin23).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin23).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin23).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin23).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin23).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin23).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y023");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y023");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin23).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin24:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y024");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin24).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin24).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin24).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin24).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin24).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin24).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin24).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin24).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y024");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y024");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin24).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin25:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y025");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin25).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin25).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin25).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin25).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin25).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin25).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin25).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin25).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y025");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y025");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin25).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin26:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y026");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin26).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin26).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin26).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin26).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin26).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin26).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin26).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin26).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y026");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y026");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin26).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin27:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y027");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin27).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin27).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin27).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin27).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin27).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin27).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin27).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin27).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y027");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y027");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin27).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin28:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y028");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin28).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin28).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin28).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin28).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin28).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin28).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin28).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin28).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y028");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y028");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin28).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin29:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y029");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin29).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin29).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin29).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin29).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin29).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin29).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin29).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin29).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y029");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y029");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin29).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin30:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y030");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin30).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin30).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin30).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin30).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin30).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin30).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin30).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin30).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y030");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y030");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin30).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin31:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y031");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin31).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin31).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin31).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin31).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin31).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin31).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin31).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin31).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y031");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y031");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin31).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin32:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y032");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin32).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin32).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin32).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin32).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin32).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin32).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin32).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin32).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y032");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y032");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin32).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin33:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y033");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin33).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin33).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin33).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin33).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin33).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin33).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin33).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin33).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y033");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y033");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin33).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin34:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y034");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin34).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin34).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin34).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin34).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin34).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin34).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin34).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin34).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y034");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y034");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin34).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin35:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y035");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin35).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin35).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin35).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin35).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin35).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin35).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin35).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin35).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y035");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y035");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin35).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin36:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y036");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin36).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin36).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin36).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin36).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin36).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin36).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin36).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin36).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y036");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y036");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin36).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin37:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y037");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin37).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin37).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin37).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin37).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin37).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin37).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin37).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin37).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y037");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y037");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin37).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin38:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y038");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin38).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin38).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin38).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin38).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin38).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin38).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin38).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin38).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y038");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y038");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin38).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin39:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y039");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin39).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin39).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin39).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin39).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin39).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin39).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin39).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin39).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y039");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y039");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin39).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin40:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y040");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin40).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin40).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin40).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin40).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin40).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin40).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin40).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin40).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y040");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y040");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin40).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin41:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y041");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin41).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin41).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin41).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin41).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin41).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin41).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin41).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin41).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y041");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y041");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin41).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin42:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y042");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin42).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin42).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin42).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin42).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin42).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin42).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin42).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin42).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y042");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y042");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin42).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin43:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y043");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin43).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin43).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin43).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin43).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin43).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin43).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin43).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin43).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y043");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y043");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin43).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin44:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y044");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin44).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin44).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin44).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin44).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin44).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin44).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin44).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin44).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y044");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y044");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin44).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin45:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y045");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin45).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin45).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin45).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin45).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin45).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin45).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin45).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin45).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y045");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y045");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin45).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin46:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y046");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin46).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin46).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin46).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin46).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin46).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin46).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin46).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin46).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y046");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y046");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin46).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin47:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y047");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin47).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin47).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin47).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin47).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin47).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin47).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin47).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin47).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y047");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y047");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin47).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin48:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y048");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin48).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin48).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin48).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin48).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin48).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin48).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin48).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin48).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y048");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y048");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin48).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin49:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y049");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin49).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin49).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin49).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin49).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin49).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin49).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin49).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin49).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y049");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y049");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin49).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin50:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y050");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin50).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin50).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin50).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin50).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin50).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin50).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin50).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin50).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y050");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y050");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin50).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin51:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y051");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin51).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin51).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin51).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin51).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin51).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin51).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin51).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin51).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y051");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y051");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin51).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin52:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y052");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin52).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin52).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin52).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin52).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin52).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin52).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin52).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin52).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y052");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y052");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin52).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin53:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y053");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin53).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin53).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin53).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin53).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin53).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin53).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin53).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin53).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y053");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y053");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin53).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin54:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y054");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin54).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin54).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin54).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin54).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin54).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin54).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin54).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin54).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y054");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y054");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin54).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin55:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y055");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin55).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin55).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin55).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin55).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin55).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin55).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin55).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin55).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y055");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y055");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin55).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin56:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y056");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin56).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin56).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin56).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin56).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin56).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin56).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin56).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin56).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y056");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y056");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin56).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin57:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y057");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin57).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin57).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin57).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin57).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin57).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin57).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin57).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin57).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y057");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y057");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin57).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin58:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y058");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin58).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin58).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin58).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin58).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin58).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin58).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin58).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin58).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y058");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y058");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin58).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin59:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y059");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin59).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin59).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin59).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin59).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin59).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin59).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin59).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin59).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y059");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y059");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin59).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin60:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y060");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin60).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin60).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin60).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin60).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin60).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin60).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin60).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin60).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y060");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y060");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin60).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin61:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y061");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin61).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin61).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin61).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin61).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin61).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin61).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin61).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin61).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y061");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y061");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin61).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin62:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y062");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin62).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin62).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin62).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin62).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin62).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin62).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin62).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin62).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y062");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y062");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin62).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
            case R.id.pin63:
                if (checked) {
                    totals.add(coilCount);
                    if (totals.size() <= coilLimit) {
                        pinSelection.add("$Y063");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        if (totals.size() == 1) {
                            findViewById(R.id.pin63).setBackgroundColor(Color.parseColor("#80ff00"));
                        } else if (totals.size() == 2) {
                            findViewById(R.id.pin63).setBackgroundColor(Color.parseColor("#00ff1a"));
                        } else if (totals.size() == 3) {
                            findViewById(R.id.pin63).setBackgroundColor(Color.parseColor("#00ff95"));
                        } else if (totals.size() == 4) {
                            findViewById(R.id.pin63).setBackgroundColor(Color.parseColor("#00fff2"));
                        } else if (totals.size() == 5) {
                            findViewById(R.id.pin63).setBackgroundColor(Color.parseColor("#00b3ff"));
                        } else if (totals.size() == 6) {
                            findViewById(R.id.pin63).setBackgroundColor(Color.parseColor("#6600ff"));
                        } else if (totals.size() == 7) {
                            findViewById(R.id.pin63).setBackgroundColor(Color.parseColor("#a200ff"));
                        } else if (totals.size() == 8) {
                            findViewById(R.id.pin63).setBackgroundColor(Color.parseColor("#d000ff"));
                        }
                        coilCounter++;
                        counter.setText(String.valueOf(coilCounter));
                    } else {
                        pinSelection.remove("$Y063");
                        for (Object Selections : pinSelection) {
                            finial_selection = finial_selection + Selections + "\n";
                        }
                        t.setText(finial_commands + finial_selection);
                        Toast.makeText(getApplicationContext(), "Coil Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y063");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    findViewById(R.id.pin63).setBackgroundColor(Color.TRANSPARENT);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                }
                break;
        }
    }

    /**
     * Method to name a frame (frame)
     *
     * @param view
     */
    public void nameTheFrame(View view) {
        nameOfFrame = findViewById(R.id.nameFrame);
        setFrameName = findViewById(R.id.setFrameName);
        numOfCoils = findViewById(R.id.numberOfCoils);
        setCoils = findViewById(R.id.setCoils);

        String content = nameOfFrame.getSelectedItem().toString();

        //totals.clear();
        if (frameName1.isEmpty()) {
            frameName1.add("$F" + content);
            commandInformation.add(0, "$F" + content);
            if (commandInformation.add(true)) {
                nameOfFrame.findViewById(R.id.nameFrame).setVisibility(View.INVISIBLE);
                setFrameName.findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                commandInformation.remove(true);
                numOfCoils.findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                setCoils.findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
            }
        } else if (frameName2.isEmpty()) {
            if (!frameName1.contains("$F" + content)) {
                frameName2.add("$F" + content);
                commandInformation.add(0, "$F" + content);
                if (commandInformation.add(true)) {
                    nameOfFrame.findViewById(R.id.nameFrame).setVisibility(View.INVISIBLE);
                    setFrameName.findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                    commandInformation.remove(true);
                    numOfCoils.findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                    setCoils.findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
            }
        } else if (frameName3.isEmpty()) {
            if (!frameName1.contains("$F" + content) && !frameName2.contains("$F" + content)) {
                frameName3.add("$F" + content);
                commandInformation.add(0, "$F" + content);
                if (commandInformation.add(true)) {
                    nameOfFrame.findViewById(R.id.nameFrame).setVisibility(View.INVISIBLE);
                    setFrameName.findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                    commandInformation.remove(true);
                    numOfCoils.findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                    setCoils.findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
            }
        } else if (frameName4.isEmpty()) {
            if (!frameName1.contains("$F" + content) && !frameName2.contains("$F" + content) && !frameName3.contains("$F" + content)) {
                frameName4.add("$F" + content);
                commandInformation.add(0, "$F" + content);
                if (commandInformation.add(true)) {
                    nameOfFrame.findViewById(R.id.nameFrame).setVisibility(View.INVISIBLE);
                    setFrameName.findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                    commandInformation.remove(true);
                    numOfCoils.findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                    setCoils.findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
            }
        } else if (frameName5.isEmpty()) {
            if (!frameName1.contains("$F" + content) && !frameName2.contains("$F" + content) && !frameName3.contains("$F" + content)
                    && !frameName4.contains("$F" + content)) {
                frameName5.add("$F" + content);
                commandInformation.add(0, "$F" + content);
                if (commandInformation.add(true)) {
                    nameOfFrame.findViewById(R.id.nameFrame).setVisibility(View.INVISIBLE);
                    setFrameName.findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                    commandInformation.remove(true);
                    numOfCoils.findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                    setCoils.findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
            }
        } else if (frameName6.isEmpty()) {
            if (!frameName1.contains("$F" + content) && !frameName2.contains("$F" + content) && !frameName3.contains("$F" + content)
                    && !frameName4.contains("$F" + content) && !frameName5.contains("$F" + content)) {
                frameName6.add("$F" + content);
                commandInformation.add(0, "$F" + content);
                if (commandInformation.add(true)) {
                    nameOfFrame.findViewById(R.id.nameFrame).setVisibility(View.INVISIBLE);
                    setFrameName.findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                    commandInformation.remove(true);
                    numOfCoils.findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                    setCoils.findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
            }
        }

        TextView t = (TextView) findViewById(R.id.frameCreationView);
        String start = "";
        for (Object Selections : commandInformation) {
            start = start + Selections + "\n";
        }
        t.setText(start);

        return;
    }

    /**
     * Method to set coils used (frame)
     *
     * @param view
     */
    public void setTheCoils(View view) {
        TextView t = (TextView) findViewById(R.id.frameCreationView);
        String start = "";
        numOfCoils = findViewById(R.id.numberOfCoils);
        setCoils = findViewById(R.id.setCoils);
        amountOfDelay = findViewById(R.id.amountOfDelay);
        setDelay = findViewById(R.id.setDelay);

        String content = numOfCoils.getSelectedItem().toString();
        commandInformation.add(1, "$N00" + content);
        //Shows the first command after click
        for (Object Selections : commandInformation) {
            start = start + Selections + "\n";
        }
        t.setText(start);

        //Saves to a seperate array, maybe for saving?
        coilsUsed.add(content);

        if (commandInformation.add(true)) {
            setCoils.findViewById(R.id.setCoils).setVisibility(View.INVISIBLE);
            numOfCoils.findViewById(R.id.numberOfCoils).setVisibility(View.INVISIBLE);
            commandInformation.remove(true);
            setDelay.findViewById(R.id.setDelay).setVisibility(View.VISIBLE);
            amountOfDelay.findViewById(R.id.amountOfDelay).setVisibility(View.VISIBLE);
        }
        return;
    }

    /**
     * Method to set a delay (frame)
     *
     * @param view
     */
    public void setTheDelay(View view) {
        amountOfDelay = findViewById(R.id.amountOfDelay);
        setDelay = findViewById(R.id.setDelay);
        amountOfOnTime = findViewById(R.id.amountOfOnTime);
        setOnTime = findViewById(R.id.setOnTime);

        String content = amountOfDelay.getSelectedItem().toString();
        if (content.equals("Slow")) {
            commandInformation.add(2, "$D020");
        } else if (content.equals("Medium")) {
            commandInformation.add(2, "$D040");
        } else if (content.equals("Fast")) {
            commandInformation.add(2, "$D060");
        }
        //commandInformation.add(2, "$D0" + content);

        TextView t = (TextView) findViewById(R.id.frameCreationView);
        String start = "";
        for (Object Selections : commandInformation) {
            start = start + Selections + "\n";
        }
        t.setText(start);

        if (commandInformation.add(true)) {

            setDelay.findViewById(R.id.setDelay).setVisibility(View.INVISIBLE);
            amountOfDelay.findViewById(R.id.amountOfDelay).setVisibility(View.INVISIBLE);
            commandInformation.remove(true);
            setOnTime.findViewById(R.id.setOnTime).setVisibility(View.VISIBLE);
            amountOfOnTime.findViewById(R.id.amountOfOnTime).setVisibility(View.VISIBLE);
        }
        return;
    }

    /**
     * Method to set an on time (frame)
     *
     * @param view
     */
    public void setTheOnTime(View view) {
        amountOfOnTime = findViewById(R.id.amountOfOnTime);
        setOnTime = findViewById(R.id.setOnTime);
        frameCreationView = findViewById(R.id.frameCreationView);
        saveFrame = findViewById(R.id.saveFrame);
        indexFinger = findViewById(R.id.indexFinger);
        middleFinger = findViewById(R.id.middleFinger);
        ringFinger = findViewById(R.id.ringFinger);
        counter = findViewById(R.id.counter);
        coilsCounter = findViewById(R.id.coilsCounter);

        firstCoil = findViewById(R.id.firstCoil);
        secondCoil = findViewById(R.id.secondCoil);
        thirdCoil = findViewById(R.id.thirdCoil);
        fourthCoil = findViewById(R.id.fourthCoil);
        fifthCoil = findViewById(R.id.fifthCoil);
        sixthCoil = findViewById(R.id.sixthCoil);
        seventhCoil = findViewById(R.id.seventhCoil);
        eighthCoil = findViewById(R.id.eighthCoil);

        numOfCoils = findViewById(R.id.numberOfCoils);
        String coilNumber = numOfCoils.getSelectedItem().toString();

        findViewById(R.id.pin0);

        String content = amountOfOnTime.getSelectedItem().toString();
        if (content.equals("Short")) {
            commandInformation.add(3, "$P010");
        } else if (content.equals("Medium")) {
            commandInformation.add(3, "$P020");
        } else if (content.equals("Long")) {
            commandInformation.add(3, "$P030");
        }
        //commandInformation.add(3, "$P0" + content);

        TextView t = (TextView) findViewById(R.id.frameCreationView);
        String start = "";
        for (Object Selections : commandInformation) {
            start = start + Selections + "\n";
        }
        t.setText(start);

        if (commandInformation.add(true)) {
            setOnTime.findViewById(R.id.setOnTime).setVisibility(View.INVISIBLE);
            amountOfOnTime.findViewById(R.id.amountOfOnTime).setVisibility(View.INVISIBLE);
            commandInformation.remove(true);
            findViewById(R.id.counter).setVisibility(View.VISIBLE);
            findViewById(R.id.indexFinger).setVisibility(View.VISIBLE);
            findViewById(R.id.middleFinger).setVisibility(View.VISIBLE);
            findViewById(R.id.ringFinger).setVisibility(View.VISIBLE);
            findViewById(R.id.frameCreationView).setVisibility(View.VISIBLE);
            findViewById(R.id.saveFrame).setVisibility(View.VISIBLE);
            findViewById(R.id.coilsCounter).setVisibility(View.VISIBLE);

            if (coilNumber.equals("1")) {
                findViewById(R.id.firstCoil).setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("2")) {
                findViewById(R.id.firstCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.secondCoil).setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("3")) {
                findViewById(R.id.firstCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.secondCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.thirdCoil).setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("4")) {
                findViewById(R.id.firstCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.secondCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.thirdCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.fourthCoil).setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("5")) {
                findViewById(R.id.firstCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.secondCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.thirdCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.fourthCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.fifthCoil).setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("6")) {
                findViewById(R.id.firstCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.secondCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.thirdCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.fourthCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.fifthCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.sixthCoil).setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("7")) {
                findViewById(R.id.firstCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.secondCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.thirdCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.fourthCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.fifthCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.sixthCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.seventhCoil).setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("8")) {
                findViewById(R.id.firstCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.secondCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.thirdCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.fourthCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.fifthCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.sixthCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.seventhCoil).setVisibility(View.VISIBLE);
                findViewById(R.id.eighthCoil).setVisibility(View.VISIBLE);
            }

            findViewById(R.id.pin0).setVisibility(View.VISIBLE);
            findViewById(R.id.pin1).setVisibility(View.VISIBLE);
            findViewById(R.id.pin2).setVisibility(View.VISIBLE);
            findViewById(R.id.pin3).setVisibility(View.VISIBLE);
            findViewById(R.id.pin4).setVisibility(View.VISIBLE);
            findViewById(R.id.pin5).setVisibility(View.VISIBLE);
            findViewById(R.id.pin6).setVisibility(View.VISIBLE);
            findViewById(R.id.pin7).setVisibility(View.VISIBLE);
            findViewById(R.id.pin8).setVisibility(View.VISIBLE);
            findViewById(R.id.pin9).setVisibility(View.VISIBLE);
            findViewById(R.id.pin10).setVisibility(View.VISIBLE);
            findViewById(R.id.pin11).setVisibility(View.VISIBLE);
            findViewById(R.id.pin12).setVisibility(View.VISIBLE);
            findViewById(R.id.pin13).setVisibility(View.VISIBLE);
            findViewById(R.id.pin14).setVisibility(View.VISIBLE);
            findViewById(R.id.pin15).setVisibility(View.VISIBLE);
            findViewById(R.id.pin16).setVisibility(View.VISIBLE);
            findViewById(R.id.pin17).setVisibility(View.VISIBLE);
            findViewById(R.id.pin18).setVisibility(View.VISIBLE);
            findViewById(R.id.pin19).setVisibility(View.VISIBLE);
            findViewById(R.id.pin20).setVisibility(View.VISIBLE);
            findViewById(R.id.pin21).setVisibility(View.VISIBLE);
            findViewById(R.id.pin22).setVisibility(View.VISIBLE);
            findViewById(R.id.pin23).setVisibility(View.VISIBLE);
            findViewById(R.id.pin24).setVisibility(View.VISIBLE);
            findViewById(R.id.pin25).setVisibility(View.VISIBLE);
            findViewById(R.id.pin26).setVisibility(View.VISIBLE);
            findViewById(R.id.pin27).setVisibility(View.VISIBLE);
            findViewById(R.id.pin28).setVisibility(View.VISIBLE);
            findViewById(R.id.pin29).setVisibility(View.VISIBLE);
            findViewById(R.id.pin30).setVisibility(View.VISIBLE);
            findViewById(R.id.pin31).setVisibility(View.VISIBLE);
            findViewById(R.id.pin32).setVisibility(View.VISIBLE);
            findViewById(R.id.pin33).setVisibility(View.VISIBLE);
            findViewById(R.id.pin34).setVisibility(View.VISIBLE);
            findViewById(R.id.pin35).setVisibility(View.VISIBLE);
            findViewById(R.id.pin36).setVisibility(View.VISIBLE);
            findViewById(R.id.pin37).setVisibility(View.VISIBLE);
            findViewById(R.id.pin38).setVisibility(View.VISIBLE);
            findViewById(R.id.pin39).setVisibility(View.VISIBLE);
            findViewById(R.id.pin40).setVisibility(View.VISIBLE);
            findViewById(R.id.pin41).setVisibility(View.VISIBLE);
            findViewById(R.id.pin42).setVisibility(View.VISIBLE);
            findViewById(R.id.pin43).setVisibility(View.VISIBLE);
            findViewById(R.id.pin44).setVisibility(View.VISIBLE);
            findViewById(R.id.pin45).setVisibility(View.VISIBLE);
            findViewById(R.id.pin46).setVisibility(View.VISIBLE);
            findViewById(R.id.pin47).setVisibility(View.VISIBLE);
            findViewById(R.id.pin48).setVisibility(View.VISIBLE);
            findViewById(R.id.pin49).setVisibility(View.VISIBLE);
            findViewById(R.id.pin50).setVisibility(View.VISIBLE);
            findViewById(R.id.pin51).setVisibility(View.VISIBLE);
            findViewById(R.id.pin52).setVisibility(View.VISIBLE);
            findViewById(R.id.pin53).setVisibility(View.VISIBLE);
            findViewById(R.id.pin54).setVisibility(View.VISIBLE);
            findViewById(R.id.pin55).setVisibility(View.VISIBLE);
            findViewById(R.id.pin56).setVisibility(View.VISIBLE);
            findViewById(R.id.pin57).setVisibility(View.VISIBLE);
            findViewById(R.id.pin58).setVisibility(View.VISIBLE);
            findViewById(R.id.pin59).setVisibility(View.VISIBLE);
            findViewById(R.id.pin60).setVisibility(View.VISIBLE);
            findViewById(R.id.pin61).setVisibility(View.VISIBLE);
            findViewById(R.id.pin62).setVisibility(View.VISIBLE);
            findViewById(R.id.pin63).setVisibility(View.VISIBLE);
        }
        return;
    }

    /**
     * Method to save data from a frame (frame)
     */
    private void saveData() {
        frameCreationView = findViewById(R.id.frameCreationView);
        nameOfFrame = findViewById(R.id.nameFrame);
        setFrameName = findViewById(R.id.setFrameName);
        indexFinger = findViewById(R.id.indexFinger);
        middleFinger = findViewById(R.id.middleFinger);
        ringFinger = findViewById(R.id.ringFinger);
        firstCoil = findViewById(R.id.firstCoil);
        secondCoil = findViewById(R.id.secondCoil);
        thirdCoil = findViewById(R.id.thirdCoil);
        fourthCoil = findViewById(R.id.fourthCoil);
        fifthCoil = findViewById(R.id.fifthCoil);
        sixthCoil = findViewById(R.id.sixthCoil);
        seventhCoil = findViewById(R.id.seventhCoil);
        eighthCoil = findViewById(R.id.eighthCoil);

        String contents = numOfCoils.getSelectedItem().toString();
        int coilLimit = Integer.parseInt(contents);
        if (totals.size() == coilLimit) {
            TextView t = (TextView) findViewById(R.id.frameCreationView);
            findViewById(R.id.frameCreationView);
            if (frameFinished1.isEmpty()) {
                pinSelection.add("$T001");
                frameFinished1.add("" + commandInformation + pinSelection);
                Toast.makeText(getApplicationContext(), "Frame Saved1" + frameFinished1, Toast.LENGTH_SHORT).show();
            } else if (frameFinished2.isEmpty()) {
                pinSelection.add("$T001");
                frameFinished2.add("" + commandInformation + pinSelection);
                Toast.makeText(getApplicationContext(), "Frame Saved2" + frameFinished2, Toast.LENGTH_SHORT).show();
            } else if (frameFinished3.isEmpty()) {
                pinSelection.add("$T001");
                frameFinished3.add("" + commandInformation + pinSelection);
                Toast.makeText(getApplicationContext(), "Frame Saved3" + frameFinished3, Toast.LENGTH_SHORT).show();
            } else if (frameFinished4.isEmpty()) {
                pinSelection.add("$T001");
                frameFinished4.add("" + commandInformation + pinSelection);
                Toast.makeText(getApplicationContext(), "Frame Saved4" + frameFinished4, Toast.LENGTH_SHORT).show();
            } else if (frameFinished5.isEmpty()) {
                pinSelection.add("$T001");
                frameFinished5.add("" + commandInformation + pinSelection);
                Toast.makeText(getApplicationContext(), "Frame Saved5" + frameFinished5, Toast.LENGTH_SHORT).show();
            } else if (frameFinished6.isEmpty()) {
                pinSelection.add("$T001");
                frameFinished6.add("" + commandInformation + pinSelection);
                Toast.makeText(getApplicationContext(), "Frame Saved6" + frameFinished6, Toast.LENGTH_SHORT).show();
            }

            pinSelection.clear();
            commandInformation.clear();
            coilsUsed.clear();

            nameOfFrame.findViewById(R.id.nameFrame).setVisibility(View.VISIBLE);
            setFrameName.findViewById(R.id.setFrameName).setVisibility(View.VISIBLE);
            findViewById(R.id.indexFinger).setVisibility(View.INVISIBLE);
            findViewById(R.id.counter).setVisibility(View.INVISIBLE);
            findViewById(R.id.coilsCounter).setVisibility(View.INVISIBLE);
            findViewById(R.id.middleFinger).setVisibility(View.INVISIBLE);
            findViewById(R.id.ringFinger).setVisibility(View.INVISIBLE);
            findViewById(R.id.frameCreationView).setVisibility(View.INVISIBLE);
            findViewById(R.id.saveFrame).setVisibility(View.INVISIBLE);
            findViewById(R.id.firstCoil).setVisibility(View.INVISIBLE);
            findViewById(R.id.secondCoil).setVisibility(View.INVISIBLE);
            findViewById(R.id.thirdCoil).setVisibility(View.INVISIBLE);
            findViewById(R.id.fourthCoil).setVisibility(View.INVISIBLE);
            findViewById(R.id.fifthCoil).setVisibility(View.INVISIBLE);
            findViewById(R.id.sixthCoil).setVisibility(View.INVISIBLE);
            findViewById(R.id.seventhCoil).setVisibility(View.INVISIBLE);
            findViewById(R.id.eighthCoil).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin0).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin1).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin2).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin3).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin4).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin5).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin6).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin7).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin8).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin9).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin10).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin11).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin12).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin13).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin14).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin15).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin16).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin17).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin18).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin19).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin20).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin21).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin22).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin23).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin24).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin25).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin26).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin27).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin28).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin29).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin30).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin31).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin32).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin33).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin34).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin35).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin36).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin37).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin38).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin39).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin40).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin41).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin42).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin43).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin44).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin45).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin46).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin47).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin48).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin49).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin50).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin51).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin52).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin53).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin54).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin55).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin56).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin57).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin58).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin59).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin60).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin61).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin62).setVisibility(View.INVISIBLE);
            findViewById(R.id.pin63).setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(getApplicationContext(), "Please add or remove coils to make your selection of " + coilLimit, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Saves the frame data to an array (frame)
     *
     * @param view
     */
    public void saveTheFrame(View view) {
        saveData();
    }

    /**
     * Test method, can be removed
     *
     * @param view
     */
    //TODO Delete this after testing
    public void resetTheFrame(View view) {
        Toast.makeText(getApplicationContext(), frameFinished1.toString(), Toast.LENGTH_SHORT).show();


/*        pinSelection.clear();
        commandInformation.clear();
        frameFinished.clear();
        TextView t = (TextView) findViewById(R.id.frameCreationView);
        t.setText("");
        setCoils.findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
        coilAmount.findViewById(R.id.coilAmount).setVisibility(View.VISIBLE);*/
    }


 /*   private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", "");
        Type type = new TypeToken<ArrayList<Object>>() {}.getType();
        ArrayList<Object> selection = gson.fromJson(json, type);
    }*/


    /**
     * Method to reload the frame and sequence spinner (export)
     *
     * @param view
     */
    public void reloadFramesAndSequences(View view) {

        Spinner frameSequenceList = (Spinner) findViewById(R.id.frameSequenceList);
        ArrayList<Object> frameSequenceLists = new ArrayList<Object>();

        frameSequenceLists.add(frameName1.toString());
        frameSequenceLists.add(frameName2.toString());
        frameSequenceLists.add(frameName3.toString());
        frameSequenceLists.add(frameName4.toString());
        frameSequenceLists.add(frameName5.toString());
        frameSequenceLists.add(frameName6.toString());

        frameSequenceLists.add(sequenceName1.toString());
        frameSequenceLists.add(sequenceName2.toString());
        frameSequenceLists.add(sequenceName3.toString());
        frameSequenceLists.add(sequenceName4.toString());
        frameSequenceLists.add(sequenceName5.toString());
        frameSequenceLists.add(sequenceName6.toString());

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, frameSequenceLists);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        frameSequenceList.setAdapter(adapter);

        String content = frameSequenceList.getSelectedItem().toString();
    }

    /**
     * Method to reload the frame spinner (frame)
     *
     * @param view
     */
    public void reloadTheFrameList(View view) {
        //frameCollection.findViewById(R.id.viewFrame);
        Spinner frameLists = (Spinner) findViewById(R.id.frameList);
        ArrayList<Object> frameList = new ArrayList<Object>();

        //if(frameList.isEmpty() != true){
        frameList.add(frameName1.toString());
        frameList.add(frameName2.toString());
        frameList.add(frameName3.toString());
        frameList.add(frameName4.toString());
        frameList.add(frameName5.toString());
        frameList.add(frameName6.toString());
/*        } else if(frameList.size() < 2){
            frameList.add(frameName2.toString());
        } else if(frameName3.isEmpty()){
            frameList.add(frameName3.toString());
        } else if(frameName4.isEmpty()){
            frameList.add(frameName4.toString());
        } else if(frameName5.isEmpty()){
            frameList.add(frameName5.toString());
        } else if(frameName6.isEmpty()){
            frameList.add(frameName6.toString());*/
        //}

//        frameList.add(frameName1.toString());

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, frameList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        frameLists.setAdapter(adapter);

        String content = frameLists.getSelectedItem().toString();

//        TextView t = (TextView) findViewById(R.id.frameCreationView);
//        t.setText("" + content);

/*
        String content = frameLists.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(), "aa", Toast.LENGTH_SHORT).show();
*/


    }

    /**
     * Method to reload the sequence spinner (sequence)
     *
     * @param view
     */
    public void reloadTheSequenceList(View view) {
        //frameCollection.findViewById(R.id.viewFrame);
        Spinner sequenceLists = (Spinner) findViewById(R.id.sequenceList);
        ArrayList<Object> sequenceList = new ArrayList<Object>();

        //if(frameList.isEmpty() != true){
        sequenceList.add(sequenceName1.toString());
        sequenceList.add(sequenceName2.toString());
        sequenceList.add(sequenceName3.toString());
        sequenceList.add(sequenceName4.toString());
        sequenceList.add(sequenceName5.toString());
        sequenceList.add(sequenceName6.toString());
/*        } else if(frameList.size() < 2){
            frameList.add(frameName2.toString());
        } else if(frameName3.isEmpty()){
            frameList.add(frameName3.toString());
        } else if(frameName4.isEmpty()){
            frameList.add(frameName4.toString());
        } else if(frameName5.isEmpty()){
            frameList.add(frameName5.toString());
        } else if(frameName6.isEmpty()){
            frameList.add(frameName6.toString());*/
        //}

//        frameList.add(frameName1.toString());

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sequenceList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sequenceLists.setAdapter(adapter);

        String content = sequenceLists.getSelectedItem().toString();

//        TextView t = (TextView) findViewById(R.id.frameCreationView);
//        t.setText("" + content);

/*
        String content = frameLists.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(), "aa", Toast.LENGTH_SHORT).show();
*/


    }

    /**
     * Method to view the selected frame (frame)
     *
     * @param view
     */
    public void viewTheSelectedFrame(View view) {
        Spinner frameLists = (Spinner) findViewById(R.id.frameList);
        TextView t = (TextView) findViewById(R.id.savedFrameView);
//        frameCollection.findViewById(R.id.viewSelectedFrame);

        String content = frameLists.getSelectedItem().toString();
        if (content.equals(frameName1.toString())) {
            t.setText(frameFinished1.toString());
        } else if (content.equals(frameName2.toString())) {
            t.setText(frameFinished2.toString());
        } else if (content.equals(frameName3.toString())) {
            t.setText(frameFinished3.toString());
        } else if (content.equals(frameName4.toString())) {
            t.setText(frameFinished4.toString());
        } else if (content.equals(frameName5.toString())) {
            t.setText(frameFinished5.toString());
        } else if (content.equals(frameName6.toString())) {
            t.setText(frameFinished6.toString());
        }

        //TextView t = (TextView) findViewById(R.id.savedFrameView);
/*        String start = "";
        for (Object Selections : frameFinished1) {
            start = start + content + Selections + "\n";
        }*/
    }

    /**
     * Method to view the selected sequence (sequence)
     *
     * @param view
     */
    //TODO update to work when sequence can be made
    public void viewTheSelectedSequence(View view) {
        Spinner sequenceLists = (Spinner) findViewById(R.id.sequenceList);
        TextView t = (TextView) findViewById(R.id.savedSequenceView);
//        frameCollection.findViewById(R.id.viewSelectedFrame);

        String content = sequenceLists.getSelectedItem().toString();
      /*  if(content.equals(sequenceName1.toString())){
            t.setText(frameFinished1.toString());
        }else if(content.equals(sequenceName2.toString())){
            t.setText(frameFinished2.toString());
        }*/

        //TextView t = (TextView) findViewById(R.id.savedFrameView);
/*        String start = "";
        for (Object Selections : frameFinished1) {
            start = start + content + Selections + "\n";
        }*/
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public void export(View view) throws IOException {
        Spinner frameSequenceList = (Spinner) findViewById(R.id.frameSequenceList);
        TextView t = (TextView) findViewById(R.id.directory);
        File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Haptic_Feedback");
        if (path.exists() && path.isDirectory()) {

        } else {
            path.mkdir();
        }

        String text = frameSequenceList.getSelectedItem().toString();
        String frameSequenceName = "";
        String newLine = "\n";
        String name = "";
        String coils = "";
        String delay = "";
        String time = "";
        String first = "";
        String second = "";
        String third = "";
        String fourth = "";
        String fifth = "";
        String sixth = "";
        String seventh = "";
        String eighth = "";
        String ninth = "";

        if (text.equals(frameName1.toString())) {
            text = frameFinished1.toString();
            frameSequenceName = text.substring(2, 7);
            frameSequenceName = frameSequenceName + ".txt";
            if (text.length() == 44) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second;
            } else if (text.length() == 51) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third;
            } else if (text.length() == 58) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth;
            } else if (text.length() == 65) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth;
            } else if (text.length() == 72) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth;
            } else if (text.length() == 79) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh;
            } else if (text.length() == 86) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);
                eighth = text.substring(79, 84);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh + newLine + eighth;
            } else if (text.length() == 93) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);
                eighth = text.substring(79, 84);
                ninth = text.substring(81, 91);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth;
            }
        }
        if (text.equals(frameName2.toString())) {
            text = frameFinished2.toString();
            frameSequenceName = text.substring(2, 7);
            frameSequenceName = frameSequenceName + ".txt";
            if (text.length() == 44) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second;
            } else if (text.length() == 51) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third;
            } else if (text.length() == 58) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth;
            } else if (text.length() == 65) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth;
            } else if (text.length() == 72) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth;
            } else if (text.length() == 79) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh;
            } else if (text.length() == 86) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);
                eighth = text.substring(79, 84);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh + newLine + eighth;
            } else if (text.length() == 93) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);
                eighth = text.substring(79, 84);
                ninth = text.substring(81, 91);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth;
            }
        }
        if (text.equals(frameName3.toString())) {
            text = frameFinished3.toString();
            frameSequenceName = text.substring(2, 7);
            frameSequenceName = frameSequenceName + ".txt";
            if (text.length() == 44) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second;
            } else if (text.length() == 51) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third;
            } else if (text.length() == 58) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth;
            } else if (text.length() == 65) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth;
            } else if (text.length() == 72) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth;
            } else if (text.length() == 79) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh;
            } else if (text.length() == 86) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);
                eighth = text.substring(79, 84);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh + newLine + eighth;
            } else if (text.length() == 93) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);
                eighth = text.substring(79, 84);
                ninth = text.substring(81, 91);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth;
            }
        }
        if (text.equals(frameName4.toString())) {
            text = frameFinished4.toString();
            frameSequenceName = text.substring(2, 7);
            frameSequenceName = frameSequenceName + ".txt";
            if (text.length() == 44) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second;
            } else if (text.length() == 51) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third;
            } else if (text.length() == 58) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth;
            } else if (text.length() == 65) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth;
            } else if (text.length() == 72) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth;
            } else if (text.length() == 79) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh;
            } else if (text.length() == 86) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);
                eighth = text.substring(79, 84);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh + newLine + eighth;
            } else if (text.length() == 93) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);
                eighth = text.substring(79, 84);
                ninth = text.substring(81, 91);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth;
            }
        }
        if (text.equals(frameName5.toString())) {
            text = frameFinished5.toString();
            frameSequenceName = text.substring(2, 7);
            frameSequenceName = frameSequenceName + ".txt";
            if (text.length() == 44) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second;
            } else if (text.length() == 51) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third;
            } else if (text.length() == 58) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth;
            } else if (text.length() == 65) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth;
            } else if (text.length() == 72) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth;
            } else if (text.length() == 79) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh;
            } else if (text.length() == 86) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);
                eighth = text.substring(79, 84);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh + newLine + eighth;
            } else if (text.length() == 93) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);
                eighth = text.substring(79, 84);
                ninth = text.substring(81, 91);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth;
            }
        }
        if (text.equals(frameName6.toString())) {
            text = frameFinished6.toString();
            frameSequenceName = text.substring(2, 7);
            frameSequenceName = frameSequenceName + ".txt";
            if (text.length() == 44) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second;
            } else if (text.length() == 51) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third;
            } else if (text.length() == 58) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth;
            } else if (text.length() == 65) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth;
            } else if (text.length() == 72) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth;
            } else if (text.length() == 79) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh;
            } else if (text.length() == 86) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);
                eighth = text.substring(79, 84);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh + newLine + eighth;
            } else if (text.length() == 93) {
                name = text.substring(2, 7);
                coils = text.substring(9, 14);
                delay = text.substring(16, 21);
                time = text.substring(23, 28);

                first = text.substring(30, 35);
                second = text.substring(37, 42);
                third = text.substring(44, 49);
                fourth = text.substring(51, 56);
                fifth = text.substring(58, 63);
                sixth = text.substring(65, 70);
                seventh = text.substring(72, 77);
                eighth = text.substring(79, 84);
                ninth = text.substring(81, 91);

                text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                        + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                        + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth;
            }
        }
        File dir = new File(path, frameSequenceName);

        try {
            FileOutputStream fos = new FileOutputStream(dir);
            fos.write(text.getBytes());
            Toast.makeText(this, "Saved " + frameSequenceName + " to the following Directory: " + dir.toString(), Toast.LENGTH_SHORT).show();
            t.setText(dir.toString());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}