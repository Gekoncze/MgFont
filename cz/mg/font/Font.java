package cz.mg.font;


public class Font {
    final long handle;
    
    public Font(FontLibrary library, byte[] data) {
        this.handle = create(library.handle, data);
    }

    @Override
    protected void finalize() throws Throwable {
        destroy(handle);
    }
    
    private native long create(long libraryHandle, byte[] data);
    private native void destroy(long handle);
}
