/*
 * Jim2Mov.java
 *
 * Created on October 8, 2006, 3:54 PM
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

package org.jim2mov.core;

import java.awt.Dimension;
import java.io.IOException;
import javax.media.Buffer;
import javax.media.Format;
import javax.media.format.RGBFormat;
import javax.media.format.VideoFormat;
import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.FileTypeDescriptor;
import javax.media.protocol.PullBufferStream;
import org.jim2mov.sun.ImagesToMovie;

/**
 * Wrapper class that allows to create movies. The only requirement is providing the implementation of
 * two simple interfaces
 * @author andre
 */
public class Jim2Mov implements PullBufferStream
{
    /**
     * The video format
     */
    private VideoFormat videoFormat = null; 
    /**
     * Finished saving?
     */
    private boolean finished = false;
    /**
     * Width of the frames
     */
    private int width = 0;
    /**
     * Height of the frames
     */
    private int height = 0;
    /**
     * Interface which provides information about the movie
     */
    private MovieInfoProvider mip = null;
    /**
     * Interface which returns the images
     */
    private ImageProvider ip = null;
    /**
     * The current frame
     */
    private int currentFrame = 0;
    /**
     * The class which interacts with JMF
     */
    private ImagesToMovie itm = null;
    
    /** 
     * Creates a new instance of Jim2Mov 
     * @param ip Interface which returns the images
     * @param mip Interface which provides information about the movie
     */
    public Jim2Mov(ImageProvider ip, MovieInfoProvider mip)
    {
        this.ip = ip;
        this.mip = mip;
        itm = new ImagesToMovie(this);
    }
    
    /** 
     * Creates a new instance of Jim2Mov 
     * @param ip Interface which returns the images
     * @param mip Interface which provides information about the movie
     * @param listener Listener to the frame add events
     */
    public Jim2Mov(ImageProvider ip, MovieInfoProvider mip, FrameSavedListener listener)
    {
        this(ip, mip);
        addFrameSavedListener(listener);
    }
    
    /** 
     * This method saves the movie.
     * @param type The type of movie. You can get the types available in the MovieInfoProvider interface
     * @throws MovieSaveException If there is any problem saving the movie
     */
    public void saveMovie(int type) throws MovieSaveException
    {
        currentFrame = 0;
        String desc = null;
        if(type == MovieInfoProvider.TYPE_QUICKTIME_JPEG)
        {
            createMOVFormat();
            desc = FileTypeDescriptor.QUICKTIME;
        }
        else if(type == MovieInfoProvider.TYPE_AVI_MJPEG)
        {
            createMJPEGFormat();
            desc = FileTypeDescriptor.MSVIDEO;
        }
        else if(type == MovieInfoProvider.TYPE_AVI_RAW)
        {
            createRGBFormat();
            desc = FileTypeDescriptor.MSVIDEO;
        }
        else
            throw new UnsupportedOperationException("File Type not supported...");
        
        try
        {
            itm.saveMovie(mip.getMediaLocator(), desc, videoFormat);
        }
        catch(Exception e)
        {
            throw new MovieSaveException(e);
        }
    }
    
    /** 
     * This method saves the movie, but the video format is given by the caller...
     * @param descriptor The file type descriptor
     * @param vf The video format
     * @throws MovieSaveException If there is any problem saving the movie
     */
    public void saveMovie(String descriptor, VideoFormat vf) throws MovieSaveException
    {
        try
        {
            this.videoFormat = vf;
            itm.saveMovie(mip.getMediaLocator(), descriptor, videoFormat);
        }
        catch(Exception e)
        {
            throw new MovieSaveException(e);
        }
    }

    /**
     * {@link http://java.sun.com/products/java-media/jmf/2.1.1/apidocs/javax/media/protocol/PullBufferStream.html}     * 
     */
    public boolean endOfStream()
    {
        return finished;
    }

    /**
     * {@link http://java.sun.com/products/java-media/jmf/2.1.1/apidocs/javax/media/protocol/PullBufferStream.html}     * 
     */
    public ContentDescriptor getContentDescriptor()
    {
        return new ContentDescriptor(ContentDescriptor.RAW);
    }

    /**
     * {@link http://java.sun.com/products/java-media/jmf/2.1.1/apidocs/javax/media/protocol/PullBufferStream.html}     * 
     */
    public long getContentLength()
    {
        return 0;
    }

    /**
     * {@link http://java.sun.com/products/java-media/jmf/2.1.1/apidocs/javax/media/protocol/PullBufferStream.html}     * 
     */
    public Object getControl(String str)
    {
        return null;
    }

    /**
     * {@link http://java.sun.com/products/java-media/jmf/2.1.1/apidocs/javax/media/protocol/PullBufferStream.html}     * 
     */
    public Object[] getControls()
    {
        return new Object[0];
    }

    /**
     * {@link http://java.sun.com/products/java-media/jmf/2.1.1/apidocs/javax/media/protocol/PullBufferStream.html}     * 
     */    
    public javax.media.Format getFormat()
    {
        return videoFormat;
    }

    /**
     * {@link http://java.sun.com/products/java-media/jmf/2.1.1/apidocs/javax/media/protocol/PullBufferStream.html}     * 
     */    
    public void read(Buffer buf) throws IOException
    {
        if (currentFrame >= mip.getNumberOfFrames())
        {
            buf.setEOM(true);
            buf.setOffset(0);
            buf.setLength(0);
            finished = true;
            return;
        }
                
        byte[] data = ip.getImage(currentFrame);
        buf.setLength(data.length);
        
        buf.setTimeStamp((long)(currentFrame * (1000 / mip.getFPS()) * 1000000L));
        buf.setSequenceNumber(currentFrame);
        
        buf.setOffset(0);
        buf.setFormat(videoFormat);
        buf.setFlags(buf.getFlags() | buf.FLAG_KEY_FRAME);
        
        buf.setData(data);
        
        fireFrameSaved(currentFrame);
        currentFrame++;
    }

    /**
     * {@link http://java.sun.com/products/java-media/jmf/2.1.1/apidocs/javax/media/protocol/PullBufferStream.html}     * 
     */    
    public boolean willReadBlock()
    {
        return false;
    }
    
    /**
     * {@link http://java.sun.com/products/java-media/jmf/2.1.1/apidocs/javax/media/protocol/PullBufferStream.html}     * 
     */    
    private void createRGBFormat()
    {
        videoFormat = new RGBFormat(
                new Dimension(mip.getMWidth(), mip.getMHeight()),
                width*height,
                int[].class,
                mip.getFPS(),
                32,
                0x00ff0000,
                0x0000ff00,
                0x000000ff,
                1,
                width,
                Format.FALSE,
                RGBFormat.BIG_ENDIAN);        
    }

    /**
     * {@link http://java.sun.com/products/java-media/jmf/2.1.1/apidocs/javax/media/protocol/PullBufferStream.html}     * 
     */    
    private void createMOVFormat()
    {
        videoFormat = new VideoFormat(VideoFormat.JPEG,
                new Dimension(mip.getMWidth(), mip.getMHeight()),
                Format.NOT_SPECIFIED,
                Format.byteArray,
                mip.getFPS());
    }

    /**
     * {@link http://java.sun.com/products/java-media/jmf/2.1.1/apidocs/javax/media/protocol/PullBufferStream.html}     * 
     */    
    private void createMJPEGFormat()
    {
        videoFormat = new VideoFormat(VideoFormat.MJPG,
                new Dimension(mip.getMWidth(), mip.getMHeight()),
                Format.NOT_SPECIFIED,
                Format.byteArray,
                mip.getFPS());
    }
    
    /**
     * Warns all listeners of a new frame added event
     * @param frameNumber the number of the frame added to the movie
     */
    private void fireFrameSaved(int frameNumber)
    {
        if (listenerList == null) return;
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2)
        {
            if (listeners[i]==FrameSavedListener.class)
            {
                ((FrameSavedListener)listeners[i+1]).frameSaved(frameNumber);
            }
        }
    }

    /**
     * Utility field used by event firing mechanism.
     */
    private javax.swing.event.EventListenerList listenerList =  null;

    /**
     * Registers FrameSavedListener to receive events.
     * @param listener The listener to register.
     */
    public synchronized void addFrameSavedListener(FrameSavedListener listener)
    {

        if (listenerList == null ) {
            listenerList = new javax.swing.event.EventListenerList();
        }
        listenerList.add (FrameSavedListener.class, listener);
    }

    /**
     * Removes FrameSavedListener from the list of listeners.
     * @param listener The listener to remove.
     */
    public synchronized void removeFrameSavedListener(FrameSavedListener listener)
    {

        listenerList.remove (FrameSavedListener.class, listener);
    }
    
}
