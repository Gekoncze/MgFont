package cz.mg.font;


public class FontLibrary {
    final long handle;
    
    static {
        System.loadLibrary("MgFont");
    }
    
    public FontLibrary() {
        this.handle = create();
    }

    @Override
    protected void finalize() throws Throwable {
        destroy(handle);
    }
    
    private native long create();
    private native void destroy(long handle);
}
