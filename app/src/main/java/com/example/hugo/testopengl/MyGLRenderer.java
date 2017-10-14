package com.example.hugo.testopengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Hugo on 11/10/2017.
 */

public class MyGLRenderer implements GLSurfaceView.Renderer {

    //chamada uma vez para inicializar o ambiente da view OpenGL ES
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        //estabelece a cor de fundo
        GLES20.glClearColor(0f, 0f, 0f, 1f);
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
    }
}
