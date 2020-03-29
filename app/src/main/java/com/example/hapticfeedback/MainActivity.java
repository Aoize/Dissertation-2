package com.example.hapticfeedback;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;

import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.lang.Object;

import android.graphics.Color;

import com.google.android.material.navigation.NavigationView;

import android.widget.ArrayAdapter;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    //Frame and Sequence creation stuff
    Button setCoils, setDelay, setOnTime, setFrameName, saveFrame, setSequenceName, setFrames, setFrame,
            setAmountOfRep, saveTheSequence;

    Spinner numOfCoils, amountOfDelay, amountOfOnTime, nameOfFrame, nameOfSequence, numOfFramesSelected,
            frameAmount, selectRepAmount;

    TextView frameCreationView, indexFinger, middleFinger, ringFinger, firstCoil, secondCoil,
            thirdCoil, fourthCoil, fifthCoil, sixthCoil, seventhCoil, eighthCoil, counter, coilsCounter,
            sequenceCreationView;

    CheckBox pin0, pin1, pin2, pin3, pin4, pin5, pin6, pin7, pin8, pin9, pin10, pin11, pin12, pin13,
            pin14, pin15, pin16, pin17, pin18, pin19, pin20, pin21, pin22, pin23, pin24, pin25,
            pin26, pin27, pin28, pin29, pin30, pin31, pin32, pin33, pin34, pin35, pin36, pin37,
            pin38, pin39, pin40, pin41, pin42, pin43, pin44, pin45, pin46, pin47, pin48, pin49,
            pin50, pin51, pin52, pin53, pin54, pin55, pin56, pin57, pin58, pin59, pin60, pin61,
            pin62, pin63;

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
                R.id.nav_savedSequences, R.id.nav_exportFramesSequences).setDrawerLayout(drawer).build();
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
    //Overall command data for frames
    ArrayList<Object> commandInformation = new ArrayList<>();
    //Coils used data
    ArrayList<Object> coilsUsed = new ArrayList<>();
    //Total amount of coils
    ArrayList<Integer> totals = new ArrayList<>();
    //Total amount of frames
    ArrayList<Integer> totalFrames = new ArrayList<>();
    //Overall command data for sequences
    ArrayList<Object> commandInformationSeq = new ArrayList<>();
    //Frames used data
    ArrayList<Object> framesUsed = new ArrayList<>();
    //FrameSelectionData
    ArrayList<Object> frameSelections = new ArrayList<>();

    //Finished frames
    ArrayList<Object> frameFinished1 = new ArrayList<>();
    ArrayList<Object> frameFinished2 = new ArrayList<>();
    ArrayList<Object> frameFinished3 = new ArrayList<>();
    ArrayList<Object> frameFinished4 = new ArrayList<>();
    ArrayList<Object> frameFinished5 = new ArrayList<>();
    ArrayList<Object> frameFinished6 = new ArrayList<>();

    //Finished sequences
    ArrayList<Object> sequenceFinished1 = new ArrayList<>();
    ArrayList<Object> sequenceFinished2 = new ArrayList<>();
    ArrayList<Object> sequenceFinished3 = new ArrayList<>();
    ArrayList<Object> sequenceFinished4 = new ArrayList<>();

    //Frame names with $F
    ArrayList<Object> frameName1 = new ArrayList<>();
    ArrayList<Object> frameName2 = new ArrayList<>();
    ArrayList<Object> frameName3 = new ArrayList<>();
    ArrayList<Object> frameName4 = new ArrayList<>();
    ArrayList<Object> frameName5 = new ArrayList<>();
    ArrayList<Object> frameName6 = new ArrayList<>();

    //Frame names with $f
    ArrayList<Object> frameNameF1 = new ArrayList<>();
    ArrayList<Object> frameNameF2 = new ArrayList<>();
    ArrayList<Object> frameNameF3 = new ArrayList<>();
    ArrayList<Object> frameNameF4 = new ArrayList<>();
    ArrayList<Object> frameNameF5 = new ArrayList<>();
    ArrayList<Object> frameNameF6 = new ArrayList<>();

    //Sequence names
    ArrayList<Object> sequenceName1 = new ArrayList<>();
    ArrayList<Object> sequenceName2 = new ArrayList<>();
    ArrayList<Object> sequenceName3 = new ArrayList<>();
    ArrayList<Object> sequenceName4 = new ArrayList<>();

    /**
     * Method to register if a pin has been selected or not (frame)
     *
     * @param view
     */
    public static int coilCounter = 0;

    public void pin(View view) {
        numOfCoils = findViewById(R.id.numberOfCoils);
        counter = findViewById(R.id.counter);
        frameCreationView = findViewById(R.id.frameCreationView);

        String finial_commands = "";
        String finial_selection = "";
        boolean checked = ((CheckBox) view).isChecked();
        TextView t = frameCreationView;
        String coilsSelected = numOfCoils.getSelectedItem().toString();
        int coilLimit = Integer.parseInt(coilsSelected);
        int coilCount = 0;

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
                    findViewById(R.id.pin0).setBackgroundColor(Color.TRANSPARENT);
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

        if (frameName1.isEmpty()) {
            frameName1.add("$F" + content);
            frameNameF1.add("$f" + content);
            commandInformation.add(0, "$F" + content);
            if (commandInformation.add(true)) {
                nameOfFrame.setVisibility(View.INVISIBLE);
                setFrameName.setVisibility(View.INVISIBLE);
                commandInformation.remove(true);
                numOfCoils.setVisibility(View.VISIBLE);
                setCoils.setVisibility(View.VISIBLE);
            }
        } else if (frameName2.isEmpty()) {
            if (!frameName1.contains("$F" + content)) {
                frameName2.add("$F" + content);
                frameNameF2.add("$f" + content);
                commandInformation.add(0, "$F" + content);
                if (commandInformation.add(true)) {
                    nameOfFrame.setVisibility(View.INVISIBLE);
                    setFrameName.setVisibility(View.INVISIBLE);
                    commandInformation.remove(true);
                    numOfCoils.setVisibility(View.VISIBLE);
                    setCoils.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
            }
        } else if (frameName3.isEmpty()) {
            if (!frameName1.contains("$F" + content) && !frameName2.contains("$F" + content)) {
                frameName3.add("$F" + content);
                frameNameF3.add("$f" + content);
                commandInformation.add(0, "$F" + content);
                if (commandInformation.add(true)) {
                    nameOfFrame.setVisibility(View.INVISIBLE);
                    setFrameName.setVisibility(View.INVISIBLE);
                    commandInformation.remove(true);
                    numOfCoils.setVisibility(View.VISIBLE);
                    setCoils.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
            }
        } else if (frameName4.isEmpty()) {
            if (!frameName1.contains("$F" + content) && !frameName2.contains("$F" + content) && !frameName3.contains("$F" + content)) {
                frameName4.add("$F" + content);
                frameNameF4.add("$f" + content);
                commandInformation.add(0, "$F" + content);
                if (commandInformation.add(true)) {
                    nameOfFrame.setVisibility(View.INVISIBLE);
                    setFrameName.setVisibility(View.INVISIBLE);
                    commandInformation.remove(true);
                    numOfCoils.setVisibility(View.VISIBLE);
                    setCoils.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
            }
        } else if (frameName5.isEmpty()) {
            if (!frameName1.contains("$F" + content) && !frameName2.contains("$F" + content) && !frameName3.contains("$F" + content)
                    && !frameName4.contains("$F" + content)) {
                frameName5.add("$F" + content);
                frameNameF5.add("$f" + content);
                commandInformation.add(0, "$F" + content);
                if (commandInformation.add(true)) {
                    nameOfFrame.setVisibility(View.INVISIBLE);
                    setFrameName.setVisibility(View.INVISIBLE);
                    commandInformation.remove(true);
                    numOfCoils.setVisibility(View.VISIBLE);
                    setCoils.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
            }
        } else if (frameName6.isEmpty()) {
            if (!frameName1.contains("$F" + content) && !frameName2.contains("$F" + content) && !frameName3.contains("$F" + content)
                    && !frameName4.contains("$F" + content) && !frameName5.contains("$F" + content)) {
                frameName6.add("$F" + content);
                frameNameF6.add("$f" + content);
                commandInformation.add(0, "$F" + content);
                if (commandInformation.add(true)) {
                    nameOfFrame.setVisibility(View.INVISIBLE);
                    setFrameName.setVisibility(View.INVISIBLE);
                    commandInformation.remove(true);
                    numOfCoils.setVisibility(View.VISIBLE);
                    setCoils.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
            }
        }

        TextView t = findViewById(R.id.frameCreationView);
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
        numOfCoils = findViewById(R.id.numberOfCoils);
        setCoils = findViewById(R.id.setCoils);
        amountOfDelay = findViewById(R.id.amountOfDelay);
        setDelay = findViewById(R.id.setDelay);
        frameCreationView = findViewById(R.id.frameCreationView);

        TextView t = frameCreationView;
        String start = "";

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
        frameCreationView = findViewById(R.id.frameCreationView);

        String content = amountOfDelay.getSelectedItem().toString();
        if (content.equals("Slow")) {
            commandInformation.add(2, "$D020");
        } else if (content.equals("Medium")) {
            commandInformation.add(2, "$D040");
        } else if (content.equals("Fast")) {
            commandInformation.add(2, "$D060");
        }

        TextView t = frameCreationView;
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

        TextView t = frameCreationView;
        String start = "";
        for (Object Selections : commandInformation) {
            start = start + Selections + "\n";
        }
        t.setText(start);

        if (commandInformation.add(true)) {
            setOnTime.setVisibility(View.INVISIBLE);
            amountOfOnTime.setVisibility(View.INVISIBLE);
            commandInformation.remove(true);
            counter.setVisibility(View.VISIBLE);
            indexFinger.setVisibility(View.VISIBLE);
            middleFinger.setVisibility(View.VISIBLE);
            ringFinger.setVisibility(View.VISIBLE);
            frameCreationView.setVisibility(View.VISIBLE);
            saveFrame.setVisibility(View.VISIBLE);
            coilsCounter.setVisibility(View.VISIBLE);

            if (coilNumber.equals("1")) {
                firstCoil.setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("2")) {
                firstCoil.setVisibility(View.VISIBLE);
                secondCoil.setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("3")) {
                firstCoil.setVisibility(View.VISIBLE);
                secondCoil.setVisibility(View.VISIBLE);
                thirdCoil.setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("4")) {
                firstCoil.setVisibility(View.VISIBLE);
                secondCoil.setVisibility(View.VISIBLE);
                thirdCoil.setVisibility(View.VISIBLE);
                fourthCoil.setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("5")) {
                firstCoil.setVisibility(View.VISIBLE);
                secondCoil.setVisibility(View.VISIBLE);
                thirdCoil.setVisibility(View.VISIBLE);
                fourthCoil.setVisibility(View.VISIBLE);
                fifthCoil.setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("6")) {
                firstCoil.setVisibility(View.VISIBLE);
                secondCoil.setVisibility(View.VISIBLE);
                thirdCoil.setVisibility(View.VISIBLE);
                fourthCoil.setVisibility(View.VISIBLE);
                fifthCoil.setVisibility(View.VISIBLE);
                sixthCoil.setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("7")) {
                firstCoil.setVisibility(View.VISIBLE);
                secondCoil.setVisibility(View.VISIBLE);
                thirdCoil.setVisibility(View.VISIBLE);
                fourthCoil.setVisibility(View.VISIBLE);
                fifthCoil.setVisibility(View.VISIBLE);
                sixthCoil.setVisibility(View.VISIBLE);
                seventhCoil.setVisibility(View.VISIBLE);
            } else if (coilNumber.equals("8")) {
                firstCoil.setVisibility(View.VISIBLE);
                secondCoil.setVisibility(View.VISIBLE);
                thirdCoil.setVisibility(View.VISIBLE);
                fourthCoil.setVisibility(View.VISIBLE);
                fifthCoil.setVisibility(View.VISIBLE);
                sixthCoil.setVisibility(View.VISIBLE);
                seventhCoil.setVisibility(View.VISIBLE);
                eighthCoil.setVisibility(View.VISIBLE);
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

        pin0 = findViewById(R.id.pin0);
        pin1 = findViewById(R.id.pin1);
        pin2 = findViewById(R.id.pin2);
        pin3 = findViewById(R.id.pin3);
        pin4 = findViewById(R.id.pin4);
        pin5 = findViewById(R.id.pin5);
        pin6 = findViewById(R.id.pin6);
        pin7 = findViewById(R.id.pin7);
        pin8 = findViewById(R.id.pin8);
        pin9 = findViewById(R.id.pin9);
        pin10 = findViewById(R.id.pin10);
        pin11 = findViewById(R.id.pin11);
        pin12 = findViewById(R.id.pin12);
        pin13 = findViewById(R.id.pin13);
        pin14 = findViewById(R.id.pin14);
        pin15 = findViewById(R.id.pin15);
        pin16 = findViewById(R.id.pin16);
        pin17 = findViewById(R.id.pin17);
        pin18 = findViewById(R.id.pin18);
        pin19 = findViewById(R.id.pin19);
        pin20 = findViewById(R.id.pin20);
        pin21 = findViewById(R.id.pin21);
        pin22 = findViewById(R.id.pin22);
        pin23 = findViewById(R.id.pin23);
        pin24 = findViewById(R.id.pin24);
        pin25 = findViewById(R.id.pin25);
        pin26 = findViewById(R.id.pin26);
        pin27 = findViewById(R.id.pin27);
        pin28 = findViewById(R.id.pin28);
        pin29 = findViewById(R.id.pin29);
        pin30 = findViewById(R.id.pin30);
        pin31 = findViewById(R.id.pin31);
        pin32 = findViewById(R.id.pin32);
        pin33 = findViewById(R.id.pin33);
        pin34 = findViewById(R.id.pin34);
        pin35 = findViewById(R.id.pin35);
        pin36 = findViewById(R.id.pin36);
        pin37 = findViewById(R.id.pin37);
        pin38 = findViewById(R.id.pin38);
        pin39 = findViewById(R.id.pin39);
        pin40 = findViewById(R.id.pin40);
        pin41 = findViewById(R.id.pin41);
        pin42 = findViewById(R.id.pin42);
        pin43 = findViewById(R.id.pin43);
        pin44 = findViewById(R.id.pin44);
        pin45 = findViewById(R.id.pin45);
        pin46 = findViewById(R.id.pin46);
        pin47 = findViewById(R.id.pin47);
        pin48 = findViewById(R.id.pin48);
        pin49 = findViewById(R.id.pin49);
        pin50 = findViewById(R.id.pin50);
        pin51 = findViewById(R.id.pin51);
        pin52 = findViewById(R.id.pin52);
        pin53 = findViewById(R.id.pin53);
        pin54 = findViewById(R.id.pin54);
        pin55 = findViewById(R.id.pin55);
        pin56 = findViewById(R.id.pin56);
        pin57 = findViewById(R.id.pin57);
        pin58 = findViewById(R.id.pin58);
        pin59 = findViewById(R.id.pin59);
        pin60 = findViewById(R.id.pin60);
        pin61 = findViewById(R.id.pin61);
        pin62 = findViewById(R.id.pin62);
        pin63 = findViewById(R.id.pin63);

        counter = findViewById(R.id.counter);

        String contents = numOfCoils.getSelectedItem().toString();
        int coilLimit = Integer.parseInt(contents);
        if (totals.size() == coilLimit) {
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
            totals.clear();

            coilCounter = 0;
            counter.setText(String.valueOf(coilCounter));

            nameOfFrame.setVisibility(View.VISIBLE);
            setFrameName.setVisibility(View.VISIBLE);
            indexFinger.setVisibility(View.INVISIBLE);
            counter.setVisibility(View.INVISIBLE);
            coilsCounter.setVisibility(View.INVISIBLE);
            middleFinger.setVisibility(View.INVISIBLE);
            ringFinger.setVisibility(View.INVISIBLE);
            frameCreationView.setVisibility(View.INVISIBLE);
            saveFrame.setVisibility(View.INVISIBLE);
            firstCoil.setVisibility(View.INVISIBLE);
            secondCoil.setVisibility(View.INVISIBLE);
            thirdCoil.setVisibility(View.INVISIBLE);
            fourthCoil.setVisibility(View.INVISIBLE);
            fifthCoil.setVisibility(View.INVISIBLE);
            sixthCoil.setVisibility(View.INVISIBLE);
            seventhCoil.setVisibility(View.INVISIBLE);
            eighthCoil.setVisibility(View.INVISIBLE);

            pin0.setChecked(false);
            pin0.setBackgroundColor(Color.TRANSPARENT);
            pin0.setVisibility(View.INVISIBLE);
            pin1.setChecked(false);
            pin1.setBackgroundColor(Color.TRANSPARENT);
            pin1.setVisibility(View.INVISIBLE);
            pin2.setChecked(false);
            pin2.setBackgroundColor(Color.TRANSPARENT);
            pin2.setVisibility(View.INVISIBLE);
            pin3.setChecked(false);
            pin3.setBackgroundColor(Color.TRANSPARENT);
            pin3.setVisibility(View.INVISIBLE);
            pin4.setChecked(false);
            pin4.setBackgroundColor(Color.TRANSPARENT);
            pin4.setVisibility(View.INVISIBLE);
            pin5.setChecked(false);
            pin5.setBackgroundColor(Color.TRANSPARENT);
            pin5.setVisibility(View.INVISIBLE);
            pin6.setChecked(false);
            pin6.setBackgroundColor(Color.TRANSPARENT);
            pin6.setVisibility(View.INVISIBLE);
            pin7.setChecked(false);
            pin7.setBackgroundColor(Color.TRANSPARENT);
            pin7.setVisibility(View.INVISIBLE);
            pin8.setChecked(false);
            pin8.setBackgroundColor(Color.TRANSPARENT);
            pin8.setVisibility(View.INVISIBLE);
            pin9.setChecked(false);
            pin9.setBackgroundColor(Color.TRANSPARENT);
            pin9.setVisibility(View.INVISIBLE);
            pin10.setChecked(false);
            pin10.setBackgroundColor(Color.TRANSPARENT);
            pin10.setVisibility(View.INVISIBLE);
            pin11.setChecked(false);
            pin11.setBackgroundColor(Color.TRANSPARENT);
            pin11.setVisibility(View.INVISIBLE);
            pin12.setChecked(false);
            pin12.setBackgroundColor(Color.TRANSPARENT);
            pin12.setVisibility(View.INVISIBLE);
            pin13.setChecked(false);
            pin13.setBackgroundColor(Color.TRANSPARENT);
            pin13.setVisibility(View.INVISIBLE);
            pin14.setChecked(false);
            pin14.setBackgroundColor(Color.TRANSPARENT);
            pin14.setVisibility(View.INVISIBLE);
            pin15.setChecked(false);
            pin15.setBackgroundColor(Color.TRANSPARENT);
            pin15.setVisibility(View.INVISIBLE);
            pin16.setChecked(false);
            pin16.setBackgroundColor(Color.TRANSPARENT);
            pin16.setVisibility(View.INVISIBLE);
            pin17.setChecked(false);
            pin17.setBackgroundColor(Color.TRANSPARENT);
            pin17.setVisibility(View.INVISIBLE);
            pin18.setChecked(false);
            pin18.setBackgroundColor(Color.TRANSPARENT);
            pin18.setVisibility(View.INVISIBLE);
            pin19.setChecked(false);
            pin19.setBackgroundColor(Color.TRANSPARENT);
            pin19.setVisibility(View.INVISIBLE);
            pin20.setChecked(false);
            pin20.setBackgroundColor(Color.TRANSPARENT);
            pin20.setVisibility(View.INVISIBLE);
            pin21.setChecked(false);
            pin21.setBackgroundColor(Color.TRANSPARENT);
            pin21.setVisibility(View.INVISIBLE);
            pin22.setChecked(false);
            pin22.setBackgroundColor(Color.TRANSPARENT);
            pin22.setVisibility(View.INVISIBLE);
            pin23.setChecked(false);
            pin23.setBackgroundColor(Color.TRANSPARENT);
            pin23.setVisibility(View.INVISIBLE);
            pin24.setChecked(false);
            pin24.setBackgroundColor(Color.TRANSPARENT);
            pin24.setVisibility(View.INVISIBLE);
            pin25.setChecked(false);
            pin25.setBackgroundColor(Color.TRANSPARENT);
            pin25.setVisibility(View.INVISIBLE);
            pin26.setChecked(false);
            pin26.setBackgroundColor(Color.TRANSPARENT);
            pin26.setVisibility(View.INVISIBLE);
            pin27.setChecked(false);
            pin27.setBackgroundColor(Color.TRANSPARENT);
            pin27.setVisibility(View.INVISIBLE);
            pin28.setChecked(false);
            pin28.setBackgroundColor(Color.TRANSPARENT);
            pin28.setVisibility(View.INVISIBLE);
            pin29.setChecked(false);
            pin29.setBackgroundColor(Color.TRANSPARENT);
            pin29.setVisibility(View.INVISIBLE);
            pin30.setChecked(false);
            pin30.setBackgroundColor(Color.TRANSPARENT);
            pin30.setVisibility(View.INVISIBLE);
            pin31.setChecked(false);
            pin31.setBackgroundColor(Color.TRANSPARENT);
            pin31.setVisibility(View.INVISIBLE);
            pin32.setChecked(false);
            pin32.setBackgroundColor(Color.TRANSPARENT);
            pin32.setVisibility(View.INVISIBLE);
            pin33.setChecked(false);
            pin33.setBackgroundColor(Color.TRANSPARENT);
            pin33.setVisibility(View.INVISIBLE);
            pin34.setChecked(false);
            pin34.setBackgroundColor(Color.TRANSPARENT);
            pin34.setVisibility(View.INVISIBLE);
            pin35.setChecked(false);
            pin35.setBackgroundColor(Color.TRANSPARENT);
            pin35.setVisibility(View.INVISIBLE);
            pin36.setChecked(false);
            pin36.setBackgroundColor(Color.TRANSPARENT);
            pin36.setVisibility(View.INVISIBLE);
            pin37.setChecked(false);
            pin37.setBackgroundColor(Color.TRANSPARENT);
            pin37.setVisibility(View.INVISIBLE);
            pin38.setChecked(false);
            pin38.setBackgroundColor(Color.TRANSPARENT);
            pin38.setVisibility(View.INVISIBLE);
            pin39.setChecked(false);
            pin39.setBackgroundColor(Color.TRANSPARENT);
            pin39.setVisibility(View.INVISIBLE);
            pin40.setChecked(false);
            pin40.setBackgroundColor(Color.TRANSPARENT);
            pin40.setVisibility(View.INVISIBLE);
            pin41.setChecked(false);
            pin41.setBackgroundColor(Color.TRANSPARENT);
            pin41.setVisibility(View.INVISIBLE);
            pin42.setChecked(false);
            pin42.setBackgroundColor(Color.TRANSPARENT);
            pin42.setVisibility(View.INVISIBLE);
            pin43.setChecked(false);
            pin43.setBackgroundColor(Color.TRANSPARENT);
            pin43.setVisibility(View.INVISIBLE);
            pin44.setChecked(false);
            pin44.setBackgroundColor(Color.TRANSPARENT);
            pin44.setVisibility(View.INVISIBLE);
            pin45.setChecked(false);
            pin45.setBackgroundColor(Color.TRANSPARENT);
            pin45.setVisibility(View.INVISIBLE);
            pin46.setChecked(false);
            pin46.setBackgroundColor(Color.TRANSPARENT);
            pin46.setVisibility(View.INVISIBLE);
            pin47.setChecked(false);
            pin47.setBackgroundColor(Color.TRANSPARENT);
            pin47.setVisibility(View.INVISIBLE);
            pin48.setChecked(false);
            pin48.setBackgroundColor(Color.TRANSPARENT);
            pin48.setVisibility(View.INVISIBLE);
            pin49.setChecked(false);
            pin49.setBackgroundColor(Color.TRANSPARENT);
            pin49.setVisibility(View.INVISIBLE);
            pin50.setChecked(false);
            pin50.setBackgroundColor(Color.TRANSPARENT);
            pin50.setVisibility(View.INVISIBLE);
            pin51.setChecked(false);
            pin51.setBackgroundColor(Color.TRANSPARENT);
            pin51.setVisibility(View.INVISIBLE);
            pin52.setChecked(false);
            pin52.setBackgroundColor(Color.TRANSPARENT);
            pin52.setVisibility(View.INVISIBLE);
            pin53.setChecked(false);
            pin53.setBackgroundColor(Color.TRANSPARENT);
            pin53.setVisibility(View.INVISIBLE);
            pin54.setChecked(false);
            pin54.setBackgroundColor(Color.TRANSPARENT);
            pin54.setVisibility(View.INVISIBLE);
            pin55.setChecked(false);
            pin55.setBackgroundColor(Color.TRANSPARENT);
            pin55.setVisibility(View.INVISIBLE);
            pin56.setChecked(false);
            pin56.setBackgroundColor(Color.TRANSPARENT);
            pin56.setVisibility(View.INVISIBLE);
            pin57.setChecked(false);
            pin57.setBackgroundColor(Color.TRANSPARENT);
            pin57.setVisibility(View.INVISIBLE);
            pin58.setChecked(false);
            pin58.setBackgroundColor(Color.TRANSPARENT);
            pin58.setVisibility(View.INVISIBLE);
            pin59.setChecked(false);
            pin59.setBackgroundColor(Color.TRANSPARENT);
            pin59.setVisibility(View.INVISIBLE);
            pin60.setChecked(false);
            pin60.setBackgroundColor(Color.TRANSPARENT);
            pin60.setVisibility(View.INVISIBLE);
            pin61.setChecked(false);
            pin61.setBackgroundColor(Color.TRANSPARENT);
            pin61.setVisibility(View.INVISIBLE);
            pin62.setChecked(false);
            pin62.setBackgroundColor(Color.TRANSPARENT);
            pin62.setVisibility(View.INVISIBLE);
            pin63.setChecked(false);
            pin63.setBackgroundColor(Color.TRANSPARENT);
            pin63.setVisibility(View.INVISIBLE);
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
     * Method to reload the frame and sequence spinner (export)
     *
     * @param view
     */
    public void reloadFramesAndSequences(View view) {
        Spinner frameSequenceList = findViewById(R.id.frameSequenceList);
        ArrayList<Object> frameSequenceLists = new ArrayList<>();

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

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, frameSequenceLists);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        frameSequenceList.setAdapter(adapter);
    }

    /**
     * Method to reload the frame spinner (frame)
     *
     * @param view
     */
    public void reloadTheFrameList(View view) {
        Spinner frameLists = findViewById(R.id.frameList);
        ArrayList<Object> frameList = new ArrayList<>();

        frameList.add(frameName1.toString());
        frameList.add(frameName2.toString());
        frameList.add(frameName3.toString());
        frameList.add(frameName4.toString());
        frameList.add(frameName5.toString());
        frameList.add(frameName6.toString());

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, frameList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        frameLists.setAdapter(adapter);
    }

    /**
     * Method to reload the sequence spinner (sequence)
     *
     * @param view
     */
    public void reloadTheSequenceList(View view) {
        Spinner sequenceLists = findViewById(R.id.sequenceList);
        ArrayList<Object> sequenceList = new ArrayList<>();

        sequenceList.add(sequenceName1.toString());
        sequenceList.add(sequenceName2.toString());
        sequenceList.add(sequenceName3.toString());
        sequenceList.add(sequenceName4.toString());

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sequenceList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sequenceLists.setAdapter(adapter);
    }

    /**
     * Method to view the selected frame (frame)
     *
     * @param view
     */
    public void viewTheSelectedFrame(View view) {
        Spinner frameLists = findViewById(R.id.frameList);
        TextView t = findViewById(R.id.savedFrameView);

        String content = frameLists.getSelectedItem().toString();
        if (!content.contains("[]")) {
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
        } else {
            Toast.makeText(getApplicationContext(), "Can't view an empty frame!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method to view the selected sequence (sequence)
     *
     * @param view
     */
    public void viewTheSelectedSequence(View view) {
        Spinner sequenceLists = findViewById(R.id.sequenceList);
        TextView t = findViewById(R.id.savedSequenceView);

        String content = sequenceLists.getSelectedItem().toString();
        if (!content.contains("[]")) {
            if (content.equals(sequenceName1.toString())) {
                t.setText(sequenceFinished1.toString());
            } else if (content.equals(sequenceName2.toString())) {
                t.setText(sequenceFinished2.toString());
            } else if (content.equals(sequenceName3.toString())) {
                t.setText(sequenceFinished3.toString());
            } else if (content.equals(sequenceName4.toString())) {
                t.setText(sequenceFinished4.toString());
            }
        } else {
            Toast.makeText(getApplicationContext(), "Can't export an empty frame/sequence!", Toast.LENGTH_SHORT).show();
        }
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

    public void export(View view) {
        Spinner frameSequenceList = findViewById(R.id.frameSequenceList);
        TextView t = findViewById(R.id.directory);
        File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Haptic_Feedback");
        if (path.exists() && path.isDirectory()) {

        } else {
            path.mkdir();
        }

        String text = frameSequenceList.getSelectedItem().toString();
        String frameSequenceName;
        String newLine = "\n";

        String name;
        String coils;
        String delay;
        String time;

        String frames;

        String first;
        String second;
        String third;
        String fourth;
        String fifth;
        String sixth;
        String seventh;
        String eighth;
        String ninth;

        String tenth;
        String eleventh;
        String twelth;
        String thirteen;

        String frame1 = "";
        String frame2 = "";
        String frame3 = "";
        String frame4 = "";
        String frame5 = "";
        String frame6 = "";

        String combined;

        if (!text.contains("[]")) {
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
                    ninth = text.substring(86, 91);

                    text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                            + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                            + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth;
                }
                File dir = new File(path, frameSequenceName);

                try {
                    FileOutputStream fos = new FileOutputStream(dir);
                    fos.write(text.getBytes());
                    t.setText(dir.toString());
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
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
                    ninth = text.substring(86, 91);

                    text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                            + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                            + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth;
                }
                File dir = new File(path, frameSequenceName);

                try {
                    FileOutputStream fos = new FileOutputStream(dir);
                    fos.write(text.getBytes());
                    t.setText(dir.toString());
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
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
                    ninth = text.substring(86, 91);

                    text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                            + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                            + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth;
                }
                File dir = new File(path, frameSequenceName);

                try {
                    FileOutputStream fos = new FileOutputStream(dir);
                    fos.write(text.getBytes());
                    t.setText(dir.toString());
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
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
                    ninth = text.substring(86, 91);

                    text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                            + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                            + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth;
                }
                File dir = new File(path, frameSequenceName);

                try {
                    FileOutputStream fos = new FileOutputStream(dir);
                    fos.write(text.getBytes());
                    t.setText(dir.toString());
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
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
                    ninth = text.substring(86, 91);

                    text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                            + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                            + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth;
                }
                File dir = new File(path, frameSequenceName);

                try {
                    FileOutputStream fos = new FileOutputStream(dir);
                    fos.write(text.getBytes());
                    t.setText(dir.toString());
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
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
                    ninth = text.substring(86, 91);

                    text = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                            + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                            + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth;
                }
                File dir = new File(path, frameSequenceName);

                try {
                    FileOutputStream fos = new FileOutputStream(dir);
                    fos.write(text.getBytes());
                    t.setText(dir.toString());
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (text.equals(sequenceName1.toString())) {
                text = sequenceFinished1.toString();
                frameSequenceName = text.substring(2, 7);
                frameSequenceName = frameSequenceName + ".txt";
                if (text.length() == 39) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(32, 37);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + newLine;
                } else if (text.length() == 55) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(48, 53);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + newLine;
                } else if (text.length() == 71) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(64, 69);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + newLine;
                } else if (text.length() == 87) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(64, 69);
                    eighth = text.substring(72, 77);
                    ninth = text.substring(80, 85);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + eighth + newLine + ninth + newLine + newLine;
                } else if (text.length() == 103) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(64, 69);
                    eighth = text.substring(72, 77);
                    ninth = text.substring(80, 85);
                    tenth = text.substring(88, 93);
                    eleventh = text.substring(96, 101);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + eighth + newLine + ninth + newLine + tenth + newLine + eleventh
                            + newLine + newLine;
                } else if (text.length() == 119) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(65, 70);
                    eighth = text.substring(73, 78);
                    ninth = text.substring(81, 86);
                    tenth = text.substring(89, 94);
                    eleventh = text.substring(97, 102);
                    twelth = text.substring(105, 110);
                    thirteen = text.substring(112, 117);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + eighth + newLine + ninth + newLine + tenth + newLine + eleventh
                            + newLine + twelth + newLine + thirteen + newLine + newLine;
                }
                if (sequenceFinished1.toString().contains(frameNameF1.toString())) {
                    frame1 = frameFinished1.toString();
                    if (frame1.length() == 44) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame1.length() == 51) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame1.length() == 58) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame1.length() == 65) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame1.length() == 72) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame1.length() == 79) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);
                        seventh = frame1.substring(72, 77);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame1.length() == 86) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);
                        seventh = frame1.substring(72, 77);
                        eighth = frame1.substring(79, 84);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame1.length() == 93) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);
                        seventh = frame1.substring(72, 77);
                        eighth = frame1.substring(79, 84);
                        ninth = frame1.substring(86, 91);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished1.toString().contains(frameNameF2.toString())) {
                    frame2 = frameFinished2.toString();
                    if (frame2.length() == 44) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame2.length() == 51) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame2.length() == 58) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame2.length() == 65) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame2.length() == 72) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame2.length() == 79) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);
                        seventh = frame2.substring(72, 77);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame2.length() == 86) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);
                        seventh = frame2.substring(72, 77);
                        eighth = frame2.substring(79, 84);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame2.length() == 93) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);
                        seventh = frame2.substring(72, 77);
                        eighth = frame2.substring(79, 84);
                        ninth = frame2.substring(86, 91);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished1.toString().contains(frameNameF3.toString())) {
                    frame3 = frameFinished3.toString();
                    if (frame3.length() == 44) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame3.length() == 51) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame3.length() == 58) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame3.length() == 65) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame3.length() == 72) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame3.length() == 79) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);
                        seventh = frame3.substring(72, 77);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame3.length() == 86) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);
                        seventh = frame3.substring(72, 77);
                        eighth = frame3.substring(79, 84);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame3.length() == 93) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);
                        seventh = frame3.substring(72, 77);
                        eighth = frame3.substring(79, 84);
                        ninth = frame3.substring(86, 91);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished1.toString().contains(frameNameF4.toString())) {
                    frame4 = frameFinished4.toString();
                    if (frame4.length() == 44) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame4.length() == 51) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame4.length() == 58) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame4.length() == 65) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame4.length() == 72) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame4.length() == 79) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);
                        seventh = frame4.substring(72, 77);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame4.length() == 86) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);
                        seventh = frame4.substring(72, 77);
                        eighth = frame4.substring(79, 84);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame4.length() == 93) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);
                        seventh = frame4.substring(72, 77);
                        eighth = frame4.substring(79, 84);
                        ninth = frame4.substring(86, 91);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished1.toString().contains(frameNameF5.toString())) {
                    frame5 = frameFinished5.toString();
                    if (frame5.length() == 44) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame5.length() == 51) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame5.length() == 58) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame5.length() == 65) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame5.length() == 72) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame5.length() == 79) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);
                        seventh = frame5.substring(72, 77);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame5.length() == 86) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);
                        seventh = frame5.substring(72, 77);
                        eighth = frame5.substring(79, 84);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame5.length() == 93) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);
                        seventh = frame5.substring(72, 77);
                        eighth = frame5.substring(79, 84);
                        ninth = frame5.substring(86, 91);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished1.toString().contains(frameNameF6.toString())) {
                    frame6 = frameFinished6.toString();
                    if (frame6.length() == 44) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame6.length() == 51) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame6.length() == 58) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame6.length() == 65) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame6.length() == 72) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame6.length() == 79) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);
                        seventh = frame6.substring(72, 77);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame6.length() == 86) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);
                        seventh = frame6.substring(72, 77);
                        eighth = frame6.substring(79, 84);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame6.length() == 93) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);
                        seventh = frame6.substring(72, 77);
                        eighth = frame6.substring(79, 84);
                        ninth = frame6.substring(86, 91);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }

                combined = text + frame1 + frame2 + frame3 + frame4 + frame5 + frame6;

                File dir = new File(path, frameSequenceName);

                try {
                    FileOutputStream fos = new FileOutputStream(dir);
                    fos.write(combined.getBytes());
                    t.setText(dir.toString());
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (text.equals(sequenceName2.toString())) {
                text = sequenceFinished2.toString();
                frameSequenceName = text.substring(2, 7);
                frameSequenceName = frameSequenceName + ".txt";
                if (text.length() == 39) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(32, 37);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + newLine;
                } else if (text.length() == 55) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(48, 53);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + newLine;
                } else if (text.length() == 71) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(64, 69);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + newLine;
                } else if (text.length() == 87) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(64, 69);
                    eighth = text.substring(72, 77);
                    ninth = text.substring(80, 85);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + eighth + newLine + ninth + newLine + newLine;
                } else if (text.length() == 103) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(64, 69);
                    eighth = text.substring(72, 77);
                    ninth = text.substring(80, 85);
                    tenth = text.substring(88, 93);
                    eleventh = text.substring(96, 101);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + eighth + newLine + ninth + newLine + tenth + newLine + eleventh
                            + newLine + newLine;
                } else if (text.length() == 119) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(65, 70);
                    eighth = text.substring(73, 78);
                    ninth = text.substring(81, 86);
                    tenth = text.substring(89, 94);
                    eleventh = text.substring(97, 102);
                    twelth = text.substring(105, 110);
                    thirteen = text.substring(112, 117);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + eighth + newLine + ninth + newLine + tenth + newLine + eleventh
                            + newLine + twelth + newLine + thirteen + newLine + newLine;
                }
                if (sequenceFinished2.toString().contains(frameNameF1.toString())) {
                    frame1 = frameFinished1.toString();
                    if (frame1.length() == 44) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame1.length() == 51) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame1.length() == 58) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame1.length() == 65) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame1.length() == 72) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame1.length() == 79) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);
                        seventh = frame1.substring(72, 77);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame1.length() == 86) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);
                        seventh = frame1.substring(72, 77);
                        eighth = frame1.substring(79, 84);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame1.length() == 93) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);
                        seventh = frame1.substring(72, 77);
                        eighth = frame1.substring(79, 84);
                        ninth = frame1.substring(86, 91);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished2.toString().contains(frameNameF2.toString())) {
                    frame2 = frameFinished2.toString();
                    if (frame2.length() == 44) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame2.length() == 51) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame2.length() == 58) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame2.length() == 65) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame2.length() == 72) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame2.length() == 79) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);
                        seventh = frame2.substring(72, 77);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame2.length() == 86) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);
                        seventh = frame2.substring(72, 77);
                        eighth = frame2.substring(79, 84);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame2.length() == 93) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);
                        seventh = frame2.substring(72, 77);
                        eighth = frame2.substring(79, 84);
                        ninth = frame2.substring(86, 91);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished2.toString().contains(frameNameF3.toString())) {
                    frame3 = frameFinished3.toString();
                    if (frame3.length() == 44) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame3.length() == 51) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame3.length() == 58) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame3.length() == 65) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame3.length() == 72) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame3.length() == 79) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);
                        seventh = frame3.substring(72, 77);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame3.length() == 86) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);
                        seventh = frame3.substring(72, 77);
                        eighth = frame3.substring(79, 84);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame3.length() == 93) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);
                        seventh = frame3.substring(72, 77);
                        eighth = frame3.substring(79, 84);
                        ninth = frame3.substring(86, 91);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished2.toString().contains(frameNameF4.toString())) {
                    frame4 = frameFinished4.toString();
                    if (frame4.length() == 44) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame4.length() == 51) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame4.length() == 58) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame4.length() == 65) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame4.length() == 72) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame4.length() == 79) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);
                        seventh = frame4.substring(72, 77);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame4.length() == 86) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);
                        seventh = frame4.substring(72, 77);
                        eighth = frame4.substring(79, 84);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame4.length() == 93) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);
                        seventh = frame4.substring(72, 77);
                        eighth = frame4.substring(79, 84);
                        ninth = frame4.substring(86, 91);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished2.toString().contains(frameNameF5.toString())) {
                    frame5 = frameFinished5.toString();
                    if (frame5.length() == 44) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame5.length() == 51) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame5.length() == 58) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame5.length() == 65) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame5.length() == 72) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame5.length() == 79) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);
                        seventh = frame5.substring(72, 77);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame5.length() == 86) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);
                        seventh = frame5.substring(72, 77);
                        eighth = frame5.substring(79, 84);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame5.length() == 93) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);
                        seventh = frame5.substring(72, 77);
                        eighth = frame5.substring(79, 84);
                        ninth = frame5.substring(86, 91);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished2.toString().contains(frameNameF6.toString())) {
                    frame6 = frameFinished6.toString();
                    if (frame6.length() == 44) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame6.length() == 51) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame6.length() == 58) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame6.length() == 65) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame6.length() == 72) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame6.length() == 79) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);
                        seventh = frame6.substring(72, 77);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame6.length() == 86) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);
                        seventh = frame6.substring(72, 77);
                        eighth = frame6.substring(79, 84);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame6.length() == 93) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);
                        seventh = frame6.substring(72, 77);
                        eighth = frame6.substring(79, 84);
                        ninth = frame6.substring(86, 91);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                combined = text + frame1 + frame2 + frame3 + frame4 + frame5 + frame6;

                File dir = new File(path, frameSequenceName);

                try {
                    FileOutputStream fos = new FileOutputStream(dir);
                    fos.write(combined.getBytes());
                    t.setText(dir.toString());
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (text.equals(sequenceName3.toString())) {
                text = sequenceFinished3.toString();
                frameSequenceName = text.substring(2, 7);
                frameSequenceName = frameSequenceName + ".txt";
                if (text.length() == 39) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(32, 37);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + newLine;
                } else if (text.length() == 55) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(48, 53);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + newLine;
                } else if (text.length() == 71) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(64, 69);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + newLine;
                } else if (text.length() == 87) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(64, 69);
                    eighth = text.substring(72, 77);
                    ninth = text.substring(80, 85);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + eighth + newLine + ninth + newLine + newLine;
                } else if (text.length() == 103) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(64, 69);
                    eighth = text.substring(72, 77);
                    ninth = text.substring(80, 85);
                    tenth = text.substring(88, 93);
                    eleventh = text.substring(96, 101);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + eighth + newLine + ninth + newLine + tenth + newLine + eleventh
                            + newLine + newLine;
                } else if (text.length() == 119) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(65, 70);
                    eighth = text.substring(73, 78);
                    ninth = text.substring(81, 86);
                    tenth = text.substring(89, 94);
                    eleventh = text.substring(97, 102);
                    twelth = text.substring(105, 110);
                    thirteen = text.substring(112, 117);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + eighth + newLine + ninth + newLine + tenth + newLine + eleventh
                            + newLine + twelth + newLine + thirteen + newLine + newLine;
                }
                if (sequenceFinished3.toString().contains(frameNameF1.toString())) {
                    frame1 = frameFinished1.toString();
                    if (frame1.length() == 44) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame1.length() == 51) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame1.length() == 58) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame1.length() == 65) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame1.length() == 72) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame1.length() == 79) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);
                        seventh = frame1.substring(72, 77);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame1.length() == 86) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);
                        seventh = frame1.substring(72, 77);
                        eighth = frame1.substring(79, 84);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame1.length() == 93) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);
                        seventh = frame1.substring(72, 77);
                        eighth = frame1.substring(79, 84);
                        ninth = frame1.substring(86, 91);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished3.toString().contains(frameNameF2.toString())) {
                    frame2 = frameFinished2.toString();
                    if (frame2.length() == 44) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame2.length() == 51) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame2.length() == 58) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame2.length() == 65) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame2.length() == 72) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame2.length() == 79) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);
                        seventh = frame2.substring(72, 77);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame2.length() == 86) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);
                        seventh = frame2.substring(72, 77);
                        eighth = frame2.substring(79, 84);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame2.length() == 93) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);
                        seventh = frame2.substring(72, 77);
                        eighth = frame2.substring(79, 84);
                        ninth = frame2.substring(86, 91);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished3.toString().contains(frameNameF3.toString())) {
                    frame3 = frameFinished3.toString();
                    if (frame3.length() == 44) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame3.length() == 51) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame3.length() == 58) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame3.length() == 65) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame3.length() == 72) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame3.length() == 79) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);
                        seventh = frame3.substring(72, 77);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame3.length() == 86) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);
                        seventh = frame3.substring(72, 77);
                        eighth = frame3.substring(79, 84);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame3.length() == 93) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);
                        seventh = frame3.substring(72, 77);
                        eighth = frame3.substring(79, 84);
                        ninth = frame3.substring(86, 91);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished3.toString().contains(frameNameF4.toString())) {
                    frame4 = frameFinished4.toString();
                    if (frame4.length() == 44) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame4.length() == 51) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame4.length() == 58) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame4.length() == 65) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame4.length() == 72) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame4.length() == 79) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);
                        seventh = frame4.substring(72, 77);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame4.length() == 86) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);
                        seventh = frame4.substring(72, 77);
                        eighth = frame4.substring(79, 84);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame4.length() == 93) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);
                        seventh = frame4.substring(72, 77);
                        eighth = frame4.substring(79, 84);
                        ninth = frame4.substring(86, 91);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished3.toString().contains(frameNameF5.toString())) {
                    frame5 = frameFinished5.toString();
                    if (frame5.length() == 44) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame5.length() == 51) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame5.length() == 58) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame5.length() == 65) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame5.length() == 72) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame5.length() == 79) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);
                        seventh = frame5.substring(72, 77);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame5.length() == 86) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);
                        seventh = frame5.substring(72, 77);
                        eighth = frame5.substring(79, 84);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame5.length() == 93) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);
                        seventh = frame5.substring(72, 77);
                        eighth = frame5.substring(79, 84);
                        ninth = frame5.substring(86, 91);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished3.toString().contains(frameNameF6.toString())) {
                    frame6 = frameFinished6.toString();
                    if (frame6.length() == 44) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame6.length() == 51) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame6.length() == 58) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame6.length() == 65) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame6.length() == 72) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame6.length() == 79) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);
                        seventh = frame6.substring(72, 77);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame6.length() == 86) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);
                        seventh = frame6.substring(72, 77);
                        eighth = frame6.substring(79, 84);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame6.length() == 93) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);
                        seventh = frame6.substring(72, 77);
                        eighth = frame6.substring(79, 84);
                        ninth = frame6.substring(86, 91);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                combined = text + frame1 + frame2 + frame3 + frame4 + frame5 + frame6;

                File dir = new File(path, frameSequenceName);

                try {
                    FileOutputStream fos = new FileOutputStream(dir);
                    fos.write(combined.getBytes());
                    t.setText(dir.toString());
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (text.equals(sequenceName4.toString())) {
                text = sequenceFinished4.toString();
                frameSequenceName = text.substring(2, 7);
                frameSequenceName = frameSequenceName + ".txt";
                if (text.length() == 39) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(32, 37);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + newLine;
                } else if (text.length() == 55) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(48, 53);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + newLine;
                } else if (text.length() == 71) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(64, 69);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + newLine;
                } else if (text.length() == 87) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(64, 69);
                    eighth = text.substring(72, 77);
                    ninth = text.substring(80, 85);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + eighth + newLine + ninth + newLine + newLine;
                } else if (text.length() == 103) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(64, 69);
                    eighth = text.substring(72, 77);
                    ninth = text.substring(80, 85);
                    tenth = text.substring(88, 93);
                    eleventh = text.substring(96, 101);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + eighth + newLine + ninth + newLine + tenth + newLine + eleventh
                            + newLine + newLine;
                } else if (text.length() == 119) {
                    name = text.substring(2, 7);
                    frames = text.substring(9, 14);

                    first = text.substring(17, 22);
                    second = text.substring(25, 30);
                    third = text.substring(33, 38);
                    fourth = text.substring(41, 46);
                    fifth = text.substring(49, 54);
                    sixth = text.substring(57, 62);
                    seventh = text.substring(65, 70);
                    eighth = text.substring(73, 78);
                    ninth = text.substring(81, 86);
                    tenth = text.substring(89, 94);
                    eleventh = text.substring(97, 102);
                    twelth = text.substring(105, 110);
                    thirteen = text.substring(112, 117);

                    text = name + newLine + frames + newLine + first + newLine + second + newLine + third
                            + newLine + fourth + newLine + fifth + newLine + sixth + newLine + seventh
                            + newLine + eighth + newLine + ninth + newLine + tenth + newLine + eleventh
                            + newLine + twelth + newLine + thirteen + newLine + newLine;
                }
                if (sequenceFinished4.toString().contains(frameNameF1.toString())) {
                    frame1 = frameFinished1.toString();
                    if (frame1.length() == 44) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame1.length() == 51) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame1.length() == 58) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame1.length() == 65) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame1.length() == 72) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame1.length() == 79) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);
                        seventh = frame1.substring(72, 77);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame1.length() == 86) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);
                        seventh = frame1.substring(72, 77);
                        eighth = frame1.substring(79, 84);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame1.length() == 93) {
                        name = frame1.substring(2, 7);
                        coils = frame1.substring(9, 14);
                        delay = frame1.substring(16, 21);
                        time = frame1.substring(23, 28);

                        first = frame1.substring(30, 35);
                        second = frame1.substring(37, 42);
                        third = frame1.substring(44, 49);
                        fourth = frame1.substring(51, 56);
                        fifth = frame1.substring(58, 63);
                        sixth = frame1.substring(65, 70);
                        seventh = frame1.substring(72, 77);
                        eighth = frame1.substring(79, 84);
                        ninth = frame1.substring(86, 91);

                        frame1 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished4.toString().contains(frameNameF2.toString())) {
                    frame2 = frameFinished2.toString();
                    if (frame2.length() == 44) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame2.length() == 51) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame2.length() == 58) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame2.length() == 65) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame2.length() == 72) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame2.length() == 79) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);
                        seventh = frame2.substring(72, 77);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame2.length() == 86) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);
                        seventh = frame2.substring(72, 77);
                        eighth = frame2.substring(79, 84);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame2.length() == 93) {
                        name = frame2.substring(2, 7);
                        coils = frame2.substring(9, 14);
                        delay = frame2.substring(16, 21);
                        time = frame2.substring(23, 28);

                        first = frame2.substring(30, 35);
                        second = frame2.substring(37, 42);
                        third = frame2.substring(44, 49);
                        fourth = frame2.substring(51, 56);
                        fifth = frame2.substring(58, 63);
                        sixth = frame2.substring(65, 70);
                        seventh = frame2.substring(72, 77);
                        eighth = frame2.substring(79, 84);
                        ninth = frame2.substring(86, 91);

                        frame2 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished4.toString().contains(frameNameF3.toString())) {
                    frame3 = frameFinished3.toString();
                    if (frame3.length() == 44) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame3.length() == 51) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame3.length() == 58) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame3.length() == 65) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame3.length() == 72) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame3.length() == 79) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);
                        seventh = frame3.substring(72, 77);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame3.length() == 86) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);
                        seventh = frame3.substring(72, 77);
                        eighth = frame3.substring(79, 84);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame3.length() == 93) {
                        name = frame3.substring(2, 7);
                        coils = frame3.substring(9, 14);
                        delay = frame3.substring(16, 21);
                        time = frame3.substring(23, 28);

                        first = frame3.substring(30, 35);
                        second = frame3.substring(37, 42);
                        third = frame3.substring(44, 49);
                        fourth = frame3.substring(51, 56);
                        fifth = frame3.substring(58, 63);
                        sixth = frame3.substring(65, 70);
                        seventh = frame3.substring(72, 77);
                        eighth = frame3.substring(79, 84);
                        ninth = frame3.substring(86, 91);

                        frame3 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished4.toString().contains(frameNameF4.toString())) {
                    frame4 = frameFinished4.toString();
                    if (frame4.length() == 44) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame4.length() == 51) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame4.length() == 58) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame4.length() == 65) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame4.length() == 72) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame4.length() == 79) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);
                        seventh = frame4.substring(72, 77);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame4.length() == 86) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);
                        seventh = frame4.substring(72, 77);
                        eighth = frame4.substring(79, 84);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame4.length() == 93) {
                        name = frame4.substring(2, 7);
                        coils = frame4.substring(9, 14);
                        delay = frame4.substring(16, 21);
                        time = frame4.substring(23, 28);

                        first = frame4.substring(30, 35);
                        second = frame4.substring(37, 42);
                        third = frame4.substring(44, 49);
                        fourth = frame4.substring(51, 56);
                        fifth = frame4.substring(58, 63);
                        sixth = frame4.substring(65, 70);
                        seventh = frame4.substring(72, 77);
                        eighth = frame4.substring(79, 84);
                        ninth = frame4.substring(86, 91);

                        frame4 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished4.toString().contains(frameNameF5.toString())) {
                    frame5 = frameFinished5.toString();
                    if (frame5.length() == 44) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame5.length() == 51) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame5.length() == 58) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame5.length() == 65) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame5.length() == 72) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame5.length() == 79) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);
                        seventh = frame5.substring(72, 77);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame5.length() == 86) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);
                        seventh = frame5.substring(72, 77);
                        eighth = frame5.substring(79, 84);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame5.length() == 93) {
                        name = frame5.substring(2, 7);
                        coils = frame5.substring(9, 14);
                        delay = frame5.substring(16, 21);
                        time = frame5.substring(23, 28);

                        first = frame5.substring(30, 35);
                        second = frame5.substring(37, 42);
                        third = frame5.substring(44, 49);
                        fourth = frame5.substring(51, 56);
                        fifth = frame5.substring(58, 63);
                        sixth = frame5.substring(65, 70);
                        seventh = frame5.substring(72, 77);
                        eighth = frame5.substring(79, 84);
                        ninth = frame5.substring(86, 91);

                        frame5 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                if (sequenceFinished4.toString().contains(frameNameF6.toString())) {
                    frame6 = frameFinished6.toString();
                    if (frame6.length() == 44) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + newLine;
                    } else if (frame6.length() == 51) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + newLine;
                    } else if (frame6.length() == 58) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine
                                + newLine;
                    } else if (frame6.length() == 65) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + newLine;
                    } else if (frame6.length() == 72) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + newLine;
                    } else if (frame6.length() == 79) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);
                        seventh = frame6.substring(72, 77);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + newLine;
                    } else if (frame6.length() == 86) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);
                        seventh = frame6.substring(72, 77);
                        eighth = frame6.substring(79, 84);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine
                                + newLine;
                    } else if (frame6.length() == 93) {
                        name = frame6.substring(2, 7);
                        coils = frame6.substring(9, 14);
                        delay = frame6.substring(16, 21);
                        time = frame6.substring(23, 28);

                        first = frame6.substring(30, 35);
                        second = frame6.substring(37, 42);
                        third = frame6.substring(44, 49);
                        fourth = frame6.substring(51, 56);
                        fifth = frame6.substring(58, 63);
                        sixth = frame6.substring(65, 70);
                        seventh = frame6.substring(72, 77);
                        eighth = frame6.substring(79, 84);
                        ninth = frame6.substring(86, 91);

                        frame6 = name + newLine + coils + newLine + delay + newLine + time + newLine + first
                                + newLine + second + newLine + third + newLine + fourth + newLine + fifth
                                + newLine + sixth + newLine + seventh + newLine + eighth + newLine + ninth
                                + newLine + newLine;
                    }
                }
                combined = text + frame1 + frame2 + frame3 + frame4 + frame5 + frame6;

                File dir = new File(path, frameSequenceName);

                try {
                    FileOutputStream fos = new FileOutputStream(dir);
                    fos.write(combined.getBytes());
                    t.setText(dir.toString());
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Can't export an empty frame/sequence!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method to give the sequence a name (sequence)
     *
     * @param view
     */
    public void nameTheSequence(View view) {
        nameOfSequence = findViewById(R.id.nameSequence);
        setSequenceName = findViewById(R.id.setSequenceName);

        numOfFramesSelected = findViewById(R.id.setNumFrames);
        setFrames = findViewById(R.id.numOfFrames);

        String content = nameOfSequence.getSelectedItem().toString();

        if (sequenceName1.isEmpty()) {
            sequenceName1.add("$S" + content);
            commandInformationSeq.add(0, "$S" + content);
            if (commandInformationSeq.add(true)) {
                nameOfSequence.setVisibility(View.INVISIBLE);
                setSequenceName.setVisibility(View.INVISIBLE);
                commandInformationSeq.remove(true);
                numOfFramesSelected.setVisibility(View.VISIBLE);
                setFrames.setVisibility(View.VISIBLE);
            }
        } else if (sequenceName2.isEmpty()) {
            if (!sequenceName1.contains("$S" + content)) {
                sequenceName2.add("$S" + content);
                commandInformationSeq.add(0, "$S" + content);
                if (commandInformationSeq.add(true)) {
                    nameOfSequence.setVisibility(View.INVISIBLE);
                    setSequenceName.setVisibility(View.INVISIBLE);
                    commandInformationSeq.remove(true);
                    numOfFramesSelected.setVisibility(View.VISIBLE);
                    setFrames.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
            }
        } else if (sequenceName3.isEmpty()) {
            if (!sequenceName1.contains("$S" + content) && !sequenceName2.contains("$S" + content)) {
                sequenceName3.add("$S" + content);
                commandInformationSeq.add(0, "$S" + content);
                if (commandInformationSeq.add(true)) {
                    nameOfSequence.setVisibility(View.INVISIBLE);
                    setSequenceName.setVisibility(View.INVISIBLE);
                    commandInformationSeq.remove(true);
                    numOfFramesSelected.setVisibility(View.VISIBLE);
                    setFrames.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
            }
        } else if (sequenceName4.isEmpty()) {
            if (!sequenceName1.contains("$S" + content) && !sequenceName2.contains("$S" + content) && !sequenceName3.contains("$S" + content)) {
                sequenceName4.add("$S" + content);
                commandInformationSeq.add(0, "$S" + content);
                if (commandInformationSeq.add(true)) {
                    nameOfSequence.setVisibility(View.INVISIBLE);
                    setSequenceName.setVisibility(View.INVISIBLE);
                    commandInformationSeq.remove(true);
                    numOfFramesSelected.setVisibility(View.VISIBLE);
                    setFrames.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
            }
        }

        TextView t = findViewById(R.id.sequenceCreationView);
        String start = "";
        for (Object Selections : commandInformationSeq) {
            start = start + Selections + "\n";
        }
        t.setText(start);
    }

    /**
     * Method to select the amount of frames to be used (sequence)
     *
     * @param view
     */
    public void numOfFrames(View view) {
        numOfFramesSelected = findViewById(R.id.setNumFrames);
        setFrames = findViewById(R.id.numOfFrames);

        frameAmount = findViewById(R.id.frameSelection);
        setFrame = findViewById(R.id.setFrames);

        selectRepAmount = findViewById(R.id.selectRepetitionAmount);
        setAmountOfRep = findViewById(R.id.setRepetitionAmount);

        sequenceCreationView = findViewById(R.id.sequenceCreationView);
        saveTheSequence = findViewById(R.id.saveSequence);

        TextView t = sequenceCreationView;
        String start = "";

        String content = numOfFramesSelected.getSelectedItem().toString();
        commandInformationSeq.add(1, "$n00" + content);

        for (Object Selections : commandInformationSeq) {
            start = start + Selections + "\n";
        }
        t.setText(start);

        framesUsed.add(content);

        Spinner frameList = frameAmount;
        ArrayList<Object> frameSelections = new ArrayList<>();

        frameSelections.add(frameName1.toString());
        frameSelections.add(frameName2.toString());
        frameSelections.add(frameName3.toString());
        frameSelections.add(frameName4.toString());
        frameSelections.add(frameName5.toString());
        frameSelections.add(frameName6.toString());

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, frameSelections);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        frameList.setAdapter(adapter);

        if (commandInformationSeq.add(true)) {
            setFrames.setVisibility(View.INVISIBLE);
            numOfFramesSelected.setVisibility(View.INVISIBLE);
            commandInformationSeq.remove(true);
            setFrame.setVisibility(View.VISIBLE);
            frameAmount.setVisibility(View.VISIBLE);
            selectRepAmount.setVisibility(View.VISIBLE);
            setAmountOfRep.setVisibility(View.VISIBLE);
            sequenceCreationView.setVisibility(View.VISIBLE);
            saveTheSequence.setVisibility(View.VISIBLE);

            selectRepAmount.setEnabled(false);
            setAmountOfRep.setEnabled(false);
        }
    }

    /**
     * Method to set a frame to be used (sequence)
     *
     * @param view
     */
    public void setTheFrames(View view) {
        TextView t = findViewById(R.id.sequenceCreationView);
        String finial_commands = "";
        String finial_selection = "";

        numOfFramesSelected = findViewById(R.id.setNumFrames);
        String framesSelected = numOfFramesSelected.getSelectedItem().toString();
        int frameLimit = Integer.parseInt(framesSelected);
        int frameCount = 0;

        frameAmount = findViewById(R.id.frameSelection);
        setFrame = findViewById(R.id.setFrames);

        selectRepAmount = findViewById(R.id.selectRepetitionAmount);
        setAmountOfRep = findViewById(R.id.setRepetitionAmount);

        for (Object Sequence : commandInformationSeq) {
            finial_commands = finial_commands + Sequence + "\n";
        }

        Spinner frameList = frameAmount;

        String content = frameList.getSelectedItem().toString();
        if (content.equals(frameName1.toString()) && !frameSelections.contains(frameNameF1)) {
            if (!frameName1.toString().contains("[]")) {
                totalFrames.add(frameCount);
                if (totalFrames.size() <= frameLimit) {
                    frameSelections.add(frameNameF1);
                    for (Object Selections : frameSelections) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    setFrame.setEnabled(false);
                    frameAmount.setEnabled(false);
                    selectRepAmount.setEnabled(true);
                    setAmountOfRep.setEnabled(true);
                }
            }
        } else if (content.equals(frameName1.toString()) && frameSelections.contains(frameNameF1)) {
            Toast.makeText(getApplicationContext(), "Frame already in use!", Toast.LENGTH_SHORT).show();
        } else if (content.equals(frameName2.toString()) && !frameSelections.contains(frameNameF2)) {
            if (!frameName2.toString().contains("[]")) {
                totalFrames.add(frameCount);
                if (totalFrames.size() <= frameLimit) {
                    frameSelections.add(frameNameF2);
                    for (Object Selections : frameSelections) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    setFrame.setEnabled(false);
                    frameAmount.setEnabled(false);
                    selectRepAmount.setEnabled(true);
                    setAmountOfRep.setEnabled(true);
                }
            }
        } else if (content.equals(frameName2.toString()) && frameSelections.contains(frameNameF2)) {
            Toast.makeText(getApplicationContext(), "Frame already in use!", Toast.LENGTH_SHORT).show();
        } else if (content.equals(frameName3.toString()) && !frameSelections.contains(frameNameF3)) {
            if (!frameName3.toString().contains("[]")) {
                totalFrames.add(frameCount);
                if (totalFrames.size() <= frameLimit) {
                    frameSelections.add(frameNameF3);
                    for (Object Selections : frameSelections) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    setFrame.setEnabled(false);
                    frameAmount.setEnabled(false);
                    selectRepAmount.setEnabled(true);
                    setAmountOfRep.setEnabled(true);
                }
            }
        } else if (content.equals(frameName3.toString()) && frameSelections.contains(frameNameF3)) {
            Toast.makeText(getApplicationContext(), "Frame already in use!", Toast.LENGTH_SHORT).show();
        } else if (content.equals(frameName4.toString()) && !frameSelections.contains(frameNameF4)) {
            if (!frameName4.toString().contains("[]")) {
                totalFrames.add(frameCount);
                if (totalFrames.size() <= frameLimit) {
                    frameSelections.add(frameNameF4);
                    for (Object Selections : frameSelections) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    setFrame.setEnabled(false);
                    frameAmount.setEnabled(false);
                    selectRepAmount.setEnabled(true);
                    setAmountOfRep.setEnabled(true);
                }
            }
        } else if (content.equals(frameName4.toString()) && frameSelections.contains(frameNameF4)) {
            Toast.makeText(getApplicationContext(), "Frame already in use!", Toast.LENGTH_SHORT).show();
        } else if (content.equals(frameName5.toString()) && !frameSelections.contains(frameNameF5)) {
            if (!frameName5.toString().contains("[]")) {
                totalFrames.add(frameCount);
                if (totalFrames.size() <= frameLimit) {
                    frameSelections.add(frameNameF5);
                    for (Object Selections : frameSelections) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    setFrame.setEnabled(false);
                    frameAmount.setEnabled(false);
                    selectRepAmount.setEnabled(true);
                    setAmountOfRep.setEnabled(true);
                }
            }
        } else if (content.equals(frameName5.toString()) && frameSelections.contains(frameNameF5)) {
            Toast.makeText(getApplicationContext(), "Frame already in use!", Toast.LENGTH_SHORT).show();
        } else if (content.equals(frameName6.toString()) && !frameSelections.contains(frameNameF6)) {
            if (!frameName6.toString().contains("[]")) {
                totalFrames.add(frameCount);
                if (totalFrames.size() <= frameLimit) {
                    frameSelections.add(frameNameF6);
                    for (Object Selections : frameSelections) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    setFrame.setEnabled(false);
                    frameAmount.setEnabled(false);
                    selectRepAmount.setEnabled(true);
                    setAmountOfRep.setEnabled(true);
                }
            }
        } else if (content.equals(frameName6.toString()) && frameSelections.contains(frameNameF6)) {
            Toast.makeText(getApplicationContext(), "Frame already in use!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method to set the repetition of the frame (sequence)
     *
     * @param view
     */
    public void repetitionAmount(View view) {
        TextView t = findViewById(R.id.sequenceCreationView);
        String finial_commands = "";
        String finial_selection = "";
        frameAmount = findViewById(R.id.frameSelection);
        setFrame = findViewById(R.id.setFrames);

        selectRepAmount = findViewById(R.id.selectRepetitionAmount);
        setAmountOfRep = findViewById(R.id.setRepetitionAmount);

        String content = selectRepAmount.getSelectedItem().toString();
        frameSelections.add("$r00" + content);

        numOfFramesSelected = findViewById(R.id.setNumFrames);
        String framesSelected = numOfFramesSelected.getSelectedItem().toString();
        int frameLimit = Integer.parseInt(framesSelected);

        for (Object Sequence : commandInformationSeq) {
            finial_commands = finial_commands + Sequence + "\n";
        }

        for (Object Selections : frameSelections) {
            finial_selection = finial_selection + Selections + "\n";
        }
        t.setText(finial_commands + finial_selection);

        if (totalFrames.size() == frameLimit) {
            frameSelections.remove(true);
            setFrame.setEnabled(false);
            frameAmount.setEnabled(false);
            selectRepAmount.setEnabled(false);
            setAmountOfRep.setEnabled(false);
            Toast.makeText(getApplicationContext(), "Frame Limit Reached", Toast.LENGTH_SHORT).show();
        } else if (frameSelections.add(true)) {
            frameSelections.remove(true);
            setFrame.setEnabled(true);
            frameAmount.setEnabled(true);
            selectRepAmount.setEnabled(false);
            setAmountOfRep.setEnabled(false);
        }
    }

    /**
     * Method to save the sequence (sequence)
     */
    private void saveSequenceData() {
        nameOfSequence = findViewById(R.id.nameSequence);
        setSequenceName = findViewById(R.id.setSequenceName);

        saveTheSequence = findViewById(R.id.saveSequence);

        frameAmount = findViewById(R.id.frameSelection);
        setFrame = findViewById(R.id.setFrames);
        selectRepAmount = findViewById(R.id.selectRepetitionAmount);
        setAmountOfRep = findViewById(R.id.setRepetitionAmount);
        sequenceCreationView = findViewById(R.id.sequenceCreationView);

        numOfFramesSelected = findViewById(R.id.setNumFrames);
        String framesSelected = numOfFramesSelected.getSelectedItem().toString();
        int frameLimit = Integer.parseInt(framesSelected);
        if (totalFrames.size() == frameLimit) {
            if (sequenceFinished1.isEmpty()) {
                frameSelections.add("$T001");
                sequenceFinished1.add("" + commandInformationSeq + frameSelections);
                Toast.makeText(getApplicationContext(), "Sequence Saved " + sequenceFinished1, Toast.LENGTH_SHORT).show();
            } else if (sequenceFinished2.isEmpty()) {
                frameSelections.add("$T001");
                sequenceFinished2.add("" + commandInformationSeq + frameSelections);
                Toast.makeText(getApplicationContext(), "Sequence Saved " + sequenceFinished2, Toast.LENGTH_SHORT).show();
            } else if (sequenceFinished3.isEmpty()) {
                frameSelections.add("$T001");
                sequenceFinished3.add("" + commandInformationSeq + frameSelections);
                Toast.makeText(getApplicationContext(), "Sequence Saved " + sequenceFinished3, Toast.LENGTH_SHORT).show();
            } else if (sequenceFinished4.isEmpty()) {
                frameSelections.add("$T001");
                sequenceFinished4.add("" + commandInformationSeq + frameSelections);
                Toast.makeText(getApplicationContext(), "Sequence Saved " + sequenceFinished4, Toast.LENGTH_SHORT).show();
            }

            setFrame.setEnabled(true);
            frameAmount.setEnabled(true);
            selectRepAmount.setEnabled(false);
            setAmountOfRep.setEnabled(false);

            commandInformationSeq.clear();
            frameSelections.clear();
            totalFrames.clear();

            nameOfSequence.setVisibility(View.VISIBLE);
            setSequenceName.setVisibility(View.VISIBLE);

            setFrame.setVisibility(View.INVISIBLE);
            frameAmount.setVisibility(View.INVISIBLE);
            selectRepAmount.setVisibility(View.INVISIBLE);
            setAmountOfRep.setVisibility(View.INVISIBLE);
            sequenceCreationView.setVisibility(View.INVISIBLE);
            saveTheSequence.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(getApplicationContext(), "Please add more frames to make your selection of  " + frameLimit, Toast.LENGTH_SHORT).show();
        }
    }

    public void saveTheSequence(View view) {
        saveSequenceData();
    }
}