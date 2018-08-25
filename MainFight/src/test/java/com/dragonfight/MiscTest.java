package com.dragonfight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dragonfight.Misc.MsLocation;

import org.junit.jupiter.api.Test;

public class MiscTest{
    private MsLocation loc1;
    private MsLocation loc2;

    @Test
    public void CheckDistance(){
        loc1 = new MsLocation(10, 1);
        loc2 = new MsLocation(5, 10);
        assertEquals(loc1.distance(loc2),10);
    }
}