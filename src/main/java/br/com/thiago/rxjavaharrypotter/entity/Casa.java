package br.com.thiago.rxjavaharrypotter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Casa {

    private String id;
    private String name;
    private String animal;
    private String founder;
    private List<Object> values;
}
