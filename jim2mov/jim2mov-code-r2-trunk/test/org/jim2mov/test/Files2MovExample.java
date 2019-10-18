/*
 * Files2MovExample.java
 *
 * Created on October 16, 2006, 10:43 PM
 * 
 * <p>Title: Jim2mov</p>
 *
 * <p>Description: Create movies from image files</p>
 *
 * <p>Copyright: (C) Copyright 2005-2006, by Andre' Neto</p>
 *
 * Project Info:  	http://jim2mov.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 * </p>
 *
 * @author Andre' Neto
 * @version 1.0.0
 *
 */

package org.jim2mov.test;

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
public class Files2MovExample implements ImageProvider, FrameSavedListener
{
    private String[] files = null;
    private int type = MovieInfoProvider.TYPE_QUICKTIME_JPEG;
    
    /**
     * Creates a new instance of Files2MovExample 
     */
    public Files2MovExample(String[] files, int type, String saveFileLocation)
    {
        this.files = files;
        this.type = type;
        DefaultMovieInfoProvider dmip = new DefaultMovieInfoProvider(saveFileLocation);
        dmip.setFPS(1);
        dmip.setNumberOfFrames(files.length);
        dmip.setMWidth(320);
        dmip.setMHeight(240);
        try
        {
            new Jim2Mov(this, dmip, this).saveMovie(type);
        }
        catch(MovieSaveException mse)
        {
            mse.printStackTrace();
        }        
    }

    public void frameSaved(int frameNumber)
    {
        System.out.println("Saved frame: " + frameNumber);
    }

    public byte[] getImage(int frame)
    {
        try
        {            
            return MovieUtils.convertImageToJPEG(new File(files[frame].trim()), 1.0f);
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        
        return null;
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        new Files2MovExample(args, MovieInfoProvider.TYPE_QUICKTIME_JPEG, "Test.mov");
    }
    
}
