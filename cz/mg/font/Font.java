package cz.mg.font;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import java.nio.ByteBuffer;


public class Font {
    private Pointer font;

    public Font(byte[] data) {
        this(createDirectBuffer(data));
    }

    public Font(ByteBuffer data) {
        if(!data.isDirect()) throw new RuntimeException("Font byte buffer data must be direct.");
        font = FontLibraryC.instance.MgFont_create(FontLibrary.getInstance().getLibrary(), Native.getDirectBufferPointer(data), data.position());
        if(font == null || font == Pointer.NULL) throw new RuntimeException("Could not create font.");
    }

    @Override
    protected void finalize() throws Throwable {
        if(font != null && font != Pointer.NULL){
            FontLibraryC.instance.MgFont_destroy(font);
        }
    }

    Pointer getFont() {
        return font;
    }

    public String getName(){
        return FontLibraryC.instance.MgFont_getName(font);
    }

    public boolean canDisplay(char ch){
        return FontLibraryC.instance.MgFont_canDisplay(font, (int)ch) != 0;
    }

    public void setSize(int size){
        FontLibraryC.instance.MgFont_setSize(font, size);
    }

    public static ByteBuffer createDirectBuffer(byte[] data){
        ByteBuffer buffer = ByteBuffer.allocateDirect(data.length);
        buffer.put(data);
        return buffer;
    }
}
