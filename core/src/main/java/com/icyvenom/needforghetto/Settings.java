package com.icyvenom.needforghetto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by amar__000 on 2015-06-30.
 */
public class Settings {
    private static String controlType;

    public final static FileHandle SAVE_LOCATION = Gdx.files.local("settings/");

    public static void setControlType(String controlType){
        Settings.controlType = controlType;
        SaveControlType();
    }

    public static void SaveControlType(){
        String fileName = "Settings.txt";
        FileHandle handle = SAVE_LOCATION.child(fileName);
        if(handle.exists()){
            String allSettings = handle.readString();
            allSettings.substring(1);
            if(handle.readString().contains("#")){
                //ControlType setting will always be first in the textfile.
                allSettings.substring(allSettings.indexOf("#"));
                allSettings = "#ControlType=" + Settings.controlType + allSettings;
                handle.writeString(allSettings, false);
            }
            else{
                allSettings = "#ControlType=" + Settings.controlType;
                handle.writeString(allSettings, false);
            }

        }
        else{
            handle.writeString("#ControlType=" + Settings.controlType, false);
        }
    }

    public static String LoadControlType(){
        
        return ;
    }
}
