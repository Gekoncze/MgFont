package cz.mg.font;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;


interface FontLibraryC extends Library {
    public static FontLibraryC instance = Native.loadLibrary("MgFontC", FontLibraryC.class);

    public Pointer MgFontLibrary_create();
    public void MgFontLibrary_destroy(Pointer library);

    public Pointer MgFont_create(Pointer library, Pointer data, int dataSize);
    public void MgFont_destroy(Pointer font);
    public int MgFont_setSize(Pointer font, int size);
    public String MgFont_getName(Pointer font);
    public int MgFont_canDisplay(Pointer font, int utfCode);

    public Pointer MgFontCharacter_create(Pointer font, int utfCode);
    public int MgFontCharacter_destroy(Pointer character);
    public int MgFontCharacter_getBitmapHeight(Pointer character);
    public int MgFontCharacter_getBitmapWidth(Pointer character);
    public int MgFontCharacter_getDeltaX(Pointer character);
    public int MgFontCharacter_getDeltaY(Pointer character);
    public int MgFontCharacter_getSizeX(Pointer character);
    public int MgFontCharacter_getSizeY(Pointer character);
    public int MgFontCharacter_read(Pointer character, int x, int y);
}
