package com.example.hugo.testopengl.shapes;

import android.opengl.GLES20;

import com.example.hugo.testopengl.MyGLRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Hugo on 14/10/2017.
 */

public class Triangle extends Shape{

    static float sTriangleCoords[] = {
            //coordenadas x, y, z
            //em ordem anti horaria
            //topo
            0f, 0.6f, 0f,
            //esquerda baixo
            -0.5f, -0.3f, 0f,
            //direita baixo
            0.5f, -0.3f, 0f
    };
    //cores, em float, vermelho, verde, azul e alpha
    static float sTriangleColor[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    public Triangle() {
        super(sTriangleCoords,sTriangleColor);
    }

}
