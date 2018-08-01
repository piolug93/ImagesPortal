package util;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class ImageUtil {
    public static String imageToBase64(String imageName) throws IOException {
        InputStream iSteamReader = new FileInputStream("D:\\AAA\\"+imageName);
        byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
        return "data:image;base64,"+ Base64.getEncoder().encodeToString(imageBytes);
    }
}
