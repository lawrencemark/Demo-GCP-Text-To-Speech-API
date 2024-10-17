package gcptexttospeech.aexp.com.demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Base64;

@Service
public class GcpTTSService {
    private static final Logger logger = LoggerFactory.getLogger(GcpTTSService.class);
    public Mono<String> synthesizeSpeech(String text, String languageCode, String voiceName, float speakingRate) {
        return Mono.fromCallable(() -> {
            try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {

                SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

                VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                        .setLanguageCode(languageCode)
                        .setName(voiceName)
                        .build();

                // Cloud Text to Speech AudioConfiguration Parameters
                AudioConfig audioConfig = AudioConfig.newBuilder()
                        .setAudioEncoding(AudioEncoding.MP3)
                        .setSpeakingRate(speakingRate)
                        .build();

                SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);


                final String webServerPath = "http://localhost:8080/filedownload/";

                // Get the audio content as byte string and save to the local filesystem
                    ByteString audioContents = response.getAudioContent();

                logger.info("SynthesizeSpeechResponse received: {} bytes", audioContents.size());
                logger.debug("Full response: {}", response);

                    String filepath = "speech" + System.currentTimeMillis() + ".mp3";
                    try (FileOutputStream fos = new FileOutputStream(filepath)) {
                        fos.write(audioContents.toByteArray());

                        TtsRecord ttsRecord = new TtsRecord(filepath, webServerPath, System.currentTimeMillis(), audioContents);

                        return ttsRecord.getJson();
                    } catch (IOException e) {
                        return "Text to Speech Engine failed, please try later" + e.getMessage();


                    }

            }
        });
    }
}