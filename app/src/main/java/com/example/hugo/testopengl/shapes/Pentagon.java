package com.example.hugo.testopengl.shapes;

/**
 * Created by Hugo on 14/10/2017.
 */

public class Pentagon extends Shape {
    //coordenadas (x,y,z) em sentido anti horario
    static float[] sPentagonCoords = {
            0f, 0.4f, 0f,
            -0.4f, 0.1f, 0f,
            -0.3f, -0.4f, 0f,
            0.3f, -0.4f, 0f,
            0.4f, 0.1f, 0f
    };

    static float[] sPentagonColor = {0.3f, 0.3f, 0.3f, 1f};

    public Pentagon() {
        super(sPentagonCoords, sPentagonColor);
    }
}
