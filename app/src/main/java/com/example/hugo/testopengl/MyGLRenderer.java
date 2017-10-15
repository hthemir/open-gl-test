package com.example.hugo.testopengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.example.hugo.testopengl.shapes.Square;
import com.example.hugo.testopengl.shapes.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Hugo on 11/10/2017.
 */

public class MyGLRenderer implements GLSurfaceView.Renderer {
    //objeto para desenhar
    private Triangle mTriangle;

    //MVPMatrix = Model View Projection Matrix
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    //matriz para adicionar movimento
    private final float[] mRotationMatrix = new float[16];

    //deve ser volatil pois esta rodando em uma thread diferente da main
    //angulo usado para evento de toque
    private volatile float mAngle;

    //chamada uma vez para inicializar o ambiente da view OpenGL ES
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        //estabelece a cor de fundo
        GLES20.glClearColor(0f, 0f, 0f, 1f);

        //a nao ser que a estrutura (coordenadas) das formas mudem durante execucao, inicialize elas aqui
        mTriangle = new Triangle();
    }

    //chamada se a geometria da view muda (por exemplo, se a orientacao da tela mudar)
    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width/height;
        //essa matriz de projecao eh aplicada nas coordenadas dos objetos desenhados em onDrawFrame()
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3,7);
    }

    //chamada sempre que necessario redesenhar a view
    @Override
    public void onDrawFrame(GL10 gl10) {
        //redesenha a cor de fundo
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        float[] scratch = new float[16];

        //criar uma transformacao de rotacao para a forma
        Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0,0, -1f);

        //calcula a transformacao da view da camera por setlookatm e combina com a matriz de projecao pelo multiplymm
        //estabelece a posicao da camera
        Matrix.setLookAtM(mViewMatrix, 0,0,0,-3,0f,0f,0f,0f,1f,0f);
        //calcula a transformacao da view da camera e da projecao
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        //combina a matriz de rotacao com a as view de projecao e camera
        //a ordem eh importante para garantir o resultado correto da multiplicacao
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0);

        //desenha o triangulo
        mTriangle.draw(scratch);
    }

    public static int loadShader(int type, String shaderCode) {
        //cria um shader de vertice (GLES20.GL_VERTEX_SHADER) ou de fragmento (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);
        //adiciona o codigo fonte ao shader
        GLES20.glShaderSource(shader, shaderCode);
        //compila o shader
        GLES20.glCompileShader(shader);

        return shader;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }

    public float getAngle() {
        return mAngle;
    }
}
