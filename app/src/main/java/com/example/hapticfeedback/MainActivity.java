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
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    //Frame and Sequence creation stuff
    Button setSequenceName, setFrame,
            setAmountOfRep, saveTheSequence, resetFramesUsed;

    Spinner numOfCoils, amountOfDelay, amountOfOnTime, nameOfFrame, nameOfSequence, numOfFramesSelected,
            frameAmount, selectRepAmount;

    TextView counter, limit, frameUsedLimit,
            sequenceCreationView, frameCounter, framesUsedCounter;

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

    /**
     * Boolean operators for name verification
     */
    public boolean firstFrame = false;
    public boolean secondFrame = false;
    public boolean thirdFrame = false;
    public boolean fourthFrame = false;
    public boolean fifthFrame = false;
    public boolean sixthFrame = false;
    public boolean firstSequence = false;
    public boolean secondSequence = false;
    public boolean thirdSequence = false;
    public boolean fourthSequence = false;

    public void pin(View view) {
        numOfCoils = findViewById(R.id.numberOfCoils);
        counter = findViewById(R.id.counter);

        limit = findViewById(R.id.limit);

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

        String finial_commands = "";
        String finial_selection = "";
        boolean checked = ((CheckBox) view).isChecked();
        TextView t = findViewById(R.id.frameCreationView);
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
                            pin0.setText("1");
                        } else if (totals.size() == 2) {
                            pin0.setText("2");
                        } else if (totals.size() == 3) {
                            pin0.setText("3");
                        } else if (totals.size() == 4) {
                            pin0.setText("4");
                        } else if (totals.size() == 5) {
                            pin0.setText("5");
                        } else if (totals.size() == 6) {
                            pin0.setText("6");
                        } else if (totals.size() == 7) {
                            pin0.setText("7");
                        } else if (totals.size() == 8) {
                            pin0.setText("8");
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
                        pin0.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y000");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin0.setText("");
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
                            pin1.setText("1");
                        } else if (totals.size() == 2) {
                            pin1.setText("2");
                        } else if (totals.size() == 3) {
                            pin1.setText("3");
                        } else if (totals.size() == 4) {
                            pin1.setText("4");
                        } else if (totals.size() == 5) {
                            pin1.setText("5");
                        } else if (totals.size() == 6) {
                            pin1.setText("6");
                        } else if (totals.size() == 7) {
                            pin1.setText("7");
                        } else if (totals.size() == 8) {
                            pin1.setText("8");
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
                        pin1.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y001");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin1.setText("");
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
                            pin2.setText("1");
                        } else if (totals.size() == 2) {
                            pin2.setText("2");
                        } else if (totals.size() == 3) {
                            pin2.setText("3");
                        } else if (totals.size() == 4) {
                            pin2.setText("4");
                        } else if (totals.size() == 5) {
                            pin2.setText("5");
                        } else if (totals.size() == 6) {
                            pin2.setText("6");
                        } else if (totals.size() == 7) {
                            pin2.setText("7");
                        } else if (totals.size() == 8) {
                            pin2.setText("8");
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
                        pin2.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y002");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin2.setText("");
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
                            pin3.setText("1");
                        } else if (totals.size() == 2) {
                            pin3.setText("2");
                        } else if (totals.size() == 3) {
                            pin3.setText("3");
                        } else if (totals.size() == 4) {
                            pin3.setText("4");
                        } else if (totals.size() == 5) {
                            pin3.setText("5");
                        } else if (totals.size() == 6) {
                            pin3.setText("6");
                        } else if (totals.size() == 7) {
                            pin3.setText("7");
                        } else if (totals.size() == 8) {
                            pin3.setText("8");
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
                        pin3.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y003");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin3.setText("");
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
                            pin4.setText("1");
                        } else if (totals.size() == 2) {
                            pin4.setText("2");
                        } else if (totals.size() == 3) {
                            pin4.setText("3");
                        } else if (totals.size() == 4) {
                            pin4.setText("4");
                        } else if (totals.size() == 5) {
                            pin4.setText("5");
                        } else if (totals.size() == 6) {
                            pin4.setText("6");
                        } else if (totals.size() == 7) {
                            pin4.setText("7");
                        } else if (totals.size() == 8) {
                            pin4.setText("8");
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
                        pin4.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y004");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin4.setText("");
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
                            pin5.setText("1");
                        } else if (totals.size() == 2) {
                            pin5.setText("2");
                        } else if (totals.size() == 3) {
                            pin5.setText("3");
                        } else if (totals.size() == 4) {
                            pin5.setText("4");
                        } else if (totals.size() == 5) {
                            pin5.setText("5");
                        } else if (totals.size() == 6) {
                            pin5.setText("6");
                        } else if (totals.size() == 7) {
                            pin5.setText("7");
                        } else if (totals.size() == 8) {
                            pin5.setText("8");
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
                        pin5.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y005");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin5.setText("");
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
                            pin6.setText("1");
                        } else if (totals.size() == 2) {
                            pin6.setText("2");
                        } else if (totals.size() == 3) {
                            pin6.setText("3");
                        } else if (totals.size() == 4) {
                            pin6.setText("4");
                        } else if (totals.size() == 5) {
                            pin6.setText("5");
                        } else if (totals.size() == 6) {
                            pin6.setText("6");
                        } else if (totals.size() == 7) {
                            pin6.setText("7");
                        } else if (totals.size() == 8) {
                            pin6.setText("8");
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
                        pin6.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y006");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin6.setText("");
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
                            pin7.setText("1");
                        } else if (totals.size() == 2) {
                            pin7.setText("2");
                        } else if (totals.size() == 3) {
                            pin7.setText("3");
                        } else if (totals.size() == 4) {
                            pin7.setText("4");
                        } else if (totals.size() == 5) {
                            pin7.setText("5");
                        } else if (totals.size() == 6) {
                            pin7.setText("6");
                        } else if (totals.size() == 7) {
                            pin7.setText("7");
                        } else if (totals.size() == 8) {
                            pin7.setText("8");
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
                        pin7.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y007");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin7.setText("");
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
                            pin8.setText("1");
                        } else if (totals.size() == 2) {
                            pin8.setText("2");
                        } else if (totals.size() == 3) {
                            pin8.setText("3");
                        } else if (totals.size() == 4) {
                            pin8.setText("4");
                        } else if (totals.size() == 5) {
                            pin8.setText("5");
                        } else if (totals.size() == 6) {
                            pin8.setText("6");
                        } else if (totals.size() == 7) {
                            pin8.setText("7");
                        } else if (totals.size() == 8) {
                            pin8.setText("8");
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
                        pin8.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y008");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin8.setText("");
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
                            pin9.setText("1");
                        } else if (totals.size() == 2) {
                            pin9.setText("2");
                        } else if (totals.size() == 3) {
                            pin9.setText("3");
                        } else if (totals.size() == 4) {
                            pin9.setText("4");
                        } else if (totals.size() == 5) {
                            pin9.setText("5");
                        } else if (totals.size() == 6) {
                            pin9.setText("6");
                        } else if (totals.size() == 7) {
                            pin9.setText("7");
                        } else if (totals.size() == 8) {
                            pin9.setText("8");
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
                        pin9.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y009");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin9.setText("");
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
                            pin10.setText("1");
                        } else if (totals.size() == 2) {
                            pin10.setText("2");
                        } else if (totals.size() == 3) {
                            pin10.setText("3");
                        } else if (totals.size() == 4) {
                            pin10.setText("4");
                        } else if (totals.size() == 5) {
                            pin10.setText("5");
                        } else if (totals.size() == 6) {
                            pin10.setText("6");
                        } else if (totals.size() == 7) {
                            pin10.setText("7");
                        } else if (totals.size() == 8) {
                            pin10.setText("8");
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
                        pin10.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y010");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin10.setText("");
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
                            pin11.setText("1");
                        } else if (totals.size() == 2) {
                            pin11.setText("2");
                        } else if (totals.size() == 3) {
                            pin11.setText("3");
                        } else if (totals.size() == 4) {
                            pin11.setText("4");
                        } else if (totals.size() == 5) {
                            pin11.setText("5");
                        } else if (totals.size() == 6) {
                            pin11.setText("6");
                        } else if (totals.size() == 7) {
                            pin11.setText("7");
                        } else if (totals.size() == 8) {
                            pin11.setText("8");
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
                        pin11.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y011");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin11.setText("");
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
                            pin12.setText("1");
                        } else if (totals.size() == 2) {
                            pin12.setText("2");
                        } else if (totals.size() == 3) {
                            pin12.setText("3");
                        } else if (totals.size() == 4) {
                            pin12.setText("4");
                        } else if (totals.size() == 5) {
                            pin12.setText("5");
                        } else if (totals.size() == 6) {
                            pin12.setText("6");
                        } else if (totals.size() == 7) {
                            pin12.setText("7");
                        } else if (totals.size() == 8) {
                            pin12.setText("8");
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
                        pin12.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y012");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin12.setText("");
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
                            pin13.setText("1");
                        } else if (totals.size() == 2) {
                            pin13.setText("2");
                        } else if (totals.size() == 3) {
                            pin13.setText("3");
                        } else if (totals.size() == 4) {
                            pin13.setText("4");
                        } else if (totals.size() == 5) {
                            pin13.setText("5");
                        } else if (totals.size() == 6) {
                            pin13.setText("6");
                        } else if (totals.size() == 7) {
                            pin13.setText("7");
                        } else if (totals.size() == 8) {
                            pin13.setText("8");
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
                        pin13.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y013");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin13.setText("");
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
                            pin14.setText("1");
                        } else if (totals.size() == 2) {
                            pin14.setText("2");
                        } else if (totals.size() == 3) {
                            pin14.setText("3");
                        } else if (totals.size() == 4) {
                            pin14.setText("4");
                        } else if (totals.size() == 5) {
                            pin14.setText("5");
                        } else if (totals.size() == 6) {
                            pin14.setText("6");
                        } else if (totals.size() == 7) {
                            pin14.setText("7");
                        } else if (totals.size() == 8) {
                            pin14.setText("8");
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
                        pin14.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y014");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin14.setText("");
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
                            pin15.setText("1");
                        } else if (totals.size() == 2) {
                            pin15.setText("2");
                        } else if (totals.size() == 3) {
                            pin15.setText("3");
                        } else if (totals.size() == 4) {
                            pin15.setText("4");
                        } else if (totals.size() == 5) {
                            pin15.setText("5");
                        } else if (totals.size() == 6) {
                            pin15.setText("6");
                        } else if (totals.size() == 7) {
                            pin15.setText("7");
                        } else if (totals.size() == 8) {
                            pin15.setText("8");
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
                        pin15.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y015");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin15.setText("");
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
                            pin16.setText("1");
                        } else if (totals.size() == 2) {
                            pin16.setText("2");
                        } else if (totals.size() == 3) {
                            pin16.setText("3");
                        } else if (totals.size() == 4) {
                            pin16.setText("4");
                        } else if (totals.size() == 5) {
                            pin16.setText("5");
                        } else if (totals.size() == 6) {
                            pin16.setText("6");
                        } else if (totals.size() == 7) {
                            pin16.setText("7");
                        } else if (totals.size() == 8) {
                            pin16.setText("8");
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
                        pin16.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y016");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin16.setText("");
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
                            pin17.setText("1");
                        } else if (totals.size() == 2) {
                            pin17.setText("2");
                        } else if (totals.size() == 3) {
                            pin17.setText("3");
                        } else if (totals.size() == 4) {
                            pin17.setText("4");
                        } else if (totals.size() == 5) {
                            pin17.setText("5");
                        } else if (totals.size() == 6) {
                            pin17.setText("6");
                        } else if (totals.size() == 7) {
                            pin17.setText("7");
                        } else if (totals.size() == 8) {
                            pin17.setText("8");
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
                        pin17.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y017");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin17.setText("");
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
                            pin18.setText("1");
                        } else if (totals.size() == 2) {
                            pin18.setText("2");
                        } else if (totals.size() == 3) {
                            pin18.setText("3");
                        } else if (totals.size() == 4) {
                            pin18.setText("4");
                        } else if (totals.size() == 5) {
                            pin18.setText("5");
                        } else if (totals.size() == 6) {
                            pin18.setText("6");
                        } else if (totals.size() == 7) {
                            pin18.setText("7");
                        } else if (totals.size() == 8) {
                            pin18.setText("8");
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
                        pin18.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y018");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin18.setText("");
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
                            pin19.setText("1");
                        } else if (totals.size() == 2) {
                            pin19.setText("2");
                        } else if (totals.size() == 3) {
                            pin19.setText("3");
                        } else if (totals.size() == 4) {
                            pin19.setText("4");
                        } else if (totals.size() == 5) {
                            pin19.setText("5");
                        } else if (totals.size() == 6) {
                            pin19.setText("6");
                        } else if (totals.size() == 7) {
                            pin19.setText("7");
                        } else if (totals.size() == 8) {
                            pin19.setText("8");
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
                        pin19.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y019");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin19.setText("");
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
                            pin20.setText("1");
                        } else if (totals.size() == 2) {
                            pin20.setText("2");
                        } else if (totals.size() == 3) {
                            pin20.setText("3");
                        } else if (totals.size() == 4) {
                            pin20.setText("4");
                        } else if (totals.size() == 5) {
                            pin20.setText("5");
                        } else if (totals.size() == 6) {
                            pin20.setText("6");
                        } else if (totals.size() == 7) {
                            pin20.setText("7");
                        } else if (totals.size() == 8) {
                            pin20.setText("8");
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
                        pin20.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y020");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin20.setText("");
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
                            pin21.setText("1");
                        } else if (totals.size() == 2) {
                            pin21.setText("2");
                        } else if (totals.size() == 3) {
                            pin21.setText("3");
                        } else if (totals.size() == 4) {
                            pin21.setText("4");
                        } else if (totals.size() == 5) {
                            pin21.setText("5");
                        } else if (totals.size() == 6) {
                            pin21.setText("6");
                        } else if (totals.size() == 7) {
                            pin21.setText("7");
                        } else if (totals.size() == 8) {
                            pin21.setText("8");
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
                        pin21.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y021");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin21.setText("");
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
                            pin22.setText("1");
                        } else if (totals.size() == 2) {
                            pin22.setText("2");
                        } else if (totals.size() == 3) {
                            pin22.setText("3");
                        } else if (totals.size() == 4) {
                            pin22.setText("4");
                        } else if (totals.size() == 5) {
                            pin22.setText("5");
                        } else if (totals.size() == 6) {
                            pin22.setText("6");
                        } else if (totals.size() == 7) {
                            pin22.setText("7");
                        } else if (totals.size() == 8) {
                            pin22.setText("8");
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
                        pin22.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y022");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin22.setText("");
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
                            pin23.setText("1");
                        } else if (totals.size() == 2) {
                            pin23.setText("2");
                        } else if (totals.size() == 3) {
                            pin23.setText("3");
                        } else if (totals.size() == 4) {
                            pin23.setText("4");
                        } else if (totals.size() == 5) {
                            pin23.setText("5");
                        } else if (totals.size() == 6) {
                            pin23.setText("6");
                        } else if (totals.size() == 7) {
                            pin23.setText("7");
                        } else if (totals.size() == 8) {
                            pin23.setText("8");
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
                        pin23.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y023");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin23.setText("");
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
                            pin24.setText("1");
                        } else if (totals.size() == 2) {
                            pin24.setText("2");
                        } else if (totals.size() == 3) {
                            pin24.setText("3");
                        } else if (totals.size() == 4) {
                            pin24.setText("4");
                        } else if (totals.size() == 5) {
                            pin24.setText("5");
                        } else if (totals.size() == 6) {
                            pin24.setText("6");
                        } else if (totals.size() == 7) {
                            pin24.setText("7");
                        } else if (totals.size() == 8) {
                            pin24.setText("8");
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
                        pin24.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y024");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin24.setText("");
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
                            pin25.setText("1");
                        } else if (totals.size() == 2) {
                            pin25.setText("2");
                        } else if (totals.size() == 3) {
                            pin25.setText("3");
                        } else if (totals.size() == 4) {
                            pin25.setText("4");
                        } else if (totals.size() == 5) {
                            pin25.setText("5");
                        } else if (totals.size() == 6) {
                            pin25.setText("6");
                        } else if (totals.size() == 7) {
                            pin25.setText("7");
                        } else if (totals.size() == 8) {
                            pin25.setText("8");
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
                        pin25.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y025");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin25.setText("");
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
                            pin26.setText("1");
                        } else if (totals.size() == 2) {
                            pin26.setText("2");
                        } else if (totals.size() == 3) {
                            pin26.setText("3");
                        } else if (totals.size() == 4) {
                            pin26.setText("4");
                        } else if (totals.size() == 5) {
                            pin26.setText("5");
                        } else if (totals.size() == 6) {
                            pin26.setText("6");
                        } else if (totals.size() == 7) {
                            pin26.setText("7");
                        } else if (totals.size() == 8) {
                            pin26.setText("8");
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
                        pin26.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y026");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin26.setText("");
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
                            pin27.setText("1");
                        } else if (totals.size() == 2) {
                            pin27.setText("2");
                        } else if (totals.size() == 3) {
                            pin27.setText("3");
                        } else if (totals.size() == 4) {
                            pin27.setText("4");
                        } else if (totals.size() == 5) {
                            pin27.setText("5");
                        } else if (totals.size() == 6) {
                            pin27.setText("6");
                        } else if (totals.size() == 7) {
                            pin27.setText("7");
                        } else if (totals.size() == 8) {
                            pin27.setText("8");
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
                        pin27.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y027");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin27.setText("");
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
                            pin28.setText("1");
                        } else if (totals.size() == 2) {
                            pin28.setText("2");
                        } else if (totals.size() == 3) {
                            pin28.setText("3");
                        } else if (totals.size() == 4) {
                            pin28.setText("4");
                        } else if (totals.size() == 5) {
                            pin28.setText("5");
                        } else if (totals.size() == 6) {
                            pin28.setText("6");
                        } else if (totals.size() == 7) {
                            pin28.setText("7");
                        } else if (totals.size() == 8) {
                            pin28.setText("8");
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
                        pin28.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y028");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin28.setText("");
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
                            pin29.setText("1");
                        } else if (totals.size() == 2) {
                            pin29.setText("2");
                        } else if (totals.size() == 3) {
                            pin29.setText("3");
                        } else if (totals.size() == 4) {
                            pin29.setText("4");
                        } else if (totals.size() == 5) {
                            pin29.setText("5");
                        } else if (totals.size() == 6) {
                            pin29.setText("6");
                        } else if (totals.size() == 7) {
                            pin29.setText("7");
                        } else if (totals.size() == 8) {
                            pin29.setText("8");
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
                        pin29.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y029");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin29.setText("");
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
                            pin30.setText("1");
                        } else if (totals.size() == 2) {
                            pin30.setText("2");
                        } else if (totals.size() == 3) {
                            pin30.setText("3");
                        } else if (totals.size() == 4) {
                            pin30.setText("4");
                        } else if (totals.size() == 5) {
                            pin30.setText("5");
                        } else if (totals.size() == 6) {
                            pin30.setText("6");
                        } else if (totals.size() == 7) {
                            pin30.setText("7");
                        } else if (totals.size() == 8) {
                            pin30.setText("8");
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
                        pin30.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y030");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin30.setText("");
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
                            pin31.setText("1");
                        } else if (totals.size() == 2) {
                            pin31.setText("2");
                        } else if (totals.size() == 3) {
                            pin31.setText("3");
                        } else if (totals.size() == 4) {
                            pin31.setText("4");
                        } else if (totals.size() == 5) {
                            pin31.setText("5");
                        } else if (totals.size() == 6) {
                            pin31.setText("6");
                        } else if (totals.size() == 7) {
                            pin31.setText("7");
                        } else if (totals.size() == 8) {
                            pin31.setText("8");
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
                        pin31.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y031");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin31.setText("");
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
                            pin32.setText("1");
                        } else if (totals.size() == 2) {
                            pin32.setText("2");
                        } else if (totals.size() == 3) {
                            pin32.setText("3");
                        } else if (totals.size() == 4) {
                            pin32.setText("4");
                        } else if (totals.size() == 5) {
                            pin32.setText("5");
                        } else if (totals.size() == 6) {
                            pin32.setText("6");
                        } else if (totals.size() == 7) {
                            pin32.setText("7");
                        } else if (totals.size() == 8) {
                            pin32.setText("8");
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
                        pin32.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y032");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin32.setText("");
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
                            pin33.setText("1");
                        } else if (totals.size() == 2) {
                            pin33.setText("2");
                        } else if (totals.size() == 3) {
                            pin33.setText("3");
                        } else if (totals.size() == 4) {
                            pin33.setText("4");
                        } else if (totals.size() == 5) {
                            pin33.setText("5");
                        } else if (totals.size() == 6) {
                            pin33.setText("6");
                        } else if (totals.size() == 7) {
                            pin33.setText("7");
                        } else if (totals.size() == 8) {
                            pin33.setText("8");
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
                        pin33.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y033");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin33.setText("");
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
                            pin34.setText("1");
                        } else if (totals.size() == 2) {
                            pin34.setText("2");
                        } else if (totals.size() == 3) {
                            pin34.setText("3");
                        } else if (totals.size() == 4) {
                            pin34.setText("4");
                        } else if (totals.size() == 5) {
                            pin34.setText("5");
                        } else if (totals.size() == 6) {
                            pin34.setText("6");
                        } else if (totals.size() == 7) {
                            pin34.setText("7");
                        } else if (totals.size() == 8) {
                            pin34.setText("8");
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
                        pin34.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y034");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin34.setText("");
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
                            pin35.setText("1");
                        } else if (totals.size() == 2) {
                            pin35.setText("2");
                        } else if (totals.size() == 3) {
                            pin35.setText("3");
                        } else if (totals.size() == 4) {
                            pin35.setText("4");
                        } else if (totals.size() == 5) {
                            pin35.setText("5");
                        } else if (totals.size() == 6) {
                            pin35.setText("6");
                        } else if (totals.size() == 7) {
                            pin35.setText("7");
                        } else if (totals.size() == 8) {
                            pin35.setText("8");
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
                        pin35.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y035");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin35.setText("");
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
                            pin36.setText("1");
                        } else if (totals.size() == 2) {
                            pin36.setText("2");
                        } else if (totals.size() == 3) {
                            pin36.setText("3");
                        } else if (totals.size() == 4) {
                            pin36.setText("4");
                        } else if (totals.size() == 5) {
                            pin36.setText("5");
                        } else if (totals.size() == 6) {
                            pin36.setText("6");
                        } else if (totals.size() == 7) {
                            pin36.setText("7");
                        } else if (totals.size() == 8) {
                            pin36.setText("8");
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
                        pin36.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y036");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin36.setText("");
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
                            pin37.setText("1");
                        } else if (totals.size() == 2) {
                            pin37.setText("2");
                        } else if (totals.size() == 3) {
                            pin37.setText("3");
                        } else if (totals.size() == 4) {
                            pin37.setText("4");
                        } else if (totals.size() == 5) {
                            pin37.setText("5");
                        } else if (totals.size() == 6) {
                            pin37.setText("6");
                        } else if (totals.size() == 7) {
                            pin37.setText("7");
                        } else if (totals.size() == 8) {
                            pin37.setText("8");
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
                        pin37.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y037");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin37.setText("");
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
                            pin38.setText("1");
                        } else if (totals.size() == 2) {
                            pin38.setText("2");
                        } else if (totals.size() == 3) {
                            pin38.setText("3");
                        } else if (totals.size() == 4) {
                            pin38.setText("4");
                        } else if (totals.size() == 5) {
                            pin38.setText("5");
                        } else if (totals.size() == 6) {
                            pin38.setText("6");
                        } else if (totals.size() == 7) {
                            pin38.setText("7");
                        } else if (totals.size() == 8) {
                            pin38.setText("8");
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
                        pin38.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y038");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin38.setText("");
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
                            pin39.setText("1");
                        } else if (totals.size() == 2) {
                            pin39.setText("2");
                        } else if (totals.size() == 3) {
                            pin39.setText("3");
                        } else if (totals.size() == 4) {
                            pin39.setText("4");
                        } else if (totals.size() == 5) {
                            pin39.setText("5");
                        } else if (totals.size() == 6) {
                            pin39.setText("6");
                        } else if (totals.size() == 7) {
                            pin39.setText("7");
                        } else if (totals.size() == 8) {
                            pin39.setText("8");
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
                        pin39.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y039");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin39.setText("");
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
                            pin40.setText("1");
                        } else if (totals.size() == 2) {
                            pin40.setText("2");
                        } else if (totals.size() == 3) {
                            pin40.setText("3");
                        } else if (totals.size() == 4) {
                            pin40.setText("4");
                        } else if (totals.size() == 5) {
                            pin40.setText("5");
                        } else if (totals.size() == 6) {
                            pin40.setText("6");
                        } else if (totals.size() == 7) {
                            pin40.setText("7");
                        } else if (totals.size() == 8) {
                            pin40.setText("8");
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
                        pin40.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y040");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin40.setText("");
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
                            pin41.setText("1");
                        } else if (totals.size() == 2) {
                            pin41.setText("2");
                        } else if (totals.size() == 3) {
                            pin41.setText("3");
                        } else if (totals.size() == 4) {
                            pin41.setText("4");
                        } else if (totals.size() == 5) {
                            pin41.setText("5");
                        } else if (totals.size() == 6) {
                            pin41.setText("6");
                        } else if (totals.size() == 7) {
                            pin41.setText("7");
                        } else if (totals.size() == 8) {
                            pin41.setText("8");
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
                        pin41.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y041");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin41.setText("");
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
                            pin42.setText("1");
                        } else if (totals.size() == 2) {
                            pin42.setText("2");
                        } else if (totals.size() == 3) {
                            pin42.setText("3");
                        } else if (totals.size() == 4) {
                            pin42.setText("4");
                        } else if (totals.size() == 5) {
                            pin42.setText("5");
                        } else if (totals.size() == 6) {
                            pin42.setText("6");
                        } else if (totals.size() == 7) {
                            pin42.setText("7");
                        } else if (totals.size() == 8) {
                            pin42.setText("8");
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
                        pin42.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y042");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin42.setText("");
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
                            pin43.setText("1");
                        } else if (totals.size() == 2) {
                            pin43.setText("2");
                        } else if (totals.size() == 3) {
                            pin43.setText("3");
                        } else if (totals.size() == 4) {
                            pin43.setText("4");
                        } else if (totals.size() == 5) {
                            pin43.setText("5");
                        } else if (totals.size() == 6) {
                            pin43.setText("6");
                        } else if (totals.size() == 7) {
                            pin43.setText("7");
                        } else if (totals.size() == 8) {
                            pin43.setText("8");
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
                        pin43.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y043");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin43.setText("");
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
                            pin44.setText("1");
                        } else if (totals.size() == 2) {
                            pin44.setText("2");
                        } else if (totals.size() == 3) {
                            pin44.setText("3");
                        } else if (totals.size() == 4) {
                            pin44.setText("4");
                        } else if (totals.size() == 5) {
                            pin44.setText("5");
                        } else if (totals.size() == 6) {
                            pin44.setText("6");
                        } else if (totals.size() == 7) {
                            pin44.setText("7");
                        } else if (totals.size() == 8) {
                            pin44.setText("8");
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
                        pin44.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y044");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin44.setText("");
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
                            pin45.setText("1");
                        } else if (totals.size() == 2) {
                            pin45.setText("2");
                        } else if (totals.size() == 3) {
                            pin45.setText("3");
                        } else if (totals.size() == 4) {
                            pin45.setText("4");
                        } else if (totals.size() == 5) {
                            pin45.setText("5");
                        } else if (totals.size() == 6) {
                            pin45.setText("6");
                        } else if (totals.size() == 7) {
                            pin45.setText("7");
                        } else if (totals.size() == 8) {
                            pin45.setText("8");
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
                        pin45.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y045");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin45.setText("");
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
                            pin46.setText("1");
                        } else if (totals.size() == 2) {
                            pin46.setText("2");
                        } else if (totals.size() == 3) {
                            pin46.setText("3");
                        } else if (totals.size() == 4) {
                            pin46.setText("4");
                        } else if (totals.size() == 5) {
                            pin46.setText("5");
                        } else if (totals.size() == 6) {
                            pin46.setText("6");
                        } else if (totals.size() == 7) {
                            pin46.setText("7");
                        } else if (totals.size() == 8) {
                            pin46.setText("8");
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
                        pin46.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y046");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin46.setText("");
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
                            pin47.setText("1");
                        } else if (totals.size() == 2) {
                            pin47.setText("2");
                        } else if (totals.size() == 3) {
                            pin47.setText("3");
                        } else if (totals.size() == 4) {
                            pin47.setText("4");
                        } else if (totals.size() == 5) {
                            pin47.setText("5");
                        } else if (totals.size() == 6) {
                            pin47.setText("6");
                        } else if (totals.size() == 7) {
                            pin47.setText("7");
                        } else if (totals.size() == 8) {
                            pin47.setText("8");
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
                        pin47.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y047");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin47.setText("");
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
                            pin48.setText("1");
                        } else if (totals.size() == 2) {
                            pin48.setText("2");
                        } else if (totals.size() == 3) {
                            pin48.setText("3");
                        } else if (totals.size() == 4) {
                            pin48.setText("4");
                        } else if (totals.size() == 5) {
                            pin48.setText("5");
                        } else if (totals.size() == 6) {
                            pin48.setText("6");
                        } else if (totals.size() == 7) {
                            pin48.setText("7");
                        } else if (totals.size() == 8) {
                            pin48.setText("8");
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
                        pin48.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y048");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin48.setText("");
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
                            pin49.setText("1");
                        } else if (totals.size() == 2) {
                            pin49.setText("2");
                        } else if (totals.size() == 3) {
                            pin49.setText("3");
                        } else if (totals.size() == 4) {
                            pin49.setText("4");
                        } else if (totals.size() == 5) {
                            pin49.setText("5");
                        } else if (totals.size() == 6) {
                            pin49.setText("6");
                        } else if (totals.size() == 7) {
                            pin49.setText("7");
                        } else if (totals.size() == 8) {
                            pin49.setText("8");
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
                        pin49.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y049");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin49.setText("");
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
                            pin50.setText("1");
                        } else if (totals.size() == 2) {
                            pin50.setText("2");
                        } else if (totals.size() == 3) {
                            pin50.setText("3");
                        } else if (totals.size() == 4) {
                            pin50.setText("4");
                        } else if (totals.size() == 5) {
                            pin50.setText("5");
                        } else if (totals.size() == 6) {
                            pin50.setText("6");
                        } else if (totals.size() == 7) {
                            pin50.setText("7");
                        } else if (totals.size() == 8) {
                            pin50.setText("8");
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
                        pin50.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y050");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin50.setText("");
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
                            pin51.setText("1");
                        } else if (totals.size() == 2) {
                            pin51.setText("2");
                        } else if (totals.size() == 3) {
                            pin51.setText("3");
                        } else if (totals.size() == 4) {
                            pin51.setText("4");
                        } else if (totals.size() == 5) {
                            pin51.setText("5");
                        } else if (totals.size() == 6) {
                            pin51.setText("6");
                        } else if (totals.size() == 7) {
                            pin51.setText("7");
                        } else if (totals.size() == 8) {
                            pin51.setText("8");
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
                        pin51.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y051");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin51.setText("");
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
                            pin52.setText("1");
                        } else if (totals.size() == 2) {
                            pin52.setText("2");
                        } else if (totals.size() == 3) {
                            pin52.setText("3");
                        } else if (totals.size() == 4) {
                            pin52.setText("4");
                        } else if (totals.size() == 5) {
                            pin52.setText("5");
                        } else if (totals.size() == 6) {
                            pin52.setText("6");
                        } else if (totals.size() == 7) {
                            pin52.setText("7");
                        } else if (totals.size() == 8) {
                            pin52.setText("8");
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
                        pin52.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y052");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin52.setText("");
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
                            pin53.setText("1");
                        } else if (totals.size() == 2) {
                            pin53.setText("2");
                        } else if (totals.size() == 3) {
                            pin53.setText("3");
                        } else if (totals.size() == 4) {
                            pin53.setText("4");
                        } else if (totals.size() == 5) {
                            pin53.setText("5");
                        } else if (totals.size() == 6) {
                            pin53.setText("6");
                        } else if (totals.size() == 7) {
                            pin53.setText("7");
                        } else if (totals.size() == 8) {
                            pin53.setText("8");
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
                        pin53.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y053");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin53.setText("");
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
                            pin54.setText("1");
                        } else if (totals.size() == 2) {
                            pin54.setText("2");
                        } else if (totals.size() == 3) {
                            pin54.setText("3");
                        } else if (totals.size() == 4) {
                            pin54.setText("4");
                        } else if (totals.size() == 5) {
                            pin54.setText("5");
                        } else if (totals.size() == 6) {
                            pin54.setText("6");
                        } else if (totals.size() == 7) {
                            pin54.setText("7");
                        } else if (totals.size() == 8) {
                            pin54.setText("8");
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
                        pin54.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y054");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin54.setText("");
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
                            pin55.setText("1");
                        } else if (totals.size() == 2) {
                            pin55.setText("2");
                        } else if (totals.size() == 3) {
                            pin55.setText("3");
                        } else if (totals.size() == 4) {
                            pin55.setText("4");
                        } else if (totals.size() == 5) {
                            pin55.setText("5");
                        } else if (totals.size() == 6) {
                            pin55.setText("6");
                        } else if (totals.size() == 7) {
                            pin55.setText("7");
                        } else if (totals.size() == 8) {
                            pin55.setText("8");
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
                        pin55.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y055");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin55.setText("");
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
                            pin56.setText("1");
                        } else if (totals.size() == 2) {
                            pin56.setText("2");
                        } else if (totals.size() == 3) {
                            pin56.setText("3");
                        } else if (totals.size() == 4) {
                            pin56.setText("4");
                        } else if (totals.size() == 5) {
                            pin56.setText("5");
                        } else if (totals.size() == 6) {
                            pin56.setText("6");
                        } else if (totals.size() == 7) {
                            pin56.setText("7");
                        } else if (totals.size() == 8) {
                            pin56.setText("8");
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
                        pin56.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y056");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin56.setText("");
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
                            pin57.setText("1");
                        } else if (totals.size() == 2) {
                            pin57.setText("2");
                        } else if (totals.size() == 3) {
                            pin57.setText("3");
                        } else if (totals.size() == 4) {
                            pin57.setText("4");
                        } else if (totals.size() == 5) {
                            pin57.setText("5");
                        } else if (totals.size() == 6) {
                            pin57.setText("6");
                        } else if (totals.size() == 7) {
                            pin57.setText("7");
                        } else if (totals.size() == 8) {
                            pin57.setText("8");
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
                        pin57.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y057");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin57.setText("");
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
                            pin58.setText("1");
                        } else if (totals.size() == 2) {
                            pin58.setText("2");
                        } else if (totals.size() == 3) {
                            pin58.setText("3");
                        } else if (totals.size() == 4) {
                            pin58.setText("4");
                        } else if (totals.size() == 5) {
                            pin58.setText("5");
                        } else if (totals.size() == 6) {
                            pin58.setText("6");
                        } else if (totals.size() == 7) {
                            pin58.setText("7");
                        } else if (totals.size() == 8) {
                            pin58.setText("8");
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
                        pin58.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y058");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin58.setText("");
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
                            pin59.setText("1");
                        } else if (totals.size() == 2) {
                            pin59.setText("2");
                        } else if (totals.size() == 3) {
                            pin59.setText("3");
                        } else if (totals.size() == 4) {
                            pin59.setText("4");
                        } else if (totals.size() == 5) {
                            pin59.setText("5");
                        } else if (totals.size() == 6) {
                            pin59.setText("6");
                        } else if (totals.size() == 7) {
                            pin59.setText("7");
                        } else if (totals.size() == 8) {
                            pin59.setText("8");
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
                        pin59.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y059");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin59.setText("");
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
                            pin60.setText("1");
                        } else if (totals.size() == 2) {
                            pin60.setText("2");
                        } else if (totals.size() == 3) {
                            pin60.setText("3");
                        } else if (totals.size() == 4) {
                            pin60.setText("4");
                        } else if (totals.size() == 5) {
                            pin60.setText("5");
                        } else if (totals.size() == 6) {
                            pin60.setText("6");
                        } else if (totals.size() == 7) {
                            pin60.setText("7");
                        } else if (totals.size() == 8) {
                            pin60.setText("8");
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
                        pin60.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y060");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin60.setText("");
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
                            pin61.setText("1");
                        } else if (totals.size() == 2) {
                            pin61.setText("2");
                        } else if (totals.size() == 3) {
                            pin61.setText("3");
                        } else if (totals.size() == 4) {
                            pin61.setText("4");
                        } else if (totals.size() == 5) {
                            pin61.setText("5");
                        } else if (totals.size() == 6) {
                            pin61.setText("6");
                        } else if (totals.size() == 7) {
                            pin61.setText("7");
                        } else if (totals.size() == 8) {
                            pin61.setText("8");
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
                        pin61.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y061");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin61.setText("");
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
                            pin62.setText("1");
                        } else if (totals.size() == 2) {
                            pin62.setText("2");
                        } else if (totals.size() == 3) {
                            pin62.setText("3");
                        } else if (totals.size() == 4) {
                            pin62.setText("4");
                        } else if (totals.size() == 5) {
                            pin62.setText("5");
                        } else if (totals.size() == 6) {
                            pin62.setText("6");
                        } else if (totals.size() == 7) {
                            pin62.setText("7");
                        } else if (totals.size() == 8) {
                            pin62.setText("8");
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
                        pin62.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y062");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin62.setText("");
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
                            pin63.setText("1");
                        } else if (totals.size() == 2) {
                            pin63.setText("2");
                        } else if (totals.size() == 3) {
                            pin63.setText("3");
                        } else if (totals.size() == 4) {
                            pin63.setText("4");
                        } else if (totals.size() == 5) {
                            pin63.setText("5");
                        } else if (totals.size() == 6) {
                            pin63.setText("6");
                        } else if (totals.size() == 7) {
                            pin63.setText("7");
                        } else if (totals.size() == 8) {
                            pin63.setText("8");
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
                        pin63.setChecked(false);
                        totals.remove(coilCount);
                    }
                } else {
                    totals.remove(0);
                    pinSelection.remove("$Y063");
                    for (Object Selections : pinSelection) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    coilCounter--;
                    counter.setText(String.valueOf(coilCounter));
                    pin63.setText("");
                }
                break;
        }

        if (coilCounter == coilLimit) {
            counter.setTextColor(Color.parseColor("#F30606"));
        } else {
            counter.setTextColor(Color.parseColor("#06F326"));
        }
    }

    /**
     * Method to name a frame (frame)
     *
     * @param view
     */
    public void nameTheFrame(View view) {
        nameOfFrame = findViewById(R.id.nameFrame);

        counter = findViewById(R.id.counter);

        String content = nameOfFrame.getSelectedItem().toString();

        if (commandInformation.isEmpty()) {
            if (frameName1.isEmpty()) {
                frameName1.add("$F" + content);
                frameNameF1.add("$f" + content);
                commandInformation.add(0, "$F" + content);
                if (commandInformation.add(true)) {
                    nameOfFrame.setVisibility(View.INVISIBLE);
                    findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                    commandInformation.remove(true);
                    findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                    findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
                }
            } else if (frameName2.isEmpty()) {
                if (!frameName1.contains("$F" + content)) {
                    frameName2.add("$F" + content);
                    frameNameF2.add("$f" + content);
                    commandInformation.add(0, "$F" + content);
                    if (commandInformation.add(true)) {
                        nameOfFrame.setVisibility(View.INVISIBLE);
                        findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                        commandInformation.remove(true);
                        findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                        findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
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
                        findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                        commandInformation.remove(true);
                        findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                        findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
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
                        findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                        commandInformation.remove(true);
                        findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                        findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
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
                        findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                        commandInformation.remove(true);
                        findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                        findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
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
                        findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                        commandInformation.remove(true);
                        findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                        findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (!commandInformation.isEmpty()) {

            if (firstFrame == false) {
                frameName1.clear();
                frameNameF1.clear();
            }
            if (secondFrame == false) {
                frameName2.clear();
                frameNameF2.clear();
            }
            if (thirdFrame == false) {
                frameName3.clear();
                frameNameF3.clear();
            }
            if (fourthFrame == false) {
                frameName4.clear();
                frameNameF4.clear();
            }
            if (fifthFrame == false) {
                frameName5.clear();
                frameNameF5.clear();
            }
            if (sixthFrame == false) {
                frameName6.clear();
                frameNameF6.clear();
            }

            commandInformation.clear();
            totals.clear();
            pinSelection.clear();
            coilCounter = 0;
            counter.setText(String.valueOf(coilCounter));

            if (frameName1.isEmpty()) {
                frameName1.add("$F" + content);
                frameNameF1.add("$f" + content);
                commandInformation.add(0, "$F" + content);
                if (commandInformation.add(true)) {
                    nameOfFrame.setVisibility(View.INVISIBLE);
                    findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                    commandInformation.remove(true);
                    findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                    findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
                }
            } else if (frameName2.isEmpty()) {
                if (!frameName1.contains("$F" + content)) {
                    frameName2.add("$F" + content);
                    frameNameF2.add("$f" + content);
                    commandInformation.add(0, "$F" + content);
                    if (commandInformation.add(true)) {
                        nameOfFrame.setVisibility(View.INVISIBLE);
                        findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                        commandInformation.remove(true);
                        findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                        findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
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
                        findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                        commandInformation.remove(true);
                        findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                        findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
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
                        findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                        commandInformation.remove(true);
                        findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                        findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
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
                        findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                        commandInformation.remove(true);
                        findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                        findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
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
                        findViewById(R.id.setFrameName).setVisibility(View.INVISIBLE);
                        commandInformation.remove(true);
                        findViewById(R.id.numberOfCoils).setVisibility(View.VISIBLE);
                        findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
                }
            }
        }

        TextView t = findViewById(R.id.frameCreationView);
        String start = "";
        for (Object Selections : commandInformation) {
            start = start + Selections + "\n";
        }
        t.setText(start);
    }

    /**
     * Method to set coils used (frame)
     *
     * @param view
     */
    public void setTheCoils(View view) {
        numOfCoils = findViewById(R.id.numberOfCoils);

        TextView t = findViewById(R.id.frameCreationView);
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
            findViewById(R.id.setCoils).setVisibility(View.INVISIBLE);
            findViewById(R.id.numberOfCoils).setVisibility(View.INVISIBLE);
            commandInformation.remove(true);
            findViewById(R.id.setDelay).setVisibility(View.VISIBLE);
            findViewById(R.id.amountOfDelay).setVisibility(View.VISIBLE);
        }
    }

    /**
     * Method to set a delay (frame)
     *
     * @param view
     */
    public void setTheDelay(View view) {
        amountOfDelay = findViewById(R.id.amountOfDelay);

        String content = amountOfDelay.getSelectedItem().toString();
        if (content.equals("Slow")) {
            commandInformation.add(2, "$D020");
        } else if (content.equals("Medium")) {
            commandInformation.add(2, "$D040");
        } else if (content.equals("Fast")) {
            commandInformation.add(2, "$D060");
        }

        TextView t = findViewById(R.id.frameCreationView);
        String start = "";
        for (Object Selections : commandInformation) {
            start = start + Selections + "\n";
        }
        t.setText(start);

        if (commandInformation.add(true)) {
            findViewById(R.id.setDelay).setVisibility(View.INVISIBLE);
            findViewById(R.id.amountOfDelay).setVisibility(View.INVISIBLE);
            commandInformation.remove(true);
            findViewById(R.id.setOnTime).setVisibility(View.VISIBLE);
            findViewById(R.id.amountOfOnTime).setVisibility(View.VISIBLE);
        }
    }

    /**
     * Method to set an on time (frame)
     *
     * @param view
     */
    public void setTheOnTime(View view) {
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

        amountOfOnTime = findViewById(R.id.amountOfOnTime);

        String coilsSelected = numOfCoils.getSelectedItem().toString();
        int coilLimit = Integer.parseInt(coilsSelected);
        limit = findViewById(R.id.limit);

        numOfCoils = findViewById(R.id.numberOfCoils);
        String coilNumber = numOfCoils.getSelectedItem().toString();

        String content = amountOfOnTime.getSelectedItem().toString();
        if (content.equals("Short")) {
            commandInformation.add(3, "$P010");
        } else if (content.equals("Medium")) {
            commandInformation.add(3, "$P020");
        } else if (content.equals("Long")) {
            commandInformation.add(3, "$P030");
        }

        TextView t = findViewById(R.id.frameCreationView);
        String start = "";
        for (Object Selections : commandInformation) {
            start = start + Selections + "\n";
        }
        t.setText(start);
        if (commandInformation.add(true)) {
            findViewById(R.id.setOnTime).setVisibility(View.INVISIBLE);
            amountOfOnTime.setVisibility(View.INVISIBLE);
            commandInformation.remove(true);
            findViewById(R.id.counter).setVisibility(View.VISIBLE);
            findViewById(R.id.indexFinger).setVisibility(View.VISIBLE);
            findViewById(R.id.middleFinger).setVisibility(View.VISIBLE);
            findViewById(R.id.ringFinger).setVisibility(View.VISIBLE);
            findViewById(R.id.frameCreationView).setVisibility(View.VISIBLE);
            findViewById(R.id.saveFrame).setVisibility(View.VISIBLE);
            findViewById(R.id.coilsCounter).setVisibility(View.VISIBLE);
            findViewById(R.id.limit).setVisibility(View.VISIBLE);

            if (coilLimit == 1) {
                limit.setText("/ 1");
            } else if (coilLimit == 2) {
                limit.setText("/ 2");
            } else if (coilLimit == 3) {
                limit.setText("/ 3");
            } else if (coilLimit == 4) {
                limit.setText("/ 4");
            } else if (coilLimit == 5) {
                limit.setText("/ 5");
            } else if (coilLimit == 6) {
                limit.setText("/ 6");
            } else if (coilLimit == 7) {
                limit.setText("/ 7");
            } else if (coilLimit == 8) {
                limit.setText("/ 8");
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

            String frame1;
            String frame2;
            String frame3;
            String frame4;
            String frame5;
            String frame6;

            if (!frameNameF1.isEmpty()) {
                frame1 = frameFinished1.toString();
                if (frame1.contains("$Y000")) {
                    pin0.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y001")) {
                    pin1.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y002")) {
                    pin2.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y003")) {
                    pin3.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y004")) {
                    pin4.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y005")) {
                    pin5.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y006")) {
                    pin6.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y007")) {
                    pin7.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y008")) {
                    pin8.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y009")) {
                    pin9.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y010")) {
                    pin10.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y011")) {
                    pin11.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y012")) {
                    pin12.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y013")) {
                    pin13.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y014")) {
                    pin14.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y015")) {
                    pin15.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y016")) {
                    pin16.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y017")) {
                    pin17.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y018")) {
                    pin18.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y019")) {
                    pin19.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y020")) {
                    pin20.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y021")) {
                    pin21.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y022")) {
                    pin22.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y023")) {
                    pin23.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y024")) {
                    pin24.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y025")) {
                    pin25.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y026")) {
                    pin26.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y027")) {
                    pin27.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y028")) {
                    pin28.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y029")) {
                    pin29.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y030")) {
                    pin30.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y031")) {
                    pin31.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y032")) {
                    pin32.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y033")) {
                    pin33.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y034")) {
                    pin34.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y035")) {
                    pin35.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y036")) {
                    pin36.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y037")) {
                    pin37.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y038")) {
                    pin38.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y039")) {
                    pin39.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y040")) {
                    pin40.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y041")) {
                    pin41.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y042")) {
                    pin42.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y043")) {
                    pin43.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y044")) {
                    pin44.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y045")) {
                    pin45.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y046")) {
                    pin46.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y047")) {
                    pin47.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y048")) {
                    pin48.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y049")) {
                    pin49.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y050")) {
                    pin50.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y051")) {
                    pin51.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y052")) {
                    pin52.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y053")) {
                    pin53.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y054")) {
                    pin54.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y055")) {
                    pin55.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y056")) {
                    pin56.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y057")) {
                    pin57.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y058")) {
                    pin58.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y059")) {
                    pin59.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y060")) {
                    pin60.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y061")) {
                    pin61.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y062")) {
                    pin62.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y063")) {
                    pin63.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
            }
            if (!frameNameF2.isEmpty()) {
                frame2 = frameFinished2.toString();
                if (frame2.contains("$Y000")) {
                    pin0.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y001")) {
                    pin1.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y002")) {
                    pin2.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y003")) {
                    pin3.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y004")) {
                    pin4.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y005")) {
                    pin5.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y006")) {
                    pin6.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y007")) {
                    pin7.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y008")) {
                    pin8.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y009")) {
                    pin9.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y010")) {
                    pin10.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y011")) {
                    pin11.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y012")) {
                    pin12.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y013")) {
                    pin13.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y014")) {
                    pin14.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y015")) {
                    pin15.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y016")) {
                    pin16.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y017")) {
                    pin17.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y018")) {
                    pin18.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y019")) {
                    pin19.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y020")) {
                    pin20.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y021")) {
                    pin21.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y022")) {
                    pin22.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y023")) {
                    pin23.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y024")) {
                    pin24.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y025")) {
                    pin25.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y026")) {
                    pin26.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y027")) {
                    pin27.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y028")) {
                    pin28.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y029")) {
                    pin29.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y030")) {
                    pin30.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y031")) {
                    pin31.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y032")) {
                    pin32.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y033")) {
                    pin33.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y034")) {
                    pin34.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y035")) {
                    pin35.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y036")) {
                    pin36.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y037")) {
                    pin37.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y038")) {
                    pin38.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y039")) {
                    pin39.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y040")) {
                    pin40.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y041")) {
                    pin41.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y042")) {
                    pin42.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y043")) {
                    pin43.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y044")) {
                    pin44.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y045")) {
                    pin45.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y046")) {
                    pin46.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y047")) {
                    pin47.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y048")) {
                    pin48.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y049")) {
                    pin49.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y050")) {
                    pin50.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y051")) {
                    pin51.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y052")) {
                    pin52.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y053")) {
                    pin53.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y054")) {
                    pin54.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y055")) {
                    pin55.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y056")) {
                    pin56.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y057")) {
                    pin57.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y058")) {
                    pin58.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y059")) {
                    pin59.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y060")) {
                    pin60.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y061")) {
                    pin61.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y062")) {
                    pin62.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y063")) {
                    pin63.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
            }
            if (!frameNameF3.isEmpty()) {
                frame3 = frameFinished3.toString();
                if (frame3.contains("$Y000")) {
                    pin0.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y001")) {
                    pin1.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y002")) {
                    pin2.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y003")) {
                    pin3.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y004")) {
                    pin4.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y005")) {
                    pin5.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y006")) {
                    pin6.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y007")) {
                    pin7.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y008")) {
                    pin8.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y009")) {
                    pin9.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y010")) {
                    pin10.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y011")) {
                    pin11.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y012")) {
                    pin12.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y013")) {
                    pin13.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y014")) {
                    pin14.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y015")) {
                    pin15.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y016")) {
                    pin16.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y017")) {
                    pin17.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y018")) {
                    pin18.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y019")) {
                    pin19.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y020")) {
                    pin20.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y021")) {
                    pin21.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y022")) {
                    pin22.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y023")) {
                    pin23.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y024")) {
                    pin24.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y025")) {
                    pin25.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y026")) {
                    pin26.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y027")) {
                    pin27.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y028")) {
                    pin28.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y029")) {
                    pin29.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y030")) {
                    pin30.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y031")) {
                    pin31.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y032")) {
                    pin32.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y033")) {
                    pin33.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y034")) {
                    pin34.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y035")) {
                    pin35.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y036")) {
                    pin36.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y037")) {
                    pin37.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y038")) {
                    pin38.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y039")) {
                    pin39.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y040")) {
                    pin40.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y041")) {
                    pin41.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y042")) {
                    pin42.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y043")) {
                    pin43.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y044")) {
                    pin44.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y045")) {
                    pin45.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y046")) {
                    pin46.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y047")) {
                    pin47.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y048")) {
                    pin48.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y049")) {
                    pin49.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y050")) {
                    pin50.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y051")) {
                    pin51.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y052")) {
                    pin52.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y053")) {
                    pin53.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y054")) {
                    pin54.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y055")) {
                    pin55.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y056")) {
                    pin56.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y057")) {
                    pin57.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y058")) {
                    pin58.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y059")) {
                    pin59.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y060")) {
                    pin60.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y061")) {
                    pin61.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y062")) {
                    pin62.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y063")) {
                    pin63.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
            }
            if (!frameNameF4.isEmpty()) {
                frame4 = frameFinished4.toString();
                if (frame4.contains("$Y000")) {
                    pin0.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y001")) {
                    pin1.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y002")) {
                    pin2.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y003")) {
                    pin3.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y004")) {
                    pin4.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y005")) {
                    pin5.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y006")) {
                    pin6.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y007")) {
                    pin7.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y008")) {
                    pin8.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y009")) {
                    pin9.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y010")) {
                    pin10.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y011")) {
                    pin11.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y012")) {
                    pin12.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y013")) {
                    pin13.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y014")) {
                    pin14.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y015")) {
                    pin15.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y016")) {
                    pin16.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y017")) {
                    pin17.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y018")) {
                    pin18.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y019")) {
                    pin19.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y020")) {
                    pin20.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y021")) {
                    pin21.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y022")) {
                    pin22.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y023")) {
                    pin23.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y024")) {
                    pin24.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y025")) {
                    pin25.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y026")) {
                    pin26.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y027")) {
                    pin27.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y028")) {
                    pin28.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y029")) {
                    pin29.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y030")) {
                    pin30.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y031")) {
                    pin31.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y032")) {
                    pin32.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y033")) {
                    pin33.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y034")) {
                    pin34.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y035")) {
                    pin35.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y036")) {
                    pin36.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y037")) {
                    pin37.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y038")) {
                    pin38.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y039")) {
                    pin39.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y040")) {
                    pin40.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y041")) {
                    pin41.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y042")) {
                    pin42.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y043")) {
                    pin43.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y044")) {
                    pin44.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y045")) {
                    pin45.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y046")) {
                    pin46.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y047")) {
                    pin47.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y048")) {
                    pin48.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y049")) {
                    pin49.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y050")) {
                    pin50.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y051")) {
                    pin51.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y052")) {
                    pin52.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y053")) {
                    pin53.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y054")) {
                    pin54.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y055")) {
                    pin55.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y056")) {
                    pin56.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y057")) {
                    pin57.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y058")) {
                    pin58.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y059")) {
                    pin59.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y060")) {
                    pin60.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y061")) {
                    pin61.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y062")) {
                    pin62.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y063")) {
                    pin63.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
            }
            if (!frameNameF5.isEmpty()) {
                frame5 = frameFinished5.toString();
                if (frame5.contains("$Y000")) {
                    pin0.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y001")) {
                    pin1.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y002")) {
                    pin2.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y003")) {
                    pin3.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y004")) {
                    pin4.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y005")) {
                    pin5.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y006")) {
                    pin6.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y007")) {
                    pin7.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y008")) {
                    pin8.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y009")) {
                    pin9.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y010")) {
                    pin10.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y011")) {
                    pin11.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y012")) {
                    pin12.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y013")) {
                    pin13.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y014")) {
                    pin14.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y015")) {
                    pin15.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y016")) {
                    pin16.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y017")) {
                    pin17.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y018")) {
                    pin18.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y019")) {
                    pin19.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y020")) {
                    pin20.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y021")) {
                    pin21.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y022")) {
                    pin22.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y023")) {
                    pin23.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y024")) {
                    pin24.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y025")) {
                    pin25.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y026")) {
                    pin26.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y027")) {
                    pin27.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y028")) {
                    pin28.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y029")) {
                    pin29.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y030")) {
                    pin30.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y031")) {
                    pin31.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y032")) {
                    pin32.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y033")) {
                    pin33.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y034")) {
                    pin34.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y035")) {
                    pin35.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y036")) {
                    pin36.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y037")) {
                    pin37.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y038")) {
                    pin38.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y039")) {
                    pin39.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y040")) {
                    pin40.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y041")) {
                    pin41.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y042")) {
                    pin42.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y043")) {
                    pin43.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y044")) {
                    pin44.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y045")) {
                    pin45.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y046")) {
                    pin46.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y047")) {
                    pin47.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y048")) {
                    pin48.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y049")) {
                    pin49.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y050")) {
                    pin50.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y051")) {
                    pin51.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y052")) {
                    pin52.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y053")) {
                    pin53.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y054")) {
                    pin54.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y055")) {
                    pin55.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y056")) {
                    pin56.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y057")) {
                    pin57.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y058")) {
                    pin58.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y059")) {
                    pin59.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y060")) {
                    pin60.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y061")) {
                    pin61.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y062")) {
                    pin62.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y063")) {
                    pin63.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
            }
            if (!frameNameF6.isEmpty()) {
                frame6 = frameFinished6.toString();
                if (frame6.contains("$Y000")) {
                    pin0.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y001")) {
                    pin1.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y002")) {
                    pin2.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y003")) {
                    pin3.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y004")) {
                    pin4.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y005")) {
                    pin5.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y006")) {
                    pin6.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y007")) {
                    pin7.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y008")) {
                    pin8.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y009")) {
                    pin9.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y010")) {
                    pin10.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y011")) {
                    pin11.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y012")) {
                    pin12.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y013")) {
                    pin13.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y014")) {
                    pin14.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y015")) {
                    pin15.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y016")) {
                    pin16.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y017")) {
                    pin17.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y018")) {
                    pin18.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y019")) {
                    pin19.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y020")) {
                    pin20.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y021")) {
                    pin21.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y022")) {
                    pin22.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y023")) {
                    pin23.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y024")) {
                    pin24.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y025")) {
                    pin25.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y026")) {
                    pin26.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y027")) {
                    pin27.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y028")) {
                    pin28.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y029")) {
                    pin29.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y030")) {
                    pin30.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y031")) {
                    pin31.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y032")) {
                    pin32.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y033")) {
                    pin33.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y034")) {
                    pin34.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y035")) {
                    pin35.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y036")) {
                    pin36.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y037")) {
                    pin37.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y038")) {
                    pin38.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y039")) {
                    pin39.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y040")) {
                    pin40.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y041")) {
                    pin41.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y042")) {
                    pin42.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y043")) {
                    pin43.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y044")) {
                    pin44.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y045")) {
                    pin45.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y046")) {
                    pin46.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y047")) {
                    pin47.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y048")) {
                    pin48.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y049")) {
                    pin49.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y050")) {
                    pin50.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y051")) {
                    pin51.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y052")) {
                    pin52.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y053")) {
                    pin53.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y054")) {
                    pin54.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y055")) {
                    pin55.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y056")) {
                    pin56.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y057")) {
                    pin57.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y058")) {
                    pin58.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y059")) {
                    pin59.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y060")) {
                    pin60.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y061")) {
                    pin61.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y062")) {
                    pin62.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y063")) {
                    pin63.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
            }
        }
    }

    /**
     * Method to save data from a frame (frame)
     */
    private void saveData() {
        counter = findViewById(R.id.counter);

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

        String commandInfoReplace;
        String pinSelectReplace;

        String contents = numOfCoils.getSelectedItem().toString();
        int coilLimit = Integer.parseInt(contents);
        if (totals.size() == coilLimit) {
            if (frameFinished1.isEmpty()) {
                pinSelection.add("$T001");
                commandInfoReplace = commandInformation.toString().replaceAll("(^\\[|\\])", "");
                pinSelectReplace = pinSelection.toString().replaceAll("(^\\[|\\])", "\n");
                frameFinished1.add(commandInfoReplace + pinSelectReplace);
                firstFrame = true;
                Toast.makeText(getApplicationContext(), "Frame Saved1" + frameFinished1, Toast.LENGTH_SHORT).show();
            } else if (frameFinished2.isEmpty()) {
                pinSelection.add("$T001");
                commandInfoReplace = commandInformation.toString().replaceAll("(^\\[|\\])", "");
                pinSelectReplace = pinSelection.toString().replaceAll("(^\\[|\\])", "\n");
                frameFinished2.add(commandInfoReplace + pinSelectReplace);
                secondFrame = true;
                Toast.makeText(getApplicationContext(), "Frame Saved2" + frameFinished2, Toast.LENGTH_SHORT).show();
            } else if (frameFinished3.isEmpty()) {
                pinSelection.add("$T001");
                commandInfoReplace = commandInformation.toString().replaceAll("(^\\[|\\])", "");
                pinSelectReplace = pinSelection.toString().replaceAll("(^\\[|\\])", "\n");
                frameFinished3.add(commandInfoReplace + pinSelectReplace);
                thirdFrame = true;
                Toast.makeText(getApplicationContext(), "Frame Saved3" + frameFinished3, Toast.LENGTH_SHORT).show();
            } else if (frameFinished4.isEmpty()) {
                pinSelection.add("$T001");
                commandInfoReplace = commandInformation.toString().replaceAll("(^\\[|\\])", "");
                pinSelectReplace = pinSelection.toString().replaceAll("(^\\[|\\])", "\n");
                frameFinished4.add(commandInfoReplace + pinSelectReplace);
                fourthFrame = true;
                Toast.makeText(getApplicationContext(), "Frame Saved4" + frameFinished4, Toast.LENGTH_SHORT).show();
            } else if (frameFinished5.isEmpty()) {
                pinSelection.add("$T001");
                commandInfoReplace = commandInformation.toString().replaceAll("(^\\[|\\])", "");
                pinSelectReplace = pinSelection.toString().replaceAll("(^\\[|\\])", "\n");
                frameFinished5.add(commandInfoReplace + pinSelectReplace);
                fifthFrame = true;
                Toast.makeText(getApplicationContext(), "Frame Saved5" + frameFinished5, Toast.LENGTH_SHORT).show();
            } else if (frameFinished6.isEmpty()) {
                pinSelection.add("$T001");
                commandInfoReplace = commandInformation.toString().replaceAll("(^\\[|\\])", "");
                pinSelectReplace = pinSelection.toString().replaceAll("(^\\[|\\])", "\n");
                frameFinished6.add(commandInfoReplace + pinSelectReplace);
                sixthFrame = true;
                Toast.makeText(getApplicationContext(), "Frame Saved6" + frameFinished6, Toast.LENGTH_SHORT).show();
            }

            pinSelection.clear();
            commandInformation.clear();
            coilsUsed.clear();
            totals.clear();

            coilCounter = 0;
            counter.setText(String.valueOf(coilCounter));

            counter.setVisibility(View.INVISIBLE);
            findViewById(R.id.coilsCounter).setVisibility(View.INVISIBLE);
            findViewById(R.id.saveFrame).setVisibility(View.INVISIBLE);
            findViewById(R.id.nameFrame).setVisibility(View.VISIBLE);
            findViewById(R.id.setFrameName).setVisibility(View.VISIBLE);
            findViewById(R.id.indexFinger).setVisibility(View.INVISIBLE);
            findViewById(R.id.middleFinger).setVisibility(View.INVISIBLE);
            findViewById(R.id.ringFinger).setVisibility(View.INVISIBLE);
            findViewById(R.id.frameCreationView).setVisibility(View.INVISIBLE);
            findViewById(R.id.limit).setVisibility(View.INVISIBLE);

            pin0.setChecked(false);
            pin0.setBackgroundColor(Color.TRANSPARENT);
            pin0.setVisibility(View.INVISIBLE);
            pin0.setText("");
            pin1.setChecked(false);
            pin1.setBackgroundColor(Color.TRANSPARENT);
            pin1.setVisibility(View.INVISIBLE);
            pin1.setText("");
            pin2.setChecked(false);
            pin2.setBackgroundColor(Color.TRANSPARENT);
            pin2.setVisibility(View.INVISIBLE);
            pin2.setText("");
            pin3.setChecked(false);
            pin3.setBackgroundColor(Color.TRANSPARENT);
            pin3.setVisibility(View.INVISIBLE);
            pin3.setText("");
            pin4.setChecked(false);
            pin4.setBackgroundColor(Color.TRANSPARENT);
            pin4.setVisibility(View.INVISIBLE);
            pin4.setText("");
            pin5.setChecked(false);
            pin5.setBackgroundColor(Color.TRANSPARENT);
            pin5.setVisibility(View.INVISIBLE);
            pin5.setText("");
            pin6.setChecked(false);
            pin6.setBackgroundColor(Color.TRANSPARENT);
            pin6.setVisibility(View.INVISIBLE);
            pin6.setText("");
            pin7.setChecked(false);
            pin7.setBackgroundColor(Color.TRANSPARENT);
            pin7.setVisibility(View.INVISIBLE);
            pin7.setText("");
            pin8.setChecked(false);
            pin8.setBackgroundColor(Color.TRANSPARENT);
            pin8.setVisibility(View.INVISIBLE);
            pin8.setText("");
            pin9.setChecked(false);
            pin9.setBackgroundColor(Color.TRANSPARENT);
            pin9.setVisibility(View.INVISIBLE);
            pin9.setText("");
            pin10.setChecked(false);
            pin10.setBackgroundColor(Color.TRANSPARENT);
            pin10.setVisibility(View.INVISIBLE);
            pin10.setText("");
            pin11.setChecked(false);
            pin11.setBackgroundColor(Color.TRANSPARENT);
            pin11.setVisibility(View.INVISIBLE);
            pin11.setText("");
            pin12.setChecked(false);
            pin12.setBackgroundColor(Color.TRANSPARENT);
            pin12.setVisibility(View.INVISIBLE);
            pin12.setText("");
            pin13.setChecked(false);
            pin13.setBackgroundColor(Color.TRANSPARENT);
            pin13.setVisibility(View.INVISIBLE);
            pin13.setText("");
            pin14.setChecked(false);
            pin14.setBackgroundColor(Color.TRANSPARENT);
            pin14.setVisibility(View.INVISIBLE);
            pin14.setText("");
            pin15.setChecked(false);
            pin15.setBackgroundColor(Color.TRANSPARENT);
            pin15.setVisibility(View.INVISIBLE);
            pin15.setText("");
            pin16.setChecked(false);
            pin16.setBackgroundColor(Color.TRANSPARENT);
            pin16.setVisibility(View.INVISIBLE);
            pin16.setText("");
            pin17.setChecked(false);
            pin17.setBackgroundColor(Color.TRANSPARENT);
            pin17.setVisibility(View.INVISIBLE);
            pin17.setText("");
            pin18.setChecked(false);
            pin18.setBackgroundColor(Color.TRANSPARENT);
            pin18.setVisibility(View.INVISIBLE);
            pin18.setText("");
            pin19.setChecked(false);
            pin19.setBackgroundColor(Color.TRANSPARENT);
            pin19.setVisibility(View.INVISIBLE);
            pin19.setText("");
            pin20.setChecked(false);
            pin20.setBackgroundColor(Color.TRANSPARENT);
            pin20.setVisibility(View.INVISIBLE);
            pin20.setText("");
            pin21.setChecked(false);
            pin21.setBackgroundColor(Color.TRANSPARENT);
            pin21.setVisibility(View.INVISIBLE);
            pin21.setText("");
            pin22.setChecked(false);
            pin22.setBackgroundColor(Color.TRANSPARENT);
            pin22.setVisibility(View.INVISIBLE);
            pin22.setText("");
            pin23.setChecked(false);
            pin23.setBackgroundColor(Color.TRANSPARENT);
            pin23.setVisibility(View.INVISIBLE);
            pin23.setText("");
            pin24.setChecked(false);
            pin24.setBackgroundColor(Color.TRANSPARENT);
            pin24.setVisibility(View.INVISIBLE);
            pin24.setText("");
            pin25.setChecked(false);
            pin25.setBackgroundColor(Color.TRANSPARENT);
            pin25.setVisibility(View.INVISIBLE);
            pin25.setText("");
            pin26.setChecked(false);
            pin26.setBackgroundColor(Color.TRANSPARENT);
            pin26.setVisibility(View.INVISIBLE);
            pin26.setText("");
            pin27.setChecked(false);
            pin27.setBackgroundColor(Color.TRANSPARENT);
            pin27.setVisibility(View.INVISIBLE);
            pin27.setText("");
            pin28.setChecked(false);
            pin28.setBackgroundColor(Color.TRANSPARENT);
            pin28.setVisibility(View.INVISIBLE);
            pin28.setText("");
            pin29.setChecked(false);
            pin29.setBackgroundColor(Color.TRANSPARENT);
            pin29.setVisibility(View.INVISIBLE);
            pin29.setText("");
            pin30.setChecked(false);
            pin30.setBackgroundColor(Color.TRANSPARENT);
            pin30.setVisibility(View.INVISIBLE);
            pin30.setText("");
            pin31.setChecked(false);
            pin31.setBackgroundColor(Color.TRANSPARENT);
            pin31.setVisibility(View.INVISIBLE);
            pin31.setText("");
            pin32.setChecked(false);
            pin32.setBackgroundColor(Color.TRANSPARENT);
            pin32.setVisibility(View.INVISIBLE);
            pin32.setText("");
            pin33.setChecked(false);
            pin33.setBackgroundColor(Color.TRANSPARENT);
            pin33.setVisibility(View.INVISIBLE);
            pin33.setText("");
            pin34.setChecked(false);
            pin34.setBackgroundColor(Color.TRANSPARENT);
            pin34.setVisibility(View.INVISIBLE);
            pin34.setText("");
            pin35.setChecked(false);
            pin35.setBackgroundColor(Color.TRANSPARENT);
            pin35.setVisibility(View.INVISIBLE);
            pin35.setText("");
            pin36.setChecked(false);
            pin36.setBackgroundColor(Color.TRANSPARENT);
            pin36.setVisibility(View.INVISIBLE);
            pin36.setText("");
            pin37.setChecked(false);
            pin37.setBackgroundColor(Color.TRANSPARENT);
            pin37.setVisibility(View.INVISIBLE);
            pin37.setText("");
            pin38.setChecked(false);
            pin38.setBackgroundColor(Color.TRANSPARENT);
            pin38.setVisibility(View.INVISIBLE);
            pin38.setText("");
            pin39.setChecked(false);
            pin39.setBackgroundColor(Color.TRANSPARENT);
            pin39.setVisibility(View.INVISIBLE);
            pin39.setText("");
            pin40.setChecked(false);
            pin40.setBackgroundColor(Color.TRANSPARENT);
            pin40.setVisibility(View.INVISIBLE);
            pin40.setText("");
            pin41.setChecked(false);
            pin41.setBackgroundColor(Color.TRANSPARENT);
            pin41.setVisibility(View.INVISIBLE);
            pin41.setText("");
            pin42.setChecked(false);
            pin42.setBackgroundColor(Color.TRANSPARENT);
            pin42.setVisibility(View.INVISIBLE);
            pin42.setText("");
            pin43.setChecked(false);
            pin43.setBackgroundColor(Color.TRANSPARENT);
            pin43.setVisibility(View.INVISIBLE);
            pin43.setText("");
            pin44.setChecked(false);
            pin44.setBackgroundColor(Color.TRANSPARENT);
            pin44.setVisibility(View.INVISIBLE);
            pin44.setText("");
            pin45.setChecked(false);
            pin45.setBackgroundColor(Color.TRANSPARENT);
            pin45.setVisibility(View.INVISIBLE);
            pin45.setText("");
            pin46.setChecked(false);
            pin46.setBackgroundColor(Color.TRANSPARENT);
            pin46.setVisibility(View.INVISIBLE);
            pin46.setText("");
            pin47.setChecked(false);
            pin47.setBackgroundColor(Color.TRANSPARENT);
            pin47.setVisibility(View.INVISIBLE);
            pin47.setText("");
            pin48.setChecked(false);
            pin48.setBackgroundColor(Color.TRANSPARENT);
            pin48.setVisibility(View.INVISIBLE);
            pin48.setText("");
            pin49.setChecked(false);
            pin49.setBackgroundColor(Color.TRANSPARENT);
            pin49.setVisibility(View.INVISIBLE);
            pin49.setText("");
            pin50.setChecked(false);
            pin50.setBackgroundColor(Color.TRANSPARENT);
            pin50.setVisibility(View.INVISIBLE);
            pin50.setText("");
            pin51.setChecked(false);
            pin51.setBackgroundColor(Color.TRANSPARENT);
            pin51.setVisibility(View.INVISIBLE);
            pin51.setText("");
            pin52.setChecked(false);
            pin52.setBackgroundColor(Color.TRANSPARENT);
            pin52.setVisibility(View.INVISIBLE);
            pin52.setText("");
            pin53.setChecked(false);
            pin53.setBackgroundColor(Color.TRANSPARENT);
            pin53.setVisibility(View.INVISIBLE);
            pin53.setText("");
            pin54.setChecked(false);
            pin54.setBackgroundColor(Color.TRANSPARENT);
            pin54.setVisibility(View.INVISIBLE);
            pin54.setText("");
            pin55.setChecked(false);
            pin55.setBackgroundColor(Color.TRANSPARENT);
            pin55.setVisibility(View.INVISIBLE);
            pin55.setText("");
            pin56.setChecked(false);
            pin56.setBackgroundColor(Color.TRANSPARENT);
            pin56.setVisibility(View.INVISIBLE);
            pin56.setText("");
            pin57.setChecked(false);
            pin57.setBackgroundColor(Color.TRANSPARENT);
            pin57.setVisibility(View.INVISIBLE);
            pin57.setText("");
            pin58.setChecked(false);
            pin58.setBackgroundColor(Color.TRANSPARENT);
            pin58.setVisibility(View.INVISIBLE);
            pin58.setText("");
            pin59.setChecked(false);
            pin59.setBackgroundColor(Color.TRANSPARENT);
            pin59.setVisibility(View.INVISIBLE);
            pin59.setText("");
            pin60.setChecked(false);
            pin60.setBackgroundColor(Color.TRANSPARENT);
            pin60.setVisibility(View.INVISIBLE);
            pin60.setText("");
            pin61.setChecked(false);
            pin61.setBackgroundColor(Color.TRANSPARENT);
            pin61.setVisibility(View.INVISIBLE);
            pin61.setText("");
            pin62.setChecked(false);
            pin62.setBackgroundColor(Color.TRANSPARENT);
            pin62.setVisibility(View.INVISIBLE);
            pin62.setText("");
            pin63.setChecked(false);
            pin63.setBackgroundColor(Color.TRANSPARENT);
            pin63.setVisibility(View.INVISIBLE);
            pin63.setText("");

            String frame1;
            String frame2;
            String frame3;
            String frame4;
            String frame5;
            String frame6;

            if (!frameNameF1.isEmpty()) {
                frame1 = frameFinished1.toString();
                if (frame1.contains("$Y000")) {
                    pin0.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y001")) {
                    pin1.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y002")) {
                    pin2.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y003")) {
                    pin3.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y004")) {
                    pin4.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y005")) {
                    pin5.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y006")) {
                    pin6.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y007")) {
                    pin7.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y008")) {
                    pin8.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y009")) {
                    pin9.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y010")) {
                    pin10.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y011")) {
                    pin11.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y012")) {
                    pin12.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y013")) {
                    pin13.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y014")) {
                    pin14.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y015")) {
                    pin15.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y016")) {
                    pin16.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y017")) {
                    pin17.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y018")) {
                    pin18.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y019")) {
                    pin19.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y020")) {
                    pin20.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y021")) {
                    pin21.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y022")) {
                    pin22.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y023")) {
                    pin23.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y024")) {
                    pin24.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y025")) {
                    pin25.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y026")) {
                    pin26.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y027")) {
                    pin27.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y028")) {
                    pin28.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y029")) {
                    pin29.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y030")) {
                    pin30.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y031")) {
                    pin31.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y032")) {
                    pin32.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y033")) {
                    pin33.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y034")) {
                    pin34.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y035")) {
                    pin35.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y036")) {
                    pin36.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y037")) {
                    pin37.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y038")) {
                    pin38.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y039")) {
                    pin39.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y040")) {
                    pin40.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y041")) {
                    pin41.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y042")) {
                    pin42.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y043")) {
                    pin43.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y044")) {
                    pin44.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y045")) {
                    pin45.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y046")) {
                    pin46.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y047")) {
                    pin47.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y048")) {
                    pin48.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y049")) {
                    pin49.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y050")) {
                    pin50.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y051")) {
                    pin51.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y052")) {
                    pin52.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y053")) {
                    pin53.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y054")) {
                    pin54.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y055")) {
                    pin55.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y056")) {
                    pin56.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y057")) {
                    pin57.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y058")) {
                    pin58.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y059")) {
                    pin59.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y060")) {
                    pin60.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y061")) {
                    pin61.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y062")) {
                    pin62.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame1.contains("$Y063")) {
                    pin63.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
            }
            if (!frameNameF2.isEmpty()) {
                frame2 = frameFinished2.toString();
                if (frame2.contains("$Y000")) {
                    pin0.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y001")) {
                    pin1.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y002")) {
                    pin2.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y003")) {
                    pin3.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y004")) {
                    pin4.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y005")) {
                    pin5.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y006")) {
                    pin6.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y007")) {
                    pin7.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y008")) {
                    pin8.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y009")) {
                    pin9.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y010")) {
                    pin10.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y011")) {
                    pin11.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y012")) {
                    pin12.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y013")) {
                    pin13.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y014")) {
                    pin14.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y015")) {
                    pin15.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y016")) {
                    pin16.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y017")) {
                    pin17.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y018")) {
                    pin18.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y019")) {
                    pin19.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y020")) {
                    pin20.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y021")) {
                    pin21.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y022")) {
                    pin22.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y023")) {
                    pin23.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y024")) {
                    pin24.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y025")) {
                    pin25.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y026")) {
                    pin26.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y027")) {
                    pin27.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y028")) {
                    pin28.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y029")) {
                    pin29.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y030")) {
                    pin30.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y031")) {
                    pin31.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y032")) {
                    pin32.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y033")) {
                    pin33.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y034")) {
                    pin34.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y035")) {
                    pin35.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y036")) {
                    pin36.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y037")) {
                    pin37.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y038")) {
                    pin38.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y039")) {
                    pin39.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y040")) {
                    pin40.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y041")) {
                    pin41.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y042")) {
                    pin42.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y043")) {
                    pin43.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y044")) {
                    pin44.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y045")) {
                    pin45.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y046")) {
                    pin46.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y047")) {
                    pin47.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y048")) {
                    pin48.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y049")) {
                    pin49.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y050")) {
                    pin50.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y051")) {
                    pin51.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y052")) {
                    pin52.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y053")) {
                    pin53.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y054")) {
                    pin54.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y055")) {
                    pin55.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y056")) {
                    pin56.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y057")) {
                    pin57.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y058")) {
                    pin58.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y059")) {
                    pin59.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y060")) {
                    pin60.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y061")) {
                    pin61.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y062")) {
                    pin62.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame2.contains("$Y063")) {
                    pin63.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
            }
            if (!frameNameF3.isEmpty()) {
                frame3 = frameFinished3.toString();
                if (frame3.contains("$Y000")) {
                    pin0.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y001")) {
                    pin1.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y002")) {
                    pin2.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y003")) {
                    pin3.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y004")) {
                    pin4.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y005")) {
                    pin5.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y006")) {
                    pin6.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y007")) {
                    pin7.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y008")) {
                    pin8.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y009")) {
                    pin9.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y010")) {
                    pin10.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y011")) {
                    pin11.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y012")) {
                    pin12.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y013")) {
                    pin13.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y014")) {
                    pin14.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y015")) {
                    pin15.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y016")) {
                    pin16.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y017")) {
                    pin17.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y018")) {
                    pin18.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y019")) {
                    pin19.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y020")) {
                    pin20.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y021")) {
                    pin21.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y022")) {
                    pin22.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y023")) {
                    pin23.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y024")) {
                    pin24.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y025")) {
                    pin25.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y026")) {
                    pin26.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y027")) {
                    pin27.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y028")) {
                    pin28.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y029")) {
                    pin29.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y030")) {
                    pin30.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y031")) {
                    pin31.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y032")) {
                    pin32.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y033")) {
                    pin33.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y034")) {
                    pin34.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y035")) {
                    pin35.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y036")) {
                    pin36.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y037")) {
                    pin37.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y038")) {
                    pin38.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y039")) {
                    pin39.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y040")) {
                    pin40.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y041")) {
                    pin41.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y042")) {
                    pin42.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y043")) {
                    pin43.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y044")) {
                    pin44.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y045")) {
                    pin45.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y046")) {
                    pin46.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y047")) {
                    pin47.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y048")) {
                    pin48.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y049")) {
                    pin49.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y050")) {
                    pin50.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y051")) {
                    pin51.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y052")) {
                    pin52.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y053")) {
                    pin53.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y054")) {
                    pin54.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y055")) {
                    pin55.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y056")) {
                    pin56.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y057")) {
                    pin57.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y058")) {
                    pin58.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y059")) {
                    pin59.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y060")) {
                    pin60.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y061")) {
                    pin61.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y062")) {
                    pin62.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame3.contains("$Y063")) {
                    pin63.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
            }
            if (!frameNameF4.isEmpty()) {
                frame4 = frameFinished4.toString();
                if (frame4.contains("$Y000")) {
                    pin0.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y001")) {
                    pin1.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y002")) {
                    pin2.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y003")) {
                    pin3.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y004")) {
                    pin4.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y005")) {
                    pin5.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y006")) {
                    pin6.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y007")) {
                    pin7.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y008")) {
                    pin8.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y009")) {
                    pin9.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y010")) {
                    pin10.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y011")) {
                    pin11.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y012")) {
                    pin12.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y013")) {
                    pin13.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y014")) {
                    pin14.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y015")) {
                    pin15.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y016")) {
                    pin16.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y017")) {
                    pin17.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y018")) {
                    pin18.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y019")) {
                    pin19.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y020")) {
                    pin20.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y021")) {
                    pin21.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y022")) {
                    pin22.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y023")) {
                    pin23.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y024")) {
                    pin24.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y025")) {
                    pin25.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y026")) {
                    pin26.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y027")) {
                    pin27.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y028")) {
                    pin28.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y029")) {
                    pin29.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y030")) {
                    pin30.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y031")) {
                    pin31.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y032")) {
                    pin32.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y033")) {
                    pin33.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y034")) {
                    pin34.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y035")) {
                    pin35.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y036")) {
                    pin36.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y037")) {
                    pin37.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y038")) {
                    pin38.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y039")) {
                    pin39.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y040")) {
                    pin40.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y041")) {
                    pin41.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y042")) {
                    pin42.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y043")) {
                    pin43.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y044")) {
                    pin44.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y045")) {
                    pin45.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y046")) {
                    pin46.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y047")) {
                    pin47.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y048")) {
                    pin48.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y049")) {
                    pin49.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y050")) {
                    pin50.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y051")) {
                    pin51.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y052")) {
                    pin52.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y053")) {
                    pin53.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y054")) {
                    pin54.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y055")) {
                    pin55.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y056")) {
                    pin56.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y057")) {
                    pin57.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y058")) {
                    pin58.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y059")) {
                    pin59.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y060")) {
                    pin60.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y061")) {
                    pin61.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y062")) {
                    pin62.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame4.contains("$Y063")) {
                    pin63.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
            }
            if (!frameNameF5.isEmpty()) {
                frame5 = frameFinished5.toString();
                if (frame5.contains("$Y000")) {
                    pin0.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y001")) {
                    pin1.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y002")) {
                    pin2.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y003")) {
                    pin3.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y004")) {
                    pin4.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y005")) {
                    pin5.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y006")) {
                    pin6.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y007")) {
                    pin7.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y008")) {
                    pin8.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y009")) {
                    pin9.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y010")) {
                    pin10.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y011")) {
                    pin11.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y012")) {
                    pin12.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y013")) {
                    pin13.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y014")) {
                    pin14.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y015")) {
                    pin15.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y016")) {
                    pin16.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y017")) {
                    pin17.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y018")) {
                    pin18.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y019")) {
                    pin19.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y020")) {
                    pin20.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y021")) {
                    pin21.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y022")) {
                    pin22.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y023")) {
                    pin23.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y024")) {
                    pin24.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y025")) {
                    pin25.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y026")) {
                    pin26.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y027")) {
                    pin27.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y028")) {
                    pin28.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y029")) {
                    pin29.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y030")) {
                    pin30.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y031")) {
                    pin31.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y032")) {
                    pin32.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y033")) {
                    pin33.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y034")) {
                    pin34.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y035")) {
                    pin35.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y036")) {
                    pin36.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y037")) {
                    pin37.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y038")) {
                    pin38.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y039")) {
                    pin39.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y040")) {
                    pin40.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y041")) {
                    pin41.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y042")) {
                    pin42.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y043")) {
                    pin43.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y044")) {
                    pin44.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y045")) {
                    pin45.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y046")) {
                    pin46.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y047")) {
                    pin47.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y048")) {
                    pin48.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y049")) {
                    pin49.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y050")) {
                    pin50.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y051")) {
                    pin51.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y052")) {
                    pin52.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y053")) {
                    pin53.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y054")) {
                    pin54.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y055")) {
                    pin55.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y056")) {
                    pin56.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y057")) {
                    pin57.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y058")) {
                    pin58.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y059")) {
                    pin59.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y060")) {
                    pin60.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y061")) {
                    pin61.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y062")) {
                    pin62.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame5.contains("$Y063")) {
                    pin63.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
            }
            if (!frameNameF6.isEmpty()) {
                frame6 = frameFinished6.toString();
                if (frame6.contains("$Y000")) {
                    pin0.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y001")) {
                    pin1.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y002")) {
                    pin2.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y003")) {
                    pin3.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y004")) {
                    pin4.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y005")) {
                    pin5.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y006")) {
                    pin6.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y007")) {
                    pin7.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y008")) {
                    pin8.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y009")) {
                    pin9.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y010")) {
                    pin10.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y011")) {
                    pin11.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y012")) {
                    pin12.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y013")) {
                    pin13.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y014")) {
                    pin14.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y015")) {
                    pin15.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y016")) {
                    pin16.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y017")) {
                    pin17.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y018")) {
                    pin18.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y019")) {
                    pin19.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y020")) {
                    pin20.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y021")) {
                    pin21.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y022")) {
                    pin22.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y023")) {
                    pin23.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y024")) {
                    pin24.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y025")) {
                    pin25.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y026")) {
                    pin26.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y027")) {
                    pin27.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y028")) {
                    pin28.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y029")) {
                    pin29.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y030")) {
                    pin30.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y031")) {
                    pin31.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y032")) {
                    pin32.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y033")) {
                    pin33.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y034")) {
                    pin34.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y035")) {
                    pin35.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y036")) {
                    pin36.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y037")) {
                    pin37.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y038")) {
                    pin38.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y039")) {
                    pin39.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y040")) {
                    pin40.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y041")) {
                    pin41.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y042")) {
                    pin42.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y043")) {
                    pin43.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y044")) {
                    pin44.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y045")) {
                    pin45.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y046")) {
                    pin46.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y047")) {
                    pin47.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y048")) {
                    pin48.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y049")) {
                    pin49.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y050")) {
                    pin50.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y051")) {
                    pin51.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y052")) {
                    pin52.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y053")) {
                    pin53.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y054")) {
                    pin54.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y055")) {
                    pin55.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y056")) {
                    pin56.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y057")) {
                    pin57.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y058")) {
                    pin58.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y059")) {
                    pin59.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y060")) {
                    pin60.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y061")) {
                    pin61.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y062")) {
                    pin62.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
                if (frame6.contains("$Y063")) {
                    pin63.setBackgroundColor(Color.parseColor("#E6F4F5"));
                }
            }
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
        ArrayList<Object> blank = new ArrayList<>();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, frameSequenceLists);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (!frameFinished1.isEmpty()) {
            if (!frameFinished1.isEmpty()) {
                frameSequenceLists.add(frameName1.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!frameFinished2.isEmpty()) {
                frameSequenceLists.add(frameName2.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!frameFinished3.isEmpty()) {
                frameSequenceLists.add(frameName3.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!frameFinished4.isEmpty()) {
                frameSequenceLists.add(frameName4.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!frameFinished5.isEmpty()) {
                frameSequenceLists.add(frameName5.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!frameFinished6.isEmpty()) {
                frameSequenceLists.add(frameName6.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!sequenceFinished1.isEmpty()) {
                frameSequenceLists.add(sequenceName1.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!sequenceFinished2.isEmpty()) {
                frameSequenceLists.add(sequenceName2.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!sequenceFinished3.isEmpty()) {
                frameSequenceLists.add(sequenceName3.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!sequenceFinished4.isEmpty()) {
                frameSequenceLists.add(sequenceName4.toString().replaceAll("(^\\[|\\])", ""));
            }
            frameSequenceList.setAdapter(adapter);
        } else {
            ArrayAdapter blankSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item, blank);
            blankSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            blank.add(" ");
            frameSequenceList.setAdapter(blankSpinner);
        }
    }

    /**
     * Method to reload the frame spinner (frame)
     *
     * @param view
     */
    public void reloadTheFrameList(View view) {
        Spinner frameLists = findViewById(R.id.frameList);
        ArrayList<Object> frameList = new ArrayList<>();
        ArrayList<Object> blank = new ArrayList<>();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, frameList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (!frameFinished1.isEmpty()) {
            if (!frameFinished1.isEmpty()) {
                frameList.add(frameName1.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!frameFinished2.isEmpty()) {
                frameList.add(frameName2.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!frameFinished3.isEmpty()) {
                frameList.add(frameName3.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!frameFinished4.isEmpty()) {
                frameList.add(frameName4.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!frameFinished5.isEmpty()) {
                frameList.add(frameName5.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!frameFinished6.isEmpty()) {
                frameList.add(frameName6.toString().replaceAll("(^\\[|\\])", ""));
            }
            frameLists.setAdapter(adapter);
        } else {
            ArrayAdapter blankSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item, blank);
            blankSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            blank.add(" ");
            frameLists.setAdapter(blankSpinner);
        }
    }

    /**
     * Method to reload the sequence spinner (sequence)
     *
     * @param view
     */
    public void reloadTheSequenceList(View view) {
        Spinner sequenceLists = findViewById(R.id.sequenceList);
        ArrayList<Object> sequenceList = new ArrayList<>();
        ArrayList<Object> blank = new ArrayList<>();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sequenceList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (!sequenceFinished1.isEmpty()) {
            if (!sequenceFinished1.isEmpty()) {
                sequenceList.add(sequenceName1.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!sequenceFinished2.isEmpty()) {
                sequenceList.add(sequenceName2.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!sequenceFinished3.isEmpty()) {
                sequenceList.add(sequenceName3.toString().replaceAll("(^\\[|\\])", ""));
            }
            if (!sequenceFinished4.isEmpty()) {
                sequenceList.add(sequenceName4.toString().replaceAll("(^\\[|\\])", ""));
            }
            sequenceLists.setAdapter(adapter);
        } else {
            ArrayAdapter blankSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item, blank);
            blankSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            blank.add(" ");
            sequenceLists.setAdapter(blankSpinner);
        }
    }

    /**
     * Method to view the selected frame (frame)
     *
     * @param view
     */
    public void viewTheSelectedFrame(View view) {
        Spinner frameLists = findViewById(R.id.frameList);
        TextView t = findViewById(R.id.savedFrameView);

        if (frameFinished1.isEmpty()) {
            ArrayList<Object> blank = new ArrayList<>();
            ArrayAdapter blankSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item, blank);
            blankSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            blank.add(" ");
            frameLists.setAdapter(blankSpinner);
            if (frameLists.getSelectedItem().toString().contains(" ")) {
                Toast.makeText(getApplicationContext(), "No frames have been made yet", Toast.LENGTH_SHORT).show();
            }
        }

        String frameReplace1;
        String frameReplace2;
        String finialReplace;
        if (!frameLists.getSelectedItem().toString().contains(" ")) {
            String content = frameLists.getSelectedItem().toString();
            if (content.equals(frameName1.toString().replaceAll("(^\\[|\\])", ""))) {
                frameReplace1 = frameFinished1.toString().replace(",", "");
                frameReplace2 = frameReplace1.replaceAll("(^\\[|\\])", "");
                finialReplace = frameReplace2.replace(" ", "\n");
                t.setText(finialReplace);
            } else if (content.equals(frameName2.toString().replaceAll("(^\\[|\\])", ""))) {
                frameReplace1 = frameFinished2.toString().replace(",", "");
                frameReplace2 = frameReplace1.replaceAll("(^\\[|\\])", "");
                finialReplace = frameReplace2.replace(" ", "\n");
                t.setText(finialReplace);
            } else if (content.equals(frameName3.toString().replaceAll("(^\\[|\\])", ""))) {
                frameReplace1 = frameFinished3.toString().replace(",", "");
                frameReplace2 = frameReplace1.replaceAll("(^\\[|\\])", "");
                finialReplace = frameReplace2.replace(" ", "\n");
                t.setText(finialReplace);
            } else if (content.equals(frameName4.toString().replaceAll("(^\\[|\\])", ""))) {
                frameReplace1 = frameFinished4.toString().replace(",", "");
                frameReplace2 = frameReplace1.replaceAll("(^\\[|\\])", "");
                finialReplace = frameReplace2.replace(" ", "\n");
                t.setText(finialReplace);
            } else if (content.equals(frameName5.toString().replaceAll("(^\\[|\\])", ""))) {
                frameReplace1 = frameFinished5.toString().replace(",", "");
                frameReplace2 = frameReplace1.replaceAll("(^\\[|\\])", "");
                finialReplace = frameReplace2.replace(" ", "\n");
                t.setText(finialReplace);
            } else if (content.equals(frameName6.toString().replaceAll("(^\\[|\\])", ""))) {
                frameReplace1 = frameFinished6.toString().replace(",", "");
                frameReplace2 = frameReplace1.replaceAll("(^\\[|\\])", "");
                finialReplace = frameReplace2.replace(" ", "\n");
                t.setText(finialReplace);
            }
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

        if (frameFinished1.isEmpty()) {
            ArrayList<Object> blank = new ArrayList<>();
            ArrayAdapter blankSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item, blank);
            blankSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            blank.add(" ");
            sequenceLists.setAdapter(blankSpinner);
            if (sequenceLists.getSelectedItem().toString().contains(" ")) {
                Toast.makeText(getApplicationContext(), "No sequences have been made yet", Toast.LENGTH_SHORT).show();
            }
        }

        String sequenceReplace1;
        String sequenceReplace2;
        String sequenceReplace3;
        if (!sequenceLists.getSelectedItem().toString().contains(" ")) {
            String content = sequenceLists.getSelectedItem().toString();
            if (content.equals(sequenceName1.toString().replaceAll("(^\\[|\\])", ""))) {
                sequenceReplace1 = sequenceFinished1.toString().replace(",", "");
                sequenceReplace2 = sequenceReplace1.replaceAll("(^\\[|\\])", "");
                sequenceReplace3 = sequenceReplace2.replaceAll(" ", "\n");
                t.setText(sequenceReplace3);
            } else if (content.equals(sequenceName2.toString().replaceAll("(^\\[|\\])", ""))) {
                sequenceReplace1 = sequenceFinished2.toString().replace(",", "");
                sequenceReplace2 = sequenceReplace1.replaceAll("(^\\[|\\])", "");
                sequenceReplace3 = sequenceReplace2.replaceAll(" ", "\n");
                t.setText(sequenceReplace3);
            } else if (content.equals(sequenceName3.toString().replaceAll("(^\\[|\\])", ""))) {
                sequenceReplace1 = sequenceFinished3.toString().replace(",", "");
                sequenceReplace2 = sequenceReplace1.replaceAll("(^\\[|\\])", "");
                sequenceReplace3 = sequenceReplace2.replaceAll(" ", "\n");
                t.setText(sequenceReplace3);
            } else if (content.equals(sequenceName4.toString().replaceAll("(^\\[|\\])", ""))) {
                sequenceReplace1 = sequenceFinished4.toString().replace(",", "");
                sequenceReplace2 = sequenceReplace1.replaceAll("(^\\[|\\])", "");
                sequenceReplace3 = sequenceReplace2.replaceAll(" ", "\n");
                t.setText(sequenceReplace3);
            }
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

        String combined;

        String frameReplace1;
        String frameReplace2;
        String frameReplace3;

        String sequenceReplace1;
        String sequenceReplace2;
        String sequenceReplace3;

        String firstFrameReplace1;
        String firstFrameReplace2;
        String firstFrameReplace3 = "";

        String secondFrameReplace1;
        String secondFrameReplace2;
        String secondFrameReplace3 = "";

        String thirdFrameReplace1;
        String thirdFrameReplace2;
        String thirdFrameReplace3 = "";

        String fourthFrameReplace1;
        String fourthFrameReplace2;
        String fourthFrameReplace3 = "";

        String fifthFrameReplace1;
        String fifthFrameReplace2;
        String fifthFrameReplace3 = "";

        String sixthFrameReplace1;
        String sixthFrameReplace2;
        String sixthFrameReplace3 = "";

        if (frameSequenceList.getSelectedItem().toString().contains(" ")) {
            ArrayList<Object> blank = new ArrayList<>();
            ArrayAdapter blankSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item, blank);
            blankSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            blank.add(" ");
            frameSequenceList.setAdapter(blankSpinner);
            if (text.equals(" ")) {
                Toast.makeText(getApplicationContext(), "Nothing to export", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (text.equals(frameName1.toString().replaceAll("(^\\[|\\])", ""))) {
                if (!frameFinished1.isEmpty()) {
                    frameReplace1 = frameFinished1.toString().replace(",", "");
                    frameReplace2 = frameReplace1.replaceAll("(^\\[|\\])", "");
                    frameReplace3 = frameReplace2.replace(" ", "\n");
                    frameSequenceName = frameReplace3.substring(0, 5);
                    frameSequenceName = frameSequenceName + ".txt";

                    File dir = new File(path, frameSequenceName);
                    try {
                        FileOutputStream fos = new FileOutputStream(dir);
                        fos.write(frameReplace3.getBytes());
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
            if (text.equals(frameName2.toString().replaceAll("(^\\[|\\])", ""))) {
                if (!frameFinished2.isEmpty()) {
                    frameReplace1 = frameFinished2.toString().replace(",", "");
                    frameReplace2 = frameReplace1.replaceAll("(^\\[|\\])", "");
                    frameReplace3 = frameReplace2.replace(" ", "\n");
                    frameSequenceName = frameReplace3.substring(0, 5);
                    frameSequenceName = frameSequenceName + ".txt";
                    File dir = new File(path, frameSequenceName);
                    try {
                        FileOutputStream fos = new FileOutputStream(dir);
                        fos.write(frameReplace3.getBytes());
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
            if (text.equals(frameName3.toString().replaceAll("(^\\[|\\])", ""))) {
                if (!frameFinished3.isEmpty()) {
                    frameReplace1 = frameFinished3.toString().replace(",", "");
                    frameReplace2 = frameReplace1.replaceAll("(^\\[|\\])", "");
                    frameReplace3 = frameReplace2.replace(" ", "\n");
                    frameSequenceName = frameReplace3.substring(0, 5);
                    frameSequenceName = frameSequenceName + ".txt";

                    File dir = new File(path, frameSequenceName);
                    try {
                        FileOutputStream fos = new FileOutputStream(dir);
                        fos.write(frameReplace3.getBytes());
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
            if (text.equals(frameName4.toString().replaceAll("(^\\[|\\])", ""))) {
                if (!frameFinished4.isEmpty()) {
                    frameReplace1 = frameFinished4.toString().replace(",", "");
                    frameReplace2 = frameReplace1.replaceAll("(^\\[|\\])", "");
                    frameReplace3 = frameReplace2.replace(" ", "\n");
                    frameSequenceName = frameReplace3.substring(0, 5);
                    frameSequenceName = frameSequenceName + ".txt";

                    File dir = new File(path, frameSequenceName);
                    try {
                        FileOutputStream fos = new FileOutputStream(dir);
                        fos.write(frameReplace3.getBytes());
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
            if (text.equals(frameName5.toString().replaceAll("(^\\[|\\])", ""))) {
                if (!frameFinished5.isEmpty()) {
                    frameReplace1 = frameFinished5.toString().replace(",", "");
                    frameReplace2 = frameReplace1.replaceAll("(^\\[|\\])", "");
                    frameReplace3 = frameReplace2.replace(" ", "\n");
                    frameSequenceName = frameReplace3.substring(0, 5);
                    frameSequenceName = frameSequenceName + ".txt";
                    File dir = new File(path, frameSequenceName);
                    try {
                        FileOutputStream fos = new FileOutputStream(dir);
                        fos.write(frameReplace3.getBytes());
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
            if (text.equals(frameName6.toString().replaceAll("(^\\[|\\])", ""))) {
                if (!frameFinished6.isEmpty()) {
                    frameReplace1 = frameFinished6.toString().replace(",", "");
                    frameReplace2 = frameReplace1.replaceAll("(^\\[|\\])", "");
                    frameReplace3 = frameReplace2.replace(" ", "\n");
                    frameSequenceName = frameReplace3.substring(0, 5);
                    frameSequenceName = frameSequenceName + ".txt";
                    File dir = new File(path, frameSequenceName);
                    try {
                        FileOutputStream fos = new FileOutputStream(dir);
                        fos.write(frameReplace3.getBytes());
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
            if (text.equals(sequenceName1.toString().replaceAll("(^\\[|\\])", ""))) {
                if (!sequenceFinished1.isEmpty()) {
                    sequenceReplace1 = sequenceFinished1.toString().replace(",", "");
                    sequenceReplace2 = sequenceReplace1.replaceAll("(^\\[|\\])", "");
                    sequenceReplace3 = sequenceReplace2.replace(" ", "\n");
                    frameSequenceName = sequenceReplace3.substring(0, 5);
                    frameSequenceName = frameSequenceName + ".txt";
                    if (sequenceFinished1.toString().contains(frameNameF1.toString().replaceAll("(^\\[|\\])", ""))) {
                        firstFrameReplace1 = frameFinished1.toString().replace(",", "");
                        firstFrameReplace2 = firstFrameReplace1.replaceAll("(^\\[|\\])", "");
                        firstFrameReplace3 = firstFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished1.toString().contains(frameNameF2.toString().replaceAll("(^\\[|\\])", ""))) {
                        secondFrameReplace1 = frameFinished2.toString().replace(",", "");
                        secondFrameReplace2 = secondFrameReplace1.replaceAll("(^\\[|\\])", "");
                        secondFrameReplace3 = secondFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished1.toString().contains(frameNameF3.toString().replaceAll("(^\\[|\\])", ""))) {
                        thirdFrameReplace1 = frameFinished3.toString().replace(",", "");
                        thirdFrameReplace2 = thirdFrameReplace1.replaceAll("(^\\[|\\])", "");
                        thirdFrameReplace3 = thirdFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished1.toString().contains(frameNameF4.toString().replaceAll("(^\\[|\\])", ""))) {
                        fourthFrameReplace1 = frameFinished4.toString().replace(",", "");
                        fourthFrameReplace2 = fourthFrameReplace1.replaceAll("(^\\[|\\])", "");
                        fourthFrameReplace3 = fourthFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished1.toString().contains(frameNameF5.toString().replaceAll("(^\\[|\\])", ""))) {
                        fifthFrameReplace1 = frameFinished5.toString().replace(",", "");
                        fifthFrameReplace2 = fifthFrameReplace1.replaceAll("(^\\[|\\])", "");
                        fifthFrameReplace3 = fifthFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished1.toString().contains(frameNameF6.toString().replaceAll("(^\\[|\\])", ""))) {
                        sixthFrameReplace1 = frameFinished6.toString().replace(",", "");
                        sixthFrameReplace2 = sixthFrameReplace1.replaceAll("(^\\[|\\])", "");
                        sixthFrameReplace3 = sixthFrameReplace2.replace(" ", "\n");
                    }
                    combined = sequenceReplace3 + newLine + firstFrameReplace3 + newLine + secondFrameReplace3
                            + newLine + thirdFrameReplace3 + newLine + fourthFrameReplace3
                            + newLine + fifthFrameReplace3 + newLine + sixthFrameReplace3;
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
            }
            if (text.equals(sequenceName2.toString().replaceAll("(^\\[|\\])", ""))) {
                if (!sequenceFinished2.isEmpty()) {
                    sequenceReplace1 = sequenceFinished2.toString().replace(",", "");
                    sequenceReplace2 = sequenceReplace1.replaceAll("(^\\[|\\])", "");
                    sequenceReplace3 = sequenceReplace2.replace(" ", "\n");
                    frameSequenceName = sequenceReplace3.substring(0, 5);
                    frameSequenceName = frameSequenceName + ".txt";
                    if (sequenceFinished2.toString().contains(frameNameF1.toString().replaceAll("(^\\[|\\])", ""))) {
                        firstFrameReplace1 = frameFinished1.toString().replace(",", "");
                        firstFrameReplace2 = firstFrameReplace1.replaceAll("(^\\[|\\])", "");
                        firstFrameReplace3 = firstFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished2.toString().contains(frameNameF2.toString().replaceAll("(^\\[|\\])", ""))) {
                        secondFrameReplace1 = frameFinished2.toString().replace(",", "");
                        secondFrameReplace2 = secondFrameReplace1.replaceAll("(^\\[|\\])", "");
                        secondFrameReplace3 = secondFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished2.toString().contains(frameNameF3.toString().replaceAll("(^\\[|\\])", ""))) {
                        thirdFrameReplace1 = frameFinished3.toString().replace(",", "");
                        thirdFrameReplace2 = thirdFrameReplace1.replaceAll("(^\\[|\\])", "");
                        thirdFrameReplace3 = thirdFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished2.toString().contains(frameNameF4.toString().replaceAll("(^\\[|\\])", ""))) {
                        fourthFrameReplace1 = frameFinished4.toString().replace(",", "");
                        fourthFrameReplace2 = fourthFrameReplace1.replaceAll("(^\\[|\\])", "");
                        fourthFrameReplace3 = fourthFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished2.toString().contains(frameNameF5.toString().replaceAll("(^\\[|\\])", ""))) {
                        fifthFrameReplace1 = frameFinished5.toString().replace(",", "");
                        fifthFrameReplace2 = fifthFrameReplace1.replaceAll("(^\\[|\\])", "");
                        fifthFrameReplace3 = fifthFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished2.toString().contains(frameNameF6.toString().replaceAll("(^\\[|\\])", ""))) {
                        sixthFrameReplace1 = frameFinished6.toString().replace(",", "");
                        sixthFrameReplace2 = sixthFrameReplace1.replaceAll("(^\\[|\\])", "");
                        sixthFrameReplace3 = sixthFrameReplace2.replace(" ", "\n");
                    }
                    combined = sequenceReplace3 + newLine + firstFrameReplace3 + newLine + secondFrameReplace3
                            + newLine + thirdFrameReplace3 + newLine + fourthFrameReplace3
                            + newLine + fifthFrameReplace3 + newLine + sixthFrameReplace3;
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
            }
            if (text.equals(sequenceName3.toString().replaceAll("(^\\[|\\])", ""))) {
                if (!sequenceFinished3.isEmpty()) {
                    sequenceReplace1 = sequenceFinished3.toString().replace(",", "");
                    sequenceReplace2 = sequenceReplace1.replaceAll("(^\\[|\\])", "");
                    sequenceReplace3 = sequenceReplace2.replace(" ", "\n");
                    frameSequenceName = sequenceReplace3.substring(0, 5);
                    frameSequenceName = frameSequenceName + ".txt";
                    if (sequenceFinished3.toString().contains(frameNameF1.toString().replaceAll("(^\\[|\\])", ""))) {
                        firstFrameReplace1 = frameFinished1.toString().replace(",", "");
                        firstFrameReplace2 = firstFrameReplace1.replaceAll("(^\\[|\\])", "");
                        firstFrameReplace3 = firstFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished3.toString().contains(frameNameF2.toString().replaceAll("(^\\[|\\])", ""))) {
                        secondFrameReplace1 = frameFinished2.toString().replace(",", "");
                        secondFrameReplace2 = secondFrameReplace1.replaceAll("(^\\[|\\])", "");
                        secondFrameReplace3 = secondFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished3.toString().contains(frameNameF3.toString().replaceAll("(^\\[|\\])", ""))) {
                        thirdFrameReplace1 = frameFinished3.toString().replace(",", "");
                        thirdFrameReplace2 = thirdFrameReplace1.replaceAll("(^\\[|\\])", "");
                        thirdFrameReplace3 = thirdFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished3.toString().contains(frameNameF4.toString().replaceAll("(^\\[|\\])", ""))) {
                        fourthFrameReplace1 = frameFinished4.toString().replace(",", "");
                        fourthFrameReplace2 = fourthFrameReplace1.replaceAll("(^\\[|\\])", "");
                        fourthFrameReplace3 = fourthFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished3.toString().contains(frameNameF5.toString().replaceAll("(^\\[|\\])", ""))) {
                        fifthFrameReplace1 = frameFinished5.toString().replace(",", "");
                        fifthFrameReplace2 = fifthFrameReplace1.replaceAll("(^\\[|\\])", "");
                        fifthFrameReplace3 = fifthFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished3.toString().contains(frameNameF6.toString().replaceAll("(^\\[|\\])", ""))) {
                        sixthFrameReplace1 = frameFinished6.toString().replace(",", "");
                        sixthFrameReplace2 = sixthFrameReplace1.replaceAll("(^\\[|\\])", "");
                        sixthFrameReplace3 = sixthFrameReplace2.replace(" ", "\n");
                    }
                    combined = sequenceReplace3 + newLine + firstFrameReplace3 + newLine + secondFrameReplace3
                            + newLine + thirdFrameReplace3 + newLine + fourthFrameReplace3
                            + newLine + fifthFrameReplace3 + newLine + sixthFrameReplace3;
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
            }
            if (text.equals(sequenceName4.toString().replaceAll("(^\\[|\\])", ""))) {
                if (!sequenceFinished4.isEmpty()) {
                    sequenceReplace1 = sequenceFinished4.toString().replace(",", "");
                    sequenceReplace2 = sequenceReplace1.replaceAll("(^\\[|\\])", "");
                    sequenceReplace3 = sequenceReplace2.replace(" ", "\n");
                    frameSequenceName = sequenceReplace3.substring(0, 5);
                    frameSequenceName = frameSequenceName + ".txt";
                    if (sequenceFinished4.toString().contains(frameNameF1.toString().replaceAll("(^\\[|\\])", ""))) {
                        firstFrameReplace1 = frameFinished1.toString().replace(",", "");
                        firstFrameReplace2 = firstFrameReplace1.replaceAll("(^\\[|\\])", "");
                        firstFrameReplace3 = firstFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished4.toString().contains(frameNameF2.toString().replaceAll("(^\\[|\\])", ""))) {
                        secondFrameReplace1 = frameFinished2.toString().replace(",", "");
                        secondFrameReplace2 = secondFrameReplace1.replaceAll("(^\\[|\\])", "");
                        secondFrameReplace3 = secondFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished4.toString().contains(frameNameF3.toString().replaceAll("(^\\[|\\])", ""))) {
                        thirdFrameReplace1 = frameFinished3.toString().replace(",", "");
                        thirdFrameReplace2 = thirdFrameReplace1.replaceAll("(^\\[|\\])", "");
                        thirdFrameReplace3 = thirdFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished4.toString().contains(frameNameF4.toString().replaceAll("(^\\[|\\])", ""))) {
                        fourthFrameReplace1 = frameFinished4.toString().replace(",", "");
                        fourthFrameReplace2 = fourthFrameReplace1.replaceAll("(^\\[|\\])", "");
                        fourthFrameReplace3 = fourthFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished4.toString().contains(frameNameF5.toString().replaceAll("(^\\[|\\])", ""))) {
                        fifthFrameReplace1 = frameFinished5.toString().replace(",", "");
                        fifthFrameReplace2 = fifthFrameReplace1.replaceAll("(^\\[|\\])", "");
                        fifthFrameReplace3 = fifthFrameReplace2.replace(" ", "\n");
                    }
                    if (sequenceFinished4.toString().contains(frameNameF6.toString().replaceAll("(^\\[|\\])", ""))) {
                        sixthFrameReplace1 = frameFinished6.toString().replace(",", "");
                        sixthFrameReplace2 = sixthFrameReplace1.replaceAll("(^\\[|\\])", "");
                        sixthFrameReplace3 = sixthFrameReplace2.replace(" ", "\n");
                    }
                    combined = sequenceReplace3 + newLine + firstFrameReplace3 + newLine + secondFrameReplace3
                            + newLine + thirdFrameReplace3 + newLine + fourthFrameReplace3
                            + newLine + fifthFrameReplace3 + newLine + sixthFrameReplace3;
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
            }
        }
    }

    /**
     * Method to give the sequence a name (sequence)
     *
     * @param view
     */
    public void nameTheSequence(View view) {
        nameOfSequence = findViewById(R.id.nameSequence);
        framesUsedCounter = findViewById(R.id.framesUsedCounter);

        String content = nameOfSequence.getSelectedItem().toString();

        if (commandInformationSeq.isEmpty()) {
            if (sequenceName1.isEmpty()) {
                sequenceName1.add("$S" + content);
                commandInformationSeq.add(0, "$S" + content);
                if (commandInformationSeq.add(true)) {
                    nameOfSequence.setVisibility(View.INVISIBLE);
                    findViewById(R.id.setSequenceName).setVisibility(View.INVISIBLE);
                    commandInformationSeq.remove(true);
                    findViewById(R.id.setNumFrames).setVisibility(View.VISIBLE);
                    findViewById(R.id.numOfFrames).setVisibility(View.VISIBLE);
                }
            } else if (sequenceName2.isEmpty()) {
                if (!sequenceName1.contains("$S" + content)) {
                    sequenceName2.add("$S" + content);
                    commandInformationSeq.add(0, "$S" + content);
                    if (commandInformationSeq.add(true)) {
                        nameOfSequence.setVisibility(View.INVISIBLE);
                        findViewById(R.id.setSequenceName).setVisibility(View.INVISIBLE);
                        commandInformationSeq.remove(true);
                        findViewById(R.id.setNumFrames).setVisibility(View.VISIBLE);
                        findViewById(R.id.numOfFrames).setVisibility(View.VISIBLE);
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
                        findViewById(R.id.setSequenceName).setVisibility(View.INVISIBLE);
                        commandInformationSeq.remove(true);
                        findViewById(R.id.setNumFrames).setVisibility(View.VISIBLE);
                        findViewById(R.id.numOfFrames).setVisibility(View.VISIBLE);
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
                        findViewById(R.id.setSequenceName).setVisibility(View.INVISIBLE);
                        commandInformationSeq.remove(true);
                        findViewById(R.id.setNumFrames).setVisibility(View.VISIBLE);
                        findViewById(R.id.numOfFrames).setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (!commandInformationSeq.isEmpty()) {
            if (firstSequence == false) {
                sequenceName1.clear();
            }
            if (secondSequence == false) {
                sequenceName2.clear();
            }
            if (thirdSequence == false) {
                sequenceName3.clear();
            }
            if (fourthSequence == false) {
                sequenceName4.clear();
            }
            commandInformationSeq.clear();
            frameSelections.clear();
            totalFrames.clear();
            frameCounting = 0;
            framesUsedCounter.setText(String.valueOf(frameCounting));
            if (sequenceName1.isEmpty()) {
                sequenceName1.add("$S" + content);
                commandInformationSeq.add(0, "$S" + content);
                if (commandInformationSeq.add(true)) {
                    nameOfSequence.setVisibility(View.INVISIBLE);
                    findViewById(R.id.setSequenceName).setVisibility(View.INVISIBLE);
                    commandInformationSeq.remove(true);
                    findViewById(R.id.setNumFrames).setVisibility(View.VISIBLE);
                    findViewById(R.id.numOfFrames).setVisibility(View.VISIBLE);
                }
            } else if (sequenceName2.isEmpty()) {
                if (!sequenceName1.contains("$S" + content)) {
                    sequenceName2.add("$S" + content);
                    commandInformationSeq.add(0, "$S" + content);
                    if (commandInformationSeq.add(true)) {
                        nameOfSequence.setVisibility(View.INVISIBLE);
                        findViewById(R.id.setSequenceName).setVisibility(View.INVISIBLE);
                        commandInformationSeq.remove(true);
                        findViewById(R.id.setNumFrames).setVisibility(View.VISIBLE);
                        findViewById(R.id.numOfFrames).setVisibility(View.VISIBLE);
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
                        findViewById(R.id.setSequenceName).setVisibility(View.INVISIBLE);
                        commandInformationSeq.remove(true);
                        findViewById(R.id.setNumFrames).setVisibility(View.VISIBLE);
                        findViewById(R.id.numOfFrames).setVisibility(View.VISIBLE);
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
                        findViewById(R.id.setSequenceName).setVisibility(View.INVISIBLE);
                        commandInformationSeq.remove(true);
                        findViewById(R.id.setNumFrames).setVisibility(View.VISIBLE);
                        findViewById(R.id.numOfFrames).setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Name in use", Toast.LENGTH_SHORT).show();
                }
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
        setFrame = findViewById(R.id.setFrames);
        selectRepAmount = findViewById(R.id.selectRepetitionAmount);
        setAmountOfRep = findViewById(R.id.setRepetitionAmount);

        frameUsedLimit = findViewById(R.id.frameUsedLimit);
        framesUsedCounter = findViewById(R.id.frameUsedLimit);

        TextView t = findViewById(R.id.sequenceCreationView);
        String start = "";

        String frameNumberSelected = numOfFramesSelected.getSelectedItem().toString();
        commandInformationSeq.add(1, "$n00" + frameNumberSelected);

        for (Object Selections : commandInformationSeq) {
            start = start + Selections + "\n";
        }
        t.setText(start);

        framesUsed.add(frameNumberSelected);

        Spinner frameList = findViewById(R.id.frameSelection);
        ArrayList<Object> frameSelections = new ArrayList<>();

        if (!frameFinished1.isEmpty()) {
            frameSelections.add(frameName1.toString().replaceAll("(^\\[|\\])", ""));
        }
        if (!frameFinished2.isEmpty()) {
            frameSelections.add(frameName2.toString().replaceAll("(^\\[|\\])", ""));
        }
        if (!frameFinished3.isEmpty()) {
            frameSelections.add(frameName3.toString().replaceAll("(^\\[|\\])", ""));
        }
        if (!frameFinished4.isEmpty()) {
            frameSelections.add(frameName4.toString().replaceAll("(^\\[|\\])", ""));
        }
        if (!frameFinished5.isEmpty()) {
            frameSelections.add(frameName5.toString().replaceAll("(^\\[|\\])", ""));
        }
        if (!frameFinished6.isEmpty()) {
            frameSelections.add(frameName6.toString().replaceAll("(^\\[|\\])", ""));
        }

        numOfFramesSelected = findViewById(R.id.setNumFrames);
        String framesSelected = numOfFramesSelected.getSelectedItem().toString();
        int frameLimit = Integer.parseInt(framesSelected);

        if (frameLimit == 1) {
            frameUsedLimit.setText("/ 1");
        } else if (frameLimit == 2) {
            frameUsedLimit.setText("/ 2");
        } else if (frameLimit == 3) {
            frameUsedLimit.setText("/ 3");
        } else if (frameLimit == 4) {
            frameUsedLimit.setText("/ 4");
        } else if (frameLimit == 5) {
            frameUsedLimit.setText("/ 5");
        } else if (frameLimit == 6) {
            frameUsedLimit.setText("/ 6");
        } else if (frameLimit == 7) {
            frameUsedLimit.setText("/ 7");
        } else if (frameLimit == 8) {
            frameUsedLimit.setText("/ 8");
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, frameSelections);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        frameList.setAdapter(adapter);

        if (commandInformationSeq.add(true)) {
            findViewById(R.id.numOfFrames).setVisibility(View.INVISIBLE);
            numOfFramesSelected.setVisibility(View.INVISIBLE);
            commandInformationSeq.remove(true);
            setFrame.setVisibility(View.VISIBLE);
            findViewById(R.id.frameSelection).setVisibility(View.VISIBLE);
            selectRepAmount.setVisibility(View.VISIBLE);
            setAmountOfRep.setVisibility(View.VISIBLE);
            findViewById(R.id.sequenceCreationView).setVisibility(View.VISIBLE);
            findViewById(R.id.saveSequence).setVisibility(View.VISIBLE);
            findViewById(R.id.frameCounter).setVisibility(View.VISIBLE);
            findViewById(R.id.framesUsedCounter).setVisibility(View.VISIBLE);
            findViewById(R.id.resetFramesUsed).setVisibility(View.VISIBLE);
            findViewById(R.id.frameUsedLimit).setVisibility(View.VISIBLE);
            findViewById(R.id.frameUsedLimit).setVisibility(View.VISIBLE);
/*            if(frameNumberSelected.equals("1")){
                findViewById(R.id.firstFrame).setVisibility(View.VISIBLE);
            }else if(frameNumberSelected.equals("2")){
                findViewById(R.id.firstFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.secondFrame).setVisibility(View.VISIBLE);
            }else if(frameNumberSelected.equals("3")){
                findViewById(R.id.firstFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.secondFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.thirdFrame).setVisibility(View.VISIBLE);
            }else if(frameNumberSelected.equals("4")){
                findViewById(R.id.firstFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.secondFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.thirdFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.fourthFrame).setVisibility(View.VISIBLE);
            }else if(frameNumberSelected.equals("5")){
                findViewById(R.id.firstFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.secondFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.thirdFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.fourthFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.fifthFrame).setVisibility(View.VISIBLE);
            }else if(frameNumberSelected.equals("6")){
                findViewById(R.id.firstFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.secondFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.thirdFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.fourthFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.fifthFrame).setVisibility(View.VISIBLE);
                findViewById(R.id.sixthFrame).setVisibility(View.VISIBLE);
            }*/

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

            selectRepAmount.setEnabled(false);
            setAmountOfRep.setEnabled(false);
        }
    }

    /**
     * Method to set a frame to be used (sequence)
     *
     * @param view
     */
    public static int frameCounting = 0;

    public void setTheFrames(View view) {
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

        sequenceCreationView = findViewById(R.id.sequenceCreationView);

        TextView t = findViewById(R.id.sequenceCreationView);
        String finial_commands = "";
        String finial_selection = "";
        ArrayList<Object> blank = new ArrayList<>();

        framesUsedCounter = findViewById(R.id.framesUsedCounter);

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

        if (!frameFinished1.isEmpty()) {
            String content = frameList.getSelectedItem().toString();
            if (content.equals(frameName1.toString().replaceAll("(^\\[|\\])", "")) && !frameSelections.contains(frameNameF1.toString().replaceAll("(^\\[|\\])", ""))) {
                totalFrames.add(frameCount);
                frameCounting++;
                framesUsedCounter.setText(String.valueOf(frameCounting));
                if (totalFrames.size() <= frameLimit) {
                    frameSelections.add(frameNameF1.toString().replaceAll("(^\\[|\\])", ""));
                    for (Object Selections : frameSelections) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    setFrame.setEnabled(false);
                    frameAmount.setEnabled(false);
                    selectRepAmount.setEnabled(true);
                    setAmountOfRep.setEnabled(true);
                }
            } else if (content.equals(frameName1.toString().replaceAll("(^\\[|\\])", "")) && frameSelections.contains(frameNameF1.toString().replaceAll("(^\\[|\\])", ""))) {
                Toast.makeText(getApplicationContext(), "Frame already in use!", Toast.LENGTH_SHORT).show();
            } else if (content.equals(frameName2.toString().replaceAll("(^\\[|\\])", "")) && !frameSelections.contains(frameNameF2.toString().replaceAll("(^\\[|\\])", ""))) {
                totalFrames.add(frameCount);
                frameCounting++;
                framesUsedCounter.setText(String.valueOf(frameCounting));
                if (totalFrames.size() <= frameLimit) {
                    frameSelections.add(frameNameF2.toString().replaceAll("(^\\[|\\])", ""));
                    for (Object Selections : frameSelections) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    setFrame.setEnabled(false);
                    frameAmount.setEnabled(false);
                    selectRepAmount.setEnabled(true);
                    setAmountOfRep.setEnabled(true);
                }
            } else if (content.equals(frameName2.toString().replaceAll("(^\\[|\\])", "")) && frameSelections.contains(frameNameF2.toString().replaceAll("(^\\[|\\])", ""))) {
                Toast.makeText(getApplicationContext(), "Frame already in use!", Toast.LENGTH_SHORT).show();
            } else if (content.equals(frameName3.toString().replaceAll("(^\\[|\\])", "")) && !frameSelections.contains(frameNameF3)) {
                totalFrames.add(frameCount);
                frameCounting++;
                framesUsedCounter.setText(String.valueOf(frameCounting));
                if (totalFrames.size() <= frameLimit) {
                    frameSelections.add(frameNameF3.toString().replaceAll("(^\\[|\\])", ""));
                    for (Object Selections : frameSelections) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    setFrame.setEnabled(false);
                    frameAmount.setEnabled(false);
                    selectRepAmount.setEnabled(true);
                    setAmountOfRep.setEnabled(true);

                }
            } else if (content.equals(frameName3.toString().replaceAll("(^\\[|\\])", "")) && frameSelections.contains(frameNameF3.toString().replaceAll("(^\\[|\\])", ""))) {
                Toast.makeText(getApplicationContext(), "Frame already in use!", Toast.LENGTH_SHORT).show();
            } else if (content.equals(frameName4.toString().replaceAll("(^\\[|\\])", "")) && !frameSelections.contains(frameNameF4.toString().replaceAll("(^\\[|\\])", ""))) {
                totalFrames.add(frameCount);
                frameCounting++;
                framesUsedCounter.setText(String.valueOf(frameCounting));
                if (totalFrames.size() <= frameLimit) {
                    frameSelections.add(frameNameF4.toString().replaceAll("(^\\[|\\])", ""));
                    for (Object Selections : frameSelections) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    setFrame.setEnabled(false);
                    frameAmount.setEnabled(false);
                    selectRepAmount.setEnabled(true);
                    setAmountOfRep.setEnabled(true);
                }

            } else if (content.equals(frameName4.toString().replaceAll("(^\\[|\\])", "")) && frameSelections.contains(frameNameF4.toString().replaceAll("(^\\[|\\])", ""))) {
                Toast.makeText(getApplicationContext(), "Frame already in use!", Toast.LENGTH_SHORT).show();
            } else if (content.equals(frameName5.toString().replaceAll("(^\\[|\\])", "")) && !frameSelections.contains(frameNameF5.toString().replaceAll("(^\\[|\\])", ""))) {
                totalFrames.add(frameCount);
                frameCounting++;
                framesUsedCounter.setText(String.valueOf(frameCounting));
                if (totalFrames.size() <= frameLimit) {
                    frameSelections.add(frameNameF5.toString().replaceAll("(^\\[|\\])", ""));
                    for (Object Selections : frameSelections) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    setFrame.setEnabled(false);
                    frameAmount.setEnabled(false);
                    selectRepAmount.setEnabled(true);
                    setAmountOfRep.setEnabled(true);
                }

            } else if (content.equals(frameName5.toString().replaceAll("(^\\[|\\])", "")) && frameSelections.contains(frameNameF5.toString().replaceAll("(^\\[|\\])", ""))) {
                Toast.makeText(getApplicationContext(), "Frame already in use!", Toast.LENGTH_SHORT).show();
            } else if (content.equals(frameName6.toString().replaceAll("(^\\[|\\])", "")) && !frameSelections.contains(frameNameF6.toString().replaceAll("(^\\[|\\])", ""))) {
                totalFrames.add(frameCount);
                frameCounting++;
                framesUsedCounter.setText(String.valueOf(frameCounting));
                if (totalFrames.size() <= frameLimit) {
                    frameSelections.add(frameNameF6.toString().replaceAll("(^\\[|\\])", ""));
                    for (Object Selections : frameSelections) {
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                    setFrame.setEnabled(false);
                    frameAmount.setEnabled(false);
                    selectRepAmount.setEnabled(true);
                    setAmountOfRep.setEnabled(true);
                }

            } else if (content.equals(frameName6.toString().replaceAll("(^\\[|\\])", "")) && frameSelections.contains(frameNameF6.toString().replaceAll("(^\\[|\\])", ""))) {
                Toast.makeText(getApplicationContext(), "Frame already in use!", Toast.LENGTH_SHORT).show();
            }
        } else {
            ArrayAdapter blankSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item, blank);
            blankSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            blank.add(" ");
            frameList.setAdapter(blankSpinner);
            Toast.makeText(getApplicationContext(), "Please make a frame first", Toast.LENGTH_SHORT).show();
        }

        if (frameCounting == frameLimit) {
            framesUsedCounter.setTextColor(Color.parseColor("#F30606"));
        } else {
            framesUsedCounter.setTextColor(Color.parseColor("#06F326"));
        }

        String frame1;
        String frame2;
        String frame3;
        String frame4;
        String frame5;
        String frame6;

        if (sequenceCreationView.getText().toString().contains(frameNameF1.toString().replaceAll("(^\\[|\\])", ""))) {
            frame1 = frameFinished1.toString();
            if (frame1.contains("$Y000")) {
                pin0.setChecked(true);
                pin0.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y001")) {
                pin1.setChecked(true);
                pin1.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y002")) {
                pin2.setChecked(true);
                pin2.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y003")) {
                pin3.setChecked(true);
                pin3.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y004")) {
                pin4.setChecked(true);
                pin4.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y005")) {
                pin5.setChecked(true);
                pin5.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y006")) {
                pin6.setChecked(true);
                pin6.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y007")) {
                pin7.setChecked(true);
                pin7.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y008")) {
                pin8.setChecked(true);
                pin8.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y009")) {
                pin9.setChecked(true);
                pin9.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y010")) {
                pin10.setChecked(true);
                pin10.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y011")) {
                pin11.setChecked(true);
                pin11.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y012")) {
                pin12.setChecked(true);
                pin12.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y013")) {
                pin13.setChecked(true);
                pin13.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y014")) {
                pin14.setChecked(true);
                pin14.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y015")) {
                pin15.setChecked(true);
                pin15.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y016")) {
                pin16.setChecked(true);
                pin16.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y017")) {
                pin17.setChecked(true);
                pin17.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y018")) {
                pin18.setChecked(true);
                pin18.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y019")) {
                pin19.setChecked(true);
                pin19.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y020")) {
                pin20.setChecked(true);
                pin20.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y021")) {
                pin21.setChecked(true);
                pin21.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y022")) {
                pin22.setChecked(true);
                pin22.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y023")) {
                pin23.setChecked(true);
                pin23.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y024")) {
                pin24.setChecked(true);
                pin24.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y025")) {
                pin25.setChecked(true);
                pin25.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y026")) {
                pin26.setChecked(true);
                pin26.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y027")) {
                pin27.setChecked(true);
                pin27.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y028")) {
                pin28.setChecked(true);
                pin28.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y029")) {
                pin29.setChecked(true);
                pin29.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y030")) {
                pin30.setChecked(true);
                pin30.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y031")) {
                pin31.setChecked(true);
                pin31.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y032")) {
                pin32.setChecked(true);
                pin32.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y033")) {
                pin33.setChecked(true);
                pin33.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y034")) {
                pin34.setChecked(true);
                pin34.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y035")) {
                pin35.setChecked(true);
                pin35.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y036")) {
                pin36.setChecked(true);
                pin36.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y037")) {
                pin37.setChecked(true);
                pin37.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y038")) {
                pin38.setChecked(true);
                pin38.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y039")) {
                pin39.setChecked(true);
                pin39.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y040")) {
                pin40.setChecked(true);
                pin40.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y041")) {
                pin41.setChecked(true);
                pin41.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y042")) {
                pin42.setChecked(true);
                pin42.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y043")) {
                pin43.setChecked(true);
                pin43.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y044")) {
                pin44.setChecked(true);
                pin44.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y045")) {
                pin45.setChecked(true);
                pin45.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y046")) {
                pin46.setChecked(true);
                pin46.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y047")) {
                pin47.setChecked(true);
                pin47.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y048")) {
                pin48.setChecked(true);
                pin48.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y049")) {
                pin49.setChecked(true);
                pin49.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y050")) {
                pin50.setChecked(true);
                pin50.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y051")) {
                pin51.setChecked(true);
                pin51.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y052")) {
                pin52.setChecked(true);
                pin52.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y053")) {
                pin53.setChecked(true);
                pin53.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y054")) {
                pin54.setChecked(true);
                pin54.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y055")) {
                pin55.setChecked(true);
                pin55.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y056")) {
                pin56.setChecked(true);
                pin56.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y057")) {
                pin57.setChecked(true);
                pin57.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y058")) {
                pin58.setChecked(true);
                pin58.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y059")) {
                pin59.setChecked(true);
                pin59.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y060")) {
                pin60.setChecked(true);
                pin60.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y061")) {
                pin61.setChecked(true);
                pin61.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y062")) {
                pin62.setChecked(true);
                pin62.setBackgroundColor(Color.parseColor("#80ff00"));
            }
            if (frame1.contains("$Y063")) {
                pin63.setChecked(true);
                pin63.setBackgroundColor(Color.parseColor("#80ff00"));
            }
        }
        if (sequenceCreationView.getText().toString().contains(frameNameF2.toString().replaceAll("(^\\[|\\])", ""))) {
            frame2 = frameFinished2.toString();
            if (frame2.contains("$Y000")) {
                pin0.setChecked(true);
                pin0.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y001")) {
                pin1.setChecked(true);
                pin1.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y002")) {
                pin2.setChecked(true);
                pin2.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y003")) {
                pin3.setChecked(true);
                pin3.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y004")) {
                pin4.setChecked(true);
                pin4.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y005")) {
                pin5.setChecked(true);
                pin5.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y006")) {
                pin6.setChecked(true);
                pin6.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y007")) {
                pin7.setChecked(true);
                pin7.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y008")) {
                pin8.setChecked(true);
                pin8.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y009")) {
                pin9.setChecked(true);
                pin9.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y010")) {
                pin10.setChecked(true);
                pin10.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y011")) {
                pin11.setChecked(true);
                pin11.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y012")) {
                pin12.setChecked(true);
                pin12.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y013")) {
                pin13.setChecked(true);
                pin13.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y014")) {
                pin14.setChecked(true);
                pin14.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y015")) {
                pin15.setChecked(true);
                pin15.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y016")) {
                pin16.setChecked(true);
                pin16.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y017")) {
                pin17.setChecked(true);
                pin17.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y018")) {
                pin18.setChecked(true);
                pin18.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y019")) {
                pin19.setChecked(true);
                pin19.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y020")) {
                pin20.setChecked(true);
                pin20.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y021")) {
                pin21.setChecked(true);
                pin21.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y022")) {
                pin22.setChecked(true);
                pin22.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y023")) {
                pin23.setChecked(true);
                pin23.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y024")) {
                pin24.setChecked(true);
                pin24.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y025")) {
                pin25.setChecked(true);
                pin25.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y026")) {
                pin26.setChecked(true);
                pin26.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y027")) {
                pin27.setChecked(true);
                pin27.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y028")) {
                pin28.setChecked(true);
                pin28.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y029")) {
                pin29.setChecked(true);
                pin29.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y030")) {
                pin30.setChecked(true);
                pin30.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y031")) {
                pin31.setChecked(true);
                pin31.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y032")) {
                pin32.setChecked(true);
                pin32.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y033")) {
                pin33.setChecked(true);
                pin33.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y034")) {
                pin34.setChecked(true);
                pin34.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y035")) {
                pin35.setChecked(true);
                pin35.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y036")) {
                pin36.setChecked(true);
                pin36.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y037")) {
                pin37.setChecked(true);
                pin37.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y038")) {
                pin38.setChecked(true);
                pin38.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y039")) {
                pin39.setChecked(true);
                pin39.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y040")) {
                pin40.setChecked(true);
                pin40.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y041")) {
                pin41.setChecked(true);
                pin41.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y042")) {
                pin42.setChecked(true);
                pin42.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y043")) {
                pin43.setChecked(true);
                pin43.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y044")) {
                pin44.setChecked(true);
                pin44.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y045")) {
                pin45.setChecked(true);
                pin45.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y046")) {
                pin46.setChecked(true);
                pin46.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y047")) {
                pin47.setChecked(true);
                pin47.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y048")) {
                pin48.setChecked(true);
                pin48.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y049")) {
                pin49.setChecked(true);
                pin49.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y050")) {
                pin50.setChecked(true);
                pin50.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y051")) {
                pin51.setChecked(true);
                pin51.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y052")) {
                pin52.setChecked(true);
                pin52.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y053")) {
                pin53.setChecked(true);
                pin53.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y054")) {
                pin54.setChecked(true);
                pin54.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y055")) {
                pin55.setChecked(true);
                pin55.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y056")) {
                pin56.setChecked(true);
                pin56.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y057")) {
                pin57.setChecked(true);
                pin57.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y058")) {
                pin58.setChecked(true);
                pin58.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y059")) {
                pin59.setChecked(true);
                pin59.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y060")) {
                pin60.setChecked(true);
                pin60.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y061")) {
                pin61.setChecked(true);
                pin61.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y062")) {
                pin62.setChecked(true);
                pin62.setBackgroundColor(Color.parseColor("#d000ff"));
            }
            if (frame2.contains("$Y063")) {
                pin63.setChecked(true);
                pin63.setBackgroundColor(Color.parseColor("#d000ff"));
            }
        }
        if (sequenceCreationView.getText().toString().contains(frameNameF3.toString().replaceAll("(^\\[|\\])", ""))) {
            frame3 = frameFinished3.toString();
            if (frame3.contains("$Y000")) {
                pin0.setChecked(true);
                pin0.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y001")) {
                pin1.setChecked(true);
                pin1.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y002")) {
                pin2.setChecked(true);
                pin2.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y003")) {
                pin3.setChecked(true);
                pin3.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y004")) {
                pin4.setChecked(true);
                pin4.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y005")) {
                pin5.setChecked(true);
                pin5.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y006")) {
                pin6.setChecked(true);
                pin6.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y007")) {
                pin7.setChecked(true);
                pin7.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y008")) {
                pin8.setChecked(true);
                pin8.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y009")) {
                pin9.setChecked(true);
                pin9.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y010")) {
                pin10.setChecked(true);
                pin10.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y011")) {
                pin11.setChecked(true);
                pin11.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y012")) {
                pin12.setChecked(true);
                pin12.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y013")) {
                pin13.setChecked(true);
                pin13.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y014")) {
                pin14.setChecked(true);
                pin14.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y015")) {
                pin15.setChecked(true);
                pin15.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y016")) {
                pin16.setChecked(true);
                pin16.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y017")) {
                pin17.setChecked(true);
                pin17.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y018")) {
                pin18.setChecked(true);
                pin18.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y019")) {
                pin19.setChecked(true);
                pin19.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y020")) {
                pin20.setChecked(true);
                pin20.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y021")) {
                pin21.setChecked(true);
                pin21.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y022")) {
                pin22.setChecked(true);
                pin22.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y023")) {
                pin23.setChecked(true);
                pin23.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y024")) {
                pin24.setChecked(true);
                pin24.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y025")) {
                pin25.setChecked(true);
                pin25.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y026")) {
                pin26.setChecked(true);
                pin26.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y027")) {
                pin27.setChecked(true);
                pin27.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y028")) {
                pin28.setChecked(true);
                pin28.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y029")) {
                pin29.setChecked(true);
                pin29.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y030")) {
                pin30.setChecked(true);
                pin30.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y031")) {
                pin31.setChecked(true);
                pin31.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y032")) {
                pin32.setChecked(true);
                pin32.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y033")) {
                pin33.setChecked(true);
                pin33.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y034")) {
                pin34.setChecked(true);
                pin34.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y035")) {
                pin35.setChecked(true);
                pin35.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y036")) {
                pin36.setChecked(true);
                pin36.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y037")) {
                pin37.setChecked(true);
                pin37.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y038")) {
                pin38.setChecked(true);
                pin38.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y039")) {
                pin39.setChecked(true);
                pin39.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y040")) {
                pin40.setChecked(true);
                pin40.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y041")) {
                pin41.setChecked(true);
                pin41.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y042")) {
                pin42.setChecked(true);
                pin42.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y043")) {
                pin43.setChecked(true);
                pin43.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y044")) {
                pin44.setChecked(true);
                pin44.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y045")) {
                pin45.setChecked(true);
                pin45.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y046")) {
                pin46.setChecked(true);
                pin46.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y047")) {
                pin47.setChecked(true);
                pin47.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y048")) {
                pin48.setChecked(true);
                pin48.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y049")) {
                pin49.setChecked(true);
                pin49.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y050")) {
                pin50.setChecked(true);
                pin50.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y051")) {
                pin51.setChecked(true);
                pin51.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y052")) {
                pin52.setChecked(true);
                pin52.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y053")) {
                pin53.setChecked(true);
                pin53.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y054")) {
                pin54.setChecked(true);
                pin54.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y055")) {
                pin55.setChecked(true);
                pin55.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y056")) {
                pin56.setChecked(true);
                pin56.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y057")) {
                pin57.setChecked(true);
                pin57.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y058")) {
                pin58.setChecked(true);
                pin58.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y059")) {
                pin59.setChecked(true);
                pin59.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y060")) {
                pin60.setChecked(true);
                pin60.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y061")) {
                pin61.setChecked(true);
                pin61.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y062")) {
                pin62.setChecked(true);
                pin62.setBackgroundColor(Color.parseColor("#a200ff"));
            }
            if (frame3.contains("$Y063")) {
                pin63.setChecked(true);
                pin63.setBackgroundColor(Color.parseColor("#a200ff"));
            }
        }
        if (sequenceCreationView.getText().toString().contains(frameNameF4.toString().replaceAll("(^\\[|\\])", ""))) {
            frame4 = frameFinished4.toString();
            if (frame4.contains("$Y000")) {
                pin0.setChecked(true);
                pin0.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y001")) {
                pin1.setChecked(true);
                pin1.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y002")) {
                pin2.setChecked(true);
                pin2.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y003")) {
                pin3.setChecked(true);
                pin3.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y004")) {
                pin4.setChecked(true);
                pin4.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y005")) {
                pin5.setChecked(true);
                pin5.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y006")) {
                pin6.setChecked(true);
                pin6.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y007")) {
                pin7.setChecked(true);
                pin7.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y008")) {
                pin8.setChecked(true);
                pin8.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y009")) {
                pin9.setChecked(true);
                pin9.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y010")) {
                pin10.setChecked(true);
                pin10.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y011")) {
                pin11.setChecked(true);
                pin11.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y012")) {
                pin12.setChecked(true);
                pin12.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y013")) {
                pin13.setChecked(true);
                pin13.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y014")) {
                pin14.setChecked(true);
                pin14.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y015")) {
                pin15.setChecked(true);
                pin15.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y016")) {
                pin16.setChecked(true);
                pin16.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y017")) {
                pin17.setChecked(true);
                pin17.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y018")) {
                pin18.setChecked(true);
                pin18.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y019")) {
                pin19.setChecked(true);
                pin19.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y020")) {
                pin20.setChecked(true);
                pin20.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y021")) {
                pin21.setChecked(true);
                pin21.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y022")) {
                pin22.setChecked(true);
                pin22.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y023")) {
                pin23.setChecked(true);
                pin23.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y024")) {
                pin24.setChecked(true);
                pin24.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y025")) {
                pin25.setChecked(true);
                pin25.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y026")) {
                pin26.setChecked(true);
                pin26.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y027")) {
                pin27.setChecked(true);
                pin27.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y028")) {
                pin28.setChecked(true);
                pin28.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y029")) {
                pin29.setChecked(true);
                pin29.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y030")) {
                pin30.setChecked(true);
                pin30.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y031")) {
                pin31.setChecked(true);
                pin31.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y032")) {
                pin32.setChecked(true);
                pin32.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y033")) {
                pin33.setChecked(true);
                pin33.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y034")) {
                pin34.setChecked(true);
                pin34.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y035")) {
                pin35.setChecked(true);
                pin35.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y036")) {
                pin36.setChecked(true);
                pin36.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y037")) {
                pin37.setChecked(true);
                pin37.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y038")) {
                pin38.setChecked(true);
                pin38.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y039")) {
                pin39.setChecked(true);
                pin39.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y040")) {
                pin40.setChecked(true);
                pin40.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y041")) {
                pin41.setChecked(true);
                pin41.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y042")) {
                pin42.setChecked(true);
                pin42.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y043")) {
                pin43.setChecked(true);
                pin43.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y044")) {
                pin44.setChecked(true);
                pin44.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y045")) {
                pin45.setChecked(true);
                pin45.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y046")) {
                pin46.setChecked(true);
                pin46.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y047")) {
                pin47.setChecked(true);
                pin47.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y048")) {
                pin48.setChecked(true);
                pin48.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y049")) {
                pin49.setChecked(true);
                pin49.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y050")) {
                pin50.setChecked(true);
                pin50.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y051")) {
                pin51.setChecked(true);
                pin51.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y052")) {
                pin52.setChecked(true);
                pin52.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y053")) {
                pin53.setChecked(true);
                pin53.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y054")) {
                pin54.setChecked(true);
                pin54.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y055")) {
                pin55.setChecked(true);
                pin55.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y056")) {
                pin56.setChecked(true);
                pin56.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y057")) {
                pin57.setChecked(true);
                pin57.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y058")) {
                pin58.setChecked(true);
                pin58.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y059")) {
                pin59.setChecked(true);
                pin59.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y060")) {
                pin60.setChecked(true);
                pin60.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y061")) {
                pin61.setChecked(true);
                pin61.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y062")) {
                pin62.setChecked(true);
                pin62.setBackgroundColor(Color.parseColor("#6600ff"));
            }
            if (frame4.contains("$Y063")) {
                pin63.setChecked(true);
                pin63.setBackgroundColor(Color.parseColor("#6600ff"));
            }
        }
        if (sequenceCreationView.getText().toString().contains(frameNameF5.toString().replaceAll("(^\\[|\\])", ""))) {
            frame5 = frameFinished5.toString();
            if (frame5.contains("$Y000")) {
                pin0.setChecked(true);
                pin0.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y001")) {
                pin1.setChecked(true);
                pin1.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y002")) {
                pin2.setChecked(true);
                pin2.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y003")) {
                pin3.setChecked(true);
                pin3.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y004")) {
                pin4.setChecked(true);
                pin4.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y005")) {
                pin5.setChecked(true);
                pin5.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y006")) {
                pin6.setChecked(true);
                pin6.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y007")) {
                pin7.setChecked(true);
                pin7.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y008")) {
                pin8.setChecked(true);
                pin8.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y009")) {
                pin9.setChecked(true);
                pin9.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y010")) {
                pin10.setChecked(true);
                pin10.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y011")) {
                pin11.setChecked(true);
                pin11.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y012")) {
                pin12.setChecked(true);
                pin12.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y013")) {
                pin13.setChecked(true);
                pin13.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y014")) {
                pin14.setChecked(true);
                pin14.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y015")) {
                pin15.setChecked(true);
                pin15.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y016")) {
                pin16.setChecked(true);
                pin16.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y017")) {
                pin17.setChecked(true);
                pin17.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y018")) {
                pin18.setChecked(true);
                pin18.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y019")) {
                pin19.setChecked(true);
                pin19.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y020")) {
                pin20.setChecked(true);
                pin20.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y021")) {
                pin21.setChecked(true);
                pin21.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y022")) {
                pin22.setChecked(true);
                pin22.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y023")) {
                pin23.setChecked(true);
                pin23.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y024")) {
                pin24.setChecked(true);
                pin24.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y025")) {
                pin25.setChecked(true);
                pin25.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y026")) {
                pin26.setChecked(true);
                pin26.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y027")) {
                pin27.setChecked(true);
                pin27.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y028")) {
                pin28.setChecked(true);
                pin28.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y029")) {
                pin29.setChecked(true);
                pin29.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y030")) {
                pin30.setChecked(true);
                pin30.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y031")) {
                pin31.setChecked(true);
                pin31.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y032")) {
                pin32.setChecked(true);
                pin32.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y033")) {
                pin33.setChecked(true);
                pin33.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y034")) {
                pin34.setChecked(true);
                pin34.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y035")) {
                pin35.setChecked(true);
                pin35.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y036")) {
                pin36.setChecked(true);
                pin36.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y037")) {
                pin37.setChecked(true);
                pin37.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y038")) {
                pin38.setChecked(true);
                pin38.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y039")) {
                pin39.setChecked(true);
                pin39.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y040")) {
                pin40.setChecked(true);
                pin40.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y041")) {
                pin41.setChecked(true);
                pin41.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y042")) {
                pin42.setChecked(true);
                pin42.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y043")) {
                pin43.setChecked(true);
                pin43.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y044")) {
                pin44.setChecked(true);
                pin44.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y045")) {
                pin45.setChecked(true);
                pin45.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y046")) {
                pin46.setChecked(true);
                pin46.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y047")) {
                pin47.setChecked(true);
                pin47.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y048")) {
                pin48.setChecked(true);
                pin48.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y049")) {
                pin49.setChecked(true);
                pin49.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y050")) {
                pin50.setChecked(true);
                pin50.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y051")) {
                pin51.setChecked(true);
                pin51.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y052")) {
                pin52.setChecked(true);
                pin52.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y053")) {
                pin53.setChecked(true);
                pin53.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y054")) {
                pin54.setChecked(true);
                pin54.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y055")) {
                pin55.setChecked(true);
                pin55.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y056")) {
                pin56.setChecked(true);
                pin56.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y057")) {
                pin57.setChecked(true);
                pin57.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y058")) {
                pin58.setChecked(true);
                pin58.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y059")) {
                pin59.setChecked(true);
                pin59.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y060")) {
                pin60.setChecked(true);
                pin60.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y061")) {
                pin61.setChecked(true);
                pin61.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y062")) {
                pin62.setChecked(true);
                pin62.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
            if (frame5.contains("$Y063")) {
                pin63.setChecked(true);
                pin63.setBackgroundColor(Color.parseColor("#00b3ff"));
            }
        }
        if (sequenceCreationView.getText().toString().contains(frameNameF6.toString().replaceAll("(^\\[|\\])", ""))) {
            frame6 = frameFinished6.toString();
            if (frame6.contains("$Y000")) {
                pin0.setChecked(true);
                pin0.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y001")) {
                pin1.setChecked(true);
                pin1.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y002")) {
                pin2.setChecked(true);
                pin2.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y003")) {
                pin3.setChecked(true);
                pin3.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y004")) {
                pin4.setChecked(true);
                pin4.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y005")) {
                pin5.setChecked(true);
                pin5.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y006")) {
                pin6.setChecked(true);
                pin6.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y007")) {
                pin7.setChecked(true);
                pin7.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y008")) {
                pin8.setChecked(true);
                pin8.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y009")) {
                pin9.setChecked(true);
                pin9.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y010")) {
                pin10.setChecked(true);
                pin10.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y011")) {
                pin11.setChecked(true);
                pin11.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y012")) {
                pin12.setChecked(true);
                pin12.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y013")) {
                pin13.setChecked(true);
                pin13.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y014")) {
                pin14.setChecked(true);
                pin14.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y015")) {
                pin15.setChecked(true);
                pin15.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y016")) {
                pin16.setChecked(true);
                pin16.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y017")) {
                pin17.setChecked(true);
                pin17.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y018")) {
                pin18.setChecked(true);
                pin18.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y019")) {
                pin19.setChecked(true);
                pin19.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y020")) {
                pin20.setChecked(true);
                pin20.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y021")) {
                pin21.setChecked(true);
                pin21.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y022")) {
                pin22.setChecked(true);
                pin22.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y023")) {
                pin23.setChecked(true);
                pin23.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y024")) {
                pin24.setChecked(true);
                pin24.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y025")) {
                pin25.setChecked(true);
                pin25.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y026")) {
                pin26.setChecked(true);
                pin26.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y027")) {
                pin27.setChecked(true);
                pin27.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y028")) {
                pin28.setChecked(true);
                pin28.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y029")) {
                pin29.setChecked(true);
                pin29.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y030")) {
                pin30.setChecked(true);
                pin30.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y031")) {
                pin31.setChecked(true);
                pin31.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y032")) {
                pin32.setChecked(true);
                pin32.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y033")) {
                pin33.setChecked(true);
                pin33.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y034")) {
                pin34.setChecked(true);
                pin34.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y035")) {
                pin35.setChecked(true);
                pin35.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y036")) {
                pin36.setChecked(true);
                pin36.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y037")) {
                pin37.setChecked(true);
                pin37.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y038")) {
                pin38.setChecked(true);
                pin38.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y039")) {
                pin39.setChecked(true);
                pin39.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y040")) {
                pin40.setChecked(true);
                pin40.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y041")) {
                pin41.setChecked(true);
                pin41.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y042")) {
                pin42.setChecked(true);
                pin42.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y043")) {
                pin43.setChecked(true);
                pin43.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y044")) {
                pin44.setChecked(true);
                pin44.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y045")) {
                pin45.setChecked(true);
                pin45.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y046")) {
                pin46.setChecked(true);
                pin46.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y047")) {
                pin47.setChecked(true);
                pin47.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y048")) {
                pin48.setChecked(true);
                pin48.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y049")) {
                pin49.setChecked(true);
                pin49.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y050")) {
                pin50.setChecked(true);
                pin50.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y051")) {
                pin51.setChecked(true);
                pin51.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y052")) {
                pin52.setChecked(true);
                pin52.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y053")) {
                pin53.setChecked(true);
                pin53.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y054")) {
                pin54.setChecked(true);
                pin54.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y055")) {
                pin55.setChecked(true);
                pin55.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y056")) {
                pin56.setChecked(true);
                pin56.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y057")) {
                pin57.setChecked(true);
                pin57.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y058")) {
                pin58.setChecked(true);
                pin58.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y059")) {
                pin59.setChecked(true);
                pin59.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y060")) {
                pin60.setChecked(true);
                pin60.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y061")) {
                pin61.setChecked(true);
                pin61.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y062")) {
                pin62.setChecked(true);
                pin62.setBackgroundColor(Color.parseColor("#00fff2"));
            }
            if (frame6.contains("$Y063")) {
                pin63.setChecked(true);
                pin63.setBackgroundColor(Color.parseColor("#00fff2"));
            }
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
     * Resets the frames used whilst creating a sequence
     *
     * @param view
     */
    public void resetTheSequence(View view) {
        frameAmount = findViewById(R.id.frameSelection);
        setFrame = findViewById(R.id.setFrames);
        framesUsedCounter = findViewById(R.id.framesUsedCounter);

        framesUsedCounter = findViewById(R.id.framesUsedCounter);
        framesUsedCounter.setTextColor(Color.parseColor("#06F326"));

        pin0.setChecked(false);
        pin1.setChecked(false);
        pin2.setChecked(false);
        pin3.setChecked(false);
        pin4.setChecked(false);
        pin5.setChecked(false);
        pin6.setChecked(false);
        pin7.setChecked(false);
        pin8.setChecked(false);
        pin9.setChecked(false);
        pin10.setChecked(false);
        pin11.setChecked(false);
        pin12.setChecked(false);
        pin13.setChecked(false);
        pin14.setChecked(false);
        pin15.setChecked(false);
        pin16.setChecked(false);
        pin17.setChecked(false);
        pin18.setChecked(false);
        pin19.setChecked(false);
        pin20.setChecked(false);
        pin21.setChecked(false);
        pin22.setChecked(false);
        pin23.setChecked(false);
        pin24.setChecked(false);
        pin25.setChecked(false);
        pin26.setChecked(false);
        pin27.setChecked(false);
        pin28.setChecked(false);
        pin29.setChecked(false);
        pin30.setChecked(false);
        pin31.setChecked(false);
        pin32.setChecked(false);
        pin33.setChecked(false);
        pin34.setChecked(false);
        pin35.setChecked(false);
        pin36.setChecked(false);
        pin37.setChecked(false);
        pin38.setChecked(false);
        pin39.setChecked(false);
        pin40.setChecked(false);
        pin41.setChecked(false);
        pin42.setChecked(false);
        pin43.setChecked(false);
        pin44.setChecked(false);
        pin45.setChecked(false);
        pin46.setChecked(false);
        pin47.setChecked(false);
        pin48.setChecked(false);
        pin49.setChecked(false);
        pin50.setChecked(false);
        pin51.setChecked(false);
        pin52.setChecked(false);
        pin53.setChecked(false);
        pin54.setChecked(false);
        pin55.setChecked(false);
        pin56.setChecked(false);
        pin57.setChecked(false);
        pin58.setChecked(false);
        pin59.setChecked(false);
        pin60.setChecked(false);
        pin61.setChecked(false);
        pin62.setChecked(false);
        pin63.setChecked(false);

        pin0.setBackgroundColor(Color.TRANSPARENT);
        pin1.setBackgroundColor(Color.TRANSPARENT);
        pin2.setBackgroundColor(Color.TRANSPARENT);
        pin3.setBackgroundColor(Color.TRANSPARENT);
        pin4.setBackgroundColor(Color.TRANSPARENT);
        pin5.setBackgroundColor(Color.TRANSPARENT);
        pin6.setBackgroundColor(Color.TRANSPARENT);
        pin7.setBackgroundColor(Color.TRANSPARENT);
        pin8.setBackgroundColor(Color.TRANSPARENT);
        pin9.setBackgroundColor(Color.TRANSPARENT);
        pin10.setBackgroundColor(Color.TRANSPARENT);
        pin11.setBackgroundColor(Color.TRANSPARENT);
        pin12.setBackgroundColor(Color.TRANSPARENT);
        pin13.setBackgroundColor(Color.TRANSPARENT);
        pin14.setBackgroundColor(Color.TRANSPARENT);
        pin15.setBackgroundColor(Color.TRANSPARENT);
        pin16.setBackgroundColor(Color.TRANSPARENT);
        pin17.setBackgroundColor(Color.TRANSPARENT);
        pin18.setBackgroundColor(Color.TRANSPARENT);
        pin19.setBackgroundColor(Color.TRANSPARENT);
        pin20.setBackgroundColor(Color.TRANSPARENT);
        pin21.setBackgroundColor(Color.TRANSPARENT);
        pin22.setBackgroundColor(Color.TRANSPARENT);
        pin23.setBackgroundColor(Color.TRANSPARENT);
        pin24.setBackgroundColor(Color.TRANSPARENT);
        pin25.setBackgroundColor(Color.TRANSPARENT);
        pin26.setBackgroundColor(Color.TRANSPARENT);
        pin27.setBackgroundColor(Color.TRANSPARENT);
        pin28.setBackgroundColor(Color.TRANSPARENT);
        pin29.setBackgroundColor(Color.TRANSPARENT);
        pin30.setBackgroundColor(Color.TRANSPARENT);
        pin31.setBackgroundColor(Color.TRANSPARENT);
        pin32.setBackgroundColor(Color.TRANSPARENT);
        pin33.setBackgroundColor(Color.TRANSPARENT);
        pin34.setBackgroundColor(Color.TRANSPARENT);
        pin35.setBackgroundColor(Color.TRANSPARENT);
        pin36.setBackgroundColor(Color.TRANSPARENT);
        pin37.setBackgroundColor(Color.TRANSPARENT);
        pin38.setBackgroundColor(Color.TRANSPARENT);
        pin39.setBackgroundColor(Color.TRANSPARENT);
        pin40.setBackgroundColor(Color.TRANSPARENT);
        pin41.setBackgroundColor(Color.TRANSPARENT);
        pin42.setBackgroundColor(Color.TRANSPARENT);
        pin43.setBackgroundColor(Color.TRANSPARENT);
        pin44.setBackgroundColor(Color.TRANSPARENT);
        pin45.setBackgroundColor(Color.TRANSPARENT);
        pin46.setBackgroundColor(Color.TRANSPARENT);
        pin47.setBackgroundColor(Color.TRANSPARENT);
        pin48.setBackgroundColor(Color.TRANSPARENT);
        pin49.setBackgroundColor(Color.TRANSPARENT);
        pin50.setBackgroundColor(Color.TRANSPARENT);
        pin51.setBackgroundColor(Color.TRANSPARENT);
        pin52.setBackgroundColor(Color.TRANSPARENT);
        pin53.setBackgroundColor(Color.TRANSPARENT);
        pin54.setBackgroundColor(Color.TRANSPARENT);
        pin55.setBackgroundColor(Color.TRANSPARENT);
        pin56.setBackgroundColor(Color.TRANSPARENT);
        pin57.setBackgroundColor(Color.TRANSPARENT);
        pin58.setBackgroundColor(Color.TRANSPARENT);
        pin59.setBackgroundColor(Color.TRANSPARENT);
        pin60.setBackgroundColor(Color.TRANSPARENT);
        pin61.setBackgroundColor(Color.TRANSPARENT);
        pin62.setBackgroundColor(Color.TRANSPARENT);
        pin63.setBackgroundColor(Color.TRANSPARENT);

        setFrame.setEnabled(true);
        frameAmount.setEnabled(true);
        selectRepAmount.setEnabled(false);
        setAmountOfRep.setEnabled(false);
        frameSelections.clear();
        totalFrames.clear();
        frameCounting = 0;
        framesUsedCounter.setText(String.valueOf(frameCounting));

        String finial_commands = "";
        String finial_selection = "";

        TextView t = findViewById(R.id.sequenceCreationView);
        for (Object Sequence : commandInformationSeq) {
            finial_commands = finial_commands + Sequence + "\n";
        }
        for (Object Selections : frameSelections) {
            finial_selection = finial_selection + Selections + "\n";
        }
        t.setText(finial_commands + finial_selection);
    }

    /**
     * Method to save the sequence (sequence)
     */
    private void saveSequenceData() {
        nameOfSequence = findViewById(R.id.nameSequence);
        setSequenceName = findViewById(R.id.setSequenceName);

        resetFramesUsed = findViewById(R.id.resetFramesUsed);

        saveTheSequence = findViewById(R.id.saveSequence);

        frameAmount = findViewById(R.id.frameSelection);
        setFrame = findViewById(R.id.setFrames);
        selectRepAmount = findViewById(R.id.selectRepetitionAmount);
        setAmountOfRep = findViewById(R.id.setRepetitionAmount);
        sequenceCreationView = findViewById(R.id.sequenceCreationView);

        frameCounter = findViewById(R.id.frameCounter);
        framesUsedCounter = findViewById(R.id.framesUsedCounter);

        findViewById(R.id.firstFrame).setVisibility(View.INVISIBLE);
        findViewById(R.id.secondFrame).setVisibility(View.INVISIBLE);
        findViewById(R.id.thirdFrame).setVisibility(View.INVISIBLE);
        findViewById(R.id.fourthFrame).setVisibility(View.INVISIBLE);
        findViewById(R.id.fifthFrame).setVisibility(View.INVISIBLE);
        findViewById(R.id.sixthFrame).setVisibility(View.INVISIBLE);
        findViewById(R.id.frameUsedLimit).setVisibility(View.INVISIBLE);

        String commandInfoSeqReplace;
        String frameSelectReplace;
        String combined;

        numOfFramesSelected = findViewById(R.id.setNumFrames);
        String framesSelected = numOfFramesSelected.getSelectedItem().toString();
        int frameLimit = Integer.parseInt(framesSelected);
        if (totalFrames.size() == frameLimit) {
            if (sequenceFinished1.isEmpty()) {
                frameSelections.add("$T001");
                commandInfoSeqReplace = commandInformationSeq.toString().replaceAll("(^\\[|\\])", "");
                frameSelectReplace = frameSelections.toString().replaceAll("(^\\[|\\])", "\n");
                combined = frameSelectReplace.replaceAll("(^\\[|\\])", "");
                sequenceFinished1.add(commandInfoSeqReplace + combined);
                firstSequence = true;
                Toast.makeText(getApplicationContext(), "frame select " + frameSelectReplace, Toast.LENGTH_SHORT).show();
            } else if (sequenceFinished2.isEmpty()) {
                frameSelections.add("$T001");
                commandInfoSeqReplace = commandInformationSeq.toString().replaceAll("(^\\[|\\])", "");
                frameSelectReplace = frameSelections.toString().replaceAll("(^\\[|\\])", "\n");
                combined = frameSelectReplace.replaceAll("(^\\[|\\])", "");
                sequenceFinished2.add(commandInfoSeqReplace + combined);
                secondSequence = true;
                Toast.makeText(getApplicationContext(), "Sequence Saved " + sequenceFinished2, Toast.LENGTH_SHORT).show();
            } else if (sequenceFinished3.isEmpty()) {
                frameSelections.add("$T001");
                commandInfoSeqReplace = commandInformationSeq.toString().replaceAll("(^\\[|\\])", "");
                frameSelectReplace = frameSelections.toString().replaceAll("(^\\[|\\])", "\n");
                combined = frameSelectReplace.replaceAll("(^\\[|\\])", "");
                sequenceFinished3.add(commandInfoSeqReplace + combined);
                thirdSequence = true;
                Toast.makeText(getApplicationContext(), "Sequence Saved " + sequenceFinished3, Toast.LENGTH_SHORT).show();
            } else if (sequenceFinished4.isEmpty()) {
                frameSelections.add("$T001");
                commandInfoSeqReplace = commandInformationSeq.toString().replaceAll("(^\\[|\\])", "");
                frameSelectReplace = frameSelections.toString().replaceAll("(^\\[|\\])", "\n");
                combined = frameSelectReplace.replaceAll("(^\\[|\\])", "");
                sequenceFinished4.add(commandInfoSeqReplace + combined);
                fourthSequence = true;
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
            resetFramesUsed.setVisibility(View.INVISIBLE);
            frameAmount.setVisibility(View.INVISIBLE);
            selectRepAmount.setVisibility(View.INVISIBLE);
            setAmountOfRep.setVisibility(View.INVISIBLE);
            sequenceCreationView.setVisibility(View.INVISIBLE);
            saveTheSequence.setVisibility(View.INVISIBLE);
            frameCounter.setVisibility(View.INVISIBLE);
            framesUsedCounter.setVisibility(View.INVISIBLE);
            frameCounting = 0;
            framesUsedCounter.setText(String.valueOf(frameCounting));

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
            Toast.makeText(getApplicationContext(), "Please add more frames to make your selection of  " + frameLimit, Toast.LENGTH_SHORT).show();
        }
    }

    public void saveTheSequence(View view) {
        saveSequenceData();
    }

}