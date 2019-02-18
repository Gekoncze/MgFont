package cz.mg.font;

import com.sun.jna.Pointer;


public class FontLibrary {
    private static FontLibrary instance = null;

    public static FontLibrary getInstance(){
        if(instance == null) instance = new FontLibrary();
        return instance;
    }

    private Pointer library;

    private FontLibrary(){
        library = FontLibraryC.instance.MgFontLibrary_create();
        if(library == null || library == Pointer.NULL) throw new RuntimeException("Could not create font library.");
    }

    @Override
    protected void finalize() throws Throwable {
        if(library != null && library != Pointer.NULL){
            FontLibraryC.instance.MgFontLibrary_destroy(library);
        }
    }

    Pointer getLibrary() {
        return library;
    }
}
