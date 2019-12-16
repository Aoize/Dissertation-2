package com.example.hapticfeedback;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.hapticfeedback.ui.frame.FrameFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;
    Button setCoils, setDelay, setOnTime;
    Spinner numOfCoils, amountOfDelay, amountOfOnTime;

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
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //Overall pin selection data
    List<Object> pinSelection = new ArrayList<>();
    //Overall command data
    List<Object> commandInformation = new ArrayList<>();
    //Coils used data
    List<Object> coilsUsed = new ArrayList<>();

    List<Object> frameFinished = new ArrayList<>();
    public void pin(View view){
        String finial_commands = "";
        String finial_selection = "";
        boolean checked = ((CheckBox) view).isChecked();
        TextView t = (TextView) findViewById(R.id.frameCreationView);

        //Moved when name has been made
        for(Object Commands : commandInformation){
            finial_commands = finial_commands + Commands + "\n";
        }

        switch(view.getId()){
            case R.id.pin0:
                if(checked){
                    pinSelection.add("$Y000");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y000");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin1:
                if(checked){
                    pinSelection.add("$Y001");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y001");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin2:
                if(checked){
                    pinSelection.add("$Y002");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y002");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin3:
                if(checked){
                    pinSelection.add("$Y003");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y003");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin4:
                if(checked){
                    pinSelection.add("$Y004");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y004");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin5:
                if(checked){
                    pinSelection.add("$Y005");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y005");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin6:
                if(checked){
                    pinSelection.add("$Y006");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y006");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin7:
                if(checked){
                    pinSelection.add("$Y007");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y007");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin8:
                if(checked){
                    pinSelection.add("$Y008");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y008");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin9:
                if(checked){
                    pinSelection.add("$Y009");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y009");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin10:
                if(checked){
                    pinSelection.add("$Y010");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y010");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin11:
                if(checked){
                    pinSelection.add("$Y011");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y011");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin12:
                if(checked){
                    pinSelection.add("$Y012");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y012");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin13:
                if(checked){
                    pinSelection.add("$Y013");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y013");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin14:
                if(checked){
                    pinSelection.add("$Y014");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y014");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin15:
                if(checked){
                    pinSelection.add("$Y015");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y015");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin16:
                if(checked){
                    pinSelection.add("$Y016");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y016");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin17:
                if(checked){
                    pinSelection.add("$Y017");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y017");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin18:
                if(checked){
                    pinSelection.add("$Y018");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y018");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin19:
                if(checked){
                    pinSelection.add("$Y019");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y019");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin20:
                if(checked){
                    pinSelection.add("$Y020");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y020");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin21:
                if(checked){
                    pinSelection.add("$Y021");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y021");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin22:
                if(checked){
                    pinSelection.add("$Y022");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y022");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin23:
                if(checked){
                    pinSelection.add("$Y023");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y023");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin24:
                if(checked){
                    pinSelection.add("$Y024");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y024");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin25:
                if(checked){
                    pinSelection.add("$Y025");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y025");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin26:
                if(checked){
                    pinSelection.add("$Y026");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y026");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin27:
                if(checked){
                    pinSelection.add("$Y027");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y027");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin28:
                if(checked){
                    pinSelection.add("$Y028");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y028");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin29:
                if(checked){
                    pinSelection.add("$Y029");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y029");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin30:
                if(checked){
                    pinSelection.add("$Y030");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y030");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin31:
                if(checked){
                    pinSelection.add("$Y031");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y031");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin32:
                if(checked){
                    pinSelection.add("$Y032");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y032");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin33:
                if(checked){
                    pinSelection.add("$Y033");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y033");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin34:
                if(checked){
                    pinSelection.add("$Y034");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y034");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin35:
                if(checked){
                    pinSelection.add("$Y035");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y035");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin36:
                if(checked){
                    pinSelection.add("$Y036");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y036");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin37:
                if(checked){
                    pinSelection.add("$Y037");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y037");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin38:
                if(checked){
                    pinSelection.add("$Y038");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y038");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin39:
                if(checked){
                    pinSelection.add("$Y039");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y039");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin40:
                if(checked){
                    pinSelection.add("$Y040");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y040");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin41:
                if(checked){
                    pinSelection.add("$Y041");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y041");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin42:
                if(checked){
                    pinSelection.add("$Y042");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y042");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin43:
                if(checked){
                    pinSelection.add("$Y043");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y043");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin44:
                if(checked){
                    pinSelection.add("$Y044");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y044");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin45:
                if(checked){
                    pinSelection.add("$Y045");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y045");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin46:
                if(checked){
                    pinSelection.add("$Y046");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y046");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin47:
                if(checked){
                    pinSelection.add("$Y047");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y047");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin48:
                if(checked){
                    pinSelection.add("$Y048");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y048");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin49:
                if(checked){
                    pinSelection.add("$Y049");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y049");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin50:
                if(checked){
                    pinSelection.add("$Y050");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y050");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin51:
                if(checked){
                    pinSelection.add("$Y051");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y051");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin52:
                if(checked){
                    pinSelection.add("$Y052");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y052");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin53:
                if(checked){
                    pinSelection.add("$Y053");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y053");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin54:
                if(checked){
                    pinSelection.add("$Y054");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y054");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin55:
                if(checked){
                    pinSelection.add("$Y055");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y055");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin56:
                if(checked){
                    pinSelection.add("$Y056");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y056");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin57:
                if(checked){
                    pinSelection.add("$Y057");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y057");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin58:
                if(checked){
                    pinSelection.add("$Y058");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y058");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin59:
                if(checked){
                    pinSelection.add("$Y059");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y059");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin60:
                if(checked){
                    pinSelection.add("$Y060");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y060");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin61:
                if(checked){
                    pinSelection.add("$Y061");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y061");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin62:
                if(checked){
                    pinSelection.add("$Y062");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y062");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
            case R.id.pin63:
                if(checked){
                    pinSelection.add("$Y063");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }else{
                    pinSelection.remove("$Y063");
                    for(Object Selections : pinSelection){
                        finial_selection = finial_selection + Selections + "\n";
                    }
                    t.setText(finial_commands + finial_selection);
                }
                break;
        }
    }

    public void saveTheFrame(View view) {
        saveData();
    }

    //TODO Delete this after testing
    public void resetTheFrame(View view) {
        Toast.makeText(getApplicationContext(),commandInformation.toString() + pinSelection.toString(),Toast.LENGTH_SHORT).show();


/*        pinSelection.clear();
        commandInformation.clear();
        frameFinished.clear();
        TextView t = (TextView) findViewById(R.id.frameCreationView);
        t.setText("");
        setCoils.findViewById(R.id.setCoils).setVisibility(View.VISIBLE);
        coilAmount.findViewById(R.id.coilAmount).setVisibility(View.VISIBLE);*/
    }

       public void setTheCoils(View view) {
        TextView t = (TextView) findViewById(R.id.frameCreationView);
        String start = "";
        numOfCoils = findViewById(R.id.numberOfCoils);
        setCoils = findViewById(R.id.setCoils);
        amountOfDelay = findViewById(R.id.amountOfDelay);
        setDelay = findViewById(R.id.setDelay);

        //TODO Move when the user can select a name
        commandInformation.add(0,"$F000");

        String content = numOfCoils.getSelectedItem().toString();
        commandInformation.add(1,"$N00" + content);
      //Shows the first command after click
        for(Object Selections : commandInformation){
            start = start + Selections + "\n";
        }
        t.setText(start);

        //TODO Ensure that something happens here as a check to save the frame
        //Saves to a seperate array, maybe for saving?
        coilsUsed.add(content);

        if(commandInformation.add(true)){
            setCoils.findViewById(R.id.setCoils).setVisibility(View.INVISIBLE);
            numOfCoils.findViewById(R.id.numberOfCoils).setVisibility(View.INVISIBLE);
            commandInformation.remove(true);
            setDelay.findViewById(R.id.setDelay).setVisibility(View.VISIBLE);
            amountOfDelay.findViewById(R.id.amountOfDelay).setVisibility(View.VISIBLE);
        }return;

/*      setCoils = findViewById(R.id.setCoils);
        commandInformation.add(0,"$F000");
        coilAmount = findViewById(R.id.coilAmount);
        String content = coilAmount.getText().toString();

        commandInformation.add(1,"$N00" + content);
        if(commandInformation.add(true)){
            setCoils.findViewById(R.id.setCoils).setVisibility(View.GONE);
            coilAmount.findViewById(R.id.coilAmount).setVisibility(View.GONE);
            commandInformation.remove(true);
        } return;*/
    }

    public void setTheDelay(View view) {
        amountOfDelay = findViewById(R.id.amountOfDelay);
        setDelay = findViewById(R.id.setDelay);
        amountOfOnTime = findViewById(R.id.amountOfOnTime);
        setOnTime = findViewById(R.id.setOnTime);

        String content = amountOfDelay.getSelectedItem().toString();
        commandInformation.add(2,"$D0" + content);

        TextView t = (TextView) findViewById(R.id.frameCreationView);
        String start = "";
        for(Object Selections : commandInformation){
            start = start + Selections + "\n";
        }
        t.setText(start);

        //TODO Set it so if "setTheCoils" has disiapeared, set this to reappear

        if(commandInformation.add(true)){

            setDelay.findViewById(R.id.setDelay).setVisibility(View.INVISIBLE);
            amountOfDelay.findViewById(R.id.amountOfDelay).setVisibility(View.INVISIBLE);
            commandInformation.remove(true);
            setOnTime.findViewById(R.id.setOnTime).setVisibility(View.VISIBLE);
            amountOfOnTime.findViewById(R.id.amountOfOnTime).setVisibility(View.VISIBLE);
        }return;
    }

    public void setTheOnTime(View view) {
        amountOfOnTime = findViewById(R.id.amountOfOnTime);
        setOnTime = findViewById(R.id.setOnTime);

        String content = amountOfOnTime.getSelectedItem().toString();
        commandInformation.add(3,"$P0" + content);

        TextView t = (TextView) findViewById(R.id.frameCreationView);
        String start = "";
        for(Object Selections : commandInformation){
            start = start + Selections + "\n";
        }
        t.setText(start);

        //TODO Set it so if "setTheCoils" has disiapeared, set this to reappear

        if(commandInformation.add(true)){
            setOnTime.findViewById(R.id.setOnTime).setVisibility(View.INVISIBLE);
            amountOfOnTime.findViewById(R.id.amountOfOnTime).setVisibility(View.INVISIBLE);
            commandInformation.remove(true);
        }return;
    }

    private void saveData(){
       // String content = spinner.getSelectedItem().toString();
        //TODO Fix this saving method
/*        if(pinSelection.toString() != content){
            Toast.makeText(getApplicationContext(),"Please remove some coils",Toast.LENGTH_SHORT).show();
        }else{*/
/*            frameFinished.add(commandInformation);
            frameFinished.add(pinSelection);*/

            SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(frameFinished);
            editor.putString("task list", json);
            editor.apply();
            Toast.makeText(getApplicationContext(),"Frame Saved",Toast.LENGTH_SHORT).show();
        //}
    }


 /*   private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", "");
        Type type = new TypeToken<ArrayList<Object>>() {}.getType();
        ArrayList<Object> selection = gson.fromJson(json, type);
    }*/
}
