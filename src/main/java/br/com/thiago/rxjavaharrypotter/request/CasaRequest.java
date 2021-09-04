package br.com.thiago.rxjavaharrypotter.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CasaRequest {

    @SerializedName("sorting-hat-choice")
    private String idCasa;
}
