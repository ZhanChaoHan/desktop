package com.jachs.desktop.desktop.jim2mov;

import java.io.File;
import java.io.IOException;
import org.jim2mov.core.DefaultMovieInfoProvider;
import org.jim2mov.core.FrameSavedListener;
import org.jim2mov.core.ImageProvider;
import org.jim2mov.core.Jim2Mov;
import org.jim2mov.core.MovieInfoProvider;
import org.jim2mov.core.MovieSaveException;
import org.jim2mov.utils.MovieUtils;

/**
 *
 * @author andre
 */
public class Files2MovExample implements ImageProvider, FrameSavedListener {
    private String[] files = null;
    private int type = MovieInfoProvider.TYPE_QUICKTIME_JPEG;

    /**
     * Creates a new instance of Files2MovExample
     */
    public Files2MovExample ( String[] files, int type, String saveFileLocation ) {
        this.files = files;
        this.type = type;
        DefaultMovieInfoProvider dmip = new DefaultMovieInfoProvider ( saveFileLocation );
        dmip.setFPS ( 1 );
        dmip.setNumberOfFrames ( files.length );
        dmip.setMWidth ( 320 );
        dmip.setMHeight ( 240 );
        try {
            new Jim2Mov ( this, dmip, this ).saveMovie ( type );
        }
        catch ( MovieSaveException mse ) {
            mse.printStackTrace ();
        }
    }

    public void frameSaved ( int frameNumber ) {
        System.out.println ( "Saved frame: " + frameNumber );
    }

    public byte[] getImage ( int frame ) {
        try {
            return MovieUtils.convertImageToJPEG ( new File ( files[frame].trim () ), 1.0f );
        }
        catch ( IOException ioe ) {
            ioe.printStackTrace ();
        }

        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main ( String[] args ) {
        new Files2MovExample ( args, MovieInfoProvider.TYPE_QUICKTIME_JPEG, "Test.mov" );
    }

}
