package de.cau.cs.kieler.klighd.microlayout;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

import org.eclipse.core.runtime.Platform;

public class BufferUtil
{
    final private BufferedImage image;
    final private Graphics2D gc;

    /** Obtain buffered image and GC
     *
     *  <p>Can be called from any thread, will
     *  always create the buffered image on the UI thread.
     *
     *  @param width Width
     *  @param height Height
     *  @return {@link BufferUtil} or <code>null</code> if interrupted, error
     */
    public static BufferUtil getBufferedImage(final int width, final int height)
    {

//        final CompletableFuture<BufferUtil> result = new CompletableFuture<>();
//        new Thread(() -> {
//        
            BufferUtil result = new BufferUtil(width, height);
//        });
//
//        try
//        {
//            // Simulate frequent timeout
//            // if (Math.random() > 0.5) throw new TimeoutException("Test");
//            return result.get(10000, TimeUnit.MILLISECONDS);
//        }
//        catch (InterruptedException ex)
//        {
//            return null;
//        } catch (ExecutionException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
            return result;
    }

    private BufferUtil(final int width, final int height)
    {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        gc = image.createGraphics();
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public Graphics2D getGraphics()
    {
        return gc;
    }

    public void dispose()
    {
        gc.dispose();
    }
}
