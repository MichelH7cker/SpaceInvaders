/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import com.sun.org.apache.bcel.internal.generic.F2D;
import java.io.InputStream;
import javafx.scene.text.Font;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.FontWeight;

/**
 * esta classe é responsável por organizar e administrar toda parte lógica e 
 * gráfica do cenaŕio do jogo. tanto o esquema de pontuação, como a vida do
 * player são organizados aqui
 * @author michel (nusp: 12609690)
 */
public class Cenario {
    
    // CONFIGURAÇÕES LÓGICAS DA CLASSE
    private int ALIENS_LEFT = 40;
    private int TOTAL_SCORE = 0;
    private int NORMAL_SCORE = 15;
    private int SPECIAL_SCORE = 30;
    
    // CONFIGURAÇÕES GRÁFICAS
    private final int WIDTH_SCREEN = 1600;
    private final int HEIGHT_SCREEN = 900;
    private final int WIDTH_HEART = 50;
    private final int HEIGHT_HEART = 50;
    private final int NUMBER_ALIENS_LINE = 5;
    private final int NUMBER_ALIENS_COLUMN = 11;
    private double WIDTH_ROCK = 50;
    private final double OFFSET_ROCK = (WIDTH_SCREEN - (3 * WIDTH_ROCK)) / 4;
    private final double OFFSET_X = 70;
    private final double OFFSET_Y = 20;
    private final double FONT_SIZE = 36;
    private final double WIDTH_LINE = 5;
    private final double SIZE_BOTTON_MENU = 4 * OFFSET_Y + FONT_SIZE + WIDTH_LINE;
    private final double END_OF_LIFE_TEXT = 235; // chute
    private final double OFFSET_HEART_IMAGE = 60;
    
    private String LIFE_TEXT = "LIFE: ";
    private String SCORE_TEXT = "SCORE: ";
    
    Font DOGICA_PIXEL_BOLD = Font.loadFont("file:src/fonts/dogicapixelbold.ttf", FONT_SIZE);

    Line line;
    
    final Image BACKGROUND_IMAGE = new Image("images/background_space_invaders.png", WIDTH_SCREEN, HEIGHT_SCREEN, false, false);
    final Image HEART_IMAGE = new Image("images/heart.png", WIDTH_HEART, HEIGHT_HEART, false, false);
    ArrayList<Image> heart_images;
    
    GraphicsContext gc;
            
    /**
     * construtor da classe Cenario
     * @param gc 
     */
    Cenario (GraphicsContext gc){
        this.gc = gc;
        heart_images = new ArrayList<Image>();
        for (int i = 0; i < 3; i++){
            heart_images.add(HEART_IMAGE);
        }
    }
    
    /**
     * retorna a largura da tela do jogo
     * @return <code>double</code> indica a largura da tela
     */
    public double getCanvasWidth(){
        return this.WIDTH_SCREEN;
    }
    
    /**
     * retorna a altura da tela do jogo
     * @return <code>double</code> indica a altura da tela
     */
    public double getCanvasHeight(){
        return this.HEIGHT_SCREEN;
    }
    
    /**
     * retorna a largura da rocha 
     * @return <code>double</code> indica a largura da rocha
     */
    public double getWidthRock(){
        return this.WIDTH_ROCK;
    }
    
    /**
     * retorna o espaçamento a ser desenhado entre as rochas do jogo
     * @return <code>double</code> indica o espaçamento entre as rochas
     */
    public double getOffsetRock(){
        return this.OFFSET_ROCK;
    }
    
    /**
     * retorna o tamanho do menu inferior do jogo
     * @return <code>double</code> indica o tamanho do menu inferior
     */
    public double getSizeBottonMenu(){
        return SIZE_BOTTON_MENU;
    }
    
    /**
     * retorna o número de linhas de aliens
     * @see <code>Alien</code>
     * @return <code>int</code> indica o número de linhas de aliens
     */
    public int getNumberAliensLine(){
        return this.NUMBER_ALIENS_LINE;
    }
    
    /**
     * retorna o número de colunas de aliens
     * @see <code>Alien</code>
     * @return <code>int</code> indica o número de colunas de aliens
     */
    public int getNumberAliensColumn(){
        return this.NUMBER_ALIENS_COLUMN;
    }
    
    /**
     * retorna o score total do player 
     * @return <code>int</code> indica o score do player
     */
    public int getTotalScore(){
        return this.TOTAL_SCORE;
    }
    
    /**
     * verifica se algum objeto com posição x está enconstando na parede esquerda
     * @param x posição x a ser verificada 
     * @return <code>boolean</code> indica se a posição x está na parede esquerda
     */
    public boolean itsOnTheLeftWall(double x){
        return x <= 0;
    }

    /**
     * verifica se algum objeto com posição x está enconstando na parede direita
     * @param x posição x a ser verificada
     * @return <code>boolean</code> indica se a posição x está na parede esquerda
     */
    public boolean itsOnTheRightWall(double x) {
        return x >= WIDTH_SCREEN;
    }
    /**
     * verifica se algum objeto com posição y está enconstando na parede superior
     * @param y posição y a ser verificada
     * @return <code>boolean</code> indica se a posição y está na parede superior
     */
    public boolean itsOnTheTop(double y){
        return y <= 0;
    }
    
    /**
     * verifica se algum objeto com posição y está enconstando na parede inferior
     * @param y posição y a ser verificada
     * @return <code>boolean</code> indica se a posição y está na parede inferior
     */
    public boolean itsOnTheBotton(double y){
        return y >= HEIGHT_SCREEN;
    }
    
    /**
     * aumenta a pontuação do player com base na recompensa de um alien normal
     * @see <code>Spaceship</code>
     * @see <code>Alien</code>
     */
    public void scoreNormalAlien(){
        TOTAL_SCORE += NORMAL_SCORE;
    }
    
    /**
     * aumenta a pontuação do player com base na recompensa de um alien especial
     * @see <code>Spaceship</code>
     * @see <code>Alien</code>
     */
    public void scoreSpecialAlien(){
        TOTAL_SCORE += SPECIAL_SCORE;
    }
    
    /**
     * desenha o menu inferior no jogo
     */
    public void drawMenu(){
        // DESENHA LINHA
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(WIDTH_LINE);
        gc.strokeLine(OFFSET_X, 
                HEIGHT_SCREEN - FONT_SIZE - (2 * OFFSET_Y), 
                WIDTH_SCREEN - OFFSET_X, 
                HEIGHT_SCREEN - FONT_SIZE -  2 * OFFSET_Y);

        // DESENHA INT SCORE
        gc.setFont(DOGICA_PIXEL_BOLD);
        gc.setFill(Color.WHITE) ;
        gc.fillText(Integer.toString(TOTAL_SCORE), 
                WIDTH_SCREEN - (2 * OFFSET_X), 
                HEIGHT_SCREEN - OFFSET_Y);
        
        // DESENHA TEXTO SCORE
        gc.setFont(DOGICA_PIXEL_BOLD);
        gc.setFill(Color.WHITE) ;
        gc.fillText(SCORE_TEXT, 
                WIDTH_SCREEN - (5 * OFFSET_X), 
                HEIGHT_SCREEN - OFFSET_Y);
        
        // DESENHA TEXTO VIDA
        gc.setFont(DOGICA_PIXEL_BOLD);
        gc.setFill(Color.WHITE) ;
        gc.fillText(LIFE_TEXT, OFFSET_X, HEIGHT_SCREEN - OFFSET_Y);
        
        // DESENHA CORAÇÕES
        int mulitply_space = 0;
        for (int i = 0; i < heart_images.size(); i++){
            gc.drawImage(HEART_IMAGE, 
                    END_OF_LIFE_TEXT + mulitply_space * OFFSET_HEART_IMAGE, 
                    HEIGHT_SCREEN - SIZE_BOTTON_MENU / 2);
            mulitply_space++;
        }
    }
}
