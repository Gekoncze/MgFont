package test;

import cz.mg.font.Font;
import cz.mg.font.FontCharacter;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;


public class Test {
    public static void main(String[] args) throws IOException {
        Font font = new Font(load());
        System.out.println("Loaded font: " + font.getName());
        font.setSize(10);
        FontCharacter ch = new FontCharacter(font, 'ž');
        for(int y = 0; y < ch.getBitmapHeight(); y++){
            for(int x = 0; x < ch.getBitmapWidth(); x++){
                int value = ch.read(x, y);
                char out = 'X';
                if(value < 255/3){
                    out = '░';
                } else if(value < 2*255/3){
                    out = '▒';
                } else {
                    out = '▓';
                }
                System.out.print(out);
            }
            System.out.println();
        }
    }

    private static byte[] load() throws IOException {
        int bufferSize = 2048;
        InputStream stream = Test.class.getResourceAsStream("Ubuntu-Regular.ttf");
        LinkedList<byte[]> buffers = new LinkedList<>();
        byte[] buffer = new byte[bufferSize];
        int lastSize;
        while((lastSize = stream.read(buffer)) == bufferSize){
            buffers.addLast(buffer);
            buffer = new byte[bufferSize];
        }
        buffers.addLast(buffer);

        int totalSize = buffers.size()*bufferSize;
        totalSize -= bufferSize - lastSize;

        byte[] bytes = new byte[totalSize];
        int i = 0;
        for(byte[] b : buffers){
            int max = b == buffers.getLast() ? lastSize : bufferSize;
            for(int ii = 0; ii < max; ii++){
                bytes[i] = b[ii];
                i++;
            }
        }

        System.out.println("loaded: " + bytes.length + " bytes");
        return bytes;
    }
}
