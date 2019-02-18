package cz.mg.font;

import com.sun.jna.Pointer;


public class FontCharacter {
    private Pointer character;

    public FontCharacter(Font font, char ch) {
        character = FontLibraryC.instance.MgFontCharacter_create(font.getFont(), ch);
        if(character == null || character == Pointer.NULL) throw new RuntimeException("Could not create font character.");
    }

    @Override
    protected void finalize() throws Throwable {
        if(character != null && character != Pointer.NULL){
            FontLibraryC.instance.MgFontCharacter_destroy(character);
        }
    }

    public int getBitmapWidth(){
        return FontLibraryC.instance.MgFontCharacter_getBitmapWidth(character);
    }

    public int getBitmapHeight(){
        return FontLibraryC.instance.MgFontCharacter_getBitmapHeight(character);
    }

    public int getDeltaX(){
        return FontLibraryC.instance.MgFontCharacter_getDeltaX(character);
    }

    public int getDeltaY(){
        return FontLibraryC.instance.MgFontCharacter_getDeltaY(character);
    }

    public int getSizeX(){
        return FontLibraryC.instance.MgFontCharacter_getSizeX(character);
    }

    public int getSizeY(){
        return FontLibraryC.instance.MgFontCharacter_getSizeY(character);
    }

    public int read(int x, int y){
        return FontLibraryC.instance.MgFontCharacter_read(character, x, y);
    }
}
