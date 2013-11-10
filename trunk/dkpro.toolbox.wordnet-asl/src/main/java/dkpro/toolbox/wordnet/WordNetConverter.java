package dkpro.toolbox.wordnet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.tools.bzip2.CBZip2InputStream;
import org.apache.tools.bzip2.CBZip2OutputStream;

import de.tudarmstadt.ukp.dkpro.lexsemresource.Entity;
import de.tudarmstadt.ukp.dkpro.lexsemresource.LexicalSemanticResource;
import de.tudarmstadt.ukp.dkpro.lexsemresource.core.ResourceFactory;

public class WordNetConverter
{

    public static void main(String[] args) throws Exception
    {
        LexicalSemanticResource wordnet = ResourceFactory.getInstance().get("wordnet", "en");
    
//        JsonConfig config = new JsonConfig();        
        
        int i=0;
        for (Entity entity : wordnet.getEntities()) {
            File folder = new File("target/wordnet_j");
            folder.mkdirs();
            File file = new File(folder, i + ".json");
            file.createNewFile();

            JSON json = JSONSerializer.toJSON(entity);
            json.write(new FileWriter(file));
            
//            serialize(new File("target/wordnet_ser/e" + i + ".gz"), entity);
            if (i == 5) {
                break;
            }
            i++;
        }  
    }
    
    /**
     * Saves a serializable object of type <T> to disk. Output file may be uncompressed, gzipped or
     * bz2-compressed. Compressed files must have a .gz or .bz2 suffix.
     * 
     * @param serializedFile
     *            model output file
     * @param serializableObject
     *            the object to serialize
     * @throws IOException
     */
    public static void serialize(File serializedFile, Object serializableObject)
        throws IOException
    {

        FileOutputStream fos = new FileOutputStream(serializedFile);
        BufferedOutputStream bufStr = new BufferedOutputStream(fos);

        OutputStream underlyingStream = null;
        if (serializedFile.getName().endsWith(".gz")) {
            underlyingStream = new GZIPOutputStream(bufStr);
        }
        else if (serializedFile.getName().endsWith(".bz2")) {
            underlyingStream = new CBZip2OutputStream(bufStr);
            // manually add bz2 prefix to make it compatible to normal bz2 tools
            // prefix has to be skipped when reading the stream with CBZip2
            fos.write("BZ".getBytes());
        }
        else {
            underlyingStream = bufStr;
        }
        ObjectOutputStream serializer = new ObjectOutputStream(underlyingStream);
        try {
            serializer.writeObject(serializableObject);

        }
        finally {
            serializer.flush();
            serializer.close();
        }
    }

    /**
     * Loads serialized Object from disk. File can be uncompressed, gzipped or bz2-compressed.
     * Compressed files must have a .gz or .bz2 suffix.
     * 
     * @param serializedFile
     * @return the deserialized Object
     * @throws IOException
     */
    @SuppressWarnings({ "unchecked" })
    public static <T> T deserialize(File serializedFile)
        throws IOException
    {
        FileInputStream fis = new FileInputStream(serializedFile);
        BufferedInputStream bufStr = new BufferedInputStream(fis);

        InputStream underlyingStream = null;
        if (serializedFile.getName().endsWith(".gz")) {
            underlyingStream = new GZIPInputStream(bufStr);
        }
        else if (serializedFile.getName().endsWith(".bz2")) {
            // skip bzip2 prefix that we added manually
            fis.read();
            fis.read();
            underlyingStream = new CBZip2InputStream(bufStr);
        }
        else {
            underlyingStream = bufStr;
        }

        ObjectInputStream deserializer = new ObjectInputStream(underlyingStream);

        Object deserializedObject = null;
        try {
            deserializedObject = deserializer.readObject();
        }
        catch (ClassNotFoundException e) {
            throw new IOException("The serialized file was probably corrupted.", e);
        }
        finally {
            deserializer.close();
        }
        return (T) deserializedObject;
    }

}
