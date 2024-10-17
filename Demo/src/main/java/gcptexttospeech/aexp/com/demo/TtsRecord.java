package gcptexttospeech.aexp.com.demo;

import com.google.protobuf.ByteString;
import java.text.MessageFormat;

public class TtsRecord {
    public String fileName;
    public String webServerPath;
    public long systemTime;
    ByteString fileArray;

    public TtsRecord(String fileName, String webServerPath, longRe systemTime, ByteString fileArray) {
        this.fileName= fileName;
        this.systemTime = systemTime;
        this.webServerPath = webServerPath;
        this.fileArray = fileArray;
    }

    public String getJson() {
        // Manually return a JSON string
       String returnString = MessageFormat.format("\"FileName: {0}\", \"File Location: {1}\",\"System Time: {2}\"", this.fileName, this.webServerPath, this.systemTime);
       return "{" + returnString + "}";
    }

    public void writeTtsRecord() {
        // Future method to write the record and the byte array to a backend database such as Redis for caching and use by mediaservices
    }
}

