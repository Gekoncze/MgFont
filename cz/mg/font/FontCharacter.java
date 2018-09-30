package cz.mg.font;

import java.io.UnsupportedEncodingException;


public class FontCharacter {
    final long handle;
    
    public FontCharacter(Font font, char ch) {
        try {
            byte[] utfCode = ("" + ch).getBytes("UTF-8");
            this.handle = create(font.handle, utfCode);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        destroy(handle);
    }
    
    private native long create(long fontHandle, byte[] utfCode);
    private native void destroy(long handle);
}
