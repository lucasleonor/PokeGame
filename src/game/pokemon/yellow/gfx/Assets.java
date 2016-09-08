package game.pokemon.yellow.gfx;

import game.pokemon.yellow.tiles.TileManager;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas G R Leonor
 */
public class Assets implements Runnable {

    private static SpriteSheet overworldSheet, playerMaleSheet, playerFemaleSheet, tilesSheet, introSheet, frameSheet, portraitSheet, pokemonMenuSheet, introIniciaisSheet, itemIconsSheet;

    public static void init() {
        Thread t = new Thread(new Assets());
        t.start();
        introSheet = new SpriteSheet(ImageLoader.loadImage("/Images/intro.png"));
    }

    public static Animation[] getPlayerAnimations(boolean male) {
        while (playerMaleSheet == null) {
            System.out.print("pl");
        }
        Animation[] animations = new Animation[4];
        for (int i = 0; i < 4; i++) {
            Animation animation = new Animation();
            BufferedImage[] frames = new BufferedImage[4];
            for (int j = 0; j < frames.length; j++) {
                if (male) {
                    frames[j] = playerMaleSheet.crop(j * TileManager.TILEWIDHT, i * TileManager.TILEHEIGHT, TileManager.TILEWIDHT, TileManager.TILEHEIGHT);
                } else {
                    frames[j] = playerFemaleSheet.crop(j * TileManager.TILEWIDHT, i * TileManager.TILEHEIGHT, TileManager.TILEWIDHT, TileManager.TILEHEIGHT);
                }
            }
            frames[3] = frames[1];
            animation.setFrames(frames);
            animation.setIndex(1);
            animations[i] = animation;
        }
        return animations;
    }

    public static Animation[] getPokemonAnimations(int id) {
        while (overworldSheet == null) {
            System.out.print("po");
        }
        if (id < 4) {
            id--;
        }
        if (id > 25) {
            id++;
        }
        int coluna = (id % 15), linha = (id / 15);
        Animation[] animations = new Animation[4];
        for (int lin = 0; lin < 2; lin++) {
            for (int col = 0; col < 2; col++) {
                Animation animation = new Animation();
                BufferedImage[] frames = new BufferedImage[2];
                for (int anim = 0; anim < 2; anim++) {
                    int x = ((coluna * 2) + col) * 32 + coluna, y = ((linha * 4) + lin * 2 + anim) * 32 + linha;
                    frames[anim] = overworldSheet.crop(x, y, 32, 32);
                }
                animation.setFrames(frames);
                animations[lin * 2 + col] = animation;
            }
        }
        return animations;
    }

    public static BufferedImage getFromTiles(int x, int y, int width, int height) {
        while (tilesSheet == null) {
            System.out.print("t");
        }
        return tilesSheet.crop(x, y, width, height);
    }

    public static BufferedImage[] getFundoIntro() {
        while (introSheet == null) {
            System.out.print("i");
        }
        BufferedImage[] images = new BufferedImage[7];
        images[0] = introSheet.crop(5, 213, 240, 96);
        images[1] = introSheet.crop(248, 213, 240, 96);
        images[2] = introSheet.crop(491, 213, 240, 96);
        images[3] = introSheet.crop(734, 377, 240, 96);
        images[4] = introSheet.crop(734, 213, 240, 96);
        images[5] = introSheet.crop(188, 134, 144, 19);
        images[6] = introSheet.crop(734, 118, 29, 58);
        return images;
    }

    public static Animation getGramaIntro() {
        while (introSheet == null) {
            System.out.print("i");
        }
        BufferedImage[] images = new BufferedImage[3];
        images[0] = introSheet.crop(5, 377, 240, 96);
        images[1] = introSheet.crop(248, 377, 240, 96);
        images[2] = introSheet.crop(491, 377, 240, 96);
        Animation a = new Animation();
        a.setFrames(images);
        return a;
    }

    public static Animation getLogo() {
        while (introSheet == null) {
            System.out.print("l");
        }
        int width = 172, height = 62;
        BufferedImage[] images = new BufferedImage[6];
        for (int i = 0; i < images.length; i++) {
            if (i < 3) {
                images[i] = introSheet.crop(1 + width * i, 1, width, height);
            } else {
                images[i] = introSheet.crop(1 + width * (i - 3), height, width, height);
            }
        }
        Animation a = new Animation();
        a.setFrames(images);
        return a;
    }

    public static BufferedImage getVenosaurIntro() {
        return introSheet.crop(830, 503, 155, 125);
    }

    public static BufferedImage getCharizardIntro() {
        return introSheet.crop(635, 491, 194, 153);
    }

    public static BufferedImage getBlastoiseIntro() {
        return introSheet.crop(834, 629, 154, 161);
    }

    public static BufferedImage getIntroIniciais() {
        return introIniciaisSheet.getSheet();
    }

    public static List<Frame> getMenuFrames() {
        while (frameSheet == null) {
            System.out.print("f");
        }
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                if (!(i >= 7 && j == 2)) {
                    SpriteSheet menu = new SpriteSheet(frameSheet.crop(i * 26 + 4 * (i + 1), j * 26 + 4 * (j + 1), 26, 26));
                    BufferedImage[][] frame = new BufferedImage[3][3];
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            frame[k][l] = menu.crop(k * 8 + k, l * 8 + l, 8, 8);
                        }
                    }
                    frames.add(new Frame(frame));
                }
            }
        }
        return frames;
    }

    public static Animation getPokemonPortrait(int id) {
        int x, y, w = 34, h = 36;
        x = (id * w * 2) % portraitSheet.getSheet().getWidth();
        y = ((id * w * 2) / portraitSheet.getSheet().getWidth()) * h;
        Animation a = new Animation();
        BufferedImage frames[] = new BufferedImage[2];
        frames[0] = portraitSheet.crop(x, y, w, h);
        frames[1] = portraitSheet.crop(x + w, y, w, h);
        a.setFrames(frames);
        return a;
    }

    public static BufferedImage getFundoPokemon() {
        return pokemonMenuSheet.crop(0, 0, 256, 192);
    }

    public static BufferedImage getCancelPokemon() {
        return pokemonMenuSheet.crop(256, 99, 58, 26);
    }

    public static BufferedImage[] getSelecionadoPokemon() {
        BufferedImage[] i = new BufferedImage[2];
        i[0] = pokemonMenuSheet.crop(384, 50, 129, 49);
        i[1] = pokemonMenuSheet.crop(384, 0, 128, 49);
        return i;
    }

    public static BufferedImage[] getNaoSelecionadoPokemon() {
        BufferedImage[] i = new BufferedImage[2];
        i[0] = pokemonMenuSheet.crop(256, 50, 129, 49);
        i[1] = pokemonMenuSheet.crop(256, 0, 128, 49);
        return i;
    }

    public static BufferedImage getItemIcon() {
        return itemIconsSheet.crop(17*32, 7*32, 32, 32);
    }

    @Override
    public void run() {
        introIniciaisSheet = new SpriteSheet(ImageLoader.loadImage("/Images/introIniciais2.png"));
        frameSheet = new SpriteSheet(ImageLoader.loadImage("/Images/menu.png"));
        overworldSheet = new SpriteSheet(ImageLoader.loadImage("/Images/kanto_overworld.png"));
        playerMaleSheet = new SpriteSheet(ImageLoader.loadImage("/Images/trainerM.png"));
        playerFemaleSheet = new SpriteSheet(ImageLoader.loadImage("/Images/trainerF.png"));
        tilesSheet = new SpriteSheet(ImageLoader.loadImage("/Images/tileset.png"));
        pokemonMenuSheet = new SpriteSheet(ImageLoader.loadImage("/Images/fundoPokemon.png"));
        portraitSheet = new SpriteSheet(ImageLoader.loadImage("/Images/kanto_icons.png"));
        itemIconsSheet = new SpriteSheet(ImageLoader.loadImage("/Images/itens.png"));
    }
}
