package game.pokemon.yellow.gfx;

import game.pokemon.yellow.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Lucas G R Leonor
 */
public class Intro {

    /*
    0 - intro
    1 - fundoAzul
    2 - luzNoFimDoFundo
    3 - arvoresBaixas
    4 - arvoresAltas
    5 - gameFreak
    6 - logoGameFreak
     */
    private BufferedImage charizard, venosaur, blastoise, images[];
    private Animation gramas, logo;
    private int windowW, windowH, timer, i;
    private double multiplier;

    public Intro() {
        Handler handler = Handler.getINSTANCE();
        images = Assets.getFundoIntro();
        gramas = Assets.getGramaIntro();
        timer = 0;
        i = 0;
        logo = Assets.getLogo();
        charizard = Assets.getCharizardIntro();
        blastoise = Assets.getIntroIniciais();
        venosaur = Assets.getVenosaurIntro();
        windowW = handler.getWindowWidth();
        windowH = handler.getWindowHeight();
        int width = images[0].getWidth(), height = images[0].getHeight();
        double auxW = ((double) windowW) / width, auxH = ((double) windowH) / height;
        multiplier = (auxW > auxH) ? auxH : auxW;
    }

    public void tick() {
        timer++;
        switch (timer) {
            case 60:
                i = 1;
                break;
            case 300:
                i = 5;
                break;
            case 420:
                i = 6;
                break;
            case 600:
                i = 2;
                break;
            case 660:
                i = 3;
                break;
            case 780:
                i = 4;
                break;
            case 1320:
                i = 7;
                break;
        }
        if (i == 2)
            gramas.tick();
        if (i == 7)
            logo.tick();
    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, windowW, windowH);
        int w = (int) (images[1].getWidth() * multiplier), h = (int) (images[1].getHeight() * multiplier);
        int x = (windowW - w) / 2, y = (windowH - h) / 2;
        if (i == 5 || i == 6) {
            g.drawImage(images[1], x, y, w, h, null);
            if (i == 6) {
                w = (int) (images[i].getWidth() * multiplier);
                h = (int) (images[i].getHeight() * multiplier);
                x = (windowW - w) / 2;
                y = (windowH - h) / 2;
                g.drawImage(images[i], x, y, w, h, null);
            }
            w = (int) (images[5].getWidth() * multiplier);
            h = (int) (images[5].getHeight() * multiplier);
            x = (windowW - w) / 2;
            y = (windowH - h) / 2;
            g.drawImage(images[5], x, y, w, h, null);
        } else if (i == 7) {
//            g.setColor(new Color(180, 0, 0));
//            g.fillRect(0, windowH / 6, windowW / 2, 2 * windowH / 3);
//            g.setColor(new Color(0, 180, 0));
//            g.fillRect(windowW / 2, windowH / 6, windowW / 2, 2 * windowH / 3);
//            w = (int) (charizard.getWidth() * multiplier / 2);
//            h = (int) (charizard.getHeight() * multiplier / 2);
//            g.drawImage(charizard, windowW - w, windowH / 6 + 2 * windowH / 3 - h, w, h, null);
//            w = (int) (venosaur.getWidth() * multiplier / 2);
//            h = (int) (venosaur.getHeight() * multiplier / 2);
//            g.drawImage(venosaur, 0, windowH / 6 + 2 * windowH / 3 - h, w, h, null);

            double auxW = ((double) windowW) / blastoise.getWidth(), auxH = ((double) windowH) / blastoise.getHeight();
            double multiplier = (auxW > auxH) ? auxH : auxW;
            w = (int) (blastoise.getWidth() * multiplier);
            h = (int) (blastoise.getHeight() * multiplier);
            x = (windowW - w) / 2;
            y = (windowH - h) / 2;
            g.drawImage(blastoise, x, y, w, h, null);
            g.drawImage(logo.getCurrentFrame(true), (windowW - logo.getCurrentFrame(true).getWidth()+15) / 2, (windowH / 6),(int) (logo.getCurrentFrame(true).getWidth() * 0.9), (int) (logo.getCurrentFrame(true).getHeight() * 0.9), null);
        } else {
            g.drawImage(images[i], x, y, w, h, null);
            if (i == 2) {
                g.drawImage(gramas.getCurrentFrame(true), x, y, w, h, null);
            }
        }
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
