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
        saveControlType();
    }

    public static void saveControlType(){
        String fileName = "Settings.txt";
        FileHandle handle = SAVE_LOCATION.child(fileName);
        if(handle.exists()){
            String allSettings = handle.readString();
            allSettings = allSettings.substring(1);
            if(allSettings.contains("#")){
                //ControlType setting will always be first in the textfile.
                allSettings=allSettings.substring(allSettings.indexOf("#"));
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

    private static void loadControlType(FileHandle handle){
        if(handle.readString().contains("Touchpad")){
            Settings.controlType="Touchpad";
        }
        else{
            Settings.controlType="Drag";
        }
    }

    public static void init(){
        String defaultValue="Drag";
        String fileName = "Settings.txt";
        FileHandle handle = SAVE_LOCATION.child(fileName);
        if(handle.exists()){
            loadControlType(handle);
        }
        else{
            setControlType(defaultValue);
        }
    }

    public static String getControlType(){
        return Settings.controlType;
    }
}
