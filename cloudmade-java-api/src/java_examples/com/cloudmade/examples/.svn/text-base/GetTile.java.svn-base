package com.cloudmade.examples;

/*
 * Example usage of CloudMade's API
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.cloudmade.api.CMClient;

public class GetTile {
	
    public static void main(String[] args) {
        CMClient client = new CMClient("BC9A493B41014CAABB98F0471D759707");
        byte[] tile = client.getTile(47.26117, 9.59882, 9, 1, 256);

        try {
            FileOutputStream fos = new FileOutputStream("tile.png");
            fos.write(tile);
            fos.close();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
