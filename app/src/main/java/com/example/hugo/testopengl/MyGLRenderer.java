package com.example.hugo.testopengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.hugo.testopengl.shapes.Square;
import com.example.hugo.testopengl.shapes.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Hugo on 11/10/2017.
 */

public class MyGLRenderer implements GLSurfaceView.Renderer {
    private Triangle mTriangle;

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
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        //i eh largura e i1 altura
        GLES20.glViewport(0, 0, i, i1);
    }

    //chamada sempre que necessario redesenhar a view
    @Override
    public void onDrawFrame(GL10 gl10) {
        //redesenha a cor de fundo
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        mTriangle.draw();
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
}
