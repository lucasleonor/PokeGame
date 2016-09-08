/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.pokemon.yellow.gfx;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author 31471730
 */
public class Frame {

    private BufferedImage frames[][];

    public Frame(BufferedImage[][] frames) {
        this.frames = frames;
    }

    public BufferedImage[][] getFrames() {
        return frames;
    }

    public BufferedImage getFrame(Rectangle rec) {
        BufferedImage frame = new BufferedImage(rec.width + 12, rec.height + 12, BufferedImage.TYPE_INT_ARGB);
        Graphics g = frame.getGraphics();
        int i;
        for (i = 1; i < rec.width / 8; i++) {
            g.drawImage(frames[1][0], 8 * i, 0, null);
        }
        int j;
        for (j = 1; j < rec.height / 8; j++) {
            g.drawImage(frames[0][1], 0, 8 * j, null);
            g.drawImage(frames[2][1], rec.width, 8 * j, null);
        }
        for (i = 1; i < rec.width / 8; i++) {
            g.drawImage(frames[1][2], 8 * i, rec.height, null);
        }
        g.drawImage(frames[0][0], 0, 0, null);
        g.drawImage(frames[0][2], 0,rec.height, null);
        g.drawImage(frames[2][2], rec.width, rec.height, null);
        g.drawImage(frames[2][0], rec.width, 0, null);
        g.dispose();
        return frame;
    }
}
