package gcptexttospeech.aexp.com.demo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/tts")
public class TextToSpeechController {

    @Autowired
    private GcpTTSService ttsService;

    @PostMapping("/synthesize")
    public Mono<ResponseEntity<String>> synthesizeSpeech(@Valid @RequestBody ttsRequest request) {
        return ttsService.synthesizeSpeech(request.getText(), request.getLanguage(), request.getSynthName(), request.getSpeakingRate())
                .map(audioContent -> ResponseEntity.ok().body(audioContent))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("Error synthesizing speech: " + e.getMessage())));
    }
}