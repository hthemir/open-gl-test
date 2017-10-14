package com.example.hugo.testopengl.shapes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Hugo on 14/10/2017.
 */

public class Triangle {

    private FloatBuffer vertexBuffer;

    static final int COORDS_PER_VERTEX = 3;
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
    float color[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    public Triangle() {
        //inicializa o buffer de bytes para as coordenadas da forma com o numero de coordenadas * 4 (bytes por float)
        ByteBuffer bb = ByteBuffer.allocateDirect(sTriangleCoords.length * 4);
        //utiliza a ordem de bytes nativa do hardware do aparelho
        bb.order(ByteOrder.nativeOrder());

        //cria um buffer float a partir do buffer de bytes
        vertexBuffer = bb.asFloatBuffer();
        //adiciona as coordenadas do triangulo ao buffer
        vertexBuffer.put(sTriangleCoords);
        //poe o buffer para ler a primeira coordenada
        vertexBuffer.position(0);
    }
}
