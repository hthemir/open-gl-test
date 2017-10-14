package com.example.hugo.testopengl.shapes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Hugo on 14/10/2017.
 */

public class Square extends Shape{
    //coordenadas (x,y,z) em sentido anti horario
    static float sSquareCoords[] = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f
    };
    static float sSquareColor[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};


    public Square() {
        super(sSquareCoords,sSquareColor);
    }
}
