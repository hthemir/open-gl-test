package com.example.hugo.testopengl.shapes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Hugo on 14/10/2017.
 */

public class Square {
    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;

    static final int COORDS_PER_VERTEX = 3;
    static float sSquareCoords[] = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f
    };

    //desenha-se um quadrado desenhando dois triangulos
    //para evitar definir cada coordenada compartilhada pelos triangulos duas vezes, utiliza uma ordem para informar como desenhar
    private short drawOrder[] = {0, 1, 2, 0, 2, 3};

    public Square() {
        //inicializa o buffer de bytes para as coordenadas da forma com o numero de coordenadas * 4 (bytes por float)
        ByteBuffer bb = ByteBuffer.allocateDirect(sSquareCoords.length * 4);
        //utiliza a ordem de bytes nativa do hardware do aparelho
        bb.order(ByteOrder.nativeOrder());

        //cria um buffer float a partir do buffer de bytes
        vertexBuffer = bb.asFloatBuffer();
        //adiciona as coordenadas do quadrado ao buffer
        vertexBuffer.put(sSquareCoords);
        //poe o buffer para ler a primeira coordenada
        vertexBuffer.position(0);

        //inicializa o buffer de bytes para a ordem de desenhar com o numero de coordenadas * 2 (bytes por short)
        ByteBuffer drawListByteBuffer = ByteBuffer.allocateDirect(drawOrder.length * 2);
        //utiliza a ordem de bytes nativa do hardware do aparelho
        drawListByteBuffer.order(ByteOrder.nativeOrder());
        //cria um buffer short a partir do buffer de bytes
        drawListBuffer = drawListByteBuffer.asShortBuffer();
        //adiciona as coordenadas de desenhar o quadrado ao buffer
        drawListBuffer.put(drawOrder);
        //poe o buffer para ler a primeira coordenada
        drawListBuffer.position(0);
    }
}
