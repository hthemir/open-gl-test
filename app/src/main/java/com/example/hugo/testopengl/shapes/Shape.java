package com.example.hugo.testopengl.shapes;

import android.opengl.GLES20;

import com.example.hugo.testopengl.MyGLRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Hugo on 14/10/2017.
 */

public abstract class Shape {
    //VARIAVEIS PROS BUFFERS
    private FloatBuffer vertexBuffer;
    static final int COORDS_PER_VERTEX = 3;
    //coordenadas (x,y,z) passadas em ordem anti horaria
    float mCoords[];
    //cores, em float, vermelho, verde, azul e alpha
    float mColor[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    //VARIAVEIS PROS SHADERS E PROGRAMA
    //codigo grafico de OpenGL ES para renderizar os vertices da forma (GLSL)

    private final String mVertexShaderCode =
            //essa matrix prove uma maneira de manipular as coordenadas dos objetos que utilizam esse shader de vertice
            "uniform mat4 uMVPMatrix;" +              //matriz (x,y,z,w) global
            "attribute vec4 vPosition;" +     //vetor (x,y,z,w) nao global
            "void main() {" +
            //a matriz deve ser inclusa como um modificador de gl_Position
            //importante notar que a matriz deve vir primeiro na multiplicacao para o produto ser correto
            "  gl_Position = uMVPMatrix * vPosition;" +
            "}";
    //codigo OpenGL ES para renderiza a face da forma com cores ou texturas
    private final String mFragmentShaderCode =
            "precision mediump float;" +        //precisao media ao calcular float
            "uniform vec4 vColor;" +            //vetor (x,y,z,w) global
            "void main() {" +
            "   gl_FragColor = vColor;" +       //cor do fragmento
            "}";
    //objeto OpenGLES que contem os shaders usados para desenhar uma forma ou mais
    private int mProgram;
    //usado para acessar e estabelecer a transformacao de view
    private int mMVPMatrixHandle;

    //VARIAVEIS PARA DESENHAR
    private int mPositionHandle;
    private int mColorHandle;
    //numero de vertices
    private int vertexCount;
    //4 bytes por vertice
    private final int vertexStride = COORDS_PER_VERTEX * 4;


    public Shape(float[] coords, float[] color) {
        mCoords = coords;
        vertexCount = mCoords.length / COORDS_PER_VERTEX;
        mColor = color;

        //inicializa o buffer de bytes para as coordenadas da forma com o numero de coordenadas * 4 (bytes por float)
        ByteBuffer bb = ByteBuffer.allocateDirect(mCoords.length * 4);
        //utiliza a ordem de bytes nativa do hardware do aparelho
        bb.order(ByteOrder.nativeOrder());

        //cria um buffer float a partir do buffer de bytes
        vertexBuffer = bb.asFloatBuffer();
        //adiciona as coordenadas da forma ao buffer
        vertexBuffer.put(mCoords);
        //poe o buffer para ler a primeira coordenada
        vertexBuffer.position(0);

        //compilar os codigos dos shaders
        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, mVertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, mFragmentShaderCode);

        //cria um programa OpenGL ES vazio
        mProgram = GLES20.glCreateProgram();
        //adiciona os shaders ao programa
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        //cria programas OpenGL ES executaveis
        GLES20.glLinkProgram(mProgram);
    }

    public void draw(float[] mvpMatrix) {
        //adiciona programa ao ambiente
        GLES20.glUseProgram(mProgram);

        //pega o controle da posicao vo shader de vertice
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //libera o controle aos vertices da forma
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //prepara os dados de coordenada da forma
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

        //pega o controle da cor do shader de fragmento
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        //estabelece a cor para desenhar a forma
        GLES20.glUniform4fv(mColorHandle, 1, mColor, 0);

        //pega o controle para configurar a matriz de transformacao
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        //passa a projecao e a view de transformacao para o shader
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

        //desenha a forma
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vertexCount);
        //desabilita a lista de vertices
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
