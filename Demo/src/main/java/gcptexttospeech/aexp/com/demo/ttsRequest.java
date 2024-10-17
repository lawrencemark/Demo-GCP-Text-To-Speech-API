package gcptexttospeech.aexp.com.demo;

import javax.validation.constraints.NotBlank;

public class ttsRequest {

    @NotBlank(message = "You can't turn nothing into nothing, please send text!")
    private String text;

    private String languageCode = "en-GB";
    private String voiceName = "en-GB-Wavenet-D";
    private float speakingRate = 1.0f;

    public String getText() {
    return text;
    }

    public String getSynthName() {
        return voiceName;
    }

    public String getLanguage() {
        return languageCode;
    }

    public Float getSpeakingRate() {
        return speakingRate;
    }

    public void setSynthName(String voiceName) {
        this.voiceName = voiceName;
    }

    public void setLanguage(String languageCode) {
        this.languageCode = languageCode;
    }

    public void setSpeakingRate(float speakingRate) {
        this.speakingRate = speakingRate;
    }
}