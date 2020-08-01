package com.gzeinnumer.externalappenttextfile.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FunctionGlobalFile {
    private static final String TAG = "FunctionGLobalFile_";

    //create file
    public static boolean initFile(String text) {
        File file;
        file = new File(FunctionGlobalDir.getStorageCard + FunctionGlobalDir.appFolder +"/File.txt");
        try{
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter writer = new PrintWriter(f);
            writer.println(text+"1");
            writer.println(text+"2");
            writer.println(text+"3");
            writer.flush();
            writer.close();
            f.close();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> readFile(String path){
        if (!FunctionGlobalDir.isFileExists(path)){
            if (!FunctionGlobalFile.initFile("Buat file")){
                FunctionGlobalDir.myLogD(TAG, "File success dibuat");
            } else {
                return null;
            }
        }

        File file = new File(FunctionGlobalDir.getStorageCard+ FunctionGlobalDir.appFolder+ "/File.txt");
        Scanner input;

        List<String> list = new ArrayList<String>();
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            FunctionGlobalDir.myLogD(TAG,e.getMessage());
            return list;
        }

        while (input.hasNextLine()) {
            list.add(input.nextLine());
        }
        return list;
    }

    public static boolean appentText(String path, String msg) {
        FileWriter fw;
        try {
            fw = new FileWriter(FunctionGlobalDir.getStorageCard+path, true);
            fw.write(msg+"\n");
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            FunctionGlobalDir.myLogD(TAG, e.getMessage());
            return false;
        }
    }
}
